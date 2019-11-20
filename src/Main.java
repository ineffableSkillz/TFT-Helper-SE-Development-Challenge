import HelperClasses.IOHelperClass;

public class Main {
    public static void main (String[] args) {

        /* Does the program need to read from XML File? (First Running?) */
        IOHelperClass IOHelper = IOHelperClass.getInstance();
        if(!IOHelper.doesChampionXMLFileExist()) {

            System.out.println("Processed Champion file not found, need to process from .txt file");

        }


    }
}
