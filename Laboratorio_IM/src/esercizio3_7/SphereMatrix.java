package esercizio3_7;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.SpotLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class SphereMatrix extends Applet{

	public SphereMatrix() {
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
		//applicazione delle diverse luci
		BG.addChild(luceAmbientale());
		BG.addChild(luceDirezionale());
		//BG.addChild(luceSpot());
		
		//ogni transformGroup creata per contenere le varie sfere sono figli diretti del BG
		//creazione matrice di sfere
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				//creazione di una TG e un Transform3D per ogni sfera nella scena
				TransformGroup TGSphere = new TransformGroup();
				Transform3D position = new Transform3D();
				//utilizzo gli indici come posizione delle sfere centrate in 0
				position.setTranslation(new Vector3d(i-2, j-2, 0));
				TGSphere.setTransform(position);
				//creazione singola sfera
				TGSphere.addChild(new Sphere(0.4f, createAppearance()));
				TG.addChild(TGSphere);
			}
		}
		BG.addChild(TG);
		return BG;
	}
	
	/**
	 * Funzione che crea una appearance per le sfere
	 * 
	 * @return appearance
	 */
	public Appearance createAppearance() { 
		Appearance appearance = new Appearance(); 
		Material material = new Material(); 
		appearance.setMaterial(material);
		return appearance;
	}
	
	/**
	 * Funzione che crea una luce direzionale da applicare alla scena
	 * 
	 * @return DirectionalLight da applicare al BG
	 */
	public DirectionalLight luceDirezionale() {
		BoundingSphere bounds = new BoundingSphere(new Point3d(2.d,2.d,0.d),2.d);
		DirectionalLight dirLight = new DirectionalLight();
		Color3f col = new Color3f(0f, 1f, 0f);
		dirLight.setColor(col);
		dirLight.setDirection(0f, 0f, -1f);
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
		Color3f col = new Color3f(0f, 1f, 0f);
		ambLight.setColor(col);
		ambLight.setInfluencingBounds(bounds);
		return ambLight;
	}
	
	/**
	 * Funzione che crea una luce spot da applicare alla scena
	 * 
	 * @return SpotLight da applicare al BG
	 */
	public SpotLight luceSpot() {
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.d,0.d,0.d),20.d);
		SpotLight spoLight = new SpotLight();
		Color3f col = new Color3f(0f, 1f, 0f);
		spoLight.setColor(col);
		spoLight.setInfluencingBounds(bounds);
		//impostare la direzione e l'angolo della luce
		spoLight.setPosition(new Point3f(0f, 0f, 1f));
		spoLight.setSpreadAngle((float)Math.PI/3);
		return spoLight;
	}

	public static void main(String[] args) {
		new MainFrame(new SphereMatrix(), 1024, 768);

	}
	
	
}
