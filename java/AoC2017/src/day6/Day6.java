package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day6 {

	public static void main(String[] args) throws IOException{
		List<String> input = Files.readAllLines(Paths.get("./src/day6/input"));
		int[] nums = Stream.of(input.get(0).split("\\t"))
						.mapToInt(Integer::parseInt)
						.toArray();
		
		int runs = 0;
		int part = 2;
		List<int[]> states = new ArrayList<>();
		
		while(true)	 {
			if(part == 2 && containsState(states, nums)) {
				System.out.println(states.size() - indexOfArray(states, nums));
				break;
			} else if (part == 1 && containsState(states, nums)) {
				System.out.println(runs);
				break;
			}
			states.add(nums.clone());
			runs++;
			
			int index = getArrayIndex(nums, Arrays.stream(nums).max().getAsInt());
			int max = nums[index];
			nums[index] = 0;
			
			
			for(int x = index+1; x < index+max+1; x++) {
				nums[x%nums.length]++;
			}
		}
	}
	
	public static int getArrayIndex(int[] arr,int value) {

        int k=0;
        for(int i=0;i<arr.length;i++){

            if(arr[i]==value){
                k=i;
                break;
            }
        }
    return k;
	}
	
	public static boolean containsState(List<int[]> states, int[] nums) {
		for(int[] state : states) {
			boolean not = false;
			for(int i = 0; i < state.length; i++) {
				if(state[i] != nums[i]) {
					not = true;
				}
			}
			if(!not) return true;
		}
		return false;
	}
	
	public static int indexOfArray(List<int[]> states, int[] nums) {
		for(int j = 0; j<states.size(); j++) {
			boolean not = false;
			for(int i = 0; i < states.get(j).length; i++) {
				if(states.get(j)[i] != nums[i]) {
					not = true;
				}
			}
			if(!not) return j;
		}
		return -1;
	}

}
