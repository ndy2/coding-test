package 시뮬레이션.programmers;

import java.util.*;

public class Main_오픈채팅방 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().solution(new String[]{
                "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"
        })));
    }

    static class Solution {

        static class Pair{
            String format;
            String uid;

            public Pair(String format, String uid) {
                this.format = format;
                this.uid = uid;
            }
        }

        String enter = "%s님이 들어왔습니다.";
        String leave = "%s님이 나갔습니다.";
        Map<String, String> nicknameMap = new HashMap<>();

        public String[] solution(String[] record) {

            List<Pair> result = new ArrayList<>();

            for (String log : record) {
                String[] split = log.split(" ");

                String op = split[0];
                String uid = split[1];

                if ("Enter".equals(op)) {
                    String nickName = split[2];
                    result.add(new Pair(enter, uid));
                    nicknameMap.put(uid, nickName);
                } else if ("Leave".equals(op)) {
                    result.add(new Pair(leave, uid));
                } else if ("Change".equals(op)) {
                    String nickName = split[2];
                    nicknameMap.put(uid, nickName);
                }
            }

            return result.stream()
                    .map(s -> String.format(s.format, nicknameMap.get(s.uid)))
                    .toArray(String[]::new);
        }
    }
}
