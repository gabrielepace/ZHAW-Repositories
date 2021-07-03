package aufgabe_2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ADS FS2019 
 * Praktikum 2
 * 
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */


public class StackTest {

    Stack stack;

    @Before
    public void setUp() throws Exception {
        stack = new ListStack();
    }

    @Test
    public void testPush1() {
        stack.push("A");
        Object o = stack.pop();
        assertEquals(o, "A");
    }

    @Test
    public void testPush2() {
        stack.push("A");
        stack.push("B");
        assertEquals(stack.pop(), "B");
        assertEquals(stack.pop(), "A");
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push("A");
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsFull() {
        assertFalse(stack.isFull());
    }

    @Test
    public void testEmptyPop() {
        try {
            stack.pop(); // throws exception
            fail("empty stack not handled");
        } catch (Exception e) {
        }
    }
}

