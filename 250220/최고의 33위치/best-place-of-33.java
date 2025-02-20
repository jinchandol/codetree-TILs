import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int answer = 0;
        map = new int[N][N];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                map[row][col] = sc.nextInt();
            }
        }

        for (int row = 1; row < N-1; row++) {
            for (int col = 1; col < N-1; col++) {
                answer = Math.max(search(row, col), answer);
            }
        }

        System.out.println(answer);
    }

    public static int search(int row, int col) {
        int count = 0;

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (map[row + dr][col + dc] == 1) count++;
            }
        }

        return count;
    }
}