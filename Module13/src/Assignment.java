public class Assignment {

    public static void main(String[] args) {

        int V = 5;
        TransitiveClosureMatrix g = new TransitiveClosureMatrix(V);

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(1, 3);
        g.addEdge(3, 4);

        boolean[][] tc = g.closure();

        System.out.println("Transitive closure matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(tc[i][j] ? "1 " : "0 ");
            }
            System.out.println();
        }
    }
}

class TransitiveClosureMatrix {

    private final int V;
    private final boolean[][] adj;

    public TransitiveClosureMatrix(int V) {
        this.V = V;
        this.adj = new boolean[V][V];
    }

    public void addEdge(int u, int v) {
        adj[u][v] = true;
    }

    /**
     * Compute and return the V*V transitive closure matrix after adding edges
     * tc[i][j] == true if there is a path from i to j
     */
    public boolean[][] closure() {
        boolean[][] transitiveClosure = new boolean[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                transitiveClosure[i][j] = adj[i][j];
            }
        }
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                if (!transitiveClosure[i][k]) {
                    continue;
                }
                for (int j = 0; j < V; j++) {
                    transitiveClosure[i][j] = transitiveClosure[i][j]
                            || (transitiveClosure[i][k] && transitiveClosure[k][j]);
                }
            }
        }
        return transitiveClosure;
    }
}
