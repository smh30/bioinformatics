package FamilyVariants;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FitPatterns {
    
    public void run() {
        // ArrayList<Record> records = new ArrayList<Record>();
        int lines = 0;
        int matches = 0;
        //first read in the file
        //this had to be done bit by bit due to GC overflow errors with big file
        try (BufferedReader br =
                     new BufferedReader(new FileReader("merged_trimmed_vcf.txt"))) {
            
            //throw out the header info - only on first chunk
            //String readLine = br.readLine();
            
            String readLine;
            
            
            while ((readLine = br.readLine()) != null) {
                lines++;
                //System.out.println("lines: " + lines);
                //split each line on the tabs
                String[] array = readLine.split("\t");
                
                //create an object with just the relevant fields
                //relevant fields: 0,1, 3, 4, 9,10,11,12,13,14,15
//                records.add(new Record(array[0],
//                        array[1],
//                        array[3],
//                        array[4],
//                        array[9], //father
//                        array[10], //mother
//                        array[11],  //d1
//                        array[12],  //d2
//                        array[13],  //d3
//                        array[14],  //s1
//                        array[15]));  //s2
                
                
                // ------------------------------------------------------------------------
                
                
                try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                        "matches_v3.txt",
                        true)))) {
                    
                    
                    //for (Record record : records) {
                    //this is for the first disease, sickle cell anaemia
                    //assuming recessive inheritance (which wikipedia agrees with)
                    for (int i = 1; i < 6; i++) {
                        //System.out.println(i);
                        //i is the alternate whatever numbet that may be
                        
                        
                        String heteroSlash = "([^" + i + "]/[" + i + "])|([" + i + "]/[^" + i + "])";
                        String heteroPipe = "([^" + i + "]\\|[" + i + "])|([" + i + "]\\|[^" + i +
                                "])";
                        String homoAlt = "([" + i + "]/[" + i + "])|([" + i + "]\\|[" + i + "])";
                        
                        String homoRef = "([^" + i + "]/[^" + i + "])|([^" + i + "]\\|[^" + i +
                                "])";
                        
                        //somehow replace 0 with !i and 1 with i?????????????????????
                        
                        if (array[9].matches(heteroSlash)
                                &&
                                array[10].matches(heteroSlash)
 &&
                                array[11].matches(heteroSlash + "|" + heteroPipe)
 &&
                                array[14].matches(heteroSlash + "|" + heteroPipe)
 &&
                                array[12].matches(homoAlt)
 &&
                                array[15].matches(homoAlt)
                                &&
                                array[13].matches(homoRef)
                        ) {
                            matches++;
                            
                            //todo alter this so that it only prints the relevant parts?????
                            out.println(Arrays.toString(array));
                            System.out.println(Arrays.toString(array));
                        }
                    }
                    
                } catch (IOException e) {
                    System.out.println("IO exception with writing" + e);
                }
                //System.out.println("matches: " + matches);
                
                //--------------------------------------------------------------------------
                
                
            }
            
            
            System.out.println("lines: " + lines);
            System.out.println("matches: " + matches);
        } catch (IOException e) {
            System.out.println("IO error " + e);
        }
        
        
        //search the records for ones that meet our pattern
        //i'm sure there's a nicer way to do this than this hideous if but it works
        
        
    }
    
    
    public static void main(String[] args) {
        FitPatterns instance = new FitPatterns();
        instance.run();
    }
    
    
}
