package 성영준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2473 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());					// 전체 용액의 수 입니다.
            int[] solution = new int[n];								// 용액의 특성값을 저장할 array입니다.
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++)
                solution[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(solution);										// 용액을 특성값을 기준으로 정렬합니다.

            int c = 0;							// 기준으로 둘 용액의 특성값입니다.
            int a = 0;							// 투 포인터로 탐색할 용액의 특성값 입니다.
            int b = 0;

            if (solution[0] >= 0) {				// 용액들이 모두 산성이라면
                c = 0;							// 가장 낮은 산성들을 출력합니다.
                a = 1;
                b = 2;
            } else if (solution[n - 1] <= 0) {	// 용액들이 모두 알칼리성입니다.
                c = n - 3;
                a = n - 2;
                b = n - 1;
            } else {									// 아니라면
                int min = Integer.MAX_VALUE;			// 가장 낮은 값을 계산할 변수를 선언합니다.
                int standard = -1;						// 기준으로 둘 용액의 특성값입니다.
                portal: while (++standard < n - 2) {	// 기준을 한칸 올려도 남은 용액들이 2개 이상이라면
                    int left = standard + 1;			// 기준보다 한칸 위의 용액과
                    int right = n - 1;					// 맨 끝의 용액을 시작으로
                    while (left < right) {				// 용액들을 섞어봅니다.
                        int target = solution[standard] + solution[left] + solution[right];
                        int targetAbs = Math.abs(target);	// 섞은 용액의 특성값의 합의 절대값이
                        if (targetAbs < min) {				// 현재 가장 낮은 특성값의 합보다 작다면
                            c = standard;					// 이 용액들의 정보를 업데이트 해줍니다.
                            a = left;
                            b = right;
                            min = targetAbs;
                        }
                        if (target < 0)					// 만약 특성값의 합이 알칼리성(음수)을 띈다면
                            left++;						// 왼쪽 용액의 위치를 한칸 올려줍니다.
                        else if (target > 0)			// 반대입니다.
                            right--;
                        else							// 만약 0이라면
                            break portal;				// portal로 이동하여 해당 용액들을 출력해줍니다.
                    }
                }
            }
            System.out.println(solution[c] + " " + solution[a] + " " + solution[b]);
        }
    }
