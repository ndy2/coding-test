package 문자열처리.정규표현식.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main_매칭점수 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution("Muzi", new String[]
                {
                        "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"
                }));


//        System.out.println(new 이분탐색.boj.Solution().solution("blind", new String[]
//                {
//                        "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
//                }));
    }

    static class Solution {

        static class Page implements Comparable<Page>{
            int idx;
            String url;
            long baseScore;
            double linkScore;

            public Page(int idx, String url, long score) {
                this.idx = idx;
                this.url = url;
                this.baseScore = score;
            }

            @Override
            public int compareTo(Page o) {
                double score = baseScore+linkScore;
                double oScore = o.baseScore +o.linkScore;
                return oScore == score? idx-o.idx:
                        oScore > score ? 1: -1;
            }
        }


        public int solution(String word, String[] pages) {
            String deliminator = "[^a-zA-Z\\s:]";
            Pattern homePattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S+)\"");
            Pattern linkPattern = Pattern.compile("<a href=\"(\\S+)\"");
            Pattern wordPattern = Pattern.compile("\\b" + word + "\\b" , Pattern.CASE_INSENSITIVE);

            List<String> urls = new ArrayList<>();
            Map<String, Page> pageMap= new HashMap<>();

            for (int i = 0; i < pages.length; i++) {
                String html = pages[i];
                Matcher homeMatcher = homePattern.matcher(html);
                String url = null;
                if (homeMatcher.find()) {
                    url = homeMatcher.group(1);
                    urls.add(url);
                }

                String body = html.split("<body>")[1].split("</body>")[0];
                body = body.replaceAll(deliminator, " ");
                Matcher bodyMatcher = wordPattern.matcher(body);
                long score = bodyMatcher.results().count();
                pageMap.put(url, new Page(i, url, score));
            }

            for (int i = 0; i < pages.length; i++) {

                Page curPage = pageMap.get(urls.get(i));

                String html = pages[i];
                Matcher linkMatcher = linkPattern.matcher(html);
                long linkCount = linkMatcher.results().count();
                linkMatcher = linkPattern.matcher(html);
                while (linkMatcher.find()) {
                    String link = linkMatcher.group(1);
                    Page targetPage = pageMap.get(link);
                    if(targetPage == null) continue;
                    targetPage.linkScore += (double) curPage.baseScore/linkCount;
                }
            }

            return pageMap.values().stream().sorted().findFirst().get().idx;
        }
    }
}
