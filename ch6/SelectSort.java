import java.util.Scanner;

public class SelectSort {

    static void swap(int[] a, int x, int y){
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    static void selectSort(int[] a, int n){
        for (int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (a[j]<a[min]) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("선택정렬");
        System.out.print("요솟수: ");
        int nx = sc.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            System.out.print("x["+i+"]: ");
            x[i] = sc.nextInt();
        }

        selectSort(x, nx);

        System.out.println("오름차순 정렬 결과");
        for (int i = 0; i < nx; i++) {
            System.out.print("x["+i+"]:"+x[i]+" ");
            
        }
    }
}
