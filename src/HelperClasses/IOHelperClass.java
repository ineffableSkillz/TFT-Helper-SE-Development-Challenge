package HelperClasses;

import MyEnums.ChampionTier;
import MyObjects.Champion;

import java.io.*;
import java.util.ArrayList;

/**
 * This singleton helper class will contain all of the functions relating to input and output,
 * primarily to and from XML Files.
 */
public class IOHelperClass {

    /* Singleton Setup */
    private static IOHelperClass instance;
    public static IOHelperClass getInstance() {
        if(instance == null)
            instance = new IOHelperClass();

        return instance;
    }
    private IOHelperClass(){}

    /* I/O Resources */
    private BufferedReader br;
    private BufferedWriter bw;
    private String pathToChampionInfoXML = "out/production/TFT Helper/Datasets/ChampionInfo.xml";
    private String pathToChampionSourceTXT = "out/production/TFT Helper/Datasets/ChampionSource.txt";
    private String writePathToChampionInfoXML = "Resources/Datasets/ChampionInfo.xml";

    /* Reader Setup and Tear Down */
    private void initReader(String path) {

        try {
            br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + path);
        }

    }
    private void closeReader() {
        try {
            br.close();
        } catch (IOException e) {
            System.out.println("Error closing Reader");
        }
    }

    /* Writer Setup and Tear Down */
    private void initWriter(String path) {

        try {
            bw = new BufferedWriter(new FileWriter(path));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void closeWriter() {
        try {
            bw.close();
        } catch (IOException e) {
            System.out.println("Error closing Reader");
        }
    }



    /**
     * This function is responsible for creating the champion objects from the txt file.
     * @return
     */
    public ArrayList<Champion> processChampionInformation() {

        initReader(pathToChampionSourceTXT);
        String currentLine = "";

        /* Champion Information */
        ChampionTier tier = null;
        ArrayList<Champion> listOfChampions = new ArrayList<>();

        /* Champion Object Creation Process */
        try {

            while ((currentLine = br.readLine()) != null) {

                /* Reading First Champion of Tier x */
                if(currentLine.contains("Tier")) {
                    tier = ChampionTier.getEnum(currentLine);
                    listOfChampions.add(generateChampion(br, tier));

                } else if(tier != null){ //Covers first line case
                    String championName = currentLine;
                    listOfChampions.add(generateChampion(br, tier, championName));
                }

            }
            closeReader();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Return List of Champions */
        return listOfChampions;

    }
    private Champion generateChampion(BufferedReader br, ChampionTier tier) throws IOException {

        String currentLine = "";
        ArrayList<String> type = new ArrayList<>();

        /* Reading Champion Name */
        String name = br.readLine();

        /* Reading in 1-3 Types */
        while(!(currentLine = br.readLine()).contains(":")) {
            type.add(currentLine);
        }

        /* Storing Ability Description */
        String abilityDescription = currentLine;

        return new Champion(name, tier, abilityDescription, type);
    }
    private Champion generateChampion(BufferedReader br, ChampionTier tier, String name) throws IOException {

        String currentLine = "";
        ArrayList<String> type = new ArrayList<>();

        /* Reading in Types */
        while(!(currentLine = br.readLine()).contains(":"))
            type.add(currentLine);

        /* Storing Ability Description */
        String abilityDescription = currentLine;

        return new Champion(name, tier, abilityDescription, type);
    }

    /**
     * This method returns a value dependent on if the champion information file exists to be read it.
     * This method is run at the start of the program to determine if a read needs to be done (first running).
     * @return
     */
    public boolean doesChampionXMLFileExist() {

        try (BufferedReader br = new BufferedReader(new FileReader(pathToChampionInfoXML))) {
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            System.out.println("IO Exception");
            return false;
        }
    }

    /**
     * This method is responsible for generating the XML file responsible for holding champions and their stats
     * in XML format.
     * @param data
     */
    public void exportChampionXML(String data) {

        try {
            initWriter(writePathToChampionInfoXML);
            bw.write("<?xml version=\"1.0\"?>\n" +
                            "<champions>\n" + data + "</champions>");
            closeWriter();
        } catch (IOException e) {
            System.out.println("Exporting of Champion Data to XML Failed");
        }


    }

}

