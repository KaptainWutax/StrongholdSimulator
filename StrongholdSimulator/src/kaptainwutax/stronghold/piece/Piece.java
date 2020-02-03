package kaptainwutax.stronghold.piece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import kaptainwutax.stronghold.gen.StrongholdGenerator;
import kaptainwutax.stronghold.util.math.BlockBox;
import kaptainwutax.stronghold.util.math.Direction;

public abstract class Piece {

	protected int pieceType;
	public List<Piece> children = new ArrayList<>();

	protected BlockBox boundingBox;

	protected Direction facing;

	public Piece(int pieceType) {
		this.pieceType = pieceType;
	}	

	public void populatePieces(StrongholdGenerator gen, Start start, List<Piece> pieceList, Random rand) {
	}

	public Direction getFacing() {
		return this.facing;
	}

	public BlockBox getBoundingBox() {
		return this.boundingBox;
	}
	
	public void setOrientation(Direction facing) {
		this.facing = facing;
	}

	protected static Piece getNextIntersectingPiece(List<Piece> pieceList, BlockBox box) {
		Iterator<Piece> var2 = pieceList.iterator();

		Piece piece;

		do {
			if(!var2.hasNext()) {
				return null;
			}

			piece = var2.next();
		} while(piece.getBoundingBox() == null || !piece.getBoundingBox().intersects(box));

		return piece;
	}

	protected static boolean isHighEnough(BlockBox box) {
		return box != null && box.minY > 10;
	}

	protected Piece method_14874(StrongholdGenerator gen, Start start, List<Piece> pieceList, Random rand, int int_1, int int_2) {
		Direction facing = this.getFacing();
		
		if(facing == null) {
			return null;
		} else if(facing == Direction.NORTH) {
			return gen.generateAndAddPiece(start, pieceList, rand, this.boundingBox.minX + int_1, this.boundingBox.minY + int_2, this.boundingBox.minZ - 1, facing, this.pieceType);
		} else if(facing == Direction.SOUTH) {
			return gen.generateAndAddPiece(start, pieceList, rand, this.boundingBox.minX + int_1, this.boundingBox.minY + int_2, this.boundingBox.maxZ + 1, facing, this.pieceType);
		} else if(facing == Direction.WEST) {
			return gen.generateAndAddPiece(start, pieceList, rand, this.boundingBox.minX - 1, this.boundingBox.minY + int_2, this.boundingBox.minZ + int_1, facing, this.pieceType);
		} else if(facing == Direction.EAST) {
			return gen.generateAndAddPiece(start, pieceList, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + int_2, this.boundingBox.minZ + int_1, facing, this.pieceType);
		}

		return null;
	}

	protected Piece method_14870(StrongholdGenerator gen, Start start, List<Piece> pieceList, Random rand, int int_1, int int_2) {
		Direction facing = this.getFacing();
		
		if(facing == null) {
			return null;
		} else if(facing == Direction.NORTH) {
			return gen.generateAndAddPiece(start, pieceList, rand, this.boundingBox.minX - 1, this.boundingBox.minY + int_1, this.boundingBox.minZ + int_2, Direction.WEST, this.pieceType);
		} else if(facing == Direction.SOUTH) {
			return gen.generateAndAddPiece(start, pieceList, rand, this.boundingBox.minX - 1, this.boundingBox.minY + int_1, this.boundingBox.minZ + int_2, Direction.WEST, this.pieceType);
		} else if(facing == Direction.WEST) {
			return gen.generateAndAddPiece(start, pieceList, rand, this.boundingBox.minX + int_2, this.boundingBox.minY + int_1, this.boundingBox.minZ - 1, Direction.NORTH, this.pieceType);
		} else if(facing == Direction.EAST) {
			return gen.generateAndAddPiece(start, pieceList, rand, this.boundingBox.minX + int_2, this.boundingBox.minY + int_1, this.boundingBox.minZ - 1, Direction.NORTH, this.pieceType);
		}

		return null;
	}

	protected Piece method_14873(StrongholdGenerator gen, Start start, List<Piece> pieceList, Random rand, int int_1, int int_2) {
		Direction facing = this.getFacing();
		
		if(facing == null) {
			return null;
		} else if(facing == Direction.NORTH) {
			return gen.generateAndAddPiece(start, pieceList, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + int_1, this.boundingBox.minZ + int_2, Direction.EAST, this.pieceType);
		} else if(facing == Direction.SOUTH) {
			return gen.generateAndAddPiece(start, pieceList, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + int_1, this.boundingBox.minZ + int_2, Direction.EAST, this.pieceType);
		} else if(facing == Direction.WEST) {
			return gen.generateAndAddPiece(start, pieceList, rand, this.boundingBox.minX + int_2, this.boundingBox.minY + int_1, this.boundingBox.maxZ + 1, Direction.SOUTH, this.pieceType);
		} else if(facing == Direction.EAST) {
			return gen.generateAndAddPiece(start, pieceList, rand, this.boundingBox.minX + int_2, this.boundingBox.minY + int_1, this.boundingBox.maxZ + 1, Direction.SOUTH, this.pieceType);
		}
		
		return null;
	}

}
