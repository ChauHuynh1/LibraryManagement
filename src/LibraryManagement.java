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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//In this class I use it for searching, borrowing and returning:

public class LibraryManagement {
    private ArrayList<Record> record;
    private ArrayList<Member> member;
    private ArrayList<Book> book;
    private ArrayList<DVD> dvd;
    private ArrayList<Journal> journal;

    public LibraryManagement() {
        record = new ArrayList<>();
        member = new ArrayList<>();
        book = new ArrayList<>();
        dvd = new ArrayList<>();
        journal = new ArrayList<>();
    }

    public ArrayList<Record> getRecord() {
        return record;
    }
    public ArrayList<Member> getMember() {
        return member;
    }
    public ArrayList<Book> getBook() {
        return book;
    }
    public ArrayList<DVD> getDvd() {
        return dvd;
    }
    public ArrayList<Journal> getJournal() {
        return journal;
    }

    //Start: When the program starts, it automatically loads the data.
    //Load Item, Member, Borrowing Record file:
    public void loadInformation()  {
        try {
            loadMemberFromFile();
        } catch(Exception e) {
            System.out.println("Cannot load Member File!");
        }

        try {
            loadBorrowingRecordFromFile();
        } catch(Exception e) {
            System.out.println("Cannot load Borrowing Record File!");
        }
        try {
            loadBookFromFile();
        } catch(Exception e) {
            System.out.println("Cannot load Book File!");
        }
        try {
            loadDVDFromFile();
        } catch(Exception e) {
            System.out.println("Cannot load DVD File!");
        }
        try {
            loadJournalFromFile();
        } catch(Exception e) {
            System.out.println("Cannot load Journal File!");
        }

    }


    //Load Member from file:
    private void loadMemberFromFile() throws IOException {
        member.clear(); // clear all member (to not append) after reloading them.
        List<String> lines = Files.readAllLines(Paths.get("src/TextFile/Member.txt")); // get input from file line by line

        for (String line : lines) {
            String[] fields = line.split(";");   // split when there is a ";"
            // split input into an array and add them to the new object member:

            String name = fields[0].trim(); // trim white spaces at the beginning and the end of String
            String ID = fields[1].trim();
            String phone = fields[2].trim();
            String email = fields[3].trim();
            String address = fields[4].trim();
            String date = fields[5].trim();
            String status = fields[6].trim();
            member.add(new Member(name, ID, phone, email, address, date, status));
        }

    }

    //Load Book from file:
    private void loadBookFromFile() throws IOException {
        book.clear(); // clear all book (to not append) after reloading them.
        List<String> lines = Files.readAllLines(Paths.get("src/TextFile/Book.txt")); // get input from file line by line

        for (String l : lines) {
            String[] array = l.split(";");   // split them when there is a ";"
            // split input into an array and add them to the new object:
            String title = array[0].trim();
            String author = array[1].trim();
            int edition = Integer.parseInt(array[2].trim());
            String publication = array[3].trim();
            int year = Integer.parseInt(array[4].trim());
            String isbn = array[5].trim();
            String language = array[6].trim();
            String subject = array[7].trim();
            int copies = Integer.parseInt(array[8].trim());
            int borrowed = Integer.parseInt(array[9].trim());
            String status = array[10].trim();
            book.add(new Book(title, author, edition, publication, year, isbn, language, subject, copies, borrowed, status));
        }
    }

    //Load DVD from file:
    private void loadDVDFromFile() throws IOException {
        dvd.clear(); // clear all dvd (to not append) after reloading them.
        List<String> lines = Files.readAllLines(Paths.get("src/TextFile/DVD.txt")); // get input from file line by line
        for (String l : lines) {
            String[] array = l.split(";");   // split them when there is a ";"
            // split input into an array and add them to the new object:
            int id = Integer.parseInt(array[0].trim());
            String title = array[1].trim();
            String author = array[2].trim();
            String publication = array[3].trim();
            int year = Integer.parseInt(array[4].trim());
            String language = array[5].trim();
            String subject = array[6].trim();
            int copies = Integer.parseInt(array[7].trim());
            int borrowed = Integer.parseInt(array[8].trim());
            String status = array[9].trim();
            dvd.add(new DVD(id, title, author, publication, year, language, subject, copies, borrowed, status));
        }
    }

