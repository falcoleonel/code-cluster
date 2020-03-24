import java.awt.*;
import java.awt.Color;
import java.util.*;

public class Scale {
    public int x, y, z;
	
    public Scale(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
	public static void reset(Scale point) {
		point.x = 0;
		point.y = 0;
		point.z = 0;
	}
	public static void resetDistPoint(Scale point) {
		point.x = 40;
		point.y = 40;
		point.z = 40;
	}
	public static Scale doScale(Scale point, Scale growth) {
		point.x += growth.x;
		point.y += growth.y;
		point.z += growth.z;
		return point;
	}
}
