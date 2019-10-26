package simulazione;

import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.image.TextureLoader;

public class Echino extends Shape3D{
	
	protected Point3f v[] = null;
	protected TriangleStripArray triangleStrip = null;
	
	
	public Echino(float raggio, float altezza) {
		float raggioS = raggio*2;
		float raggioI = raggio;
		int steps = 30;
		float top = altezza/2;
		float bottom = -altezza/2;
		v = new Point3f[(steps+1)*2];
		
		for (int i = 0; i < steps; i++) {
			double angle = 2.0*Math.PI*(double)i/(double)steps; 
			float xI = (float)Math.sin(angle)*raggioI;
			float yI = (float)Math.cos(angle)*raggioI;
			float xS = (float)Math.sin(angle)*raggioS;
			float yS = (float)Math.cos(angle)*raggioS;
			v[i*2] = new Point3f(xI, yI, bottom);
			v[i*2+1] = new Point3f(xS, yS, top);
		}
	
		v[steps*2] = new Point3f(0.0f, raggioI, bottom); 
		v[steps*2+1] = new Point3f(0.0f, raggioS, top);
		int[] stripCounts = {(steps+1)*2};
		triangleStrip = new TriangleStripArray((steps+1)*2,GeometryArray.COORDINATES, stripCounts); 
		triangleStrip.setCoordinates(0, v);
		GeometryInfo geometry = new GeometryInfo(triangleStrip); 
		NormalGenerator normal = new NormalGenerator(); 
		normal.generateNormals(geometry); 
		setGeometry(triangleStrip); 
		setGeometry(geometry.getGeometryArray());
	
		setAppearance(createApp());
		
	}
		private static Appearance createApp() {
			Appearance appearance = new Appearance();
			Material material = new Material();
			PolygonAttributes polyAttributes = new PolygonAttributes(); 
			polyAttributes.setPolygonMode(PolygonAttributes.POLYGON_FILL); 
			polyAttributes.setCullFace(PolygonAttributes.CULL_NONE); 
			appearance.setPolygonAttributes(polyAttributes);
			//Carico texture, impostazione attributi materiale (riflessione)
			material.setShininess(80.0f);
			material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f)); 
			//Caricamento della texture da file.
			TextureLoader textureLoader = new TextureLoader("../texture/wood.jpg", null);
			// Inizializzazione dell ’oggetto Texture.
			Texture texture = textureLoader.getTexture();
			// Impostazione dell’aspetto .
			appearance.setTexture(texture);
			//Impostazione texture e suoi attributi 
			TextureAttributes attributes = new TextureAttributes(); 
			attributes.setTextureMode(TextureAttributes.MODULATE); 
			TexCoordGeneration texCoordinates = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR, TexCoordGeneration.TEXTURE_COORDINATE_3);
			appearance.setTexCoordGeneration(texCoordinates);
			appearance.setTexture(texture); 
			appearance.setTextureAttributes(attributes);
			appearance.setMaterial(material); 
			return appearance;
		}
}