package shapes;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Matrix3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Cone;

public class Trottola extends Group {
	//bisogna creare tante appearance quante sono le cose che vogliamo colorare in modo differente
	static final protected Appearance appUP = new Appearance() ;
	static final protected Appearance appDOWN = new Appearance() ;
    static final protected Transform3D upTransform = new Transform3D (
                    new Matrix3d(1.0, 0.0, 0.0, 0.0, 0.5, 0.0, 0.0, 0.0, 1.0),
                    new Vector3d(0.0, 0.5, 0.0),
                    1.0);
    static final protected Transform3D downTransform = new Transform3D (
                    new Matrix3d(1.0, 0.0, 0.0, 0.0, -0.5, 0.0 , 0.0, 0.0, 1.0) ,
                    new Vector3d(0.0 , -0.5, 0.0) ,
                    1.0);
    protected TransformGroup upTG = new TransformGroup(upTransform); 
    protected TransformGroup downTG = new TransformGroup(downTransform); 
    protected Cone upCone = new Cone();
    protected Cone downCone = new Cone() ;
    
    public Trottola () {
    	upCone.setAppearance ( appUP ) ; 
    	downCone.setAppearance ( appDOWN ) ; 
    	upTG.addChild (upCone) ;
    	downTG.addChild (downCone) ; 
    	addChild(upTG) ;
    	addChild(downTG ) ;
    	
    	//colore per la parte superiore
    	ColoringAttributes colorUP = new ColoringAttributes(); 
    	colorUP.setColor(0f, 0f, 1f); 
    	appUP.setColoringAttributes(colorUP); 
    	//colore per la parte inferiore
    	ColoringAttributes colorDOWN = new ColoringAttributes(); 
    	colorDOWN.setColor(1.0f, 0f, 0f); 
    	appDOWN.setColoringAttributes(colorDOWN); 
    	//trasparenza
    	//appUP.setTransparencyAttributes(new TransparencyAttributes( TransparencyAttributes.BLENDED, 0.35f)); 
    	//appDOWN.setTransparencyAttributes(new TransparencyAttributes( TransparencyAttributes.BLENDED, 0.35f));
    	
    }
}
