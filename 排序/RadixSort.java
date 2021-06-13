package 排序;
import java.util.Arrays;
public class RadixSort {

	
	public static void main(String[] args) {
		int[] array = {1,8,7,5,4,1};
		radixSort(array);
		System.out.println(Arrays.toString(array));
		

	}
	
	//基数排序方法
	public static void radixSort(int[] arr) {
		//首先得到数组中的最大数的位数
		//得到最大数
		int max = arr[0];
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		//得到最大数是几位数
		int maxLength = (max+"").length();
		
		
		
		
		//定义一个二维数组，表示10个桶，每个桶就是一个一维数组
		//说明:
		//1.每个一维数组的大小为arr.length
		//2. 基数排序是用空间换时间
		int[][] bucket = new int[10][arr.length];
		
		//为了记录每个桶中，实际存放了多少个数据，定义一个一维数组来记录各个桶的每次放入的数据个数
		int[] bucketElementCounts = new int[10];
		
		
		//不停地比较他的位数，并放入数组中
		for(int i = 0, n = 1; i < maxLength; i++, n*=10) {   //一共有多少位数，比较多少次
			for(int j = 0; j < arr.length; j++) {  //取出数组中的每一位数并进行排序
				int digitOfElement = arr[j] / n %10;   //取出它的每一位
				//放入对应的桶中
				bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
				bucketElementCounts[digitOfElement]++;
			}
			
			//按照桶的顺序（一维数组的下标依次取出数据，放入原来数组）
			int index = 0;
			
			//遍历每一个桶，并将桶中的数据放入到原数组中
			for(int k = 0; k < bucketElementCounts.length; k++) {  //length就是桶的个数
				//如果桶中有数据，就放入到原来的数组中
				if(bucketElementCounts[k] != 0) {
					//循环第k个桶，放入
					for(int l = 0; l < bucketElementCounts[k]; l++) {
						arr[index++] = bucket[k][l];
						
					}
					
				}
				//第l轮后，需要将每一个bucketElementCounts[k] = 0;
				bucketElementCounts[k] = 0;
				
			}
		}
		
		
	}

}
