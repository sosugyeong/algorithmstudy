import java.util.*;
//not 재귀호출

public class Factorial2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("수 입력: ");
        int x = sc.nextInt();
        int result = 1;

        for (int i = 1; i <= x; i++) {
            result = result*i;
        }

        System.out.println(x+"의 팩토리얼은 "+result+"입니다.");
    }
}
