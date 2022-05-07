package practice.datastruc.graph;
 
import java.util.Iterator;
import java.util.LinkedList;
 
public class EulerCircuit {
	public static void main(String[] args) throws java.lang.Exception {
		EulerCircuit g = new EulerCircuit(5);
		g.addEdge(1, 0);
		g.addEdge(0, 2);
		g.addEdge(2, 1);
		g.addEdge(0, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 0);

		if (g.isEulerianCycle())
			System.out.println("给定的有向图中 存在 欧拉回路");
		else
			System.out.println("给定的有向图中 不存在 欧拉回路");
	}

	private int V; // 顶点数量
	private LinkedList<Integer> adj[];// 邻接表

	private int in[]; // 维护入度

	EulerCircuit(int v) {
		V = v;
		adj = new LinkedList[v];
		in = new int[V];
		for (int i = 0; i < v; ++i) {
			adj[i] = new LinkedList();
			in[i] = 0;
		}
	}

	void addEdge(int v, int w) {
		adj[v].add(w);
		in[w]++;
	}

	void DFSUtil(int v, Boolean visited[]) {
		// 将当前节点标记为已访问
		visited[v] = true;

		int n;

		// 对与此顶点相邻的所有顶点遍历
		Iterator<Integer> i = adj[v].iterator();
		while (i.hasNext()) {
			n = i.next();
			if (!visited[n])
				DFSUtil(n, visited);
		}
	}
	// 返回此图的反转（或转置）

	EulerCircuit getTranspose() {
		EulerCircuit g = new EulerCircuit(V);
		for (int v = 0; v < V; v++) {
			// 对与此顶点相邻的所有顶点遍历
			Iterator<Integer> i = adj[v].listIterator();
			while (i.hasNext()) {
				g.adj[i.next()].add(v);
				(g.in[v])++;
			}
		}
		return g;
	}
	// 如果图是强连接的，则返回true

	Boolean isSC() {
		// Step 1: 将所有顶点标记为未访问（对于第一个DFS）
		Boolean visited[] = new Boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		// Step 2: 从第一个顶点开始进行DFS遍历.
		DFSUtil(0, visited);

		// 如果DFS遍历没有访问所有顶点，则返回false.
		for (int i = 0; i < V; i++)
			if (visited[i] == false)
				return false;

		// Step 3: 创建一个反转图
		EulerCircuit gr = getTranspose();

		// Step 4: 将所有顶点标记为未访问（对于第二个DFS）
		for (int i = 0; i < V; i++)
			visited[i] = false;

		// Step 5: 从第一个顶点开始对反转图执行DFS.
		// 起始顶点必须与第一个DFS的起始点相同
		gr.DFSUtil(0, visited);

		// 如果在第二个DFS中未访问所有顶点，则返回false
		for (int i = 0; i < V; i++)
			if (visited[i] == false)
				return false;

		return true;
	}
	/*
	 * 如果有向图有欧拉回路，则此函数返回true； 否则返回false
	 */

	Boolean isEulerianCycle() {
		// 检查是否连接了所有非零度顶点
		if (isSC() == false)
			return false;

		// 检查每个顶点的进出度是否相同
		for (int i = 0; i < V; i++)
			if (adj[i].size() != in[i])
				return false;

		return true;
	}

}