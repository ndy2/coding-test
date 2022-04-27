package 그래프.programmers;

import java.util.*;
import java.util.function.Predicate;

public class Main_길찾기게임 {

    public static void main(String[] args) {

        System.out.println(Arrays.deepToString(new Solution().solution(new int[][]{
                {5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}
        })));
    }

    static class Solution {

        static class Node implements Comparable<Node> {

            int idx;
            int y, x;
            static int[][] answer;
            static int preorderIdx = 0;
            static int postOrderIdx = 0;

            Node parent;
            boolean isLeftChild;
            Node left;
            Node right;

            Predicate<Node> leftPredicate;
            Predicate<Node> rightPredicate;

            public Node(int idx, int x, int y) {
                this.idx = idx;
                this.x = x;
                this.y = y;

                leftPredicate = n -> n.x < x;
                rightPredicate = n -> n.x > x;
            }

            public void setLeft(Node left) {
                this.left = left;
                left.isLeftChild = true;
                left.parent = this;
            }

            public void setRight(Node right) {
                this.right = right;
                right.parent = this;
            }

            boolean checkParent(Node n) {
                if (parent == null) return true;
                return isLeftChild ? parent.checkLeft(n) : parent.checkRight(n);
            }

            boolean checkLeft(Node n) {
                return leftPredicate.test(n) && checkParent(n);
            }

            boolean checkRight(Node n) {
                return rightPredicate.test(n) && checkParent(n);
            }

            @Override
            public int compareTo(Node o) {
                if (o.y != y) return o.y - y;
                else return x - o.x;
            }

            static void initAnswer(int n){
                answer = new int[2][n];
            }

            void preorder(){
                answer[0][preorderIdx++] = idx;
                if(left!=null) left.preorder();
                if(right!=null) right.preorder();
            }

            void postOrder(){
                if(left!=null) left.postOrder();
                if(right!=null) right.postOrder();
                answer[1][postOrderIdx++] = idx;
            }
        }

        class TreeRows {
            Map<Integer, List<Node>> treeRows = new HashMap<>();

            TreeRows(Queue<Node> nodes) {

                int prevY = -1;
                List<Node> row = null;
                while (!nodes.isEmpty()) {
                    Node node = nodes.poll();
                    if (prevY != node.y) {
                        treeRows.put(prevY, row);
                        prevY = node.y;
                        row = new LinkedList<>();
                    }
                    row.add(node);
                }
                treeRows.put(prevY, row);
                System.out.println("");
            }

            LinkedList<Node> findByRow(int row) {
                System.out.println("row = " + row);
                if(row == -1) return null;
                return new LinkedList<>(treeRows.get(row));
            }
        }

        public int[][] solution(int[][] nodeinfo) {

            Queue<Node> nodes = new PriorityQueue<>();
            Set<Integer> heightSet = new HashSet<>();
            for (int i = 0; i < nodeinfo.length; i++) {
                int[] xy = nodeinfo[i];
                heightSet.add(xy[1]);
                nodes.offer(new Node(i + 1, xy[0], xy[1]));
            }

            Queue<Integer> heights = new PriorityQueue<>(Comparator.reverseOrder());
            heights.addAll(heightSet);
            int topY = heights.peek();

            heights.add(-1);

            TreeRows treeRows = new TreeRows(nodes);

            while (true) {
                int curRow = heights.poll();
                int childrenRow = heights.peek();
                System.out.println("curRow : " + curRow + " childRow " + childrenRow);
                List<Node> parentNodes = treeRows.findByRow(curRow);
                Queue<Node> childrenNodes = treeRows.findByRow(childrenRow);
                if (childrenNodes == null) break;

                for (Node parent : parentNodes) {
                    if(childrenNodes.isEmpty()) break;
                    if (parent.checkLeft(childrenNodes.peek())) {
                        System.out.println("parent = " + parent.idx +" -> leftChild =" + childrenNodes.peek().idx);
                        parent.setLeft(childrenNodes.poll());
                    }
                    if(childrenNodes.isEmpty()) break;
                    if (parent.checkRight(childrenNodes.peek())) {
                        System.out.println("parent = " + parent.idx +" -> rightChild =" + childrenNodes.peek().idx);
                        parent.setRight(childrenNodes.poll());
                    }
                }
            }

            Node top = treeRows.findByRow(topY).get(0);
            Node.initAnswer(nodeinfo.length);
            top.preorder();
            top.postOrder();

            return Node.answer;
        }
    }


}
