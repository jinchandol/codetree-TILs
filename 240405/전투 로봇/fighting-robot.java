import java.io.*;
import java.util.*;

public class Main {
    static int N, time;
    static int[][] map;
    static int[] drs = {0, 1, 0, -1};
    static int[] dcs = {1, 0, -1, 0};
    static int[] fishSize;
    static List<Fish> fishList;
    static Shark shark;
    static class Fish implements Comparable<Fish>{
        int r, c, size;
        
        public Fish (int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.r == o.r) {
                return Integer.compare(this.c, o.c);
            }
            return Integer.compare(this.r, o.r);
        }
    }
    
    static class Shark {
        int r, c, size, count, dist;
        
        public Shark(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
        
        public Shark(int r, int c, int size, int count) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.count = count;
        }
    }
    
    private static boolean findTarget() {
        // 맵 복사
    	int[][] copyMap = new int[N][N];
    	
    	// bfs 진행
    	boolean[][] visited = new boolean[N][N];
        Queue<Shark> q = new LinkedList<>();
        visited[shark.r][shark.c] = true;
        q.add(new Shark(shark.r, shark.c, 0));
    	
        while (!q.isEmpty()) {
            Shark s = q.poll();

            for (int dir=0; dir<4; dir++) {
                int nr = s.r + drs[dir];
                int nc = s.c + dcs[dir];
                
                if (nr<0 || N<=nr || nc<0 || N<=nc) continue;
                // 해당 위치에 더 큰 크기의 물고기가 있는 경우 이동하지 못한다.
                if (visited[nr][nc]) continue;
                if (shark.size < map[nr][nc]) continue;

                visited[nr][nc] = true;
                copyMap[nr][nc] = s.dist+1;
                q.add(new Shark(nr, nc, s.dist+1));
            }
        }
        
        
        int result = -1;
        int dist = Integer.MAX_VALUE;
        
        for (int i=0; i<fishList.size(); i++) {
        	Fish f = fishList.get(i);
        	if (shark.size <= map[f.r][f.c]) continue;
        	if (copyMap[f.r][f.c] == 0) continue;
        	int tmp = copyMap[f.r][f.c];
        	if (dist > tmp) {
        		dist = tmp;
        		result = i;
        	}
        }
        
        if (result != -1) {
        	Fish f = fishList.get(result);
            
            time += dist;
            map[f.r][f.c] = 0;
            
            shark.r = f.r;
            shark.c = f.c;
            fishList.remove(result);
            
            if (shark.count == 1) {
                shark.size += 1;
                shark.count = shark.size;
            } else {
                shark.count--;
            }
            return true;
        } else {
        	return false;
        }
    }

       
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        fishList = new ArrayList<>();
        time = 0;
        
        
        StringTokenizer st;
        for (int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0; c<N; c++) {
                map[r][c] =    Integer.parseInt(st.nextToken());
                if (1<=map[r][c] && map[r][c]<=6) {
                    fishList.add(new Fish(r, c, map[r][c]));
                }
                
                if (map[r][c] == 9) {
                    shark = new Shark(r, c, 2, 2);
                    map[r][c] = 0;
                }
            }
        }

        Collections.sort(fishList);
        
        while(true) {
            // 상어가 물고기를 잡는 로직
        	boolean isOk = findTarget();
        	
        	if (!isOk) break;
        }
        
        System.out.println(time);
    }
}