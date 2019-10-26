package shapes;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class NewShape extends Applet {
	
	public NewShape() {
		setLayout(new BorderLayout()); //layout manager del container
        //trova la miglior configurazione grafica per il sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        // Canvas3: si occupa del rendering 3D on-screen e off-screen
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();    // creazione del sottografo principale
        scene.compile();
        //Creazione del SimpleUniverse
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        
        //spostamento visuale con rotazione 
        Transform3D t3d = new Transform3D();
        t3d.lookAt(new Point3d(0d, 0d, 20d), new Point3d(0d, 0d, 0d), new Vector3d(0d, 1d, 0d));
        t3d.invert();
        //creazione viewing platform (transformGroup e viewPlatform)
        ViewingPlatform vp = simpleU.getViewingPlatform();
        TransformGroup vtg = vp.getViewPlatformTransform();
        vtg.setTransform(t3d);
        
        simpleU.addBranchGraph(scene);
	}
	
	public  BranchGroup createSceneGraph() {
		BranchGroup objRoot = new BranchGroup();
		Transform3D rotate = new Transform3D();
		rotate.rotX(Math.PI/4.0d);
		TransformGroup objRotate = new TransformGroup(rotate);
		//ROTAZIONE
		TransformGroup objSpin = new TransformGroup();
		objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objRoot.addChild(objRotate);
		objRotate.addChild(objSpin);
		
		//codice per l'impostazione delle luci su un oggetto
		//non funziona se ho assegnato dei colori a tali oggetti
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.d,0.d,0.d),50.d);
		DirectionalLight lightP1 = new DirectionalLight();
        Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
        lightP1.setColor(green);
        lightP1.setDirection(0f, 1f, -1f);
        lightP1.setInfluencingBounds(bounds);
        objRotate.addChild(lightP1);
		
		objSpin.addChild(new Sphere(5));
		Transform3D xAxis = new Transform3D();
		xAxis.rotZ(-Math.PI/4.0f);
		Alpha rotationAlpha2 = new Alpha(-1, 2000);
		RotationInterpolator rotatorX  = new RotationInterpolator(rotationAlpha2,objSpin, xAxis, 0.0f, (float) Math.PI*2.0f);
		rotatorX.setSchedulingBounds(bounds);
		objSpin.addChild(rotatorX);
		return objRoot;
	}
	
	public static void main(String[] args) {
		new MainFrame(new NewShape(), 1024, 768);
	}
	
	
}
