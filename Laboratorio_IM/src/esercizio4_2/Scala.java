package esercizio4_2;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
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

public class Scala extends Group{
	
	protected TransformGroup TG = new TransformGroup();
	protected TransformGroup TGprimo = new TransformGroup();
	protected TransformGroup TGsecondo = new TransformGroup();
	protected TransformGroup TGterzo = new TransformGroup();
	
	public Scala(float xDim, float yDim, float zDim, float posX, float posY) {
		Gradino primo = new Gradino(xDim, yDim, zDim);
		Gradino secondo = new Gradino(xDim + 0.5f, yDim, zDim + 0.5f);
		Gradino terzo = new Gradino(xDim + 1, yDim, zDim + 1f);
		//metodi per il posizionamento dei gradini
		posizionePrimo(primo, posX, posY);
		posizioneSecondo(secondo, yDim, posX, posY);
		posizioneTerzo(terzo, yDim, posX, posY);
		//aggiungo gli elementi alla scena
		TG.addChild(TGprimo);
		TG.addChild(TGsecondo);
		TG.addChild(TGterzo);
		addChild(TG);
	}
	
	private TransformGroup posizionePrimo(Gradino primo, float posX, float posY) {
		Transform3D posPrimo = new Transform3D();
		posPrimo.setTranslation(new Vector3d(posX, posY, 0));
		TGprimo.setTransform(posPrimo);
		TGprimo.addChild(primo);
		return TGprimo;
	}
	
	private TransformGroup posizioneSecondo(Gradino secondo, float yDim, float posX, float posY) {
		Transform3D posSecondo = new Transform3D();
		posSecondo.setTranslation(new Vector3d(posX, posY - yDim, 0));
		TGsecondo.setTransform(posSecondo);
		TGsecondo.addChild(secondo);
		return TGsecondo;
	}
	
	private TransformGroup posizioneTerzo(Gradino terzo, float yDim, float posX, float posY) {
		Transform3D posTerzo = new Transform3D();
		posTerzo.setTranslation(new Vector3d(posX, posY - (2*yDim), 0));
		TGterzo.setTransform(posTerzo);
		TGterzo.addChild(terzo);
		return TGterzo;
	}
}
