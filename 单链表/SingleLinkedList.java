package 单链表;

import java.util.Stack;

public class SingleLinkedList {

	public static void main(String[] args) {
		// 先创建几个节点
		Node node1 = new Node(1, "宋江");
		Node node2 = new Node(2, "玉麒麟");
		Node node3 = new Node(3, "吴用");
		Node node4 = new Node(4, "宋江");
		Node node5 = new Node(5, "玉麒麟");
		Node node6 = new Node(6, "吴用");
		Node node7 = new Node(7, "吴用");
		SingleLinkList list = new SingleLinkList();
		SingleLinkList list2 = new SingleLinkList();
		list.addByOrder(node2);
		list.addByOrder(node1);
		list.addByOrder(node7);
		list.addByOrder(node3);
		System.out.println("list");
		list.list();
		System.out.println("2");
		list2.addByOrder(node4);
		list2.addByOrder(node1);
		list2.addByOrder(node5);
		list2.addByOrder(node6);
		list2.list();
		System.out.println("=--");
		SingleLinkList list3 = SingleLinkList.combineTwoList(list,list2);
		list3.list();
//		list.list();
//		list.addByOrder(node1);
//		list.list();
		
		//修改结点
//		Node newNode2 = new Node(5, "卢俊义");
//		list.updateByNo(newNode2);
		
		//删除结点
//		list.delete(node7);
//		list.list();
//		System.out.println(SingleLinkList.getLength(list));
//		System.out.println("-----");
//		//反转节点
//		SingleLinkList.reverseList(list);
//		list.list();
//		
//		System.out.println("--------------");
//		//逆序打印单链表
//		SingleLinkList.reversePrint(list);
//		System.out.println("----------");
//		SingleLinkList list3 = SingleLinkList.combineTwoList(list, list2);
//		list3.list();
//		

	}

}

//定义一个SingleLinkedList来管理节点
class SingleLinkList {
	// 先初始化一个头节点，头节点先不要动。 //不存放具体的数据
	private Node head = new Node();

	// 添加节点到链表(最后),先遍历这个节点，然后将最后一个节点的next指向新结点
	public void add(Node node) {
		// 因为头节点，不能动（不能移动到指向最后一个节点），因此需要一个辅助变量。
		Node temp = head;
		// 遍历链表，找到最后
		while (temp.next != null) {
//			System.out.println("添加的temp"+temp);
			temp = temp.next;
		}
		// 找到最后一个链表，添加数据。
		temp.next = node;
	}

	// 第二种方式，添加节点，通过节点的no按顺序插入(如果有这个排名，则添加失败）
	public void addByOrder(Node node) {
		// 因为头节点不能动，因此需要一个辅助指针
		// 找到的temp是位于添加位置的前一个节点
		Node temp = head;
		boolean flag = false; // flag标志添加的编号是否存在，默认为false。
		while (true) {
			if (temp.next == null) {
				// 说明temp已经在链表的最后了
				break;
			}
			if (temp.next.no > node.no) {
				// 位置找到了，在temp和temp.next之间

				break;
			} else if (temp.next.no == node.no) {
				// 说明希望添加的数据no已经存在了
				flag = true;
				break;
			}
			temp = temp.next;
		}

		// 判断flag的值
		if (flag) {
			// 不能添加，编号存在
			System.out.println("准备插入的no已经存在，不能插入。" + node.no);
		} else {
			node.next = temp.next;
			temp.next = node;
		}
	}

