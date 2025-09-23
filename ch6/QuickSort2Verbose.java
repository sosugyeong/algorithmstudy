import java.util.Scanner;

public class QuickSort2Verbose {

    static void swap(int[] a, int idx1, int idx2){
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }

    static void quickSort(int[] a, int left, int right){
        //스택 생성
        IntStack lstack = new IntStack(right-left+1);
        IntStack rstack = new IntStack(right-left+1);

        lstack.push(left);
        rstack.push(right);
        System.out.printf("a[%d] ~ a[%d]을 나누는 문제를 스택에 push.\n", left, right);
        System.out.print("lstack:"); lstack.dump();
        System.out.print(" rstack:"); rstack.dump();

        while (lstack.isEmpty()!=true) {
            int pl = left = lstack.pop();
            int pr = right = rstack.pop();
            int x = a[(left+right)/2];
            System.out.printf("스택에서 나누는 문제를 꺼냄. a[%d]~a[%d]을 나눔.\n", left, right);

            do{
                while (a[pl]<x) pl++;
                while (a[pr]>x) pr--;
                if(pl<=pr){
                    swap(a, pl++, pr--);
                }
            } while(pl <= pr);

            if(left < pr){
                lstack.push(left);
                rstack.push(pr);
                System.out.printf("스택에서 나누는 문제를 꺼냄. a[%d]~a[%d]을 나눔.\n", left, pr);
                System.out.print("lstack:"); lstack.dump();
                System.out.print(" rstack:"); rstack.dump();
            }

            if(pl < right){
                lstack.push(pl);
                rstack.push(right);
                System.out.printf("스택에서 나누는 문제를 꺼냄. a[%d]~a[%d]을 나눔.\n", pl, right);
                System.out.print("lstack:"); lstack.dump();
                System.out.print(" rstack:"); rstack.dump();
            }
        }
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("퀵 정렬");
        System.out.print("요솟수: ");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]: ");
            x[i] = stdIn.nextInt();
        }

        quickSort(x, 0, nx - 1); 

        System.out.println("오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++)
            System.out.println("x[" + i + "]=" + x[i]);
    }
    
}
