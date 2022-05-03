package 자료구조.해시;

import java.util.HashMap;
import java.util.Map;

public class Main_위장 {


    public static void main(String[] args) {
        System.out.println(new Solution().solution(new String[][]{
                {"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}
        }));
    }

    static class Solution {

        public int solution(String[][] clothes) {
            Map<String, Integer> clotheMap = new HashMap<>();

            for (String[] clothe : clothes) {
                String clotheType = clothe[1];
                if (clotheMap.containsKey(clotheType)) {
                    clotheMap.put(clotheType, clotheMap.get(clotheType) + 1);
                } else {
                    clotheMap.put(clotheType, 1);
                }
            }

            return clotheMap.values().stream()
                    .mapToInt(i -> i + 1)
                    .reduce((a, b) -> a * b)
                    .getAsInt() - 1;
        }
    }
}
