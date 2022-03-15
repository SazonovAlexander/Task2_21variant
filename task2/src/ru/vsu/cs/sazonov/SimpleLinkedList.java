package ru.vsu.cs.sazonov;

import java.util.Iterator;

public class SimpleLinkedList<T> implements Iterable<T> {


    public static class SimpleLinkedListException extends Exception {
        public SimpleLinkedListException(String message) {
            super(message);
        }
    }

    public class SimpleLinkedListNode {
        public T value;
        public SimpleLinkedListNode next;

        public SimpleLinkedListNode(T value, SimpleLinkedListNode next) {
            this.value = value;
            this.next = next;
        }

        public SimpleLinkedListNode(T value) {
            this(value, null);
        }
    }

    private SimpleLinkedListNode head = null;
    private SimpleLinkedListNode tail = null;
    private int size = 0;


    public void addFirst(T value) {
        head = new SimpleLinkedListNode(value, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }


    public void addLast(T value) {
        if (size == 0) {
            head = tail = new SimpleLinkedListNode(value);
        } else {
            tail.next = new SimpleLinkedListNode(value);
            tail = tail.next;
        }
        size++;
    }

    private void checkEmptyError() throws SimpleLinkedListException {
        if (size == 0) {
            throw new SimpleLinkedListException("Empty list");
        }
    }


    private SimpleLinkedListNode getNode(int index) {
        SimpleLinkedListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }


    public void removeFirst() throws SimpleLinkedListException {
        checkEmptyError();
        head = head.next;
        if (size == 1) {
            tail = null;
        }
        size--;
    }


    public void removeLast() throws SimpleLinkedListException {
        checkEmptyError();
        if (size == 1) {
            head = tail = null;
        } else {
            tail = getNode(size - 2);
            tail.next = null;
        }
        size--;
    }


    public void remove(int index) throws SimpleLinkedListException {
        checkEmptyError();
        if (index < 0 || index >= size) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        if (index == 0) {
            removeFirst();
        } else {
            SimpleLinkedListNode prev = getNode(index - 1);
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
            size--;
        }
    }


    public int size() {
        return size;
    }


    public T get(int index) throws SimpleLinkedListException {
        checkEmptyError();
        return getNode(index).value;
    }


    public T getFirst() throws SimpleLinkedListException {
        checkEmptyError();
        return head.value;
    }


    public T getLast() throws SimpleLinkedListException {
        checkEmptyError();
        return tail.value;
    }


    public void swap(){
        SimpleLinkedListNode max=head;
        SimpleLinkedListNode min=head;
        SimpleLinkedListNode prevMax=head;
        SimpleLinkedListNode prevMin=head;
        SimpleLinkedListNode nextMax;
        SimpleLinkedListNode nextMin;
        SimpleLinkedListNode curr=head;
        while(curr.next!=null){
            SimpleLinkedListNode currNext=curr.next;
            if((Integer) currNext.value>=(Integer) max.value){
                prevMax=curr;
                max=currNext;
            }
            if((Integer)currNext.value<(Integer) min.value){
                prevMin=curr;
                min=currNext;}
            curr=curr.next;
        }

        if (prevMax == max)
            prevMax = null;

        if (prevMin == min)
            prevMin = null;

        nextMax = max.next;
        nextMin = min.next;
        if (min == nextMax)
        {
            min.next = max;
            max.next = nextMin;
            if (max != head)
                prevMax.next = min;
            else head=min;
        }
        else
        if (max == nextMin)
        {
            max.next = min;
            min.next = nextMax;
            if (min != head)
                prevMin.next = max;
            else head=max;

        }
        else
        if(head!=max && head!=min){
            if (max != head)
                prevMax.next = min;
            min.next = nextMax;

            if (min != head)
                prevMin.next = max;
            max.next = nextMin;
        } else{
            if(max==head){
                head=min;
                min.next=nextMax;
                prevMin.next=max;
                max.next=nextMin;
                return;
            }
            head=max;
            max.next=nextMin;
            prevMax.next=min;
            min.next=nextMax;

        }
    }


    @Override
    public Iterator<T> iterator() {
        class SimpleLinkedListIterator implements Iterator<T> {
            SimpleLinkedListNode curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        }

        return new SimpleLinkedListIterator();
    }
}
