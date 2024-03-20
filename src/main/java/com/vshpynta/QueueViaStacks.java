package com.vshpynta;

import java.util.Deque;
import java.util.LinkedList;

//https://leetcode.com/problems/implement-queue-using-stacks/
public class QueueViaStacks {

    private Deque<Integer> inputStack = new LinkedList<>();
    private Deque<Integer> outputStack = new LinkedList<>();

    public QueueViaStacks() {

    }

    public void push(int x) {
        inputStack.push(x);
    }

    public int pop() {
        fillOutputStackIfEmpty();
        return outputStack.pop();
    }

    public int peek() {
        fillOutputStackIfEmpty();
        return outputStack.peek();
    }

    public boolean empty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

    private void fillOutputStackIfEmpty() {
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }
    }
}
