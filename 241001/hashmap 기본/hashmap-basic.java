import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        int N = Integer.parseInt(br.readLine());

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "add" : {
                    int key = Integer.parseInt(st.nextToken());
                    int value = Integer.parseInt(st.nextToken());
                    hashmap.put(key, value);
                    break;
                }
                case "find" : {
                    int key = Integer.parseInt(st.nextToken());
                    if (hashmap.get(key) != null) {
                        System.out.println(hashmap.get(key));
                    } else {
                        System.out.println("None");
                    }
                    break;
                }
                case "remove" : {
                    int key = Integer.parseInt(st.nextToken());
                    hashmap.remove(key);
                }
            }
        }
    }
}