package ch.zhaw.ads;

import java.util.ArrayList;

/**
 * ADS FS2019
 * Praktikum 1
 * Aufgabe 2 - Stack
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class ListStack implements Stack {
    ArrayList<Object> stack = new ArrayList<Object>();

    public Object pop() {
        if (stack.isEmpty()) {
            return null;
        } else {
            Object object = stack.get(0);
            stack.remove(0);
            return object;
        }
    }

    public void push(Object object) {
        stack.add(0, object);
    }

    public Object peek() {
        if (stack.isEmpty()) {
            return null;
        } else {
            return stack.get(0);
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public boolean isFull() {
        return false;
    }
}