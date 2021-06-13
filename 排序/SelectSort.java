package 排序;

//选择排序
public class SelectSort {

	public static void main(String[] args) {
		int arr[] = {5,-6,8,7,4,3};
		
		for(int i = 0; i <arr.length-1;i++) {
			int minIndex = i;
			int BeginMin = arr[i];
			for(int j = i+1; j < arr.length;j++) {
				if(arr[j]<arr[minIndex]) {
					minIndex = j;
				}
			}
			if(BeginMin != i) {
				
				arr[i] = arr[minIndex];
				arr[minIndex] = BeginMin;
			}
		}
		for(int i:arr) {
			System.out.println(i);
		}

	}

}
