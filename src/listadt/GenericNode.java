package listadt;
import java.util.Comparator;
import java.util.function.*;

public interface GenericNode <T>{
    int count(Predicate<T> tester);
    GenericNode<T> add(T data);
    GenericNode<T> remove(T data);
    GenericNode<T> removeNodes(Predicate<T> tester);
    T get(int index);
    GenericNode<T> getNodes(Predicate<T> tester);
    <R> GenericNode<R> map(Function<T,R> converter);
    GenericNode<T> filter(Predicate<T> predicate);

    GenericNode<T> insert(int index, T object);
}
