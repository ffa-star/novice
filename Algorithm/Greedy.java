package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Greedy {

	public static void main(String[] args) {
		//创建广播电台，放入到Map
		HashMap<String,HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
		
		//将电台放入到broadcast中
		HashSet<String> hashset1 = new HashSet<>();
		hashset1.add("北京");
		hashset1.add("上海");
		hashset1.add("天津");
		
		HashSet<String> hashset2 = new HashSet<>();
		hashset2.add("广州");
		hashset2.add("北京");
		hashset2.add("深圳");
		
		HashSet<String> hashset3 = new HashSet<>();
		hashset3.add("成都");
		hashset3.add("上海");
		hashset3.add("杭州");
		
		HashSet<String> hashset4 = new HashSet<>();
		hashset4.add("上海");
		hashset4.add("天津");
		
		
		
		HashSet<String> hashset5 = new HashSet<>();
		hashset5.add("杭州");
		hashset5.add("大连");
		
		//加入到map中
		broadcasts.put("K1",hashset1);
		broadcasts.put("K2",hashset2);
		broadcasts.put("K3",hashset3);
		broadcasts.put("K4",hashset4);
		broadcasts.put("K5",hashset5);
		
		//存放所有的地区
		HashSet<String> allAreas = new HashSet<String>();
		allAreas.add("北京");
		allAreas.add("上海");
		allAreas.add("天津");
		allAreas.add("广州");
		allAreas.add("深圳");
		allAreas.add("成都");
		allAreas.add("杭州");
		allAreas.add("大连");
		
		//创建ArrayList，存放选择的电台集合
		ArrayList<String> selects = new ArrayList<String>();
		
		//定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集。(重复的）
		HashSet<String> tempSet = new HashSet<>();
		
		//定义一个maxKey，保存在一次遍历过程中，能够覆盖最多未覆盖的地区对应的电台的key
		String maxKey = null;
		//如果maxKey不为空，则会加入到selects中去
		while(allAreas.size() != 0) {  //如果不为空，则还没有覆盖到所有的地区
			//每进行一次循环，需要将maxKey置空
			maxKey = null;
			
			//遍历broadcast，取出对应的key
			for(String key : broadcasts.keySet()) {
				
				//每进行一次for，需要将tempSet清空
				tempSet.clear();
				HashSet<String> areas = broadcasts.get(key);
				//得到的比如说：k1  北京 上海 天津
				tempSet.addAll(areas);
				//求出tempSet 和 allAreas 集合的交集，交集会赋给tempSet
				tempSet.retainAll(allAreas);
				
				//如果当前集合包含的未覆盖地区的数量，比maxKey指向的集合未覆盖地区还多，maxKey赋值为key
				//这里每次都选择最好的，就是贪心算法。
				if(tempSet.size()>0 && (maxKey == null || tempSet.size()>broadcasts.get(maxKey).size())) {
					maxKey = key;
				}
			}
			
			//不为空，将maxKey加入到select集合中
			if(maxKey != null) {
				selects.add(maxKey);
				//将maxKey指向的广播电台覆盖的地区，从allAreas中去掉
				allAreas.removeAll(broadcasts.get(maxKey));
			}
			
		}
		System.out.println(selects);
	}

}
