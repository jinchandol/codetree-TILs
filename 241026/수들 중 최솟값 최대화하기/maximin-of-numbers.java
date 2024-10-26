import java.util.*;
import java.io.*;

public class Main {
    public static int n, ans;
    public static ArrayList<Integer> nums = new ArrayList<>();
    public static int[][] map;
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];
        visited = new boolean[n];
        ans = 0;

        for (int r=0; r<n; r++) {
            for (int c=0; c<n; c++) {
                map[r][c] = sc.nextInt();
            }
        }

        search(0);

        System.out.println(ans);
    }

    public static int calc() {
        int tmp = Integer.MAX_VALUE;
        for (int idx = 0; idx < n; idx++) {
            tmp = Math.min(tmp, map[idx][nums.get(idx)]);
        }
        return tmp;
    }

    public static void search(int row) {
        if (row == n) {
            ans = Math.max(ans, calc());
            return;
        }

        for (int i=0; i<n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            nums.add(i);

            search(row + 1);

            nums.remove(nums.size() - 1);
            visited[i] = false;
        }
    }
}