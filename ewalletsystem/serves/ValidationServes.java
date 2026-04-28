package ewalletsystem.serves;

public interface ValidationServes {

    boolean isUserNameValid(String userName);
    boolean isPasswordValid(String password);
    boolean isPhoneNumberValid(String phoneNumber);
   boolean isAgeValid(Float age);


}
