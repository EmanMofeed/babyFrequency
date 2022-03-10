package datastructure;

import Data.Data;

import java.lang.reflect.Array;

public class HashTable<T> {

    private int tableSize;
    private HashNode<T>[] table;
    private int currentSize;

    public HashTable(int size) {
        table = (HashNode<T>[]) Array.newInstance(HashNode.class, size);
        for (int i = 0; i < size; i++)
            table[i] = null;
        tableSize = size;
        currentSize = 0;
    }

    public HashNode[] getTable() {
        return table;
    }

    public void makeEmpty() {
        for (int i = 0; i < table.length; i++)
            table[i] = null;

        currentSize = 0;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int getTableSize() {
        return tableSize;
    }

    public boolean contains(T key) {
        return get(key) != null;
    }
    // Retrieve the value associated with the specified key
    // in the table, if there is any.  If not, the value
    // null will be returned.
    public T get(T data) {
        int i = 1;
        int location = getHash(data);
        while ((table[location] != null) && (table[location].getStatus() != 0)) {
            if (table[location].getData().equals(data))
                return table[location].getData();
            location = (location + i * i) % tableSize;
            i++;
        }
        return null;
    }

    public void   insert(T data) {

        if (currentSize >= tableSize / 2) {
            rehash();
        }

            int hash = getHash(data);
            int i = 1;
            while ((table[hash] != null) && (table[hash].getStatus() != 0) && (table[hash].getStatus() != 2) &&
                    data != table[hash].getData()) {
                hash = (hash + i * i) % tableSize;
            }
        currentSize++;
        table[hash] = new HashNode<>(data, 1);

        }

    public int getHash(T key) {
        return (Math.abs(key.hashCode())) % table.length;
    }

    private void rehash() {
        HashTable<T> newHashTable;
        newHashTable = new HashTable<>(nextPrime(2 * table.length));

        for (int i = 0; i < table.length; i++)
            if ((table[i] != null) && (table[i].getStatus() == 1))
                newHashTable.insert(table[i].getData());

        table = newHashTable.table;
        tableSize = newHashTable.tableSize;
    }

    public T find(T data) {
        int i = 1;
        int hash = getHash(data);
        while ((table[hash] != null) && (table[hash].getStatus() != 0) && (!table[hash].getData().equals(data)) &&(table[hash].getStatus() !=2)) {
            hash = (hash + i * i) % tableSize;
            i++;
        }
        if ((table[hash] == null) || (table[hash].getStatus() == 0))
            return null;
        else
            return table[hash].getData();
    }
    public boolean remove(T key) {
        int i = 1;
        if (!contains(key))
            return false;

        int hash = getHash(key);
        while ((table[hash] != null) && (table[hash].getStatus() != 0) && (!table[hash].getData().equals(key)))
            hash = (hash + i * i) % tableSize;
        currentSize--;
        table[hash].setDeleteStatus();
        return true;
    }


    private int nextPrime(int n) {
        if (n % 2 == 0)
            n++;
        while (!isPrime(n)) {
            n += 2;
        }
        return n;
    }

    private boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n / 2; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }


    public void printHashTable() {
        for (int i = 0; i < table.length; i++)
           if (table[i] == null)
                System.out.println("**");
           else if (table[i].getStatus() == 1)
                System.out.println(table[i].toString());
    }

}
