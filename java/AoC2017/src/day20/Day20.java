package day20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.MutableTriple;

public class Day20 {

	public static void main(String[] args) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/day20/input"));
		
		List<MutableTriple<MutableTriple<Integer, Integer, Integer>, 
			MutableTriple<Integer, Integer, Integer>, MutableTriple<Integer, Integer, Integer>>> particles = new ArrayList<>();
		
		for(String line : input) {
			String[] positions = line.trim().split(" ");
			List<MutableTriple<Integer, Integer, Integer>> temps = new ArrayList<>();
			
			for(String pos : positions) {
				pos = pos.replaceAll("[^-?0-9]+", " ");
				List<Integer> temp = Arrays.asList(pos.trim().split(" ")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
				temps.add(new MutableTriple<>(temp.get(0), temp.get(1), temp.get(2)));
			}
			particles.add(new MutableTriple<>(temps.get(0), temps.get(1), temps.get(2)));
		}
			
		int closest = -1;
		MutableTriple<Integer, Integer, Integer> closestParticle = new MutableTriple<>(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
		
		
		for(int i = 0; i < 1000; i++) {
			for(int j = 0; j < particles.size(); j++) {
				MutableTriple<MutableTriple<Integer, Integer, Integer>, 
							  MutableTriple<Integer, Integer, Integer>, 
							  MutableTriple<Integer, Integer, Integer>> particle = particles.get(j);
				
				particle.getMiddle().left += particle.getRight().left;
				particle.getMiddle().middle += particle.getRight().middle;
				particle.getMiddle().right += particle.getRight().right;
				
				particle.getLeft().left += particle.getMiddle().left;
				particle.getLeft().middle += particle.getMiddle().middle;
				particle.getLeft().right += particle.getMiddle().right;
			}
			
			for(MutableTriple<MutableTriple<Integer, Integer, Integer>, 
					MutableTriple<Integer, Integer, Integer>, 
					MutableTriple<Integer, Integer, Integer>> curr : particles) {
				MutableTriple<Integer, Integer, Integer> pos = curr.left;
				
				int dist = Math.abs(pos.left) + Math.abs(pos.middle) + Math.abs(pos.right);
				if(dist < Math.abs(closestParticle.left) + Math.abs(closestParticle.middle) + Math.abs(closestParticle.right)) {
					closestParticle = pos;
					closest = particles.indexOf(curr);
				}
			}
		}
		
		System.out.println(closest);
		
		// part 2
		particles.clear();
		for(String line : input) {
			String[] positions = line.trim().split(" ");
			List<MutableTriple<Integer, Integer, Integer>> temps = new ArrayList<>();
			
			for(String pos : positions) {
				pos = pos.replaceAll("[^-?0-9]+", " ");
				List<Integer> temp = Arrays.asList(pos.trim().split(" ")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
				temps.add(new MutableTriple<>(temp.get(0), temp.get(1), temp.get(2)));
			}
			particles.add(new MutableTriple<>(temps.get(0), temps.get(1), temps.get(2)));
		}
		
		for(int z = 0; z < 1000; z++) {
			for(int j = 0; j < particles.size(); j++) {
				MutableTriple<MutableTriple<Integer, Integer, Integer>, 
							  MutableTriple<Integer, Integer, Integer>, 
							  MutableTriple<Integer, Integer, Integer>> particle = particles.get(j);
				
				particle.getMiddle().left += particle.getRight().left;
				particle.getMiddle().middle += particle.getRight().middle;
				particle.getMiddle().right += particle.getRight().right;
				
				particle.getLeft().left += particle.getMiddle().left;
				particle.getLeft().middle += particle.getMiddle().middle;
				particle.getLeft().right += particle.getMiddle().right;
			}
			
			for(int i = 0; i < particles.size(); i++) {
				boolean collided = false;
				
				MutableTriple<MutableTriple<Integer, Integer, Integer>, 
				MutableTriple<Integer, Integer, Integer>, MutableTriple<Integer, Integer, Integer>> curr = particles.get(i);
				
				Iterator<MutableTriple<MutableTriple<Integer, Integer, Integer>, 
						MutableTriple<Integer, Integer, Integer>, 
						MutableTriple<Integer, Integer, Integer>>> it = particles.iterator();
				
				while(it.hasNext()) {
					MutableTriple<MutableTriple<Integer, Integer, Integer>, 
					MutableTriple<Integer, Integer, Integer>, 
					MutableTriple<Integer, Integer, Integer>> m = it.next();
					
					if(!m.equals(curr) && m.getLeft().equals(curr.getLeft())) {
						collided = true;
						it.remove();
					}
				}
				
				if(collided) particles.remove(curr);
			}
		}
		
		System.out.println(particles.size());
	}
}

