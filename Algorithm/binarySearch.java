package Algorithm;

public class binarySearch {

	public static void main(String[] args) {
		int[] a = {1,2,3,5,9};
		System.out.println(binarySearch(a,9 ));

	}
	
	//二分查找非递归实现
	/**
	 * 
	 * @param arr  待查找数组
	 * @param target	需要查找的 数
	 * @return 下标
	 */
	public static int binarySearch(int[] arr, int target) {
		int left = 0, right = arr.length-1;
		while(left <= right) {
			int mid = left+(right-left)/2;
			if(target == arr[mid]) {
				return mid;
			}else if(target >arr[mid]) {
				left = mid+1;
			}else {
				right = mid -1;
			}
		}
		return -1;
	}

}
