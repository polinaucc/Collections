package ua.polina.collections;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.polina.collections.arrayList.Array;
import ua.polina.collections.arrayList.SimpleArrayList;

class SimpleArrayListTest {
    Array<String> strings;
    @BeforeEach
    void setUp() {
        strings = new SimpleArrayList<>();
        strings.add("test");
        strings.add("test2");
    }

    @Test
    void add() {
        Assert.assertTrue(strings.add("one"));
        Assert.assertTrue(strings.add("two"));
    }
    @Test
    void get(){
        String value = strings.get(0);
        Assert.assertEquals(value, "test");
    }
    @Test
    void update(){
        strings.updat(0, "newTest");
        Assert.assertEquals(strings.get(0), "newTest");
    }

    @Test
    void delete(){
        strings.delete(1);
        Assert.assertEquals(strings.size(), 1);
    }


}