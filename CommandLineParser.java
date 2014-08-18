/****************************************************************************
 * Command Line Parser for Standard Input (stdin)
 * 
 * Operations support:
 * 	SET - record a new data entry
 * 	GET - search of a data entry in database
 * 	UNSET - clear the record of a data entry
 * 	NUMEQUALTO - search the number of occurrence of certain value
 * 	END - exit the program
 * 	BEGIN - start a new transaction entry
 * 	ROLLBACK - discard current transaction and go back to previous one
 * 	COMMIT - close all pending transactions and make the changes
 ****************************************************************************/

import java.util.HashMap;
import java.util.Stack;

public class CommandLineParser {
	
	/**
	 * Parsing the command line inputs w.r.t. each defined operation
	 * @param transactionStack - the stack storing the transactions
	 * @param currTransaction - current transaction being executed
	 * @param cmd - current command line input
	 */
	public void parsingCmd(Stack<Transactions> transactionStack, 
						   Transactions currTransaction,
						   String[] cmd){
		
		HashMap<String, Integer> dbMap = currTransaction.getDBMap();
		HashMap<Integer, Integer> freqMap = currTransaction.getFreqMap();
		
		// Operation is defined as first command line input
		switch(cmd[0]){
		case "SET":
			if(cmd.length != 3){
				exitProgram("Input Arguments Mismatch!", 1);
			} else {
				setMap(dbMap, freqMap, cmd);
				System.out.println();
			}
			break;
		case "GET":
			if(cmd.length != 2){
				exitProgram("Input Arguments Mismatch!", 1);
			} else {
				getValues(dbMap, cmd);
			}
			break;
		case "UNSET":
			if(cmd.length != 2){
				exitProgram("Input Arguments Mismatch!", 1);
			} else {
				unsetMap(dbMap, freqMap, cmd);
				System.out.println();
			}
			break;
		case "NUMEQUALTO":
			if(cmd.length != 2){
				exitProgram("Input Arguments Mismatch!", 1);
			} else {
				int frequency = getFreq(freqMap, Integer.parseInt(cmd[1]));
				String s = Integer.toString(frequency);
				System.out.println(s);
			}
			break;
		case "END":
			exitProgram(" ", 0);
			break;
		case "BEGIN":
			// Create new transaction entry when "BEGIN" is reached
			Transactions transaction = new Transactions();
			transaction.setDBMap(new HashMap<String, Integer>(dbMap));
			transaction.setFreqMap(new HashMap<Integer, Integer>(freqMap));
			
			// Push previous transaction into stack for "ROLLBACK"
			transactionStack.push(transaction);
			System.out.println();
			break;
		case "ROLLBACK":
			if(transactionStack == null || transactionStack.isEmpty()){
				System.out.println("NO TRANSACTION");
			} else {
				// Pop from the stack to obtain previous transaction
				Transactions newTransaction = transactionStack.pop();
				currTransaction.setDBMap(newTransaction.getDBMap());
				currTransaction.setFreqMap(newTransaction.getFreqMap());
				System.out.println();
			}
			break;
		case "COMMIT":
			if(transactionStack == null || transactionStack.isEmpty()){
				System.out.println("NO TRANSACTION");
			} else {
				System.out.println();
				transactionStack.clear();
			}
			break;
		default:
			exitProgram("No Such Command: " + cmd[0], 1);
			break;
		}
	}
	
	/**
	 * Exiting current program 
	 * @param s - String which is shown before exiting
	 * @param status - 0 for normal, 1 when error occur
	 */
	private void exitProgram(String s, int status){
		System.out.println(s);
		System.exit(status);
	}
	
	/**
	 * Calculate the occurrence of certain values
	 * @param freqMap - the HashMap recording occurrence
	 * @param num - the desired value
	 * @return number of occurrence
	 */
	private int getFreq(HashMap<Integer, Integer> freqMap, int num){
		if(freqMap.containsKey(num)){
			return freqMap.get(num);
		} else {
			return 0;
		}
	}
	
	/**
	 * Update the database hashmap and frequency hashmap
	 * @param dbMap - the HashMap recording database entries
	 * @param freqMap - the HashMap recording occurrence
	 * @param cmd - command line input
	 */
	private void setMap(HashMap<String, Integer> dbMap, HashMap<Integer, Integer> freqMap, String[] cmd){
		String key = cmd[1];
		int value = Integer.parseInt(cmd[2]);
		
		// Overwrite the current value -> adjust frequency map
		if(dbMap.containsKey(key)){
			int originalVal = dbMap.get(key);
			decreaseFreq(freqMap, originalVal);
		}
		
		increaseFreq(freqMap, value);
		dbMap.put(key, value);
	}
	
	/**
	 * Obtain the record value from input key
	 * @param dbMap - the HashMap recording database entries
	 * @param cmd - command line input
	 */
	private void getValues(HashMap<String, Integer> dbMap, String[] cmd){
		if(dbMap.containsKey(cmd[1])){
			System.out.println(Integer.toString(dbMap.get(cmd[1])));
		} else {
			System.out.println("NULL");
		}
	}
	
	/**
	 * Increase the occurrence of the key
	 * @param freqMap - the HashMap recording occurrence
	 * @param key - the key to be increased
	 */
	private void increaseFreq(HashMap<Integer, Integer> freqMap, int key){
		if(freqMap.containsKey(key)){
			freqMap.put(key, freqMap.get(key) + 1);
		} else {
			freqMap.put(key, 1);
		}
	}
	
	/**
	 * Decrease the occurrence of the key
	 * @param freqMap - the HashMap recording occurrence
	 * @param key - the key to be decreased
	 */
	private void decreaseFreq(HashMap<Integer, Integer> freqMap, int key){
		if(freqMap.get(key) > 1){
			freqMap.put(key, freqMap.get(key) - 1);
		} else {
			freqMap.remove(key);
		}
	}
	
	/**
	 * Clear the indicated data entry
	 * @param dbMap - the HashMap recording database entries
	 * @param freqMap - the HashMap recording occurrence
	 * @param cmd - command line input
	 */
	private void unsetMap(HashMap<String, Integer> dbMap, HashMap<Integer, Integer> freqMap, String[] cmd){
		if(dbMap.containsKey(cmd[1])){
			int val = dbMap.remove(cmd[1]);
			decreaseFreq(freqMap, val);
		}
	}
}
