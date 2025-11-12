import java.util.*;
//not 재귀호출

public class Gcd {

    static int gcd(int x, int y){
        int result = 0;
        for (int i = 1; i < x+y; i++) {
            if (x%i==0 && y%i==0) {
                result = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("두 정수의 최대공약수를 구합니다.");
        System.out.print("첫번째 수 입력: ");
        int x = sc.nextInt();
        System.out.print("두번째 수 입력: ");
        int y = sc.nextInt();

        System.out.println("두 수의 최대공약수는 "+gcd(x, y)+"입니다.");
    }
}