    //Load Journal from file:
    private void loadJournalFromFile() throws IOException {
        journal.clear(); // clear all journal (to not append) after reloading them.
        List<String> lines = Files.readAllLines(Paths.get("src/TextFile/Journal.txt")); // get input from file line by line
        for (String l : lines) {
            String[] array = l.split(";");   // split them when there is a ";"
            // split input into an array and add them to the new object:
            String title = array[0].trim();
            String publication = array[1].trim();
            int year = Integer.parseInt(array[2].trim());
            String ISSN = array[3].trim();
            String language = array[4].trim();
            String subject = array[5].trim();
            int copies = Integer.parseInt(array[6].trim());
            int borrowed = Integer.parseInt(array[7].trim());
            String status = array[8].trim();
            journal.add(new Journal(title, publication, year, ISSN, language, subject, copies, borrowed, status));
        }
    }

    //Load Borrowing Record from file:
    private void loadBorrowingRecordFromFile() throws IOException {
        record.clear(); // clear all record (to not append) after reloading them.
        List<String> lines = Files.readAllLines(Paths.get("src/TextFile/BorrowingRecord.txt")); // get input from file line by line

        for (String l : lines) {
            String[] fields = l.split(";"); // split them when there is a ";"
            // split input into an array of Strings
            String phoneNumber = fields[0].trim();
            String member = fields[1].trim();
            String type = fields[2].trim();
            String title = fields[3].trim();
            int quantity = Integer.parseInt(fields[4].trim());
            String status = fields[5].trim();
            String transactionDay = fields[6].trim();
            String expectedReturnDay = fields[7].trim();
            String returnedDay = fields[8].trim();
            record.add(new Record(phoneNumber, member, type, title, quantity, status, transactionDay, expectedReturnDay, returnedDay));
        }

    }

    public void display(Object object) {
        ArrayList<Object> result = (ArrayList<Object>) object;
        int count = 0;
        while (true) {
            boolean next = true;
            boolean pre = true;

            for (int i = 0; i < 10 && count < result.size(); i++) {
                System.out.println(result.get(count).toString());
                count++;
            }

            if (count == result.size())
                next = false;
            if (count <= 10)
                pre = false;

            Scanner input = new Scanner(System.in);
            System.out.println("Enter a function: (n: next page, p: previous page, q: quit) ");
            String user = input.next();
            //Check if user can go to next page, and enter correct or not:
            while (!user.equals("n") && !user.equals("p") && !user.equals("q")
                    || (!next && user.equals("n")) || (!pre && user.equals("p"))) {
                if (!next && user.equals("n"))
                    System.out.println("Cannot go to next page!");
                else if (!pre && user.equals("p"))
                    System.out.println("Cannot go to previous page!");
                else
                    System.out.println("Invalid input!");
                System.out.println("Enter a function: (n: next page, p: previous page, q: quit) ");
                user = input.next();
            }
            if (user.equals("q"))
                break;
            if (user.equals("p"))
                count = count - (20 + count % 10);
        }
    }

    //   Search members by keywords: Search and display members whose info containing all the keywords.
//   If the keywords are empty, display all members. The program displays 10 matched members per page (screen),
//   press 'n' to go to the next page, 'p' to go to the previous page, and 'q' for quit.
//   For each member satisfying the search criteria, display all
//   the member info including the current borrow record if he/she borrows any items.
    public ArrayList<Member> searchMember(String keyword) {
        ArrayList<Member> result = new ArrayList<>();
        if (keyword.isEmpty())
            display(member);
        else {
            for (Member m : member) {
                if (m.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        m.getID().toLowerCase().contains(keyword.toLowerCase()) || m.getPhone().contains(keyword) ||
                        m.getEmail().toLowerCase().contains(keyword.toLowerCase()) || m.getAddress().contains(keyword) ||
                        m.getExpiredDay().contains(keyword) || m.getStatus().contains(keyword))
                    result.add(m);
            }
        }
        return result;
    }

