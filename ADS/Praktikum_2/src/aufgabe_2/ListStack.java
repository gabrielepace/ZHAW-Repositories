package aufgabe_2;

import java.util.LinkedList;

import java.util.List;


/**
 * ADS FS2019 
 * Praktikum 2
 * 
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class ListStack<E> implements Stack<E> {

    private final List<E> list;

    
    // make a new stack using @link  linkedlias as a basis of the stack
    public ListStack() {
        this(new LinkedList<>());
    }

   
    // make a new stack base on the list param.
    public ListStack(List<E> list) {
        this.list = list;
    }

    @Override
    public void push(E e) {
        list.add(e);
    }
    
    // make pop() till the stack empty 

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The stack is empty.");
        }
        
        // the header postion is a final type 

        final int headPosition = list.size() - 1;

        final E head = list.get(headPosition);
        list.remove(headPosition);

        return head;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The stack is empty.");
        }

        return list.get(list.size() - 1);
    }
    
    // cheake if the stack empty

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
    // cheake if the class is full

    @Override
    public boolean isFull() {
        return false;
    }
}

