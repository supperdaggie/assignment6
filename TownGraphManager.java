package assignment6;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class TownGraphManager implements TownGraphManagerInterface {

	private Graph<Town, Road> graph;

	public TownGraphManager() {
		graph = new Graph<Town, Road>();
	}

	
	public boolean addRoad(String t1, String t2, int weight, String name) {
		if (graph.addEdge(new Town(t1), new Town(t2), weight, name) != null) {			
			return true;
		}
		return false;
	}

	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}
	
	
	@Override
	public boolean deleteRoadConnection(String t1, String t2, String road) {
		if (graph.removeEdge(new Town(t1), new Town(t2), 0, road) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}
	
	@Override
	public boolean containsRoadConnection(String t1, String t2) {
		return graph.containsEdge(new Town(t1), new Town(t2));
		}
	
	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}
	
	@Override
	public String getRoad(String t1, String t2) {
		return ((Road) graph.getEdge(new Town(t1), new Town(t2))).getName();
	}
	
	@Override
	public Town getTown(String name) {
		return new Town(name);
	}
	
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> all = new ArrayList<String>();
		for (Road r : graph.edgeSet()) {
			all.add(r.getName());
			Collections.sort(all);
		}
		return all;
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> all = new ArrayList<String>();
		for (Town town : graph.vertexSet()) {
			all.add(town.getName());
			Collections.sort(all);
		}
		return all;
	}

	@Override
	public ArrayList<String> getPath(String t1, String t2) {
		return graph.shortestPath(new Town(t1), new Town(t2));
	}

	/**
	 * @param f !
	 * @throws IOException !
	 */
	public void populateTownGraph (File f) throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(f));
		String[] string = new String[6];
		String lines = null;

		try {
			while ((lines = buffer.readLine()) != null) {
				string = lines.split(";|\\,");
				addTown(string[2]);
				addTown(string[3]);
				addRoad(string[2], string[3], Integer.parseInt(string[1]), string[0]);
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}

