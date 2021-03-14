package testcrab;

import org.w3c.dom.Node;

import java.util.LinkedList;
public class MyQueue <E> {
    private Object[] elements;

    private java.util.LinkedList<E> list = new java.util.LinkedList<>();
    public MyQueue(E[] e){
        this.elements = e;
    }

    public MyQueue(){
        this.elements = null;
    }

    public void enqueue(E e){
        list.addLast(e);
    }
    public E dequeue(){
        return list.removeFirst();
    }

    public E getElement(int i){
        return list.get(i);
    }

    public E peek(){
        return list.peek();
    }

    public int getSize(){
        return list.size();
    }

    public boolean contains(E e){
        return list.contains(e);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public String toString(){
        return list.toString();
    }

}

