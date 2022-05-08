package assignment6;

import java.util.Objects;

public class Town implements Comparable<Town> {
	private String name;

	public Town(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		Town other = (Town) o;
		return Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(Town o) {
		if (this.name == o.name) {
			return 0;
		} else {
			return 1;
		}
	}
	
	@Override
	public String toString() {
		return "Town [name=" + name + "]";
	}

}