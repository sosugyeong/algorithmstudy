import java.util.*;

public class IntDeque {
    private int[] que;
    private int capacity;
    private int rear;
    private int front;
    private int num;

    //예외: 큐가 비어있음
    public class EmptyIntDequeException extends RuntimeException{
        public EmptyIntDequeException() {}
    }

    //예외: 큐가 가득 참
    public class OverflowIntDequeException extends RuntimeException {
        public OverflowIntDequeException() {}
    }

    //생성자
    public IntDeque(int maxlen){
        num = front = rear = 0;
        capacity = maxlen;
        try {
            que = new int[capacity];
        } catch (OutOfMemoryError e) {
            capacity = 0;
        }
    }

    public int enqueFront(int x) throws OverflowIntDequeException{//덱의 맨 앞에서 데이터를 인큐
        if (num >= capacity) {
            throw new OverflowIntDequeException();
        }
        num++;
        if(--front < 0){
            front = capacity-1;
        }
        que[front] = x;
        return x;
    }
    
    public int enqueRear(int x) throws OverflowIntDequeException{//덱의 맨 끝에서 데이터를 인큐
        if (num >= capacity) {
            throw new OverflowIntDequeException();
        }
        que[rear++] = x;
        num++;
        if (rear == capacity) {
            rear = 0;
        }
        return x;
    }

    public int dequeFront() throws EmptyIntDequeException{//덱의 맨 앞 데이터를 디큐
        if(num <= 0){
            throw new EmptyIntDequeException();
        }
        int x = que[front++];
        num--;
        if(front == capacity){
            front = 0;
        }
        return x;
    }
    
    public int dequeRear() throws EmptyIntDequeException{//덱의 맨 끝 데이터를 디큐
        if(num <= 0){
            throw new EmptyIntDequeException();
        }
        num--;
        if(--rear<0){
            rear = capacity - 1;
        }
        return que[rear];
    }

    public int peekFront() throws EmptyIntDequeException{ //덱의 맨 앞 데이터를 피크
        if (num <= 0) {
            throw new EmptyIntDequeException();
        }

        return que[front];
    }
    public int peekRear() throws EmptyIntDequeException{ //덱의 맨 뒤 데이터를 피크
        if(num <= 0){
            throw new EmptyIntDequeException();
        }
        return que[rear == 0 ? capacity-1 : rear-1];
    }

    public int indexOf(int x){ //덱에서 x를 검색하여 인덱스 반환
        for (int i = 0; i < num; i++) {
            if (x == que[(i+front)%capacity]) {
                return i;
            }
        }
        return -1;
    }

    public int search(int x){ //덱에서 x를 검색하여 맨앞에서 몇번째인지 반환
        for (int i = 0; i < num; i++) {
            if (x == que[(i+front)%capacity]) {
                return i+1;
            }
        }
        return 0;
    }

    public void clear(){
        num = front = rear = 0;
    }

    public int getCapacity(){
        return capacity;
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

    public void dump(){ //덱의 모든 데이터를 앞->끝 순서로 출력
        if (num <= 0) {
            System.out.println("덱이 비어 있습니다");
        } else {
            for (int i = 0; i < num; i++) {
                    System.out.print(que[(i+front)%capacity]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IntDeque s = new IntDeque(30);

        while(true){
            System.out.println();
            System.out.printf("현재 데이터 개수: %d / %d \n", s.size(), s.getCapacity());
            System.out.print("(1)앞에서 인큐 (2)앞에서 디큐 (3)앞에서 피크 (4)뒤에서 인큐 (5)뒤에서 디큐 (6)뒤에서 피크");
            System.out.println(" (7)덤프 (8)검색 (0)종료:");

            int menu = sc.nextInt();
            if (menu == 0) break;

            int x = 0;

            switch (menu) {
                case 1:
                    System.out.print("데이터: ");
                    x = sc.nextInt();
                    try {
                        s.enqueFront(x);
                    } catch (OverflowIntDequeException e) {
                        System.out.println("큐가 가득 찼습니다.");
                    }
                    break;
            
                case 2:
                    try {
                        s.dequeFront();
                        System.out.println("디큐한 데이터는 "+x+"입니다.");
                    } catch (EmptyIntDequeException e) {
                        System.out.println("큐가 비어있습니다.");
                    }
                    break;

                case 3:
                    try {
                        s.peekFront();
                        System.out.println("피크한 데이터는 "+x+"입니다.");
                    } catch (EmptyIntDequeException e) {
                        System.out.println("큐가 비어있습니다.");
                    }
                    break;
                    
                case 4:
                    System.out.print("데이터: ");
                    x = sc.nextInt();
                    try {
                        s.enqueRear(x);
                    } catch (OverflowIntDequeException e) {
                        System.out.println("큐가 가득 찼습니다.");
                    }
                    break;
            
                case 5:
                    try {
                        s.dequeRear();
                        System.out.println("디큐한 데이터는 "+x+"입니다.");
                    } catch (EmptyIntDequeException e) {
                        System.out.println("큐가 비어있습니다.");
                    }
                    break;

                case 6:
                    try {
                        s.peekRear();
                        System.out.println("피크한 데이터는 "+x+"입니다.");
                    } catch (EmptyIntDequeException e) {
                        System.out.println("큐가 비어있습니다.");
                    }
                    break;

                case 7:
                    s.dump();
                    break;
                
                case 8:
                    System.out.println("데이터: ");
                    x = sc.nextInt();
                    int n = s.search(x);
                    if(n != 0){
                        System.out.printf("%d번째 데이터로 인덱스 %d의 위치에 저장되어 있습니다.\n", n, s.indexOf(x));
                    } else {
                        System.out.println("그 데이터는 등록되어 있지 않습니다.");
                    }
            }
        }
    }
}
