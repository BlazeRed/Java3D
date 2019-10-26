package shapes;

import javax.media.j3d.Appearance;
import javax.media.j3d.PolygonAttributes;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;

public class MyColorCube extends ColorCube{
	
	protected PolygonAttributes polyAttrbutes = new PolygonAttributes(); 
	protected Appearance app = new Appearance();
	
	public MyColorCube() {
		new Box(2, 2, 2, app);
		
		polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);
		app.setPolygonAttributes(polyAttrbutes);
		setAppearance(app);
		
		
	}
	

}
