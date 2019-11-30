import java.awt.*;
import java.awt.Color;
import java.util.*;

public class Translation {
    public int x, y, z;

    public Translation(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
	public static void reset(Translation point) {
		point.x = 0;
		point.y = 0;
		point.z = 0;
	}
	public static Translation traslacion(Translation point, Translation movement) {
		point.x += movement.x;
		point.y += movement.y;
		point.z += movement.z;
		return point;
	}
}