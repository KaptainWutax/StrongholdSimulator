package kaptainwutax.stronghold.util.math;

import java.util.Random;

public enum Direction {
	
    DOWN(Direction.Axis.Y),
    UP(Direction.Axis.Y),
    NORTH(Direction.Axis.Z),
    SOUTH(Direction.Axis.Z),
    WEST(Direction.Axis.X),
    EAST(Direction.Axis.X);
	
	private static Direction[] HORIZONTALS = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
	private Axis axis;
	
	Direction(Axis axis) {
		this.axis = axis;
	}	
	
	public Axis getAxis() {
		return this.axis;
	}
	
	public static Direction randomHorizontal(Random rand) {
		return HORIZONTALS[rand.nextInt(HORIZONTALS.length)];
	}
	
	public static enum Axis {
		X, Y, Z
	}
	
}
