package 성영준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] sequence = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            sequence[i] += sequence[i - 1] + Integer.parseInt(st.nextToken());
        int left = 0, right = 1, minLen = Integer.MAX_VALUE;
        while (right <= n)
            if (sequence[right] - sequence[left] >= s)
                minLen = Math.min(minLen, right - left++);
            else
                right++;
        if (minLen == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(minLen);
    }
}
