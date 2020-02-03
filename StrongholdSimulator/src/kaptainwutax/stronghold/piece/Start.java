package kaptainwutax.stronghold.piece;

import java.util.Random;

public class Start extends SpiralStaircase {

	public PieceWeight pieceWeight;
	public PortalRoom portalRoom;

	public Start(Random rand, int x, int z) {
		super(0, rand, x, z);
	}

}
