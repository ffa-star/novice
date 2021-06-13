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
}
