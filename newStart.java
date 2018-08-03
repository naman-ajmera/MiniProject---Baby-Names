import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of newStart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class newStart {
  public int getRank(int year, String name , String gender){
      FileResource fr = new FileResource("yob" + year + "short.csv");
      int rank = 1;
      for( CSVRecord rec : fr.getCSVParser(false)){
       if(rec.get(0).equals(name)){
           rank += 1;
           if(rec.get(1).equals(gender)){ break;}}
    
           else{ rank = -1;}   
        
    
    
       } return rank ;}
  public void testgetRank(){
     System.out.println(getRank(2012,"Jacob","M"));}
  public String getName(int year , int rank , String gender){
   FileResource fr = new FileResource("yob" + year + "short.csv");   
   int currentRank = 0;
   
   String name = "";
   for( CSVRecord rec : fr.getCSVParser(false)){
       currentRank = getRank(year,rec.get(0),gender);
       //System.out.println(currentRank);
       if(currentRank == rank  &&   rec.get(1).equals(gender)){
           System.out.println(currentRank);
           name = rec.get(0);
           
         }  
    }
       return name;
    }    
   public void testgetName(){
    System.out.println(getName(2012,2,"M"));}}   
