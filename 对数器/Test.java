package 对数器;

public class Test {

	public static void main(String[] args) {
		

	}
	
	
	
	//生成随机数组
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int)Math.random()*(maxSize+1)];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)((maxValue+1)*Math.random()) - (int)((maxValue+1)*Math.random());
			
		}
		return arr;
	}

}
