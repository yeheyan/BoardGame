package listadt;
import java.io.*;
import java.util.Comparator;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.function.*;

public class ListImp<T> {
    private GenericNode<T> head;

    public ListImp(){
        this.head = new EmptyNode<T>();
    }

    private ListImp(GenericNode<T> head){
        this.head = head;
    }

    public void add(T object){
        this.head = this.head.add(object);
    }
    public void insert(int index, T object){
        this.head = this.head.insert(index, object);
    }

    public int count(Predicate<T> tester){
        return this.head.count(tester);
    }

    public void remove(T object){
        this.head = this.head.remove(object);
    }

    public void removeNodes(Predicate<T> tester){
        this.head = this.head.removeNodes(tester);
    }

    public T get(int index) {
        if (index < 0 || index >= this.count(x -> true)){
            throw new IllegalArgumentException("Invalid index");
        }else {
            return this.head.get(index);
        }
    }

    public ListImp<T> getNodes(Predicate<T> tester){
        return new ListImp<T>(this.head.getNodes(tester));
    }

    public <R> ListImp<R> map(Function<T,R> converter) {
        return new ListImp<R>(head.map(converter));
    }
    public ListImp<T> filter(Predicate<T> predicate) {
        return new ListImp<T>(head.filter(predicate));
    }

    public void saveFile(String filename) throws IOException {
        try {
            File file = new File(filename);
            java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(file, true));
            for (int i = 0; i < this.count(x -> true); i++) {
                pw.println(this.get(i).toString());
            }
            pw.close();
        } catch (java.io.IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    @Override
    public String toString(){
        return this.head.toString();
    }


}
