package esercizio4_2;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class CreateColonna extends Group{
	
	protected TransformGroup TG = new TransformGroup();
	protected TransformGroup TGFusto = new TransformGroup();
	protected TransformGroup TGEchino = new TransformGroup();
	protected TransformGroup TGAbaco = new TransformGroup();
	protected float altezza, base;
	
	public CreateColonna(float altezza, float base, float posX, float posY) {
		this.altezza = altezza;
		this.base = base;
		//definisco i vari oggetti della scena
		Fusto fusto = new Fusto(base, altezza);
		Echino echino = new Echino(base, base);
		Abaco abaco = new Abaco(base*2f);
		
		
		//trasformazioni per posizionare gli elementi
		posizioneFusto(fusto, posX, posY);
		posizioneEchino(echino, altezza, posX, posY);
		posizioneAbaco(abaco, altezza, base, posX, posY);
		//aggiunta dei vari TG al TG principale
		TG.addChild(TGFusto);
		TG.addChild(TGEchino);
		TG.addChild(TGAbaco);
		addChild(TG);
	}
	
	public float getAltezza() {
		return altezza;
	}

	private TransformGroup posizioneFusto(Fusto fusto, float posX, float posY) {
		Transform3D posFusto = new Transform3D();
		posFusto.setTranslation(new Vector3d(posX, posY, 0));
		TGFusto.setTransform(posFusto);
		TGFusto.addChild(fusto);//new Fusto(1f, 7f));
		return TGFusto;
	}

	private TransformGroup posizioneEchino(Echino echino, float altezza, float posX, float posY) {
		Transform3D posEchino = new Transform3D();
		Transform3D rotate = new Transform3D();
		rotate.rotX(-Math.PI/2);
		posEchino.setTranslation(new Vector3d(posX, posY + altezza/2, 0));
		posEchino.mul(rotate);
		TGEchino.setTransform(posEchino);
		TGEchino.addChild(echino); //new Echino(1.5f, 2f));
		return TGEchino;
	}
	
	private TransformGroup posizioneAbaco(Abaco abaco, float altezza, float base, float posX, float posY) {
		Transform3D posAbaco = new Transform3D();
		posAbaco.setTranslation(new Vector3d(posX, posY + (altezza/2+base), 0));
		TGAbaco.setTransform(posAbaco);
		TGAbaco.addChild(abaco);//new Abaco(1.6f));
		return TGAbaco;
	}
}
