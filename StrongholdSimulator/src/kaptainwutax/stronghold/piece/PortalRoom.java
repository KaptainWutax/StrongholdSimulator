package kaptainwutax.stronghold.piece;

import java.util.List;
import java.util.Random;

import kaptainwutax.stronghold.gen.StrongholdGenerator;
import kaptainwutax.stronghold.util.math.BlockBox;
import kaptainwutax.stronghold.util.math.Direction;

public class PortalRoom extends Piece {

	public PortalRoom(int pieceType, BlockBox boundingBox, Direction facing) {
		super(pieceType);
		this.setOrientation(facing);
		this.boundingBox = boundingBox;
	}

	@Override
	public void populatePieces(StrongholdGenerator gen, Start start, List<Piece> pieceList, Random rand) {
		if(start != null) {
			start.portalRoom = this;
		}
	}

	public static PortalRoom createPiece(List<Piece> list_1, int int_1, int int_2, int int_3, Direction direction_1, int int_4) {
		BlockBox blockBox_1 = BlockBox.rotated(int_1, int_2, int_3, -4, -1, 0, 11, 8, 16, direction_1);
		return Piece.isHighEnough(blockBox_1) && Piece.getNextIntersectingPiece(list_1, blockBox_1) == null ? new PortalRoom(int_4, blockBox_1, direction_1) : null;
	}

}