	// 根据给定的结点的no来修改链表中的节点的值
	public void updateByNo(Node node) {
		Node temp = head;
		boolean flag = false;  //判断是否找到
		while (true) {
			if (temp == null) {
				break;
				// 表示找到末尾了
			}
			if (temp.no == node.no) {
				// 找到了
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {
			temp.name = node.name;
		}else {
			System.out.println("没有找到该"+node.no+"结点");
		}
	}
	
	//删除结点
	public void delete(Node node) {
		//1. 需要一个辅助的变量
//		2. 找到需要删除结点的前一个节点
//		3. temp.next = temp.next.next
//		4.被删除的结点将会没有其他节点引用，会被垃圾回收机制删除
		
		Node temp = head;
		
		boolean flag = false;
		while(true) {
			if(temp.next == null) {
				//没找到
				break;
			}
			if(temp.next.no == node.no) {
				//找到了
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {
			temp.next=temp.next.next;
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
		
	}

	// 遍历列表
	public void list() {

		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}

		Node temp = head.next;
		while (true) {
			// 判断是否到最后
			if (temp == null) {
				break;
			}
			// 输出节点信息
			System.out.println(temp);
			temp = temp.next;
		}

		// 或者
//		while(temp != null) {
//			System.out.println(temp);
//			temp = temp.next;
//		}
	}
	
//	查询有效节点个数（不包括头结点）
	public static int getLength(SingleLinkList list) {
		Node temp = list.head;
		int count = 0;
		if(temp.next == null) {
			
			return 0;
		}
		while(true) {
			if(temp == null) {
				break;
			}
			count++;
			temp = temp.next;
		}
		return count;
		
	}
//	查找单链表中倒数第k个节点
//	1. 首先得到它的节点总长度
//	2. 计算倒数第k个节点为正数第几个。正数第几个 = 总长度-倒数长度+1；
//	3. 遍历
	
	public static Node findLastIndexNode(SingleLinkList list, int index) {
		//如果链表为空，则返回null
		if(list.head.next == null) {
			return null;
		}
		
		//得到链表的长度
		int size = list.getLength(list);
		
		//判断index的合理性（这里就不写了）
		
		
		//遍历查询.从头结点开始，比如一共有3个节点（不包括头结点），那么需要从首元节点开始，.next size-index次。这里的size-index可以理解为和第一个的间隔
		Node temp = list.head.next;
		for(int i = 0; i < size-index; i++) {
			temp = temp.next;
		}
		return temp;
		
	}
	
//	单链表的反转，就是节点之间交换位置。
//	1. 定义一个节点，作为反转的头。 node reversehead = new node();
//	2. 遍历原来的链表，取出每一个节点，放在新链表的最前端。
//	3. 原来的链表的head.next = reversehead.next;
	public static void reverseList(SingleLinkList list) {  //这里的参数也可以是一个头节点。
		
		Node temp = list.head.next;
		Node temp2 = null;
		//如果链表为空,或者只有有一个节点，返回原来的链表
		if(list.head.next == null || list.head.next.next==null) {
			return;
		}
		
		Node reverseHead = new Node();
		while(temp != null) {
			temp2 = temp.next; //保存下一个节点，不然后面赋值后节点会断掉。
			temp.next = reverseHead.next ;
			reverseHead.next = temp;
//			System.out.println(temp);
			
			temp = temp2;
		}
		list.head.next = reverseHead.next;
	}
	
//	单链表的反转方法二
	public static void reverseList2(SingleLinkList list) {
		while(list.head.next != null || list.head.next.next!= null) {
			return;  //没有节点或者只有一个节点
		}
		
		Node cur = list.head.next;
		Node pre = null;
		Node curNext = null;
		while(cur != null) {
			curNext = cur.next;  //先把下一个节点保存起来，不然就没了
			cur.next= pre;
			pre = cur;			//这里就相当于把cur赋值给pre，然后next下移
			cur = cur.next;
		}
		
		
	}
	
	
	//逆序打印链表
//	方式一：先把链表反转，然后打印
//	方式二：利用栈的先进后出原理，将节点存入栈中，然后弹出。
//	这里采用方式二
	public static void reversePrint(SingleLinkList list) {
		Stack<Node> stack = new Stack<>();
		//遍历链表
		Node temp = list.head.next;
		if(temp == null) {
			System.out.println("链表为空");
			return;
		}
		//把元素压栈
		while(temp != null) {
			stack.push(temp);
			temp = temp.next;
		}
		//将栈中的节点打印
		while(stack.size()>0) {
			System.out.println(stack.pop());
		}
	}

//	合并两个有序的单链表，合并之后依然有序
	public static SingleLinkList combineTwoList(SingleLinkList list1,SingleLinkList list2) {
		//定义一个新的头结点
		//比较两个链表的大小，谁小就加谁。
		//返回这个头节点
		
		//第二种方式是遍历list2，然后有序加入list1中。
		Node temp1 = list1.head.next;
		Node temp2 = list2.head.next;
		
//		System.out.println("temp2.next"+temp2.next.next);
		SingleLinkList list3 = new SingleLinkList();
		if(temp1 == null) {
			return list2;
		}
		if(temp2 == null) {
			return list1;
		}
		while(temp1 != null && temp2 != null) {
			if(temp1.no  <= temp2.no) {
				Node tempNext = temp1.next;
				temp1.next = null;
				System.out.println(temp1);
				list3.add(temp1);
				temp1 = tempNext; 
				System.out.println(temp1);
				
			}else {
				Node temp3 = temp2.next;
				System.out.println("temp3"+temp3);
				temp2.next = null;
				System.out.println(temp2);
				list3.add(temp2);
				temp2 = temp3;
				System.out.println(temp2);
//				continue;
			}
		}
		if(temp1 == null) {
			list3.add(temp2);
		}else {
			list3.add(temp1);
		}
		return list3;
	}
	
	public static SingleLinkList combineTwoList2(SingleLinkList list1,SingleLinkList list2) {
		//定义一个新的头结点
		//比较两个链表的大小，谁小就加谁。
		//返回这个头节点
		
		//第二种方式是遍历list2，然后有序加入list1中。
		Node temp1 = list1.head.next;
		Node temp2 = list2.head.next;
		
//		System.out.println("temp2.next"+temp2.next.next);
		SingleLinkList list3 = new SingleLinkList();
		Node cur = list3.head;
		if(temp1 == null) {
			return list2;
		}
		if(temp2 == null) {
			return list1;
		}
		while(temp1 != null && temp2 != null) {
			if(temp1.no  <= temp2.no) {
				cur.next = temp1;
				temp1 = temp1.next;
				cur = cur.next;
			}
		} 
	
}



//定义一个节点，每一个节点都是一个对象
class Node {
	public int no;
	public String name; // 类里面的数据
	public Node next; // 指向下一个节点

	public Node(int no, String name) {
		this.name = name;
		this.no = no;
	}

	public Node() {
	}

	// 为了显示方便，重写tostring
	@Override
	public String toString() {
		return "Node [no=" + no + ", name=" + name + "]";
	}

}
