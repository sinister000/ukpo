package structures;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class HashtableTest {

    //White box testing methods

    @Test
    public void getByNotNullExistingKey_shouldReturnValue() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        assertEquals("zero", hashtable.get(0));
    }

    @Test
    public void getByNotNullNonExistentKey_shouldReturnNull() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        assertNull(hashtable.get(1));
    }

    @Test
    public void getByNullKey_shouldReturnNull() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        assertNull(hashtable.get(null));
    }

    @Test
    public void containsNotNullExistingValue_shouldReturnTrue() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        assertTrue(hashtable.containsValue("zero"));
    }

    @Test
    public void containsNotNullNonExistentValue_shouldReturnFalse() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        assertFalse(hashtable.containsValue("one"));
    }

    @Test
    public void containsNullValue_shouldReturnFalse() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        assertFalse(hashtable.containsValue("one"));
    }

    @Test
    public void putNullKey_shouldThrowException() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> hashtable.put(null, ""));

        String expectedMessage = "Unable to add element with null key into hashtable.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void putNullValue_shouldThrowException() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> hashtable.put(1, null));

        String expectedMessage = "Unable to add null into hashtable.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void itemNumberLessThanThreshold_capacityShouldBeThree() throws NoSuchFieldException, IllegalAccessException {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        hashtable.put(1, "one");
        Field capacity = hashtable.getClass().getDeclaredField("capacity");
        capacity.setAccessible(true);
        assertEquals(3, capacity.getInt(hashtable));
    }

    @Test
    public void itemNumberMoreThanThreshold_shouldIncreaseTable() throws NoSuchFieldException, IllegalAccessException {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        hashtable.put(1, "one");
        hashtable.put(2, "two");
        Field capacity = hashtable.getClass().getDeclaredField("capacity");
        capacity.setAccessible(true);
        assertEquals(7, capacity.getInt(hashtable));
    }

    //Black box testing methods

    @Test
    public void sizeEqualsFive() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        hashtable.put(1, "one");
        hashtable.put(2, "two");
        hashtable.put(3, "three");
        hashtable.put(4, "four");

        assertEquals(5, hashtable.size());
    }

    @Test
    public void removeNonexistentItem() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        hashtable.put(1, "one");
        hashtable.put(2, "two");
        hashtable.put(3, "three");
        hashtable.put(4, "four");

        assertFalse(hashtable.remove(10));
    }

    @Test
    public void removeExistingItem() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        hashtable.put(1, "one");
        hashtable.put(2, "two");
        hashtable.put(3, "three");
        hashtable.put(4, "four");

        assertTrue(hashtable.remove(4));
    }

    @Test
    public void getNonexistentItem() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        hashtable.put(1, "one");
        hashtable.put(2, "two");
        hashtable.put(3, "three");
        hashtable.put(4, "four");

        assertNull(hashtable.get(5));
    }

    @Test
    public void getExistingItem() {
        Hashtable<Integer, String> hashtable = new Hashtable<>(3);
        hashtable.put(0, "zero");
        hashtable.put(1, "one");
        hashtable.put(2, "two");
        hashtable.put(3, "three");
        hashtable.put(4, "four");

        assertEquals("zero", hashtable.get(0));
    }
}