SimpleTransactionDatabase
=========================

Simple Transaction Database Implementation

The transaction database supports following operations:<br>
  SET - record a new data entry <br>
  GET - search of a data entry in database<br>
  UNSET - clear the record of a data entry<br>
  NUMEQUALTO - search the number of occurrence of certain value<br>
 	END - exit the program<br>
 	BEGIN - start a new transaction entry<br>
 	ROLLBACK - discard current transaction and go back to previous one<br>
 	COMMIT - close all pending transactions and make the changes permanently <br>
 	
Data Structure:<br>
  Current program uses two HashMaps, one for database record and one for occurrence tracking.
