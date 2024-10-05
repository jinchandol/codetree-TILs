import java.util.Scanner;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Main {    
    // 변수 선언
    public static int n;
    public static TreeMap<Integer, Integer> m = new TreeMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            String command = sc.next();

            if(command.equals("add")) {
                int k = sc.nextInt(); 
                int v = sc.nextInt();
                m.put(k, v);
            }
            else if(command.equals("remove")) {
                int k = sc.nextInt();
                m.remove(k);
            }
            else if(command.equals("find")) {
                int k = sc.nextInt();
                if(!m.containsKey(k))
                    System.out.println("None");
                else
                    System.out.println(m.get(k));
            }
            else {
                if(m.isEmpty()) {
                    System.out.println("None");
                    continue;
                }
                Iterator<Entry<Integer, Integer>> it = m.entrySet().iterator();
                while(it.hasNext()) {
                    Entry<Integer, Integer> entry = it.next();
                    System.out.print(entry.getValue() + " ");
                }
                System.out.println();
            }
        }
    }
}