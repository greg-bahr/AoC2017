package day17;

import java.io.IOException;
import java.util.ArrayList;

public class Day17 {
	public static void main(String[] args) throws IOException {
		int steps = 355;
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0);
		int position = 0;
		
		for(int i = 1; i < 2018; i++) {			
			position = ((position+steps)%i)+1;
			list.add(position, i);
		}
		
		System.out.println(list.get(position+1));
	
		position = 0;
		int num = 0;
		
		for(int i = 1; i < 50000001; i++) {
			position = ((position+steps)%i)+1;
			if(position == 1) num = i;
		}
		
		System.out.println(num);
		
	}
}