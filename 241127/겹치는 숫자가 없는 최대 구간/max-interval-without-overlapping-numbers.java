import java.util.Scanner;

public class Main {
    
    public static int[] arr = new int[100001];
    public static int[] countArr = new int[100001];
    public static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i=1; i<=n; i++)
            arr[i] = sc.nextInt();

        int j = 0;
        int ans = 0;
        for (int i=1; i<=n; i++) {
            while(j+1 <= n && countArr[arr[j+1]] != 1) {
                countArr[arr[j+1]]++;
                j++;
            }

            ans = Math.max(ans, j-i+1);
            countArr[arr[i]]--;
        }

        System.out.println(ans);
                
    }
}