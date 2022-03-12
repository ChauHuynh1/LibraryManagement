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
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Item {
    // Data field of an item include: type (book, journal, DVD), title, publication, year, language, subject,
    //copies, borrowed, status (available, on_hired).
    // (copies is the total quantity, and borrowed is the number of item is being borrowed
    // at the moment for calculating status)
    private String type;
    private String title;
    private String publication;
    private int year;
    private String language;
    private String subject;
    private int copies;
    private int borrowed;
    private String status;

    public Item() {
        type = "Unknown";
        title = "Unknown";
        publication = "Unknown";
        year = 0;
        language = "Unknown";
        subject = "Unknown";
        copies = 0;
        borrowed = 0;
        status = "On loan";
    }

    public Item(String type, String title, String publication, int year, String language, String subject,
                int copies, int borrowed, String status) {
        this.type = type;
        this.title = title;
        this.publication = publication;
        this.year = year;
        this.language = language;
        this.subject = subject;
        this.copies = copies;
        this.borrowed = borrowed;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }
    public String getPublication() {
        return publication;
    }
    public int getYear() {
        return year;
    }
    public String getLanguage() {
        return language;
    }
    public String getSubject() {
        return subject;
    }
    public int getCopies() {
        return copies;
    }
    public int getBorrowed() {
        return borrowed;
    }
    public String getStatus() {
        return status;
    }
    public void setBorrowed(int borrowed) {
        this.borrowed = borrowed;
    }
    public void setStatus (String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return type + "; " + title + "; " + publication + "; " + year + language + "; " + subject + "; " + copies + "; " +
               borrowed + "; " + status + "; ";
    }

    //Check if the type is entered correctly:
    protected String askItemType(Scanner input) {

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

    //Enter the title:
    public String askItemTitle(Scanner input) {
        System.out.println("Enter a title: ");
        String title = input.nextLine();

        while (!title.isEmpty()) {
            System.out.println("Must enter a title for your item! Can write Unknown instead!");
            System.out.println("Enter a title: ");
            title = input.nextLine();
        }

        return title;
    }

    //Enter publication:
    public String askItemPublication(Scanner input) {
        System.out.println("Enter a publication: ");
        String publication = input.nextLine();

        while (!publication.isEmpty()) {
            System.out.println("Must enter a publication for your item! Can write Unknown instead!");
            System.out.println("Enter a publication: ");
            publication = input.nextLine();
        }

        return publication;
    }

    //Enter a year:
    public int askItemYear(Scanner input) {
        System.out.println("Enter the publish year: ");
        String year = input.nextLine();
        int currentYear= Calendar.getInstance().get(Calendar.YEAR);

        while (!Pattern.matches("^\\d{4}$", year) || (Integer.parseInt(year) > currentYear)) {
            System.out.println("Invalid year!");
            System.out.println("Enter the publish year: ");
            year = input.nextLine();
        }

        return Integer.parseInt(year);
    }

    //Enter the language of Item:
    public String askItemLanguage(Scanner input) {
        System.out.println("Enter the language: ");
        String language = input.nextLine();

        while (!Pattern.matches("^[[a-z-A-Z]{1,}[\\s]]+$", language)) {
            System.out.println("Must enter a language for your item! Can write Unknown instead!");
            System.out.println("Enter the language: ");
            language = input.nextLine();
        }

        return language;
    }

    //Enter the subject of Item:
    public String askItemSubject(Scanner input) {
        System.out.println("Enter the subject: ");
        String subject = input.nextLine();

        while (!subject.isEmpty()) {
            System.out.println("Must enter a subject for your item! Can write Unknown instead!");
            System.out.println("Enter the subject: ");
            subject = input.nextLine();
        }

        return subject;
    }

    //Enter the number of copies of the Item:
    public int askItemCopies(Scanner input) {
        System.out.println("Enter the amount of copies: ");
        String copies = input.nextLine();

        while (!Pattern.matches("^\\d+$", copies)) {
            System.out.println("Must enter a number!");
            System.out.println("Enter the amount of copies: ");
            copies = input.nextLine();
        }
        return Integer.parseInt(copies);
    }
    //Enter the number of the current item are on loan:
    public int askItemBorrowed(Scanner input) {
        System.out.println("Enter an amount of borrow: ");
        String amount = input.nextLine();

        while (!Pattern.matches("^\\d+$", amount)) {
            System.out.println("An amount must be an integer!");
            System.out.println("Enter an amount of borrow: ");
            amount = input.nextLine();
        }

        return Integer.parseInt(amount);
    }

    //Enter the status (available or on loan) of the item:
    public String askItemStatus(Scanner input) {
        System.out.println("Enter a status: ");
        String status = input.nextLine();

        while (!Pattern.matches("^Available$", status) && !Pattern.matches("^On_Loan$", status)) {
            System.out.println("Invalid status! Please enter exactly 'Available' or 'On_Loan' ! ");
            System.out.println("Enter a status: ");
            status = input.nextLine();
        }
        return status;
    }
}
