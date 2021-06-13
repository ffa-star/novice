package 实际问题;

//用链表模拟栈
//思路：
//添加节点的时候从最后加。
//定义一直指针指向最后一个节点
//取出节点的时候从这个指针后移。
public class LinkStack {

	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		SingleLinkList list = new SingleLinkList(5);
		list.push(node1);
		list.push(node2);
		list.push(node3);
		list.push(node4);
		list.push(node5);
		list.list();
//		list.pop();
//		list.pop();
//		list.pop();
//		list.pop();
//		list.pop();
//		list.pop();
//		list.list();

	}

}

class SingleLinkList{
	private int maxsize;  //最大容量
	private Node top = null;
	private Node pre = null;
	private Node head = null;
	private int num;		//当前节点数量
	
	public SingleLinkList(int maxsize) {
		this.maxsize = maxsize;
		head = new Node(0);
	}
	//添加节点
	public void push(Node node) {
		if(!isFull()) {
			Node temp = head;
			while(temp.next != null) {
				temp = temp.next;
			}
			
			temp.next = node;
			top = node;
			num++;
		}else {
			System.out.println("栈已满");
		}
	}
	
	//取出节点
	public void pop() {
		if(!isEmpty()) {
			//取出top节点
			//或者这里直接遍历到最后一个节点
			Node temp = head;
			while(temp.next.next != null) {
				temp = temp.next;
			}
			System.out.println(temp.next);
			temp.next = null;
			
		}else {
			System.out.println("栈空");
		}
	}
	
	public boolean isEmpty() {
		return head.next == null;
	}
	
	public boolean isFull() {
		return num == maxsize;
	}
	
	//遍历栈   反转链表 然后输出
	public void list() {
		if(!isEmpty()) {
			Node temp = head.next;
			Node pre = null;
			Node curnext = null;
			while(true) {
				if(temp == null) {
					//遍历完了
					break;
				}
//				System.out.println(pre);
				curnext = temp.next;
				temp.next = pre;
				pre = temp;
				temp = curnext;
//				System.out.println(temp);
			}
			Node temp2 = pre;
			while(true) {
				if(temp2 == null) {
					break;
				}
				System.out.println(temp2);
				temp2 = temp2.next;
				
			}
		}else {
			System.out.println("栈空");
		}
		
	}
	
}

class Node{
	int no;
	Node next;
	public Node(int no) {
		this.no = no;
	}
	public Node() {
		
	}
	@Override
	public String toString() {
		return "Node [no=" + no + "]";
	}
	
}
