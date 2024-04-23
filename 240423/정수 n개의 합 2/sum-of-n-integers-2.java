import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n+1];
        int[] prefix = new int[n+1];

        prefix[0] = 0;

        for (int i=1; i<arr.length; i++){
            arr[i] = sc.nextInt();
            prefix[i] = prefix[i-1] + arr[i];
        }

        int maxi = Integer.MIN_VALUE;

        for (int i=k; i<arr.length; i++){
            maxi = Math.max(maxi, (prefix[i]-prefix[i-k]));
        }

        System.out.println(maxi);


    }
}