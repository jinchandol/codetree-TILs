import java.util.Scanner;

public class Main {
    public static int n, s;
    public static int[] arr;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        s = sc.nextInt();

        int[] arr = new int[n+1];

        for (int i=1; i<=n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int ans = Integer.MAX_VALUE;

        int sumVal = 0;
        int j = 0;
        
        for(int i = 1; i <= n; i++) {
            while(j + 1 <= n && sumVal + arr[j + 1] < s) {
                sumVal += arr[j + 1];
                j++;
            }
            
            if (j + 1 <= n && sumVal + arr[j + 1] >= s) 
                ans = Math.min(ans, j - i + 2);
            
            sumVal -= arr[i];
        }

        System.out.print((ans == Integer.MAX_VALUE) ? -1 : ans);
    }
}