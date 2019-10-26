package esercizio4_2;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import esercizio4_4.Timpano;

public class Facciata extends Group{
	
	protected TransformGroup TG = new TransformGroup();
	protected TransformGroup TGcolonnato = new TransformGroup();
	protected TransformGroup TGscala = new TransformGroup();
	protected TransformGroup TGtimpano = new TransformGroup();
	private float altezza = 0;
	private float larghezza = 0;
	
	public Facciata(double scaleFactor) {
		Colonnato col = new Colonnato(6);
		altezza = col.getAltezza();
		larghezza = col.getLarghezza();
		
		Transform3D dimension = new Transform3D();
		dimension.setScale(scaleFactor);
		//applico una scalatura all'intera facciata per permettere il ridimensionamento
		posizioneScala();
		posizioneTimpano(altezza);
		TG.addChild(col);
		TG.addChild(TGscala);
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

	private void posizioneScala() {
		Scala scala = new Scala(larghezza, 0.2f, 0.5f, 0f, 0f);
		Transform3D position = new Transform3D();
		position.setTranslation(new Vector3d(larghezza-1f, -1f, 0));
		TGscala.setTransform(position);
		TGscala.addChild(scala);
	}
}
