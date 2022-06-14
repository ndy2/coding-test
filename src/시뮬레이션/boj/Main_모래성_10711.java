package 시뮬레이션.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_모래성_10711 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {

        String[] HW = br.readLine().split(" ");
        int H = Integer.parseInt(HW[0]);
        int W = Integer.parseInt(HW[1]);

        if (Integer.min(H, W) < 3) {
            System.out.println(0);
            return;
        }

        int[][] map = new int[H][W];
        for (int r = 0; r < H; r++) {
            String line = br.readLine();
            for (int c = 0; c < W; c++) {
                map[r][c] = line.charAt(c) != '.' ? (line.charAt(c) - '0') : 0;
            }
        }

        int waveCount = 0;
        while (true) {
            List<Pair> destroyed = new ArrayList<>();
            for (int r = 1; r < H - 1; r++) {
                for (int c = 1; c < W - 1; c++) {
                    int strength = map[r][c];
                    if (strength == 0) continue;

                    int numZero = 0;
                    for (int d = 0; d < 8; d++) {
                        int ty = r + dy[d];
                        int tx = c + dx[d];
                        if (map[ty][tx] == 0) numZero++;
                    }

                    if (numZero >= strength) {
                        destroyed.add(new Pair(r, c));
                    }
                }
            }
            if (destroyed.size() == 0) {
                break;
            } else {
                for (Pair pair : destroyed) {
                    map[pair.y][pair.x] = 0;
                }
            }
            waveCount++;
        }
        System.out.println(waveCount);
    }
}
