package 树;

public class ArrBinaryTreeDemo {

	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7};
		//创建一个数
		ArrBinaryTree abt = new ArrBinaryTree(arr);
		abt.preOrder(0);  //就是把数组元素按照前序的方式输出
	}

}

//编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree{
	private int[] arr;   //存储数据节点的数组
	
	public ArrBinaryTree(int[] arr) {
		this.arr = arr;
	}
	
	//编写一个方法，完成顺序存储二叉树的前序遍历 就是把数组元素按前序遍历的方式输出
	/**
	 * 
	 * @param index 数组的下标
	 */
	public void preOrder(int index) {
		//如果数组为空，或者arr.length = 0
		if(arr == null || arr.length == 0) {
			System.out.println("数组为空");
		}
		
		System.out.println(arr[index]);  //输出当前元素
		//向左递归遍历,就是找到下一个左边的节点。序号为2*index+1
		if((index*2+1)<arr.length) {
			preOrder(2*index+1);
		}
		//向右递归遍历
		if((index*2+2)<arr.length) {
			preOrder(2*index+2);
		}
	}
}
