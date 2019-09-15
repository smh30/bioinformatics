package FamilyVariants;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FitPatterns {
    
    public void run() {
        ArrayList<Record> records = new ArrayList<Record>();
        
        
        //first read in the file
        //this had to be done bit by bit due to GC overflow errors with big file
        try (BufferedReader br =
                     new BufferedReader(new FileReader("last2m.txt"))) {
            
            //throw out the header info - only on first chunk
            //String readLine = br.readLine();
            
            String readLine;
            
            int lines = 0;
            
            while ((readLine = br.readLine()) != null) {
                lines++;
                //split each line on the tabs
                String[] array = readLine.split("\t");
                
                //create an object with just the relevant fields
                //relevant fields: 0,1, 3, 4, 9,10,11,12,13,14,15
                records.add(new Record(array[0],
                        array[1],
                        array[3],
                        array[4],
                        array[9],
                        array[10],
                        array[11],
                        array[12],
                        array[13],
                        array[14],
                        array[15]));
            }
            
            
            System.out.println("lines: " + lines);
        } catch (IOException e) {
            System.out.println("IO error " + e);
        }
        
        
        
        //search the records for ones that meet our pattern
        //i'm sure there's a nicer way to do this than this hideous if but it works
        System.out.println("last record: " + records.get(records.size()-1));
        int matches = 0;
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("matches.txt",
                true)))) {
    
            
            for (Record record : records) {
                //this is for the first disease, sickle cell anaemia
                //assuming recessive inheritance (which wikipedia agrees with)
                if ((record.father.equals("0/1") || record.father.equals("1/0")) &&
                        (record.mother.equals("0/1") || record.mother.equals("1/0")) &&
                        (record.daughter1.equals("0|1") || record.daughter1.equals("1|0") || record.daughter1.equals("0/1") || record.daughter1.equals("1/0")) &&
                        (record.son1.equals("0|1") || record.son1.equals("1|0") || record.son1.equals("0/1") || record.son1.equals("1/0")) &&
                        (record.daughter2.equals("1|1")) &&
                        (record.son2.equals("1|1"))
                        &&
                        (record.daughter3.equals("0|0"))
                ) {
                    matches++;
                    System.out.println(record);
                    out.println(record.toString());
                }
        
            }
        } catch (IOException e){
            System.out.println("IO exception with writing" + e);
        }
        System.out.println("matches: " + matches);
        
        
    }
    
    
    public static void main(String[] args) {
        FitPatterns instance = new FitPatterns();
        instance.run();
    }
    
    
}
