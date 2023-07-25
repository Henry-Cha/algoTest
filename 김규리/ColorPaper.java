package gitCodding;

import java.io.*;
import java.util.*;

public class ColorPaper {
	
	static int graphs[][];
	static int N;
	static int w;
	static int b;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		graphs = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				graphs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(0, 0, N);
		
		System.out.println(w);
		System.out.println(b);
	}
	
	public static boolean ColorCheck(int x, int y, int size) {
				// �ش� ��ǥ���� ������ ũ�� ��ŭ ��ǥ�� ��
				// �ش� ��ǥ���� ���� �ʴٸ� �� ���� �簢������ ������
				// �ǹǷ� false return
				for(int i = x; i < x + size; i++) {
					for(int j = y; j < y + size; j++) {
						if(graphs[i][j] != graphs[x][y]) {
							return false;
						}
					}
				}
				return true;
	}
	
	public static void DFS(int x, int y, int size) {
		
		if(ColorCheck(x, y, size) == true) {
			// ���� �ش� ��ǥ���� 0�� ���, 
			// ��� + 1
			if(graphs[x][y] == 0) {
				w += 1;
			}
			// ���� �ش� ��ǥ���� 1�� ���, 
			// �Ķ��� + 1 
			if(graphs[x][y] == 1) {
				b += 1;
			}
			return;
		}
		
		// ���� �ش� size ���簢�� ������� �������� ���� ���
		// size�� ���� (���̸� 1/2�� ���̱�)
		int nsize = size / 2;
		
		DFS(x, y, nsize); // 2��и�
		DFS(x + nsize, y, nsize); // 1��и�
		DFS(x, y + nsize, nsize); // 3��и�
		DFS(x + nsize, y + nsize, nsize); // 4��и�
	}
}
