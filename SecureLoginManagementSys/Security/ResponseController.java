package Security;

// package import
//import System.DBhandler.DataBaseConnector;
//java in built
//import java.security.NoSuchAlgorithmException;
// import java.sql.*;
// import java.util.Random;
import java.util.Scanner;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

import Formating.Displayer;

public class ResponseController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Displayer displayer = new Displayer();

    protected void menuExecutor() {
        System.out.println(displayer.indigoColor + displayer.bold);
        System.out.print("Enter choice : ");
        System.out.print(displayer.reset);
        switch (scanner.nextLine()) {
            case "1":
            case "home": {
                if (!LoginController.isLoged) {
                    System.out.println("You First have to Login !");
                    return;
                }

                if (LoginController.authentication == null) {
                    System.out.println(displayer.redColor + displayer.bold);
                    System.out.println("Sorry ! We Do not find your Role \n Log out and Do log in with Proper ID");
                    System.out.println(displayer.reset);
                    return;
                }

                return;
            }
            case "2":
            case "about Us": {
                System.out.println("About Us Selected ! ");

                break;
            }
            case "3":
            case "services": {
                System.out.println("Services Selected ! ");

                break;
            }
            case "4":
            case "feedback": {
                System.out.println("feedback Selected ! ");

                break;
            }
            case "5":
            case "support": {
                System.out.println("Support Selected ! ");

                break;
            }
            case "6":
            case "logout": {
                LoginController.isLoged = false;
                LoginController.authentication = "null";
                LoginController.userIdNumber = 0;
                break;
            }
            case "7":
            case "exit": {

                System.exit(0);
                break;
            }
            default: {
                System.out.println(displayer.redColor + displayer.bold);
                System.out.println("Select from <1> TO <7> OR ");
                System.out.println(OperationExecutor.menu);
                System.out.println(displayer.reset);
                OperationExecutor.executor();
            }
        }
    }
}
