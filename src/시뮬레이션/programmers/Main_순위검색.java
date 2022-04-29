package 시뮬레이션.programmers;

import java.util.*;
import java.util.stream.Collectors;

public class Main_순위검색 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new String[]
                {"java backend junior pizza 150",
                        "python frontend senior chicken 210",
                        "python frontend senior chicken 150",
                        "cpp backend senior pizza 260",
                        "java backend junior chicken 80",
                        "python backend senior chicken 50"}, new String[]
                {"java and backend and junior and pizza 100",
                        "python and frontend and senior and chicken 200",
                        "cpp and - and senior and pizza 250",
                        "- and backend and senior and - 150",
                        "- and - and - and chicken 100",
                        "- and - and - and - 150"})));
    }

    static class Solution {

        static class Applicant {
            int language;
            int end;
            int carrier;
            int soulFood;
            int score;

            public Applicant(String info) {
                String[] infos = info.split(" ");
                this.language = resolveLanguage(infos[0]);
                this.end = resolveEnd(infos[1]);
                this.carrier = resolveCarrier(infos[2]);
                this.soulFood = resolveSoulFood(infos[3]);
                this.score = Integer.parseInt(infos[4]);
            }
        }

        static class Query {
            int[] language;
            int[] end;
            int[] carrier;
            int[] soulFood;
            int score;

            public Query(String query) {
                String[] fields = query.split(" ");
                this.language = new int[]{resolveLanguage(fields[0])};
                this.end = new int[]{resolveEnd(fields[2])};
                this.carrier = new int[]{resolveCarrier(fields[4])};
                this.soulFood = new int[]{resolveSoulFood(fields[6])};
                if(language[0] == -1) language = new int[]{0,1,2};
                if(end[0] == -1) end = new int[]{0,1};
                if(carrier[0] == -1) carrier = new int[]{0,1};
                if(soulFood[0] == -1) soulFood = new int[]{0,1};

                this.score = Integer.parseInt(fields[7]);
            }
        }


        private static int resolveLanguage(String language) {
            return language.equals("-") ? -1 : language.equals("java") ? 0 : language.equals("python") ? 1 : 2;
        }

        private static int resolveEnd(String end) {
            return end.equals("-") ? -1 : end.equals("backend") ? 0 : 1;
        }

        private static int resolveCarrier(String carrier) {
            return carrier.equals("-") ? -1 : carrier.equals("junior") ? 0 : 1;
        }

        private static int resolveSoulFood(String soulFood) {
            return soulFood.equals("-") ? -1 : soulFood.equals("chicken") ? 0 : 1;
        }

        static int lowerBound(List<Integer> list, int key) {
            int left = 0, right = list.size() - 1;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (list.get(mid) < key)
                    left = mid + 1;
                else
                    right = mid - 1;
            }

            return left;
        }

        public int[] solution(String[] info, String[] query) {

            List<Applicant> applicants = Arrays.stream(info).map(Applicant::new).collect(Collectors.toList());

            List<Integer>[][][][] map = new List[3][2][2][2];
            for (int l = 0; l < 3; l++) {
                for (int e = 0; e < 2; e++) {
                    for (int c = 0; c < 2; c++) {
                        for (int s = 0; s < 2; s++) {
                            map[l][e][c][s] = new ArrayList<>();
                        }
                    }
                }
            }

            for (Applicant a : applicants) {
                map[a.language][a.end][a.carrier][a.soulFood].add(a.score);
            }
            for (int l = 0; l < 3; l++) {
                for (int e = 0; e < 2; e++) {
                    for (int c = 0; c < 2; c++) {
                        for (int s = 0; s < 2; s++) {
                            map[l][e][c][s].sort(Comparator.naturalOrder());
                        }
                    }
                }
            }



            int qLen = query.length;
            int[] ans = new int[qLen];
            for (int qi = 0; qi < qLen; qi++) {
                String qu = query[qi];
                Query q = new Query(qu);
                for (int i : q.language) {
                    for (int j : q.end) {
                        for (int k : q.carrier) {
                            for (int l : q.soulFood) {
                                List<Integer> targetScores = map[i][j][k][l];
                                ans[qi] += targetScores.size() - lowerBound(targetScores, q.score);
                            }
                        }
                    }
                }
            }
            return ans;
        }
    }
}
