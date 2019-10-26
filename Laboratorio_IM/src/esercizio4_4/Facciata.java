package esercizio4_4;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class Facciata extends Group{
	
	protected TransformGroup TG = new TransformGroup();
	protected TransformGroup TGcolonnato = new TransformGroup();
	protected TransformGroup TGtimpano = new TransformGroup();
	private float altezza = 0;
	private float larghezza = 0;
	
	public Facciata(float scaleFactor) {
		Colonnato col = new Colonnato(6);		
		altezza = col.getAltezza();
		larghezza = col.getLarghezza();
		Transform3D dimension = new Transform3D();
		dimension.setScale(scaleFactor);
		posizioneTimpano(altezza);
		TG.addChild(col);
		TG.addChild(TGtimpano);
		TG.setTransform(dimension);
		addChild(TG);

	}

	private void posizioneTimpano(float altezza) {
		Timpano timpano = new Timpano(3, larghezza*1.83f, 2);
		Transform3D position = new Transform3D();
		position.setTranslation(new Vector3d(larghezza-1f, altezza-0.5f, 0.5));
		TGtimpano.setTransform(position);
		TGtimpano.addChild(timpano);
	}
	
	public float getLarghezza() {
		return larghezza;
	}
}
