package 树.线索二叉树;


public class ThreadBinaryTree {

	public static void main(String[] args) {
		System.out.println(0^1);

	}

}


//实现线索化功能的二叉树
class BinaryTree{
	private TreeNode root;
	//为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
	private TreeNode pre = null;
	
	public void setTreeNode(TreeNode root) {
		this.root = root;
	}
	
	
	//遍历中序线索化二叉树
	public void  therdList() {
		//定义一个变量，存储当前遍历的节点，从root开始
		TreeNode node = root;
		while(node != null) {
			//循环找到lefttype = 1的节点，第一个找到的就是最左边的节点
			//后面随着遍历而变化，因为当lefttype=1时，说明该节点是按照线索化处理后的有效节点.或者说这个节点是叶子节点了。
			while(node.getLeftType()==0) {
				node = node.getLeft();
			}
			//打印当前节点
			System.out.println(node);
			//如果当前节点的右指针指向的是后继节点，就一直输出
			while(node.getRightType() == 1) {
				//当前node的右指针指向的节点就是后继节点，输出
				node = node.getRight();
			}
			//替换遍历的节点
			node = node.getRight();
			
			
		}
	}
	
	
	//编写对二叉树进行中序线索化的方法
	/**
	 * 
	 * @param node 就是当前需要线索化的节点
	 */
	public void threadNodes(TreeNode node) {
		if(node == null) {
			return;
		}
		
		//1. 先线索化左子树
		threadNodes(node.getLeft());
		//2. 线索化当前节点
			//处理当前节点的前驱节点
		if(node.getLeft() == null) {
			//让当前节点的左指针指向前驱节点。
			node.setLeft(pre);
			//修改左指针的类型
			node.setLeftType(1);
		}
			//处理后继节点
		if(pre != null && pre.getRight() == null) {
			//让前驱节点的右指针指向当前节点
			pre.setRight(node);
			pre.setRightType(1);
		}
		//每处理一个节点后，让当前节点是下一个节点的前驱节点。
		pre = node;
		
		//3. 线索化右子树
		threadNodes(node.getRight());
		
		
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




class TreeNode{
	private int no;
	private String name;
	private TreeNode left,right;
	private int leftType, rightType;
	//如果leftType == 0， 表示指向的是左子树，如果是1，表示指向前驱节点。
	//rightType是1，指向后继节点
	
	
	
	public int getNo() {
		return no;
	}
	public int getLeftType() {
		return leftType;
	}
	public void setLeftType(int no) {
		this.leftType = no;
	}
	public int getRightType() {
		return rightType;
	}
	public void setRightType(int no) {
		this.rightType = no;
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
