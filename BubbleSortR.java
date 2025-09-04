import java.util.Scanner;

public class BubbleSortR {

    static void swap(int[] a, int x, int y){
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    static void bubbleSort(int[] a, int n){
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1-i; j++) {
                if (a[j] > a[j+1]) {
                    swap(a, j, j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("버블정렬 v.R");
        System.out.print("요솟수: ");
        int nx = sc.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            System.out.print("x["+i+"]: ");
            x[i] = sc.nextInt();
        }

        bubbleSort(x, nx);

        System.out.println("오름차순 정렬 결과");
        for (int i = 0; i < nx; i++) {
            System.out.print("x["+i+"]:"+x[i]+" ");
        }
    }
}
