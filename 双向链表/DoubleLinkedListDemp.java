package 双向链表;



public class DoubleLinkedListDemp {

	public static void main(String[] args) {
		Node node1 = new Node(1,"宋1江");
		Node node2 = new Node(2,"宋2江");
		Node node3 = new Node(3,"宋3江");
		Node node4 = new Node(4,"宋4江");
		Node node5 = new Node(4,"宋5江");
		DoubleLinkList list = new DoubleLinkList();
		list.add(node1);
		list.add(node2);
		list.add(node3);
		list.add(node4);
//		list.list();
		list.update(node5);
//		list.list();
		list.delete(node5);
		list.list();
		

	}

}

class DoubleLinkList{
	//先初始化一个头节点
	private Node head = new Node(0,"");
	
	//返回头节点
	public Node getHead() {
		return head;
	}
	
	//遍历双向链表
	public void list() {
		
		Node temp = this.head.next;
		if(temp == null) {
			
			System.out.println("链表为空");
			return;
		}
		while(temp != null) {
			System.out.println(temp);
			temp = temp.next;
		}
	}
	
	//增加节点
	public void add(Node node) {
		Node temp = head;
		while(temp.next != null) {
			temp = temp.next;
		}
		temp.next = node;
		node.pre = temp;
		
	}
	
	//修改一个节点的内容
	public void update(Node node) {
		//先判断是否为空
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		Node temp = head.next;
		while(true) {
			if(temp == null) {
				//没找到
				System.out.println("不存在");
				return;
			}
			if(temp.no == node.no) {
				temp.name = node.name;
				return;
			}
			temp = temp.next;
		}
	}
	
	
	//删除一个节点
	public void delete(Node node) {
		//判断为空
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		Node temp = head.next;
		while(true) {
			if(temp == null) {
				System.out.println("没有找到");
				return;
			}
			if(temp.no == node.no) {
				//找到了，删除这个节点
				//将这个节点的下一个给上一个的next
				temp.pre.next = temp.next ;
				
				//将下一个节点的pre给上一个节点
				//如果这个节点是最后一个节点，则不需要执行这段话
				if(temp.next != null) {
					temp.next.pre = temp.pre;
				}
				return;
			}
			temp = temp.next;
			
		}
	}
	
	
	
	
	
}

class Node {
	public int no;
	public String name; // 类里面的数据
	public Node next; // 指向下一个节点
	public Node pre;  //指向前一个节点

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

