import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of Babytest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Babytest {
   public void FirstTest(){
       FileResource fr = new FileResource();
       int total = 0;
       int totalBoys = 0;
       int totalGirls = 0;
       int countM = 0;
       int countF = 0;
       int totalnames =0;
       for(CSVRecord rec : fr.getCSVParser(false)){
         int numborn = Integer.parseInt(rec.get(2));
         total += numborn;
         if(rec.get(1).equals("M")){
             totalBoys += numborn;
            countM += 1; 
        }
         else{totalGirls += numborn;
            countF += 1;}    
         
        }
       totalnames = countF + countM;
       System.out.println(totalnames);
       System.out.println(total);
       System.out.println(totalBoys);
       System.out.println(totalGirls);
       System.out.println(countM);
       System.out.println(countF);
    }
   public int getRank( int year,String name , String gender){
   FileResource fr = new FileResource(); 
   //DirectoryResource dr = new DirectoryResource();
   int rank = 0;
   for(CSVRecord rec : fr.getCSVParser(false)){
     if(  (rec.get(1)).equals(gender)){ rank += 1 ;
        // System.out.println(rec.get(0));
         //System.out.println(rec.get(1));
        if(rec.get(0).equals(name)){ break;}
        
      }
     //else{ rank = -1;}
     //if( !rec.get(1).equals(gender)){ rank = -1;}
     //rank = -1;
     //return -1;
    }
   return rank ; }
   public void testgetRank(){
      
       int test = getRank(2012,"Emma","M");
       System.out.println(test);}
   public String getName( int year,int rank , String gender){
       FileResource fr = new FileResource("yob"  + year + ".csv");
       int Rank = 0;
       String name = "";
       for( CSVRecord rec : fr.getCSVParser(false)){
        Rank = getRank(year,rec.get(0),gender);
           if(rec.get(1).equals(gender)){
               if(Rank == rank){name  = rec.get(0);
                }
            }
        }
    
       return name;}
    public void testgetName(){
       String tesT = getName(1982,450,"M");
       System.out.println(tesT);}
    public void whatIsNameinYear(String name,int year , int newYear, String gender){
        DirectoryResource dr = new DirectoryResource();
        String newName = "";
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource();
           for(CSVRecord rec : fr.getCSVParser(false)){;
           if(rec.get(1).equals(gender) && rec.get(0).equals(name)){
            int currentRank = getRank(year,rec.get(0),rec.get(1));
            newName = getName(year,currentRank,gender);
     
            }}}
        System.out.println(newName);
    }
     public int yearOfHighestRank(String name,String gender){
       DirectoryResource dr = new DirectoryResource();
         int highestRank = 0;
         int highestYear = 0;
         int currentYear = 0;
         String currentFileName ="";
         for( File f : dr.selectedFiles()){
            //FileResource fr = new FileResource();
          currentFileName = f.getName();
          currentYear = Integer.parseInt(currentFileName.substring(3,7));
            //System.out.println(currentYear + " Name of current file being processed " +currentFileName);
          int currentRank = getRank(currentYear, name, gender); 
            //System.out.println(currentRank + "for baby " +name);
          if(highestRank < currentRank) {  // keeps a track for the highest rank found from all files processed so far
           highestRank = currentRank;
           highestYear = currentYear;
              //System.out.println( " Name of baby " +name + " baby rank " +highestRank + " year " +highestYear);
                   }
       } 
        System.out.println(highestYear);
        return highestYear;
               
            }
        public void testYearOfHighest(){
        yearOfHighestRank("Mich","M");
        }
    public void boysTest(int year,String name,String gender){
     FileResource fr = new FileResource();
     int total = 0;
     for(CSVRecord rec : fr.getCSVParser(false)){
         int numborn = Integer.parseInt(rec.get(2));
       if(rec.get(1).equals(gender)){
           total += numborn;
        if(rec.get(0).equals(name)){break;}
        } 
      }
      System.out.println(total);}
    public void testboysTest(){
        boysTest(2012,"Ethan","M");
    }
   public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource();
        int pivot = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                pivot++;
                if(rec.get(0).equals(name)){
                    break;
                }
            }
        }
        int sum = 0;
        int newPivot = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                newPivot++;
                if(newPivot < pivot){
                    sum += Integer.parseInt(rec.get(2));
                }
            }
        }
        //System.out.println(sum);
        return sum;
    }
    
   public void testTotalBirthsRankedHigher(){
        //FileResource fr = new FileResource();
        int sum = getTotalBirthsRankedHigher(2012, "Emily", "F");
        System.out.println(sum);
    }
 
    public double getAverageRank(String name,String gender){
    DirectoryResource dr = new DirectoryResource();
    int currentrank = 0;
    int totalRank = 0;
    int countFile =0;
    for(File f : dr.selectedFiles()){
     FileResource fr = new FileResource(f);
      countFile += 1;
     for(CSVRecord rec : fr.getCSVParser(false)){   
      if(  (rec.get(1)).equals(gender)){ currentrank += 1 ;
        // System.out.println(rec.get(0));
         //System.out.println(rec.get(1));
        if(rec.get(0).equals(name)){break; }
        //System.out.println(currentrank);
      }
     //if(currentrank!=-1){
      //countFile += 1;
      //totalRank += currentrank;
      //}
    }      
      //System.out.println(currentrank);
     // totalRank += currentrank;
     
    }
      totalRank += currentrank;
    System.out.println(countFile);
      return (double)totalRank/countFile;}
   public void test(){
    DirectoryResource dr = new DirectoryResource();
    System.out.println(getAverageRank("Robert","M"));}
}
      
 