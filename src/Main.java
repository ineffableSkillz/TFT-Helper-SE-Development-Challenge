import HelperClasses.WebsiteExtractorHelperClass;
import MyObjects.Champion;
import HelperClasses.IOHelperClass;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Champion> listOfChampions;
    static IOHelperClass IOHelper = IOHelperClass.getInstance();
    public static void main (String[] args) {

        /* Does the program need to read from XML File? (First Running?) */
        if(!IOHelper.doesChampionXMLFileExist())
            firstTimeSetup();
        else
            readInChampionInfo();

        generateMenu();

    }

    private static void firstTimeSetup() {
        /* Get Champion Attributes */
        listOfChampions = IOHelper.processChampionInformation();

        /* Getting Champion Stats */
        ArrayList<WebsiteExtractorHelperClass> listOfThreads = new ArrayList<>();
        WebsiteExtractorHelperClass tempThread;
        for(Champion x : listOfChampions) {

            /* Start Data Collection Process */
            tempThread = new WebsiteExtractorHelperClass((x));
            tempThread.start();

            /* Add Thread to List */
            listOfThreads.add(tempThread);

        }


        /* Waiting for All Threads to Finish */
        for(WebsiteExtractorHelperClass thread : listOfThreads)
            while(thread.isAlive());

        /* Compile Champion XML Info */
        StringBuilder sb = new StringBuilder();
        for (Champion champ : listOfChampions)
            sb.append(champ.getXMLFormat());

        /* Write XML Info */
        IOHelper.exportChampionXML(sb.toString());


    }
    private static void readInChampionInfo() {
    }
    private static void generateMenu() {
        homeScreen();
    }

    private static void homeScreen() {

        System.out.println("\t\t\tTFT Helper\t\t\t");
        displayHomeScreenOptions();

        boolean validAnswer = false;
        while(!validAnswer) {
            switch (getNumericalInput()) {

                case 1:
                    championSuggestionScreen();
                    validAnswer = true;
                    break;
                default:
                    System.out.println("This is not a valid option");
                    displayHomeScreenOptions();
                    break;

            }
        }

    }
    private static void displayHomeScreenOptions() {
        System.out.println("1. Champion Suggestions - Add/Remove champions to get recommended accompanying champions");
    }
    private static void championSuggestionScreen() {

        clearScreen();
        System.out.println("Champion Selection Screen");
    }

    /* User Input */
    private static int getNumericalInput() {

        Scanner sc = new Scanner(System.in);

        try {
            return Integer.parseInt(sc.nextLine().substring(0, 1));
        } catch (NumberFormatException e) {
            System.out.println("Please Enter an Integer");
            return getNumericalInput();
        }
    }

    /* Misc. Tools */
    private static void clearScreen() {

        for(int x = 0; x < 50; x++)
            System.out.println("\n\n");

    }
}
