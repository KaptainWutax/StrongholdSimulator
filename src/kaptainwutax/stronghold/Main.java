package kaptainwutax.stronghold;

import kaptainwutax.stronghold.gen.StrongholdGenerator;
import kaptainwutax.stronghold.piece.Piece;
import kaptainwutax.stronghold.piece.PortalRoom;

public class Main {

	public static void main(String[] args) {
		StrongholdGenerator gen = new StrongholdGenerator();
		gen.generate(1234L, -38, 90);
		
		for(Piece piece: gen.pieceList) {
			if(piece instanceof PortalRoom) {
				System.out.print("===> ");
			}
			
			System.out.print(piece.getClass().getSimpleName() + ": ");			
			System.out.println(piece.getBoundingBox().minX + ", " + piece.getBoundingBox().minZ);
		}
	}	
	
}
