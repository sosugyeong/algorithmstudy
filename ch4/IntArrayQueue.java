import java.util.*;

public class IntArrayQueue {
    private int[] que;
    private int capacity;
    private int num;

    public class EmptyIntQueueException extends RuntimeException{
        public EmptyIntQueueException(){}
    }

    public class OverflowIntQueueException extends RuntimeException{
        public OverflowIntQueueException(){}
    }

    public IntArrayQueue(int maxlen){ //생성자
        num = 0;
        capacity = maxlen;
        try{
            que = new int[capacity];
        } catch (OutOfMemoryError e){
            capacity = 0;
        }
    }

    public int enque(int x) throws OverflowIntQueueException{
        if(num >= capacity){
            throw new OverflowIntQueueException();
        }
        que[num++] = x;

        return x;
    }

    public int deque() throws EmptyStackException{
        int x = 0;

        if (num <= 0) {
            throw new EmptyIntQueueException();
        }
        x = que[0];

        for (int i = 0; i < que.length-1; i++) {
            que[i] = que[i+1];
            que[i+1] = 0;
        }

        return x;
    }

    public int peek() throws EmptyStackException{
        int x = 0;

        if (num<=0) {
            throw new EmptyIntQueueException();
        }
        x = que[num-1];
        return x;
    }

    public int indexOf(int x) throws EmptyStackException{
        for (int i = num-1; i>=0; i--) {
            if (x == que[i]) {
                return i;
            }
        }
        return -1;
    }

    public void clear(){
        num = 0;
    }

    public int capacity(){
        return capacity;
    }

    public int size(){
        return num;
    }

    public boolean isEmpty(){
        return num == 0;
    }

    public boolean isFull(){
        return num >= capacity;
    }

    public void dump(){
        if (num <= 0) {
            System.out.println("스택이 비어있습니다.");
        }

        for (int i = 0; i < que.length; i++) {
            System.out.print(que[i] +" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IntArrayQueue s = new IntArrayQueue(10);

        while(true){

            System.out.println("1)인큐 2)디큐 3)피크 4)덤프 5)검색 6)비움 0)끝내기");
            System.out.print("선택: ");

            int menu = sc.nextInt();
            if (menu == 0) {
                break;
            }
            int x;
            switch (menu) {
                case 1:
                    System.out.print("수 입력: ");
                    x = sc.nextInt();

                    try {
                        s.enque(x);
                    } catch (OutOfMemoryError e) {
                        System.out.println("큐가 꽉 찼습니다.");
                    }
                    break;
                
                case 2:
                    try {
                        x = s.deque();
                        System.out.println("팝한 큐는 "+x+"입니다.");
                    } catch (EmptyStackException e) {
                        System.out.println("큐가 텅 비었습니다.");
                    }
                    break;

                case 3:
                    try {
                        x = s.peek();
                        System.out.println("피크한 데이터는 "+x+"입니다.");
                    } catch (OutOfMemoryError e) {
                        System.out.println("큐가 꽉 찼습니다.");
                    }
                    break;

                case 4:
                    s.dump();
                    break;

                case 5:
                    System.out.print("검색할 수 입력: ");
                    int z = sc.nextInt();
                    x = s.indexOf(z);
                    System.out.println(x+"에 있습니다.");
                    break;

                case 6:
                    s.clear();
                    break;

                case 7:
                    break;
            
                default:
                    break;
            }
        }

    }
}
