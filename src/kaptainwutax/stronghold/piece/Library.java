package kaptainwutax.stronghold.piece;

import java.util.List;
import java.util.Random;

import kaptainwutax.stronghold.util.math.BlockBox;
import kaptainwutax.stronghold.util.math.Direction;

public class Library extends Piece {

	public Library(int pieceType, Random rand, BlockBox boundingBox, Direction facing) {
		super(pieceType);
		this.setOrientation(facing);
		rand.nextInt(5); //Random entrance.
		this.boundingBox = boundingBox;
	}

	public static Library createPiece(List<Piece> list_1, Random random_1, int int_1, int int_2, int int_3, Direction direction_1, int int_4) {
		BlockBox blockBox_1 = BlockBox.rotated(int_1, int_2, int_3, -4, -1, 0, 14, 11, 15, direction_1);
		if (!Piece.isHighEnough(blockBox_1) || Piece.getNextIntersectingPiece(list_1, blockBox_1) != null) {
			blockBox_1 = BlockBox.rotated(int_1, int_2, int_3, -4, -1, 0, 14, 6, 15, direction_1);
			if (!Piece.isHighEnough(blockBox_1) || Piece.getNextIntersectingPiece(list_1, blockBox_1) != null) {
				return null;
			}
		}

		return new Library(int_4, random_1, blockBox_1, direction_1);
	}

}
