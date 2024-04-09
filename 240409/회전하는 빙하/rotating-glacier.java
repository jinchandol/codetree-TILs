import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, Q, size, group, mount;
	static int[][] map, tmp;
	static int[][] step = new int[4][];
	static boolean[][] visited;
	static int[] level;
	static int[] drs = { 0, 1, 0, -1 };
	static int[] dcs = { 1, 0, -1, 0 };

	static {
		step[0] = new int[] { 0, 1 };
		step[1] = new int[] { 1, 0 };
		step[2] = new int[] { -1, 0 };
		step[3] = new int[] { 0, -1 };
	}

	static class Pair {
		int r, c, dist;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	// 회전시키는 메소드
	private static void rotate(int lv) {

		int boxSize = (int) Math.pow(2, lv);
		int halfSize = (int) Math.pow(2, lv - 1);

		// 회전하기
		for (int r = 0; r < size; r += boxSize) {
			for (int c = 0; c < size; c += boxSize) {
				move(r, c, halfSize, 0); // 오른쪽
				move(r, c + halfSize, halfSize, 1); // 아래
				move(r + halfSize, c, halfSize, 2); // 위
				move(r + halfSize, c + halfSize, halfSize, 3); // 왼쪽
			}
		}

		// rotate 이후 결과를 map배열로 가져오기
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				map[r][c] = tmp[r][c];
			}
		}
	}

	private static void move(int sr, int sc, int halfSize, int dir) {
		for (int r = sr; r < sr + halfSize; r++) {
			for (int c = sc; c < sc + halfSize; c++) {
				int nr = r + step[dir][0] * halfSize;
				int nc = c + step[dir][1] * halfSize;

				if (nr < 0 || size <= nr || nc < 0 || size <= nc)
					continue;
				tmp[nr][nc] = map[r][c];
			}
		}
	}

	private static void melt() {
		init();

		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (map[r][c] != 0) {
					int cnt = 0;
					for (int dir = 0; dir < 4; dir++) {
						int nr = r + drs[dir];
						int nc = c + dcs[dir];

						if (nr < 0 || size <= nr || nc < 0 || size <= nc) {
							cnt++;
							continue;
						}

						if (map[nr][nc] == 0) {
							cnt++;
							continue;
						}
					}

					if (cnt >= 2) {
						tmp[r][c] = map[r][c] - 1;
					} else {
						tmp[r][c] = map[r][c];
					}
				}

			}
		}

		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				map[r][c] = tmp[r][c];
			}
		}

	}

	private static void search(Pair pair) {
		Queue<Pair> q = new LinkedList<>();

		visited[pair.r][pair.c] = true;
		q.add(pair);
		int count = 1;
		while (!q.isEmpty()) {
			Pair p = q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nr = p.r + drs[dir];
				int nc = p.c + dcs[dir];

				if (nr < 0 || size <= nr || nc < 0 || size <= nc)
					continue;
				if (map[nr][nc] == 0)
					continue;
				if (visited[nr][nc])
					continue;

				visited[nr][nc] = true;
				q.add(new Pair(nr, nc));
				count++;
			}
		}

		group = Math.max(group, count);
	}

	private static void total() {
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				mount += map[r][c];
			}
		}
	}

	private static void init() {
		for (int r = 0; r < size; r++) {
			Arrays.fill(tmp[r], 0);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Q = sc.nextInt();
		size = (int) Math.pow(2, N);

		map = new int[size][size];
		tmp = new int[size][size];

		visited = new boolean[size][size];
		level = new int[Q];
		group = 0;
		mount = 0;

		// 맵 정보 입력
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		// 회전 레벨 입력
		for (int i = 0; i < Q; i++) {
			level[i] = sc.nextInt();
		}

		// 회전 구현
		for (int i = 0; i < Q; i++) {
			if (level[i] == 0) {
				melt();
				continue;
			}
			rotate(level[i]);
			melt();
		}

		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (map[r][c] != 0) {
					// bfs 탐색 구현
					search(new Pair(r, c));
				}
			}
		}

		// 총합 구현
		total();

		System.out.println(mount);
		System.out.println(group);
	}
}