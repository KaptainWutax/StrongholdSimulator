package kaptainwutax.stronghold.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import kaptainwutax.stronghold.piece.ChestCorridor;
import kaptainwutax.stronghold.piece.Corridor;
import kaptainwutax.stronghold.piece.FiveWayCrossing;
import kaptainwutax.stronghold.piece.LeftTurn;
import kaptainwutax.stronghold.piece.Library;
import kaptainwutax.stronghold.piece.Piece;
import kaptainwutax.stronghold.piece.PieceWeight;
import kaptainwutax.stronghold.piece.PortalRoom;
import kaptainwutax.stronghold.piece.PrisonHall;
import kaptainwutax.stronghold.piece.RightTurn;
import kaptainwutax.stronghold.piece.SmallCorridor;
import kaptainwutax.stronghold.piece.SpiralStaircase;
import kaptainwutax.stronghold.piece.SquareRoom;
import kaptainwutax.stronghold.piece.Stairs;
import kaptainwutax.stronghold.piece.Start;
import kaptainwutax.stronghold.util.Seeds;
import kaptainwutax.stronghold.util.math.BlockBox;
import kaptainwutax.stronghold.util.math.Direction;

public class StrongholdGenerator {

	private final List<PieceWeight> PIECE_WEIGHTS = Arrays.asList(
			new PieceWeight(Corridor.class, 40, 0),
			new PieceWeight(PrisonHall.class, 5, 5),
			new PieceWeight(LeftTurn.class, 20, 0),
			new PieceWeight(RightTurn.class, 20, 0),
			new PieceWeight(SquareRoom.class, 10, 6),
			new PieceWeight(Stairs.class, 5, 5),
			new PieceWeight(SpiralStaircase.class, 5, 5),
			new PieceWeight(FiveWayCrossing.class, 5, 4),
			new PieceWeight(ChestCorridor.class, 5, 4),
			new PieceWeight(Library.class, 10, 2) {
				@Override
				public boolean canSpawnMoreStructuresOfType(int int_1) {
					return super.canSpawnMoreStructuresOfType(int_1) && int_1 > 4;
				}
			}, new PieceWeight(PortalRoom.class, 20, 1) {
		@Override
		public boolean canSpawnMoreStructuresOfType(int int_1) {
			return super.canSpawnMoreStructuresOfType(int_1) && int_1 > 5;
		}
	});

	protected List<PieceWeight> pieceWeights = null;
	public Class<? extends Piece> currentPiece = null;
	protected int totalWeight = 0;
	
	public List<Piece> pieceList = null;
	public BlockBox strongholdBox = null;
	
	public void generate(long worldSeed, int chunkX, int chunkZ) {
		this.pieceWeights = new ArrayList<>(PIECE_WEIGHTS);
		this.pieceList = new ArrayList<>();
		this.currentPiece = null;
		this.totalWeight = 0;
		
		Start startPiece;
		int attemptCount = 0;

		do {
			long layoutSeed = Seeds.setStructureStartSeed(null, worldSeed + (long)(attemptCount++), chunkX, chunkZ);
			Random rand = new Random(layoutSeed);
			startPiece = new Start(rand, (chunkX << 4) + 2, (chunkZ << 4) + 2);
			this.pieceList.add(startPiece);

			startPiece.populatePieces(this, startPiece,this.pieceList, rand);
			List<Piece> pieces = startPiece.children;

			while(!pieces.isEmpty()) {
				int i = rand.nextInt(pieces.size());
				Piece piece = pieces.remove(i);
				piece.populatePieces(this, startPiece, this.pieceList, rand);
			}
		} while(this.pieceList.isEmpty() || startPiece.portalRoom == null);
		
		this.strongholdBox = BlockBox.empty();
		
		this.pieceList.forEach(piece -> {
			this.strongholdBox.encompass(piece.getBoundingBox());
		});
	}

	public Piece generateAndAddPiece(Start startPiece, List<Piece> pieceList, Random rand, int int_1, int int_2, int int_3, Direction facing, int int_4) {
		if (int_4 > 50) {
			return null;
		} else if(Math.abs(int_1 - startPiece.getBoundingBox().minX) <= 112 && Math.abs(int_3 - startPiece.getBoundingBox().minZ) <= 112) {
			Piece piece = this.getNextStructurePiece(startPiece, pieceList, rand, int_1, int_2, int_3, facing, int_4 + 1);
			
			if(piece != null) {
				pieceList.add(piece);
				startPiece.children.add(piece);
			}

			return piece;
		} else {
			return null;
		}
	}

