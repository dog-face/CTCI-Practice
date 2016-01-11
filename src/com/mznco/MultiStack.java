package com.mznco;

/**
 * Created by Misery on 1/11/16.
 */
public class MultiStack {
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

    Stack stack1;
    Stack stack2;
    Stack stack3;
    Stack stack4;
    int maxSize;
    int currentStackNum;
    int currentStackSize;

    Stack currentStack;

    public MultiStack(){
        stack1 = new Stack();
        stack2 = new Stack();
        stack3 = new Stack();
        stack4 = new Stack();

        maxSize = 3;
        currentStackNum = 1;
        currentStackSize = 0;

        currentStack = stack1;
    }

    public void push(int newData){
        if(currentStackSize < maxSize){//still room on this stack
            currentStack.push(newData);
            currentStackSize++;
        }
        else{//need to move to the next stack
            currentStackNum++;
            updateCurrentStack();
            currentStackSize = 0;
            currentStack.push(newData);
            currentStackSize++;
        }
    }

    public void updateCurrentStack(){
        if(currentStackNum == 1)
            currentStack = stack1;
        if(currentStackNum == 2)
            currentStack = stack2;
        if(currentStackNum == 3)
            currentStack = stack3;
        if(currentStackNum == 4)
            currentStack = stack4;
    }

    public int pop() {
        if(currentStack.isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        else{
            int retval = currentStack.pop();
            currentStackSize--;
            if(currentStackSize == 0){//we've emptied this stack
                currentStackNum--;
                currentStackSize = maxSize;
                updateCurrentStack();
            }
            return retval;
        }
    }
}
