package Algorithm;

public class Hannoitower {

	public static void main(String[] args) {
		hannoiTower(3, 'a', 'b', 'c');

	}

	// 汉诺塔移动
	/**
	 * 
	 * @param num 盘子的数量
	 * @param a   柱子
	 * @param b
	 * @param c
	 */
	public static void hannoiTower(int num, char a, char b, char c) {
		// 如果只有一个盘
		if (num == 1) {
			System.out.println("第1个盘从" + a + "->" + c);
		} else {
			// 如果n>=2,我们总是可以看做两个盘，最上面的所有盘可最小面的一个盘
			// 1. 先把最上面的所有盘从a->b,移动过程会使用c
			hannoiTower(num - 1, a, c, b);
			// 2. 把最下面的盘 a->c
			System.out.println("第" + num + "个盘从" + a + "->" + c);
			// 3.把B塔的盘一道C
			hannoiTower(num - 1, b, a, c);
		}
	}
}
