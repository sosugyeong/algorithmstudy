import java.util.Scanner;

public class CountingSortEx {
    static void countingSort(int[] a, int n, int min, int max){
        int[] f = new int[max-min+2];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) f[a[i]-min]++;
        for (int i = 1; i <= max-min+1; i++) f[i] += f[i-1];
        for (int i = n-1; i >= 0; i--) b[--f[a[i]-min]] = a[i];
        for (int i = 0; i < n; i++) a[i] = b[i];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("도수 정렬");
        System.out.print("요솟수: ");
        int nx = sc.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            do{
                System.out.print("x["+i+"]: ");
                x[i] = sc.nextInt();
            } while(x[i]<0);
        }

        int max = x[0];
        for (int i = 1; i < nx; i++) {
            if(x[i]>max) max = x[i];
        }
        int min = x[0];
        for (int i = 1; i < nx; i++) {
            if(x[i]<min) min = x[i];
        }

        countingSort(x, nx, min, max);  

        System.out.println("오름차순 도수 정렬 결과");
        for (int i = 0; i < nx; i++) {
            System.out.println("x["+i+"]="+x[i]);
        }
    }
    
}
