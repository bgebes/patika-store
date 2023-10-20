package patikastore.utilities;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataOperations {
    public static <T> boolean createItem(List<T> items, T item) {
        return items.add(item);
    }

    public static <T> T selectItem(List<T> items, int id) {
        return items.get(id);
    }

    public static <T> List<T> selectItemsByFilter(List<T> items, Predicate<T> predicate) {
        return items.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T> T updateItem(List<T> items, int id, T item) {
        return items.set(id, item);
    }

    public static <T> T deleteItem(List<T> items, int id) {
        return items.remove(id);
    }
}
