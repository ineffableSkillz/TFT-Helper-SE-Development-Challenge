package HelperClasses;

import MyObjects.Champion;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * The purpose of this class is to be run as a thread during the initial start-up of this program (or when there's
 * been a TFT patch).
 *
 * This class is responsible for connecting to the website holding the champion data, extract the data from it
 * and store it in the object representing said champion.
 */
public class WebsiteExtractorHelperClass extends Thread implements Runnable{

     private Champion champion;

    private String htmlParameter = "<div id=\"ability-td\"></div>";

    private String baseURL = "https://rankedboost.com/league-of-legends/teamfight-tactics/";
    private String championName;
    private String fullURL;

    private ArrayList<Integer> health = new ArrayList<>();
    private ArrayList<Integer> dps = new ArrayList<>();
    private ArrayList<Integer> dmg = new ArrayList<>();

    private String cost;

    private int mana;
    private int crit;
    private int magicResist;
    private int armour;
    private int attackRange;

    private double attackSpeed;

    private BufferedReader br;

    public WebsiteExtractorHelperClass(Champion champion) {

        /* Storing Champion Name */
        championName = champion.getName();
        if(championName.contains("’")) { //Champions: Rek'sai and Kha'zix

            String[] temp = championName.split("’");
            championName = temp[0] + temp[1];

        } else if(championName.contains(". ")) {

            championName = championName.replace(". ", "-");

        }

        /* Creating Website URL */
        fullURL = (baseURL + championName + "/").toLowerCase();

        /* Saving Champion Object */
        this.champion = champion;
    }


    @Override
    public void run() {

        try {

            URL oracle = new URL(fullURL);
            br = new BufferedReader(new InputStreamReader(oracle.openStream()));

            String currentLine;

            /* Skipping Ahead to Relevant HTML - Slight Performance Optimisation*/
            while(!(currentLine = br.readLine().strip()).equals(htmlParameter));

            /* Extracting Data */
            String[] features =  {"cost", "hp", "mana", "damage", "critchance", "attackspeed", "attackrange", "magicresist",
                                    "armor"};


            boolean done = false;
            while(!done) {

                while((currentLine = br.readLine()) != null) {

                    /* Base Case - Slight Optimisation */
                    if(!currentLine.contains(".png"))
                        continue;

                    /* The line above the line holding the information we need */
                    for(String feature: features) {
                        if(currentLine.toLowerCase().contains(("tft/" + feature +".png"))) {

                            /* Extract information from line below */
                            switch(feature) {

                                case "cost":
                                    cost = extractValue(br.readLine());
                                    break;

                                case "hp":
                                    extractMultipleValues(health);
                                    break;

                                case "mana":
                                    mana = Integer.parseInt(extractValue(br.readLine()));
                                    break;

                                case "magicresist":
                                    magicResist = Integer.parseInt(extractValue(br.readLine()));
                                    break;

                                case "armor":
                                    armour = Integer.parseInt(extractValue(br.readLine()));
                                    done = true;
                                    break;

                                case "attackrange":
                                    attackRange = Integer.parseInt(extractValue(br.readLine()).split(" ")[0]);
                                    break;

                                case "critchance":
                                    crit = Integer.parseInt(extractValue(br.readLine()).split("%")[0]);
                                    break;

                                case "attackspeed":
                                    attackSpeed = Double.parseDouble(extractValue(br.readLine()));
                                    break;

                                case "damage":

                                    if(currentLine.contains("dps"))
                                       extractMultipleValues(dps);
                                     else if(currentLine.contains("damage"))
                                        extractMultipleValues(dmg);
                                    break;

                                default:
                                    break;
                            }
                        }
                    }
                }
            }

            br.close();
            champion.addStats(cost, health, mana, dps, dmg, crit, attackSpeed, attackRange, magicResist, armour);
            System.out.println("Stats for " + championName +" added successfully.");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            if(e.toString().contains("Server returned HTTP response code: 429 for URL:")) {
                System.out.println("Too Many Requests: " + championName);

                for(int x = 0; x < 250; x++); //Pause to defeat request
                run();

            }
        }

    }
    private void extractMultipleValues(ArrayList<Integer> list) throws IOException{

        String[] temp = (extractValue(br.readLine())).split("/");

        /* Tidying Input */
        for(String x: temp) {
            x = x.strip();
            list.add(Integer.parseInt(x));
        }

        /* Special Case - Singed */
        if(temp.length == 1) {
            list.add(0);
            list.add(0);
        }

    }

    /* Extracts the stat value(s) as a string with no modification */
    private String extractValue(String line) {

        return line.split("\">")[1].split("<")[0].strip();
    }

}
