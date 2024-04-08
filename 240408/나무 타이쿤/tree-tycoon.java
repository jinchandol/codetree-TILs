import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, M, ans;
    static int[][] map, rule;
    static int[][] yak;
    static int[] cross = { 2, 4, 6, 8 };
    static int[] drs = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] dcs = { 0, 1, 1, 0, -1, -1, -1, 0, 1 };

    private static void move(int idx) {
        int[][] result = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (yak[r][c] == 1) {
                    int dir = rule[idx][0];
                    int step = rule[idx][1];
                    
                    int count = 0;
                    
                    int currR = r;
                    int currC = c;
                    
                    int nextR = 0;
                    int nextC = 0;
                    
                    while (count<step) {
                        nextR = currR + drs[dir];
                        nextC = currC + dcs[dir];
                        
                        if (0<=nextR && nextR<N) {
                            currR = nextR;
                        } else {
                            if (nextR < 0) {
                                currR = N-1;
                            }
                            
                            if (nextR > N-1) {
                                currR = 0;
                            }
                        }

                        if (0<=nextC && nextC<N) {
                            currC = nextC;
                        } else {
                            if (nextC < 0) {
                                currC = N-1;
                            }
                            
                            if (nextC > N-1) {
                                currC = 0;
                            }
                        }
                        
                        count++;
                    }

                    result[currR][currC] = 1;
                }
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                yak[r][c] = result[r][c];
            }
        }
    }

    private static void injection() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (yak[r][c] == 1) {
                    map[r][c]++;
                }
            }
        }
    }

    private static void interaction() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (yak[r][c] == 1) {
                    for (int dir = 0; dir < 4; dir++) {
                        int nr = r + drs[cross[dir]];
                        int nc = c + dcs[cross[dir]];

                        if (nr < 0 || N <= nr || nc < 0 || N <= nc)
                            continue;
                        if (map[nr][nc] == 0)
                            continue;

                        map[r][c]++;
                    }
                }
            }
        }
    }

    private static void setting() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (yak[r][c] == 1) {
                    yak[r][c] = 0;
                } else {
                    if (map[r][c] >= 2) {
                        map[r][c] -= 2;
                        yak[r][c] = 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        ans = 0;
        map = new int[N][N];

        yak = new int[N][N];
        rule = new int[M + 1][2];

        // 맵의 정보 받아오기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] = sc.nextInt();
            }
        }

        // 이동 규칙 정보 받아오기
        for (int i = 1; i < M + 1; i++) {
            rule[i][0] = sc.nextInt();
            rule[i][1] = sc.nextInt();
        }

        // 초기 영양제 위치 세팅
        yak[N - 1][0] = 1;
        yak[N - 1][1] = 1;
        yak[N - 2][0] = 1;
        yak[N - 2][1] = 1;

        for (int i = 1; i < M + 1; i++) {

            // 영양제의 움직임 구현
            move(i);
            
            // 영양제 투입
            injection();

            // 주변 상호작용 구현
            interaction();

            // 전체 탐색하며 영양제 위치 재정렬
            setting();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] > 0) {
                    ans += map[r][c];
                }
            }
        }

        System.out.println(ans);

    }

}