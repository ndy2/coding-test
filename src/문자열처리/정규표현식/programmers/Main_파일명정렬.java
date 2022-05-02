package 문자열처리.정규표현식.programmers;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main_파일명정렬 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})));

    }

    static class Solution {

        static Pattern pattern = Pattern.compile("(\\D+)(\\d+)\\S*");

        static class File implements Comparable<File>{

            String fileStr;
            String head;
            String number;
            int numberValue;

            int idx;

            File(String fileStr, int idx){
                this.fileStr = fileStr;
                this.idx = idx;

                Matcher matcher = pattern.matcher(fileStr);
                if(matcher.find()){
                    head = matcher.group(1).toLowerCase();


                    number = matcher.group(2);
                    numberValue = Integer.parseInt(number);
                }
            }

            @Override
            public int compareTo(File o) {
                int headComp = head.compareTo(o.head);
                if(headComp !=0) return headComp;
                else{
                    return numberValue!=o.numberValue? numberValue - o.numberValue:idx-o.idx;
                }
            }
        }

        public String[] solution(String[] files) {
            int fl = files.length;
            String[] answer = new String[fl];

            Queue<File> q = new PriorityQueue<>();

            for (int i = 0; i < fl; i++) {
                q.offer(new File(files[i],i));
            }

            for (int i = 0; i < fl; i++) {
                answer[i] = q.poll().fileStr;
            }
            return answer;
        }
    }
}
