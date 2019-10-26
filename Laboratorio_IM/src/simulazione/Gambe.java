package simulazione;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;

public class Gambe extends Group{
	
	protected TransformGroup TG = new TransformGroup();
	protected TransformGroup TGsx = new TransformGroup();
	protected TransformGroup TGdx = new TransformGroup();
	
	public Gambe(float posX, float posY, float raggio, float altezza, Appearance app) {
	
		Cylinder sx = new Cylinder(0.5f, 3.5f, Primitive.GENERATE_NORMALS, app);
		Cylinder dx = new Cylinder(0.5f, 3.5f, Primitive.GENERATE_NORMALS, app);
		
		Transform3D possx = new Transform3D();
		Transform3D posdx = new Transform3D();
		possx.setTranslation(new Vector3d(posX - raggio/3, posY - altezza/2, 0));
		posdx.setTranslation(new Vector3d(posX + raggio/3, posY - altezza/2, 0));
		
		TGsx.setTransform(possx);
		TGdx.setTransform(posdx);
		
		TGsx.addChild(sx);
		TGdx.addChild(dx);
		
		TG.addChild(TGsx);
		TG.addChild(TGdx);
		
		addChild(TG);
		
	}

}
