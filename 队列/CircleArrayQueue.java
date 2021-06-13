package 队列;

public class CircleArrayQueue {

	public static void main(String[] args) {
		CircleQueue c = new CircleQueue(3);
		c.addQueue(1);
		c.addQueue(2);
		c.listQueue();
		c.getQueue();
		c.addQueue(5);
		

	}

}
class CircleQueue{
	private int front,rear,maxsize;
	private int[] arr;
	
	public CircleQueue(int maxsize) {
		this.maxsize = maxsize;
		arr = new int[maxsize];
		front = 0;		//指向队列头部，front指向数组中第一个数据
		rear = 0;		//指向队列列尾，指向队列尾的数据的下一个位置
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return front == rear;
	}
	
	//判断队列是否满
	public boolean isFull() {
		return (rear+1)%maxsize==front;
	}
	
	//添加数据
	public void addQueue(int n) {
		if(isFull()) {
			System.out.println("队列已满");
			return;
		}
		
		arr[rear] = n;
		rear = (rear+1)%maxsize;  //将rear指向下一个空的位置。如果指向队尾，且front=0，下一次加入数据时就会显示队列已满。
		
	}
	
	//取出数据
	public int getQueue() {
		//判断队列是否为空
		if(isEmpty()) {
			//因为这边需要返回一个int类型的数据，但是为空返回不了，不能返回-1等（怕被当做数据处理），所以抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
		
		int n = arr[front];
		front = (front+1) %  maxsize;
		return n; 
	}
	
	//求出当前队列中有效数据的个数
	public int size() {
		return (rear+maxsize-front)%maxsize;
	}
	
	//显示队列的所有数据,从front开始遍历，遍历多少个元素
	
	public void listQueue() {
		if(isEmpty()) {
			System.out.println("队列为空");
			return;
		}
		for(int i = front; i < front+size(); i++) {
			System.out.printf("arr[%d]=%d\n", i&maxsize,arr[i%maxsize]);
		}
	}
	
	//显示队列的头数据，不是取出数据
	public int headQueue() {
		if(isEmpty()) {
			
			throw new RuntimeException("队列为空");
		}
		return arr[front];  //注意，这里不能是++。
	}
}

