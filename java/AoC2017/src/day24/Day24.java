package day24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day24 {
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/day24/input"));
		
		ArrayList<Node> nodes = new ArrayList<>();
		for(String line : input) {
			String[] temp = line.trim().split("/");
			
			nodes.add(new Node(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
		}
		
		
		HashMap<Node, Set<Node>> neighbors = new HashMap<>();
		
		for(int i = 0; i < nodes.size(); i++) {
			Set<Node> nodeNeighbors = new HashSet<>();
			for(Node node : nodes) {
				if(!node.equals(nodes.get(i)) && node.x != 0 && (node.contains(nodes.get(i).x) || node.contains(nodes.get(i).y))) {
					nodeNeighbors.add(node);
				}
			}
			neighbors.put(nodes.get(i), nodeNeighbors);
		}
		
		
		Set<ArrayList<Node>> bridges = new HashSet<>();
		
		for(Node n : neighbors.keySet()) {
			if(n.x == 0) {
				ArrayList<Node> b = new ArrayList<>();
				b.add(n);
				bridges.add(b);
				bridges.addAll(findBridges(bridges, neighbors, b, 0));
			}
		}
		
		System.out.println(max);
		
		int str = 0;
		int len = 0;
		for(ArrayList<Node> bridge : bridges) {
			if(bridge.size() > len) {
				len = bridge.size();
				str = sum(bridge);
			} else if(bridge.size() == len && sum(bridge) > str) {
				str = sum(bridge);
			}
		}
		
		System.out.println(str);
	}
	
	public static Set<ArrayList<Node>> findBridges(Set<ArrayList<Node>> bridges, HashMap<Node, Set<Node>> neighbors, ArrayList<Node> bridge, int i) {
		bridges.add(bridge);
		
		if(sum(bridge) > max) max = sum(bridge);
		
		Node prev = bridge.get(bridge.size()-1);
		for(Node n : neighbors.get(prev)) {
			if(!bridge.contains(n) && (prev.x==prev.y || !(prev.get(i) == n.x || prev.get(i) == n.y))) {
				ArrayList<Node> branch = (ArrayList<Node>) bridge.clone();
				branch.add(n);
				// i know its bad but it works so who cares
				int x = -1;
				if(prev.get((i+1)%2) == n.x) {
					x = 0;
				} else {
					x = 1;
				}
				
				findBridges(bridges, neighbors, branch, x);
			}
		}
		return bridges;
	}
	
	public static int sum(ArrayList<Node> bridge) {
		int sum = 0;
		for(Node n : bridge) {
			sum += n.x + n.y;
		}
		return sum;
	}
	
}