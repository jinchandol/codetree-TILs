import java.util.Scanner;

public class Main {
    public static int MAX_LENGTH = 100;
    public static int[] a = new int[MAX_LENGTH];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        int n = sc.nextInt();
        for(int i = 0; i < n; i++)
            a[i] = sc.nextInt();

        int cnt = 0;
        for (int i = 0; i < n - 2; i++) 
            for (int j = i+1; j < n - 1; j++)
                for (int k = j+1; k < n; k++)
                    if (a[i] <= a[j] && a[j] <= a[k])
                        cnt++;
        
        System.out.println(cnt);
    }
}