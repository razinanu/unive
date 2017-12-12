import java.util.ArrayList;
import java.util.HashMap;

public class Chromosom {

	String activSystem;
	HashMap<String, Double> evaluateCritic;

	public HashMap<String, Double> getEvaluateCritic() {
		return evaluateCritic;
	}

	public void setEvaluateCritic(HashMap<String, Double> evaluateCritic) {
		this.evaluateCritic = evaluateCritic;
	}

	public String getActivSystem() {
		return activSystem;
	}

	public void setActivSystem(String activSystem) {
		this.activSystem = activSystem;
	}

	double cost;
	double green;
	double comfort;

	public double getCost() {
		return cost;
	}

	public void setCost(double evaluate) {
		this.cost = evaluate;
	}

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
