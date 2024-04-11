package listadt;
import java.util.Comparator;
import java.util.function.*;

public class EmptyNode<T> implements GenericNode<T> {
    @Override
    public int count(Predicate<T> tester){
        return 0;
    }
    @Override
    public GenericNode<T> add(T data){
        return new ElementNode<T>(data, this);
    }
    @Override
    public GenericNode<T> insert(int index, T object){
        if (index == 0){
            return new ElementNode<T>(object, this);
        }else{
            throw new IllegalArgumentException("Invalid index");
        }
    }
    @Override
    public GenericNode<T> remove(T data){
        return this;
    }

    @Override
    public GenericNode<T> removeNodes(Predicate<T> tester){
        return this;
    }

    @Override
    public T get(int index) throws IllegalArgumentException{
        throw new IllegalArgumentException("Invalid index");
    }

    @Override
    public GenericNode<T> getNodes(Predicate<T> tester){
        return this;
    }

    @Override
    public <R> GenericNode<R> map(Function<T,R> converter) {
        return new EmptyNode<R>();
    }

    @Override
    public GenericNode<T> filter(Predicate<T> predicate) {
        return new EmptyNode<T>();
    }

    @Override
    public String toString(){
        return "";
    }
}
