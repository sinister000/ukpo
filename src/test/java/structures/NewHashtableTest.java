package structures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewHashtableTest {

    @Test
    public void getKeys_shouldReturnAllKeys() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        hashtable.put(1, "one");
        hashtable.put(2, "two");
        hashtable.put(3, "three");
        hashtable.put(4, "four");

        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        assertEquals(expected, hashtable.keys());
    }

    @Test
    public void getValues_shouldReturnAllValues() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        hashtable.put(1, "one");
        hashtable.put(2, "two");
        hashtable.put(3, "three");
        hashtable.put(4, "four");

        List<String> expected = new ArrayList<>(Arrays.asList("zero", "one", "two", "three", "four"));
        assertEquals(expected, hashtable.values());
    }

    @Test
    public void getValueWithGetOrDefaultMethod_shouldReturnValue() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        hashtable.put(1, "one");
        hashtable.put(2, "two");
        hashtable.put(3, "three");
        hashtable.put(4, "four");

        assertEquals("zero", hashtable.getOrDefault(0, "default value"));
    }

    @Test
    public void getValueWithGetOrDefaultMethod_shouldReturnDefaultValue() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        hashtable.put(1, "one");
        hashtable.put(2, "two");
        hashtable.put(3, "three");
        hashtable.put(4, "four");

        assertEquals("default value", hashtable.getOrDefault(5, "default value"));
    }
}