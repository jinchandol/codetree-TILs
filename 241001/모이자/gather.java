import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int minValue = Integer.MAX_VALUE;
        int[] arr = new int[N];

        for (int i=0; i<N; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i=0; i<N; i++) {
            int tmp = 0;
            for (int j=0; j<N; j++) {
                tmp += arr[j]*Math.abs(i-j);
            }
            minValue = Math.min(minValue, tmp);
        }

        System.out.println(minValue);
    }
}