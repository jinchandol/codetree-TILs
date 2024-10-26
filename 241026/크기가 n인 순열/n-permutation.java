import java.util.*;
import java.io.*;

public class Main {
    public static int n;
    public static ArrayList<Integer> nums = new ArrayList<>();
    public static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        visited = new boolean[n+1];

        permutation(1);
    }

    public static void printPermutation() {
        for (int i=0; i<nums.size(); i++) {
            System.out.print(nums.get(i) + " ");
        }
        System.out.println();
    }

    public static void permutation(int currNum) {
        if (currNum == n + 1) {
            printPermutation();
            return;
        }

        for (int i=1; i<=n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            nums.add(i);

            permutation(currNum + 1);

            nums.remove(nums.size() - 1);
            visited[i] = false;
        }
    }
}