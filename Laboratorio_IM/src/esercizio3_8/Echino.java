package esercizio3_8;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class Echino extends Shape3D{
	
	protected Point3f v[] = null;
	protected TriangleStripArray triangleStrip = null;
	protected PolygonAttributes polyAttributes = new PolygonAttributes(); 
	protected Appearance appearance = new Appearance();
	
	public Echino(float raggio, float altezza) {
		float raggioS = raggio*2;
		float raggioI = raggio;
		int steps = 30;
		float top = altezza/2;
		float bottom = -altezza/2;
		v = new Point3f[(steps+1)*2];
		//ciclo per la generazione dei punti
		for (int i = 0; i < steps; i++) {
			double angle = 2.0*Math.PI*(double)i/(double)steps; 
			float xI = (float)Math.sin(angle)*raggioI;
			float yI = (float)Math.cos(angle)*raggioI;
			float xS = (float)Math.sin(angle)*raggioS;
			float yS = (float)Math.cos(angle)*raggioS;
			v[i*2] = new Point3f(xI, yI, bottom);
			v[i*2+1] = new Point3f(xS, yS, top);
		}
	
		v[steps*2] = new Point3f(0.0f, raggioI, bottom); 
		v[steps*2+1] = new Point3f(0.0f, raggioS, top);
		int[] stripCounts = {(steps+1)*2};
		triangleStrip = new TriangleStripArray((steps+1)*2,GeometryArray.COORDINATES, stripCounts); 
		triangleStrip.setCoordinates(0, v);
		GeometryInfo geometry = new GeometryInfo(triangleStrip); 
		NormalGenerator normal = new NormalGenerator(); 
		normal.generateNormals(geometry); 
		setGeometry(triangleStrip); 
		setGeometry(geometry.getGeometryArray());
		//Impostazione aspetto wireframe (tramite FILL riempio l’intera figura) 
		polyAttributes.setPolygonMode(PolygonAttributes.POLYGON_FILL); 
		polyAttributes.setCullFace(PolygonAttributes.CULL_NONE); 
		appearance.setPolygonAttributes(polyAttributes);
		ColoringAttributes color = new ColoringAttributes(); 
		color.setColor(0.5f, 0.5f, 0.4f);
		appearance.setColoringAttributes(color);
		Material material = new Material(); 
		appearance.setMaterial(material); 
		setAppearance(appearance);
	}
}
