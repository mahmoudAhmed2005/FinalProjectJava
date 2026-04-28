package ewalletsystem.serves;

import ewalletsystem.model.Account;

import java.util.Scanner;


public interface Dubilication  {

   ValidationServes validationServes = new ValidationServesImpl();


    Scanner scanner = new Scanner(System.in);
    default Account getUserCredentials() {
        System.out.print("Enter username: ");
        String userName = scanner.next();


        boolean isUserNameValid = validationServes.isUserNameValid(userName);

        if(!isUserNameValid){
            System.out.println("Invalid username! ❌  should[\"❗ Username must be at least 3 letters, contain only English letters, and start with a capital letter.]");

            return null;
        }



        System.out.print("Enter password: ");
        String password = scanner.next();




        boolean isPasswordValid = validationServes.isPasswordValid(password);

        if(!isPasswordValid){
            System.out.println("Invalid username! ❌ should[❗ Password must be 8+ characters, include uppercase, lowercase, number, and special character.]");

            return null;

        }



        return new Account(userName, password);
    }






    default Account getSignupData() {
        System.out.print("Enter username: ");
        String userName = scanner.next();

        boolean isUserNameValid = validationServes.isUserNameValid(userName);

        if(!isUserNameValid){
            System.out.println("Invalid username! ❌  should[\"❗ Username must be at least 3 letters, contain only English letters, and start with a capital letter.]");

      return null;
        }




        System.out.print("Enter password: ");
        String password = scanner.next();

        boolean isPasswordValid = validationServes.isPasswordValid(password);

        if(!isPasswordValid){
            System.out.println("Invalid username! ❌ should[❗ Password must be 8+ characters, include uppercase, lowercase, number, and special character.]");

            return null;

        }




        System.out.print("Enter phone number:  ");
        String phoneNumber = scanner.next();

        boolean isPhoneNumberValid = validationServes.isPhoneNumberValid(phoneNumber);

        if(!isPasswordValid){
            System.out.println(" Invalid PhoneNumber! ❌ [❗ Phone number must be 11+ digits and include country code like +20.]");

            return null;

        }





        System.out.print("Enter age: ");
        float age = scanner.nextFloat();

        boolean isAgeValid = validationServes.isAgeValid(age);


        if(!isAgeValid){
            System.out.println("Invalid Age! ❌ should[❗ Age must be 18 or older.");

            return null;

        }


        return new Account(userName, password, phoneNumber, age);



    }
}
