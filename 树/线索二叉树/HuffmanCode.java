package 树.线索二叉树;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.*;

public class HuffmanCode {

	public static void main(String[] args) {
		String str = "i like like like java do you like a java  ";
		byte[] bytes = str.getBytes();
		System.out.println(Arrays.toString(bytes));
		byte[] huffmanBytes = huffmanZip(bytes);
		System.out.println(Arrays.toString(huffmanBytes));
		System.out.println(huffmanBytes.length);
////		System.out.println(bytes.length);
//		List<Node> nodes = getNodes(bytes);
////		System.out.println(nodes);
//		Node node = createHuffmanTree(nodes);
//		node.preOrder();
//
//		// 测试赫夫曼编码
//		getCodes(node);
////		System.out.println(huffmanCodes);
////		System.out.println("----");
//		getCodes(node, "", sb);
////		System.out.println(huffmanCodes);
//		
//		//压缩
//		byte[] bytes2 = zip(bytes,huffmanCodes);
//		System.out.println(Arrays.toString(bytes2));
//		System.out.println(byteToBiString(true,(byte)5));

//		String str1 = Integer.toBinaryString(-1);
//		System.out.println(str1);
//		int temp = -1|256;
//		System.out.println(Integer.toBinaryString(temp));
//		byte[] sourceBytes = decode(huffmanCodes, huffmanBytes);
//		System.out.println(new String(sourceBytes));
		
		//测试压缩文件
		String srcFile = "E:\\图片\\55.jpg";
		String dstFile = "E:\\555.zip";
//		zip(srcFile,dstFile);
		unZipFile(dstFile,"E:\\666.jpg");
		
	}
	//编写一个方法，完成对压缩文件的解压
	public static void unZipFile(String zipFile, String dstFile) {
		//定义文件输入流
		InputStream is = null;
		//定义一个对象输入流
		ObjectInputStream ois = null;
		//定义一个文件输出流
		OutputStream os = null;
		try {
			is = new FileInputStream(zipFile);
			ois = new ObjectInputStream(is);
			//读取byte数组
			byte[] huffmanBytes = (byte[])ois.readObject();
			//读取赫夫曼表
			Map<Byte,String> codes = (Map<Byte,String>)ois.readObject();
			byte[] src = decode(codes, huffmanBytes);
			os = new FileOutputStream(dstFile);
			//写入数据到文件中
			os.write(src);
		} catch (IOException | ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			
			try {
				os.close();
				ois.close();
				is.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	
	
	//编写方法，将一个文件进行压缩
	/**
	 * 
	 * @param srcFile 传入的希望压缩的文件的全路劲
	 * @param dstFile 将压缩后的文件的路径
	 */
	public static void zip(String srcFile, String dstFile) {
		//创建输出流
		FileInputStream is = null;
		OutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			//创建一个文件的输入流
			is = new FileInputStream(srcFile);
			//创建一个和源文件大小一样的byte[]
			byte[] b = new byte[is.available()];
			//读取文件
			is.read(b);
			//使用赫夫曼编码
			//1.对源文件进行压缩
			byte[] huffmanBytes = huffmanZip(b);
			//创建文件的输出流，存放文件
			os = new FileOutputStream(dstFile);
			//创建一个和文件输出流关联的objectoutputstream
			oos = new ObjectOutputStream(os);
			//这里以对象流的方式写入赫夫曼编码，目的是为了以后恢复源文件使用
			oos.writeObject(huffmanBytes);  
			//注意，一定要把赫夫曼编码写入压缩文件
			oos.writeObject(huffmanCodes);
			
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				is.close();
				os.close();
				oos.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//完成对压缩数据的解码
	/**
	 * 
	 * @param huffmanCodes  赫夫曼编码表
	 * @param huffmanBytes	赫夫曼编码得到的字节数组
	 * @return	原来的字符串对应的数组
	 */
	private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes){
		//先得到huffmanBytes 对应的二进制字符串，形式10110000.。。。。。
		StringBuilder sb = new StringBuilder();
		//将byte数组转成二进制的字符串
		for(int i = 0; i < huffmanBytes.length; i++) {
			//判断是不是最后一个字节
			boolean flag = (i == huffmanBytes.length-1);
			//这里是除了最后一位，其他都是补高位。
			sb.append(byteToBiString(!flag,huffmanBytes[i])); //如果不是，就不需要补高位
		}
		
		//把字符串按照指定的赫夫曼编码进行解码
		//把赫夫曼编码表进行交换，因为要反向查询
		Map<String,Byte> map = new HashMap<String,Byte>();
		for(Map.Entry<Byte, String> entry:huffmanCodes.entrySet()) {
			map.put(entry.getValue(),entry.getKey());
		}
		
		//创建一个集合，存放byte
		List<Byte> list = new ArrayList<>();
		for(int i = 0; i < sb.length();) {
			//一个一个扫描1001110....这串字符串
			int count = 1;  //小计数器，得到一个字符的二进制
			boolean flag = true;
			Byte b = null;
			while(flag) {
				//取出一个二进制位，进行拼接
				String key = sb.substring(i,i+count);
				//i不懂，让count移动，直到匹配到一个字符
				b = map.get(key);
				if(b == null) {
					//没有匹配到
					count++;
				}else {
					//匹配到
					flag = false;
				}
			}
			list.add(b);
			i += count;  //因为substring是不包括后面一个位置的。
		}
		
		//当for循环结束后， list中就存放了所有的字符
		//把list中的数据放入到byte数组并返回
		byte[] b = new byte[list.size()];
		for(int i = 0; i <b.length;i++) {
			b[i] = list.get(i);
		}
		return b;
		
	}
	
	
	//完成数据的解压
	//思路
	//1. 先将huffmancodesBytes数组【-88.。。。。。。。。。。。。。】重新转成字符串1010011000.......
	//2. 根据赫夫曼对应的编码100001111，重新转成字节数组
	
	/**
	 * 将一个byte转成一个二进制的字符串
	 * @param b
	 * @param flag  是否需要补高位
	 * @return 是该b对应的二进制的字符串，是按补码返回，但是原来的赫夫曼编码也是补码的形式
	 */
	private static String byteToBiString(boolean flag, byte b) {
		int temp = b;
		//如果b是正数，需要补高位。
		if(flag) {
			temp |= 256;   //按位或 temp = 1, 0000 0001  256是1 0000 0000  按位或后就会1 0000 0001
		}
		String str = Integer.toBinaryString(temp);  //他的作用是把一个10进制数转为32位的2进制数。同时对负数，会用补码表示。
		//这里返回的是temp对应的二进制的补码，而且因为是integer所以是32位.正数就直接返回的是它对应的二进制。有几位就是几位
		if(flag) {
			return str.substring(str.length()-8);
		}else {
			return str;
		}
	}
	
	
	
	
	//使用一个方法，将前面的方法封装起来。
	/**
	 * 
	 * @param bytes 原始的字符串对应的字节数组 （这个字节数组里存储的是字符对应的ASCII码）
	 * @return	经过赫夫曼编码处理后的字节数组（压缩后的数组）
	 */
	private static byte[] huffmanZip(byte[] bytes) {
		//第一步，创建节点
		List<Node> nodes = getNodes(bytes);
		//第二步，根据nodes，创建赫夫曼树
		Node huffmanTree = createHuffmanTree(nodes);
		//根据赫夫曼树创建对应的赫夫曼编码
		Map<Byte,String> huffmanCodes = getCodes(huffmanTree);
		//根据赫夫曼编码进行压缩,得到压缩后的字节数组
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
		return huffmanCodeBytes;
	}
	
	
	

	/**
	 * 将字符串对应的byte数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
	 * 
	 * @param bytes        原始的字符串对应的byte
	 * @param huffmanCodes 生成的赫夫曼编码
	 * @return 返回赫夫曼编码处理后的byte数组
	 */
	private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
		// 利用huffmanCodes将 bytes转成赫夫曼编码对应的字符串
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(huffmanCodes.get(b)); // 得到每个字符对应的赫夫曼编码
		}

		//因为这里是要把上面sb里的一连串的1001100000.....转成byte数组，所以需要统计byte数组的长度，每8位为一个
		// 将连串的编码转成byte数组
		// 统计返回byte数组的长度（有几个字符，因为是8位对应一个字节）。
		int len = (sb.length() + 7) / 8;

		// 创建存储压缩后的byte数组
		byte[] huffmanCodeBytes = new byte[len];
		int index = 0; // 记录是第几个byte
		for (int i = 0; i < sb.length(); i += 8) {
			String strByte;
			if (i + 8 > sb.length()) {
				// i+8超过长度了
				strByte = sb.substring(i); // 从i一直去到后面
			} else {
				strByte = sb.substring(i, i + 8);
			}
			huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2); // 将字符二进制转换为十进制byte  补码存储
			index++;
		}
		return huffmanCodeBytes;
	}

