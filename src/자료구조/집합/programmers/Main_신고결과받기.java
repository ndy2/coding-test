package 자료구조.집합.programmers;

import java.util.*;

public class Main_신고결과받기 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().solution(new String[]{
                "muzi", "frodo", "apeach", "neo"
        }, new String[]{
                "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"
        }, 2)));
    }

    static class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {

            int numUser = id_list.length;
            Map<String, Set<String>> reportMap = new HashMap<>(numUser);
            Map<String, Set<String>> reportedByMap = new HashMap<>(numUser);
            Map<String, Integer> reportedCnt = new HashMap<>(numUser);

            for (String id : id_list) {
                reportMap.put(id, new HashSet<>());
                reportedByMap.put(id, new HashSet<>());
                reportedCnt.put(id, 0);
            }

            for (String r : report) {
                String[] fromTo = r.split(" ");
                String from = fromTo[0];
                String to = fromTo[1];

                if(!reportedByMap.get(to).contains(from)){
                    reportMap.get(from).add(to);
                    reportedCnt.put(to,reportedCnt.get(to) + 1);
                }
                reportedByMap.get(to).add(from);
            }

            int[] answer = new int[numUser];
            for (int i = 0; i < numUser; i++) {
               answer[i] = (int) reportMap.get(id_list[i]).stream()
                       .filter(id -> reportedCnt.get(id) >=k)
                       .count();
            }

            return answer;
        }
    }
}
