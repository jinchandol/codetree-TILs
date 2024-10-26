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
        
        for (int i = 0; i < size - 1; i++) {
            Line line1 = Lines.get(selectedLine.get(i));
            for (int j = i + 1; j < size; j++) {
                Line line2 = Lines.get(selectedLine.get(j));
                
                // 두 선분이 겹치는지 확인
                if (!(line1.right < line2.left || line2.right < line1.left)) {
                    return 0;
                }
            }
        }

        return size;
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