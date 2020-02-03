package kaptainwutax.stronghold.piece;

import java.util.List;
import java.util.Random;

import kaptainwutax.stronghold.gen.StrongholdGenerator;
import kaptainwutax.stronghold.util.math.BlockBox;
import kaptainwutax.stronghold.util.math.Direction;

public class Corridor extends Piece {

	private final boolean leftExitExists;
	private final boolean rightExitExixts;

	public Corridor(int pieceType, Random rand, BlockBox boundingBox, Direction facing) {
		super(pieceType);
		this.setOrientation(facing);
		rand.nextInt(5); //Random entrance.
		this.boundingBox = boundingBox;
		this.leftExitExists = rand.nextInt(2) == 0;
		this.rightExitExixts = rand.nextInt(2) == 0;
	}

	@Override
	public void populatePieces(StrongholdGenerator gen, Start start, List<Piece> pieceList, Random rand) {
		this.method_14874(gen, start, pieceList, rand, 1, 1);

		if(this.leftExitExists) {
			this.method_14870(gen, start, pieceList, rand, 1, 2);
		}

		if(this.rightExitExixts) {
			this.method_14873(gen, start, pieceList, rand, 1, 2);
		}
	}

	public static Corridor createPiece(List<Piece> list_1, Random random_1, int int_1, int int_2, int int_3, Direction direction_1, int int_4) {
		BlockBox blockBox_1 = BlockBox.rotated(int_1, int_2, int_3, -1, -1, 0, 5, 5, 7, direction_1);
		return Piece.isHighEnough(blockBox_1) && Piece.getNextIntersectingPiece(list_1, blockBox_1) == null ? new Corridor(int_4, random_1, blockBox_1, direction_1) : null;
	}

}
