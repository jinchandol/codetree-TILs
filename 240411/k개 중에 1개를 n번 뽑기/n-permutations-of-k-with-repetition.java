import java.io.*;
import java.util.*;

public class Main {
    static int K, N;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        K = sc.nextInt();
        N = sc.nextInt();
        arr = new int[N];
        
        recur(0);
    }

    private static void recur(int idx){
        if (idx == N) {
            for (int i=0; i<N; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i=1; i<=K; i++){
            arr[idx] = i;
            recur(idx + 1);
        }
    }
}