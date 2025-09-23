import java.util.Scanner;

public class ShakerSort {

    static void swap(int[] a, int x, int y){
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    static void shakerSort(int[] a, int n){
        int left = 0;
        int right = n-1;
        int last = right;
        while (left < right) {
            for (int j = right; j > left; j--) {
                if (a[j-1]>a[j]) {
                    swap(a, j-1, j);
                    last = j;
                }
            }
            left = last;

            for (int j = left; j < right; j++) {
                if (a[j]>a[j+1]) {
                    swap(a, j, j+1);
                    last = j;
                }
            }
            right = last;

            System.out.println("left="+left+", right="+right);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("쉐이커 정렬");
        System.out.print("요솟수: ");
        int nx = sc.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            System.out.print("x["+i+"]: ");
            x[i] = sc.nextInt();
        }

        shakerSort(x, nx);
        
        System.out.println("\n\n정렬 결과");
        for (int i = 0; i < nx; i++) {
            System.out.print("x["+i+"]:"+x[i]+" ");
        }

    }
    
}