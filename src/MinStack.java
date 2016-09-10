import java.util.PriorityQueue;
import java.util.Stack;

public class MinStack {

    Stack<Integer> stack;
    PriorityQueue<Integer> heap;
    public MinStack() {
        stack = new Stack<>();
        heap = new PriorityQueue<>();
    }

    public void push(int x) {
        stack.push(x);
        heap.add(x);
    }

    public void pop() {
        Integer poppedInteger = stack.pop();
        heap.remove(poppedInteger);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return heap.peek();
    }

}
