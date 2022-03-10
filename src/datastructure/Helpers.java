package datastructure;

import Data.Baby;
import Data.Data;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Helpers {

    HashTable<Baby> hashTable = new HashTable<>(10);



    public HashTable<Baby> read(int year) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        HashTable<Baby> readHash = new HashTable<>(10);

        if (selectedFile != null) {
            File file = new File(selectedFile.getAbsolutePath());
            readHash = addFile(file.getAbsolutePath(), year);
        }
        return readHash;
    }

    //add file to AVL tree
    private HashTable<Baby> addFile(String path, int year) {
        try {
            File file = new File(path);
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String nextLine = input.nextLine();
                Data frequencies = new Data(nextLine, year);
                Heap<Data> heap = new Heap<>(17, Data.class);
                heap.insert(frequencies);
                Baby baby = new Baby(nextLine, heap);
                Baby found = hashTable.find(baby);
                if (found == null) {
                    hashTable.insert(baby);
                } else {
                    Data remove = heap.remove();
                    while (remove != null) {
                        found.getYearHeap().insert(remove);
                        remove = heap.remove();
                    }
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return hashTable;
    }

//    public int sizeOfHash() {
//        int totalNumberOfNames = 0;
//        DirectoryChooser directoryChooser = new DirectoryChooser();
//        File selectedDirectory = directoryChooser.showDialog(null);
//        File[] arrays = selectedDirectory.listFiles();
//        for (File file : arrays) {
//            totalNumberOfNames += readFile(file.getAbsolutePath());
//        }
//        return totalNumberOfNames / arrays.length;
//    }

    public int readFile(String path) {
        int i = 0;
        try {
            File file = new File(path);
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                i++;
                input.nextLine();
            }
            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return i;
    }

    public void insert(String name, String gender) {
        Heap<Data> heap = new Heap<Data>(17, Data.class);
        Baby newBabyRecord = new Baby(name, gender, new Heap<Data>(17, Data.class));
        Baby found = hashTable.find(newBabyRecord);
        if (found == null) {
            hashTable.insert(newBabyRecord);
        } else {
            Data remove = heap.remove();
            while (remove != null) {
                found.getYearHeap().insert(remove);
                remove = heap.remove();
            }
            hashTable.insert(newBabyRecord);
        }
    }

    public String delete(String name, String gender) {
        Baby deleted = new Baby(name, gender);
        if (hashTable.remove(deleted)) {
            return "deleted Successfully";
        } else {
            return "does not exist in the table";
        }
    }

    public String search(String name, String gender) {
        Baby search = new Baby(name, gender);
        Baby found = hashTable.find(search);
        return found.toString();
    }

    public String addToHeap(String name, String gender, int year, int frequency) {
        Baby search = new Baby(name, gender);
        Baby found = hashTable.find(search);
        if (found == null) {
            return "the Key Not found" + search.toString();
        } else {
            Data[] array = found.getYearHeap().getHeapTable();
            for (int i = 1; i < array.length; i++) {
                if (array[i] == null) {
                    Data data = new Data(year, frequency);
                    found.getYearHeap().insert(data);
                    return found.toString();
                } else if (array[i].getYear() == year) {
                    array[i].setFrequencies(array[i].getFrequencies() + frequency);
                    break;
                }
            }

        }
        return found.toString();

    }

    public String update(String name, String gender, int year, int frequency) {
        Baby search = new Baby(name, gender);
        Baby find = hashTable.find(search);
        if (find == null) {
            return "the name and gender does not exist in Hash table";
        } else {
            Data[] data = find.getYearHeap().getHeapTable();
            for (int i = 1; i < data.length; i++) {
                if (data[i] == null) {
                    return "the year does not exist in the hash table";
                } else {
                    if (data[i].getYear() == year) {
                        data[i].setFrequencies(frequency);
                        break;
                    }

                }
            }
            return "new Record = name :  " + name + "\n gender = " + gender + " new Frequency " + frequency;
        }
    }

    public Baby maxFrequency() {
        int maxFreq = Integer.MIN_VALUE;
        Baby maxFreqBaby = null;

        for (int i = 0; i < hashTable.getTableSize(); i++) {
            if (hashTable.getTable()[i] != null) {
                Baby baby = ((Baby) hashTable.getTable()[i].getData());

                int freq = baby.getYearHeap().getHeapTable()[1].getFrequencies();
                if (freq > maxFreq) {
                    maxFreq = freq;
                    maxFreqBaby = baby;
                }
            } else continue;
        }

        return maxFreqBaby;
    }


}
