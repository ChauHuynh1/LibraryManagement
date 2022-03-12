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
import java.util.Scanner;
import java.util.regex.Pattern;

public class Book extends Item {
    //Data field of Book includes author, edition, ISBN and other data field :
    private String author;
    private int edition;
    private String ISBN;


    public Book() {
        author = "Unknown";
        edition = 0;
        ISBN = "0";
    }

    public Book(String author, int edition, String ISBN) {
        this.author = author;
        this.edition = edition;
        this.ISBN = ISBN;
    }

    public Book (String title, String author, int edition, String publication, int year, String ISBN, String language,
                 String subject, int copies, int borrowed, String status) {
        super("Book", title, publication, year, language, subject, copies, borrowed, status);
        this.author = author;
        this.edition = edition;
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }
    public int getEdition() {
        return edition;
    }
    public String getISBN() {
        return ISBN;
    }

    //Method toString for printing:
    public String toString() {
        return getTitle() + "; " + getAuthor() + "; " + getEdition() + "; " + getPublication() + "; " + getYear() + "; " +
                getISBN() + "; " + getLanguage() + "; " + getSubject() + "; " + getCopies() + "; " + getBorrowed() + "; " + getStatus() + "; ";
    }

    //ask for Author:
    protected String askAuthor(Scanner input) {
        System.out.println("Enter the author for your Book: ");
        String author = input.nextLine();

        while(!Pattern.matches("^[[a-z-A-Z]{1,}[\\s]]+$", author)) {
            System.out.println("Must enter a author for your Book! Can write Unknown instead!");
            System.out.println("Enter the author for your Book: ");
            author = input.nextLine();
        }
        return author;
    }
    //Ask for edition
    protected int askEdition(Scanner input) {
        System.out.println("Enter the edition: ");
        String edition = input.nextLine();

        String editionPattern = "^\\d+$";
        while (!Pattern.matches(editionPattern, edition)) {
            System.out.println("Must enter a number!");
            System.out.println("Enter the amount of edition: ");
            edition = input.nextLine();
        }
        return Integer.parseInt(edition);
    }

    //Ask for ISBN:
    protected String askISBN(Scanner input) {
        System.out.println("Enter an ISBN: ");
        String isbn = input.nextLine();

        while(!checkISBN(isbn)) {
            System.out.println("You enter the invalid ISBN!");
            System.out.println("Enter an ISBN: ");
            isbn = input.nextLine();
        }

        return isbn;
    }

    //check for ISBN: this code is taken as a reference from https://www.moreofless.co.uk/validate-isbn-13-java/.
    private boolean checkISBN(String isbn) {
        if (isbn.isEmpty()) {
            return false;
        }

        //remove any hyphens
        isbn = isbn.replaceAll("-", "");

        //must be a 13 digit ISBN
        if (isbn.length() != 13) {
            return false;
        }

        try {
            int count = 0;
            for (int i = 0; i < 12; i++) {
                int ISBNDigit = Integer.parseInt(isbn.substring(i, i + 1));
                count += (i % 2 == 0) ? ISBNDigit : ISBNDigit * 3;
            }

            //checksum must be 0-9. If calculated as 10 then = 0
            int sum = 10 - (count % 10);
            if (sum == 10) {
                sum = 0;
            }
            return sum == Integer.parseInt(isbn.substring(12));
        } catch (NumberFormatException e) {
            //to catch invalid ISBNs that have non-numeric characters in them
            return false;
        }
    }
}
