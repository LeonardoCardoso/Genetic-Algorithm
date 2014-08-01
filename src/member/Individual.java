package member;

import main.Main;
import util.Fitness;

public class Individual {

	private byte[] genes = new byte[Main.defaultGeneLength];
	private int fitness = -1;

	/** Create a random individual */
	public void generateIndividual() {
		for (int i = 0; i < size(); i++) {
			byte gene = (byte) Math.round(Math.random());
			genes[i] = gene;
		}
	}

	/** Get gene */
	public byte getGene(int index) {
		return genes[index];
	}

	/** Set gene */
	public void setGene(int index, byte value) {
		genes[index] = value;
		fitness = 0;
	}

	/** Get the fitness number */
	public int getFitness() {

		if (this.fitness == 0)
			setFitness(Fitness.getFitness(this));

		return this.fitness;
	}

	/** Set fitness number */
	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	/** Number of genes */
	public int size() {
		return Main.defaultGeneLength;
	}

	/** Get the genes array */
	public String getGeneticCode() {
		String geneString = "";
		for (int i = 0; i < size(); i++) {
			geneString += getGene(i);
		}
		return geneString;
	}
}
