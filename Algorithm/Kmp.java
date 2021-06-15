package Algorithm;
import java.util.*;
public class Kmp {

	public static void main(String[] args) {
		String s1 = "aabdssdd";
		String s2 = "aaddddss ";
//		System.out.println(violenceMatch(s1,s2));
		int[] next = nextKmp("abada");
		System.out.println(Arrays.toString(next));
//		System.out.println(kmp(s1,s2,next));

	}
		
	//暴力匹配
	public static int violenceMatch(String str1, String str2) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		
		int s1Len = s1.length;
		int s2Len = s2.length;
		
		int i = 0, j = 0;
		while(i < s1Len && j <s2Len) {
			//不越界
			if(s1[i] == s2[j]) {
				i++;
				j++;	
			}else {
				//没有匹配成功
				i = i - (j-1);
				j = 0;
			}
		}
		//判断是否匹配成功
		if(j == s2Len) {
			return i - j;   //返回第一次出现的下标
		}else {
			return -1;
		}
	}
	
	//kmp搜索算法
	/**
	 * 
	 * @param s1 原来的字符串
	 * @param s2子串
	 * @param next	部分匹配表 子串
	 */
	public static int kmp(String s1, String s2, int[] next) {
		//遍历s1
		for(int i = 0 , j = 0; i <s1.length(); i++) {
			//处理不相等的情况
			while( j > 0 && s1.charAt(i) != s2.charAt(j)) {
				j = next[j-1];
			}
			if(s1.charAt(i) == s2.charAt(j)) {
				j++;
			}
			
			if(j == s2.length()) {
				return i-j+1;  //这里需要加1是因为j比i多+1了。
			}
		}
		
		
		
		
		return -1;
	}
	
	//kmp获取到一个字符串（子串）的部分匹配值
	public static int[] nextKmp(String s1){
		//保存部分匹配值
		int[] next = new int[s1.length()];
		//如果字符串长度为1，部分匹配值就是0
		next[0] = 0;
		for(int i = 1,j = 0; i < s1.length();i++) {
			while(j > 0 && s1.charAt(i)!= s1.charAt(j)) {
				j = next[j-1];   //next[j-1]就是在j失位的情况下，需要移动多少位。
			}
			//当这个条件满足时，部分匹配值就+1
			if(s1.charAt(i) == s1.charAt(j)) {
				j++;   //这里的j++是在前一个的基础上加的，也就代表这前面是匹配成功的。
			}
			next[i] = j;
		}
		return next;
	}
	
	
}
