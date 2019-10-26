package shapes;

import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Point3f;

public class MyCylinder extends Shape3D {
	public static final float TOP = 1.0f;
	public static final float BOTTOM = -1.0f;
	protected Point3f v[] = null;
	protected TriangleStripArray triangleStrip = null;
	protected PolygonAttributes polyAttrbutes = new PolygonAttributes ( ) ;
	protected Appearance appearance = new Appearance ( ) ;
	
	public MyCylinder( int steps ) {
		v = new Point3f[(steps+1)*2];
		
		for(int i=0; i<steps; i++) {
			double angle = 2.0*Math.PI*(double)i/(double)steps;
		    float x = (float)Math.sin(angle);
		    float y = (float)Math.cos(angle);
		    v[i*2+0] = new Point3f(x, y, BOTTOM);
		    v[i*2+1] = new Point3f(x,y,TOP);
		}
		
		v[steps*2+0] = new Point3f(0.0f , 1.0f , BOTTOM); v[steps*2+1] = new Point3f(0.0f , 1.0f , TOP);
		int [ ] stripCounts ={( steps+1)*2};
		triangleStrip = new TriangleStripArray((steps+1)*2, GeometryArray.COORDINATES,stripCounts );
		triangleStrip.setCoordinates(0,v);
		setGeometry( triangleStrip );
		//Impostazione aspetto wireframe .
		polyAttrbutes.setPolygonMode(PolygonAttributes.POLYGON_LINE ) ;
		polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE ) ;
		appearance.setPolygonAttributes (polyAttrbutes) ;
		setAppearance ( appearance );	
	}
}
