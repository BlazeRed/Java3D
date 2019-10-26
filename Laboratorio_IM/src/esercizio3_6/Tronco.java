package esercizio3_6;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.TriangleArray;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Point3d;

public class Tronco extends Shape3D{
	protected Point3d vertex[] = new Point3d[10];
	protected TriangleStripArray triangles = null;
	protected PolygonAttributes polyAttrbutes = new PolygonAttributes(); 
	protected Appearance appearance = new Appearance();
	//creazione vertici per il tronco di piramide
	public Tronco(double altezza, double baseMinore, double baseMaggiore) {
		vertex[0] = new Point3d(-baseMinore, -baseMinore, altezza/2); 
		vertex[1] = new Point3d(-baseMaggiore, -baseMaggiore, -altezza/2);
		vertex[2] = new Point3d(baseMinore, -baseMinore, altezza/2); 
		vertex[3] = new Point3d(baseMaggiore, -baseMaggiore, -altezza/2); 
		vertex[4] = new Point3d(baseMinore, baseMinore, altezza/2); 
		vertex[5] = new Point3d(baseMaggiore, baseMaggiore, -altezza/2); 
		vertex[6] = new Point3d(-baseMinore, baseMinore, altezza/2); 
		vertex[7] = new Point3d(-baseMaggiore, baseMaggiore, -altezza/2); 
		vertex[8] = vertex[0];
		vertex[9] = vertex[1];
		int [] stripCounts = {(vertex.length)};
		// n° vertici, formato vertici (coordinate), n° vertici di ogni geometria
		triangles = new TriangleStripArray(vertex.length, GeometryArray.COORDINATES, stripCounts);
		triangles.setCoordinates(0, vertex); 
		setGeometry(triangles);
		polyAttrbutes.setPolygonMode(PolygonAttributes.POLYGON_FILL); 
		polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);
		//Applico trasparenza alla piramide per migliorare effetto visivo 
		appearance.setTransparencyAttributes(new TransparencyAttributes( TransparencyAttributes.BLENDED, 0.35f)); 
		appearance.setPolygonAttributes(polyAttrbutes);
		//Colore piramide
		ColoringAttributes color = new ColoringAttributes(); 
		color.setColor(1.0f, 0.6f, 0.2f); 
		appearance.setColoringAttributes(color); 
		setAppearance (appearance);
	}
}
