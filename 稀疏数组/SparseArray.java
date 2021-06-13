package 稀疏数组;

public class SparseArray {
	public static void main(String[] args) {
		//一、创建原始的二维数组
		int[][] array1 = new int[11][11];
		array1[1][2] = 1;
		array1[2][3] = 2;
		System.out.println("初始化后的二维数组");
		for(int[] row:array1) {
			for(int col : row) {
				System.out.printf("%d\t",col);
			}
			System.out.println();
		}
		
		
		//二、将二维数组转换为稀疏数组
//		1. 先遍历二维数组，得到他的元素个数
		int sum = 0;
		for(int[] row:array1) {
			for(int col : row) {
				if(col != 0) 
					sum++;		
			}
		}
//		2.初始化稀疏数组
		int sparseArray[][] = new int[sum+1][3];
		sparseArray[0][0] = 11;   //存储行数
		sparseArray[0][1] = 11;	  //存储列数
		sparseArray[0][2] = sum;  //存储元素个数
		
//		3. 遍历二维数组，将数值存储到稀疏数组中
		int count = 0;
		
		for(int i = 0; i < array1.length; i++) {
			for(int j = 0; j < array1[i].length; j++) {
				if(array1[i][j] != 0) { 
					count++;	
					sparseArray[count][0] = i;
					sparseArray[count][1] = j;
					sparseArray[count][2] = array1[i][j];
				}
				
			}
		}
		
		System.out.println("\n稀疏数组");
		for(int i = 0; i < sparseArray.length;i++) {
			
				System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
			
		}
		
		
		//三、将稀疏数组转换为二维数组
//		1. 首先得到二维数组的行和列，初始化二维数组
		int[][] array2 = new int[sparseArray[0][0]][sparseArray[0][1]];
		
//		2. 遍历稀疏数组，给二维数组赋值
		for(int i = 1; i < sparseArray.length; i++) {
			for(int j = 0; j < 3; j++) {
				array2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
			}
		}
		
		System.out.println("稀疏数组变为二维数组");
		for(int[] row:array2) {
			for(int col : row) {
				
					System.out.printf("%d\t",col);	
			}
			System.out.println();
		}
		
		
	}
}
