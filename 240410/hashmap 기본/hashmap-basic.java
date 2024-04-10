import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Integer> map = new HashMap<>();
        int N = sc.nextInt();

        while (N-- > 0){
            String s = sc.next();
            switch (s) {
                case "add" : {
                    int key = sc.nextInt();
                    int value = sc.nextInt();
                    map.put(key, value);
                    break;
                }

                case "find" : {
                    int key = sc.nextInt();
                    System.out.println((map.get(key) == null) ? "None" : map.get(key));
                    break;
                }

                case "remove" : {
                    int key = sc.nextInt();
                    map.remove(key);
                    break;
                }

                default :
                    break;
            }
        }
    }
}