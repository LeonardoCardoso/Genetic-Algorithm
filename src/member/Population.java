package member;

public class Population {

	Individual[] individuals;

	/** Create a new population */
	public Population(int populationSize, boolean initialize) {

		individuals = new Individual[populationSize];

		/**
		 * Initialize the population when it is not a population in evolving
		 * process
		 */
		if (initialize) {

			/** Create the individuals */
			for (int i = 0; i < size(); i++) {
				Individual newIndividual = new Individual();
				newIndividual.generateIndividual();
				welcomeIndividual(i, newIndividual);
			}

		}
	}

	/** Get individual */
	public Individual getIndividual(int index) {
		return individuals[index];
	}

	/** Get the fittest individual */
	public Individual getFittest() {
		Individual fittest = individuals[0];

		/** Run along the individuals array to find the fittest one */
		for (int i = 1; i < size(); i++) {
			if (fittest.getFitness() <= getIndividual(i).getFitness()) {
				fittest = getIndividual(i);
			}
		}

		return fittest;
	}

	/** Number of individuals */
	public int size() {
		return individuals.length;
	}

	/** Add/modify an individual */
	public void welcomeIndividual(int index, Individual individual) {
		individuals[index] = individual;
	}
}
