import java.util.Scanner;

public class BubbleSortEx {

    static int comp; // 비교 횟수
    static int exchange; // 교환 횟수

    static void swap(int[] a, int x, int y){
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    static void bubbleSort(int[] a, int n){
        for (int i = 0; i < n-1; i++) {
            System.out.printf("패스: %d\n",i+1);
            for (int j = n-1; j > i; j--) {

                for(int m = 0; m<n-1; m++){
                    System.out.printf("%2d %c", a[m], (m!=j-1)? ' ':(a[j-1]>a[j]) ? '+':'-');
                }
                System.out.printf("%2d\n", a[n-1]);

                comp++;
                if (a[j-1]>a[j]) {
                    exchange++;
                    swap(a, j-1, j);
                }
            }
            for(int m = 0; m<n; m++){
                System.out.printf("%3d ", a[m]);
            }
            System.out.println();
        }
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("버블정렬 (버전: 자세한!)");
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