package 排序;

import java.util.Arrays;

public class ShellSort {

	public static void main(String[] args) {
		int arr[] = {2,1,-9,5,2,1,3,10};
		shellSortByMyself(arr);
		System.out.println(Arrays.toString(arr));
		

	}
	
	//交换法
	public static void shellSort(int[] arr) {
		int temp = 0; 
		for(int gap = arr.length/2; gap > 0; gap /= 2) {    //获取步长一共需要变化的次数，也就是一共会分组几次。
			for(int i = gap; i< arr.length;i++) {			//从第一组的第二个元素开始。逐个往后。
				for(int j = i - gap; j >= 0; j-=gap) {		//获取每一组前面的数。
					if(arr[j] > arr[j+gap]) {
						temp = arr[j];
						arr[j] = arr[j+gap];
						arr[j+gap] = temp;
					}
				}
			}
		}
	}
	
	public static void shellSortByMyself(int[] arr) {
		int temp = 0;
		int insertVal = 0;
		int insertIndex = 0;
		for(int gap = arr.length/2; gap > 0; gap/=2) {      //获取一共需要分几次组。直到增量为1
			for(int i = gap; i< arr.length;i++) {  //从第一个组的第二个元素开始遍历元素
				insertVal = arr[i];
				insertIndex = i-gap;
				while(insertIndex >=0 && arr[insertIndex] > insertVal) {
					arr[insertIndex+gap] = arr[insertIndex]; 
					insertIndex-=gap;
				}
				if(insertIndex != i-gap) {
					arr[insertIndex+gap] = insertVal;
				}
				
			}
			
		}
	}
	
	public static void shellSort2(int[] arr) {
		
	}
	
	
	
	

}
