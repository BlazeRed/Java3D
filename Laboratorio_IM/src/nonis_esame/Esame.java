package nonis_esame;

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
import javax.media.j3d.ImageComponent2D;
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
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;


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
		t3d.lookAt(new Point3d(0d, 0d, 25d), new Point3d(0d, 0d, 0d), new Vector3d(0d, 1d, 0d));
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
		TransformGroup TG = new TransformGroup();
		
		TG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		TG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		//Rotazione mouse
		MouseRotate myMouseRotate=new MouseRotate(TG);
		myMouseRotate.setSchedulingBounds(new BoundingSphere());
		BG.addChild(myMouseRotate);

		//ruoto l'intero ogetto
		Transform3D rotate = new Transform3D();
		rotate.rotY(Math.PI/3);
		TG.setTransform(rotate);

		//imposto le luci
		BG.addChild(luceDirezionale());
		BG.addChild(luceAmbientale());

		//aggiungo gli elementi alla scena
		TG.addChild(tavolo());
		TG.addChild(imac());
		
		//rotazione oggetto
		rotateObj(TG, BG);
		
		BG.addChild(TG);
		BG.addChild(background());
		return BG;
	}
	
	private void rotateObj(TransformGroup TG, BranchGroup BG) {
		Transform3D rotate = new Transform3D();
		rotate.rotY(-Math.PI/4.0f);
		Alpha rotationAlpha=new Alpha(-1,5000);
		//RotationInterpolator cambia l'orientamento di un oggetto (alpha, target, trasformazione, angolo partenza, angolo arrivo)
		RotationInterpolator rotator=new RotationInterpolator(rotationAlpha, TG, rotate, 0.0f,  (float)Math.PI * 2.0f );
		BoundingSphere bounds=new BoundingSphere();
		//Imposta un raggio d'azione all'interpolatore
		rotator.setSchedulingBounds(bounds);
		BG.addChild(rotator);
	}

	private TransformGroup tavolo() {
		TransformGroup TGtavolo = new TransformGroup();
		Transform3D pos = new Transform3D();
		pos.setTranslation(new Vector3d(0, -5, 0));
		TGtavolo.setTransform(pos);
		TGtavolo.addChild(new Tavolo(2, 2, 2));
		return TGtavolo;
	}

	private TransformGroup imac() {
		TransformGroup TGimac = new TransformGroup();
		Transform3D pos = new Transform3D();
		pos.setTranslation(new Vector3d(0, -3, 0));
		TGimac.setTransform(pos);
		TGimac.addChild(new IMac(1));
		return TGimac;
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
		dirLight.setDirection(1f, 0f, -1f);
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

	/**
	 * @return the background of the scene
	 */
	private Background background() {
		TextureLoader loader = new TextureLoader( "../immagini_esame/Alien-bedroom.jpg", this );
		ImageComponent2D load = loader.getImage( );
		Background back = new Background( );
		back.setImage( load );
		back.setImageScaleMode(Background.SCALE_FIT_MAX);
		BoundingSphere myBounds = new BoundingSphere(new Point3d( ), 1000.0 );
		back.setApplicationBounds( myBounds );
		return back;
	}


	public static void main(String[] args) {
		new MainFrame(new Esame(), 1024, 768);
	}

}
