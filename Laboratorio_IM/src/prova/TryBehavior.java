package prova;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class TryBehavior extends Applet {

	public TryBehavior() {
		setLayout(new BorderLayout()); //layout manager del container
		//trova la miglior configurazione grafica per il sistema
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		// Canvas3: si occupa del rendering 3D on-screen e off-screen
		Canvas3D canvas3D = new Canvas3D(config);
		add("Center", canvas3D);
		BranchGroup scene = createSceneGraph();    // creazione del sottografo principale

		//Creazione del SimpleUniverse
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

		//spostamento visuale con rotazione 
		Transform3D t3d = new Transform3D();
		t3d.lookAt(new Point3d(0d, 0d, 5d), new Point3d(0d, 0d, 0d), new Vector3d(0d, 1d, 0d));
		t3d.invert();

		//creazione viewing platform (transformGroup e viewPlatform)
		TransformGroup vtg = simpleU.getViewingPlatform().getViewPlatformTransform();

		//Creo un behavior per la navigazione da tastiera
		KeyNavigatorBehavior keyNavBeh=new KeyNavigatorBehavior(vtg);
		//Imposto il bound del behavior
		keyNavBeh.setSchedulingBounds(new BoundingSphere(new Point3d(),10000.0));
		scene.addChild(keyNavBeh);

		scene.compile();
		vtg.setTransform(t3d);
		simpleU.addBranchGraph(scene);
	}

	//crea la scena
	private BranchGroup createSceneGraph() {
		//crea la radice del BranchGraph
		BranchGroup objRoot = new BranchGroup();
		TransformGroup objSpin = new TransformGroup();
		objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		Alpha rotationAlpha=new Alpha(-1,8000);
		//Crea un interpolatore per le rotazioni collegato con il gruppo di trasformazione
		RotationInterpolator rotator=new RotationInterpolator(rotationAlpha,objSpin);
		BoundingSphere bounds=new BoundingSphere();
		//Imposta un raggio d'azione all'interpolatore
		rotator.setSchedulingBounds(bounds);
		

		//aggiungo al gruppo un cubo colorato
		objSpin.addChild(new ColorCube(0.3));
		
		objSpin.addChild(rotator);

		//Crea il behavior per ruotare il cubo
		MouseRotate myMouseRotate=new MouseRotate(objSpin);
		//Imposta un raggio d'azione del behavior
		myMouseRotate.setSchedulingBounds(new BoundingSphere());
		objRoot.addChild(myMouseRotate);
		objRoot.addChild(objSpin);
		
		return objRoot;
	}
	
	
	

	public static void main(String[] args) {
		new MainFrame(new TryBehavior(), 1024, 768);

	}

}
