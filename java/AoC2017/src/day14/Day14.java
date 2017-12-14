package day14;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day14 {

	public static void main(String[] args) throws IOException {
		String input = "vbqugkhl";
		String test = "flqrgnkx";

		int total = 0;
		
		for(int i = 0; i<128; i++) {
			String row = knotHash(input+"-"+i);
			total += row.chars().filter(c -> c-'0' == 1).count();
		}
		System.out.println(total);
		
		List<int[]> rows = new ArrayList<>();
		for(int i = 0; i<128; i++) {
			rows.add(knotHash(input+"-"+i).chars().map(c -> c-'0').toArray());
		}
		
		//rows.stream().forEachOrdered(n -> System.out.println(Arrays.toString(n)));
		
		int group = 1;
	
		for(int y = 0; y < rows.size(); y++) {
			for(int x = 0; x < rows.get(y).length; x++) {
				Point current = new Point(x, y);
				
				if(rows.get(y)[x] == 1) {
					rows.get(y)[x] = ++group;
					markNeighbors(current, rows, group);
				}
			}
		}
		
		//rows.stream().forEachOrdered(n -> System.out.println(Arrays.toString(n)));
		
		System.out.println(group-1);
	}
	
	public static void markNeighbors(Point point, List<int[]> rows, int group) {
		for(Point pnt : point.getNeighbors()) {
			if(rows.get(pnt.y)[pnt.x] == 1) {
				rows.get(pnt.y)[pnt.x] = group;
				markNeighbors(pnt, rows, group);
			}
		}
	}
	
	public static String knotHash(String input) {
		int skip = 0;
		int position = 0;
		List<Integer> asciiLengths = input.chars().boxed().collect(Collectors.toList());
		asciiLengths.addAll(Arrays.asList(17, 31, 73, 47, 23));
		
		List<Integer> hash = IntStream.range(0, 256).boxed().collect(Collectors.toList());

		for(int q = 0; q < 64; q++) {
			for(int length : asciiLengths) {
				ArrayList<Integer> reverse = new ArrayList<>();
				for(int n = 0; n < length; n++) {
					reverse.add(hash.get((position+n)%hash.size()));
				}
				Collections.reverse(reverse);

				for(int i = 0; i < reverse.size(); i++) {
					hash.set((position+i)%hash.size(), reverse.get(i));
				}
				position += length + skip++;
			}
		}

		ArrayList<Integer> denseHash = new ArrayList<>();
		for(int i = 0; i < 16; i++) {
			int num = hash.get(i*16);
			for(int j = 1; j < 16; j++) {
				num ^= hash.get(j+(i*16));
			}
			denseHash.add(num);
		}
		StringBuilder result = new StringBuilder();
		denseHash.stream().forEachOrdered(n -> result.append(String.format("%8s", Integer.toBinaryString(n)).replace(' ', '0')));
		return result.toString();
	}
}

