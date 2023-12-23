import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static final int MAX_NODE = 9;
    private static final int INF = Integer.MAX_VALUE;

    private static final int[] dx = {2, 2, -2, -2, 1, -1, 1, -1};
    private static final int[] dy = {1, -1, 1, -1, 2, 2, -2, -2};

    private static final int WHITE = 0;
    private static final int GRAY = 1;

    private static Queue<Pair> q = new ArrayDeque<>();
    private static int[][] color = new int[MAX_NODE][MAX_NODE];
    private static int[][] dist = new int[MAX_NODE][MAX_NODE];

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void BFS(int y, int x) {
        for (int i = 1; i < MAX_NODE; i++) {
            for (int j = 1; j < MAX_NODE; j++) {
                dist[i][j] = INF;
            }
        }

        for (int i = 0; i < MAX_NODE; i++) {
            for (int j = 0; j < MAX_NODE; j++) {
                color[i][j] = WHITE;
            }
        }

        color[y][x] = GRAY;
        dist[y][x] = 0;
        q.add(new Pair(y, x));

        while (!q.isEmpty()) {
            Pair u = q.poll();
            int uy = u.x;
            int ux = u.y;

            for (int i = 0; i < 8; i++) {
                int vx = ux + dx[i];
                int vy = uy + dy[i];

                if (vx >= 1 && vy >= 1 && vx <= 8 && vy <= 8 && color[vy][vx] == WHITE) {
                    color[vy][vx] = GRAY;
                    dist[vy][vx] = dist[uy][ux] + 1;
                    q.add(new Pair(vy, vx));
                }

            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String st = scanner.next();
            String en = scanner.next();

            int srcy = st.charAt(0) - 'a' + 1;
            int srcx = Character.getNumericValue(st.charAt(1));
            int desty = en.charAt(0) - 'a' + 1;
            int destx = Character.getNumericValue(en.charAt(1));

            BFS(srcy, srcx);
            System.out.println("To get from " + st + " to " + en + " takes " + dist[desty][destx] + " knight moves.");
        }
    }
}
