@FunctionalInterface
public interface ResourceParser<T> {
    T parse(String line);
}