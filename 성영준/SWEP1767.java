package 성영준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SWEP1767 {
    static BufferedReader br;
    static char[][] processor;
    static int n, maxCores, minLen;
    static List<int[]> cores;

    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            setProcessor();
            setFixCore();
            getFreeCore();
            dfs(0, cores.size(), 0, minLen);
            sb.append(minLen).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int idx, int size, int addCores, int addLen) {
        if (maxCores - addCores > size - idx)
            return;
        if (idx == size) {
            if (maxCores < addCores) {
                maxCores = addCores;
                minLen = addLen;
            } else if (maxCores == addCores && minLen > addLen)
                minLen = addLen;
            return;
        }
        int[] candidate = cores.get(idx);
        for (int i = 0; i < 4; i++) {
            if (!checkLine(i, candidate[0], candidate[1]))
                continue;
            dfs(idx + 1, size, addCores + 1, addLen + layLine(i, candidate[0], candidate[1]));
            recoverLine(i, candidate[0], candidate[1]);
        }
        dfs(idx + 1, size, addCores, addLen);
    }

    private static void getFreeCore() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (processor[i][j] == '1')
                    cores.add(new int[] { i, j });
    }

    private static boolean checkLine(int dirction, int y, int x) {
        if (dirction == 0) {
            while (--y >= 0)
                if (processor[y][x] != '0')
                    return false;
        } else if (dirction == 1) {
            while (++x < n) {
                if (processor[y][x] != '0')
                    return false;
            }
        } else if (dirction == 2) {
            while (++y < n)
                if (processor[y][x] != '0')
                    return false;
        } else if (dirction == 3) {
            while (--x >= 0)
                if (processor[y][x] != '0')
                    return false;
        }
        return true;
    }

    private static void recoverLine(int dirction, int y, int x) {
        processor[y][x] = '1';
        if (dirction == 0)
            while (--y >= 0)
                processor[y][x] = '0';
        else if (dirction == 1)
            while (++x < n)
                processor[y][x] = '0';
        else if (dirction == 2)
            while (++y < n)
                processor[y][x] = '0';
        else if (dirction == 3)
            while (--x >= 0)
                processor[y][x] = '0';
    }

    private static int layLine(int dirction, int y, int x) {
        int sum = -1;
        if (dirction == 0)
            while (y >= 0) {
                processor[y--][x] = '2';
                sum++;
            }
        else if (dirction == 1)
            while (x < n) {
                processor[y][x++] = '2';
                sum++;
            }
        else if (dirction == 2)
            while (y < n) {
                processor[y++][x] = '2';
                sum++;
            }
        else if (dirction == 3)
            while (x >= 0) {
                processor[y][x--] = '2';
                sum++;
            }
        return sum;
    }

    private static void setFixCore() {
        boolean[][] visited = new boolean[4][n];
        boolean[] flag = new boolean[4];
        int bound = n / 2 + 1, flagCnt = 4;
        int end = n, end2 = n - 1;
        for (int start = 0; start < bound; start++) {
            if (!flag[0]) {
                List<Integer> candidte = new LinkedList<>();
                for (int i = 0; i < start; i++) {
                    if (processor[start][i] != '1')
                        continue;
                    flag[0] = true;
                    break;
                }
                for (int i = start; i < end; i++) {
                    if (processor[start][i] != '1')
                        continue;
                    if (visited[0][i]) {
                        flag[0] = true;
                        continue;
                    }
                    candidte.add(i);
                    visited[0][i] = true;
                }
                if (!flag[0])
                    for (int i = end; i < n; i++) {
                        if (processor[start][i] != '1')
                            continue;
                        flag[0] = true;
                        break;
                    }
                for (int i : candidte) {
                    minLen += layLine(0, start, i);
                }
                if (flag[0])
                    flagCnt--;
            }
            if (!flag[1]) {
                List<Integer> candidte = new LinkedList<>();
                for (int i = n - 1; i > end2; i--) {
                    if (processor[i][end2] != '1')
                        continue;
                    flag[1] = true;
                    break;
                }
                for (int i = end2; i >= start; i--) {
                    if (processor[i][end2] != '1')
                        continue;
                    if (visited[1][i]) {
                        flag[1] = true;
                        continue;
                    }
                    candidte.add(i);
                    visited[1][i] = true;
                }
                if (!flag[1])
                    for (int i = start - 1; i >= 0; i--) {
                        if (processor[i][end2] != '1')
                            continue;
                        flag[1] = true;
                        break;
                    }
                for (int i : candidte) {
                    minLen += layLine(1, i, end2);
                }
                if (flag[1])
                    flagCnt--;
            }
            if (!flag[2]) {
                List<Integer> candidte = new LinkedList<>();
                for (int i = n - 1; i > end2; i--) {
                    if (processor[end2][i] != '1')
                        continue;
                    flag[2] = true;
                    break;
                }
                for (int i = end2; i >= start; i--) {
                    if (processor[end2][i] != '1')
                        continue;
                    if (visited[2][i]) {
                        flag[2] = true;
                        continue;
                    }
                    candidte.add(i);
                    visited[2][i] = true;
                }
                if (!flag[2])
                    for (int i = start - 1; i >= 0; i--) {
                        if (processor[end2][i] != '1')
                            continue;
                        flag[2] = true;
                        break;
                    }
                for (int i : candidte) {
                    minLen += layLine(2, end2, i);
                }
                if (flag[2])
                    flagCnt--;
            }
            if (!flag[3]) {
                List<Integer> candidte = new LinkedList<>();
                for (int i = 0; i < start; i++) {
                    if (processor[i][start] != '1')
                        continue;
                    flag[3] = true;
                    break;
                }
                for (int i = start; i < end; i++) {
                    if (processor[i][start] != '1')
                        continue;
                    if (visited[3][i]) {
                        flag[3] = true;
                        continue;
                    }
                    candidte.add(i);
                    visited[3][i] = true;
                }
                if (!flag[3])
                    for (int i = end; i < n; i++) {
                        if (processor[i][start] != '1')
                            continue;
                        flag[3] = true;
                        break;
                    }
                for (int i : candidte) {
                    minLen += layLine(3, i, start);
                }
                if (flag[3])
                    flagCnt--;
            }
            if (flagCnt == 0)
                break;
            end--;
            end2--;
        }
    }

    private static void setProcessor() throws NumberFormatException, IOException {
        n = Integer.parseInt(br.readLine());
        maxCores = 0;
        minLen = 0;
        cores = new ArrayList<>();
        processor = new char[n][n];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < n; j++)
                processor[i][j] = line[j * 2];
        }
    }
}