package simulazione;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;


public class Piedistallo extends Group{
	protected TransformGroup TG = new TransformGroup();
	protected TransformGroup TGfusto = new TransformGroup();
	protected TransformGroup TGechino = new TransformGroup();
	protected TransformGroup TGbase = new TransformGroup();
	protected float altezza = 8;
	
	public Piedistallo(float posX, float posY) {
		Cylinder fusto = new Cylinder(1f, altezza,Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, createApp());
		Box base = new Box(1.5f, 0.5f, 1.5f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, createApp());
		Echino echino = new Echino(1, 2);
		Cylinder tappo = new Cylinder(2f, 0.2f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, createApp());
		
		//posiziono i vari elementi
		posizioneFusto(fusto, posX, posY, altezza);
		posizioneBase(base, posX, posY, altezza);
		posizioneEchino(echino, posX, posY, altezza, tappo);
		
		TG.addChild(TGfusto);
		TG.addChild(TGbase);
		TG.addChild(TGechino);
		addChild(TG);
		
	}
	
	public float getAltezza() {
		return altezza;
	}
	
	private void posizioneBase(Box base, float posX, float posY, float altezza) {
		Transform3D posBase = new Transform3D();
		posBase.setTranslation(new Vector3d(posX, posY - altezza/2, 0));
		TGbase.setTransform(posBase);
		TGbase.addChild(base);
	}
	
	private void posizioneFusto(Cylinder fusto, float posX, float posY, float altezza) {
		Transform3D posFusto = new Transform3D();
		posFusto.setTranslation(new Vector3d(posX, posY, 0));
		TGfusto.setTransform(posFusto);
		TGfusto.addChild(fusto);
	}
	
	private void posizioneEchino(Echino echino, float posX, float posY, float altezza, Cylinder tappo) {
		TransformGroup TGtappo = new TransformGroup();
		Transform3D posEchino = new Transform3D();
		Transform3D posTappo = new Transform3D();
		Transform3D rotate = new Transform3D();
		rotate.rotX(-Math.PI/2);
		posEchino.setTranslation(new Vector3d(posX, posY + altezza/2, 0));
		posEchino.mul(rotate);
		posTappo.setTranslation(new Vector3d(posX, posY + altezza-2.9f, 0));
		TGtappo.setTransform(posTappo);
		TGechino.setTransform(posEchino);
		TGtappo.addChild(tappo);
		TGechino.addChild(echino);
		TG.addChild(TGtappo);
	}
	
	private static Appearance createApp() {
		Appearance app = new Appearance();
		Material material = new Material();
		//Carico texture, impostazione attributi materiale (riflessione)
		material.setShininess(80.0f);
		material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f)); 
		app.setMaterial(material);
		//Caricamento della texture da file.
		TextureLoader textureLoader = new TextureLoader("../texture/wood.jpg", null);
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

