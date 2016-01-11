package com.mznco;

/**
 * Created by Misery on 1/11/16.
 */
public class MinStack {
    public class Stack{
        public class StackNode{
            protected StackNode next;
            protected int data;

            public StackNode(int newData){
                data = newData;
                next = null;
            }

        }

        StackNode top;

        public Stack(){
            top = null;
        }

        public boolean isEmpty(){
            if(top == null){
                return true;
            }
            return false;
        }

        public void push(int newData) {
            StackNode newNode = new StackNode(newData);
            if (top == null) {
                top = newNode;
            } else {
                newNode.next = top;
                top = newNode;
            }
        }
        public int pop() {
            if (top == null) {
                throw new IndexOutOfBoundsException();
            }
            else {
                int retval = top.data;
                top = top.next;
                return retval;
            }
        }

    }

    Stack dataStack;
    Stack minStack;
    int min;

    public MinStack(){
        dataStack = new Stack();
        minStack = new Stack();
    }

    public void push(int newData){
        if(dataStack.isEmpty()){
            min = newData;
            dataStack.push(newData);
            //minStack.push(newData);
        }
        else{
            if(newData <= min){
                minStack.push(min);
                min = newData;
                dataStack.push(newData);
            }
            else{
                dataStack.push(newData);
            }
        }
    }

    public int pop(){
        if(dataStack.isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        else{
            int retval = dataStack.pop();
            if(retval == min){//if it was the minimum item
                min = minStack.pop();
            }
            return retval;
        }
    }

    public int min(){
        return min;
    }
}