    //check phoneNumber unique:
    public boolean uniqueNumber(String phoneNumber) {
        for (Member m : member) {
            if (m.getPhone().contains(phoneNumber)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Record> searchMemberInRecord(String keyword) {
        ArrayList<Record> result = new ArrayList<>();

        for (Record r : record) {
            if (r.getName().toLowerCase().contains(keyword.toLowerCase()) || r.getPhoneNumber().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(r);
            }
        }

        return result;
    }

    //    Search items by keywords: Search and display items containing all the keywords. If the keywords are empty,
//    display all items. The program displays 10 matched items per page (screen), press 'n' to go to the next page,
//    'p' to go to the previous page, and 'q' for quit. For each items satisfying the search criteria,
//    display all the item info.

    //search Book object:
    public ArrayList<Book> searchBook(String keyword) {
        ArrayList<Book> result = new ArrayList<>();
        if (keyword.isEmpty())
            display(book);
        else {
            for (Book b : book) {
                if (b.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        b.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                        b.getPublication().toLowerCase().contains(keyword.toLowerCase()) ||
                        b.getISBN().contains(keyword) || b.getLanguage().toLowerCase().contains(keyword.toLowerCase()) ||
                        b.getSubject().toLowerCase().contains(keyword.toLowerCase()) || b.getStatus().toLowerCase().contains(keyword.toLowerCase()))
                    result.add(b);
            }
        }
        return result;
    }

    //search DVD object:
    public ArrayList<DVD> searchDVD(String keyword) {
        ArrayList<DVD> result = new ArrayList<>();
        if (keyword.isEmpty())
            display(dvd);
        else {
            for (DVD d : dvd) {
                if (d.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        d.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                        d.getPublication().toLowerCase().contains(keyword.toLowerCase()) ||
                        d.getLanguage().toLowerCase().contains(keyword.toLowerCase()) ||
                        d.getSubject().toLowerCase().contains(keyword.toLowerCase()) ||
                        d.getStatus().toLowerCase().contains(keyword.toLowerCase()))
                    result.add(d);
            }
        }
        return result;
    }

    //check ID unique:
    public boolean uniqueID(int ID) {
        for (DVD d : dvd) {
            if (d.getId() == ID) {
                return false;
            }
        }
        return true;
    }

    //check ISBN unique:
    public boolean uniqueISBN(String ISBN) {
        for (Book b : book) {
            if (b.getISBN().toLowerCase().contains(ISBN.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    //search Journal object:
    public ArrayList<Journal> searchJournal(String keyword) {
        ArrayList<Journal> result = new ArrayList<>();
        if (keyword.isEmpty())
            display(journal);
        else {
            for (Journal j : journal) {
                if (j.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        j.getPublication().toLowerCase().contains(keyword.toLowerCase()) ||
                        j.getISSN().contains(keyword) || j.getLanguage().toLowerCase().contains(keyword.toLowerCase()) ||
                        j.getSubject().contains(keyword.toLowerCase()) ||
                        j.getStatus().toLowerCase().contains(keyword.toLowerCase()))
                    result.add(j);
            }
        }
        return result;
    }

    //check ISSN unique:
    public boolean uniqueISSN(String ISSN) {
        for (Journal j : journal) {
            if (j.getISSN().toLowerCase().contains(ISSN.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    // Methods contribute for updateItemInfo method
    public int findBook(String isbn) {
        //find isbn of this book and delete it then rewrite it:
        int i;
        for (i = 0; i < book.size(); i++) {
            if (book.get(i).getISBN().contains(isbn))
                return i;
        }
        return -1;
    }

    public int findJournal(String issn) {
        //find issn of this book and delete it then rewrite it:
        int i;
        for (i = 0; i < journal.size(); i++) {
            if (journal.get(i).getISSN().contains(issn))
                return i;
        }
        return -1;
    }

    public int findDVD(int id) {
        //find id of this book and delete it then rewrite it:
        int i;
        for (i = 0; i < dvd.size(); i++) {
            if(dvd.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    // Methods contribute for updateMemberInfo method:
    public int findMember(String number) {
        for (int i = 0; i < member.size(); i++) {
            if (member.get(i).getPhone().equals(number)) {
                return i;
            }
        }
        return -1;
    }

    private boolean ableToBorrow(String phoneNumber) {
        int borrowTime = 0;
        int returnTime = 0;
        for (int i = 0; i < record.size(); i++){
            if(record.get(i).getPhoneNumber().contains(phoneNumber.toLowerCase()) &&
                    record.get(i).getStatus().toLowerCase().contains("Borrow".toLowerCase())) {
                borrowTime++;
            }
            if(record.get(i).getPhoneNumber().toLowerCase().contains(phoneNumber.toLowerCase()) &&
                    record.get(i).getStatus().toLowerCase().contains("Return".toLowerCase())) {
                returnTime++;
            }

        }

        return borrowTime < 5 || returnTime != 0;
    }

    public Record borrow(Scanner input, String memberPhone) {

        Member m = new Member();
        Item item = new Item();
        Record r = new Record();

        int index = findMember(memberPhone);

        while (index == -1) {
            System.out.println("Member is not existed!");
            memberPhone = m.askMemberPhone(input);
            index = findMember(memberPhone);
        }

        while(!ableToBorrow(memberPhone)) {
            System.out.println("This person cannot borrow more item!");
            memberPhone = m.askMemberPhone(input);
        }

        String type = item.askItemType(input);


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.now();


        String expectedReturnDay = null;
        String title = null;

        switch (type) {
            case "Book": {
                Book b = new Book();
                String isbn = b.askISBN(input);

                while (findBook(isbn) == -1) {
                    System.out.println("No book detected!");
                    isbn = b.askISBN(input);
                }

                while (book.get(findBook(isbn)).getBorrowed() == book.get(findBook(isbn)).getCopies()) {
                    System.out.println("All on loan!");
                    isbn = b.askISBN(input);
                }

                title = book.get(findBook(isbn)).getTitle();

                expectedReturnDay = r.askExpectedReturnDay(input);
                while (countDay(dateFormatter.format(date), expectedReturnDay) < 0 ||
                        countDay(dateFormatter.format(date), expectedReturnDay) > 14) {
                    if (countDay(dateFormatter.format(date), expectedReturnDay) < 0)
                        System.out.println("Cannot enter the day in the past!");
                    else if (countDay(dateFormatter.format(date), expectedReturnDay) > 14)
                        System.out.println("Cannot borrow more than 14 days!");
                    expectedReturnDay = r.askExpectedReturnDay(input);
                }


                book.get(findBook(isbn)).setBorrowed(book.get(findBook(isbn)).getBorrowed() + 1);

                if (book.get(findBook(isbn)).getCopies() == book.get(findBook(isbn)).getBorrowed())
                    book.get(findBook(isbn)).setStatus("On_Loan");

                break;
            }
            case "DVD": {
                DVD d = new DVD();
                int id = d.askId(input);


                while (dvd.get(findDVD(id)).getBorrowed() == dvd.get(findDVD(id)).getCopies()) {
                    System.out.println("All on loan!");
                    id = d.askId(input);
                }


                title = dvd.get(findDVD(id)).getTitle();

                expectedReturnDay = r.askExpectedReturnDay(input);
                while (countDay(dateFormatter.format(date), expectedReturnDay) < 0 ||
                        countDay(dateFormatter.format(date), expectedReturnDay) > 7) {
                    if(countDay(dateFormatter.format(date), expectedReturnDay) < 0)
                        System.out.println("Cannot enter the day in the past!");
                    else if (countDay(dateFormatter.format(date), expectedReturnDay) > 7)
                        System.out.println("Cannot borrow more than 7 days");
                    expectedReturnDay = r.askExpectedReturnDay(input);
                }

                dvd.get(findDVD(id)).setBorrowed(dvd.get(findDVD(id)).getBorrowed() + 1);
                if (dvd.get(findDVD(id)).getCopies() == dvd.get(findDVD(id)).getBorrowed())
                    dvd.get(findDVD(id)).setStatus("On_Loan");

                break;
            }
            case "Journal": {
                Journal j = new Journal();

                String issn = j.askISSN(input);

                while (journal.get(findJournal(issn)).getBorrowed() == journal.get(findJournal(issn)).getCopies()) {
                    System.out.println("All on loan!");
                    issn = j.askISSN(input);
                }

                title = journal.get(findJournal(issn)).getTitle();


                expectedReturnDay = r.askExpectedReturnDay(input);
                while (countDay(dateFormatter.format(date), expectedReturnDay) < 0 ||
                        countDay(dateFormatter.format(date), expectedReturnDay) > 7) {
                    if(countDay(dateFormatter.format(date), expectedReturnDay) < 0)
                        System.out.println("Cannot enter the day in the past!");
                    else if (countDay(dateFormatter.format(date), expectedReturnDay) > 7)
                        System.out.println("Cannot borrow more than 7 days");
                    expectedReturnDay = r.askExpectedReturnDay(input);
                }

                journal.get(findJournal(issn)).setBorrowed(journal.get(findJournal(issn)).getBorrowed() + 1);

                if (journal.get(findJournal(issn)).getCopies() == journal.get(findJournal(issn)).getBorrowed())
                    journal.get(findJournal(issn)).setStatus("On_Loan");

                break;
            }
        }

        //Data field for Record: phone number; member, type, title, quantity, status, transactionDay, expectedReturnDay, returnedDay
        return new Record(memberPhone, member.get(index).getName(), type, title, 1, "Borrow",
                dateFormatter.format(date), expectedReturnDay, "0");
    }

    public Record returnItem(Scanner input){

        Member m = new Member();
        Item item = new Item();
        Record r = new Record();

        String memberPhone = m.askMemberPhone(input);

        int index = findMember(memberPhone);

        while (index == -1) {
            System.out.println("Member is not existed!");
            memberPhone = m.askMemberPhone(input);
            index = findMember(memberPhone);
        }

        String type = item.askItemType(input);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.now();

        String expectedReturnDay = null;
        String title = null;

        switch (type) {
            case "Book": {
                Book b = new Book();
                String isbn = b.askISBN(input);

                while (findBook(isbn) == -1) {
                    System.out.println("No book detected!");
                    isbn = b.askISBN(input);
                }

                title = book.get(findBook(isbn)).getTitle();

                expectedReturnDay = r.askExpectedReturnDay(input);
                while (countDay(dateFormatter.format(date), expectedReturnDay) < 0 ||
                        countDay(dateFormatter.format(date), expectedReturnDay) > 14) {
                    if (countDay(dateFormatter.format(date), expectedReturnDay) < 0)
                        System.out.println("Cannot enter the day in the past!");
                    else if (countDay(dateFormatter.format(date), expectedReturnDay) > 14)
                        System.out.println("Cannot borrow more than 14 days!");
                    expectedReturnDay = r.askExpectedReturnDay(input);
                }


                book.get(findBook(isbn)).setBorrowed(book.get(findBook(isbn)).getCopies() + 1);

                break;
            }
            case "DVD": {
                DVD d = new DVD();
                int id = d.askId(input);

                title = dvd.get(findDVD(id)).getTitle();

                expectedReturnDay = r.askExpectedReturnDay(input);
                while (countDay(dateFormatter.format(date), expectedReturnDay) < 0 ||
                        countDay(dateFormatter.format(date), expectedReturnDay) > 7) {
                    if(countDay(dateFormatter.format(date), expectedReturnDay) < 0)
                        System.out.println("Cannot enter the day in the past!");
                    else if (countDay(dateFormatter.format(date), expectedReturnDay) > 7)
                        System.out.println("Cannot borrow more than 7 days");
                    expectedReturnDay = r.askExpectedReturnDay(input);
                }
                dvd.get(findDVD(id)).setBorrowed(dvd.get(findDVD(id)).getCopies() + 1);
                break;
            }
            case "Journal": {
                Journal j = new Journal();

                String issn = j.askISSN(input);

                title = journal.get(findJournal(issn)).getTitle();


                expectedReturnDay = r.askExpectedReturnDay(input);
                while (countDay(dateFormatter.format(date), expectedReturnDay) < 0 ||
                        countDay(dateFormatter.format(date), expectedReturnDay) > 7) {
                    if(countDay(dateFormatter.format(date), expectedReturnDay) < 0)
                        System.out.println("Cannot enter the day in the past!");
                    else if (countDay(dateFormatter.format(date), expectedReturnDay) > 7)
                        System.out.println("Cannot borrow more than 7 days");
                    expectedReturnDay = r.askExpectedReturnDay(input);
                }
                journal.get(findJournal(issn)).setBorrowed(journal.get(findJournal(issn)).getCopies() + 1);
                break;
            }
        }

        String returnedDay = r.askReturnDay(input);
        System.out.println("Your late fee is:" + countDay(expectedReturnDay, returnedDay) * 0.1);


        return new Record(memberPhone, member.get(index).getName(), type, title, 1, "Return",
                dateFormatter.format(date), expectedReturnDay, returnedDay);
    }

    public int countDay (String day1, String day2) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        int numberOfDay = 0;
        try {
            Date date1 = myFormat.parse(day1);
            Date date2 = myFormat.parse(day2);
            long diff = date2.getTime() - date1.getTime();
            numberOfDay = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return numberOfDay;
    }
}