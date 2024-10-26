import java.util.*;

public class Main {
    public static int n, ans;
    public static ArrayList<Integer> selectedNums = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        findBeautifulNumber(0);
        System.out.println(ans);
    }

    public static void printNum() {
        for (int i : selectedNums) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void findBeautifulNumber(int count) {
        if (count == n) {
            if (check()) {
                ans++;
            }
            return;
        }
        
        for (int i=1; i<=4; i++){
            selectedNums.add(i);
            findBeautifulNumber(count+1);
            selectedNums.remove(selectedNums.size() - 1);
        }
    }

    public static boolean check() {
        int i = 0;
        while (i < selectedNums.size()) {
            int num = selectedNums.get(i);
            int count = 0;
            
            // 연속된 숫자의 길이를 확인
            while (i < selectedNums.size() && selectedNums.get(i) == num) {
                count++;
                i++;
            }
            
            // 연속된 숫자가 아름다운 수의 조건을 만족하는지 확인
            if (count != num && count % num != 0) {
                return false;
            }
        }
        return true;
    }
}