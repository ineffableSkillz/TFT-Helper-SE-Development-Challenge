package HelperClasses;

import java.io.*;

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
    BufferedReader br;
    BufferedWriter bw;

    private BufferedReader initReader(String path) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br;
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + path);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

        return null;
    }



    /* Boolean Functions */

    /**
     * This method returns a value dependent on if the champion information file exists to be read it.
     * This method is run at the start of the program to determine if a read needs to be done (first running).
     * @return
     */
    public boolean doesChampionXMLFileExist() {

        try (BufferedReader br = new BufferedReader(new FileReader("out/production/TFT Helper/Datasets/ChampionInfo.xml"))) {
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            System.out.println("IO Exception");
            return false;
        }
    }
}

