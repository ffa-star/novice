package 位运算;

public class YiHuo {

	//一个数组中有两种数出现了奇数次，其他都出现了偶数次。找到这两个数
	//思路：先全部异或。这样就能得到a^b的结果。因为这两个是不一样的数，所以肯定存在一个位数是1.这里找到最右边为1的位置。假设这个位置是第八位。然后找到数组中第八位为1的数，以及第八位为0的数。分别异或。就能得到这两个数
	public static void main(String[] args) {
		

	}
	public static void printOddTimesNum2(int[] arr) {
		int eor = 0;
		for(int i = 0; i < arr.length; i++) {
			eor^=arr[i];
		}
		int rightOne = eor&(~eor+1);   //此时rightOne只有1个1
		int onlyOne = 0;   //eor'
		for(int i = 0; i <arr.length; i++) {
			if((arr[i] & rightOne) != 0) {   //表示这些数字的rightOne的那一位有1
				onlyOne ^= arr[i];
			}
		}
		System.out.println(onlyOne +" "+(eor^onlyOne));
	}

}
