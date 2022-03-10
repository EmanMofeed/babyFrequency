package Data;
import datastructure.Heap;
import java.util.Objects;
/*
 *Baby class : Entry of the hash table (name , gender ,heap)
 */
public class Baby {

    private String name;
    private String gender;
    private Heap<Data> yearHeap;

    public Baby(String line, Heap<Data> yearHeap) {
        int firstQ = line.indexOf(',');
        int secondQ = line.indexOf(',', firstQ + 1);
        name = line.substring(0, firstQ);
        gender = line.substring(firstQ + 1, secondQ);
        this.yearHeap = yearHeap;
    }

    public Baby(String name, String gender, Heap<Data> yearHeap) {
        this.name = name;
        this.gender = gender;
        this.yearHeap = yearHeap;
    }

    public Baby(String name, String gender) {
        this.name = name;
        this.gender = gender;
        this.yearHeap=new Heap<Data>(17, Data.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Heap<Data> getYearHeap() {
        return yearHeap;
    }

    public void setYearHeap(Heap<Data> yearHeap) {
        this.yearHeap = yearHeap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Baby baby = (Baby) o;
        return Objects.equals(name, baby.name) &&
                Objects.equals(gender, baby.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender);
    }


    @Override
    public String toString() {
        return "name=  " + name + "  gender=  " + gender + "  \n" + yearHeap.toString();

    }
}
