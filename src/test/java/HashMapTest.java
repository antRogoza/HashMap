import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashMapTest {

    HashMap map;

    @Before
    public void initial() {

        map = new HashMap();
        map.put(1, 2);
        map.put(2, 3);
        map.put(3, 4);
    }

    @Test
    public void size() {

        assertNotEquals(-1, map.size());
        assertNotEquals(0, map.size());
        assertEquals(3, map.size());
        map.put(5, 5);
        assertEquals(4, map.size());
    }

    @Test
    public void get() {

        assertEquals(2, map.get(1));
        assertEquals(4, map.get(3));
        map.put(0, 1);
        assertEquals(1, map.get(0));
    }

    @Test
    public void put() {

        assertNotNull(map);
        assertEquals(1, map.map[1].getKey());
        assertEquals(2, map.map[1].getValue());
    }

}