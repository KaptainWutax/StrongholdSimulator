package kaptainwutax.stronghold.piece;

public class PieceWeight {

	public Class<? extends Piece> pieceClass;
	public final int pieceWeight;
	public int instancesSpawned;
	public int instancesLimit;

	public PieceWeight(Class<? extends Piece> pieceClass, int pieceWeight, int instancesLimit) {
		this.pieceClass = pieceClass;
		this.pieceWeight = pieceWeight;
		this.instancesLimit = instancesLimit;
	}

	public boolean canSpawnMoreStructuresOfType(int p_75189_1_)  {
		return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
	}

	public boolean canSpawnMoreStructures() {
		return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
	}

}
