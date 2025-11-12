import java.util.Scanner;

public class InsetionSortCen {

    static void insetionSortCen(int[] a, int n){
        for (int i = 2; i < n; i++) {
            int tmp = a[0] = a[i];
            int j = i;
            for(; a[j-1]>tmp; j--){
                a[j] = a[j-1];
            }
            if(j>0) a[j] = tmp;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("연습문제 Q8. 단순 삽입 정렬");
        System.out.print("요솟수: ");
        int nx = sc.nextInt();
        int[] x = new int[nx+1];

        for (int i = 1; i <= nx; i++) {
            System.out.print("x["+i+"]: ");
            x[i] = sc.nextInt();
        }

        insetionSortCen(x, nx+1);

        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 1; i <= nx; i++) {
            System.out.print( "x["+i+"]="+x[i]+"  ");
        }
    }
}
