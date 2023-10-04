package 성영준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1644 {
    static int[] primes;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int target = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        if (target == 1)
            System.out.println(0);
        else {
            setPrimeNumber(target);
            System.out.println(setSequentialSumOfDecimals(target));
        }
    }

    private static int setSequentialSumOfDecimals(int target) {
        int sum = primes[0];
        int last = primes.length - 1;
        int left = 0;
        int right = 0;
        int cnt = 0;
        while (right < last) {
            if (sum < target)
                sum += primes[++right];
            else if (sum > target)
                sum -= primes[left++];
            else {
                cnt++;
                sum += primes[++right];
            }
        }
        return cnt;
    }

    static void setPrimeNumber(int target) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] eratosthenes = new boolean[target + 1];
        int last = (int) Math.sqrt(target);
        for (int i = 2; i <= last; i++)
            if (!eratosthenes[i]) {
                q.add(i);
                for (int j = i * 2; j <= target; j += i)
                    eratosthenes[j] = true;
            }
        for (int i = last + 1; i <= target; i++)
            if (!eratosthenes[i])
                q.add(i);
        primes = new int[q.size() + 1];
        Iterator<Integer> it = q.iterator();
        int i = 0;
        while (it.hasNext())
            primes[i++] = it.next();
    }
}
