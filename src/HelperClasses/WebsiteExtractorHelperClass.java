package HelperClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WebsiteExtractorHelperClass implements Runnable{

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

    public WebsiteExtractorHelperClass(String name) {
        championName = name;
        fullURL = (baseURL + championName + "/").toLowerCase();
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

                            System.out.println(currentLine);

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
            System.out.println("done");

            //Find line, go underneath and extract value(s)
            //Special case for dmg
            //Note: Implemented so in the event website changes the ordering of the stats


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void extractMultipleValues(ArrayList<Integer> list) throws IOException{

        String[] temp = (extractValue(br.readLine())).split("/");

        /* Tidying Input */
        for(String x: temp) {
            x = x.strip();
            list.add(Integer.parseInt(x));
        }

    }

    /* Extracts the stat value(s) as a string with no modification */
    private String extractValue(String line) {

        return line.split("\">")[1].split("<")[0];
    }

}
