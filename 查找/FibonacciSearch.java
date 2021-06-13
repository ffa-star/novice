package 查找;

import java.util.Arrays;

public class FibonacciSearch {

	public static int maxSize = 20;
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}
	
	//得到20个元素的斐波那契数列
	public static int[] fib() {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for(int i = 2; i < maxSize; i++) {
			f[i] = f[i-1] + f[i-2];
		}
		return f;
	}
	
	//查找(非递归）
	public static int fibSearch(int[] a, int key) {
		int low = 0;
		int high = a.length-1;
		int k = 0; //表示斐波那契分割数值的下标
		int mid = 0;
		int f[] = fib();
		//获取到斐波那契分割数值的下标
		while(high > f[k]-1) {
			k++;
		}
		
		//因为f[k]值可能大于a的长度，因此需要使用Arrays类，构造一个新的数组，并指向a[]
		//不足的部分会使用0填充
		int[] temp = Arrays.copyOf(a, f[k]);       //f[k]表示长度
		//实际上需要使用a数组的最后的数填充temp
		for(int i = high+1; i < temp.length; i++) {
			temp[i] = a[high];
		}
		
		//使用while循环，进行查找
		while(low <= high) {
			mid = low + f[k-1]-1;   //low是要加上前面的位置，如果此时到右边查找了，就派上用场了。
			if(key < temp[mid]) {
				//继续向数组的前面查找  左边  因为前面是f[k-1] ,所以k要变成k-1
				high = mid - 1;
				k = k-1;
				
				//k--说明
//				1. 全部元素=前面的元素+后面的元素
//				2. f[k] = f[k-1] + f[k-2];
//				因为前面有f[k-1]个元素，所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
//				即在f[k-1]的前面继续查找 k--.。
			}else if(key > temp[mid]) {
				low = mid + 1;
				k-=2;
			}else {
				//找到了
				if(mid <= high) {
					return mid;
				}else {
					return high;
				}
			}
		}
		return -1;  //没找到
	}
}
