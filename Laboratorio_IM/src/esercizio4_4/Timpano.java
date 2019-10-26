package esercizio4_4;

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
import javax.vecmath.Point3d;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.image.TextureLoader;

public class Timpano extends Shape3D{
	
	protected Point3d vertex[] = new Point3d[10];
	protected TriangleStripArray triangles = null;
	
	public Timpano(float altezza, float larghezza, float profondita) {
		float alt = altezza/2;
		float larg = larghezza/2;
		float prof = profondita/2;
		
		vertex[0] = new Point3d(-larg, 0, -prof); 
		vertex[1] = new Point3d(0, alt, -prof);
		vertex[2] = new Point3d(larg, 0, -prof); 
		vertex[3] = vertex[1]; 
		vertex[4] = new Point3d(larg, 0, 0); 
		vertex[5] = new Point3d(0, alt, 0);
		vertex[6] = new Point3d(-larg, 0, 0);
		vertex[7] = vertex[1];
		vertex[8] = vertex[0];
		vertex[9] = vertex[4];
		
		int [] stripCounts = {(vertex.length)};
		triangles = new TriangleStripArray(vertex.length, GeometryArray.COORDINATES, stripCounts);
		triangles.setCoordinates(0, vertex); 
		GeometryInfo geometry = new GeometryInfo(triangles); 
		NormalGenerator normal = new NormalGenerator(); 
		normal.generateNormals(geometry); 
		setGeometry(triangles); 
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
		TextureLoader textureLoader = new TextureLoader("../texture/PietraColonna.jpg", null);
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
