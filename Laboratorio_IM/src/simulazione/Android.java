package simulazione;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

public class Android extends Group{
	protected TransformGroup TG = new TransformGroup();
	protected TransformGroup TGtesta = new TransformGroup();
	protected TransformGroup TGcorpo = new TransformGroup();
	protected TransformGroup TGgambe = new TransformGroup();
	protected TransformGroup TGbraccia = new TransformGroup();
	protected TransformGroup TGantenne = new TransformGroup();
	
	public Android(float scaleFactor) {
		float altezza = 6;
		float posX = 0;
		float posY = 0;
		float raggio = 3;
		
		Transform3D scale = new Transform3D();
		scale.setScale(scaleFactor);
		TG.setTransform(scale);
		
		Cylinder corpo = new Cylinder(raggio, altezza, Primitive.GENERATE_NORMALS, createApp());
		Sphere testa = new Sphere(raggio, Primitive.GENERATE_NORMALS, createApp());
		
		Gambe gambe = new Gambe(posX, posY, raggio, altezza, createApp());
		Braccia braccia = new Braccia(posX, posY, raggio, altezza, createApp());
		
		
		
		posizioneCorpo(corpo, posX, posY, altezza);
		posizioneTesta(testa, posX, posY, altezza);
		
		TGgambe.addChild(gambe);
		TGgambe.addChild(braccia);
		TG.addChild(TGcorpo);
		TG.addChild(TGtesta);
		TG.addChild(TGgambe);
		TG.addChild(TGbraccia);
		addChild(TG);
		
	}
	

	
	private TransformGroup posizioneCorpo(Cylinder corpo, float posX, float posY, float altezza) {
		Transform3D posCorpo = new Transform3D();
		posCorpo.setTranslation(new Vector3d(posX, posY, 0));
		TGcorpo.setTransform(posCorpo);
		TGcorpo.addChild(corpo);
		return TGcorpo;
	}
	
	private TransformGroup posizioneTesta(Sphere testa, float posX, float posY, float altezza) {
		Transform3D posTesta = new Transform3D();
		posTesta.setTranslation(new Vector3d(posX, posY + altezza/2, 0));
		TGtesta.setTransform(posTesta);
		TGtesta.addChild(testa);
		return TGtesta;
	}


	private Appearance createApp() {
		Appearance app = new Appearance();
		Material material = new Material();
		//Carico texture, impostazione attributi materiale (riflessione)
		material.setShininess(80.0f);
		material.setSpecularColor(new Color3f(0.0f, 0.0f, 0.0f));
		
		material.setAmbientColor(164/255f, 198/255f, 57/255f);
        material.setDiffuseColor(164/255f, 198/255f, 57/255f);
        app.setMaterial(material);
		return app;
	}
}
