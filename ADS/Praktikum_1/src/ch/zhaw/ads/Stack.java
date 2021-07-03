package ch.zhaw.ads;

/**
 * ADS FS2019
 * Praktikum 1
 * Aufgabe 2 - Stack
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public interface Stack {

	void push(Object object);
	
	Object pop();
	
	Object peek();
	
	boolean isEmpty();
	
	boolean isFull();
}
