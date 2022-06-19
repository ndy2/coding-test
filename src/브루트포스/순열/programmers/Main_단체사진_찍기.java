package 브루트포스.순열.programmers;

import java.util.Arrays;

public class Main_단체사진_찍기 {

    public static void main(String[] args) {

        System.out.println(
                new Solution().solution(2, new String[]{"N~F=0", "R~T>2"})
        );
    }

    static class Solution {
        char[] friends = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

        public int solution(int n, String[] data) {

            int answer = 0;
            do {
                answer += Arrays.stream(data).allMatch(this::satisfy) ? 1 : 0;
            } while (nextPermutation(friends));
            return answer;
        }

        boolean satisfy(String elem) {
            int posFriends1 = findPos(elem.charAt(0));
            int posFriends2 = findPos(elem.charAt(2));

            int numFriendBetweenFriend1AndFriend2 = Math.abs(posFriends2 - posFriends1) - 1;
            int dist = elem.charAt(4) - '0';

            char op = elem.charAt(3);

            return op == '=' ? numFriendBetweenFriend1AndFriend2 == dist :
                    op == '>' ? numFriendBetweenFriend1AndFriend2 > dist :
                            op == '<' && numFriendBetweenFriend1AndFriend2 < dist;
        }

        int findPos(char friend) {
            for (int i = 0; i < friends.length; i++) {
                if (friend == friends[i]) {
                    return i;
                }
            }
            throw new RuntimeException();
        }

        boolean nextPermutation(char[] origin) {
            int N = origin.length;
            int i = N - 1;
            while (i > 0 && origin[i - 1] >= origin[i]) --i;
            if (i == 0) return false;

            int j = N - 1;
            while (origin[i - 1] >= origin[j]) --j;

            char tmp = origin[i - 1];
            origin[i - 1] = origin[j];
            origin[j] = tmp;

            int k = N - 1;
            while (i < k) {
                tmp = origin[i];
                origin[i] = origin[k];
                origin[k] = tmp;
                ++i;
                --k;
            }
            return true;
        }
    }
}



