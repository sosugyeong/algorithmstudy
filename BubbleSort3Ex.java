import java.util.Scanner;

public class BubbleSort3Ex {
    static int comp;
    static int exchange;

    static void swap(int[] a, int x, int y){
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    static void bubbleSort(int[] a, int n){
        int k = 0;
        int i = 0;
        while (k<n-1) {
            System.out.printf("패스%d:\n", ++i);
            int last = n-1;
            for (i = n-1; i > k; i--) {

                for(int m = 0; m<n-1; m++){
                    System.out.printf("%2d %c", a[m], (m!=i-1)?' ':(a[i-1]>a[i])?'+':'-');
                }
                System.out.printf("%2d\n", a[n-1]);

                comp++;
                if (a[i-1]>a[i]) {
                    exchange++;
                    swap(a, i-1, i);
                    last = i;
                }
            }
            k = last;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("버블정렬 v.3");
        System.out.print("요솟수: ");
        int nx = sc.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            System.out.print("x["+i+"]: ");
            x[i] = sc.nextInt();
        }

        bubbleSort(x, nx);

        System.out.println("\n오름차순 정렬 결과");
        for (int i = 0; i < nx; i++) {
            System.out.print("x["+i+"]:"+x[i]+" ");
        }

        System.out.println("\n\n비교를 "+comp+"회 했습니다.");
        System.out.println("교환을 "+exchange+"회 했습니다.");
    }
}