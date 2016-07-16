import java.util.LinkedList;
import java.util.Queue;

public class MyStack {

    private Queue<Integer> q1 = new LinkedList<Integer>();
    private Queue<Integer> q2 = new LinkedList<Integer>();

    public void push(int x) {
        while (!q1.isEmpty()){
            q2.add(q1.remove());
        }
        q1.add(x);
        while (!q2.isEmpty()){
            q1.add(q2.remove());
        }
    }


    public void pop() {
        q1.remove();
    }


    public int top() {
        return q1.peek();
    }


    public boolean empty() {
        return q1.isEmpty();
    }
}
