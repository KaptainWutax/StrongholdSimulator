package kaptainwutax.stronghold.piece;

import java.util.List;
import java.util.Random;

import kaptainwutax.stronghold.gen.StrongholdGenerator;
import kaptainwutax.stronghold.util.math.BlockBox;
import kaptainwutax.stronghold.util.math.Direction;

public class RightTurn extends Piece {

	public RightTurn(int pieceType, Random rand, BlockBox boundingBox, Direction facing) {
		super(pieceType);
		this.setOrientation(facing);
		rand.nextInt(5); //Random entrance.
		this.boundingBox = boundingBox;
	}

	@Override
	public void populatePieces(StrongholdGenerator gen, Start start, List<Piece> pieceList, Random rand) {
		Direction facing = this.getFacing();

		if(facing != Direction.NORTH && facing != Direction.EAST) {
			this.method_14870(gen, start, pieceList, rand, 1, 1);
		} else {
			this.method_14873(gen, start, pieceList, rand, 1, 1);
		}
	}

	public static RightTurn createPiece(List<Piece> list_1, Random random_1, int int_1, int int_2, int int_3, Direction direction_1, int int_4) {
		BlockBox blockBox_1 = BlockBox.rotated(int_1, int_2, int_3, -1, -1, 0, 5, 5, 5, direction_1);
		return Piece.isHighEnough(blockBox_1) && Piece.getNextIntersectingPiece(list_1, blockBox_1) == null ? new RightTurn(int_4, random_1, blockBox_1, direction_1) : null;
	}

}
