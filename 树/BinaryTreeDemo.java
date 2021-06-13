package 树;

public class BinaryTreeDemo {

	public static void main(String[] args) {
		// 创建一棵二叉树
		BinaryTree bt = new BinaryTree();
		//创建节点
		TreeNode root = new TreeNode(1,"宋江");
		bt.setTreeNode(root);
		TreeNode node1 = new TreeNode(2,"左1");
		TreeNode node2 = new TreeNode(3,"右1");
		TreeNode node3 = new TreeNode(4,"右2");
		
		//二叉树可以递归方式创建二叉树
		root.setLeft(node1);
		root.setRight(node2);
		node2.setRight(node3);
		//前序遍历
		bt.preOrder();
		//中序遍历
//		System.out.println("中序");
//		bt.infixOrder();
		//后续编写
//		System.out.println("后序遍历");
//		bt.postOrder();
		
//		System.out.println("前序查找");
//		System.out.println(bt.postOrderSearch(5));
		bt.delete(3);
		bt.preOrder();

	}

}

//定义二叉树
class BinaryTree{
	private TreeNode root;
	public void setTreeNode(TreeNode root) {
		this.root = root;
	}
	
	
	//前序遍历
	public void preOrder() {
		if(this.root != null)
		{
			this.root.preOrder();
		}else {
			System.out.println("二叉树为空，无法遍历");
		}
	}
	
	//中序遍历
	public void infixOrder() {
		if(this.root != null) {
			this.root.infixOrder();
		}else {
			System.out.println("二叉树为空，无法遍历");
		
		}
	}
	
	public void postOrder() {
		if(this.root != null)
		{
			this.root.postOrder();
		}else {
			System.out.println("二叉树为空，无法遍历");
		}
	}
	
	
	//前序遍历查找
	public TreeNode preOrderSearch(int no) {
		if(root != null) {
			return root.postOrderSearch(no);
		}
		return null;
	}
	
	public TreeNode infixOrderSearch(int no) {
		if(root != null) {
			return root.infixOrderSearch(no);
		}
		return null;
	}
	public TreeNode postOrderSearch(int no) {
		if(root != null) {
			return root.postOrderSearch(no);
		}
		return null;
	}
	
	//删除节点
	public void delete(int no) {
		if(root != null) {
			//如果只有一个root节点，判断root是否是要删除的节点
			if(root.getNo() == no) {
				root = null;
			}else {
				root.delete(no);
			}
		}else {
			System.out.println("空树，不能删除");
		}
	}
	
}


//定义树节点
class TreeNode{
	private int no;
	private String name;
	private TreeNode left,right;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	public TreeNode(int no, String name) {
		this.no = no;
		this.name = name;
	}
	@Override
	public String toString() {
		return "TreeNode [no=" + no + ", name=" + name + "]";
	}
	
	
	//节点的方法
	//编写前序遍历的方法
	public void preOrder() {
		System.out.println(this); //先输出父节点   因为这个方法是由一个节点调用的，所以this指向的就是那个节点
		//递归向左子树前序遍历
		if(this.left != null) {
			this.left.preOrder();
		}
		//递归右子树前序遍历
		if(this.right != null) {
			this.right.preOrder();
		}
	}
	
	//中序遍历
	public void infixOrder() {
		//首先想左子树中序遍历
		if(this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		//递归右子树
		if(this.right != null) {
			this.right.infixOrder();
		}
	}
	
	//后续遍历
	public void postOrder() {
		if(this.left != null) {
			this.left.postOrder();
		}
		if(this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}
	
	//前序遍历查找
	public TreeNode preOrderSearch(int no) {
		//比较当前节点是不是
		if(this.no == no) {
			return this;
		}
		
		//1.不是则判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
		//找到则返回
		TreeNode resNode = null;
		if(this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if(resNode != null) {
			//左子树找到
			return resNode;
		}
		//下面是没找到的情况，此时resNode还是为空
		
		//判断是否有右子节点，如果有，则向右递归
		if(this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}
	
	//中序遍历查找
	public TreeNode infixOrderSearch(int no) {
		TreeNode resNode = null;
		//判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
		if(this.left != null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		
		//没有找到，就和当前节点比较  因为前面都是left并没有和this比较
		if(this.no == no) {
			return this;
		}
		
		//没有找到，想右递归
		if(this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
	}
	
	//后续遍历查找
	public TreeNode postOrderSearch(int no) {
		//判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
		TreeNode resNode = null;
		if(this.left != null) {
			resNode = this.left.postOrderSearch(no);
		}
		if(resNode != null) {   //左子树找到
			return resNode;
		}
		if(this.right !=null) {  //左子树没有找到，右子树递归遍历查找
			resNode = this.right.postOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		
		//如果左右子树都没有找到，比较当前节点
		if(this.no == no) {
			return this;
		}
		return resNode;
	}
	
	//递归删除节点
	public void delete(int no) {
		
		//判断左子节点是否为空并且删除
		if(this.left != null && this.left.no == no) {
			this.left =null;
			return;
		}
		
		//判断右子节点
		if(this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		
		//左子树递归删除
		if(this.left != null) {
			this.left.delete(no);
		}
		
		//右子树递归
		if(this.right != null) {
			this.right.delete(no);
		}
		
		
	}
	
}
