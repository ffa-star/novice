package 树;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

	public static void main(String[] args) {
		int arr[] = {13,7,8,3,29,6,1};
		
		Node node = createHuffmanTree(arr);
		node.preOrder();

	}
	
	//创建赫夫曼树的方法
	public static Node createHuffmanTree(int[] arr) {
		//第一步：为了操作方便，先遍历arr数组，然后将arr的每个元素构建成node
		//将node放入到ArrayList中
		List<Node> nodes = new ArrayList<>();
		for(int i = 0; i < arr.length; i++) {
			nodes.add(new Node(arr[i]));
		}
		
		while(nodes.size()>1) {
			//排序 从小到大  因为node实现了compareable接口，所以这里可以sort
			Collections.sort(nodes); 
	
			//取出根节点权值最小的两棵二叉树
			Node leftNode = nodes.get(0);
			//取出第二小的节点
			Node rightNode = nodes.get(1);
			
			//构建一棵新的二叉树
			Node parent = new Node(leftNode.value+rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;
			
			
			//从ArrayList中删除处理过的两个二叉树
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			
			
			//将parent加入到nodes汇总
			nodes.add(parent);
			
			Collections.sort(nodes);
	//		System.out.println(nodes);
		}
		
		//返回数的头
		return nodes.get(0);
	}

}

//创建节点类
//因为这里对象需要排序
class Node implements Comparable<Node>{
	int value;
	Node left, right;
	
	//前序遍历
	public void preOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.preOrder();
		}
		if(this.right != null) {
			this.right.preOrder();
		}
	}
	
	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	
	public int compareTo(Node o) {
		//表示从小到大排序
		return this.value - o.value;
	}
	
	
}