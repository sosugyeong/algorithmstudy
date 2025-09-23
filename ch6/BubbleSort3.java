import java.util.Scanner;

public class BubbleSort3 {
    static void swap(int[] a, int x, int y){
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    static void bubbleSort(int[] a, int n){
        int k = 0;
        while (k<n-1) {
            int last = n-1;
            for (int i = n-1; i > k; i--) {
                if (a[i-1]>a[i]) {
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
    }
}
