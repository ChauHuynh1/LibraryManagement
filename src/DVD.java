import java.util.Scanner;

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
*/import java.util.regex.Pattern;

public class DVD extends Item {
    //Data field for DVD: author and other data field in item.
    private String author;
    private int id;

    public DVD () {
        this.author = "Unknown";
    }

    public DVD (int id, String title, String author, String publication, int year, String language,
                String subject, int copies, int borrowed, String status) {
        super("DVD", title, publication, year, language, subject, copies, borrowed, status);
        this.id = id;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    protected int askId(Scanner input) {
        System.out.println("Enter the ID of your DVD:");
        String id = input.nextLine();

        while (!Pattern.matches("^\\d+$", id)) {
            System.out.println("Must enter a number!");
            System.out.println("Enter the ID of your DVD:");
            id = input.nextLine();
        }
        return Integer.parseInt(id);
    }

    protected String askDVDAuthor(Scanner input) {
        System.out.println("Enter the author for your DVD: ");
        String author = input.nextLine();

        while(!Pattern.matches("^[[a-z-A-Z]{1,}[\\s]]+$", author)) {
            System.out.println("Must enter a author for your DVD! Can write Unknown instead!");
            System.out.println("Enter the author for your DVD: ");
            author = input.nextLine();
        }
        return author;
    }

    //Method toString for printing:
    public String toString() {
        return getId() + "; " + getTitle() + "; " + getAuthor() + "; " + getPublication() + "; " + getYear() + "; " + getLanguage() + "; " +
                getSubject() + "; " + getCopies() + "; " + getBorrowed() + "; " + getStatus() + "; ";
    }

}
