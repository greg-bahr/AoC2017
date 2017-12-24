package day23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.math3.primes.Primes;

public class Day23 {
	public static void main(String[] args) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/day23/input"));
		
		Program prg = new Program(0, input);
		
		while(!prg.isTerminated()) {
			prg.loop();
		}
		
		System.out.println(prg.getAmt());
		
		long h = 0;
		for(int b = 106500; b <= 123500; b += 17) {
			if(!Primes.isPrime(b)) h++;
		}
		System.out.println((int)h);
		
//		long b = 106500;
//		long c = 123500;
//		long h = 0;
//		
//		for(int i = 0; i < 1000; i++) {
//			long f = 1;
//			long d = 2;
//			long e = 2;
//			long g = 2;
//			while(g != 0) {
//				while(g != 0) {
//					g = (g*e)-b;
//					
//					if(g == 0) { // this only works when b is not prime
//						f = 0;
//					}
//					e++;
//					g = e-b;
//				}
//				
//				d++;
//				g = d-b;
//			}
//			
//			if(f == 0) {
//				h++;
//			}
//			b += 17;
//		}
//		
//		System.out.println(h);
	}
}