	private Piece getNextStructurePiece(Start startPiece, List<Piece> pieceList, Random rand, int int_1, int int_2, int int_3, Direction facing, int pieceType) {
		if(!this.canAddStructurePieces()) {
			return null;
		} else {
			if(this.currentPiece != null) {
				Piece piece = classToPiece(this.currentPiece, pieceList, rand, int_1, int_2, int_3, facing, pieceType);
				this.currentPiece = null;

				if(piece != null) {
					return piece;
				}
			}

			int int_5 = 0;

			while(int_5 < 5) {
				++int_5;
				int int_6 = rand.nextInt(this.totalWeight);
				Iterator<PieceWeight> pieceWeightsIterator = this.pieceWeights.iterator();

				while(pieceWeightsIterator.hasNext()) {
					PieceWeight pieceWeight = pieceWeightsIterator.next();
					int_6 -= pieceWeight.pieceWeight;

					if (int_6 < 0) {
						if(!pieceWeight.canSpawnMoreStructuresOfType(pieceType) || pieceWeight == startPiece.pieceWeight) {
							break;
						}

						Piece piece = classToPiece(pieceWeight.pieceClass, pieceList, rand, int_1, int_2, int_3, facing, pieceType);

						if(piece != null) {
							++pieceWeight.instancesSpawned;
							startPiece.pieceWeight = pieceWeight;

							if (!pieceWeight.canSpawnMoreStructures()) {
								pieceWeightsIterator.remove();
							}

							return piece;
						}
					}
				}
			}

			BlockBox boundingBox = SmallCorridor.createBox(pieceList, rand, int_1, int_2, int_3, facing);

			if(boundingBox != null && boundingBox.minY > 1) {
				return new SmallCorridor(pieceType, boundingBox, facing);
			} else {
				return null;
			}
		}
	}

	private static Piece classToPiece(Class<? extends Piece> pieceClass, List<Piece> pieceList, Random rand, int int_1, int int_2, int int_3, Direction facing, int int_4) {
		Piece piece = null;

		if (pieceClass == Corridor.class) {
			piece = Corridor.createPiece(pieceList, rand, int_1, int_2, int_3, facing, int_4);
		} else if(pieceClass == PrisonHall.class) {
			piece = PrisonHall.createPiece(pieceList, rand, int_1, int_2, int_3, facing, int_4);
		} else if(pieceClass == LeftTurn.class) {
			piece = LeftTurn.createPiece(pieceList, rand, int_1, int_2, int_3, facing, int_4);
		} else if(pieceClass == RightTurn.class) {
			piece = RightTurn.createPiece(pieceList, rand, int_1, int_2, int_3, facing, int_4);
		} else if(pieceClass == SquareRoom.class) {
			piece = SquareRoom.createPiece(pieceList, rand, int_1, int_2, int_3, facing, int_4);
		} else if(pieceClass == Stairs.class) {
			piece = Stairs.createPiece(pieceList, rand, int_1, int_2, int_3, facing, int_4);
		} else if(pieceClass == SpiralStaircase.class) {
			piece = SpiralStaircase.createPiece(pieceList, rand, int_1, int_2, int_3, facing, int_4);
		} else if(pieceClass == FiveWayCrossing.class) {
			piece = FiveWayCrossing.createPiece(pieceList, rand, int_1, int_2, int_3, facing, int_4);
		} else if(pieceClass == ChestCorridor.class) {
			piece = ChestCorridor.createPiece(pieceList, rand, int_1, int_2, int_3, facing, int_4);
		} else if(pieceClass == Library.class) {
			piece = Library.createPiece(pieceList, rand, int_1, int_2, int_3, facing, int_4);
		} else if(pieceClass == PortalRoom.class) {
			piece = PortalRoom.createPiece(pieceList, int_1, int_2, int_3, facing, int_4);
		}

		return piece;
	}

	private boolean canAddStructurePieces() {
		boolean flag = false;
		this.totalWeight = 0;

		for(PieceWeight pieceWeight: this.pieceWeights) {
			if(pieceWeight.instancesLimit > 0 && pieceWeight.instancesSpawned < pieceWeight.instancesLimit) {
				flag = true;
			}

			totalWeight += pieceWeight.pieceWeight;
		}

		return flag;
	}

}
