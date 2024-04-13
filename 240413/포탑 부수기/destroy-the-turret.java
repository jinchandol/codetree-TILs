import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, K, ans;
	static int[][] map;
	static boolean[][] process;
	static int[] drs = { 0, 1, 0, -1, 1, 1, -1, -1 };
	static int[] dcs = { 1, 0, -1, 0, 1, -1, -1, 1 };

	static class Tower implements Comparable<Tower> {
		int r, c, power, attackTime;

		public Tower(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Tower(int r, int c, int power) {
			this.r = r;
			this.c = c;
			this.power = power;
			this.attackTime = 0;
		}

		@Override
		public int compareTo(Tower o) {
			// 제 1 정렬 기준 (공격력)
			if (this.power != o.power)
				return Integer.compare(this.power, o.power);
			// 제 2 정렬 기준 (최근 공격)
			if (this.attackTime != o.attackTime)
				return Integer.compare(o.attackTime, this.attackTime);
			// 제 3 정렬 기준 (행과 열의 합이 가장 큰 포탑)
			if ((this.r + this.c) != (o.r + o.c))
				return Integer.compare((o.r + o.c), (this.r + this.c));
			// 제 4 정렬 기준 (열)
			return Integer.compare(o.c, this.c);
		}

		@Override
		public String toString() {
			return "Tower [r=" + r + ", c=" + c + ", power=" + power + ", attackTime=" + attackTime + "]";
		}
	}

	private static void init() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0)
					continue;
				process[r][c] = true;
			}
		}
	}

	private static void setStat(Tower attack, Tower defense, int time) {

		attack.attackTime = time;
		attack.power += (N + M);
		map[attack.r][attack.c] = attack.power;

		process[attack.r][attack.c] = false;
		process[defense.r][defense.c] = false;

	}

	private static boolean Laser(Tower attack, Tower defense) {
		boolean[][] visited = new boolean[N][M];
		int[][][] dp = new int[N][M][2];
		Queue<Tower> q = new LinkedList<>();

		visited[attack.r][attack.c] = true;
		dp[attack.r][attack.c] = new int[] { attack.r, attack.c };
		q.add(new Tower(attack.r, attack.c));

		while (!q.isEmpty()) {
			Tower t = q.poll();

			// 목적지에 도달하였으면 역추적하면서 값을 적용해주어야 함.
			if (t.r == defense.r && t.c == defense.c) {
//				for (int[][] arr : dp) {
//					for (int[] arr2 : arr) {
//						System.out.print(Arrays.toString(arr2) + " ");
//					}
//					System.out.println();
//				}

				map[t.r][t.c] = Math.max(0, map[t.r][t.c] - attack.power);

				int nr = dp[t.r][t.c][0];
				int nc = dp[t.r][t.c][1];

				while (true) {
					if (nr == attack.r && nc == attack.c) {
						return true;
					}

					map[nr][nc] = Math.max(0, map[nr][nc] - (attack.power / 2));
					process[nr][nc] = false;

					int nextR = dp[nr][nc][0];
					int nextC = dp[nr][nc][1];

					nr = nextR;
					nc = nextC;
				}
			}

			for (int dir = 0; dir < 4; dir++) {
				int nr = t.r + drs[dir];
				int nc = t.c + dcs[dir];

				// 격자 밖으로 벗어나는 경우
				if (nr < 0 || N <= nr || nc < 0 || M <= nc) {
					nr = (nr + N) % N;
					nc = (nc + M) % M;
				}

				// 이미 방문한 경우
				if (visited[nr][nc])
					continue;
				// 레이저가 이동하지 못하는 경우
				if (map[nr][nc] == 0)
					continue;

				visited[nr][nc] = true;
				dp[nr][nc] = new int[] { t.r, t.c };
				q.add(new Tower(nr, nc));
			}
		}

		return false;
	}

	private static void doCanon(Tower attack, Tower defense) {
		map[defense.r][defense.c] = Math.max(0, map[defense.r][defense.c] - attack.power);

		for (int dir = 0; dir < 8; dir++) {
			int nr = defense.r + drs[dir];
			int nc = defense.c + dcs[dir];

			// 격자 밖으로 벗어나는 경우
			if (nr < 0 || N <= nr || nc < 0 || M <= nc) {
				nr = (nr + N) % N;
				nc = (nc + M) % M;
			}

			// 포탄이 영향을 끼치지 못하는 경우
			if (map[nr][nc] == 0)
				continue;

			// 공격자 자기자신이 포탑 사정권 안에 들어가는 경우
			if (attack.r == nr && attack.c == nc)
				continue;

			map[nr][nc] = Math.max(0, map[nr][nc] - (attack.power / 2));
			process[nr][nc] = false;
		}

	}

	private static void repair(List<Tower> towerList) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (process[r][c])
					map[r][c]++;
			}
		}

		for (int i = 0; i < towerList.size(); i++) {
			Tower t = towerList.get(i);
			t.power = map[t.r][t.c];
		}

		for (int i = 0; i < towerList.size(); i++) {
			if (towerList.get(i).power <= 0) {
				towerList.remove(i);
				i--;
			}
		}
	}

	private static int fin() {
		int maxi = Integer.MIN_VALUE;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				maxi = Math.max(maxi, map[r][c]);
			}
		}

		return maxi;
	}

	private static void printMap() {
		for (int[] arr : map) {
			System.out.println(Arrays.toString(arr));
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Tower> towerList = new ArrayList<>();

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		ans = 0;
		map = new int[N][M];
		process = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = sc.nextInt();
				if (map[r][c] != 0) {
					towerList.add(new Tower(r, c, map[r][c]));
				}
			}
		}

		for (int i = 1; i <= K; i++) {
			// 해당 턴 초기화
			init();

			// 가장 약한 포탑과 가장 강한 포탑 설정
			Collections.sort(towerList);

			int attackIdx = 0;
			int defenseIdx = towerList.size() - 1;

			if (attackIdx == defenseIdx)
				continue;

			// 1. 공격자 선정
			Tower attack = towerList.get(attackIdx);
			Tower defense = towerList.get(defenseIdx);
			setStat(attack, defense, i);

			// 2. 공격자의 공격
			if (!Laser(attack, defense)) {
				doCanon(attack, defense);
			}

			// 3. 포탑 정비 및 포탑 정보 수정
			repair(towerList);
		}

		ans = fin();

		System.out.println(ans);
	}

}