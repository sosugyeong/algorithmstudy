import java.util.Scanner;

public class ShellSort2 {
    static int shellSort2(int[] a, int n){
        int count = 0;
        int h;
        for(h=1; h<n; h=h*3+1){
            ;
        }

        for(; h>0; h/=3){
            for (int i = h; i < n; i++) {
                int j;
                int tmp = a[i];
                for(j = i-h; j>=0 && a[j]>tmp; j-=h){
                    a[j+h] = a[j];
                    count++;
                }
                a[j+h] = tmp;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("쉘 정렬(버전 2)");
        System.out.print("요솟수: ");
        int nx = sc.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            System.out.print("x["+i+"]: ");
            x[i] = sc.nextInt();
        }

        int count = shellSort2(x, nx);

        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++) {
            System.out.print( "x["+i+"]="+x[i]+"\n");
        }
        System.out.println("요소의 이동 회수는 " + count + "회입니다.");
    }
}
