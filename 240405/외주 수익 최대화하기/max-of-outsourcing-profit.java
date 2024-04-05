import java.util.*;
import java.io.*;

public class Main {
    static int n, ans;
    static int[][] task;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        task = new int[n+1][3];
        ans = 0;
        for (int i=1; i<=n; i++){
            task[i][0] = i; // 일의 시점
            int end = sc.nextInt();
            task[i][1] = end + i - 1; // 일의 종점
            task[i][2] = sc.nextInt(); // 일의 수익
        }

        // recur(idx, profit);
        recur(1, 0, 0);

        System.out.println(ans);
    }

    private static void recur(int idx, int end, int profit){
        if (idx == n+1){
            ans = Math.max(ans, profit);
            return;
        }

        if (end < task[idx][0]){
            if (task[idx][1] <= n) {
                recur(idx + 1, task[idx][1], profit + task[idx][2]);
                recur(idx + 1, end, profit);
            } else {
                recur(idx + 1, end, profit);
            } 
        } else {
            recur(idx + 1, end, profit);
        }
    }
}