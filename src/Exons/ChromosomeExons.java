package Exons;

import java.util.ArrayList;
import java.util.List;

public class ChromosomeExons {
    String chromosome;
    ArrayList<Integer> exonStarts = new ArrayList<Integer>();
    ArrayList<Integer> exonEnds = new ArrayList<Integer>();
    
    public String getChromosome() {
        return chromosome;
    }
    
    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }
    
    public ArrayList<Integer> getExonStarts() {
        return exonStarts;
    }
    
    public void setExonStarts(ArrayList<Integer> exonStarts) {
        this.exonStarts = exonStarts;
    }
    
    public ArrayList<Integer> getExonEnds() {
        return exonEnds;
    }
    
    public void setExonEnds(ArrayList<Integer> exonEnds) {
        this.exonEnds = exonEnds;
    }
    
    @Override
    public String toString() {
        return this.chromosome + "," + this.exonStarts +","+ this.exonEnds;
    }
}
