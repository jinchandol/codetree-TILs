import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        String[] words = new String[100001];
        Map<String, Integer> map = new HashMap<>();

        for (int i=0; i<n; i++){
            words[i+1] = sc.next();
            map.put(words[i+1], i+1);
        }

        for (int i=0; i<m; i++){
            String key = sc.next();
            // 입력된 값이 숫자일 때에는 array에 저장한 문자를 출력합니다.
            if('0' <= key.charAt(0) && key.charAt(0) <= '9')
                System.out.println(words[Integer.parseInt(key)]);
            
            // 입력된 값이 문자일 때에는 hashmap에 기록된 대응되는 숫자를 출력합니다.
            else
                System.out.println(map.get(key));
        }

    }
}