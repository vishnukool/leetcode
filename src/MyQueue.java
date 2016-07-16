import java.util.Stack;

public class MyQueue {
    // Push element x to the back of queue.
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public void push(int x) {
        while (!s1.isEmpty()){
            s2.add(s1.pop());
        }
        s1.add(x);
        while (!s2.isEmpty()){
            s1.add(s2.pop());
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        s1.pop();
    }

    // Get the front element.
    public int peek() {
        return s1.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s1.isEmpty();
    }
}

