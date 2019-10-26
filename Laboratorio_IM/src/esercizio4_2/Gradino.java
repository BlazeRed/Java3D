package esercizio4_2;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

public class Gradino extends Box{
	public Gradino(float xDim, float yDim, float zDim) {
		super(xDim, yDim, zDim, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, createApp());
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
