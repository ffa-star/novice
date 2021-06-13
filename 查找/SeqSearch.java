package 查找;

public class SeqSearch {

	public static void main(String[] args) {
		

	}
	
	public static int seqSearch(int[] arr, int value) {
		for(int i =0 ; i < arr.length; i++)
		{
			if(arr[i] == value) {
				return i;
			}
		}
		
		//没找到
		return -1;
	}

}
