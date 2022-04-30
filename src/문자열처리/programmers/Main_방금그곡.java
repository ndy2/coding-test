package 문자열처리.programmers;

import java.util.PriorityQueue;
import java.util.Queue;

public class Main_방금그곡 {

    public static void main(String[] args) {

        System.out.println(new Solution().solution("CC#BCC#BCC#BCC#B", new String[]
                {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));

    }

    static class Solution {

        static String convert(String orig) {
            StringBuilder sb = new StringBuilder(orig);
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == '#') {
                    char note = sb.charAt(i - 1);
                    sb.replace(i - 1, i + 1, String.valueOf((char) (note - ('A' - 'a'))));
                }
            }
            return sb.toString();
        }

        static int toInt(String time){
            String[] hhmm = time.split(":");
            return Integer.parseInt(hhmm[0]) * 60 + Integer.parseInt(hhmm[1]);
        }

        static class MusicInfo implements Comparable<MusicInfo> {

            int idx;
            int playtime;
            String name;
            String score;

            @Override
            public int compareTo(MusicInfo o) {
                return o.playtime != playtime ? o.playtime - playtime : idx - o.idx;
            }

            public MusicInfo(String name) {
                this.idx = 100;
                this.playtime = -1;
                this.name = name;
            }

            public MusicInfo(int idx, String s){
                String[] mi = s.split(",");
                this.idx = idx;
                this.playtime = toInt(mi[1])- toInt(mi[0]);
                this.name = mi[2];
                this.score = convert(mi[3]);
            }

            boolean checkTarget(String target){
                String playScore;
                StringBuilder sb = new StringBuilder();
                int scoreLen = score.length();
                int q = playtime / scoreLen;
                for (int i = 0; i < q; i++) {
                    sb.append(score);
                }
                int r = playtime % scoreLen;
                sb.append(score, 0, r);

                playScore = sb.toString();
                return playScore.contains(target);
            }
        }

        public String solution(String m, String[] musicinfos) {
            String target = convert(m);
            MusicInfo answer = new MusicInfo("(None)");
            Queue<MusicInfo> q = new PriorityQueue<>();
            q.offer(answer);
            for (int i = 0; i < musicinfos.length; i++) {
                MusicInfo musicInfo = new MusicInfo(i, musicinfos[i]);
                if(musicInfo.checkTarget(target)){
                    q.offer(musicInfo);
                }
            }

            return q.peek().name;
        }
    }
}
