package ewalletsystem;

import ewalletsystem.serves.ApplicationServes;
import ewalletsystem.serves.EWalletApplicationServesImpl;

public class Main {


    public static void main(String[] args) {


//        ApplicationServes applicationServes = new EWalletApplicationServesImpl();
//        applicationServes.start();



        new EWalletApplicationServesImpl().start();


    }
}