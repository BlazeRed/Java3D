package nonis_esame;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;

public class Tavolo extends Box {
	
	public Tavolo(float xDim, float yDim, float zDim) {
		super(xDim, yDim, zDim, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, createApp());
	}
	
	private static Appearance createApp() {
		Appearance app = new Appearance();
		Material material = new Material();
		//Carico texture, impostazione attributi materiale (riflessione)
		material.setShininess(80.0f);
		material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f));
		//impostazione colore tavolo
		material.setAmbientColor(0/255f, 0/255f, 0/255f);
        material.setDiffuseColor(0/255f, 0/255f, 0/255f);
        app.setMaterial(material);
		return app;
	}
}
