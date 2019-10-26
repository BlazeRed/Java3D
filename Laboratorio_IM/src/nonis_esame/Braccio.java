package nonis_esame;

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

public class Braccio extends Group{

	protected TransformGroup TG = new TransformGroup();
	protected TransformGroup TGbase = new TransformGroup();
	protected TransformGroup TGnodoBasso = new TransformGroup();
	protected TransformGroup TGconnettore = new TransformGroup();
	protected TransformGroup TGnodoAlto = new TransformGroup();
	
	public Braccio(float scaleFactor) {
		Cylinder base = new Cylinder(0.1f, 0.5f, Primitive.GENERATE_NORMALS, createApp());
		Sphere nodoBasso = new Sphere(0.4f, Primitive.GENERATE_NORMALS, createApp());
		Cylinder connettore = new Cylinder(0.2f, 1f, Primitive.GENERATE_NORMALS, createApp());
		Sphere nodoAlto = new Sphere(0.4f, Primitive.GENERATE_NORMALS, createApp());
		
		//impostazioni di scalatura
		Transform3D scale = new Transform3D();
		scale.setScale(scaleFactor);
		TG.setTransform(scale);
		
		posizioneBase(base);
		posizioneNodoBasso(nodoBasso);
		posizioneConnettore(connettore);
		posizioneNodoAlto(nodoAlto);
		
		TG.addChild(TGbase);
		TG.addChild(TGconnettore);
		TG.addChild(TGnodoAlto);
		TG.addChild(TGnodoBasso);
		addChild(TG);
	}

	private void posizioneNodoAlto(Sphere nodoAlto) {
		Transform3D pos = new Transform3D();
		pos.setTranslation(new Vector3d(0f, 1.1f, 1f));
		TGnodoAlto.addChild(nodoAlto);
		TGnodoAlto.setTransform(pos);
	}

	private void posizioneConnettore(Cylinder connettore) {
		Transform3D pos = new Transform3D();
		pos.setTranslation(new Vector3d(0f, 0.8f, 0.5f));
		//torazione braccio
		Transform3D rot = new Transform3D();
		rot.rotX(Math.PI/3);
		pos.mul(rot);
		TGconnettore.addChild(connettore);
		TGconnettore.setTransform(pos);
	}

	private void posizioneNodoBasso(Sphere nodoBasso) {
		Transform3D pos = new Transform3D();
		pos.setTranslation(new Vector3d(0f, 0.5f, 0f));
		TGnodoBasso.addChild(nodoBasso);
		TGnodoBasso.setTransform(pos);
		
	}

	private void posizioneBase(Cylinder base) {
		Transform3D pos = new Transform3D();
		pos.setTranslation(new Vector3d(0, 0, 0));
		TGbase.addChild(base);
		TGbase.setTransform(pos);
		
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
