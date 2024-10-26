import java.util.*;

class Line {
    int left, right;
    public Line(int left, int right) {
        this.left = left;
        this.right = right;
    }
}


public class Main {
    public static int n, ans; 
    public static ArrayList<Line> Lines = new ArrayList<>();
    public static ArrayList<Integer> selectedLine = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for (int i=0; i<n; i++) {
            Lines.add(new Line(sc.nextInt(), sc.nextInt()));
        }

        selectLine(0);
        System.out.println(ans);
    }

    public static int calc() {
        int size = selectedLine.size();
        boolean isOk = true;
        for (int target = 0; target < size - 1 ; target++) {
            Line targetLine = Lines.get(selectedLine.get(target));
            for (int idx = target + 1; idx < size; idx++) {
                Line idxLine = Lines.get(selectedLine.get(idx));
                if (idxLine.left < targetLine.left && targetLine.left < idxLine.right) {
                    isOk = false;
                    break;
                }

                if (idxLine.left < targetLine.right && targetLine.right < idxLine.right) {
                    isOk = false;
                    break;
                }
            }
        }

        return (isOk ? size : 0);

    }

    public static void selectLine(int index) {
        if (index == Lines.size()) {
            ans = Math.max(ans, calc());
            return;
        }

        selectedLine.add(index);
        selectLine(index + 1);
        
        selectedLine.remove(selectedLine.size() - 1);
        selectLine(index + 1);
    }
}