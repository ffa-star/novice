package 排序;

import java.util.Arrays;

public class MergetSort {

	public static void main(String[] args) {
		int[] arr = { 6, 1, 5, 3, -8, -9, 2 };
		int[] temp = new int[arr.length];
		mergeSort(arr, 0, arr.length - 1, temp);
//		System.out.println(Arrays.toString(arr));

	}

	// 分+合的方法
	public static void mergeSort(int[] arr, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2; // 获取中间值，在下面的递归中mid变成了right或者left。
			// 向左递归分解
			mergeSort(arr, left, mid, temp);
			// 如果left>=right，也即是left>=mid = right 下面的mid+1>= right+1
			// 所以下面的递归是不会执行的，此时会执行合并的代码

//			System.out.println(Arrays.toString(arr));
			// 向右递归分解
//			System.out.println("----");
			mergeSort(arr, mid + 1, right, temp);

//			System.out.println(Arrays.toString(arr));

			// 合并
			merge(arr, left, mid, right, temp);
//			System.out.println(Arrays.toString(arr));
//			System.out.println("left" + left + "right" + right);
		}
	}

	// 合并的方法
	/**
	 * 
	 * @param arr   排序的原始数组
	 * @param left  左边有序序列的初始索引
	 * @param mid   中间索引
	 * @param right 右边数组的最右边的索引
	 * @param temp  做中转的数组
	 */
	public static void merge(int[] arr, int left, int mid, int right, int temp[]) {
		int i = left; // 初始化i，左边有序序列的初始索引
		int j = mid + 1; // 初始化j，右边有序序列的初始索引
		int t = 0; // 指向temp数据的当前索引

		// 第一步
//		先把左右两边的数组按照规则填充到temp数组中
//		直到左右两边的有序序列，有一边处理完毕
		while (i <= mid && j <= right) {
			// 如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素。将这个元素拷贝到temp数组中
			if (arr[i] <= arr[j]) {
				temp[t] = arr[i];
				t += 1;
				i += 1;
			} else {
				// 反之，将右边填充到temp中
				temp[t] = arr[j];
				t++;
				j++;

			}
		}

		// 第二步
//		把有剩余数据的一边全部填充到temp数组中
		while (i <= mid) {
			// 左边的有序序列还有剩余元素
			temp[t] = arr[i];
			t += 1;
			i += 1;
		}

		while (j <= right) {
			temp[t] = arr[j];
			t += 1;
			j += 1;
		}

		// 第三步
//		将temp数组拷贝到arr数组中
//		注意，并不是每次都拷贝过去的，只是考虑当前归并的元素
		t = 0;
		int tempLeft = left;
		while (tempLeft <= right) {
			arr[tempLeft] = temp[t];
			t += 1;
			tempLeft += 1;
		}
	}

	// 求小和问题

	public static int process(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int mid = l + (r - l) / 2;
		return process(arr, l, mid) + process(arr, mid + 1, r) + merge1(arr, l, r, mid);
		// 返回左侧排序求小和的数量，+右侧排序求小和的数量+左右两侧merge的时候小和的数量
	}

	public static int merge1(int[] arr, int l, int r, int m) {
		int[] help = new int[r - l + 1];
		int h = 0, p1 = l, p2 = m + 1;
		int res = 0;
		while (p1 <= m && p2 <= r) {
			// 只有左侧的数组比右侧小，才增加
			res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0; // 个数*左组的数
			help[h++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[h++] = arr[p1++];
		}
		while (p2 <= r) {
			help[h++] = arr[p2++];
		}
		for (int i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
		return res;
	}

	// 归并排序2
	public static void process2(int[] arr, int l, int r) {
		if (l == r) {
			return;
		}
		int mid = l + (r - l) >> 1;
		process(arr, l, mid);
		process(arr, mid + 1, r);
		merge2(arr, l, mid, r);
	}

	public static void merge2(int[] arr, int l, int mid, int r) {
		int[] help = new int[r - l + 1];
		int i = 0;
		int p1 = l, p2 = mid + 1;
		while(p1 <= mid && p2 <= r) {
			help[i++] = arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
		}
		while (p1 <= mid) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}

	}

}
