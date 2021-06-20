package 查找;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearch {

	public static void main(String[] args) {
		int arr[] = { -1, 2, 6, 9, 5, 3, 8, 6 };
		int[] arr2 = {1,1,2,3,5,8,10};
		System.out.println(nearstIndex(arr2,0));

//		System.out.println(binarySearch(arr, 0, arr.length - 1, 0));

	}

	public static int binarySearch(int[] arr, int left, int right, int findVal) {
		int mid = (left + right) / 2;
		int midVal = arr[mid];
		if (left > right) {
			return -1;
		}

		if (findVal > midVal) {
			return binarySearch(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) {
			return binarySearch(arr, left, mid - 1, findVal);
		} else {
			return mid;
		}

	}

// 找到所有的数的索引
//	在找到mid索引值，先向左扫描得到所有的下标，加入到集合ArrayList中
//	再向右扫描，得到所有的下标并加入到集合中
//	返回集合
	public static ArrayList binarySearchAll(int[] arr, int left, int right, int findVal) {
		int mid = (left + right) / 2;
		int midVal = arr[mid];
		if (left > right) {
			return new ArrayList<Integer>();
		}

		if (findVal > midVal) {
			return binarySearchAll(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) {
			return binarySearchAll(arr, left, mid - 1, findVal);
		} else {
			//找到了
			ArrayList list = new ArrayList<Integer>();
			int temp = mid - 1;
			while (true) {
//				temp为索引
				if (temp < 0 || arr[temp] != findVal) {
					break;
				}
				list.add(temp);
				temp--;
			}
			list.add(arr[mid]);

			temp = mid + 1; // 向右边扫描
			while (true) {
				if (temp > arr.length - 1 || arr[temp] != findVal) {
					break;
				}
				list.add(temp);
				temp++;
			}
			return list;

		}

	}
	
	//在arr上，找到满足 》=value 的最左侧位置
	public static int nearstIndex(int[] arr, int value) {
		int l = 0, r = arr.length-1, index = -1;
		while(l <= r) {
			int mid = l+((r-l) >> 1);
			if(arr[mid] >= value) {
				index = mid;
				r = mid-1;
			}else {
				l = mid+1;
			}
		}
		return index;
		
	}
	
	
	//在arr上，找到《=value的最右侧的位置
	public static int farIndex(int[] arr, int value) {
		int l = 0, r = arr.length-1, index = -1;
		while( l <= r) {
			int mid = l +(r-l) >> 1;
			if(arr[mid] <= value) {
				index = mid;
				l = mid+1;
			}else {
				r = mid-1;
			}
		}
		return index;
	}
	
	
	//局部最小   左边的比他大，右边的也比他大。利用二分法。只要能够舍去一边的结果，就可以用二分法
	public static int getLessIndex(int[] arr) {
		if(arr == null || arr.length == 0) {
			return -1;
		}
		if(arr.length == 1 || arr[0] <arr[1]) {
			return 0;
		}
		if(arr[arr.length-1] < arr[arr.length-2]) {
			return arr.length-1;
		}
		
		int left = 1, right = arr.length-2, mid = 0;
		while(left < right) {
			mid = (left+right)>>1;
			if(arr[mid]> arr[mid-1]) {
				right = mid-1;
			}else if(arr[mid] > arr[mid+1]){
				left = mid+1;
			}else {
				//既比左边小，又比右边小。
				return mid;
			}
		}
		return left;
	}
	
	
	

}
