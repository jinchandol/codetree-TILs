import java.util.*;
import java.io.*;

public class Main {
    public static int ans = Integer.MIN_VALUE;
    public static int[] alphabet = new int[6];
    public static boolean[] visited = new boolean[6];
    public static ArrayList<Integer> index = new ArrayList<>();
    public static ArrayList<Character> operation = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();

        for (char c : s) {
            if (0 <= c - 'a' && c - 'a' <=5) index.add(c - 'a');
            else operation.add(c);
        }

        if (index.size() == 1) {
            ans = 4;
            System.out.println(ans);
            return;
        }

        settingNumber(0);
        System.out.println(ans);
    }

    public static void settingNumber(int n) {
        if (n == index.size()) {
            performOperations();
            return;
        }
        for (int i = 1; i <= 4; i++) {
            alphabet[index.get(n)] = i;
            settingNumber(n + 1);
        }
    }

    public static void performOperations() {
        int result = alphabet[index.get(0)]; 
        for (int i = 0; i < operation.size(); i++) {
            int nextValue = alphabet[index.get(i + 1)];
            result = doOperation(result, nextValue, operation.get(i));
        }
        ans = Math.max(ans, result);
    }

    public static int doOperation(int a, int b, int operation) {
        switch (operation) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            default: throw new IllegalArgumentException("Invalid operation");
        }
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int minus(int a, int b) {
        return a - b;
    }

    public static int product(int a, int b) {
        return a * b;
    }
}