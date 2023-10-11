import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FamilyGuyDatabase{

   
   private ArrayList<Episode> episodes;
   private String dataFileName;

   public FamilyGuyDatabase(String dataFileName){
   
    episodes = new ArrayList<>();
    this.dataFileName = dataFileName;
    ArrayList<String> rows = createListOfStringsFromFile(dataFileName);
    rows.remove(0);
    rows.remove(0);
    
    for(String row : rows){
       String[] parts = splitOnNonQuotedCommas(row);
       int seasons = Integer.parseInt(parts[0]);
       int episodeNums = Integer.parseInt(parts[1]);
       String titles = parts[3];
       String[] guests = splitOnNonQuotedCommas(parts[8]);
       String[] features = splitOnNonQuotedCommas(parts[9]);
       String[] musicalNumbers = splitOnNonQuotedCommas(parts[11]);
     
       Episode add = new Episode(seasons, episodeNums, titles, guests, features, musicalNumbers);
       episodes.add(add);
    
   }
   }
   
   public ArrayList<Episode> getEpisodes(){
      return episodes;
   }
   
   
    private ArrayList<String> createListOfStringsFromFile(String fileName) {
        ArrayList<String> data = null;
        try {
            data = (ArrayList<String>) Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println("The file " + fileName + " could not be opened.");
            e.printStackTrace();
        }
        return data;
    }


    private String[] splitOnNonQuotedCommas(String hasCommasWithinQuotes) {
        return hasCommasWithinQuotes.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }


}