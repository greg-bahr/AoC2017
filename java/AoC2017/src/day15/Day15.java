package day15;

import java.io.IOException;

public class Day15 {

	public static void main(String[] args) throws IOException {
		long genA = 277;
		long genB = 349;
		
		long genAFactor = 16807;
		long genBFactor = 48271;
		long divisor = 2147483647;
		
		int total = 0;
		
		// part 1
		for(int i = 0; i < 40000000; i++) {
			genA = (genA * genAFactor)%divisor;
			genB = (genB * genBFactor)%divisor;
			
			total += (genA & 0xffff) == (genB & 0xffff) ? 1 : 0;
		}
		System.out.println(total);
		
		total = 0;
		genA = 277;
		genB = 349;
		
		// part 2
		for(int i = 0; i < 5000000; i++) {
			genA = (genA * genAFactor)%divisor;
			genB = (genB * genBFactor)%divisor;
			while(genA % 4 != 0) {
				genA = (genA * genAFactor)%divisor;
			}
			while(genB % 8 != 0) {
				genB = (genB * genBFactor)%divisor;
			}
			
			total += (genA & 0xffff) == (genB & 0xffff) ? 1 : 0;
		}
		
		System.out.println(total);
	}
}

