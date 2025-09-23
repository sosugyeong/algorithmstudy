import java.util.*;

class DoubleIntStack {

    private int[] stack;
    private int capacity;
    private int ptrA;
    private int ptrB;

    public enum AorB {StackA, StackB};

    public class EmptyStackException extends RuntimeException{
        public EmptyStackException() {}
    }

    public class OverflowStackException extends RuntimeException{
        public OverflowStackException() {}
    }

    public DoubleIntStack(int maxlen){
        ptrA = 0;
        ptrB = maxlen-1;
        capacity = maxlen;
        try{
            stack = new int[capacity];
        } catch (OutOfMemoryError e){
            capacity = 0;
        }
    }

    public int push(AorB sw, int x) throws OverflowStackException{
        if(ptrA >= ptrB+1){
            throw new OverflowStackException();
        }
        switch (sw) {
            case StackA:
                stack[ptrA++] = x;
                break;
            
            case StackB:
                stack[ptrB--] = x;
                break;
        }
        return x;
    }

    public int pop(AorB sw) throws EmptyStackException{
        int x = 0;
        
        switch (sw) {
            case StackA:
                if(ptrA <= 0) throw new EmptyStackException();
                x = stack[--ptrA];
                break;
            
            case StackB:
                if(ptrA >= capacity-1) throw new EmptyStackException();
                x = stack[++ptrB];
                break;
        }
        return x;
    }

    public int peek(AorB sw) throws EmptyStackException{
        int x = 0;
        
        switch (sw) {
            case StackA:
                if(ptrA <= 0) throw new EmptyStackException();
                x = stack[ptrA-1];
                break;
            
            case StackB:
                if(ptrA >= capacity-1) throw new EmptyStackException();
                x = stack[ptrB+1];
                break;
        }
        return x;
    }

    public void clear(AorB sw){
        switch(sw){
            case StackA: ptrA = 0; break;
            case StackB: ptrB = capacity-1; break;
        }
    }

    public int indexOf(AorB sw, int x){
        switch(sw){
            case StackA: 
                for (int i = ptrA - 1; i >= 0; i--){
                    if(stack[i] == x)
                        return i;
                }
                break;

            case StackB: 
                for (int i = ptrB + 1; i < capacity; i++){
                    if(stack[i] == x)
                        return i;
                }
                break;
        }

        return -1;
    }

    public int getCapacity(){
        return capacity;
    }

    public int size(AorB sw){
        switch(sw){
            case StackA: return ptrA;
            case StackB: return capacity-ptrB-1;
        }
        return 0;
    }

    public boolean isEmpty(AorB sw){
        switch(sw){
            case StackA: return ptrA <= 0;
            case StackB: return ptrB >= capacity - 1;
        }
        return true;
    }

    public boolean isFull(){
        return ptrA >= ptrB+1;
    }

    public void dump(AorB sw){
        switch(sw){
            case StackA: 
                if(ptrA <= 0) System.out.println("스택이 비어있습니다.");
                else{
                    for (int i = 0; i < ptrA; i++) {
                        System.out.print(stack[i]+"  ");
                    }
                    System.out.println();
                }
                break;
            case StackB: 
                if(ptrB >= capacity-1) System.out.println("스택이 비어있습니다.");
                else{
                    for (int i = capacity-1; i > ptrA; i--) {
                        System.out.print(stack[i]+"  ");
                    }
                    System.out.println();
                }
                break;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //Random rand = new Random();
        DoubleIntStack s = new DoubleIntStack(64);

        while (true) {
            System.out.println("현재 데이터 개수");
            System.out.println("A: "+s.size(AorB.StackA)+" / B: "+s.size(AorB.StackB));

            System.out.println("A:  1)푸시 2)팝 3)피크 4)덤프 5)검색 6)비움");
            System.out.println("B:  7)푸시 8)팝 9)피크 10)덤프 11)검색 12)비움");
            System.out.println("13)출력  0)종료");
            System.out.print("선택: ");

            int menu = sc.nextInt();
            if (menu == 0) break;

            int x;
            int num;
            switch (menu) {
                case 1:
                    System.out.print("데이터: ");
                    x = sc.nextInt();

                    try{
                        s.push(AorB.StackA, x);
                    } catch(OverflowStackException e){
                        System.out.println("스택이 가득 찼습니다.");
                    }
                    break;

                case 2:
                    try{
                        x = s.pop(AorB.StackA);
                        System.out.println("팝한 데이터는 "+x+"입니다.");
                    } catch(EmptyStackException e){
                        System.out.println("스택이 비어 있습니다.");
                    }
                    break;

                case 3:
                    try{
                        x = s.peek(AorB.StackA);
                        System.out.println("피크한 데이터는 "+x+"입니다.");
                    } catch(EmptyStackException e){
                        System.out.println("스택이 비어 있습니다.");
                    }
                    break;

                case 4:
                    s.dump(AorB.StackA);
                    break;

                case 5: //clear
                    System.out.print("검색할 데이터: ");
                    num = sc.nextInt();
                    System.out.println(s.indexOf(AorB.StackA, num));
                    break;
            
                case 6: //indexOf
                    s.clear(AorB.StackA);
                    System.out.println("스택이 비워졌습니다.");
                    break;
                
                case 7:
                    System.out.print("데이터: ");
                    x = sc.nextInt();

                    try{
                        s.push(AorB.StackB, x);
                    } catch(OverflowStackException e){
                        System.out.println("스택이 가득 찼습니다.");
                    }
                    break;

                case 8:
                    try{
                        x = s.pop(AorB.StackB);
                        System.out.println("팝한 데이터는 "+x+"입니다.");
                    } catch(EmptyStackException e){
                        System.out.println("스택이 비어 있습니다.");
                    }
                    break;

                case 9:
                    try{
                        x = s.peek(AorB.StackA);
                        System.out.println("피크한 데이터는 "+x+"입니다.");
                    } catch(EmptyStackException e){
                        System.out.println("스택이 비어 있습니다.");
                    }
                    break;

                case 10:
                    s.dump(AorB.StackB);
                    break;

                case 11:
                    System.out.print("검색할 데이터: ");
                    num = sc.nextInt();
                    System.out.println(s.indexOf(AorB.StackB, num));
                    break;
            
                case 12: //indexOf
                    s.clear(AorB.StackB);
                    System.out.println("스택이 비워졌습니다.");
                    break;
                

                case 13: 
                    System.out.println("용량 : " + s.getCapacity());
                    System.out.println("데이터수 -> A: " + s.size(AorB.StackA)+" / B: "+s.size(AorB.StackB));
				    System.out.println("A는 비어 " + (s.isEmpty(AorB.StackA) ? "있습니다." : "있지 않습니다."));
				    System.out.println("B는 비어 " + (s.isEmpty(AorB.StackB) ? "있습니다." : "있지 않습니다."));
				    System.out.println("가득 차 " + (s.isFull() ? "있습니다." : "있지 않습니다."));


                default:
                    break;
            }
        }
    }
}