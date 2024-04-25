import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M, K;
	static int[][] maze;
	static int[] drs = { -1, 1, 0, 0 };
	static int[] dcs = { 0, 0, -1, 1 };
	static Pair exit;
	static Pair[] runners;

	static class Pair {
		int r, c, dist;
		boolean isExit;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
			this.dist = 0;
			this.isExit = false;
		}

		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + ", dist=" + dist + ", isExit=" + isExit + "]";
		}
	}

	private static void move() {
		for (int idx = 0; idx < M; idx++) {
			// 탈출한 참가자는 고려하지 않아도 됨
			if (runners[idx].isExit)
				continue;

			for (int dir = 0; dir < 4; dir++) {
				int nr = runners[idx].r + drs[dir];
				int nc = runners[idx].c + dcs[dir];

				// 격자를 벗어나는 경우
				if (nr < 0 || N <= nr || nc < 0 || N <= nc)
					continue;
				// 벽이 있는 경우
				if (maze[nr][nc] > 0)
					continue;
				// 가까워 지는 것이 아닌 경우
				if (Math.abs(exit.r - nr) + Math.abs(exit.c - nc) >= Math.abs(exit.r - runners[idx].r)
						+ Math.abs(exit.c - runners[idx].c))
					continue;

				runners[idx].r = nr;
				runners[idx].c = nc;
				runners[idx].dist++;

				// 탈출했으면 상태 갱신
				if (runners[idx].r == exit.r && runners[idx].c == exit.c) {
					runners[idx].isExit = true;
				}

				break;
			}
		}
	}

	private static boolean isOk() {
		for (int idx = 0; idx < M; idx++) {
			// 탈출하지 못한 참가자가 있다면 rotation 진행되어야 함.
			if (!runners[idx].isExit)
				return true;
		}

		return false;
	}

	private static boolean search(int r, int c, int range) {
		for (int idx = 0; idx < M; idx++) {
			// 탈출한 참가자는 고려하지 않아도 됨
			if (runners[idx].isExit)
				continue;

			if (r <= runners[idx].r && runners[idx].r <= r + range && c <= runners[idx].c
					&& runners[idx].c <= c + range) {
				return true;
			}
		}
		return false;
	}

	private static void rotation(int r, int c, int range) {
		int[][] tmp = new int[N][N];
		boolean[] isRotation = new boolean[M];

		// maze 회전시킴
		for (int row = r; row <= r + range; row++) {
			for (int col = c; col <= c + range; col++) {
				tmp[r + (col - c)][c + range - (row - r)] = maze[row][col];

				// 참가자 회전 갱신
				for (int idx = 0; idx < M; idx++) {
					// 탈출한 참가자는 고려하지 않아도 됨
					if (runners[idx].isExit)
						continue;

					if (isRotation[idx])
						continue;

					if (runners[idx].r == row && runners[idx].c == col) {
						runners[idx].r = r + (col - c);
						runners[idx].c = c + range - (row - r);
						isRotation[idx] = true;
					}
				}
			}
		}

		for (int row = r; row <= r + range; row++) {
			for (int col = c; col <= c + range; col++) {
				maze[row][col] = tmp[row][col];
				if (maze[row][col] > 0)
					maze[row][col]--;
				if (maze[row][col] == -1) {
					exit.r = row;
					exit.c = col;
				}
			}
		}
	}

	private static void mazing() {
		// 사각형을 잡고
		int range = Integer.MAX_VALUE;
		for (int idx = 0; idx < M; idx++) {
			// 탈출한 참가자는 고려하지 않아도 됨
			if (runners[idx].isExit)
				continue;
			range = Math.min(range, Math.max(Math.abs(exit.r - runners[idx].r), Math.abs(exit.c - runners[idx].c)));
		}

		
		int targetR = -1;
		int targetC = -1;

		out: for (int r = exit.r - range; r <= exit.r; r++) {
			for (int c = exit.c - range; c <= exit.c; c++) {
				if (r < 0 || c < 0)
					continue;
				
				if (r + range >= N || c + range >= N) 
					continue;
				

				if (search(r, c, range)) {
					targetR = r;
					targetC = c;
					break out;
				}
			}
		}
		
		if (targetR != -1 && targetC != -1) {
			// 회전을 시킴
			rotation(targetR, targetC, range);
		}
	}

	private static void printMaze() {
		for (int[] arr : maze) {
			System.out.println(Arrays.toString(arr));
		}

		for (Pair p : runners) {
			System.out.println(p);
		}

		System.out.println("---------------------------------------------");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		maze = new int[N][N];
		runners = new Pair[M];

		int totalDist = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				maze[r][c] = sc.nextInt();
			}
		}

		// 탈출하는 사람의 정보를 받음
		for (int i = 0; i < M; i++) {
			runners[i] = new Pair(sc.nextInt() - 1, sc.nextInt() - 1);
		}

		// 탈출 위치에 대한 정보를 받음
		exit = new Pair(sc.nextInt() - 1, sc.nextInt() - 1);
		maze[exit.r][exit.c] = -1;

		// 탈출 시작함
		for (int time = 1; time <= K; time++) {
			// 참가자들이 이동함
			move();
			
			if (isOk()) {
				// 맵이 회전함
				mazing();
			} else {
				break;
			}
		}

		for (int i = 0; i < M; i++) {
			totalDist += runners[i].dist;
		}

		System.out.println(totalDist);
		System.out.println((exit.r + 1) + " " + (exit.c + 1));
	}

}