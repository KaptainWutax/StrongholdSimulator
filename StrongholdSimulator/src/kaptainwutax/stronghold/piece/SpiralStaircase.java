package kaptainwutax.stronghold.piece;

import java.util.List;
import java.util.Random;

import kaptainwutax.stronghold.gen.StrongholdGenerator;
import kaptainwutax.stronghold.util.math.BlockBox;
import kaptainwutax.stronghold.util.math.Direction;

public class SpiralStaircase extends Piece {

	private boolean isStructureStart;

	public SpiralStaircase(int pieceType, Random rand, int x, int z) {
		super(pieceType);
		this.isStructureStart = true;
		this.setOrientation(Direction.randomHorizontal(rand));

		if(this.getFacing().getAxis() == Direction.Axis.Z) {
			this.boundingBox = new BlockBox(x, 64, z, x + 5 - 1, 74, z + 5 - 1);
		} else {
			this.boundingBox = new BlockBox(x, 64, z, x + 5 - 1, 74, z + 5 - 1);
		}

	}

	public SpiralStaircase(int pieceType, Random rand, BlockBox boundingBox, Direction facing) {
		super(pieceType);
		this.isStructureStart = false;
		this.setOrientation(facing);
		rand.nextInt(5); //Random entrance.
		this.boundingBox = boundingBox;
	}

	@Override
	public void populatePieces(StrongholdGenerator gen, Start start, List<Piece> pieceList, Random rand) {
		if(this.isStructureStart) {
			gen.currentPiece = FiveWayCrossing.class;
		}

		this.method_14874(gen, start, pieceList, rand, 1, 1);
	}

	public static SpiralStaircase createPiece(List<Piece> list_1, Random random_1, int int_1, int int_2, int int_3, Direction direction_1, int int_4) {
		BlockBox blockBox_1 = BlockBox.rotated(int_1, int_2, int_3, -1, -7, 0, 5, 11, 5, direction_1);
		return Piece.isHighEnough(blockBox_1) && Piece.getNextIntersectingPiece(list_1, blockBox_1) == null ? new SpiralStaircase(int_4, random_1, blockBox_1, direction_1) : null;
	}

}
