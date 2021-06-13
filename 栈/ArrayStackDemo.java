package 栈;

public class ArrayStackDemo {

	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(5);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.list();
		stack.pop();
		stack.list();
		

	}

}

class ArrayStack{
	private int maxsize;	//最大容量
	private int[] stack;	//用数组表示栈
	private int top = -1;   //栈顶
	
	public ArrayStack(int maxsize) {
		this.maxsize = maxsize;
		stack = new int[maxsize];
	}
	
	//栈满
	public boolean isFull() {
		return top == maxsize-1;
	}
	
	//栈空
	public boolean isEmpty() {
		return top == -1;
	}
	
	//入栈
	public void push(int n) {
		if(!isFull()) {
			top++;
			stack[top] = n;
		}else {
			System.out.println("栈已满");
		}
	}
	
	//出栈
	public int pop() {
		if(!isEmpty()) {
			int val = stack[top];
			top--;
			return val;
		}else {
			throw new RuntimeException("栈空");
		}
	}
	
	//遍历栈  需要从栈顶开始显示数据
	public void list() {
		if(isEmpty()) {
			System.out.println("null");
			return;
		}
		for(int i = top; i>=0; i--) {
			System.out.println(stack[i]);
		}
	}
}
