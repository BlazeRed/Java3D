package nonis_esame;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.SwitchValueInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;

public class IMac extends Group{

	protected TransformGroup TG = new TransformGroup();
	protected TransformGroup TGbase = new TransformGroup();
	protected TransformGroup TGbraccio = new TransformGroup();
	protected TransformGroup TGschermo = new TransformGroup();

	public IMac(float scaleFactor) {

		Sphere base = new Sphere(1, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 200, baseApp());
		Box schermo = new Box(2f, 1.5f, 0.1f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, appSchermo());
		Braccio braccio = new Braccio(1);

		//impostazioni di scalatura
		Transform3D scale = new Transform3D();
		scale.setScale(scaleFactor);
		TG.setTransform(scale);
		
		posizioneBase(base);
		posizioneBraccio(braccio);
		posizioneSchermo(schermo);
		

		TG.addChild(TGbase);
		TG.addChild(TGbraccio);
		TG.addChild(TGschermo);
		addChild(TG);
	}

	private Appearance baseApp() {
		Appearance app = new Appearance();
		Material material = new Material();
		//Carico texture, impostazione attributi materiale (riflessione)
		material.setShininess(80.0f);
		material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f)); 
		app.setMaterial(material);
		//Caricamento della texture da file.
		TextureLoader textureLoader = new TextureLoader("../immagini_esame/ipec.jpg", null);
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

	private void posizioneSchermo(Box schermo) {
		Transform3D pos = new Transform3D();
		pos.setTranslation(new Vector3d(0, 2.8, 1.3));
		TGschermo.addChild(schermo);
		TGschermo.setTransform(pos);
	}

	private void posizioneBraccio(Braccio braccio) {
		Transform3D pos = new Transform3D();
		pos.setTranslation(new Vector3d(0, 1, 0));
		TGbraccio.addChild(braccio);
		TGbraccio.setTransform(pos);
	}

	private void posizioneBase(Sphere base) {
		Transform3D pos = new Transform3D();
		pos.setTranslation(new Vector3d(0, 0, 0));
		TGbase.addChild(base);
		TGbase.setTransform(pos);
	}

	private Appearance appSchermo() {
		Appearance app = new Appearance();
		Material material = new Material();
		//Carico texture, impostazione attributi materiale (riflessione)
		material.setShininess(80.0f);
		material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f)); 
		app.setMaterial(material);
		//Caricamento della texture da file.
		TextureLoader textureLoader = new TextureLoader("../immagini_esame/2001_2.jpg", null);
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
}
