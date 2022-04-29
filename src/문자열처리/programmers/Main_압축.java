package 문자열처리.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main_압축 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().solution("TOBEORNOTTOBEORTOBEORNOT")));
    }

    static class Solution {

        class LzwDictionary extends HashMap<String, Integer> {

            int idx;

            public LzwDictionary() {
                idx = 1;
                for (int i = 0; i < 26; i++) {
                    put(String.valueOf((char)('A' + i)));
                }
            }

            public void put(String key){
                super.put(key, idx++);
            }

        }

        public int[] solution(String msg) {
            List<Integer> answer = new ArrayList<>();
            LzwDictionary dictionary = new LzwDictionary();

            for (int i = 0; i < msg.length(); i++) {
                StringBuilder w = new StringBuilder();
                int j;
                for (j = i; j < msg.length(); j++) {
                    if(dictionary.containsKey(w.toString() + msg.charAt(j))){
                        w.append(msg.charAt(j));
                    }else{
                        break;
                    }
                }
                String ww = w.toString();
                if(j != msg.length()){
                    dictionary.put(w.append(msg.charAt(j)).toString());
                }
                i += ww.length() - 1;

                Integer add = dictionary.get(ww);
                answer.add(add);
            }

            return answer.stream().mapToInt(i -> i).toArray();
        }
    }
}
