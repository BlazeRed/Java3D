package simulazione;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;

import esercizio4_4.Tempio;

public class Esame extends Applet{
	
	
	public Esame() {
		setLayout(new BorderLayout()); 
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas3D = new Canvas3D(config);
		add("Center", canvas3D);
		BranchGroup scene = createSceneGraph();    // creazione del sottografo principale
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

		//spostamento visuale con rotazione 
		Transform3D t3d = new Transform3D();
		t3d.lookAt(new Point3d(0d, 0d, 30d), new Point3d(0d, 0d, 0d), new Vector3d(0d, 1d, 0d));
		t3d.invert();
		
		//spostamento tramite tastiera
		TransformGroup vtg = simpleU.getViewingPlatform().getViewPlatformTransform();
		KeyNavigatorBehavior keyNavBeh=new KeyNavigatorBehavior(vtg);
		keyNavBeh.setSchedulingBounds(new BoundingSphere(new Point3d(),10000.0));
		scene.addChild(keyNavBeh);
		vtg.setTransform(t3d);
		
		scene.compile();
		simpleU.addBranchGraph(scene);


	}
	

	private BranchGroup createSceneGraph() {
		BranchGroup BG = new BranchGroup();
		//TG di livello piu alto
		TransformGroup TG = new TransformGroup();
		TransformGroup TGpiedistallo = new TransformGroup();
		TG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		TG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		
		//impostaioni per cambiare colore allo sfondo
//		Background sfondo = new Background();
//		sfondo.setColor(1f, 1f, 1f);
//		BoundingSphere myBounds = new BoundingSphere(new Point3d( ), 1000.0 );
//		sfondo.setApplicationBounds( myBounds );
//		BG.addChild(sfondo);
		
		BG.addChild(luceAmbientale());
		BG.addChild(luceDirezionale());
		
		//Rotazione mouse
		MouseRotate myMouseRotate=new MouseRotate(TG);
		myMouseRotate.setSchedulingBounds(new BoundingSphere());
		BG.addChild(myMouseRotate);

		Android android = new Android(0.3f);
		Piedistallo piedistallo = new Piedistallo(0, 0);
		
		float altezza = piedistallo.getAltezza();
		
		TGpiedistallo.addChild(piedistallo);
		TG.addChild(TGpiedistallo);
		TG.addChild(rotazioneAndroid(android, altezza));
		BG.addChild(TG);
		return BG;
	}
	
	private TransformGroup rotazioneAndroid(Android android, float altezza) {
		TransformGroup TGandroidPos = new TransformGroup();
		//imposto la posizione
		Transform3D posAnd = new Transform3D();
		posAnd.setTranslation(new Vector3d(0, altezza-1.5F, 0));
		TGandroidPos.setTransform(posAnd);
		
		//imposto la rotazione
		TransformGroup TGandroid = new TransformGroup();
		Transform3D rotate = new Transform3D();
		rotate.rotY(-Math.PI/4.0f);
		Alpha rotationAlpha=new Alpha(-1,3000);
		//RotationInterpolator cambia l'orientamento di un oggetto (alpha, target, trasformazione, angolo partenza, angolo arrivo)
		RotationInterpolator rotator=new RotationInterpolator(rotationAlpha, TGandroid, rotate, 0.0f,  (float)Math.PI * 2.0f );
		BoundingSphere bounds=new BoundingSphere();
		//Imposta un raggio d'azione all'interpolatore
		rotator.setSchedulingBounds(bounds);
		TGandroid.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		TGandroidPos.addChild(android);
		TGandroid.addChild(TGandroidPos);
		TGandroid.addChild(rotator);
		return TGandroid;
	}
	
	/**
	 * Funzione che crea una luce direzionale
	 * 
	 * @return DirectionalLight da applicare al BG
	 */
	public DirectionalLight luceDirezionale() {
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.d,0.d,0.d),100.d);
		DirectionalLight dirLight = new DirectionalLight();
		Color3f col = new Color3f(1f, 1f, 1f);
		dirLight.setColor(col);
		dirLight.setDirection(1f, 1f, -1f);
		dirLight.setInfluencingBounds(bounds);
		return dirLight;
	}

	/**
	 * Funzione che crea una luce ambientale da applicare alla scena
	 * 
	 * @return AmbientLight da applicare al BG
	 */
	public AmbientLight luceAmbientale() {
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.d,0.d,0.d),20.d);
		AmbientLight ambLight = new AmbientLight();
		Color3f col = new Color3f(1f, 1f, 1f);
		ambLight.setColor(col);
		ambLight.setInfluencingBounds(bounds);
		return ambLight;
	}


	public static void main(String[] args) {
		new MainFrame(new Esame(), 1024, 768);
	}

}
