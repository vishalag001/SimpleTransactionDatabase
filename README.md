SimpleTransactionDatabase
=========================

Simple Transaction Database Implementation

The transaction database supports following operations:
  SET - record a new data entry
  GET - search of a data entry in database
  UNSET - clear the record of a data entry
  NUMEQUALTO - search the number of occurrence of certain value
 	END - exit the program
 	BEGIN - start a new transaction entry
 	ROLLBACK - discard current transaction and go back to previous one
 	COMMIT - close all pending transactions and make the changes permanently 
 	
Data Structure:
  Current program uses two HashMaps, one for database record and one for occurrence tracking.
