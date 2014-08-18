/****************************************************************************
 * Data Structure for Transaction Entry
 * 
 * Data Structure:
 * 	dbMap - HashMap for recording data entries. Key is the String and value
 * 			is the value input from user
 * 	freqMap - HashMap for recording occurrence for values in dbMap. Key is 
 * 			  the values in dbMap, value is the occurrence of certain value
 * 			  in dbMap.
 ****************************************************************************/

import java.util.HashMap;

public class Transactions {
	private HashMap<String, Integer> dbMap = new HashMap<String, Integer>();
	private HashMap<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
	
	public HashMap<String, Integer> getDBMap(){
		return dbMap;
	}
	
	public HashMap<Integer, Integer> getFreqMap(){
		return freqMap;
	}
	
	public void setDBMap(HashMap<String, Integer> dbMap){
		this.dbMap = dbMap;
	}
	
	public void setFreqMap(HashMap<Integer, Integer> freqMap){
		this.freqMap = freqMap;
	}
}
