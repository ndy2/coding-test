package 그래프.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main_다단계칫솔판매 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().solution(new String[]
                {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}, new String[]
                {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}, new String[]
                {"young", "john", "tod", "emily", "mary"}, new int[]
                {12, 4, 2, 5, 10})));
    }

    static class Solution {

        static class Tree extends HashMap<String, Node>{

            public Node put(Node value) {
                return put(value.name, value);
            }

            Node findByName(String name){
                return get(name);
            }
        }

        static class Node {
            Node parent;
            List<Node> children = new ArrayList<>();
            String name;
            int money;

            public Node(String name) {
                this.name = name;
            }

            void sell(int amount){
                money += amount - amount/10;
                if(parent != null && amount / 10 >=1) parent.sell(amount / 10);
            }

            public void setParent(Node parent) {
                this.parent = parent;
                parent.addChild(this);
            }

            void addChild(Node child){
                children.add(child);
            }
        }

        public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            int numNode = enroll.length;
            Tree tree = new Tree();
            tree.put(new Node("minho"));

            for (String enName : enroll) {
                tree.put(new Node(enName));
            }

            for (int i = 0; i < numNode; i++) {
                Node me = tree.findByName(enroll[i]);
                Node parent = tree.findByName(referral[i].equals("-")?"minho":referral[i]);
                me.setParent(parent);
            }

            int numSeller = seller.length;
            for (int i = 0; i < numSeller; i++) {
                Node node = tree.findByName(seller[i]);
                int sellAmount = amount[i] * 100;

                node.sell(sellAmount);
            }

            return Arrays.stream(enroll)
                    .map(s -> tree.findByName(s).money)
                    .mapToInt(i->i).toArray();
        }
    }
}
