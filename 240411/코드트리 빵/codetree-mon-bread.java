import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, time;
	// 상좌우하 이동
	static int[] drs = {-1, 0, 0, 1};
	static int[] dcs = {0, -1, 1, 0};
	
	static int[][] map;
	static int[][] step;
	static boolean[][] check, visited;
	
	static Person[] persons;
	static Store[] stores;
	static final Person EMPTY = new Person(-1, -1);
	
	static class Person {
		int r, c;
		boolean arrive;
		
		public Person(int r, int c) {
			this.r = r;
			this.c = c;
			arrive = false;
		}
	}
	
	static class Store {
		int r, c;
		boolean state;
		
		public Store(int r, int c) {
			this.r = r;
			this.c = c;
			state = false;
		}
	}
	
	static void search(int idx) {
		for(int r = 0; r < N; r++)
            for(int c = 0; c < N; c++) {
                visited[r][c] = false;
                step[r][c] = 0;
            }
		
		Store s = stores[idx];
				
		Queue<int[]> q = new LinkedList<>();
		visited[s.r][s.c] = true;
		step[s.r][s.c] = 0;
		q.add(new int[] {s.r, s.c});
		
		while(!q.isEmpty()) {
			int[] currS = q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = currS[0] + drs[dir];
				int nc = currS[1] + dcs[dir];
				
				if (nr<0 || N<=nr || nc<0 || N<=nc) continue;
				if (visited[nr][nc]) continue;
				if (map[nr][nc] == 2) continue;				
				
				visited[nr][nc] = true;
				step[nr][nc] = step[currS[0]][currS[1]] + 1;
				q.add(new int[]{nr, nc});
			}
		}
	}
	
	public static void simulate() {
        // Step 1. 격자에 있는 사람들에 한하여 편의점 방향을 향해 1칸 움직입니다.
        for(int i = 1; i <= M; i++) {
            // 아직 격자 밖에 있는 사람이거나 이미 편의점에 도착한 사람이라면 패스합니다.
            if(persons[i] == EMPTY 
            		|| (persons[i].r == stores[i].r && persons[i].c == stores[i].c))
                continue;
            
            // 원래는 현재 위치에서 편의점 위치까지의 최단거리를 구해줘야 합니다.
            // 다만 최단거리가 되기 위한 그 다음 위치를 구하기 위해서는
            // 거꾸로 편의점 위치를 시작으로 현재 위치까지 오는 최단거리를 구해주는 것이 필요합니다.
            // 따라서 편의점 위치를 시작으로 하는 BFS를 진행합니다.
            search(i);
    
            int pr = persons[i].r, pc = persons[i].c;
            // 현재 위치에서 상좌우하 중 최단거리 값이 가장 작은 곳을 고르면
            // 그 곳으로 이동하는 것이 최단거리 대로 이동하는 것이 됩니다.
            // 그러한 위치 중 상좌우하 우선순위대로 가장 적절한 곳을 골라줍니다.
            int minDist = Integer.MAX_VALUE;
            int minR = -1, minC = -1;
            for(int j = 0; j < 4; j++) {
                int nr = pr + drs[j], nc = pc + dcs[j];
                
                if (nr<0 || N<=nr || nc<0 || N<=nc) continue;
				if (!visited[nr][nc]) continue;
                if(minDist > step[nr][nc]) {
                    minDist = step[nr][nc];
                    minR = nr; minC = nc;
                }
            }
    
            // 우선순위가 가장 높은 위치로 한 칸 움직여줍니다.
            persons[i] = new Person(minR, minC);
        }
    
        // Step 2. 편의점에 도착한 사람에 한하여 
        //         앞으로 이동 불가능하다는 표시로 
        //         grid값을 2로 바꿔줍니다.
        for(int i = 1; i <= M; i++) {
            if(persons[i].r == stores[i].r && persons[i].c == stores[i].c) {
                int pr = persons[i].r, pc = persons[i].c;
                map[pr][pc] = 2;
            }
        }
    
        // Step 3. 현재 시간 currT에 대해 currT ≤ m를 만족한다면
        //         t번 사람이 베이스 캠프로 이동합니다.
    
        // currT가 m보다 크다면 패스합니다.
        if(time > M)
            return;
        
        // Step 3-1. 편의점으로부터 가장 가까운 베이스 캠프를 고르기 위해
        //           편의점을 시작으로 하는 BFS를 진행합니다.
        search(time);
    
        // Step 3-2. 편의점에서 가장 가까운 베이스 캠프를 선택합니다.
        //           i, j가 증가하는 순으로 돌리기 때문에
        //           가장 가까운 베이스 캠프가 여러 가지여도
        //           알아서 (행, 열) 우선순위대로 골라집니다.
        int minDist = Integer.MAX_VALUE;
        int minR = -1, minC = -1;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                // 방문 가능한 베이스 캠프 중
                // 거리가 가장 가까운 위치를 찾아줍니다.
                if(visited[r][c] && map[r][c] == 1 && minDist > step[r][c]) {
                    minDist = step[r][c];
                    minR = r; minC = c;
                }
            }
        }
        
        // 우선순위가 가장 높은 베이스 캠프로 이동합니다.
        persons[time] = new Person(minR, minC);
        // 해당 베이스 캠프는 앞으로 이동이 불가능한 칸임을 표시합니다.
        map[minR][minC] = 2;
    }
	
	// 전부 편의점에 도착헀는지를 확인합니다.
    public static boolean end() {
        // 단 한 사람이라도
        // 편의점에 도착하지 못했다면
        // 아직 끝나지 않은 것입니다.
        for(int i = 1; i <= M; i++) {
            if(!(persons[i].r == stores[i].r && persons[i].c == stores[i].c))
                return false;
        }
    
        // 전부 편의점에 도착했다면 끝입니다.
        return true;
    }
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		time = 0;
		
		map = new int[N][N];
		step = new int[N][N];
		check = new boolean[N][N];
		visited = new boolean[N][N];
		
		persons = new Person[M+1]; // M명의 사람들이 존재
		stores = new Store[M+1]; // M개의 편의점이 존재
		
		for (int r=0; r<N; r++) {
			for (int c=0; c<N; c++) {
				map[r][c] = sc.nextInt();
			}
		}
		
		
		for (int i=1; i<=M; i++) {
			int r = sc.nextInt()-1;
			int c = sc.nextInt()-1;
			stores[i] = new Store(r, c);
		}
		
		for (int i=1; i<=M; i++) {
			persons[i] = EMPTY;
		}
		
		// 이동 시작
		while (true) {
			time++;
			simulate();
			if (end()) break;
		}
		
		System.out.println(time);
	}

	
}