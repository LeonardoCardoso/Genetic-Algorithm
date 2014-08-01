package util;

import main.Main;
import member.Individual;

public class Fitness {

	static byte[] solution = new byte[Main.defaultGeneLength];

	/** Set a candidate solution as a byte array */
	public static void setSolution(byte[] newSolution) {
		solution = newSolution;
	}

	/**
	 * Covert a candidate solution as a string to a candidate solution as a byte
	 * array
	 */
	public static void setSolution(String newSolution) {

		solution = new byte[newSolution.length()];

		/**
		 * Run along the string converting its char as a byte
		 */
		for (int i = 0; i < newSolution.length(); i++) {
			String character = newSolution.substring(i, i + 1);
			if (character.contains("0") || character.contains("1")) {
				solution[i] = Byte.parseByte(character);
			} else
				solution[i] = 0;

		}

	}

	/**
	 * Calculate inidividuals fitness by comparing it to the candidate solution.
	 * How many genes are equal to the candidate solution.
	 */
	public static int getFitness(Individual individual) {
		int fitness = 0;

		/**
		 * Run along the individuals genes and compare them to the candidates
		 * genes
		 */
		for (int i = 0; i < individual.size() && i < solution.length; i++) {
			if (individual.getGene(i) == solution[i])
				fitness++;
		}

		return fitness;
	}
}
