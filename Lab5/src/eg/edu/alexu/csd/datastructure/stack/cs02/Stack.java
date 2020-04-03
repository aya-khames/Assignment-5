package eg.edu.alexu.csd.datastructure.stack.cs02;

import java.util.EmptyStackException;

public class Stack implements IStack{
    private class Node {
        private Object data;
        private Node next;
        public Node(Object element,Node Next) {
            data = element;
            next = Next;
        }

        public Object getElement() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }

    private Node Top;
    private int Size;
    public Stack() {
        Top = null;
        Size = 0;
    }

    /**
     * Removes the element at the top of stack and returns that element.
     * @return top of stack element, or through exception if empty
     */

    @Override
    public Object pop() {
        if(Size==0)
            throw new EmptyStackException();
        else{
            Size--;
            Object temp = Top.getElement();
            Top = Top.getNext();
            return temp;
        }
    }

    /**
     * Get the element at the top of stack without removing it from stack.
     * @return top of stack element, or through exception if empty
     */

    @Override
    public Object peek() {
        if(Size==0)
            throw new EmptyStackException();
        else
            return Top.getElement();
    }

    /**
     * Pushes an item onto the top of this stack.
     * @param object
     * to insert
     */

    @Override
    public void push(Object element) {
        Node N = new Node(element,Top);
        Size++;
        Top = N;
    }

    /**
     * Returns the number of elements in the stack.
     * @return number of elements in the stack
     */

    @Override
    public boolean isEmpty() {
        if(Size==0)
            return true;
        else
            return false;
    }

    /**
     * Returns the number of elements in the stack.
     * @return number of elements in the stack
     */

    @Override
    public int size() {
        return Size;
    }
}