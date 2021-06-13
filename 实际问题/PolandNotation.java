package 实际问题;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

	public static void main(String[] args) {
		String mid = "1+((2*3)*4)-5";
		//将一个中缀表达式转换为后缀表达式的功能
//		1+((2*3)*4)-5 => 1 2 3 + 4 * + 5 -
//		说明：
//		因为直接对str进行操作不方便，因此先将中缀表达式拆分，放入到list中。
		List<String> list = MidExpressionList(mid);
//		System.out.println("shixing");
		List<String> list2 = toBackExpression(list);
		System.out.println(list2);
		
//		将得到的中缀表达式的list 转换成后缀表达式的list。
		
		
		
//实现逆波兰表达式的计算
//		先定义一个中缀表达式(3+4)*5-6 ==> 3 4 + 5 * 6 -
		String expression = "300 4 + 5 * 6 -";
//		思路：
//		  1. 先将表达式放入到ArrayList中
//		  2. 将ArrayList 传递给一个方法，遍历ArrayList 配合栈，完成计算
		
//		List<String> expList = getListString(expression);
//		System.out.println(calc(expList));

	}
//将中缀表达式转成对应的list
	public static List<String> MidExpressionList(String s){
		List<String> ls = new ArrayList<>();
		int i = 0; //这是一个指针，用来遍历字符串
		String str;  //做对多位数的拼接工作
		char c;  //每遍历一个字符，放入到c中
		do {
			//如果c是一个非数字，加入到ls中
			if((c = s.charAt(i)) < 48 ||(c = s.charAt(i)) > 57 ) {
				//符号直接加入list中
				ls.add(""+c);   //把它转换成字符串
				i++;
			}
			else {
				//考虑多位数的问题
				str = "";  //先将str 清空
				while(i <s.length() && (c = s.charAt(i)) > 48 &&(c = s.charAt(i)) < 57)
				{
					str+=c; //拼接
					i++;
				}
				ls.add(str);
			}
		}while(i < s.length());
		return ls;
	}
	
//	将得到的中缀表达式的list 转换成后缀表达式的list。
	public static List<String> toBackExpression(List<String> ls){
		//定义两个栈
		Stack<String> s1 = new Stack<String>();   //符号栈
		//中间的结果栈s2在整个转换过程中没有pop操作，而且后面还要逆序输出，这里用list集合
		List<String> resultList = new ArrayList<>();
		
		//遍历ls
		for(String ss: ls) {
			//如果是一个数，就入栈
			if(ss.matches("\\d+")) {
				resultList.add(ss);
			}else if(ss.equals("(") || s1.isEmpty()){
				//入符号栈
				s1.push(ss);
			}else if(ss.equals(")")){
				//一次弹出s1栈顶的所有运算符，压入s2中，知道遇到左括号为止，此时这个括号丢弃
				while(!s1.peek().equals("(")) {
					resultList.add(s1.pop());
				}
				//将左括号丢弃
				s1.pop();
				
			}else {  //符号
				//当ss的优先级小于等于s1栈顶运算符的优先级，将s1栈顶的运算符弹出加入到s2中，继续与新栈顶运算符比较
				while(s1.size()!= 0 &&Operation.getValue(s1.peek())>= Operation.getValue(ss)) {
					resultList.add(s1.pop());
				}
				s1.push(ss);
			}
		}
		
		//将s1剩余的运算符依次弹出并压入s2
		while(s1.size()!= 0) {
			resultList.add(s1.pop());
		}
		
		return resultList;
				
	}

	
	
//	将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
	public static List<String> getListString(String expression){
		//将expression按照空格进行分隔
		String[] split = expression.split(" ");
		List<String> list = new ArrayList<>();
		for(String ele:split) {
			list.add(ele);
		}
		return list;
	}
	
//	完成对逆波兰表达式的运算
	/*
	 * 1. 从左至右扫描
	 * 2. 遇到运算符，弹出两个数字，
	 * 3. 进行运算，将得到的结果入栈
	 * 
	 * 
	 * */
	
	public static int calc(List<String> ls) {
		//创建栈，只需要一个栈即可，因为只有数字
		Stack<String> stack = new Stack<String>();
		//遍历ls
		for(String item:ls) {
			//使用正则表达式
			if(item.matches("\\d+")) {   //匹配的是多位数
				//入栈
				stack.push(item);
			}else {  //是运算符
				//pop出两个数，并运算，再入栈
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;
				if(item.equals("+")) {
					res = num1+num2;
				}else if(item.equals("-")) {
					res = num1-num2;
				}else if(item.equals("*")) {
					res = num1*num2;
				}else if(item.equals("/")) {
					res = num1/num2;
				}
				stack.push(String.valueOf(res));
			}
			
		}
		//留在栈中的就是结果
		return Integer.valueOf(stack.pop());
	}

}

//编写一个类，返回一个运算符对应的优先级
class Operation{
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 1;
	private static int DIV = 1;
	
	//写一个方法，返回对应的优先级
	public static int getValue(String operation) {
		int result = 0;
		switch(operation) {
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "*":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
//			System.out.println(operation);
			System.out.println("不存在该运算符");  //这里左括号会出现这句话
			break;
		}
	return result;
	}
	
}
