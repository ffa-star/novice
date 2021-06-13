package 实际问题;

public class Josephus {

	public static void main(String[] args) {
		CircleSingleLinkList list = new CircleSingleLinkList();
		Boy first = list.add(5);
//		list.list(first);
		list.countBoy(1,2,5);

	}

}

class CircleSingleLinkList{
	//生成第一个指针first
	private Boy first = null;
	Boy cur = null;
	//生成环 num代表有多少个节点
	public Boy add(int num) {
		if(num < 1) {
			System.out.println("no");
			return null;
		}
		for(int i = 1; i <= num; i++) {
			Boy boy = new Boy(i);
			
			if(i == 1) {
				first = boy;
				boy.setNext(first);
				cur = boy;
				
			}else {
				cur.setNext(boy);
				boy.setNext(first);
				cur = boy;
//				System.out.println(cur.getNext());

			}
		}
		return first;
	}
	
	//打印环
	
	public void list(Boy first) {
		Boy cur = first;
		if(cur.getNext() == cur) {
			System.out.println(cur);
			return;
		}
		while(true) {
			//先遍历cur，再判断她的下一个是否是first。
			System.out.println(cur);
			
			if(cur.getNext() == first) {
				break;
			}
			cur = cur.getNext();
		}
	}
	
	/**
	 * 
	 * @param startNo  	表示从第几个小孩开始数
	 * @param countNum	表示数几下
	 * @param nums		表示最初有多少个小孩
	 */
	public void countBoy(int startNo, int countNum, int nums) {
		//先对数据进行校验
		if(startNo > nums || startNo < 1 ||first == null) {
			System.out.println("error");
			return;
		}
		
		//建立一个辅助指针，因为这里相当于删除一个节点，所以helper相当于删除节点的前驱节点。
		Boy helper = first;
		//让这个helper节点指向最后一个节点
		while(helper.getNext()!=first) {
			helper = helper.getNext();
		}
		
		//小孩报数前，先让两个节点移动k-1次
		for(int i = 0; i < startNo-1;i++) {
			first = first.getNext();
			helper = helper.getNext();
			
		}
		
		//当小孩报数时，让first和helper同时移动m-1次
		while(true) {
			if(helper == first) {
				break;
			}
			for(int i = 1; i <= countNum-1; i++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			//这个时候first指向的就是需要删除的节点
			System.out.println(first);
			first = first.getNext();
			helper.setNext(first);
			
		}
		//输出最后一个
		System.out.println(first);
	
	}
	
	
}

class Boy{
	private int no;
	private Boy next;
	public Boy(int no) {
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Boy getNext() {
		return next;
	}
	public void setNext(Boy next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return "Boy [no=" + no + "]";
	}
	
	
}
