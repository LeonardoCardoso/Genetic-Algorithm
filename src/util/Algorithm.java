package util;

import member.Individual;
import member.Population;

public class Algorithm {

	/** Rate for maintaining of the originality */
	private static final double uniformRate = 0.5;

	/** Rate allowed to mutate */
	private static final double mutationRate = 0.015;

	/** Tournament set size */
	private static final int tournamentSize = 5;

	/** Keep the fittest individual each evolution */
	private static final boolean elitism = true;

	/** Evolve population */
	public static Population evolvePopulation(Population population) {

		Population newPopulation = new Population(population.size(), false);

		/**
		 * If elitism is wanted, then the best individual of this population
		 * must be keeped
		 */
		int elitismOffset;
		if (elitism) {
			newPopulation.welcomeIndividual(0, population.getFittest());
			elitismOffset = 1;
		} else
			elitismOffset = 0;

		/** Create a crossover in population individuals */
		for (int i = elitismOffset; i < population.size(); i++) {
			Individual firstIndividual = tournamentSelection(population);
			Individual secondIndividual = tournamentSelection(population);

			Individual newIndividual = crossover(firstIndividual,
					secondIndividual);
			newPopulation.welcomeIndividual(i, newIndividual);
		}

		/** Mutate the population */
		for (int i = elitismOffset; i < newPopulation.size(); i++)
			mutate(newPopulation.getIndividual(i));

		return newPopulation;
	}

	/** Crossover the fittest individuals chosen from tournament selection */
	private static Individual crossover(Individual firstIndividual,
			Individual secondIndividual) {

		Individual newIndividual = new Individual();

		/** Run along the genes */
		for (int i = 0; i < firstIndividual.size(); i++) {

			/** Crossover */
			if (Math.random() <= uniformRate)
				newIndividual.setGene(i, firstIndividual.getGene(i));
			else
				newIndividual.setGene(i, secondIndividual.getGene(i));

		}

		return newIndividual;
	}

	/** Mutate the individual */
	private static void mutate(Individual individual) {

		/** Run along the genes */
		for (int i = 0; i < individual.size(); i++) {

			/** Create random gene */
			if (Math.random() <= mutationRate) {
				byte gene = (byte) Math.round(Math.random());
				individual.setGene(i, gene);
			}

		}

	}

	/**
	 * Select the fittest individual for crossover from a small set of randomly
	 * chosen individuals of this population
	 */
	private static Individual tournamentSelection(Population population) {

		/** Create a tournament population */
		Population tournament = new Population(tournamentSize, false);

		/** For each place in the tournament get a random individual */
		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * population.size());
			tournament.welcomeIndividual(i, population.getIndividual(randomId));
		}

		/** Get the fittest individual */
		Individual fittest = tournament.getFittest();
		return fittest;
	}
}
