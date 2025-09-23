import java.util.*;

public class Hanoi {

    static String[] abc = {"A기둥", "B기둥", "C기둥"};

    static void move(int no, int x, int y){
        if (no>1) {
            move(no-1, x, 6-x-y);
        }

        System.out.printf("원반[%d]을(를) %s에서 %s로 옮김\n", no, abc[x-1], abc[y-1]);

        if (no>1) {
            move(no-1, 6-x-y, y);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("하노이의 탑");
        System.out.print("원반의 개수: ");
        int n = sc.nextInt();

        move(n, 1, 3);
    }
}
