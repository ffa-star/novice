package 实际问题;

public class Queue8 {

//	定义一个max，表示共有多少个皇后
	int max = 4;
//	定义数组array，保存皇后放置位置的结果，比如 arr = {0,4,7,5,2,6,1,3}
	int[] array = new int[max];
	static int count = 0;  //计算有多少种算符

	public static void main(String[] args) {
		Queue8 queue = new Queue8();
		queue.check(0);
		System.out.println("一共有"+count+"种解法");
		
	}

	
	//编写方法，放置第n个皇后
	private void check(int n) {
		if(n == max) {  //n = 8, 因为n从0开始，所以相当于放第九个皇后。所以八个皇后已经放好了。
			print();
			return;
		}
		
		//依次放入皇后，并判断是否冲突  每一行都会调用8次check；每一个check都有8次for循环 
		for(int i = 0; i < max; i++) {
			//先把当前皇后n，放到该行的第一列。
			array[n] = i;
			//判断当放置n个皇后到第i列时，是否冲突
			if(judge(n)) {//不冲突
				//接着放n+1个皇后，开始递归
				check(n+1);
			}
			//如果冲突，就继续执行array[n] = i; 但此时i已经+1了。就将第n个皇后放置在本行的后移的一个位置。
		}
	}
	
	
	
	// 查看当我们放置第n个皇后时，去检测该皇后是否和前面已经摆放的皇后冲突。
	/**
	 * 
	 * @param n 表示第n个皇后
	 * @return
	 */
	private boolean judge(int n) {
		for (int i = 0; i < n; i++) {
			// 说明
//			1.  因此数组存放的值是列，所以不需要判断行
//			array[i] == array[n]表示判断n和前面的n-1个皇后是否在同一列上。
//			Math.abs(n-i) == Math.abs(array[n] - array[i] 判断是否在同一斜线上
//			行可以看做是x, 列是y 同一斜线就是 y = x; 这里看的是斜率。

			if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
				return false;
			}
		}
		return true;
	}

	// 写一个方法，可以将皇后摆放的位置输出
	private void print() {
		count++;
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
			
		}
		System.out.println();
	}

}
