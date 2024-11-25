import java.util.Scanner;

public class Main {
    public static String str;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        str = sc.next();
        int n = str.length();

        // 모든 쌍을 다 잡아봅니다.
        int cnt = 0;
        for(int i = 0; i < n; i++)
            for(int j = i + 1; j < n; j++)
                if(str.charAt(i) == '(' && str.charAt(j) == ')')
                    cnt++;
        
        System.out.println(cnt);
    }
}