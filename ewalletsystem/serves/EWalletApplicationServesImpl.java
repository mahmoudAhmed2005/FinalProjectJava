package ewalletsystem.serves;

import ewalletsystem.model.Account;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Handles the console-based interaction for the E-Wallet system.
 */
public class EWalletApplicationServesImpl implements ApplicationServes , Dubilication{

    private AccountServes accountServes = new AccountServesImpl();
   private ValidationServes validationServes = new ValidationServesImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void start() {

        System.out.println("=================================");
        System.out.println("   Welcome to E-Wallet System 💳");
        System.out.println("=================================");

        int counter = 0;

        boolean exit = false;

        while (true) {
            try {
                System.out.println("\nChoose an option:");
                System.out.println("[1] Login");
                System.out.println("[2] Sign Up");
                System.out.println("[3] Exit");
                System.out.print("Your choice: ");

                int choose = scanner.nextInt();

                switch (choose) {
                    case 1:
                        login();
                        break;

                    case 2:
                        signup();
                        break;

                    case 3:
                        System.out.println("\nThank you for using E-Wallet. Goodbye 👋");
                        exit = true;
                        break;

                    default:
                        System.out.println("❌ Invalid choice. Please try again.");
                        counter++;
                }

                if (exit) break;

                if (counter == 4) {
                    System.out.println("⚠️ Too many invalid attempts. Please contact admin.");
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input type. Please enter a number.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    /**
     * Handles user registration.
     */
    private void signup() {

        System.out.println("\n===== Sign Up =====");





        Account account = getSignupData();

        if (account == null) {
            return;
        }

        account = accountServes.createAccount(account);

        if (Objects.nonNull(account)) {
            System.out.println("\n✅ Account created successfully!");
            mainProfile(account);
        } else {
            System.out.println("\n❌ Username already exists. Try another one.");
        }
    }

    /**
     * Handles user login.
     */
    private void login() {

        System.out.println("\n===== Login =====");



        Account account =  getUserCredentials();

         account = accountServes.isAccountExistByUserNameAndPassword(account);

        if (Objects.nonNull(account)) {
            System.out.println("\n✅ Login successful!");
            mainProfile(account);
        } else {
            System.out.println("\n❌ Invalid username or password.");
        }
    }








    /**
     * Displays main user menu after login/signup.
     */

    int counter2 = 0;
    private void mainProfile(Account account) {
        System.out.println("\n===== Main Menu =====");
        System.out.println("[1] Deposit");
        System.out.println("[2] Withdraw");
        System.out.println("[3] Transfer");
        System.out.println("[4] Show Profile Ditals");
        System.out.println("[5] Change Password");
        System.out.println("[6] Remove  Account");
        System.out.println("[7] Logout");
        System.out.println("=====================");

        System.out.println("pleas enter your feature : ");


        int feature = scanner.nextInt();
        switch (feature){
            case 1 :
                deposit(account);
                break;

                case 2 :
                    withdraw(account);
                   break;

                case 3 :
                    transfer(account);
                    break;

                case 4 :
                    showProfileDetals(account);
                    break;

                case 5 :
                    changePassword(account);
                    break;

                case 6 :
                    removeAccount(account);
                    break;

                case 7 :
                    logout(account);
                    break;

            default:
                System.out.println("❌ Invalid choice. Please try again.");

              break;

        }




    }



        private void logout(Account account) {

            boolean result = accountServes.logout(account);

            if (result) {
                System.out.println("Logged out successfully 👋");
            } else {
                System.out.println("Logout failed ❌");
            }

            // هنا المهم 👇 ترجعك لصفحة الـ login
            return;
        }




    private void removeAccount(Account account) {

        System.out.println("=== REMOVE ACCOUNT ===");

        System.out.println("Are you sure you want to delete your account? (yes/no)");
        String confirm = scanner.next();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Operation cancelled ❌");
            return;
        }

        boolean result = accountServes.removeAccount(account);

        if (result) {
            System.out.println("Account removed successfully 🗑️");
        } else {
            System.out.println("Failed to remove account ❌");
        }
    }











    private void changePassword(Account account) {

        System.out.println("=== CHANGE PASSWORD ===");

        System.out.println("Enter new password:");
        String newPassword = scanner.next();

        // نحط الباسورد الجديدة داخل نفس الـ account object
        account.setNewPassword(newPassword);

        boolean result = accountServes.changePassword(account);

        if (result) {
            System.out.println("Password changed successfully ✅");
            mainProfile(account);
        } else {
            System.out.println("Password change failed ❌");
        }
    }


    private void transfer(Account account) {

        System.out.println("=== TRANSFER ===");

        // عرض المستخدمين المتاحين
        System.out.println("Available users:");

        // إدخال بيانات التحويل
        System.out.println("\nEnter receiver userName:");
        String userName = scanner.next();

        System.out.println("Enter amount:");
        double amount = scanner.nextDouble();

        // تنفيذ التحويل
        boolean result = accountServes.transfer(account, userName, amount);

        // النتيجة
        if (result) {
            System.out.println("Transfer successful ✅");
            mainProfile(account);
        } else {
            System.out.println("Transfer failed ❌");
        }
    }





    private void withdraw(Account account) {
        System.out.println("pleas  you need to withdraw");
        double withdraw = scanner.nextDouble();

        boolean result = accountServes.withdraw(account, withdraw);

        if (result) {
            System.out.println("Withdraw successful ✅");
            mainProfile(account);
        } else {
            System.out.println("Withdraw failed ❌");
        }

    }

    private void deposit(Account account) {
        System.out.println("pleas amount you need to deposit");
        double amount = scanner.nextDouble();
        boolean isDepositSuccess= accountServes.deposit(account,amount);

        if (isDepositSuccess){
            System.out.println("Deposit Success");
            mainProfile(account);
        }
        else {
            System.out.println("Deposit Failed");
        }
    }

    private void showProfileDetals(Account account) {


        System.out.println("=================================");
        System.out.println("         Account Data       ");
        System.out.println("=================================");


        System.out.println("Username : " + account.getUserName());
        System.out.println("Password : " + account.getPassword());
        System.out.println("Balance : " + account.getBalance());
        System.out.println("PhoneNumber : " + account.getPhoneNumber());
        System.out.println("Age : " + account.getAge());

        mainProfile(account);


    }


}


























