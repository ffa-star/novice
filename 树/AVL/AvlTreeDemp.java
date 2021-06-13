package 树.AVL;

public class AvlTreeDemp {

	public static void main(String[] args) {
		int[] arr = { 9, 8, 7, 4 };
		AVLTree avl = new AVLTree();
		for (int i = 0; i < arr.length; i++) {
			avl.add(new Node(arr[i]));
		}
		System.out.println(avl.getRoot());
		
	}

}

//创建AVL树
class AVLTree {
	private Node root;

	// 添加节点
	public void add(Node node) {
		if (root == null) {
			root = node;
		} else {
			root.add(node);
		}
		
		
	}
	
	public Node getRoot() {
		return root;
	}

	public void infixOrder() {
		if (root != null) {
			root.infixOrder();
		} else {
			System.out.println("二叉排序树为空");
		}
	}

}

class Node {
	int value;
	Node left, right;

	public Node(int value) {
		this.value = value;
	}
	
	//有旋转
	private void rightRotate() {
		Node newNode = new Node(value);
		newNode.right = right;
		newNode.left = left.right;
		value = left.value;
		left = left.left;
		right = newNode;
	}
	
	

	//左旋转方法
	private void leftRotate() {
		//创建新的节点，以当前根节点的值
		Node newNode = new Node(value);
		//把新的节点的左子树设置成当前节点的左子树
		newNode.left = left;
		//把新的节点的右子树设置成当前节点右子树的左子树
		newNode .right = right.left;
		//把当前节点的值替换成右子节点的值
		value = right.value;
		//把当前节点的右子树设置成右子树的右子树
		right.right = right.right;
		//把当前节点的左子树设置成新的节点
		left = newNode;
	}
	
	
	
	// 返回左子树的高度
	public int leftHeight() {
		if (left == null) {
			return 0;
		} else {
			return left.height();
		}
	}

	// 返回右子树的高度
	public int rightHeight() {
		if (right == null) {
			return 0;
		} else {
			return right.height();
		}
	}

	// 返回当前节点的高度，以该结点为根节点的高度
	public int height() {
		return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1
				;
	//这里不懂	可以用下面的代码
//		public int getHeight(TreeNode root){
//		if(root==null){
//			return 0;
//		}
//		int leftheight=getHeight(root.left);
//		int rightheight=getHeight(root.right);
//		return Math.max(leftheight, rightheight)+1;
//	}

	}

	// 添加节点 递归的形式添加，需要满足二叉排序树的要求
	public void add(Node node) {
		if (node == null) {
			return;
		}
		// 判断传入的节点的值和当前的子树根结点的值的关系
		if (node.value < this.value) { // this是这棵子树的根节点
			// 如果当前节点左子节点为空
			if (this.left == null) {
				this.left = node;
			} else {
				this.left.add(node);
			}

		} else {// 添加的节点大于等于当前节点的值
			if (this.right == null) {
				this.right = node;
			} else {
				this.right.add(node);
			}
		}
		//当添加玩一个节点后，如果（右子树的高度-左子树的高度）》1
		if(rightHeight() - leftHeight() > 1) {
			//如果他的右子树的左子树的高度大于他的右子树的右子树的高度，
			if(right != null && right.rightHeight() < right.leftHeight()) {
				//先对他的右子树进行右旋转
				right.rightRotate();
				//然后对他进行坐旋转
				this.leftRotate();
			}
			return;
		}else if(leftHeight() - rightHeight() >1) {
			//如果他的左子树的右子树的高度大与她的左子树的高度
			if(left != null && left.rightHeight() > left.leftHeight()) {
				//先对当前节点的左节点（左子树）-》 左旋转
				left.leftRotate();
				//再对当前节点进行右旋转
				this.rightRotate();
			}else {
				//直接进行右旋转
				this.rightRotate();
			}
		}
	}

	

	// 中序遍历
	public void infixOrder() {
		if (this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

}