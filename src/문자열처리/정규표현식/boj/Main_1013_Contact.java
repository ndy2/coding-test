package 문자열처리.정규표현식.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main_1013_Contact {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Pattern pattern = Pattern.compile("(100+1+|01)+");

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String s = pattern.matcher(br.readLine()).matches() ? "YES\n" : "NO\n";
            sb.append(s);
        }

        System.out.print(sb);
    }

}
