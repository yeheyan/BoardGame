@FunctionalInterface
public interface ResourceParser<T> {
    /**
     * Parse a line of the resource file
     * @param line a line of the resource file
     * @return a resource
     */
    T parse(String line);
}