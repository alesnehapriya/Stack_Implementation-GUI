# Stack_Implementation-GUI
Primary Actor: End User who uses the program to insert the objects in the stack.
Stakeholders and Interest:
End User: who desires to insert the objects in the Stack.
Programmer: executes without any errors and fully functional.
Preconditions:
User has input the number of the objects to be inserted and click Submit.
Success Guarantee – Post Conditions:
1. The program gets connected with SQLite database.
2. Push:- An object inserted gets stored in the database along with Index number and successful message is shown. The pushed objects are shown in Stack bucket.
3. Pop:- An object is deleted in Last In First Out manner.
4. Size:- Determines the current size of the stack.
5. Top:- Determines the top of the stack.
6. Display:- Populates the current items/objects in Database along with “Top of the Stack”.
7. Display Through Index:- When provided index, displays the object in stored in that index.
Main Success Scenario (Basic Flow):
Input Events from Actor: End User
System Events and Responses
Program Starts
Database Connection status as Successful is shown.
Enter the number of Objects
Displays the Operations that can be performed.
Select PUSH to insert Objects
Takes the objects to be inserted as input and displays in the Stack Bucket. At the same time, objects are stored in the Database: StacksData.sqlite, Table: StackData, Column:(ObjectID, Object).
Click on POP
Check if stack is empty. If not, calculates the number of rows in the StackData database and deletes the last entered object.
Click Display
Displays the objects remaining in the Stack on the Screen.
Click Size
Displays the current size of the stack by calculating the number of rows in the Database.
Click Top
Display the recently added object.
Click Display through Index
When provided index, displays the object located in the Index.
Extensions or Alternative Scenario:
1. Error Message is generated for the following exceptions:
a. When the database connectivity is not successful.
b. When the number of objects are not entered.
c. When non-positive numbers are entered.
d. When there are no objects in the Stack, stack empty message is shown.
