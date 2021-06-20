package 排序;

import java.util.Arrays;

public class QuickSortTest {

	public static void main(String[] args) {
		int arr[] = { -8, 5, 6, 3, 7, 9, 1 };
		recQuickSort(arr,0, arr.length-1);
		System.out.println(Arrays.toString(arr));

	}
	public static void recQuickSort(int[] arr, int left, int right) {
		if(right <= left) {
			return;
		}
		
		else{
			int index = quickSort(arr,left,right);
			quickSort(arr,left,index-1);
			quickSort(arr,index+1,right);
		}

	}

	public static int quickSort(int arr[], int left, int right) {
		int i = left;
		int j = right + 1;
		// j+1是因为下面是从--j开始的
		while (true) {
			while (j > 0 && arr[--j] > arr[left]) {   //这边如果=0，就会出现数组越界
			} // 找到比最左边基准数小的位置
			while (i < right && arr[++i] < arr[left]) {
			} // 找到比最左边的基准数大的位置
			
			if (i >= j) {
				// 已经找到基准数了;
				break;
			}
			swap(arr, i, j);
		}
		
		//将基准数和游标最后一次的位置交换。也就是将基准数放中间
		swap(arr,left,j);
		
		return j;

	}

	public static void swap(int[] arr, int i, int j) {
		// 交换元素
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	public static void quickSort2(int[] arr, int l, int r) {
		if(l <r) {
			//随机选择一个位置，然后和最后一个数做交换
			swap(arr,l+(int)Math.random()*(r-l+1),r);
			int[] p = partition(arr,l,r);
			//p数组只包含两个值，也就是划分好的数组的相等部分的左边界和右边界。
			quickSort(arr,l,p[0]-1);  //<区
			quickSort(arr,p[1]+1,r);  //>区
		}
	}
	
	//这是一个处理arr[l..r]的函数
	//默认以arr[r]做划分， arr[r] -》 p  得到三个部分  <p  ==p  >p;
	//返回等于区域（左边界，右边界），也就是数组长度为2
	private static int[] partition(int[] arr, int l, int r) {
		int less = l-1;   //<区右边界  它是从左往右逼近
		int more = r;     //>区左边界
		while(l < more) {
			if(arr[l] < arr[r]) {   //l表示当前数的位置， arr[r]划分值
				swap(arr,++less,l++);   //把右边界的下一个数和这个数交换
				
			}else if(arr[l] > arr[r]) {
				swap(arr,--more,l);
			}else {
				l++;
			}
		}
		Integer x = Integer.MAX_VALUE;
		swap(arr,more,r);   //将最后一个数和>区第一个数交换
		return new int[] {less+1,more};
	}
	
}
