package datastructure;

public class HashNode<T> {
    private T data;
    private int status; //insert 1 , delete 2 , empty 0

    public HashNode(T data, int status) {
        this.data = data;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public void setDeleteStatus() {
        status = 2;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
