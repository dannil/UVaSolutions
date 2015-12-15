import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	private List<Edge> edges;
	private String[] map;
	private HashMap<Point, Integer> hash;

	private int[] parent;
	private int[] level;

	private int getSolutionForCase() {
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length(); j++) {
				if (this.map[i].charAt(j) == 'S' || this.map[i].charAt(j) == 'A') {
					Node source = new Node(i, j, 0);
					BFS(source, this.edges);
				}
			}
		}

		this.parent = new int[this.hash.size()];
		this.level = new int[this.hash.size()];

		for (int i = 0; i < this.parent.length; i++) {
			this.parent[i] = i;
			this.level[i] = 1;
		}

		// Sort edges on Edge's compare(), which is the weight
		Collections.sort(this.edges);

		// MST Kruskal
		int cost = 0;
		int N = 0;
		for (Edge e : this.edges) {
			if (N >= this.parent.length - 1)
				return cost;
			if (find(e.a) != find(e.b)) {
				union(find(e.a), find(e.b));
				cost += e.weight;
				N++;
			}
		}
		return cost;
	}

	// Union
	private void union(int a, int b) {
		if (a == b) {
			return;
		}

		if (this.level[a] > this.level[b]) {
			this.parent[b] = a;
		} else {
			this.parent[a] = b;
			if (this.parent[a] == this.parent[b]) {
				this.level[b]++;
			}
		}

	}

	// Find
	private int find(int i) {
		if (this.parent[i] == i) {
			return i;
		}

		this.parent[i] = find(this.parent[i]);
		return this.parent[i];
	}

	private class Node {

		int i;
		int j;
		int k;

		public Node(int i, int j, int k) {
			this.i = i;
			this.j = j;
			this.k = k;
		}

	}

	private class Point {

		int i;
		int j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public int hashCode() {
			return 52 * 52 * this.i + 52 * this.j;
		}

		@Override
		public boolean equals(Object arg0) {
			Point s = (Point) arg0;
			// TODO Auto-generated method stub
			return this.i == s.i && this.j == s.j;
		}
	}

	private class Edge implements Comparable<Edge> {

		int a;
		int b;
		int weight;

		public Edge(int a, int b, int weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge other) {
			// Compare weights
			return this.weight - other.weight;
		}

	}

	public void BFS(Node source, List<Edge> edges) {
		final int[] dx = { -1, 0, 1, 0 };
		final int[] dy = { 0, -1, 0, 1 };

		Queue<Node> queue;
		boolean[][] visited;

		queue = new LinkedList<Node>();
		visited = new boolean[this.map.length + 1][this.map[0].length() + 1];

		// Add the starting node
		queue.add(source);
		visited[source.i][source.j] = true;

		while (!queue.isEmpty()) {
			Node node = queue.poll();

			// Checks every direction and tries to create edges
			for (int d = 0; d < 4; d++) {
				int X = node.i + dx[d];
				int Y = node.j + dy[d];

				if (X < 0 || Y < 0 || X > this.map.length || Y > this.map[0].length()) {
					continue;
				}
				if (visited[X][Y]) {
					continue;
				}
				if (this.map[X].charAt(Y) == '#') {
					continue;
				}

				// Add the newly visited node to our matrix, and add it to our
				// queue
				visited[X][Y] = true;
				queue.add(new Node(X, Y, node.k + 1));

				if (this.map[X].charAt(Y) == 'S' || this.map[X].charAt(Y) == 'A') {
					Point a = new Point(source.i, source.j);
					Point b = new Point(X, Y);

					if (!this.hash.containsKey(a)) {
						this.hash.put(a, this.hash.size());
					}
					if (!this.hash.containsKey(b)) {
						this.hash.put(b, this.hash.size());
					}

					// Create the new edge, from our current point (a) to the
					// next (b)
					Edge e = new Edge(this.hash.get(a), this.hash.get(b), node.k + 1);
					edges.add(e);
				}
			}
		}
	}

	public void run() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int testCases = Integer.parseInt(reader.readLine());

		for (int i = 0; i < testCases; i++) {
			String[] rowContents = reader.readLine().split(" ");
			int J = Integer.parseInt(rowContents[0]);
			int I = Integer.parseInt(rowContents[1]);
			this.map = new String[I];

			for (int j = 0; j < I; j++) {
				this.map[j] = reader.readLine();
			}

			this.hash = new HashMap<Point, Integer>();
			this.edges = new ArrayList<Edge>();

			System.out.println(getSolutionForCase());
		}
	}

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.run();
	}
}