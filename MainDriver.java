/****************************************************************************
 * Main Driver for Simple Database Operations (Transactions)
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
 * 
 * Data Structure:
 * 	In order to fulfill the requirement of O(log n) for time complexity,
 * 	current program uses two HashMaps, one for database record and one for
 * 	occurrence, to reach the O(1) time complexity.
 ****************************************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainDriver {
	private static Stack<Transactions> transactionStack = new Stack<Transactions>();
	private static CommandLineParser cmdParser = new CommandLineParser();
	
	public static void main(String[] args) throws IOException {
		try{
			// Create reader for standard input/command line
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String inputCmd;
			
			// Create the transaction entry
			Transactions currTransaction = new Transactions();
			currTransaction.setDBMap(new HashMap<String, Integer>());
			currTransaction.setFreqMap(new HashMap<Integer, Integer>());
			
			// Read through the whole operations from stdin
			while((inputCmd = br.readLine()) != null && inputCmd.length() != 0){
				String[] command = inputCmd.split("\\s+");
				cmdParser.parsingCmd(transactionStack, currTransaction, command);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
