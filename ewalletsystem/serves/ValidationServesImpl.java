package ewalletsystem.serves;

public class ValidationServesImpl implements ValidationServes  {

    @Override
    public boolean isUserNameValid(String userName) {

        if (userName.isBlank()) {
            return false;
        }

        if (userName.length() < 3) {
            return false;
        }

        if (!userName.matches("[a-zA-Z]+")) {
            return false;
        }

        if (!Character.isUpperCase(userName.charAt(0))) {
            return false;
        }

        return true;
    }


    @Override
    public boolean isPasswordValid(String Password) {

        if (Password.isBlank()) {
            return false;
        }


        if (Password.length() < 8) {
            return false;
        }

        if (
                !Password.chars().anyMatch(c -> c >= 'A' && c <= 'Z')
                        || !Password.chars().anyMatch(c -> c >= 'a' && c <= 'z')
                        || !Password.chars().anyMatch(c -> c >= '0' && c <= '9')
                        || !Password.chars().anyMatch(c -> !Character.isLetterOrDigit(c))
        ) {
            return false;


        }
        return true;
    }


    @Override
    public boolean isPhoneNumberValid(String phoneNumber) {


            if (phoneNumber == null || phoneNumber.isBlank()) {
                System.out.println("❌ Phone number must not be empty.");
                return false;
            }

            if (!phoneNumber.startsWith("+")) {
                System.out.println("❌ Phone number must include country code like +20.");
                return false;
            }

            if (phoneNumber.length() < 11) {
                System.out.println("❌ Phone number must be at least 11 characters.");
                return false;
            }


            return true;
        }

    @Override
    public boolean isAgeValid(Float age) {


        if(age<18) {
            return false;
        }

        return true;
    }
}

