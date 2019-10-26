package esercizio4_3;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.media.j3d.Texture;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;


public class EarthMoon extends Applet{

	public EarthMoon() {
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
		t3d.lookAt(new Point3d(0d, 0d, 10d), new Point3d(0d, 0d, 0d), new Vector3d(0d, 1d, 0d));
		t3d.invert();
		TransformGroup vtg = simpleU.getViewingPlatform().getViewPlatformTransform();
		//Creo un behavior per la navigazione da tastiera
		KeyNavigatorBehavior keyNavBeh=new KeyNavigatorBehavior(vtg);
		//Imposto il bound del behavior
		keyNavBeh.setSchedulingBounds(new BoundingSphere(new Point3d(),10000.0));
		scene.addChild(keyNavBeh);
		vtg.setTransform(t3d);
		scene.compile();
		simpleU.addBranchGraph(scene);

	}

	/**
	 * Funzione che crea il sottografo
	 * @return il BranchGroup da aggiungere al SimpleUniverse
	 */
	public BranchGroup createSceneGraph() {
		BranchGroup BG = new BranchGroup();
		/*CREAZIONE DELLA TERRA*/
		Sphere earth = new Sphere(1.0f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 200, createEarthAppearance() );
		/*CREAZIONE DELLA TERRA*/
		Sphere moon = new Sphere(0.3f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 200, createMoonAppearance() );
		moonRotation(BG, moon);
		earthRotation(BG, earth);
		BG.addChild(background());
		BG.addChild(luceDirezionale());
		BG.addChild(luceAmbientale());
		return BG; 
	}

	private void moonRotation(BranchGroup BG,  Sphere moon) {
		//TG generico per la luna
		TransformGroup TGmoon = new TransformGroup();
		//TG per la posizione della luna
		TransformGroup moonPosition = new TransformGroup();
		//TG per orbita bella luna 
		TransformGroup moonOrbit = new TransformGroup();
		//posizione luna
		Transform3D position = new Transform3D();
		position.setTranslation(new Vector3d(1, 1, -2));
		//orbita della luna
		Transform3D orbit = new Transform3D(); 
		orbit.rotY(-Math.PI / 4.0f);
		//imposto la posizione
		moonPosition.setTransform(position);
		Alpha rotationAlpha=new Alpha(-1,8000);
		//Crea un interpolatore per le rotazioni collegato con il gruppo di trasformazione
		RotationInterpolator rotator=new RotationInterpolator(rotationAlpha, moonOrbit, orbit, 0.0f,  (float)Math.PI * 2.0f );
		BoundingSphere bounds=new BoundingSphere();
		moonOrbit.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		//Imposta un raggio d'azione all'interpolatore
		rotator.setSchedulingBounds(bounds);
		TGmoon.addChild(moon);
		moonPosition.addChild(TGmoon);
		moonOrbit.addChild(rotator);
		moonOrbit.addChild(moonPosition);
		BG.addChild(moonOrbit);
	}


	private void earthRotation(BranchGroup BG,  Sphere earth) {
		TransformGroup TGearth = new TransformGroup();

		Transform3D rotateEarth = new Transform3D();
		rotateEarth.rotY(-Math.PI/4.0f);
		Alpha rotationAlpha=new Alpha(-1,8000);
		//RotationInterpolator cambia l'orientamento di un oggetto (alpha, target, trasformazione, angolo partenza, angolo arrivo)
		RotationInterpolator rotator=new RotationInterpolator(rotationAlpha, TGearth, rotateEarth, 0.0f,  (float)Math.PI * 2.0f );
		BoundingSphere bounds=new BoundingSphere();
		//Imposta un raggio d'azione all'interpolatore
		rotator.setSchedulingBounds(bounds);
		TGearth.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		TGearth.addChild(earth);
		BG.addChild(rotator);
		BG.addChild(TGearth);
	}

	/**
	 * @return the background of the scene
	 */
	private Background background() {
		TextureLoader loader = new TextureLoader( "../texture/stars.jpg", this );
		ImageComponent2D stars = loader.getImage( );
		Background sky = new Background( );
		sky.setImage( stars );
		sky.setImageScaleMode(Background.SCALE_FIT_MAX);
		BoundingSphere myBounds = new BoundingSphere(new Point3d( ), 1000.0 );
		sky.setApplicationBounds( myBounds );
		return sky;
	}

	/**
	 * @return appearance of earth
	 */
	private Appearance createEarthAppearance() {
		Appearance app = new Appearance();
		Material material = new Material();
		//Carico texture, impostazione attributi materiale (riflessione)
		material.setShininess(80.0f);
		material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f)); 
		app.setMaterial(material);
		//Caricamento della texture da file.
		TextureLoader textureLoader = new TextureLoader("../texture/earth.jpg", null);
		// Inizializzazione dell ’oggetto Texture.
		Texture texture = textureLoader.getTexture();
		//Impostazione texture e suoi attributi 
		TextureAttributes attributes = new TextureAttributes(); 
		attributes.setTextureMode(TextureAttributes.COMBINE); 
		app.setTexture(texture); 
		app.setTextureAttributes(attributes);
		return app;
	}

	/**
	 * @return appearance of moon
	 */
	private Appearance createMoonAppearance() {
		Appearance app = new Appearance();
		Material material = new Material();
		//Carico texture, impostazione attributi materiale (riflessione)
		material.setShininess(80.0f);
		material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f)); 
		app.setMaterial(material);
		//Caricamento della texture da file.
		TextureLoader textureLoader = new TextureLoader("../texture/moon.jpg", null);
		// Inizializzazione dell ’oggetto Texture.
		Texture texture = textureLoader.getTexture();
		// Impostazione dell’aspetto .
		app.setTexture(texture);
		//Impostazione texture e suoi attributi 
		TextureAttributes attributes = new TextureAttributes(); 
		attributes.setTextureMode(TextureAttributes.COMBINE); 
		app.setTexture(texture); 
		app.setTextureAttributes(attributes);
		return app;
	}

	/**
	 * Funzione che crea una luce direzionale da applicare alla scena
	 * 
	 * @return DirectionalLight da applicare al BG
	 */
	public DirectionalLight luceDirezionale() {
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.d,0.d,0.d),10.d);
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

	public static void main(String[] args) {
		new MainFrame(new EarthMoon(), 1024, 768);

	}


}
