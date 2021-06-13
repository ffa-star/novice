package 图;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

	private ArrayList<String> vertexList; // 存储顶点
	private int[][] edges; // 存储图对应的邻接矩阵
	private int numOfEdges; // 表示边的数目
	private boolean[] isVisited; // 记录某个节点是否被访问

	public static void main(String[] args) {
		int n = 5; // 节点的个数
		String vertexValue[] = { "A", "B", "C", "D", "E" };
		// 创建图对象
		Graph g1 = new Graph(n);
		for (String value : vertexValue) {
			g1.insertVertex(value);
		}

		// 添加边
		g1.insertEdge(0, 1, 1); // A-B
		g1.insertEdge(0, 2, 1); // A-C
		g1.insertEdge(1, 2, 1); // B-C
		g1.insertEdge(1, 3, 1); // B-D
		g1.insertEdge(1, 4, 1); // B-E
		g1.dfs();

	}

	public Graph(int n) {
		edges = new int[n][n]; // n是顶点的数目
		vertexList = new ArrayList<>();
		numOfEdges = 0; // 变数为0
		isVisited = new boolean[n];
	}

	// 得到第一个邻接节点的下标
	/**
	 * 
	 * @param index 当前节点的下标
	 * @return 如果存在下标，返回下标，否则返回-1
	 */
//	public int getFirstNeighbour(int index) {
//		for (int j = 0; j < vertexList.size(); j++) {
//			if (edges[index][j] > 0) {
//				return j;
//			}
//		}
//		return -1;
//	}
//
//	// 根据前一个邻接节点的下标获取下一个邻接节点
//	public int getNextNeighbour(int v1, int v2) {
//		for (int j = v2 + 1; j < vertexList.size(); j++) {
//			if (edges[v1][j] > 0) {
//				return j;
//			}
//		}
//		return -1;
//	}
//	
//	//深度优先遍历
//	/**
//	 * 
//	 * @param isVisited
//	 * @param i  元素的位置
//	 */
//	public void dfs(boolean[] isVisited, int i) {
//		//首先访问该节点
//		
//		System.out.println(getValueByIndex(i)+ "->");
//		//将该节点设为已经访问过
//		isVisited[i] = true;
//		
//		//查找节点i的第一个邻接节点
//		int w = getFirstNeighbour(i);  //w是节点的索引位置
//		while(w != -1) {
//			if(!isVisited[w]) {
//				//如果没有访问过
//				dfs(isVisited,w);
//			}
//			//如果w节点已经被访问过  可以理解为回溯到原来的节点，需要从那个地方再次出发
//			w = getNextNeighbour(i,w);
//		}
//	}
//	
//	//对dfs进行重载,遍历所有的节点，并进行dfs
//	public void dfs() {
//		//遍历所有的节点进行dfs
//		for(int i = 0; i < getNumOfVertex(); i++) {
//			if(!isVisited[i]) {
//				dfs(isVisited,i);
//			}
//		}
//	}
	
	
	//获得当前节点的下一个邻接节点的索引
	public int getFirst(int i) {
		for(int j = 0; j < vertexList.size(); j++) {
			if(edges[i][j] == 1) {
				return j;
			}
		}
		return -1;
	}
	
	//获得当前节点除了参数中这个节点有边之外的下一个节点的索引
	public int getNext(int v1, int v2) {
		for(int j = v2+1; j < vertexList.size(); j++) {
			if(edges[v1][j] == 1) {
				return j;
			}
		}
		return -1;
	}
	
	//深度遍历    这个方法只对一个节点进行了深度优先遍历
	public void dfs(boolean[] isVisited, int i) {
		System.out.println(getValueByIndex(i)+"->");   //输出节点
		isVisited[i] = true;  //节点已经访问过
		int next = getFirst(i);
		while(next != -1) {
			if(!isVisited[next]) {
				//如果这个节点没有访问过
				dfs(isVisited,next);
			}
			//得到除了这个next节点的下一个节点
			next = getNext(i,next);
		}
	}
	
	//对上面的方法进行重载，循环遍历所有的节点
	public void dfs() {
		for(int i = 0; i < vertexList.size();i++) {
			if(!isVisited[i]) {
				dfs(isVisited,i);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// 常用方法
	// 返回节点个数
	public int getNumOfVertex() {
		return vertexList.size();
	}

	// 返回边的个数
	public int getEdges() {
		return numOfEdges;
	}

	// 返回节点i（下标）对应的数组 0 -》 A
	public String getValueByIndex(int i) {
		return vertexList.get(i);
	}

	// 返回v1和v2的权值
	public int getWeight(int v1, int v2) {
		return edges[v1][v2];
	}

	// 返回图对应的矩阵
	public void showGraph() {
		for (int[] link : edges) {
			System.out.println(Arrays.toString(link));
		}
	}

	// 插入节点
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}

	// 添加边
	/**
	 * 
	 * @param v1           表示点的下标，即第几个顶点 “A" - "B" A 0 , B 1
	 * @param v2第二个顶点对应的下标
	 * @param weight       表示这两个顶点是否关联
	 */
	public void insertEdge(int v1, int v2, int weight) { // weight是1或者0
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}
}
