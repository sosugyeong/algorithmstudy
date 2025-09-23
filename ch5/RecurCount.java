import java.util.*;

public class RecurCount {

    static int count;

    static void recur(int n){
        count++;
        if (n>0) {
            recur(n-1);
            System.out.println(n);
            recur(n-2);;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("정수 입력: ");
        int x = sc.nextInt();
        recur(x);

        System.out.println("메서드 호출 횟수: "+count);
    }
}