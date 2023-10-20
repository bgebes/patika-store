package patikastore.utilities;

import java.util.Map;

public interface MapOperations<K, V, T> {
    Map<K, V> toMap();

    T fromMap(Map<String, String> map);

    String[] getHeaders();

    K[] getKeys();

    V[] getValues();

}
