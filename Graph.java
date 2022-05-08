package assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<E, V> implements GraphInterface<Town, Road> {

	private Set<Town> ver = new HashSet<Town>();
	private Set<Road> ed = new HashSet<Road>();
	private Map<String, Town> tMap = new HashMap<String, Town>();
	ArrayList<String> shortestPath = new ArrayList<String>();
	
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
			throw new IllegalArgumentException();
		}
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		ed.add(newRoad);

		return newRoad;
	}

	@Override
	public boolean addVertex(Town v) {
		boolean e = true;
		if (v == null) {
			throw new NullPointerException();
		}
		if (!(ver.contains(v))) {
			ver.add(v);
			return true;
		}
		return e;
	}
	
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road road = null;
	    for (Road n : ed) {
	    	if (n.contains(destinationVertex) && n.contains(sourceVertex) && (weight > -1) && description != null) {
	    		road = n;
	        }
	    }
	    if (ed.remove(road)) {
	      return road;
	    }
	    return null;
	  }

	@Override
	public boolean removeVertex(Town v) {
		if (v == null) {
			return false;
		}
		return ver.remove(v);
	}
	
	@Override
	public Set<Road> edgeSet() {
		return ed;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> townEdges = new HashSet<Road>();
		for (Road r : ed) {
			if (r.contains(vertex)) {
				townEdges.add(r);
			}
		}
		return townEdges;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		boolean contains = false;
		for (Road e : ed) {
			if (e.contains(destinationVertex) && e.contains(sourceVertex)) {
				contains = true;
			}
		}
		return contains;
	}

	@Override
	public boolean containsVertex(Town v) {
		return ver.contains(v);
	}

	@Override
	public Set<Town> vertexSet() {
		return ver;
	}
	
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (containsEdge(sourceVertex, destinationVertex)) {
			for (Road e : ed) {
				if (e.contains(destinationVertex) && e.contains(sourceVertex)) {
					return new Road(sourceVertex, destinationVertex, e.getWeight(), e.getName());
				}
			}
		}
		return null;
	}
	
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		Town d = destinationVertex;
		while (!d.equals(sourceVertex)) {
			if (!tMap.containsKey(d.getName())) {
				shortestPath.clear();
				break;
			}
			Town t1 = tMap.get(d.getName());
			if (t1 == null) {
				return shortestPath;
			}
			Road r = getEdge(t1, d);
			shortestPath.add(0, t1.getName() + " via " + r.getName() + " to " + d.getName() + " " + r.getWeight() + " mi");
			d = t1;
		}
		return shortestPath;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		Set<Town> visit = new HashSet<Town>();
		ArrayList<Town> noVisit = new ArrayList<Town>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (Town t : ver) {
			noVisit.add(t);
			map.put(t.getName(), Integer.MAX_VALUE);
			tMap.put(t.getName(), null);
		}
		map.put(sourceVertex.getName(), 0);
		while (!noVisit.isEmpty()) {
			int s = 0;
			for (int i = 0; i < noVisit.size(); i++) {
				Town unvisitedTown = noVisit.get(i);
				if (map.get(noVisit.get(s).getName()) > map.get(unvisitedTown.getName())) {
					s = i;
				}
			}
			Town closestTown = noVisit.remove(s);
			visit.add(closestTown);
			for (Road edge : edgesOf(closestTown)) {
				Town adj = edge.getDestination();
				if (adj.equals(closestTown)) {
					adj = edge.getSource();
				}
				if (visit.contains(adj)) {
					continue;
				}
				int adjDistance = map.get(closestTown.getName()) + edge.getWeight();
				if (adjDistance < map.get(adj.getName())) {
					map.put(adj.getName(), adjDistance);
					tMap.put(adj.getName(), closestTown);
				}
			}
		}

	}

}