import java.util.Scanner;

public class Main {
    public static int[] A = new int[100001];
    public static int[] B = new int[100001];
    public static int n, m;

    public static boolean isSubSequence() {
        int i=1;
        for (int j=1; j<=m; j++) {
            while(i<=n && A[i] != B[j])
                i++;
            
            if (i == n+1)
                return false;
            else
                i++;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        for (int i=1; i<=n; i++)
            A[i] = sc.nextInt();

        for (int j=1; j<=m; j++)
            B[j] = sc.nextInt();

        if (isSubSequence()) System.out.println("Yes");
        else System.out.println("No");
    }
}