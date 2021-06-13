package 排序;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		int[] arr = {4,6,8,5,9,-100 };
		heapSort(arr);
		System.out.println(Arrays.toString(arr));

	}
	
	public static void heapSort(int arr[]) {
		//将无序序列构建成一个堆，根据升序选择大顶堆
		for(int i = arr.length/2-1; i>=0;i--) {
			adjustHeap(arr,i,arr.length);
		}
		
		//2. 将堆顶元素与末尾元素进行交换，将最大元素“沉”到数组末端
		//3. 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到有序
		for(int j = arr.length-1; j>0; j--)
		{
			//交换
			int temp = arr[j];
			arr[j] = arr[0];  //arr[0]是最大值
			arr[0] = temp;
			adjustHeap(arr,0,j);
		}
		
	}
	
	//将一个数组（二叉树）调整成一个大顶堆
	
	/**
	 * 功能：完成将以i对应的非叶子节点的树调整成大顶堆。
	 * @param arr   	待调整的数组
	 * @param i			表示非叶子节点在数组中的索引
	 * @param length	表示对多少个元素进行调整  length在逐渐减少
	 */
	public static void  adjustHeap(int[] arr, int i, int length) {
		int temp = arr[i];  //先取出当前元素的值，保存在临时变量。
		//开始调整
		//k = i*2+1  k是i节点的左子节点
		for(int k = i*2+1; k < length; k = k*2+1) {
			if(k+1 < length && arr[k]<arr[k+1]) {   //说明左子节点的值小于右子节点的值
				k++;   //k指向右子节点
			}
			if(arr[k] > temp) {
				//如果子节点大于父节点
				arr[i] = arr[k];   //把较大的值赋给当前节点，然后让i指向k，继续循环比较
				i = k;
			}else {
				break;
			}
		}
		//当for循环结束后，已经将以i为父节点的树的最大值，放在了最顶上。
		arr[i] = temp;  //将temp值放到调整后的位置
	}

}
