
/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2020B
  Assessment: Assignment 1
  Author: Nguyen Dang Huynh Chau
  ID: s3777214
  Created  date: 29/07/2020
  Last modified: 09/09/2020
  Acknowledgement: mentiones in Readme file
*/
import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {
    private static final Scanner input = new Scanner(System.in);
    private static final LibraryManagement lib = new LibraryManagement();
    private static final ArrayList<Book> bookList = lib.getBook();
    private static final ArrayList<DVD> dvdList = lib.getDvd();
    private static final ArrayList<Journal> journalList = lib.getJournal();
    private static final ArrayList<Record> recordList = lib.getRecord();
    private static final ArrayList<Member> memberList = lib.getMember();

    public static void main(String[] args) throws IOException {
        lib.loadInformation();  //automatically load all information.
        do {
            System.out.println(displayMenu()); //display menu
            String userInput = input.nextLine(); //get input from user
            //check if user entered correct:
            //check if not number
            try {
                Integer.parseInt(userInput);
            } catch (Exception e) {
                System.out.println("Invalid input!");
                continue;
            }
            //check if not in [1, 10]:
            if (Integer.parseInt(userInput) <= 0 || Integer.parseInt(userInput) > 10) {
                System.out.println("Input must be between 1 and 10!");
                continue;
            }
            //menu:
            switch (Integer.parseInt(userInput)) {
                //Search item:
                case 1: {
                    searchItem();
                    break;
                }
                //add item:
                case 2: {
                    addItem();
                    break;
                }
                //update item:
                case 3: {
                    updateItem();
                    break;
                }
                //search member:
                case 4: {
                    searchMember();
                    break;
                }
                //add member:
                case 5: {
                    addNewMember();
                    break;
                }
                //Update member:
                case 6: {
                    updateMemberInfo();
                    break;
                }
                //Borrow Item and then update:
                case 7: {
                    String s;
                    try{
                       s = isAbleToBorrow();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("This person is not existed!");
                        break;
                    }

                    if (s == null) {
                        System.out.println("Please update this member ID before borrow");
                        break;
                    }
                    else
                        borrowItem(s);
                    break;
                }
                //Return Item:
                case 8: {
                    returnItem();
                    break;
                }
                //Save to file:
                case 9: {
                    saveToFile();
                    break;
                }
                //Quit:
                case 10: {
                    System.out.println("Goodbye!");
                    System.exit(1);
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + userInput);
            }
        } while (true);
    }

    public static String displayMenu() {
        return "Welcome to QuickLib!!!\n" +
                "********************************\n" +
                "1. Search items by keywords\n" +
                "2. Add new item\n" +
                "3. Update item info\n" +
                "4. Search members by keywords\n" +
                "5. Register new member\n" +
                "6. Update member info\n" +
                "7. Borrow items\n" +   //chưa check member có expired hay chưa, check từng cái thông tin của record!
                "8. Return items\n" +
                "9. Save data\n" +
                "10. Quit\n" +
                "**************************************\n" +
                "Enter a function (1-10): ";
    }

    //1. Search items by keywords
    public static void searchItem() {
        //Ask which type they want to search:
        String userInput = checkType();
        switch (userInput) { //Specify the cases.
            case "Book": {
                System.out.println("Enter a keyword to search: ");
                userInput = input.nextLine();
                lib.display(lib.searchBook(userInput));
                break;
            }
            case "DVD": {
                System.out.println("Enter a keyword to search: ");
                userInput = input.nextLine();
                lib.display(lib.searchDVD(userInput));
                break;
            }
            case "Journal": {
                System.out.println("Enter a keyword to search: ");
                userInput = input.nextLine();
                lib.display(lib.searchJournal(userInput));
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    //2. Add new item:
    public static void addItem() {
        //Ask type they want to add. //in case user enter wrong something, they can update it later.
        String userInput = checkType();
        switch (userInput) {
            case "Book": {
                Book newBook = addBook();
                boolean save = remindSave();
                if (save) bookList.add(newBook);
                break;
            }
            case "DVD": {
                DVD newDVD = addDVD();
                boolean save = remindSave();
                if (save) dvdList.add(newDVD);
                break;
            }
            case "Journal": {
                Journal newJournal = addJournal();
                boolean save = remindSave();
                if (save) journalList.add(newJournal);
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    //3. Update item info
    public static void updateItem() {
        String userInput = checkType();
        switch (userInput) {
            case "Book": {
                Book updateBook = new Book();
                String isbn = updateBook.askISBN(input);
                int index = lib.findBook(isbn);
                if (index == -1) {
                    System.out.println("Cannot find this book!");
                    break;
                } else {
                    updateBook = updateBook();
                    boolean save = remindSave();
                    if (save)
                        bookList.set(index, updateBook);
                    else
                        System.out.println("You will loose your information if you do not save!");
                }
                break;
            }
            case "DVD": {
                DVD updateDVD = new DVD();
                int id = updateDVD.askId(input);
                int index = lib.findDVD(id);
                if (index == -1) {
                    System.out.println("Cannot find this DVD!");
                    break;
                } else {
                    updateDVD = updateDVD();
                    boolean save = remindSave();
                    if (save)
                        dvdList.set(index, updateDVD);
                    else
                        System.out.println("You will loose your information if you do not save!");
                }
                break;
            }
            case "Journal": {
                Journal updateJournal = new Journal();
                String ISSN = updateJournal.askISSN(input);
                int index = lib.findJournal(ISSN);

                if (index == -1) {
                    System.out.println("Cannot find this Journal!");
                    break;
                } else {
                    updateJournal = updateJournal();
                    boolean save = remindSave();
                    if (save)
                        journalList.set(index, updateJournal);
                }
                break;
            }
        }
    }

    //4. Search members by keywords:
    public static void searchMember() {
        System.out.println("Enter a keyword to search a member: ");
        String member = input.nextLine();
        lib.display(lib.searchMember(member));
        lib.display(lib.searchMemberInRecord(member));
    }

    //5. Register new member:
    public static void addNewMember() {
        Member newMember = addMember();
        boolean save = remindSave();
        if (save) memberList.add(newMember);
    }

    //6. Update member info:
    public static void updateMemberInfo() {
        System.out.println("Enter the member phone to update: ");
        String number = input.nextLine();
        Member updateMember = updateMember();
        boolean save = remindSave();
        if (save) memberList.set(lib.findMember(number), updateMember);
    }

    private static Member updateMember() {
        Member m = new Member();
        String name = m.askMemberName(input);
        String id = m.askMemberID(input);
        String phone = m.askMemberPhone(input);
        String email = m.askMemberEmail(input);
        String address = m.askMemberAddress(input);
        String expiredDay = m.askMemberExpiredDay(input);
        String status = m.askMemberStatus(input, expiredDay);

        return new Member(name, id, phone, email, address, expiredDay, status);
    }

    //check if this member can borrow:
    private static String isAbleToBorrow() {
        Member m = new Member();
        String memberPhone = m.askMemberPhone(input);
        if (memberList.get(lib.findMember(memberPhone)).getStatus().toLowerCase().contains("Expired".toLowerCase())) {
            return null;
        }
        return memberPhone;
    }

    //7. Borrow items:
    private static void borrowItem(String phoneNumber) {
        Record newRecord = lib.borrow(input, phoneNumber);
        boolean save = remindSave();
        System.out.println("You must update the item and record after borrow or all information will loose!\n" +
                "That will have a great influence on facility management of this Library!");
        if (save) {
            recordList.add(newRecord);
            System.out.println("Press 9 twice to save the new update in Item and Record!");
        } else
            System.out.println("You will loose your information if you do not save!");
    }

    //8. Return items:
    private static void returnItem() {
        Record newRecord = lib.returnItem(input);
        boolean save = remindSave();
        System.out.println("You must update the item and record after borrow or all information will loose!\n" +
                "That will have a great influence on facility management of this Library!");
        if (save) {
            recordList.add(newRecord);
            System.out.println("Press 3 to update your Item and press 9 twice to save the new update in Item and Record!");
        }
    }

    //9. Save to file:
    private static void saveToFile() throws IOException {
        BufferedWriter w;
        String selection = checkFileName();
        switch (selection) {
            case "Book": {
                w = new BufferedWriter(new FileWriter(new File("src/Book.txt")));
                for (Book book : bookList) {
                    String s = String.valueOf(book);
                    w.write(s + "\n");
                }
                w.close();
                break;
            }
            case "DVD": {
                w = new BufferedWriter(new FileWriter(new File("src/DVD.txt")));
                for (DVD dvd : dvdList) {
                    String s = String.valueOf(dvd);
                    w.write(s + "\n");
                }
                w.close();
                break;
            }
            case "Journal": {
                w = new BufferedWriter(new FileWriter(new File("src/Journal.txt")));
                for (Journal journal : journalList) {
                    String s = String.valueOf(journal);
                    w.write(s + "\n");
                }
                w.close();
                break;
            }

            case "BorrowingRecord": {
                w = new BufferedWriter(new FileWriter(new File("src/BorrowingRecord.txt")));
                for (Record record : recordList) {
                    String s = String.valueOf(record);
                    w.write(s + "\n");
                }
                w.close();
                break;
            }
            case "Member": {
                w = new BufferedWriter(new FileWriter(new File("src/Member.txt")));
                for (Member member : memberList) {
                    String s = String.valueOf(member);
                    w.write(s + "\n");
                }
                w.close();
                break;
            }
        }
    }

    //remind user to save:
    private static boolean remindSave() {
        System.out.println("Do you want to save it in to file before quit? Press 'Yes' or 'No'! ");
        String userInput = getYesNoAnswer();
        System.out.println("Press number 9 to save! If you do not save you will loose your information!");
        return !userInput.equals("No");
    }

    private static String getYesNoAnswer() {
        String answer = input.nextLine();

        while (!Pattern.matches("^Yes$", answer) && !Pattern.matches("^No$", answer)) {
            System.out.println("Only enter 'Yes' or 'No' !");
            answer = input.nextLine();
        }
        return answer;
    }

    private static String checkType() {
        System.out.println("Enter a type of your item: ");
        String type = input.nextLine();

        while (!Pattern.matches("^Book$", type) && !Pattern.matches("^DVD$", type) &&
                !Pattern.matches("^Journal$", type)) {
            System.out.println("Invalid type! Please enter exactly 'Book' or 'DVD' or 'Journal'! ");
            System.out.println("Enter a type of your item: ");
            type = input.nextLine();
        }
        return type;
    }

    private static String checkFileName() {
        System.out.println("Enter a file name: ");
        String fileName = input.nextLine();

        while (!Pattern.matches("^Book$", fileName) && !Pattern.matches("^DVD$", fileName) &&
                !Pattern.matches("^Journal$", fileName) && !Pattern.matches("^BorrowingRecord$", fileName)
                && !Pattern.matches("^Member$", fileName)) {
            System.out.println("Invalid file name! Please enter exactly 'Book' or 'DVD' or 'Journal' or 'BorrowingRecord' or 'Member'! ");
            System.out.println("Enter a type of your item: ");
            fileName = input.nextLine();
        }

        return fileName;
    }

    private static Book addBook() {
        //variable for adding new item:
        Book book = new Book();

        String isbn = book.askISBN(input);

        while (!lib.uniqueISBN(isbn)) {
            System.out.println("This book is duplicated!"); //check if the book is duplicated in the begin will remind
            isbn = book.askISBN(input);                     //user to carefully check if they enter wrong! There might be
        }                                                   //book with same name but different ISBN number!

        String title = book.askItemTitle(input);
        String author = book.askAuthor(input);
        int edition = book.askEdition(input);

        String language = book.askItemLanguage(input);
        String publication = book.askItemPublication(input);
        int year = book.askItemYear(input);
        String subject = book.askItemSubject(input);
        int copies = book.askItemCopies(input);
        int borrowed = book.askItemBorrowed(input);
        String status = book.askItemStatus(input);

        while (checkStatusCopiesBorrowed(copies, borrowed, status)) {
            copies = book.askItemCopies(input);
            borrowed = book.askItemBorrowed(input);
            status = book.askItemStatus(input);
        }
        return new Book(title, author, edition, publication, year, isbn, language, subject, copies, borrowed, status);

    }

    private static DVD addDVD() {
        //variable for adding new item:
        DVD dvd = new DVD();

        int id = dvd.askId(input); //same logic with isbn number of book

        while (!lib.uniqueID(id)) {
            System.out.println("This DVD is duplicated!");
            id = dvd.askId(input);
        }

        String title = dvd.askItemTitle(input);
        String author = dvd.askDVDAuthor(input);
        String publication = dvd.askItemPublication(input);
        int year = dvd.askItemYear(input);
        String language = dvd.askItemLanguage(input);
        String subject = dvd.askItemSubject(input);
        int copies = dvd.askItemCopies(input);
        int borrowed = dvd.askItemBorrowed(input);
        String status = dvd.askItemStatus(input);

        while (checkStatusCopiesBorrowed(copies, borrowed, status)) {
            copies = dvd.askItemCopies(input);
            borrowed = dvd.askItemBorrowed(input);
            status = dvd.askItemStatus(input);
        }

        return new DVD(id, title, author, publication, year, language, subject, copies, borrowed, status);
    }

    private static Journal addJournal() {
        //variable for adding new item:
        Journal journal = new Journal();

        String issn = journal.askISSN(input); //same logic with isbn number of book

        while (!lib.uniqueISSN(issn)) {
            System.out.println("This journal is duplicated!");
            issn = journal.askISSN(input);
        }

        String title = journal.askItemTitle(input);
        String publication = journal.askItemPublication(input);
        int year = journal.askItemYear(input);

        String language = journal.askItemLanguage(input);
        String subject = journal.askItemSubject(input);
        int copies = journal.askItemCopies(input);
        int borrowed = journal.askItemBorrowed(input);
        String status = journal.askItemStatus(input);

        while (checkStatusCopiesBorrowed(copies, borrowed, status)) {
            copies = journal.askItemCopies(input);
            borrowed = journal.askItemBorrowed(input);
            status = journal.askItemStatus(input);
        }

        return new Journal(title, publication, year, issn, language, subject, copies, borrowed, status);
    }

    private static boolean checkStatusCopiesBorrowed(int copies, int borrowed, String status) {
        //check if the borrowed amount is larger than the copies amount:
        if (borrowed > copies) {
            System.out.println("Cannot hire more than the copies amount!");
            return true;
        }

        //Check if the book is still available but status is on_loan:
        if (borrowed < copies && (status.contains("On_Loan"))) {
            System.out.println("This title of book is still available! Please check input carefully");
            return true;
        }
        //Check if the book is not available but status is available:
        if (borrowed == copies && (status.contains("Available"))) {
            System.out.println("This title of book is not available! Please check input carefully");
            return true;
        }
        return false;
    }

    private static Member addMember() {
        //variable for adding new item:
        Member member = new Member();

        String phone = member.askMemberPhone(input); //A user must have an unique phone number so this is same with isbn number.

        while (!lib.uniqueNumber(phone)) {
            System.out.println("This member is duplicated!");

            phone = member.askMemberPhone(input);
        }

        String name = member.askMemberName(input);
        String id = member.askMemberID(input);


        String email = member.askMemberEmail(input);
        String address = member.askMemberAddress(input);
        String expiredDay = member.askMemberExpiredDay(input);
        String status = member.askMemberStatus(input, expiredDay);

        return new Member(name, id, phone, email, address, expiredDay, status);
    }

    //Update item: (Book)
    private static Book updateBook() {
        Book book = new Book();
        return new Book(book.askItemTitle(input), book.askAuthor(input), book.askEdition(input), book.askItemPublication(input),
                book.askItemYear(input), book.askISBN(input), book.askItemLanguage(input), book.askItemSubject(input), book.askItemCopies(input),
                book.askItemBorrowed(input), book.askItemStatus(input));
    }

    //Update item: (DVD)
    private static DVD updateDVD() {
        DVD dvd = new DVD();
        return new DVD(dvd.askId(input), dvd.askItemTitle(input), dvd.askDVDAuthor(input), dvd.askItemPublication(input),
                dvd.askItemYear(input), dvd.askItemLanguage(input), dvd.askItemSubject(input), dvd.askItemCopies(input),
                dvd.askItemBorrowed(input), dvd.askItemStatus(input));
    }

    //Update Item (Journal):
    protected static Journal updateJournal() {
        Journal j = new Journal();
        return new Journal(j.askItemTitle(input), j.askItemPublication(input), j.askItemYear(input), j.askISSN(input),
                j.askItemLanguage(input), j.askItemSubject(input), j.askItemCopies(input), j.askItemBorrowed(input), j.askItemStatus(input));
    }
}