import java.util.Arrays;

public class point {
	private double coords[];
	
	public point(double[] coords) {
		this.coords = Arrays.copyOf(coords, coords.length); 
	}
	
	public double eDistance(point p) {
		return Math.sqrt(this.eDistanceSquared(p));
	}
	
	public double eDistanceSquared(point p) {
		double sum = 0;
		
		if (p.coords.length != this.coords.length) {
			throw new IllegalArgumentException("Mismatched dimensions");
		}
		
		for (int i = 0; i < this.coords.length; i++) {
			sum += Math.pow(this.coords[i] - p.coords[i],2);
		}
		
		return sum;
	}
	
	public double tDistance(point p) {
		double sum = 0;
		
		if (p.coords.length != this.coords.length) {
			throw new IllegalArgumentException("Mismatched dimensions");
		}
		
		for (int i = 0; i < this.coords.length; i++) {
			sum += Math.abs(this.coords[i] - p.coords[i]);
		}
		
		return sum;
	}
	
	public int hashCode() {
		double code = 0;
		
		for (int i = 0; i < this.coords.length; i++) {
			code = code*31 + this.coords[i];
		}
		
		return (int)code;
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if (o.getClass() != point.class) {
			return false;
		}
		
		point p = (point) o;
		
		if (p.coords.length != this.coords.length) {
			return false;
		}
		
		for (int i = 0; i < this.coords.length; i++) {
			if (this.coords[i] != p.coords[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		
		for (int i = 0; i < this.coords.length - 1; i++) {
			result.append(this.coords[i]);
			result.append(" ");
		}
		result.append(this.coords[this.coords.length - 1]);
		
		return result.toString();
	}
	
	public String toStringPretty() {
		StringBuilder result = new StringBuilder();
		
		result.append("(");
		
		for (int i = 0; i < this.coords.length - 1; i++) {
			result.append(this.coords[i]);
			result.append(", ");
		}
		result.append(this.coords[this.coords.length - 1]);
		result.append(")");
		
		return result.toString();
	}
}
