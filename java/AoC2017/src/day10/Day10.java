package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day10 {
	public static void main(String[] args) throws IOException {
		
		List<String> lines = Files.readAllLines(Paths.get("./src/day10/input"));
		int[] lengths = Stream.of(lines.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
		
		// part 1
		List<Integer> hash = IntStream.range(0, 256).boxed().collect(Collectors.toList());
		int skip = 0;
		int position = 0;
		
		for(int length : lengths) {
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
		
		System.out.println(""+(hash.get(0)*hash.get(1)));
		
		// part 2
		skip = 0;
		position = 0;
		List<Integer> asciiLengths = lines.get(0).chars().boxed().collect(Collectors.toList());
		asciiLengths.add(17);
		asciiLengths.add(31);
		asciiLengths.add(73);
		asciiLengths.add(47);
		asciiLengths.add(23);
		hash = IntStream.range(0, 256).boxed().collect(Collectors.toList());
		
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
		denseHash.stream().forEachOrdered(n -> System.out.print(String.format("%02X", n).toLowerCase()));
	}
}
