RMIT University Vietnam
Course: INTE2512 Object-Oriented Programming
Semester: 2020B
Assessment name: Assignment 1
Author Name: Nguyen Dang Huynh Chau
ID Number: s3777214

1. INTRODUCTION
One short paragraph to describe what this software is about.

QuickLib is a user-friendly program which enables librarians to access to the library's member and item database. An item can either be a book, a journal or a DVD. A book has a title, authors, edition, publication, year, ISBN (13 or 10 digits), language, subject, and status (available or on loan) A journal has a title, publication, year, ISSN (8 digits), language, subject, and status. A DVD has a title, authors, publication, year, language, subject, and status. A member has full name, ID (a driver’s license number or a passport number), phone, email, address, expired date (same as the expired date of the ID provided), and status (active or expired).

2. FEATURES
List the implemented features of the software in point-form.

    1. Search items by keywords: Perform a search Item by keywords, it could be the keyword from any field of the item list
    2. Add new item: Add new item to the list, can only be Book, Journal, and DVD
    3. Update item info: Updating existing Item information, select the item ID to update it
    4. Search members by keywords: Perform a search Member by keywords, it could be the keyword from any field of the member list
    5. Register new member: Register new member to the list
    6. Update member info: Update current information of existing Member in the list, member can extend their expire date
    7. Borrow items: Perform a borrow for a Member, only items which is in stock can be borrowed.
    8. Return items: Return a borrowed item for a Member, if the return date is late, it will automatically calculate late fee for Member and add to their record.
    9. Save data: Save all existing information
    10. Quit: Save all existing information and exit program

3. DESIGN
Describe major design decisions and major algorithms.

Firstly, search Item is based on the searching habit of the author. It will ask which type the user want to search first, and check if the keyword searching exist.
Secondly, add and update Item is also mostly same with searching. It will ask which type the user want to add or update first, and check if that item is already exist to eleminate the duplication. And program will ask if the user want to save or not. In the search, register and update is all the same with search, add and update Item.

4. INSTALLATION
InteliJ IDEA Ultimate
Java JDK 11


5. KNOWN BUGS
Date: in the beginning, the date when the user enter are not checked properly:
For example: If user enter 29/99/2020 this program still thinks that is correct!
A reference for this date checking is found so this problem is solved!

ISSN: this ISSN number is only check whether it contains 8 digit or not when it is entered.
After that, in order to solve this problem an algorithms for checking ISSN is found so that this problem is solved.


6. ACKNOWLEDGEMENT
Dr. Tran Ngoc Quang who have provided me with valuable support and guidance in the completion of this project.
course Object-Oriented Programming, the lectures.
https://www.geeksforgeeks.org/program-check-date-valid-not/ //For checking date which is entered as a string in java.
https://www.moreofless.co.uk/validate-isbn-13-java/ // for ISBN
https://www.tutorialspoint.com/java/java_files_io.htm //for java file
https://www.regextester.com/19 //for regex
https://en.wikipedia.org/wiki/International_Standard_Serial_Number //for ISSN
