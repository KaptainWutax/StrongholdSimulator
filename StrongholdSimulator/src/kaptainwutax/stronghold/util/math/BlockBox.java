package kaptainwutax.stronghold.util.math;

public class BlockBox {
	
	public int minX;
   	public int minY;
   	public int minZ;
   	public int maxX;
   	public int maxY;
   	public int maxZ;

   	public BlockBox(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
	   this.minX = minX;
      	this.minY = minY;
      	this.minZ = minZ;
      	this.maxX = maxX;
      	this.maxY = maxY;
      	this.maxZ = maxZ;
	}
   
	public static BlockBox empty() {
		return new BlockBox(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	public static BlockBox rotated(int int_1, int int_2, int int_3, int int_4, int int_5, int int_6, int int_7, int int_8, int int_9, Direction direction_1) {
		switch(direction_1) {
			case NORTH:
				return new BlockBox(int_1 + int_4, int_2 + int_5, int_3 - int_9 + 1 + int_6, int_1 + int_7 - 1 + int_4, int_2 + int_8 - 1 + int_5, int_3 + int_6);
			case SOUTH:
				return new BlockBox(int_1 + int_4, int_2 + int_5, int_3 + int_6, int_1 + int_7 - 1 + int_4, int_2 + int_8 - 1 + int_5, int_3 + int_9 - 1 + int_6);
			case WEST:
				return new BlockBox(int_1 - int_9 + 1 + int_6, int_2 + int_5, int_3 + int_4, int_1 + int_6, int_2 + int_8 - 1 + int_5, int_3 + int_7 - 1 + int_4);
			case EAST:
				return new BlockBox(int_1 + int_6, int_2 + int_5, int_3 + int_4, int_1 + int_9 - 1 + int_6, int_2 + int_8 - 1 + int_5, int_3 + int_7 - 1 + int_4);
			default:
				return new BlockBox(int_1 + int_4, int_2 + int_5, int_3 + int_6, int_1 + int_7 - 1 + int_4, int_2 + int_8 - 1 + int_5, int_3 + int_9 - 1 + int_6);
		}
	}

	public boolean intersects(BlockBox box) {
	   return this.maxX >= box.minX && this.minX <= box.maxX && this.maxZ >= box.minZ && this.minZ <= box.maxZ && this.maxY >= box.minY && this.minY <= box.maxY;
	}
	
	public void encompass(BlockBox blockBox_1) {
		this.minX = Math.min(this.minX, blockBox_1.minX);
		this.minY = Math.min(this.minY, blockBox_1.minY);
		this.minZ = Math.min(this.minZ, blockBox_1.minZ);
		this.maxX = Math.max(this.maxX, blockBox_1.maxX);
		this.maxY = Math.max(this.maxY, blockBox_1.maxY);
		this.maxZ = Math.max(this.maxZ, blockBox_1.maxZ);
   }

}
