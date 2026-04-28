package ewalletsystem.serves;

import ewalletsystem.model.Account;

/**
 * Service interface for handling account-related operations
 * in the e-wallet system.
 */
public interface AccountServes {

    /**
     * Creates a new account in the system.
     *
     * @param account the Account object containing user details
     *                such as username, password, etc.
     * @return Boolean true if the account was successfully created,
     *                 false if creation failed (e.g., username already exists)
     */
    Account createAccount(Account account);

    /**
     * Checks whether an account exists using the provided
     * username and password.
     *
     * @param account the Account object containing login credentials
     * @return Boolean true if an account with matching username and password exists,
     *                 false otherwise
     */
    Account isAccountExistByUserNameAndPassword(Account account);

    Boolean deposit (Account account , double amount);

    Boolean withdraw (Account account , double withdraw);

    Boolean transfer (Account account, String toUserName, double amount);

    Boolean changePassword (Account account );

    Boolean removeAccount (Account account );

    Boolean logout (Account account );

}