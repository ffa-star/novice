package 树.二叉排序树;

public class BinarySortTreeDemo {

	public static void main(String[] args) {
		int[] arr = { 7, 3, 10, 12, 5, 1, 9 };
		BinarySortTree bst = new BinarySortTree();
		for (int i = 0; i < arr.length; i++) {
			bst.add(new Node(arr[i]));
		}
		bst.infixOrder();
		bst.delNode(3);
		bst.infixOrder();
	}

}

class BinarySortTree {
	private Node root;

	// 添加节点
	public void add(Node node) {
		if (root == null) {
			root = node;
		} else {
			root.add(node);
		}
	}

	public void infixOrder() {
		if (root != null) {
			root.infixOrder();
		} else {
			System.out.println("二叉排序树为空");
		}
	}

	// 查找要删除的节点
	public Node search(int value) {
		if (root == null) {
			return null;
		} else {
			return root.search(value);
		}
	}

	// 查找父节点
	public Node searchParent(int value) {
		if (root == null) {
			return null;
		} else {
			return root.searchParent(value);
		}
	}

	public int delLeftTreeMax(Node node) {
		Node temp = node;
		// 循环查找有节点
		while (temp.right != null) {
			temp = temp.right;
		}
		int value = temp.value;
		delNode(temp.value);
		return value;
	}

	/**
	 * 返回以node根节点的二叉排序树的最小节点的值 删除以node为根节点的二叉排序树的最小节点
	 * 
	 * @param node 传入的节点，当做二叉树的根节点
	 * @return 以node为根节点的二叉排序树的最小节点的值
	 */
	public int delRightTreeMin(Node node) {
		Node temp = node;
		// 循环查找左节点，就会找到最小值
		while (temp.left != null) {
			temp = temp.left;
		}
		// 这时候temp就指向了最小节点
		delNode(temp.value);
		return temp.value;
	}

	// 删除节点
	public void delNode(int value) {
		if (root == null) {
			return;
		} else {
			// 1. 需求先去找到需要删除的节点 targetNode
			Node targetNode = search(value);
			if (targetNode == null) { // 没有找打要删除的节点
				return;
			}
			// 下面表示找到了
			// 如果这棵二叉排序树只有一个节点
			if (root.left == null && root.right == null) {
				root = null;
				return;
			}

			// 找父节点
			Node parent = searchParent(value);
			// 如果是叶子节点
			if (targetNode.left == null && targetNode.right == null) {
				// 判断targetNode是父节点的左子节点还是右子节点
				if (parent.left != null && parent.left.value == value) {
					// 左子节点
					parent.left = null;
				} else if (parent.right != null && parent.value == value) {
					parent.right = null;
				}
			} else if (targetNode.left != null && targetNode.right != null) {
				// 有两棵子树 删除右子树的最小的，返回把值作为根节点的值
				int min = delRightTreeMin(targetNode.right);
				targetNode.value = min;

			} else {
				// 只有一棵子树
				// 先判断targetNode唯一一棵子树在左边还是右边，然后判断targetNode是parent的做还是右节点
				// 1. 如果targetNode有左子节点
				if (targetNode.left != null) {
					// 如果targetNode是parent的左子节点
					if (parent != null) {
						if (parent.left.value == value) {
							parent.left = targetNode.left;
						} else {
							// 是父节点的右子节点
							parent.right = targetNode.left;
						}
					} else {
						root = targetNode.left;
					}

				} else {// 要删除的节点只有右子节点
					if (parent != null) {
						if (parent.left.value == value) {
							parent.left = targetNode.right;
						} else {
							// 是父节点的右子节点
							parent.right = targetNode.right;
						}
					}else {
						root = targetNode.right;
					}
				}
			}

			
		}
	}
}

class Node {
	int value;
	Node left, right;

	public Node(int value) {
		this.value = value;
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
	}

	// 查找要删除的节点
	/**
	 * 
	 * @param value
	 * @return 返回找到的节点，否则返回null
	 */
	public Node search(int value) {
		if (value == this.value) {
			return this;
		} else if (value < this.value) {
			if (this.left == null) {
				return null;
			}
			return this.left.search(value);
		} else {
			if (this.right == null) {
				return null;
			}
			return this.right.search(value);
		}
	}

	// 查找要删除节点的父节点
	public Node searchParent(int value) {
		if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		} else {
			// 如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
			if (value < this.value && this.left != null) {
				return this.left.searchParent(value);
			} else if (value >= this.value && this.right != null) {
				return this.right.search(value);
			} else {
				return null; // 没有找到父节点
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
