package main;

import member.Population;
import util.Algorithm;
import util.Fitness;

public class Main {

	public static int defaultGeneLength = 64;

	public static void main(String[] args) {

		int individualNumber = 50;
		String optimumGenes = generateCandidateSolution();

		System.out.println("Candidate Solution: " + optimumGenes + "\n");

		/** Set a candidate solution */
		Fitness.setSolution(optimumGenes);

		/** Initializa a population */
		Population population = new Population(individualNumber, true);

		/** Evolve the population until candidate/optimum solution is reached */
		int generationCount = 1;

		System.out.println("Generation: " + generationCount + "\tFittest: "
				+ population.getFittest().getFitness() + "\tGenetic Code: "
				+ population.getFittest().getGeneticCode()
				+ " **Initial Generation** ");

		while (population.getFittest().getFitness() < defaultGeneLength) {
			generationCount++;
			population = Algorithm.evolvePopulation(population);

			System.out
					.println("Generation: "
							+ generationCount
							+ "\tFittest: "
							+ population.getFittest().getFitness()
							+ "\tGenetic Code: "
							+ population.getFittest().getGeneticCode()
							+ ((population.getFittest().getFitness() == defaultGeneLength) ? " **Optimum Solution**"
									: ""));

		}

	}

	/** Create a random candidateSolution */
	public static String generateCandidateSolution() {
		String candidateSolution = "";

		/** Create random bytes */
		for (int i = 0; i < defaultGeneLength; i++) {
			candidateSolution += String
					.valueOf((byte) Math.round(Math.random()));
		}

		// candidateSolution =
		// "1111100000000000000000000000000000000000000000000000000000001111";

		return candidateSolution;
	}
}