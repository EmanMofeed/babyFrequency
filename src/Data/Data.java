package Data;

/**
 * Data class Heap  Entry
 */
public class Data implements Comparable<Data> {

    private int year;
    private int frequencies;
    //Eman,F,130
    public Data(String line, int year) {
        int firstQ = line.indexOf(',');
        int secondQ = line.indexOf(',', firstQ + 1);
        frequencies = Integer.parseInt(line.substring(secondQ + 1));
        this.year = year;
    }

    public Data(int year, int frequencies) {
        this.year = year;
        this.frequencies = frequencies;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(int frequencies) {
        this.frequencies = frequencies;
    }



    @Override
    public int compareTo(Data o) {
        if (this.frequencies == o.frequencies) {
            return 0;
        } else if (this.frequencies > o.getFrequencies()) {
            return 1;
        } else
            return -1;
    }

    @Override
    public String toString() {
        return "year=" + year + "frequencies=" + frequencies;
    }
}
