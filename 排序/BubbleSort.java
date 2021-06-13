package 排序;

public class BubbleSort {

	public static void main(String[] args) {
		int arr[] = { 3, 9, 5, 4, 8 };

		int temp = 0;
		boolean flag = true;
		for (int i = 0; i < arr.length - 1; i++) {   //一共比较n-1趟
			flag = true; //用来判断这一趟排序中有没有进行过交换
			for (int j = 0; j < arr.length - 1 - i; j++) {  //每一趟从0开始，到上一次排好序的数字前
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					flag = false;
				}
			}
			if(flag) {  //在一趟排序中，一次交换都没有发生，退出
				break;
			}
		}
		for (int i : arr) {
			System.out.println(i);
		}
	
		
		
	}
		
}
