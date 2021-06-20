package 排序;

import java.util.Arrays;

//插入排序
public class InsertSort {

	public static void main(String[] args) {
		int arr[] = {11,5,6,9,3,7,5};
		int insertVal = 0;
		int insertIndex = 0;
		for(int i = 1; i < arr.length;i++) {
		//定义待插入的数
			insertVal = arr[i];
		//定义待插入数的索引  也就是和它前面一个数进行比较
			insertIndex = i - 1;   
		
		//给insertval找到插入的位置
		while(insertIndex >= 0 && insertVal < arr[insertIndex]) {   
			//insertIndex >= 0  保证在给insertVal找插入位置时，不越界。
//			insertVal < arr[insertIndex]，表示待插入的数还没有找到适当的位置。因为是从小到大排序，所以需要找到比他小的位置。
//			此时需要将arr[indexIndex]后移,因为要插在她前面。这时的后移只会移除后面的数据，但是后面的数据已经保存在insertVal里面了。
			arr[insertIndex+1] = arr[insertIndex];
			insertIndex--;
		
		}
		
		//当退出while循环时，说明插入位置找到，insertIndex+1。因为退出循环前insertIndex--了
		//如果insertVal在她实际的地方，就不需要赋值了
		if(insertIndex != i-1) {
			arr[insertIndex+1] = insertVal;
		}
		
		System.out.println(Arrays.toString(arr));
		}

	}
	
	public void insertSort2(int[] arr) {
		for(int i = 1; i < arr.length; i++) {
			int insertIndex = i-1, insertVal = arr[i];
			while(insertIndex >=0 && insertVal < arr[insertIndex]) {
				arr[insertIndex+1] = arr[insertIndex];
				insertIndex--;
			}
			if(insertIndex != i-1) {
				arr[insertIndex+1] = insertVal;
			}
			
		}
		
		
		
	}
	

}
