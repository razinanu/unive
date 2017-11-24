import java.util.ArrayList;

public class Chromosom {
	ArrayList<Systems> arraySys;

	public ArrayList<Systems> getSystems() {
		return arraySys;
	}

	public void setSystems(ArrayList<Systems> arraySys) {
		this.arraySys = arraySys;
	}

	public ArrayList<Interface> getInf() {
		return inf;
	}

	public void setInf(ArrayList<Interface> inf) {
		this.inf = inf;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double evaluate) {
		this.cost = evaluate;
	}

	ArrayList<Interface> inf;
	double cost;
	double green;

	public double getGreen() {
		return green;
	}

	public void setGreen(double green) {
		this.green = green;
	}

	public double getComfort() {
		return comfort;
	}

	public void setComfort(double comfort) {
		this.comfort = comfort;
	}

	double comfort;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
