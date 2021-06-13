package 哈希表;

public class HashTableDemo {

	public static void main(String[] args) {
		//创建一个哈希表
		HashTab hashTab = new HashTab(7);
		Emp emp1 = new Emp(1,"1");
		Emp emp2 = new Emp(2,"2");
		Emp emp3 = new Emp(3,"3");
		Emp emp4 = new Emp(3,"3");
		hashTab.add(emp1);
		hashTab.add(emp2);
		hashTab.add(emp3);
//		hashTab.add(emp3);
		hashTab.findEmpById(3);
		
		hashTab.list();
		
		
		

	}

}
//创建哈希表，管理多条链表
class HashTab{
	private EmpLinkedList[] empLinkedListArray;
	private int size;  //表示共有多少条链表
	
	public HashTab(int size) {
		//初始化链表
		empLinkedListArray = new EmpLinkedList[size];
		for(int i = 0; i < size; i++) {
			empLinkedListArray[i] = new EmpLinkedList();
		}
		this.size = size;
	}
	//添加雇员
	public void add(Emp emp) {
		//根据员工的id，得到该员工应该添加到哪条链表
		int empLinkedListNo = hashFun(emp.id);
		//将emp添加到对应的链表中
		empLinkedListArray[empLinkedListNo].add(emp);
	}
	
	//遍历哈希表（所有的链表）
	public void list() {
		for(int i = 0; i < size; i++) {
			empLinkedListArray[i].list(i);
		}
	}
	
	//根据id查找雇员
	public void findEmpById(int id) {
		//使用散列函数确定到哪条链表查找
		int no = hashFun(id);
		Emp emp = empLinkedListArray[no].findEmpById(id);
		if(emp == null) {
			System.out.println("no");
		}else {
			System.out.println("查找到的雇员为"+no+" "+emp);
		}
	}
	
	
	
	//编写一个散列函数，使用一个简单的取模法
	public int hashFun(int id) {
		return id % size;
	}
	
}


//创建EmpLinkedList,表示链表
class EmpLinkedList{
	//头指针，指向第一个节点，这个链表的head是有效的。直接指向第一个雇员
	private Emp head;  //默认为空
	
	//添加雇员到链表
	//假定当添加雇员时，id是自增长，即id的分配总是从小到大。
	//因此将该雇员直接加入到本链表的最后即可。
	public void add(Emp emp) {
		//如果添加第一个雇员
		if(head == null) {
			head = emp;
			return;
		}
		//不是第一个雇员
		//使用辅助指针
		Emp temp = head;
		while(true) {
			if(temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		
		//此时temp为空
		temp.next = emp;
	}
	
	//遍历雇员信息
	public void list(int no) {
		if(head == null) {
			System.out.println(no+" 链表为空");
			return;
		}
		Emp temp = head;
		while(temp != null) {
			System.out.println(no+" "+temp);
			temp = temp.next;
		}
	}
	
	//根据id查找雇员
		public Emp findEmpById(int id) {
			if(head == null) {
				return null;
			}
			Emp temp = head;
			while(true) {
				if(temp == null) {
					break;
				}
				if(temp.id == id) {
					return temp;
				}
				temp = temp.next;
			}
			return null;
		}
	
	public EmpLinkedList() {
		
	}
	
}





class Emp{
	//雇员节点
	public int id;
	public String name;
	Emp next;
	public Emp(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + "]";
	}
	
	
}
