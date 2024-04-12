import listadt.ListImp;

import java.io.InputStream;
import java.util.Scanner;
/**
 * ResourceLoader class
 * Load resources from a file
 * @param <T> type of the resource
 */
public class ResourceLoader<T> {
    /**
     * Load resources from a file
     * @param resourcePath path to the resource file
     * @param parser parser to parse each line of the resource file
     * @return a list of resources
     */
    public ListImp<T> load(String resourcePath, ResourceParser<T> parser) {
        ListImp<T> resources = new ListImp<>();
        InputStream is = getClass().getResourceAsStream(resourcePath);
        if (is == null) {
            throw new RuntimeException("Resource not found: " + resourcePath);
        }
        try (Scanner scanner = new Scanner(is)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                T resource = parser.parse(line);
                resources.add(resource);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Error reading resource: " + resourcePath, e);
        }
        return resources;
    }
}
