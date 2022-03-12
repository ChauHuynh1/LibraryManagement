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

//In this class I use for record the borrowing and returning activities:

public class Record {
    //Data field for Record: member, item, quantity, status, transactionDay, expectedReturnDay, returnedDay:
    private String phoneNumber;
    private String name; //name
    private String type;
    private String title;
    private int quantity;
    private String status; //Borrow or return.
    private String transactionDay;
    private String expectedReturnDay;
    private String returnedDay; //if borrow then = "0";

    //No-arg constructor:
    public Record() {
        this.phoneNumber = "0";
        this.name = "Unknown";
        this.type = "Unknown";
        this.title = "Uknown";
        this.quantity = 0;
        this.status = "Return";
        this.transactionDay = "0";
        this.expectedReturnDay = "0";
        this.returnedDay = "0";
    }

    //Constructor:
    public Record(String phoneNumber, String name, String type, String title, int quantity, String status, String transactionDay,
                  String expectedReturnDay, String returnedDay) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.type = type;
        this.title = title;
        this.quantity = quantity;
        this.status = status;
        this.transactionDay = transactionDay;
        this.expectedReturnDay = expectedReturnDay;
        this.returnedDay = returnedDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getTitle() {
        return title;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getTransactionDay() {
        return transactionDay;
    }
    public String getStatus() {
        return status;
    }
    public String getExpectedReturnDay() {
        return expectedReturnDay;
    }
    public String getReturnedDay() {
        return returnedDay;
    }

    public String toString() {
        return  getPhoneNumber()+ "; "+ getName() + "; " + getType() + "; " + getTitle() + "; " + getQuantity() + "; " + getStatus() + "; " +
                getTransactionDay() + "; " + getExpectedReturnDay() + "; " + getReturnedDay() + "; ";
    }

    // Returns true if
    // given year is valid.
    private static boolean isLeap(int year) {
        // Return true if year is
        // a multiple of 4 and not
        // multiple of 100.
        // OR year is multiple of 400.
        return (((year % 4 == 0) &&
                (year % 100 != 0)) ||
                (year % 400 == 0));
    }

    //check if the day is valid or not:
    //take as reference : https://www.geeksforgeeks.org/program-check-date-valid-not/
    private static boolean isValidDate(String expiredDay) {
        // If year, month and day
        // are not in given range

        String[] arr = expiredDay.split("/");


        int[] intArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String num = arr[i];
            intArr[i] = Integer.parseInt(num);
        }
        int d = intArr[0];
        int m = intArr[1];
        int y = intArr[2];

        if (m < 1 || m > 12)
            return true;
        if (d < 1 || d > 31)
            return true;

        // Handle February month
        // with leap year
        if (m == 2) {
            if (isLeap(y))
                return (d > 29);
            else
                return (d > 28);
        }

        // Months of April, June,
        // Sept and Nov must have
        // number of days less than
        // or equal to 30.
        if (m == 4 || m == 6 || m == 9 || m == 11)
            return (d > 30);

        return false;
    }

    protected String askExpectedReturnDay(Scanner input) {
        System.out.println("Enter the expected return day: ");
        String expectedReturnDay = input.nextLine();

        //All the string day must be like this : 15/07/2000 or 07/08/2020
        //it may get 99/99/2020 correct so more checking step is added!
        while (isValidDate(expectedReturnDay)) {
            System.out.println("Invalid day!");
            System.out.println("Enter the expected return day: ");
            expectedReturnDay = input.nextLine();

        }

        return expectedReturnDay;
    }

    protected String askReturnDay(Scanner input) {
        System.out.println("Enter return day: ");
        String returnDay = input.nextLine();

        //All the string day must be like this : 15/07/2000 or 07/08/2020
        //it may get 99/99/2020 correct so more checking step is added!
        while (isValidDate(returnDay)) {
            System.out.println("Invalid day!");
            System.out.println("Enter the return day: ");
            returnDay = input.nextLine();

        }
        return returnDay;
    }
}