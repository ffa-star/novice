package 实际问题;

//使用栈来求解一个表达式
public class PlusStack {

//	思路：
//	 1. 先用一个指针遍历表达式
//	2. 使用两个栈，一个存放数字，另外一个存放表示式
//	3. 扫描到数字就直接存放
//	4. 扫描到符号，如果栈为空，就直接放入，否则和栈顶的表达式进行比较。如果优先级小于等于栈顶元素，则从数栈中取出两个元素，从符号栈中取出一个元素，进行运算，将结果写入数栈中，再将符号写入符号栈中
//	5. 当扫描完毕后，就顺序地取出符号和数字进行运算。
//	5. 最后数字栈中只有最后一个元素。就是结果。
	public static void main(String[] args) {
		String expression = "300+2*6-2";
		
		//创建两个栈，一个存放数字，一个存放符号栈
		ArrayStack numStack = new ArrayStack(10);
		ArrayStack operStack = new ArrayStack(10);
		
		//定义需要的相关变量
		int index = 0;   //用于扫描
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;
		char ch = ' ';  //将每次扫描得到的char保存到ch
		String keepNum = "";  //用于拼接多位数
		//开始while循环扫描expression
		while(true) {
			//一次得到expression的每一个字符
//			ch = expression.substring(index,index+1).charAt(0);
//			System.out.println(ch);
			ch = expression.charAt(index);   //自己的代码
			//substring是从index开始截取，不包括index+1
			//判断ch是什么，然后做相应的处理
			if(operStack.isOper(ch)) {  //如果是运算符
				//判断当前符号栈是否为空
				if(operStack.isEmpty()) {
					operStack.push(ch);
				}else if(operStack.priority(ch)<= operStack.priority(operStack.getTop())){
					//如果扫描到的运算符的优先级比栈顶的优先级低，则从数字展中取出两个，从符号栈中取出一个进行运算，然后把新的运算符放入到符号栈中
					//因为扫描到的是不为空的符号，所以数字展中肯定有两个以上的数字。两个数字间夹杂一个符号
					num1 = numStack.pop();
					num2 = numStack.pop();
					//运算
					res = operStack.cal(num1, num2, operStack.pop());
					//把结果和操作符入栈
					numStack.push(res);
					operStack.push(ch);
					
				}else {  //优先级没有栈顶的高，就存入
					operStack.push(ch);
				}
				
			}else {  //不是运算符，是数字，但因为是字符，所以要转换成数字.而且这里还要再向后扫描，万一是一个多位数。
//				numStack.push(ch-48);
//				拼接多位数
				keepNum+=ch;
				
				//如果ch已经是expression的最后一位，直接入栈
				if(index == expression.length()-1) {
					numStack.push(Integer.parseInt(keepNum));
				}
				
				//判断下一个字符是不是数字，继续扫描，如果是运算符，则入栈
				else {
					if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))) {
				
					//如果是运算符，则入栈
					numStack.push(Integer.parseInt(keepNum));
					//清空keepNum
					keepNum = "";
					}
				}
				
			}
			index++;
			if(index >= expression.length()) {  
				//到最后了
				break;
			}
		}
//		5. 当扫描完毕后，就顺序地取出符号和数字进行运算。
//		5. 最后数字栈中只有最后一个元素。就是结果。
		while(true) {
			//如果符号栈为空，则计算到最后结果，数栈中只有一个数字
			if(operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			//运算
			res = operStack.cal(num1, num2, operStack.pop());
			//把结果和入栈
			numStack.push(res);
			
			
		}
		//数字栈中最后一个就是结果
		System.out.println(numStack.pop());
		
		

	}

}

class ArrayStack{
	private int top = -1;
	private int[] array;
	private int maxsize;
	
	public ArrayStack(int maxsize) {
		this.maxsize = maxsize;	
		array = new int[maxsize];
	}
	
	public void push(int n) {
		if(!isFull()) {
			top++;
			array[top] = n;
		}else {
			System.out.println("栈已满");
		}
	}
	
	public int pop() {
		if(!isEmpty()) {
//			System.out.println(array[top]);
			
			return array[top--];
		}else {
			throw new RuntimeException("栈为空");
		}
	}
	
	//遍历栈
	public void list() {
		if(!isEmpty()) {
			int n = top;
			System.out.println(array[n]);
			n--;
		}
	}
	
	//返回表头元素
	public int getTop() {
		return array[top];
	}
	
	//返回运算符的优先级
	//优先级使用数字表示，数字越大，优先级越高  只有+-*/
	public int priority(int oper) {
		if(oper == '*' || oper =='/') { //char的底层也是数字
			return 1;
		}else if(oper == '+' || oper == '-') {
			return 0;
		}else {
			return -1;
		}
	}
	
	//判断是否是一个操作符
	public boolean isOper(char val) {
		return val =='+' ||val =='-' ||val =='*' ||val =='/' ;
	}
	
	//计算方法
	public int cal(int num1, int num2, int oper) {
		int res = 0;  //用于存放计算结果
		switch(oper) {
		case '+':
			res = num1+num2;
			break;
		case '-':
			res = num2-num1;  //注意顺序，num2是后弹出来的，也就是在运算时中是前面一个
			break;
		case '*':
			res = num2*num1;
			break;
		case '/':
			res = num2/num1;
			break;
		}
		return res;
	}
	
	public boolean isEmpty(){
		return top == -1;
	}
	
	public boolean isFull() {
		return top == maxsize-1;
	}
	
	
}
