package 队列;

public class ArrayQueue {

	public static void main(String[] args) {
		Queue q = new Queue(5);
		q.addQueue(1);
		q.addQueue(5);
		q.listQueue();
		System.out.println(q.headQueue());

	}

}

class Queue{
	private int front,rear,maxsize;
	private int[] arr;
	
	public Queue(int maxsize) {
		this.maxsize = maxsize;
		arr = new int[maxsize];
		front = -1;		//指向队列头部，front指向数组中数据的前一个位置
		rear = -1;		//指向队列列尾，指向队列尾的数据（最后一个数据）
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return front == rear;
	}
	
	//判断队列是否满
	public boolean isFull() {
		return rear == maxsize-1;
	}
	
	//添加数据
	public void addQueue(int n) {
		if(isFull()) {
			System.out.println("队列已满");
			return;
		}
		rear++;  //将rear指向下一个空的位置。
		arr[rear] = n;
		
	}
	
	//取出数据
	public int getQueue() {
		//判断队列是否为空
		if(isEmpty()) {
			//因为这边需要返回一个int类型的数据，但是为空返回不了，不能返回-1等（怕被当做数据处理），所以抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
		front++; 
		return arr[front];
	}
	
	//显示队列的所有数据
	public void listQueue() {
		if(isEmpty()) {
			System.out.println("队列为空");
			return;
		}
		for(int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]=%d\n", i,arr[i]);
		}
	}
	
	//显示队列的头数据，不是取出数据
	public int headQueue() {
		if(isEmpty()) {
			
			throw new RuntimeException("队列为空");
		}
		return arr[front+1];  //注意，这里不能是++。
	}
}
