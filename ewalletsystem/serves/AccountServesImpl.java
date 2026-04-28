package ewalletsystem.serves;

import ewalletsystem.model.Account;
import ewalletsystem.model.EWalletSystem;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Implementation of AccountServes interface.
 * Handles account creation and authentication logic.
 */
public class AccountServesImpl implements AccountServes {

    // Instance of the system that holds all accounts
    private EWalletSystem eWalletSystem = new EWalletSystem();

    /**
     * Creates a new account if the username does not already exist.
     *
     * @param account the account object containing user details
     * @return true if account is created successfully,
     *         false if username already exists
     */
    @Override
    public Account createAccount(Account account) {

        // Check if username already exists in the system
        Optional <Account> accountOptional = eWalletSystem.getAccounts()
                .stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName())).findFirst();

        // If username exists, do not create account
        if (accountOptional.isPresent()) {
            return null;
        }

        // Add new account to the system
        eWalletSystem.getAccounts().add(account);
        return account;
    }

    /**
     * Validates if an account exists using username and password.
     *
     * @param account the account object containing login credentials
     * @return true if matching account is found, false otherwise
     */

    @Override
    public Account isAccountExistByUserNameAndPassword(Account account) {

        // Check if any account matches both username AND password
       Optional <Account> accountOptional = eWalletSystem.getAccounts()
                .stream()
                .filter(acc ->
                        acc.getUserName().equals(account.getUserName()) &&
                                acc.getPassword().equals(account.getPassword())
                ).findFirst();

       if(accountOptional.isPresent()){
           return accountOptional.get();
       }

        // Return result directly (true if found, false otherwise)
        return null;
    }





    public Boolean deposit(Account account, double amount) {

        // validate amount
        if (amount < 100 || amount % 100 != 0) {
            return false;
        }

        // validate account
        if (account == null) {
            return false;
        }

        // find account
        Account accountExist = eWalletSystem.getAccounts()
                .stream()
                .filter(acc ->
                        acc.getUserName().equals(account.getUserName()) &&
                                acc.getPassword().equals(account.getPassword())
                )
                .findFirst()
                .orElse(null);

        // if account not found
        if (accountExist == null) {
            return false;
        }

        // update balance
        double totalBalance = accountExist.getBalance() + amount;
        accountExist.setBalance(totalBalance);

        return true;
    }

    @Override
    public Boolean withdraw(Account account, double  withdraw) {

        if (withdraw < 100 ) {
            return false;
        }

        // validate account
        if (account == null) {
            return false;
        }

        return eWalletSystem.getAccounts()
                .stream()
                .filter(acc ->
                        acc.getUserName().equals(account.getUserName()) &&
                                acc.getPassword().equals(account.getPassword()) &&
                                acc.getBalance() >= withdraw
                )
                .findFirst()
                .map(acc -> {
                    acc.setBalance(acc.getBalance() - withdraw);
                    return true;
                })
                .orElse(false);
    }


    @Override
    public Boolean transfer(Account account, String toUserName, double amount) {

        // validate amount
        if (amount < 100 || amount % 100 != 0) {
            System.out.println("Invalid amount ❌ (must be >= 100 and multiple of 100)");
            return false;
        }

        // validate sender input
        if (account == null) {
            System.out.println("Sender account is null ❌");
            return false;
        }

        // find sender
        Account sender = eWalletSystem.getAccounts()
                .stream()
                .filter(acc ->
                        acc.getUserName().equals(account.getUserName()) &&
                                acc.getPassword().equals(account.getPassword())
                )
                .findFirst()
                .orElse(null);

        if (sender == null) {
            System.out.println("Sender account not found ❌");
            return false;
        }

        // find receiver
        Account receiver = eWalletSystem.getAccounts()
                .stream()
                .filter(acc -> acc.getUserName().equals(toUserName))
                .findFirst()
                .orElse(null);

        if (receiver == null) {
            System.out.println("Receiver account not found ❌");
            return false;
        }

        // check balance
        if (sender.getBalance() < amount) {
            System.out.println("Insufficient balance ❌");
            return false;
        }

        // do transfer
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        System.out.println("Transfer completed successfully ✅");

        return true;
    }


    @Override
    public Boolean changePassword(Account account) {

        // validate input
        if (account == null) {
            return false;
        }

        // find existing account (by old credentials)
        Account existingAccount = eWalletSystem.getAccounts()
                .stream()
                .filter(acc ->
                        acc.getUserName().equals(account.getUserName()) &&
                                acc.getPassword().equals(account.getPassword())
                )
                .findFirst()
                .orElse(null);

        // if not found
        if (existingAccount == null) {
            System.out.println("Account not found ❌");
            return false;
        }

        // validate new password (you must set it in account object)
        if (account.getNewPassword() == null || account.getNewPassword().isEmpty()) {
            System.out.println("New password is invalid ❌");
            return false;
        }

        // change password
        existingAccount.setPassword(account.getNewPassword());

        System.out.println("Password changed successfully ✅");

        return true;
    }





    @Override
    public Boolean removeAccount(Account account) {

        // validate input
        if (account == null) {
            return false;
        }

        // find account in system
        Optional<Account> existingAccount = eWalletSystem.getAccounts()
                .stream()
                .filter(acc ->
                        acc.getUserName().equals(account.getUserName()) &&
                                acc.getPassword().equals(account.getPassword())
                )
                .findFirst();

        // if not found
        if (existingAccount.isEmpty()) {
            System.out.println("Account not found ❌");
            return false;
        }

        // remove account
        eWalletSystem.getAccounts().remove(existingAccount.get());

        System.out.println("Account removed successfully 🗑️");

        return true;
    }


    @Override
    public Boolean logout(Account account) {

        if (account == null) {
            return false;
        }

        System.out.println("Logged out successfully 👋");

        return true;
    }
}









