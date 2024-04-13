import java.util.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        int[] command = new int[m];

        for (int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        for (int i=0; i<m; i++){
            command[i] = sc.nextInt();
            int result = binarySearch(arr, command[i], 0, n-1);
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    public static int binarySearch(int[] arr, int target, int l, int r){
        int left = l;
        int right = r;

        while(left<=right){
            int mid = (left+right)/2;

            if (arr[mid] == target) return mid+1;

            if (arr[mid] > target) right = mid-1;
            else left = mid+1;
        }

        return -1;
    }
}