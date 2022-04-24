package 자료구조.링크드리스트.programmers;

import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main_표편집 {

    public static void main(String[] args) {
//
//        System.out.println(
//                new Solution().solution(8, 2,
//                        new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));
//
//
//        System.out.println(
//                new Solution().solution(8, 2,
//                        new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));
//

        System.out.println(
                new Solution().solution(4, 0,
                        new String[]{"C","C","C","C","Z","Z"}));
    }


    static class Solution {
        public String solution(int n, int k, String[] cmd) {
            String answer = "";

            Data data = new Data(n, k);
            data.print();
            System.out.println();

            for (String c : cmd) {
                System.out.println("======" + c + "===========");

                switch (c.charAt(0)){
                    case 'U' :{
                        int x = Integer.parseInt(c.substring(2));
                        data.moveCursor(-x);
                        break;
                    }
                    case 'D' :{
                        int x = Integer.parseInt(c.substring(2));
                        data.moveCursor(x);
                        break;
                    }

                    case 'C' :{
                        data.removeAtCursor();
                        break;
                    }
                    case 'Z' :{
                        data.restore();
                        break;
                    }
                }
                data.print();
                System.out.println();
                System.out.println();
            }

            return data.answer();
        }


        static class Data extends LinkedList<Integer>{

            int n;
            int cursor;
            Stack<Integer> store = new Stack<>();

            public Data(int n, int k) {
                super.addAll(IntStream.range(0, n).boxed().collect(Collectors.toList()));
                this.n = n;
                cursor = k;
            }

            void moveCursor(int x){
                cursor+=x;
            }

            void removeAtCursor(){
                store.push(
                        remove(cursor)
                );
                System.out.println("data removed : " + cursor);
                if(cursor == size() ){
                    cursor --;
                }
                System.out.println("cursor @ " + cursor);
            }

            void restore(){
                Integer pop = store.pop();
                int restoreIdx = lowerBound(pop);
                add(restoreIdx,pop);
                System.out.println("data restore : " + pop);

                if(restoreIdx < cursor){
                    cursor++;
                }
                System.out.println("cursor @ " + cursor);
            }

            void print(){
                System.out.println("cursor : " + cursor + " size : " + size());
                for (Integer integer : this) {
                    System.out.print(integer+" ");
                }
            }

            int lowerBound(int target) {
                int begin = 0;
                int end = this.size();

                while(begin < end) {
                    int mid = (begin + end) / 2;

                    if(this.get(mid) >= target) {
                        end = mid;
                    }
                    else {
                        begin = mid + 1;
                    }
                }
                return end;
            }

            public String answer() {
                StringBuilder sb = new StringBuilder();
                int pos = 0;
                for (int i = 0; i < n; i++) {

                    if(pos < size()){



                    Integer cur = get(pos);
                    if(i == cur){
                        pos ++;
                        sb.append('O');
                    }else{
                        sb.append('X');
                    }
                    }else{
                        sb.append('X');
                    }

                }

                return sb.toString();
            }
        }
    }
}
