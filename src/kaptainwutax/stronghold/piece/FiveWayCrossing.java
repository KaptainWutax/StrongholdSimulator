package kaptainwutax.stronghold.piece;

import java.util.List;
import java.util.Random;

import kaptainwutax.stronghold.gen.StrongholdGenerator;
import kaptainwutax.stronghold.util.math.BlockBox;
import kaptainwutax.stronghold.util.math.Direction;

public class FiveWayCrossing extends Piece {

	private final boolean lowerLeftExists;
	private final boolean upperLeftExists;
	private final boolean lowerRightExists;
	private final boolean upperRightExists;

	public FiveWayCrossing(int pieceType, Random rand, BlockBox boudingBox, Direction facing) {
		super(pieceType);
		this.setOrientation(facing);
		rand.nextInt(5); //Random entrance.
		this.boundingBox = boudingBox;
		this.lowerLeftExists = rand.nextBoolean();
		this.upperLeftExists = rand.nextBoolean();
		this.lowerRightExists = rand.nextBoolean();
		this.upperRightExists = rand.nextInt(3) > 0;
	}

	@Override
	public void populatePieces(StrongholdGenerator gen, Start start, List<Piece> pieceList, Random rand) {
		int int_1 = 3;
		int int_2 = 5;
		Direction facing = this.getFacing();

		if (facing == Direction.WEST || facing == Direction.NORTH) {
			int_1 = 8 - int_1;
			int_2 = 8 - int_2;
		}

		this.method_14874(gen, start, pieceList, rand, 5, 1);
		
		if(this.lowerLeftExists) {
			this.method_14870(gen, start, pieceList, rand, int_1, 1);
		}

		if(this.upperLeftExists) {
			this.method_14870(gen, start, pieceList, rand, int_2, 7);
		}

		if(this.lowerRightExists) {
			this.method_14873(gen, start, pieceList, rand, int_1, 1);
		}

		if(this.upperRightExists) {
			this.method_14873(gen, start, pieceList, rand, int_2, 7);
		}
	}
	

	public static FiveWayCrossing createPiece(List<Piece> list_1, Random random_1, int int_1, int int_2, int int_3, Direction direction_1, int int_4) {
		BlockBox blockBox_1 = BlockBox.rotated(int_1, int_2, int_3, -4, -3, 0, 10, 9, 11, direction_1);
		return Piece.isHighEnough(blockBox_1) && Piece.getNextIntersectingPiece(list_1, blockBox_1) == null ? new FiveWayCrossing(int_4, random_1, blockBox_1, direction_1) : null;
	}

}
