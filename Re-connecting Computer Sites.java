import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static class Link implements Comparable<Link> {
        int x, y, v;

        public Link(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }

        @Override
        public int compareTo(Link other) {
            return this.v - other.v;
        }
    }

    static int[] parent, rank;

    static void makeInit(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    static int findParent(int x) {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x]);
        }
        return parent[x];
    }

    static void pLink(int x, int y) {
        if (rank[x] > rank[y]) {
            rank[x] += rank[y];
            parent[y] = x;
        } else {
            rank[y] += rank[x];
            parent[x] = y;
        }
    }

    static int union(int x, int y) {
        x = findParent(x);
        y = findParent(y);
        if (x != y) {
            pLink(x, y);
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxN = 1000001;

        while (scanner.hasNextInt()) {
            int N = scanner.nextInt();
            int K, M, a, b, c;
            int flag = 0;

            Link[] D = new Link[maxN];

            int sum = 0;
            for (int i = 1; i < N; i++) {
                a = scanner.nextInt();
                b = scanner.nextInt();
                c = scanner.nextInt();
                sum += c;
            }

            if (flag != 0) {
                System.out.println();
            }
            flag = 1;
            System.out.println(sum);

            K = scanner.nextInt();
            for (int i = 0; i < K; i++) {
                D[i] = new Link(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            }

            M = scanner.nextInt();
            for (int i = 0, j = K; i < M; i++, j++) {
                D[j] = new Link(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            }

            M = K + M;
            sum = 0;

            Arrays.sort(D, 0, M);

            makeInit(N);

            for (int i = 0; i < M; i++) {
                sum += union(D[i].x, D[i].y) * D[i].v;
            }

            System.out.println(sum);
        }
    }
}
