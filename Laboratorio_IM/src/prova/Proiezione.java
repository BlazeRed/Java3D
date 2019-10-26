package prova;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import shapes.MyColorCube;
import shapes.MyCylinder;
import shapes.Trottola;

public class Proiezione extends Applet{
	
	protected PolygonAttributes polyAttrbutes = new PolygonAttributes(); 
	
	public Proiezione() {
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration(); 
		Canvas3D canvas3D = new Canvas3D(config);
		add("Center", canvas3D);
		BranchGroup scene = createSceneGraph();
		scene.compile();
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D); 
		simpleU.getViewingPlatform().setNominalViewingTransform();
		//Accedo all'oggetto view del SimpleUniverse
		View myView = simpleU.getViewer().getView();
		//Abilitazione compatibility mode per modificare la matrice di proiezione 
		myView.setCompatibilityModeEnable(true);
		
		//Creazione trasformazione
		Transform3D t3d = new Transform3D(); 
		//Impostazione matrice di proiezione ortogonale e prospettica 
		t3d.ortho(-4, 4, -4, 4, 3.5, 50.0);  //originale 2
		//t3d.ortho(-2, 2, -2, 2, 2.7, 10.0); 
		//t3d.perspective(Math.PI/6, 1024/768, 2.7, 10); 
		//t3d.perspective(Math.PI/4, 1024/768, 2.6, 10); 
		myView.setLeftProjection(t3d);
		createViewBranch(simpleU);
		simpleU.addBranchGraph(scene);
	}
	
	//funzione che crea e gestisce la ViewBranch per un certo simpleUniverse
	public TransformGroup createViewBranch(SimpleUniverse simpleU) {
		Transform3D fuga2 = new Transform3D();
        Transform3D fuga3 = new Transform3D();
		//utilizzo la funzione lookAt per lo spostamento della telecamera 
        fuga2.lookAt(new Point3d(2d, 0d, 2d), new Point3d(0d, 0d, 0d), new Vector3d(0d, 1d, 0d));
        fuga2.invert();
        
        fuga3.lookAt(new Point3d(2d, 3d, 2d), new Point3d(0d, 0d, 0d), new Vector3d(0d, 1d, 0d));
        fuga3.invert();
        
        ViewingPlatform vp = simpleU.getViewingPlatform();
        TransformGroup vtg = vp.getViewPlatformTransform();
        vtg.setTransform(fuga3);
		return vtg;
	}

	/**
	* Funzione che crea il sottografo
	* @return il BranchGroup da aggiungere al SimpleUniverse
	*/
	public BranchGroup createSceneGraph() {
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
		
		objSpin.addChild(new MyColorCube());
		Transform3D xAxis = new Transform3D();
		xAxis.rotZ(-Math.PI/4.0f);
		Alpha rotationAlpha2 = new Alpha(-1, 4000);
		RotationInterpolator rotatorX  = new RotationInterpolator(rotationAlpha2,objSpin, xAxis, 0.0f, (float) Math.PI*2.0f);
		rotatorX.setSchedulingBounds(bounds);
		objSpin.addChild(rotatorX);
		return objRoot;
	}
	
	public static void main(String[] args) {
		new MainFrame(new Proiezione(), 1024, 768);

	}

}