	// 重载getCodes
	private static Map<Byte, String> getCodes(Node root) {
		if (root == null) {
			return null;
		}
		// 处理root的左子树
		getCodes(root, "", sb);
		// 右子树
//		getCodes(root.right,"1",sb);
		return huffmanCodes;
	}

	// 生成赫夫曼树对应的赫夫曼编码
	// 思路：
	// 1. 将赫夫曼编码存放在Map<Byte,String>形式 32-》01
	static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
	// 2. 在生成赫夫曼编码表示，需要拼接路径。定义一个StringBuilder 存储某个叶子结点的路径
	static StringBuilder sb = new StringBuilder();

	/**
	 * 功能：将传入node节点的所有叶子结点的赫夫曼编码得到，并放入到HuffmanCode集合中
	 * 
	 * @param node 传入节点
	 * @param code 路径 左边是0，右边是1
	 * @param sb   用于拼接路径
	 */
	private static void getCodes(Node node, String code, StringBuilder sb) {
		StringBuilder sb2 = new StringBuilder(sb);
		// 将code将入到sb2
		sb2.append(code);
		if (node != null) {
			// 判断当前node是叶子节点还是非叶子节点
			if (node.data == null) {
				// 非叶子节点
				// 递归
				// 向左
				getCodes(node.left, "0", sb2);
				// 向右
				// 左边递归完后，回到右边，此时sb2也会回到上面一层
				getCodes(node.right, "1", sb2);
//				System.out.println(sb2);
			} else {
				// 叶子节点
				// 表示找到了最后
				huffmanCodes.put(node.data, sb2.toString());
			}

		}
	}

