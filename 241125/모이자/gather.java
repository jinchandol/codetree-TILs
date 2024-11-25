import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        // 이동 거리의 합의 최솟값
        Scanner sc = new Scanner(System.in);

        int answer = Integer.MAX_VALUE;
        int N = sc.nextInt();
        int[] arr = new int[N];

        for (int index = 0; index < N; index++) {
            arr[index] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            int tmp = 0;
            for (int j = 0; j < N; j++) {
                tmp += (arr[j] * Math.abs(i-j));
            }
            answer = Math.min(answer, tmp);
        }

        System.out.println(answer);
    }
}