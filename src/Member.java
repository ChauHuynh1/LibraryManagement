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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Member {
    //Data field for member: name, ID, phone, email, address, expiredDate, status:
    private String name;
    private String ID;
    private String phone;
    private String email;
    private String address;
    private String expiredDay;
    private String status;

    public Member() {
        this.name = "Unknown";
        this.ID = "0";
        this.phone = "0";
        this.email = "Unknown";
        this.address = "Unknown";
        this.expiredDay = "0";
        this.status = "Expired";
    }

    public Member(String name, String ID, String phone, String email, String address, String expiredDay, String status) {
        this.name = name;
        this.ID = ID;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.expiredDay = expiredDay;
        this.status = status;
    }

    public String getName() {
        return name;
    }
    public String getID() {
        return ID;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getExpiredDay() {
        return expiredDay;
    }
    public String getStatus() {
        return status;
    }

    //Ask member name:
    protected String askMemberName(Scanner input) {
        System.out.println("Enter member name: ");
        String name = input.nextLine();

        while (!name.isEmpty()) {
            System.out.println("Invalid member name!");
            System.out.println("Enter member name: ");
            name = input.nextLine();
        }

        return name;
    }

    //Ask member ID:
    protected String askMemberID(Scanner input) {
        System.out.println("Enter a member ID: ");
        String id = input.nextLine();

        String idPattern1 = "^\\d{12}$";
        String idPattern2 = "^C\\d{7}$";

        while (!Pattern.matches(idPattern1, id) && !Pattern.matches(idPattern2, id)) {
            System.out.println("Invalid ID!");
            System.out.println("Enter a member ID: ");
            id = input.nextLine();
        }

        return id;
    }

    //Ask phone:
    protected String askMemberPhone(Scanner input) {
        System.out.println("Enter member number: ");
        String memberNumber = input.nextLine();

        String numberPattern = "^\\d{10,13}$";
        while (!Pattern.matches(numberPattern, memberNumber)) {
            System.out.println("Invalid member number!");
            System.out.println("Enter member number: ");
            memberNumber = input.nextLine();
        }
        return memberNumber;
    }

    //Ask email:
    protected String askMemberEmail(Scanner input) {
        System.out.println("Enter member email: ");
        String memberEmail = input.nextLine();

        String emailPattern = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        while (!Pattern.matches(emailPattern, memberEmail)) {
            System.out.println("Invalid member email!");
            System.out.println("Enter member email: ");
            memberEmail = input.nextLine();
        }

        return memberEmail;
    }

    //Ask address:
    protected String askMemberAddress(Scanner input) {
        System.out.println("Enter member address: ");
        String memberAddress = input.nextLine();

        //Suppose all the address look like this '120 Le Thanh Ton, D1, HCM':
        String addressPattern = "^\\d{1,3}[\\s][[a-z-A-Z]{1,}[\\s]]+[,][\\s][[\\w]+[\\s]]+[,][\\s][[a-z-A-Z]{1,}[\\s]]+$";
        while (!Pattern.matches(addressPattern, memberAddress)) {
            System.out.println("Invalid member address!");
            System.out.println("Enter member address ");
            memberAddress = input.nextLine();
        }
        return memberAddress;
    }

    //Ask expiredDay:
    protected String askMemberExpiredDay(Scanner input) {
        System.out.println("Enter the expired day of the member account: ");
        String expiredDay = input.nextLine();

        //All the string day must be like this : 15/07/2000 or 07/08/2020
        //it may get 99/99/2020 correct so more checking step is added!
        while (!isValidDate(expiredDay) || !Pattern.matches("^\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}$", expiredDay)) {
            System.out.println("Invalid day!");
            System.out.println("Enter the expired day of the member account: ");
            expiredDay = input.nextLine();

        }

        return expiredDay;
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
            return false;
        if (d < 1 || d > 31)
            return false;

        // Handle February month
        // with leap year
        if (m == 2) {
            if (isLeap(y))
                return (d <= 29);
            else
                return (d <= 28);
        }

        // Months of April, June,
        // Sept and Nov must have
        // number of days less than
        // or equal to 30.
        if (m == 4 || m == 6 || m == 9 || m == 11)
            return (d <= 30);

        return true;
    }

    //Ask status:
    protected String askMemberStatus(Scanner input, String expiredDay) {

        System.out.println("Enter the status of this member: ");
        String status = input.nextLine();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.now();

        LibraryManagement lib = new LibraryManagement();

        while(!Pattern.matches("^Active$", status) && !Pattern.matches("^Expired$", status)) {
            System.out.println("Please enter exactly 'Active' or 'Expired'!");
            System.out.println("Enter the status of this member: ");
            status = input.nextLine();
        }

        if(lib.countDay(dateFormatter.format(date), expiredDay) < 0) {
            while(status.toLowerCase().contains("Active".toLowerCase())) {
                System.out.println("The status must be 'Expired'!");
                status = input.nextLine();
            }
        }
        else {
            while(status.toLowerCase().contains("Expired".toLowerCase())) {
                System.out.println("The status must be 'Active'!");
                status = input.nextLine();
            }
        }

        return status;
    }

    @Override
    public String toString() {
        return getName() + "; " + getID() + "; " + getPhone() + "; " + getEmail() + "; " + getAddress() + "; " + getExpiredDay() + "; " +
                getStatus() + "; ";
    }
}