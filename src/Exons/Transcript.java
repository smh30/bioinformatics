package Exons;

import java.util.ArrayList;

public class Transcript {
    String chromosome;
    int trsStart;
    int trsEnd;
    ArrayList<Integer> exonStarts;
    ArrayList<Integer> exonEnds;
    
    public Transcript() {
    }
    
    public String getChromosome() {
        return chromosome;
    }
    
    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }
    
    public int getTrsStart() {
        return trsStart;
    }
    
    public void setTrsStart(int trsStart) {
        this.trsStart = trsStart;
    }
    
    public int getTrsEnd() {
        return trsEnd;
    }
    
    public void setTrsEnd(int trsEnd) {
        this.trsEnd = trsEnd;
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
        return this.chromosome +","+ this.trsStart +","+ this.trsEnd +","+ this.exonStarts+","+ this.exonEnds;
    }
}
