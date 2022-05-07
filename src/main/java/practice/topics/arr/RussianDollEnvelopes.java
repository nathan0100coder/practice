package practice.topics.arr;

import java.util.Arrays;
import java.util.Comparator;

public class RussianDollEnvelopes {

    public static void main(String[] args) {
        int[][] envelopers = { { 4, 3 }, { 1, 2 }, { 5, 7 }, { 5, 3 }, { 1, 1 }, { 4, 9 } };
        System.out.println(maxEnvelopes(envelopers));
    }

    static int maxEnvelopes(int[][] es) {
        if (es == null || es.length == 0 || es[0] == null || es[0].length != 2) {
            return 0;
        }
        Dot[] dots = new Dot[es.length];
        for (int i = 0; i < es.length; i++) {
            dots[i] = new Dot(es[i][0], es[i][1]);
        }
        Arrays.sort(dots, new DotComparator());
        int[] ends = new int[es.length];
        ends[0] = dots[0].h;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < dots.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (dots[i].h > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = dots[i].h;
        }
        return right + 1;
    }
    static class Dot {

        public int w;
        public int h;
        public Dot(int weight, int hight) {
            w = weight;
            h = hight;
        }

    }
    static class DotComparator implements Comparator<Dot> {
        @Override
        public int compare(Dot arg0, Dot arg1) {
            if (arg0.w != arg1.w) {
                return arg0.w - arg1.w;
            } else {
                return arg1.h - arg0.h;
            }
        }

    }
}
