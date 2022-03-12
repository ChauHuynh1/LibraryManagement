
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

public class Journal extends Item {
    //Data field for Journal: ISSN and other data field in Item.
    private String ISSN;

    public Journal() {
        this.ISSN = "0";
    }

    public Journal(String title, String publication, int year, String ISSN, String language, String subject,
                   int copies, int borrowed, String status) {
        super("Journal", title, publication, year, language, subject, copies, borrowed, status);
        this.ISSN = ISSN;
    }

    public String getISSN() {
        return ISSN;
    }

    //Method toString for printing:
    public String toString() {
        return getTitle() + "; " + getPublication() + "; " + getYear() + "; " + getISSN() + "; " + getLanguage() + "; " +
                getSubject() + "; " + getCopies() + "; " + getBorrowed() + "; " + getStatus() + "; ";
    }

    //Ask and check ISSN:
    protected String askISSN(Scanner input) {
        System.out.println("Enter an ISSN: ");
        String issn = input.nextLine();

        while(!(Pattern.matches("^[0-9]{4}-[0-9]{3}[0-9xX]$", issn) && checkISSN(issn))) {
            System.out.println("You enter the invalid ISSN!");
            System.out.println("Enter an ISSN: ");
            issn = input.nextLine();
        }


        return issn;
    }

    //check ISSN:
    private boolean checkISSN(String issn) {
        try {
            issn = issn.replace("-","");
            int count = 0;
            for (int i = 0; i < 7; i++) {
                int ISSNDigit = Character.getNumericValue(issn.charAt(i));
                count += ((8 - i) * ISSNDigit);
            }

            int remainder = count % 11;
            int valid_check;
            if(remainder == 0) {
                valid_check = 0;
            } else if (remainder == 1)
                return issn.charAt(7) == 'X';
            else {
                valid_check = 11 - remainder;
            }

            return valid_check == Character.getNumericValue(issn.charAt(7));


        } catch (NumberFormatException e) {
            //to catch invalid ISBNs that have non-numeric characters in them
            return false;
        }
    }
}