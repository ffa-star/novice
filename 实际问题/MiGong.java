package 实际问题;

public class MiGong {

	public static void main(String[] args) {
		// 创建一个二维数组，模拟迷宫
		int[][] map = new int[8][7];
		//使用1表示强  左右全部置为1
		for(int i = 0; i < 8;i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}
		
		//上下全部置为1
		for(int i = 0; i < 7;i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}
		
		//设置挡板
		map[3][1]= 1;
		map[3][2]= 1;
//		map[3][3]= 1;
//		map[3][4]= 1;
//		map[3][5]= 1;
//		map[3][6]= 1;
		
		//输出地图
		for(int i = 0; i <8; i++) {
			for(int j = 0; j < 7; j++) {
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
		
		System.out.println("-------------");
		
		//使用递归完成实验
		setWay(map,1,1);
//		输出新的地图，这就是小球走过，并标识过的地图
		for(int i = 0; i <8; i++) {
			for(int j = 0; j < 7; j++) {
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}

	}
	
//	使用递归回溯来给小球找路
//	(i,j)表示从地图的哪个位置开始找
//	如果小球能到map[6][5]，则说明通路找到。
//	当map[i][j]为0时，表示该点没有走过，当为1时，表示强，不能走，当为2时，表示通路，可以走。 3表示该点已经走过，但是走不通。
//	在走迷宫时，需要确定一个策略（方法），先走下 ->右 ->上 ->左。如果该点走不通，再回溯。
	/**
	 * 
	 * @param map 表示地图
	 * @param i  从哪个位置开始找
	 * @param j
	 * @return 如果找到通路，返回true，否则返回false
	 */
	public static boolean setWay(int[][] map, int i, int j) {
		if(map[6][5] == 2) {
//			说明通路已经找到
			return true;
		}else {
			if(map[i][j] == 0) {	//如果当前的点还没有走过
				//按照下、右、上、左走
				map[i][j] = 2;  //假定该点是可以走通的。
				if(setWay(map,i+1,j)) {   //在这个点的基础上向下走
					return true;
				}else if(setWay(map,i+1,j)) {   //在这个点的基础上向下走
					return true;
					
				}else if(setWay(map,i,j+1)) {   //在这个点的基础上向右走
					return true;
					
				}else if(setWay(map,i-1,j)) {   //在这个点的基础上向上走
					return true;
					
				}else if(setWay(map,i,j-1)) {   //在这个点的基础上向左走
					return true;
				}else {
					//说明该点走不通，死路。
					map[i][j] = 3;
					return false;
				}
			}else {  //map[i][j]不为0，可能是1,2,3 
				return false;
				
			}
		}
	}

}
