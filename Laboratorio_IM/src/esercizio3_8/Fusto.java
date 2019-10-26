package esercizio3_8;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;

public class Fusto extends Cylinder{
	
	public Fusto(float radius, float height) {
		super(radius, height, Primitive.GENERATE_NORMALS, 20, 1, createApp());
	}

	private static Appearance createApp() {
		Appearance appearance = new Appearance();
		Material material = new Material();
		ColoringAttributes color = new ColoringAttributes(); 
		color.setColor(0.5f, 0.5f, 0.4f); 
		appearance.setColoringAttributes(color); 
		appearance.setMaterial(material);
		return appearance;
	}
}
