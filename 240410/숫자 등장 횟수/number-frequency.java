import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        Map<Integer, Integer> map = new HashMap<>();

        for (int i=0; i<n; i++){
            int key = sc.nextInt();
            if(map.containsKey(key)){
                map.put(key, map.get(key)+1);
            } else {
                map.put(key, 1);
            }
        }

        for (int i=0; i<m; i++){
            int key = sc.nextInt();
            System.out.print(map.getOrDefault(key, 0) + " ");
        }
    }
}