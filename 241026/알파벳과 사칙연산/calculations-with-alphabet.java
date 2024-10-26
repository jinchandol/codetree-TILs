import java.util.*;
import java.io.*;

public class Main {
    public static int ans;
    public static int[] alphabet = new int[6];
    public static ArrayList<Integer> index = new ArrayList<>();
    public static ArrayList<Character> operation = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();

        for (char c : s) {
            if (0 <= c - 'a' && c - 'a' <=5) index.add(c - 'a');
            else operation.add(c);
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
        int numIndex = 0;
        int operIndex = 0;
        int answer = alphabet[index.get(numIndex)];

        while (numIndex < index.size()-1 && operIndex < operation.size()) {
            int a = answer;
            int b = alphabet[index.get(numIndex+1)];
            
            answer = doOperation(a, b, operation.get(operIndex));
            
            numIndex += 2;
            operIndex++;
        }

        ans = Math.max(ans, answer);
    }

    public static int doOperation(int a, int b, int operation) {
        switch (operation) {
            case '+' : {
                return sum(a, b);
            }
            case '-' : {
                return minus(a, b);
            }
            default : {
                return product(a, b);
            }
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