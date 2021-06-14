package Algorithm;

public class Dynamtic {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int[] w = {1,4,3};	//物品的重量
		int[] val = {1500,3000,2000};  //物品的价值  这里的
		int m = 4; //背包的容量
		int n = w.length; //物品的个数
		
		
		//创建二维数组，v[i][j]表示在前i个物品中，能够装入容量为j的背包的最大价值
		int[][] v = new int[n+1][m+1];  //第一行和第一列是0
		//为了记录放入商品的情况，定义一个二维数组，与v的大小相同
		int[][] path = new int[n+1][m+1];
		
		//初始化第一行和第一列,本程序中可以不写，默认为0
		for(int i = 0; i <v.length; i++) {
			v[i][0] = 0; //将第一列设为0
		}
		for(int i = 0; i <v.length; i++) {
			v[0][i] = 0;  //将第一行设为0
		}
		
		
		//根据公式，动态规划处理
		for(int i = 1; i < v.length; i++) { //二维数组中不处理第一行,第一列
			for(int j = 1; j <v[0].length; j++) {
				if(w[i-1] > j) {   //因为程序是从1开始的 所以需要i-1
					//如果物品的重量大于当前仅剩的重量，就填入前一个的最大价值
					v[i][j] = v[i-1][j];
				}else {
					//否则就比较前面的价值和加入这个价值后的最大值
//					v[i][j] = Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
					//为了记录商品存放到背包的情况，可以用下面的公式
					if(v[i-1][j]< val[i-1]+v[i-1][j-w[i-1]]) {
						v[i][j]  = val[i-1]+v[i-1][j-w[i-1]];
						path[i][j] = 1;
						//这个情况是最优的
					}else {
						v[i][j]  = v[i-1][j];
					}
				
				}
			}
		}
		
		for(int i = 0; i < v.length; i++) {
			for(int j = 0; j < v[i].length; j++) {
				System.out.print(v[i][j]+"\t");
			}
			System.out.println();
		}
		
		System.out.println();
		
		int i = path.length-1;    //行的最大下标
		int j = path[0].length-1; //列的最大下标
		while(i>0 && j>0) {
			if(path[i][j] == 1) {
				System.out.printf("第%d个商品放入到背包\n",i);
				j -= w[i-1];  //表示上个商品放完之后，剩余的容量。
			}
			i--;
		}
	}
	

}
