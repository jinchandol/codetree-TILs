import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int L, N, Q, ans;
	static int[][] map;
	static int[] drs = { -1, 0, 1, 0 };
	static int[] dcs = { 0, 1, 0, -1 };
	static int[][] command;
	static List<Knight> kList;
	static int[] originalHP;

	static class Knight {
		int r, c, h, w, k;
		boolean movable;

		public Knight(int r, int c, int h, int w, int k) {
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.k = k;
			this.movable = false;
		}
	}

	private static void knightMove(int i, int d) {
		// 움직이고자 하는 방향의 맞닿아 있는 좌표들
		Knight knight = kList.get(i);

		switch (d) {
		// 상
		case 0: {
			for (int c = 0; c <= knight.w; c++) {
				int nr = knight.r + drs[d];
				int nc = knight.c + c;

				// 이동할 좌표가 맵 밖이라면 그대로 있습니다.
				if (nr < 0 || L <= nr || nc < 0 || L <= nc) {
					knight.movable = false;
					return;
				}
				// 다음 칸이 벽이라면
				if (map[nr][nc] == 2) {
					knight.movable = false;
					return;
				}

				knight.movable = true;
			}

			boolean canGo = true;

			// 상기 구문을 통과할 경우 해당 기사는 움직일 가능성이 존재함.
			for (int idx = 0; idx < kList.size(); idx++) {
				if (idx == i)
					continue;
				if (kList.get(idx).k <= 0)
					continue;

				Knight nextKnight = kList.get(idx);
				for (int nextC = 0; nextC <= nextKnight.w; nextC++) {
					int targetR = nextKnight.r + nextKnight.h;
					int targetC = nextKnight.c + nextC;

					if (knight.r + drs[d] == targetR && knight.c <= targetC && targetC <= knight.c + knight.w) {
						knightMove(idx, d);

						if (!nextKnight.movable) {
							canGo = false;
							break;
						}

						break;
					}
				}

			}

			if (canGo) {
				knight.r += drs[d];
				knight.c += dcs[d];
			}
			break;
		}

		// 우
		case 1: {
			for (int r = 0; r <= knight.h; r++) {
				int nr = knight.r + r;
				int nc = knight.c + knight.w + dcs[d];

				// 이동할 좌표가 맵 밖이라면 그대로 있습니다.
				if (nr < 0 || L <= nr || nc < 0 || L <= nc) {
					knight.movable = false;
					return;
				}
				// 다음 칸이 벽이라면
				if (map[nr][nc] == 2) {
					knight.movable = false;
					return;
				}

				knight.movable = true;
			}

			boolean canGo = true;

			// 상기 구문을 통과할 경우 해당 기사는 움직일 가능성이 존재함.
			for (int idx = 0; idx < kList.size(); idx++) {
				if (idx == i)
					continue;
				if (kList.get(idx).k <= 0)
					continue;

				Knight nextKnight = kList.get(idx);
				for (int nextR = 0; nextR <= nextKnight.h; nextR++) {
					int targetR = nextKnight.r + nextR;
					int targetC = nextKnight.c;

					if (knight.r <= targetR && targetR <= knight.r + knight.h
							&& targetC == knight.c + knight.w + dcs[d]) {
						knightMove(idx, d);

						if (!nextKnight.movable) {
							canGo = false;
							break;
						}

						break;
					}
				}

			}

			if (canGo) {
				knight.r += drs[d];
				knight.c += dcs[d];
			}
			break;
		}

		// 하
		case 2: {
			for (int c = 0; c <= knight.w; c++) {
				int nr = knight.r + knight.h + drs[d];
				int nc = knight.c + c;

				// 이동할 좌표가 맵 밖이라면 그대로 있습니다.
				if (nr < 0 || L <= nr || nc < 0 || L <= nc) {
					knight.movable = false;
					return;
				}
				// 다음 칸이 벽이라면
				if (map[nr][nc] == 2) {
					knight.movable = false;
					return;
				}

				knight.movable = true;
			}

			boolean canGo = true;

			// 상기 구문을 통과할 경우 해당 기사는 움직일 가능성이 존재함.
			for (int idx = 0; idx < kList.size(); idx++) {
				if (idx == i)
					continue;
				if (kList.get(idx).k <= 0)
					continue;

				Knight nextKnight = kList.get(idx);
				for (int nextC = 0; nextC <= nextKnight.w; nextC++) {
					int targetR = nextKnight.r;
					int targetC = nextKnight.c + nextC;

					if (knight.r + knight.h + drs[d] == targetR && knight.c <= targetC
							&& targetC <= knight.c + knight.w) {
						knightMove(idx, d);

						if (!nextKnight.movable) {
							canGo = false;
							break;
						}

						break;
					}

				}
			}

			if (canGo) {
				knight.r += drs[d];
				knight.c += dcs[d];
			}
			break;
		}

		// 좌
		case 3: {
			for (int r = 0; r <= knight.h; r++) {
				int nr = knight.r + r;
				int nc = knight.c + dcs[d];

				// 이동할 좌표가 맵 밖이라면 그대로 있습니다.
				if (nr < 0 || L <= nr || nc < 0 || L <= nc) {
					knight.movable = false;
					return;
				}
				// 다음 칸이 벽이라면
				if (map[nr][nc] == 2) {
					knight.movable = false;
					return;
				}

				knight.movable = true;
			}

			boolean canGo = true;

			// 상기 구문을 통과할 경우 해당 기사는 움직일 가능성이 존재함.
			for (int idx = 0; idx < kList.size(); idx++) {
				if (idx == i)
					continue;
				if (kList.get(idx).k <= 0)
					continue;

				Knight nextKnight = kList.get(idx);
				for (int nextR = 0; nextR <= nextKnight.h; nextR++) {
					int targetR = nextKnight.r + nextR;
					int targetC = nextKnight.c + nextKnight.w;

					if (knight.r <= targetR && targetR <= knight.r + knight.h && targetC == knight.c + dcs[d]) {
						knightMove(idx, d);

						if (!nextKnight.movable) {
							canGo = false;
							break;
						}

						break;
					}
				}

			}

			if (canGo) {
				knight.r += drs[d];
				knight.c += dcs[d];
			}
			break;
		}
		}

	}

	private static void damageCal(int i) {
		for (int idx = 0; idx < kList.size(); idx++) {
			if (kList.get(idx).k <= 0)
				continue;

			if (!kList.get(idx).movable)
				continue;

			if (i == idx)
				continue;

			Knight knight = kList.get(idx);
			int damage = 0;

			for (int r = knight.r; r <= knight.r + knight.h; r++) {
				for (int c = knight.c; c <= knight.c + knight.w; c++) {
					if (map[r][c] == 1) {
						damage++;
					}
				}
			}

			knight.k -= damage;
		}
	}

	private static void init() {
		for (Knight knight : kList) {
			knight.movable = false;
		}
	}

	private static void printPos() {
		int idx = 0;
		for (Knight knight : kList) {
			System.out.println("체력은" + knight.k + " " + knight.r + " " + knight.c + " 이고 " + (idx++) + "번 째 기사입니다.");
			System.out.println(knight.movable);
			System.out.println("----------------------------------");
		}
	}

	private static boolean isAlive(int r, int c, int h, int w) {
		for (int i = r; i <= r + h; i++) {
			for (int j = c; j <= c + w; j++) {
				if (i < 0 || L <= i || j < 0 || L <= j) {
					return false;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ans = 0;
		L = sc.nextInt(); // 체스판의 크기
		N = sc.nextInt(); // 기사의 숫자
		Q = sc.nextInt(); // 명령
		kList = new ArrayList<>();
		map = new int[L][L];
		command = new int[Q][2];
		originalHP = new int[N];

		for (int r = 0; r < L; r++) {
			for (int c = 0; c < L; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		for (int cnt = 0; cnt < N; cnt++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			int h = sc.nextInt() - 1;
			int w = sc.nextInt() - 1;
			int k = sc.nextInt();

			if (!isAlive(r, c, h, w)) {
				kList.add(new Knight(r, c, h, w, 0));
				continue;
			}

			kList.add(new Knight(r, c, h, w, k));
			originalHP[cnt] = k;
		}

		for (int cnt = 0; cnt < Q; cnt++) {
			int i = sc.nextInt() - 1;
			int d = sc.nextInt();

			command[cnt][0] = i;
			command[cnt][1] = d;
		}

		// 명령의 진행
		for (int cnt = 0; cnt < Q; cnt++) {
			int i = command[cnt][0];
			int d = command[cnt][1];

			if (kList.get(i).k <= 0)
				continue;

			int currR = kList.get(i).r;
			int currC = kList.get(i).c;

			knightMove(i, d);
			// 대결 데미지
			if (currR == kList.get(i).r && currC == kList.get(i).c) {
				init();
				continue;
			}
			damageCal(i);
			init();
		}

		for (int cnt = 0; cnt < N; cnt++) {
			if (kList.get(cnt).k > 0) {
				ans += (originalHP[cnt] - kList.get(cnt).k);
			}
		}

		System.out.println(ans);
	}
}