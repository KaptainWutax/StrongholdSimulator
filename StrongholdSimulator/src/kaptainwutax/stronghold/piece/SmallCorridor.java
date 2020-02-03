package kaptainwutax.stronghold.piece;

import java.util.List;
import java.util.Random;

import kaptainwutax.stronghold.util.math.BlockBox;
import kaptainwutax.stronghold.util.math.Direction;

public class SmallCorridor extends Piece {

	public SmallCorridor(int pieceType) {
		super(pieceType);
	}

	public SmallCorridor(int pieceType, BlockBox boundingBox, Direction facing) {
		super(pieceType);
		this.setOrientation(facing);
		this.boundingBox = boundingBox;
	}

	public static BlockBox createBox(List<Piece> pieceList, Random rand, int int_1, int int_2, int int_3, Direction facing) {
		BlockBox box = BlockBox.rotated(int_1, int_2, int_3, -1, -1, 0, 5, 5, 4, facing);
		Piece piece = Piece.getNextIntersectingPiece(pieceList, box);

		if(piece == null) {
			return null;
		} else {
			if(piece.getBoundingBox().minY == box.minY) {
				for(int int_5 = 3; int_5 >= 1; --int_5) {
					box = BlockBox.rotated(int_1, int_2, int_3, -1, -1, 0, 5, 5, int_5 - 1, facing);
					if(!piece.getBoundingBox().intersects(box)) {
						return BlockBox.rotated(int_1, int_2, int_3, -1, -1, 0, 5, 5, int_5, facing);
					}
				}
			}

			return null;
		}
	}

}
