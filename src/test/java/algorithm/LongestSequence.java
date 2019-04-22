package algorithm;

import java.util.HashMap;
import java.util.Map;

public class LongestSequence {
    public int longestSequence(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int max = 1;
        //key表示遍历的某个数,value表示最长连续数列的长度
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])){
                map.put(arr[i], 1);
                if (map.containsKey(arr[i] - 1)){
                    max = Math.max(max, merge(map, arr[i] -1, arr[i]));
                }
                if (map.containsKey(arr[i] + 1)){
                    max = Math.max(max, merge(map, arr[i], arr[i] + 1));
                }
            }
        }
        return max;
    }

    //将less左边的数列与more右边的数列合并
    public int merge(Map<Integer, Integer> map, int less, int more){
        int left = less - map.get(less) + 1;
        int right = more + map.get(more) - 1;
        int len = right - left + 1;
        map.put(left, len);
        map.put(right, len);
        return len;
    }

    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};
        LongestSequence longestSequence = new LongestSequence();
        System.out.println(longestSequence.longestSequence(arr));
    }
}
