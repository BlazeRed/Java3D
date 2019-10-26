package shapes;

import javax.media.j3d.Geometry;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Point3d;

public class Octahedron extends VisualObject{

	private static final Point3d P1 = new Point3d(0, 0, 1.0);
    private static final Point3d P2 = new Point3d(0, 0, -1.0);
    private static final Point3d P3 = new Point3d(-1.0, 1.0, 0);
    private static final Point3d P4 = new Point3d ( 1.0 , 1.0, 0);
    private static final Point3d P5 = new Point3d ( 1.0 , -1.0, 0);
    private static final Point3d P6 = new Point3d ( -1.0 , -1.0, 0);

    private static final Point3d [ ] faces = {
    		P1,P3,P4,
    		P1,P5,P4,
    		P1,P5,P6,
    		P1,P3,P6,
    		P2,P3,P4,
    		P2,P5,P4,
    		P2,P5,P6,
    		P2,P3,P6	
    };
    protected Geometry createGeometry ( ) { 
    	TriangleArray triangles ;
    	//TriangleArray.COORDINATES mi dice che i triangoli sono formati da un ARRAY DI COORDINATE
    	triangles = new TriangleArray(faces.length,TriangleArray.COORDINATES);
    	triangles.setCoordinates(0,faces );
    	return triangles ;
    }  
}