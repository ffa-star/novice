package 查找;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearch {

	public static void main(String[] args) {
		int arr[] = { -1, 2, 6, 9, 5, 3, 8, 6 };

		System.out.println(binarySearch(arr, 0, arr.length - 1, 0));

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

}
