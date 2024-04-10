import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M, K, C, dead;
	static int[][] map;
	static int[][] yakMap;
	static int[] drs = { 0, 1, 0, -1, 1, 1, -1, -1 };
	static int[] dcs = { 1, 0, -1, 0, 1, -1, -1, 1 };

	private static void yakDown() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (yakMap[r][c] > 0)
					yakMap[r][c]--;
			}
		}

	}

	private static void grow() {
		int[][] tmp = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				tmp[r][c] = map[r][c];
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {

				if (map[r][c] > 0) {
					int count = 0;

					for (int dir = 0; dir < 4; dir++) {
						int nr = r + drs[dir];
						int nc = c + dcs[dir];

						if (nr < 0 || N <= nr || nc < 0 || N <= nc)
							continue;
						if (map[nr][nc] == -1 || map[nr][nc] == 0)
							continue;
						if (yakMap[nr][nc] > 0)
							continue;

						count++;
					}

					tmp[r][c] = map[r][c] + count;
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = tmp[r][c];
			}
		}
	}

	private static void spread() {
		int[][] tmp = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				tmp[r][c] = map[r][c];
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] > 0) {
					int count = 0;
					int tree = 0;

					for (int dir = 0; dir < 4; dir++) {
						int nr = r + drs[dir];
						int nc = c + dcs[dir];

						if (nr < 0 || N <= nr || nc < 0 || N <= nc)
							continue;

						if (yakMap[nr][nc] > 0)
							continue;

						if (map[nr][nc] == 0)
							count++;
					}

					if (count > 0) {
						tree = (map[r][c] / count);
					}

					for (int dir = 0; dir < 4; dir++) {
						int nr = r + drs[dir];
						int nc = c + dcs[dir];

						if (nr < 0 || N <= nr || nc < 0 || N <= nc)
							continue;

						if (yakMap[nr][nc] > 0)
							continue;

						if (map[nr][nc] == 0)
							tmp[nr][nc] += tree;
					}
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = tmp[r][c];
			}
		}
	}

	private static void yak() {
		int maxCnt = 0;
		int targetR = -1;
		int targetC = -1;
		int[][] deadTree = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int deadCnt = map[r][c];

				if (map[r][c] == 0) {
					deadTree[r][c] = deadCnt;
					continue;
				} else if (map[r][c] > 0){
					for (int dir = 4; dir < 8; dir++) {
						for (int i = 1; i <= K; i++) {
							int nr = r + i * drs[dir];
							int nc = c + i * dcs[dir];

							if (nr < 0 || N <= nr || nc < 0 || N <= nc)
								break;

							if (map[nr][nc] == -1)
								break;

							if (map[nr][nc] == 0 || yakMap[nr][nc] > 0)
								break;

							deadCnt += map[nr][nc];
						}
					}
					deadTree[r][c] = deadCnt;
				}
			}
		}
		

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (deadTree[r][c] > maxCnt) {
					maxCnt = deadTree[r][c];
					targetR = r;
					targetC = c;
				}
			}
		}
		
		yakMap[targetR][targetC] = C + 1;
		map[targetR][targetC] = 0;

		for (int dir = 4; dir < 8; dir++) {
			for (int i = 1; i <= K; i++) {
				int nr = targetR + i * drs[dir];
				int nc = targetC + i * dcs[dir];

				if (nr < 0 || N <= nr || nc < 0 || N <= nc)
					break;

				if (map[nr][nc] == -1)
					break;

				if (map[nr][nc] == 0 || yakMap[nr][nc] > 0) {
					yakMap[nr][nc] = C + 1;
					map[nr][nc] = 0;
					break;
				}

				yakMap[nr][nc] = C + 1;
				map[nr][nc] = 0;
			}
		}

		dead += maxCnt;

	}

	private static void printMap() {
		for (int[] arr : map) {
			System.out.println(Arrays.toString(arr));
		}
	}

	private static void printYakMap() {
		for (int[] arr : yakMap) {
			System.out.println(Arrays.toString(arr));
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		C = sc.nextInt();
		dead = 0;

		map = new int[N][N];
		yakMap = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		for (int turn = 1; turn <= M; turn++) {
			yakDown();
			grow();
			spread();
			yak();
		}

		System.out.println(dead);
	}

}