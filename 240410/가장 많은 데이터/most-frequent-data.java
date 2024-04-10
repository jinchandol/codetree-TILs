import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0;
        
        String[] words = new String[100001];
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++)
            words[i] = sc.next();

        for (int i=0;i<n;i++){
            if(map.containsKey(words[i])){
                map.put(words[i], map.get(words[i]) + 1);
            } else {
                map.put(words[i], 1);
            }

            ans = Math.max(ans, map.get(words[i]));
        }

        System.out.println(ans);
    }
}