import listadt.ListImp;

import java.io.InputStream;
import java.util.Scanner;

public class ResourceLoader<T> {
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
        return resources;
    }
}
