package HelperClasses;

import java.io.*;
import java.util.ArrayList;

/**
 * This singleton helper class will contain all of the functions relating to input and output,
 * primarily to and from XML Files.
 */
public class IOHelperClass {

    /* Singleton Setup */
    private static IOHelperClass instance;
    private IOHelperClass(){}
    public static IOHelperClass getInstance() {
        if(instance == null)
            instance = new IOHelperClass();

        return instance;
    }

    /* I/O Resources */
    private BufferedReader br;
    private BufferedWriter bw;
    private String pathToChampionInfoXML = "out/production/TFT Helper/Datasets/ChampionInfo.xml";
    private String pathToChampionSourceTXT = "out/production/TFT Helper/Datasets/ChampionSource.txt";

    private BufferedReader initReader(String path) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            return br;
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + path);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

        return null;
    }



    public void processChampionInformation() {

        br = initReader(pathToChampionSourceTXT);
        String currentLine = "";

        /* Champion Information */
        ChampionTier tier = null;
        ArrayList<Champion> listOfChampions = new ArrayList<>();

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

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Champion champ : listOfChampions)
            System.out.println(champ);


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

        /* Reading in 1-3 Types */
        while(!(currentLine = br.readLine()).contains(":")) {
            type.add(currentLine);
        }

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



}

