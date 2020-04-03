package eg.edu.alexu.csd.datastructure.stack.cs02;

import static org.junit.jupiter.api.Assertions.*;
import java.util.EmptyStackException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StackTest {
    @Test
    void testStack() {
        Stack s = new Stack();
        Stack n = new Stack();
        s.push(2);
        s.push('*');
        s.push("stack");
        s.push(99.99);
        s.push(n);//pushing stack to stack
        Assertions.assertThrows(EmptyStackException.class, ()->n.pop());
        n.push(99);
        n.push(60);
        assertEquals(60,(int) (((Stack) (s.peek())).pop()));
        assertEquals(1,n.size());
        assertEquals(5,s.size());
        s.pop();
        assertEquals(99.99,s.pop());
        assertEquals("stack",s.pop());
        assertEquals('*',s.pop());
        assertFalse(s.isEmpty());
        assertEquals(2,s.pop());
        assertTrue(s.isEmpty());
    }

    @Test
    void testPop() {
        Stack s = new Stack();
        Assertions.assertThrows(EmptyStackException.class, ()->s.pop());
        s.push(90);
        s.push(60);
        s.push(20.99);
        s.push("test");
        assertEquals("test",s.pop());
        assertEquals(20.99,s.pop());
        assertEquals(60,s.pop());
        assertEquals(90,s.pop());
        Assertions.assertThrows(EmptyStackException.class, ()->s.pop());
    }

    @Test
    void testPeek() {
        Stack s = new Stack();
        Assertions.assertThrows(EmptyStackException.class, ()->s.peek());
        s.push(8.88);
        assertEquals(8.88,s.peek());
        assertEquals(8.88,s.peek());
        s.push('a');
        assertEquals('a',s.peek());
        assertEquals('a',s.peek());
        s.push(77);
        assertEquals(77,s.peek());
        assertEquals(77,s.peek());
        s.push("peek");
        assertEquals("peek",s.peek());
        assertEquals("peek",s.peek());
        s.pop();
        assertEquals(77,s.peek());
        s.pop();
        assertEquals('a',s.peek());
        s.pop();
        assertEquals(8.88,s.peek());
        s.pop();
        Assertions.assertThrows(EmptyStackException.class, ()->s.peek());
    }

    @Test
    void testPush() {
        Stack s = new Stack();
        assertTrue(s.isEmpty());
        assertEquals(0,s.size());
        s.push(88);
        assertFalse(s.isEmpty());
        assertEquals(1,s.size());
        s.push('o');
        assertEquals(2,s.size());
        s.push("hi");
        assertEquals("hi",s.peek());
        s.push(0);
        assertEquals(0,s.peek());
        assertEquals(4,s.size());
        assertFalse(s.isEmpty());
    }

    @Test
    void testIsEmpty() {
        Stack s = new Stack();
        assertTrue(s.isEmpty());
        s.push('i');
        assertFalse(s.isEmpty());
        s.push(100);
        assertFalse(s.isEmpty());
        s.pop();
        assertFalse(s.isEmpty());
        s.pop();
        assertTrue(s.isEmpty());
        s.push("size");
        assertFalse(s.isEmpty());
        s.pop();
        assertTrue(s.isEmpty());
    }

    @Test
    void testSize() {
        Stack s = new Stack();
        assertEquals(0,s.size());
        s.push(0.11);
        assertEquals(1,s.size());
        s.pop();
        assertEquals(0,s.size());
        s.push('X');
        assertEquals(1,s.size());
        s.push("H");
        assertEquals(2,s.size());
        s.push(61);
        assertEquals(3,s.size());
        s.pop();
        assertEquals(2,s.size());
        s.pop();
        assertEquals(1,s.size());
        s.pop();
        assertEquals(0,s.size());
    }
}