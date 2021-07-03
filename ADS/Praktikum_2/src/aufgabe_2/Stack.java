package aufgabe_2;


/**
 * ADS FS2019 
 * Praktikum 2
 * 
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */



public interface Stack<E> {

    // to add data  of the stack
    void push(E e);

   // return data from  of the stack
    E pop();

    // return data of tf the top of the stack
    
    E peek();

    // return when the stack is empty
    
    boolean isEmpty();

    // return when the stack is full
    
    boolean isFull();
}