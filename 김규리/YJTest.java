package gitCodding;

import java.io.*;
import java.util.*;

public class YJTest {
	
	static int scores[];
	static int test[];
	static int result; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		scores = new int[10];
		test = new int[10];
		result = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < 10; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}
		
		solution(0);
		
		System.out.println(result);
	
	}
	
	static void solution(int stack) {
		// ���� 10���� ��� ���� �����ߴٸ�
		if(stack == 10) {
			int cnt = 0;
			// ���䰪�̶� ���ؼ� ������ ���� ī��Ʈ
			for(int i = 0; i < 10; i++) {
				if(scores[i] == test[i]){
					cnt += 1;
				}
			}
			// ���� ������ ������ 5�� �̻��̶��
			if(cnt >= 5) {
				result += 1; // ����� + 1
			}
			
			return;
		}
		
		// 5������ �� ��ȣ
		for(int i = 1; i < 6; i++) {
			
			// ���� �������� ���������� ��� ���ٸ� continue
			if(stack > 1 && i == test[stack - 1] && i == test[stack - 2]) {
				continue;
			}
			
			test[stack] = i; // stack��° ������ ���� i�� �� ����
			solution(stack + 1); // ���
			test[stack] = 0;
		}
	}
}
