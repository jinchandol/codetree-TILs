import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        visited = new boolean[N];

        recur(0, 0);
    }

    private static void recur(int idx, int cnt){
        if (cnt == M) {
            for (int i=0; i<N; i++){
                if (visited[i]) {
                    System.out.print(i+1 + " ");
                }
            }
            System.out.println();
            return;
        }
        
        if (idx == N) {
            return;
        }

        visited[idx] = true;
        recur(idx + 1, cnt + 1);
        visited[idx] = false;
        recur(idx + 1, cnt);
    }
}