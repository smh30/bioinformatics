package Exons;

import FamilyVariants.Record;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class FilterByExons {
    
    
    public void run() {
        
        int lines = 0;
        ArrayList<Transcript> transcripts = new ArrayList<Transcript>();
        
        //read in the exons
        try (BufferedReader br = new BufferedReader(new FileReader("wgEncodeGencodeBasicV17.txt"))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                
                lines++;
                
                String[] array = line.split("\t");
                
                Transcript transcript = new Transcript();
                transcript.setChromosome(array[2]);
                transcript.setTrsStart(Integer.parseInt(array[4]));
                transcript.setTrsEnd(Integer.parseInt(array[5]));
                
                String starts = array[9]; //format of this string is 45783,45378,
                //needs to be split into a list of ints
                String[] startArray = starts.split(",");
                ArrayList<Integer> startList = new ArrayList<Integer>();
                for (String s : startArray) {
                    startList.add(Integer.parseInt(s));
                }
                transcript.setExonStarts(startList);
                
                String ends = array[10];
                String[] endArray = ends.split(",");
                ArrayList<Integer> endList = new ArrayList<Integer>();
                for (String s : endArray) {
                    endList.add(Integer.parseInt(s));
                }
                transcript.setExonEnds(endList);
                
                //System.out.println(transcript);
                
                transcripts.add(transcript);
            }
            
        } catch (IOException e) {
            System.out.println("IO exception with reading " + e);
        }
        
        System.out.println("lines in exon file: "+lines);
        
        //create a list of the chromosomes represented, since there are weird names like chr17_ctg5_hap1
        HashSet<String> chromosomeNames = new HashSet<String>();
        for (Transcript transcript : transcripts) {
            chromosomeNames.add(transcript.chromosome);
        }
        
        
        //lol sorry about the variable names in this bit, it got messy
        //this is basically creating a list of objects
        //where each object represents all of the coding regions on one chromosome
        //so it has a String with the chromosome name
        //and lists of start and end points
        ArrayList<ChromosomeExons> exonsByChromosome = new ArrayList<ChromosomeExons>();
        for (String chromosomeName : chromosomeNames) {
            //System.out.println("chromosome name: " + chromosomeName);
            ChromosomeExons thisChromosome = new ChromosomeExons();
            thisChromosome.chromosome = chromosomeName;
            for (Transcript transcript : transcripts) {
                if (transcript.chromosome.equals(chromosomeName)){
                    thisChromosome.exonStarts.addAll(transcript.exonStarts);
                    thisChromosome.exonEnds.addAll(transcript.exonEnds);
                }
            }
            exonsByChromosome.add(thisChromosome);
        }
    
    
//        for (ChromosomeExons chromosomeExons : exonsByChromosome) {
//            System.out.println(chromosomeExons);
//        }
        
        
        
        ArrayList<Record> records = new ArrayList<Record>();
        lines=0;
        //read in the matches file into a Record
        try (BufferedReader br = new BufferedReader(new FileReader("matches.txt"))) {
            String line;
            while((line=br.readLine())!=null){
                String[] array = line.split(",");
                lines++;
                records.add(new Record(array[0],
                        array[1],
                        array[2],
                        array[3],
                        array[4],
                        array[5],
                        array[6],
                        array[7],
                        array[8],
                        array[9],
                        array[10]));
            }
            
        }catch (IOException e){
            System.out.println("Error reading the matches: "+e);
        }
    System.out.println("lines in records: " +lines);

        
      
        
        // find the matches between the two
        //for each of the records (our matches from the previous step)
        //there's gotta be a better way, this is getting slow.....
        int numRecords = 0;
        int numMatches = 0;
        
        ArrayList<Record> matches = new ArrayList<>();
        
        for (Record record : records) {
            numRecords++;
//            if (record.getPosition().equals("19187291")){
//                System.out.println(record);
//            }
            //grab the chromosome exon object for its chromosome
            ChromosomeExons exons = getExonsWithChromosome(exonsByChromosome,
                    record.getChromosome());
           //System.out.println(exons);
            
            //and then check if the location of the record is in the coding parts
            
            int numOfExons = exons.exonStarts.size();
            int recordPosition = Integer.parseInt(record.getPosition());
    
            for (int i = 0; i < numOfExons ; i++) {
                if (exons.exonStarts.get(i)<=recordPosition && recordPosition<= exons.exonEnds.get(i)){
                    //System.out.println(record);
                    matches.add(record);
                    numMatches++;
                }
                
            }
        }
        
        //there are some duplicates in the results, so add to a set to remove them
        //seems like dups are due to overlapping/ same exon for multiple coding regions????
        HashSet<Record> uniqueMatches = new HashSet<>(matches);
        
        
        System.out.println("Records checked: " + numRecords + ", Matches: " + numMatches + ", " +
                "matches.size: " + matches.size() + ", unique: " + uniqueMatches.size());
        
        
        //write the results to file
    
    int printed = 0;
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("matches_in_exons" +
                ".txt",
                true)))) {
            for (Record uniqueMatch : uniqueMatches) {
                out.println(uniqueMatch);
                printed++;
            }
        
           
            }
         catch (IOException e){
            System.out.println("IO exception with writing" + e);
        }
        System.out.println("printed: " + printed);
    
    
    }
    
    
    public ChromosomeExons getExonsWithChromosome(ArrayList<ChromosomeExons> chromosomeExons,
                                                  String chromosome){
        for (ChromosomeExons chromosomeExon : chromosomeExons) {
            if (chromosomeExon.getChromosome().equals("chr"+chromosome)){
                return chromosomeExon;
            }
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        FilterByExons filter = new FilterByExons();
        filter.run();
    }
}
