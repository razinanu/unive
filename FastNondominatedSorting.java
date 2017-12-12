import java.util.ArrayList;
import java.util.List;

import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Population;
import org.moeaframework.core.Solution;
import org.moeaframework.core.comparator.DominanceComparator;
import org.moeaframework.core.comparator.ObjectiveComparator;
import org.moeaframework.core.comparator.ParetoDominanceComparator;

public class FastNondominatedSorting {

	/**
	 * Attribute key for the rank of a solution.
	 */
	public static final String RANK_ATTRIBUTE = "rank";
	/**
	 * Attribute key for the crowding distance of a solution.
	 */
	public static final String CROWDING_ATTRIBUTE = "crowdingDistance";

	/**
	 * The dominance comparator.
	 */
	private final DominanceComparator comparator;

	/**
	 * Constructs a fast non-dominated sorting operator using Pareto dominance.
	 */
	public FastNondominatedSorting() {
		this(new ParetoDominanceComparator());
	}

	/**
	 * Returns the dominance comparator used by this fast non-dominated sorting
	 * routine.
	 * 
	 * @return the dominance comparator used by this fast non-dominated sorting
	 *         routine
	 */
	public DominanceComparator getComparator() {
		return comparator;
	}

	/**
	 * Constructs a fast non-dominated sorting operator using the specified
	 * dominance comparator.
	 * 
	 * @param comparator
	 *            the dominance comparator
	 */
	public FastNondominatedSorting(DominanceComparator comparator) {
		super();
		this.comparator = comparator;
	}

	/**
	 * Performs fast non-dominated sorting on the specified population,
	 * assigning the {@code rank} and {@code crowdingDistance} attributes to
	 * solutions.
	 * 
	 * @param population
	 *            the population whose solutions are to be evaluated
	 */
	public List<Solution> evaluate(Population population) {
		List<Solution> remaining = new ArrayList<Solution>();
		List<Solution> best = new ArrayList<Solution>();

		for (Solution solution : population) {
			remaining.add(solution);
		}

		int rank = 0;

		while (!remaining.isEmpty()) {
			NondominatedPopulation front = new NondominatedPopulation(
					comparator);

			for (Solution solution : remaining) {
				front.add(solution);
			}

			for (Solution solution : front) {
				remaining.remove(solution);
				if (rank == 0) {
					best.add(solution);
					System.out.println("rank 0");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 1) {
					System.out.println("rank 1");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 2) {
					System.out.println("rank 2");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 3) {
					System.out.println("rank 3");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 4) {
					System.out.println("rank 4");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 5) {
					System.out.println("rank 5");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 6) {
					System.out.println("rank 6");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 7) {
					System.out.println("rank 7");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 8) {
					System.out.println("rank 8");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 9) {
					System.out.println("rank 9");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 10) {
					System.out.println("rank 10");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 11) {
					System.out.println("rank 11");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 12) {
					System.out.println("rank 12");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				if (rank == 13) {
					System.out.println("rank 13");
					System.out.print(solution.getObjective(0) + ",");
					System.out.print(solution.getObjective(1) + ",");
					System.out.print(solution.getObjective(2));
					System.out.println();

				}
				solution.setAttribute(RANK_ATTRIBUTE, rank);
			}

			updateCrowdingDistance(front);

			rank++;
		}
		return best;
	}

	/**
	 * Computes and assigns the {@code crowdingDistance} attribute to solutions.
	 * The specified population should consist of solutions within the same
	 * front/rank.
	 * 
	 * @param front
	 *            the population whose solutions are to be evaluated
	 */
	protected void updateCrowdingDistance(Population front) {
		int n = front.size();

		if (n < 3) {
			for (Solution solution : front) {
				solution.setAttribute(CROWDING_ATTRIBUTE,
						Double.POSITIVE_INFINITY);
			}
		} else {
			int numberOfObjectives = front.get(0).getNumberOfObjectives();

			for (Solution solution : front) {
				solution.setAttribute(CROWDING_ATTRIBUTE, 0.0);
			}

			for (int i = 0; i < numberOfObjectives; i++) {
				front.sort(new ObjectiveComparator(i));

				double minObjective = front.get(0).getObjective(i);
				double maxObjective = front.get(n - 1).getObjective(i);

				front.get(0).setAttribute(CROWDING_ATTRIBUTE,
						Double.POSITIVE_INFINITY);
				front.get(n - 1).setAttribute(CROWDING_ATTRIBUTE,
						Double.POSITIVE_INFINITY);

				for (int j = 1; j < n - 1; j++) {
					double distance = (Double) front.get(j).getAttribute(
							CROWDING_ATTRIBUTE);
					distance += (front.get(j + 1).getObjective(i) - front.get(
							j - 1).getObjective(i))
							/ (maxObjective - minObjective);
					front.get(j).setAttribute(CROWDING_ATTRIBUTE, distance);
				}
			}
		}
	}

}