	private static List<Node> getNodes(byte[] bytes) {
		// 创建一个ArrayList
		List<Node> nodes = new ArrayList<>();

		// 遍历bytes，统计每个byte出现的次数 -> map（key，value）
		Map<Byte, Integer> counts = new HashMap<>();
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) {
				// map还没有这个字符数据
				counts.put(b, 1);
			} else {
				counts.put(b, count + 1);
			}
		}

		// 把每个键值对转成一个node对象，并加入到nodes集合。
		for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}

		return nodes;

	}

	private static Node createHuffmanTree(List<Node> nodes) {
		while (nodes.size() > 1) {
			Collections.sort(nodes);
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			nodes.add(parent);
		}
		// 返回nodes里面最后一个节点,也就是赫夫曼树的根节点
		return nodes.get(0);

	}

}

//存放数据和权值
class Node implements Comparable<Node> {
	Byte data; // 存放数据（字符）本身，比如a = 97
	int value; // 存放权值
	Node left, right;

	public Node(Byte data, int value) {
		this.data = data;
		this.value = value;
	}

	public int compareTo(Node o) {
		// 从小到大排序
		return this.value - o.value;
	}

	public String toString() {
		return "Node[data = " + data + " value = " + value + "]";
	}

	public Node(int value) {
		this.value = value;
	}

	// 前序遍历
	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}
}
