package HelperClasses;

import MyObjects.Champion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MenuHandler {

    /* Singleton Setup */
    private static MenuHandler instance;
    public static MenuHandler getInstance() {
        if(instance == null)
            instance = new MenuHandler();

        return instance;
    }
    private MenuHandler(){}

    /* Variables */
    private ArrayList<Champion> listOfChampions = null;
    private ArrayList<Champion> teamDraftListOfChampions = new ArrayList<>();

    /* Tools */
    Scanner sc = new Scanner(System.in);

    public void generateMenu(ArrayList<Champion> listOfChampions) {
        this.listOfChampions = listOfChampions;
        homeScreen();
    }

    /* Home Screen Methods */
    private void homeScreen() {

        System.out.println("\t\t\tTFT Helper\t\t\t");
        displayHomeScreenOptions();

        boolean validAnswer = false;
        while(!validAnswer) {
            switch (getNumericalInput()) {

                case 0:
                    sc.close();
                    System.exit(0);
                    break;
                case 1:
                    teamDrafting();
                    validAnswer = true;
                    break;
                default:
                    System.out.println("This is not a valid option");
                    displayHomeScreenOptions();
                    break;

            }
        }

    }
    private void displayHomeScreenOptions() {
        System.out.println("0. Close Program");
        System.out.println("1. Team Drafter");
    }

    /* Team Drafting Screen Methods */
    private void teamDrafting() {

        clearScreen();
        System.out.println("Team Drafting Screen");
        displayChampions();
        displayTeamDraftingScreenOptions();

        boolean validAnswer = false;
        while(!validAnswer) {
            switch (getNumericalInput()) {

                case 1:
                    addChampion();
                    validAnswer = true;
                    break;
                default:
                    System.out.println("This is not a valid option");
                    displayHomeScreenOptions();
                    break;

            }
        }
    }
    private void displayTeamDraftingScreenOptions() {
        System.out.println("0. Go to Main Menu");
        System.out.println("1. Add Champion(s)");
        System.out.println("2. Remove Champion(s)");
        System.out.println("3. Clear Team Drafter");
    }
    private void addChampion() {
        System.out.println("Enter Champion Name: ");
        System.out.println("For multiple champions, separate using \",\"");
        System.out.println("Example Input: Kog, Zed, Taliya");
        System.out.print("-> ");

        String userInput =  getStringInput();

        if(userInput.contains(",")) //Multiple Champions
            extractMultipleChampions(userInput);
        else
            extractSingleChampion(userInput);

    }
    private void extractSingleChampion(String userInput) {

        findChampion(userInput);
    }
    private void extractMultipleChampions(String userInput) {

        String[] listOfNames = userInput.split(",");

        for(String championName : listOfNames)
            findChampion(championName.trim());

    }
    private void findChampion(String championName) {

        ArrayList<Champion> temporaryList = new ArrayList<>(); //Used to check if shorthand name applies to multiple champions

        for(Champion champion : listOfChampions) {
            if(champion.getName().toLowerCase().contains(championName.toLowerCase())) { //If a (potential) match is found [Note: null throw error?]
                temporaryList.add(champion);
            }
        }

        /* If Multiple Champions for Shorthand Found (possible false positive) */
        if(temporaryList.size() > 1) {

            System.out.println("For champion name \"" + championName + "\", multiple matches have been found.");
            System.out.println("Enter the number corresponding to the champion you wish to add");

            /* Display Potential Champions */
            for(int x = 0; x < temporaryList.size(); x++)
                System.out.println((x+1) + ". " + temporaryList.get(x).getName());

            /* User Choice */
            int userNumericalInput = getNumericalInput();
            while(userNumericalInput < 1 || userNumericalInput > temporaryList.size()) {
                System.out.println("Enter valid choice using the numbers provided");
                userNumericalInput = getNumericalInput();
            }

            /* Adding User's Choice */
            teamDraftListOfChampions.add(temporaryList.get(getNumericalInput()-1));

        } else if(temporaryList.size() == 1) { //Single Champion (No Collision)

            teamDraftListOfChampions.add(temporaryList.get(0));
        }

    }
    private void displayChampions() { //Note: Can be optimised with state check

        /* Base Case */
        if(teamDraftListOfChampions.size() == 0)
            return;

        /* Setting Up Variables to Display in Desired Format */
        ArrayList<String> listOfNames = new ArrayList<>();
        HashMap<String, ArrayList<String>> MAP_name_type = new HashMap<>();

        for(Champion champ : teamDraftListOfChampions) {

            /* Store Champion Name */
            listOfNames.add(champ.getName());

            /* Map Champion Name to Types */
            MAP_name_type.put(champ.getName(), champ.getType());

        }

        /* Print */
        for(String name : listOfNames)
            System.out.println(name + ":\t\t");

        for(String name : listOfNames) {

            ArrayList<String> tempType = MAP_name_type.get(name);
            //PRINTING TYPES

        }



    }

    /* User Input */
    private int getNumericalInput() {
        try {
            int retValue = Integer.parseInt(sc.nextLine().substring(0, 1));
            return retValue;
        } catch (NumberFormatException e) {
            System.out.println("Please Enter an Integer");
            return getNumericalInput();
        }
    }
    private String getStringInput() {

        String retValue = sc.nextLine();
        return retValue;
    }

    /* Misc. Tools */
    private void clearScreen() {

        for(int x = 0; x < 50; x++)
            System.out.println("\n\n");

    }

}
