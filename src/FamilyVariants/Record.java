package FamilyVariants;

public class Record {
    String chromosome;
    String position;
    String referent;
    String alternate;
    String father;
    String mother;
    String daughter1;
    String daughter2;
    String daughter3;
    String son1;
    String son2;
    
    public Record(String chromosome, String position, String referent, String alternate, String father, String mother, String daughter1, String daughter2, String daughter3, String son1, String son2) {
        this.chromosome = chromosome;
        this.position = position;
        this.referent = referent;
        this.alternate = alternate;
        this.father = father;
        this.mother = mother;
        this.daughter1 = daughter1;
        this.daughter2 = daughter2;
        this.daughter3 = daughter3;
        this.son1 = son1;
        this.son2 = son2;
    }
    
    public Record() {
    }
    
    public String getChromosome() {
        return chromosome;
    }
    
    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public String getReferent() {
        return referent;
    }
    
    public void setReferent(String referent) {
        this.referent = referent;
    }
    
    public String getAlternate() {
        return alternate;
    }
    
    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }
    
    public String getFather() {
        return father;
    }
    
    public void setFather(String father) {
        this.father = father;
    }
    
    public String getMother() {
        return mother;
    }
    
    public void setMother(String mother) {
        this.mother = mother;
    }
    
    public String getDaughter1() {
        return daughter1;
    }
    
    public void setDaughter1(String daughter1) {
        this.daughter1 = daughter1;
    }
    
    public String getDaughter2() {
        return daughter2;
    }
    
    public void setDaughter2(String daughter2) {
        this.daughter2 = daughter2;
    }
    
    public String getDaughter3() {
        return daughter3;
    }
    
    public void setDaughter3(String daughter3) {
        this.daughter3 = daughter3;
    }
    
    public String getSon1() {
        return son1;
    }
    
    public void setSon1(String son1) {
        this.son1 = son1;
    }
    
    public String getSon2() {
        return son2;
    }
    
    public void setSon2(String son2) {
        this.son2 = son2;
    }
    
    @Override
    public String toString() {
        return this.chromosome +","+ this.position +","+
                this.referent+","+this.alternate+","+
                this.father+","+this.mother+","+
                this.daughter1+","+this.daughter2+","+this.daughter3+","+
                this.son1+","+this.son2;
    }
}
