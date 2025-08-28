import java.util.*;
// 

public class Queue<E> {
    private E[] que;
    private int capacity;
    private int num;
    private int front;
    private int rear;

    //예외: 큐가 비어있음
    public static class EmptyIntGQueueException extends RuntimeException{
        public EmptyIntGQueueException() {}
    }

    //예외: 큐가 가득 참
    public static class OverflowIntGQueueException extends RuntimeException {
        public OverflowIntGQueueException() {}
    }

    //생성자
    public Queue(int maxlen){
        num = front = rear = 0;
        capacity = maxlen;
        try {
            que = (E[])new Object[capacity];
        } catch (OutOfMemoryError e) {
            capacity = 0;
        }
    }

    public E enque(E x) throws OverflowIntGQueueException{
        if(num >= capacity){
            throw new OverflowIntGQueueException();
        }
        que[rear++] = x;
        num++;
        if(rear == capacity){
            rear = 0;
        }
        return x;
    }

    public E deque() throws EmptyIntGQueueException{
        if (num <= 0) {
            throw new EmptyIntGQueueException();
        }
        E x = que[front++];
        num--;
        if (front == capacity) {
            front = 0;
        }
        return x;
    }

    public E peek() throws EmptyIntGQueueException{
        if (num <= 0) { 
            throw new EmptyIntGQueueException();
        }
        return que[front];
    }

    public void clear(){
        front = rear = num = 0;
    }

    public int indexOf(E x){
        for (int i = 0; i < num; i++) {
            if (x == que[(i+front)%capacity]) {
                return i;
            }
        }
        return -1;
    }

    public int size(){
        return num;
    }

    public boolean isEmpty(){
        return num <= 0;
    }

    public boolean isFull(){
        return num >= capacity;
    }

    public int getCapacity(){
        return capacity;
    }

    public void dump(){
        if (num <= 0) {
            System.out.println("큐가 비어있습니다.");
        } else {
            for (int i = 0; i < num; i++) {
                System.out.print(que[(i+front)%capacity]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<String> s = new Queue<String>(20);

        while(true){
            System.out.println();
            System.out.printf("현재 데이터 개수: %d / %d \n", s.size(), s.getCapacity());
            System.out.print("(1)인큐  (2)디큐  (3)피크  (4)덤프  (0)종료: ");

            int menu = sc.nextInt();
            if (menu == 0) break;

            String x;

            switch (menu) {
                case 1:
                    System.out.print("데이터: ");
                    x = sc.next();
                    try {
                        s.enque(x);
                    } catch (OverflowIntGQueueException e) {
                        System.out.println("큐가 가득 찼습니다.");
                    }
                    break;

                case 2:
                    try {
                        x = s.deque();
                        System.out.println("디큐한 데이터는 "+x+"입니다.");
                    } catch (EmptyIntGQueueException e) {
                        System.out.println("큐가 비어있습니다.");
                    }
                    break;

                case 3:
                    try {
                        x = s.peek();
                        System.out.println("디큐한 데이터는 "+x+"입니다.");
                    } catch (EmptyIntGQueueException e) {
                        System.out.println("큐가 비어있습니다.");
                    }
                    break;

                case 4:
                    s.dump();
                    break;
            
            }
        }
    }
}
