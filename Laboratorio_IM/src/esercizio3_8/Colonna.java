package esercizio3_8;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;


public class Colonna extends Applet{
	
	public Colonna() {
		setLayout(new BorderLayout()); 
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();    // creazione del sottografo principale
        scene.compile();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        
        //imposto la visuale
        Transform3D look = new Transform3D();
        look.lookAt(new Point3d(0f, 0f, 20f), new Point3d(0f, 0f, 0f), new Vector3d(0f, 1f, 0f));
        look.invert();
        
        ViewingPlatform vp = simpleU.getViewingPlatform();
        TransformGroup vtg = vp.getViewPlatformTransform();
        vtg.setTransform(look);
        
        simpleU.addBranchGraph(scene);
	}
	
	public  BranchGroup createSceneGraph() {
		BranchGroup BG = new BranchGroup();
		TransformGroup TG = new TransformGroup();
		//aggiunta delle luci
		BG.addChild(luceAmbientale());
		BG.addChild(luceDirezionale());
		//imposto le capability
		TG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        TG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		// mouse behaviour
        MouseRotate mouse = new MouseRotate(TG);
        mouse.setSchedulingBounds(new BoundingSphere());
        KeyNavigatorBehavior keyNav = new KeyNavigatorBehavior(TG); //Imposto il bound del behavior
        keyNav.setSchedulingBounds(new BoundingSphere(new Point3d(), 10000.0));
        TG.addChild(keyNav);
        TG.addChild(mouse);
        //aggiunta della colonna alla scena 
        TG.addChild(new CreateColonna(5f, 0.5f, 0, 0));
		BG.addChild(TG);
		return BG;
	}
	
	/**
	 * Funzione che crea una luce direzionale
	 * 
	 * @return DirectionalLight da applicare al BG
	 */
	public DirectionalLight luceDirezionale() {
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.d,0.d,0.d),20.d);
		DirectionalLight dirLight = new DirectionalLight();
		Color3f col = new Color3f(0.5f, 0.5f, 0.4f);
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
		Color3f col = new Color3f(0.5f, 0.5f, 0.4f);
		ambLight.setColor(col);
		ambLight.setInfluencingBounds(bounds);
		return ambLight;
	}
	
	public static void main(String[] args) {
		new MainFrame(new Colonna(), 1024, 768);

	}

}
