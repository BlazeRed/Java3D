package esercizio4_4;

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
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

public class Colonnato extends Group{
	
	protected TransformGroup TG = new TransformGroup();
	protected TransformGroup TGcolonna = new TransformGroup();
	protected TransformGroup TGcolGroup = new TransformGroup();
	protected TransformGroup TGabaco = new TransformGroup();
	protected float altezza = 0;
	protected float larghezza = 0;
	
	public Colonnato(int nCol) {
		createColonnato(nCol);
		TG.addChild(TGcolonna);
		TG.addChild(createAbaco());
		addChild(TG);
	}
	
	private void createColonnato(int n) {
		larghezza = n;
		for(int i=0; i<n; i++) {
			CreateColonna colonna = new CreateColonna(3f, 0.3f, i*2f, 0);
			altezza = colonna.getAltezza();
			TGcolonna.addChild(colonna);
			
		}
	}
	
	private TransformGroup createAbaco() {
		Transform3D position = new Transform3D();
		position.setTranslation(new Vector3d(larghezza-1f, altezza-0.8f, 0));
		TGabaco.addChild(new Box(larghezza-0.5f, 0.3f, 0.5f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, createApp()));
		TGabaco.setTransform(position);
		
		return TGabaco;
	}
	
	public float getAltezza() {
		return altezza;
	}
	
	public float getLarghezza() {
		return larghezza;
	}
	
	private static Appearance createApp() {
		Appearance app = new Appearance();
		Material material = new Material();
		//Carico texture, impostazione attributi materiale (riflessione)
		material.setShininess(80.0f);
		material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f)); 
		app.setMaterial(material);
		//Caricamento della texture da file.
		TextureLoader textureLoader = new TextureLoader("../texture/PietraColonna.jpg", null);
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
