package listadt;
import java.util.Comparator;
import java.util.function.*;
public class ElementNode<T> implements GenericNode<T>{
    private T data;
    private GenericNode<T> next;

    public ElementNode(T data, GenericNode<T> next){
        this.data = data;
        this.next = next;
    }

    @Override
    public int count(Predicate<T> tester){
        if (tester.test(this.data)){
            return 1 + this.next.count(tester);
        }else{
            return this.next.count(tester);
        }
    }

    @Override
    public GenericNode<T> add(T data) {
        this.next = this.next.add(data);
        return this;
    }

    @Override
    public GenericNode<T> insert(int index, T object){
        if (index == 0){
            return new ElementNode<T>(object, this);
        }else{
            this.next = this.next.insert(index - 1, object);
            return this;
        }
    }

    @Override
    public GenericNode<T> remove(T data) {
        if (this.data.equals(data)) {
            return this.next;
        } else {
            this.next = this.next.remove(data);
            return this;
        }
    }

    @Override
    public GenericNode<T> removeNodes(Predicate<T> tester){
        if (tester.test(this.data)){
            return this.next.removeNodes(tester);
        }else{
            this.next = this.next.removeNodes(tester);
            return this;
        }
    }
    @Override
    public T get(int index) {
        if (index == 0) {
            return this.data;
        }
        return this.next.get(index - 1);
    }

    @Override
    public GenericNode<T> getNodes(Predicate<T> tester){
        if (tester.test(this.data)){
            return new ElementNode<T>(this.data, this.next.getNodes(tester));
        }else{
            return this.next.getNodes(tester);
        }
    };

    @Override
    public <R> GenericNode<R> map(Function<T,R> converter) {
        return new ElementNode<R>(converter.apply(this.data), this.next.map(converter));
    }

    @Override
    public GenericNode<T> filter(Predicate<T> predicate) {
        if (predicate.test(data)) return new ElementNode<T>(data, next.filter(predicate));
        return next.filter(predicate);
    }

    @Override
    public String toString(){
        return data.toString()  + "\n" + next.toString();
    };
}
