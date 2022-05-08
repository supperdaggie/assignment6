package assignment6;

public class Road implements Comparable<Road> {

	private Town a;
	private Town b;
	private String x;
	private int y;

	public Road(Town source, Town destination, String name) {
		
		this(source, destination, 0, name);
	}

	
	public Road(Town source, Town destination, int weight, String name) {
		this.a = source;
		this.b = destination;
		this.y = weight;
		this.x = name;
	}
	
	@Override
	public boolean equals(Object i) {
		if (a.equals(((Road) i).getSource()) && b.equals(((Road) i).getDestination())) {
			return true;
		}
		return false;
	}
	
	public boolean contains(Town town) {
		return a.getName().equals(town.getName()) || b.getName().equals(town.getName());
	}

	@Override
	public int compareTo(Road j) {
		if (this.x == j.x)
			return 0;
		else
			return 1;
	}
	

	public Town getSource() {
		return a;
	}

	public Town getDestination() {
		return b;
	}

	
	public String getName() {
		return x;
	}


	public int getWeight() {
		return y;
	}

	@Override
	public String toString() {
		return "Road [source=" + a + ", destination=" + b + ", name=" + x + ", weight=" + y
				+ "]";
	}
}
