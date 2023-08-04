package gitCodding;

import java.io.*;
import java.util.*;

public class RecommCand {

	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    int N = Integer.parseInt(br.readLine());
	    int S = Integer.parseInt(br.readLine());
	    
	    int recomm[] = new int[S];
	    List<Integer> students = new ArrayList<Integer>();

	    int cnt[] = new int[S + 1];
	    
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    for(int i = 0; i < S; i++) {
	        recomm[i] = Integer.parseInt(st.nextToken());            
	    }
	    
	    for(int i:recomm) {
	    	// ���� �ѹ��� ��õ�� ���� �ʾҴٸ�
	        if(cnt[i] == 0) {
	        	// �ĺ��� ����� ����Ʋ �������� �۴ٸ�
	        	if(students.size() < N) {
	        		students.add(i);
	        		cnt[i] += 1; // ��õ + 1
	        	}
	        	// ����Ʋ ������ �� á�ٸ�
	        	else {
	        		int s = 0;
	        		int s_v = 100; 
	        		int s_idx = 0;
	        		
	        		for(int j = 0; j < students.size(); j++) {
	        			// j��° �л��� ��õ���� s_v���� �۴ٸ�
	        			if(s_v > cnt[students.get(j)]) {
	        				s_v = cnt[students.get(j)]; // s_v�� ����
	        				s = students.get(j);
	        				s_idx = j; // �ش� �л��� �ε��� ��
	        			}
	        		}
	        			
        			cnt[s] = 0;
        			students.remove(s_idx); // ��õ�� ���� ���� �л� ����
        			students.add(i); // �л� i �߰�
        			cnt[i] += 1;
	        	}
	        }
	        // �̹� ��õ�� ���� �л��̶��
	        else {
	        	cnt[i] += 1;
	        }
	        
	    } 
	    
	    Collections.sort(students);
	    
	    for (int i = 0; i < students.size(); i++) {
			System.out.print(students.get(i)+" ");
		}
	}
}