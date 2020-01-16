import HelperClasses.MenuHandler;
import HelperClasses.WebsiteExtractorHelperClass;
import MyObjects.Champion;
import HelperClasses.IOHelperClass;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Champion> listOfChampions = new ArrayList<>();
    static IOHelperClass IOHelper = IOHelperClass.getInstance();
    static MenuHandler myMenuHandler = MenuHandler.getInstance();
    public static void main (String[] args) {

        /* Does the program need to read from XML File? (First Running?) */
        if(!IOHelper.doesChampionXMLFileExist())
            firstTimeSetup();
        else
            readInChampionInfo();

        myMenuHandler.generateMenu(listOfChampions);


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
        IOHelper.parseChampionXMLFile(listOfChampions);
    }

}
