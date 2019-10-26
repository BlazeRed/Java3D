package esercizio4_4;

import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class CreateTempio extends Group {

	protected TransformGroup TG = new TransformGroup();
	protected TransformGroup TGanteriore = new TransformGroup();
	protected TransformGroup TGposteriore = new TransformGroup();
	protected TransformGroup TGsinistra = new TransformGroup();
	protected TransformGroup TGdestra = new TransformGroup();
	protected TransformGroup TGscala = new TransformGroup();
	protected float largColonnato = 0;
	protected float largFacciata = 0;

	public CreateTempio(float scaleFactor) {
		//applico una scalatura per variare la dimensione del tempio
		Transform3D dimension = new Transform3D();
		dimension.setScale(scaleFactor);
		//s è lo scale factor da applicare alla Facciata
		int s = 1;
		//numero di colonne da passare al Colonnato
		int numCol = 10;
		//creo le facciate
		Facciata anteriore = new Facciata(s);
		Facciata posteriore = new Facciata(s);
		//creazione dei colonnati
		Colonnato sinistra = new Colonnato(numCol);
		Colonnato destra = new Colonnato(numCol);
		//recupero le informazioni sulla larghezza della facciata
		largFacciata = anteriore.getLarghezza();
		//recupero la loro dimensione per il posizionamento della facciata
		largColonnato = sinistra.getLarghezza();
		posizioneScala(largFacciata, largColonnato);
		positionLati(sinistra, destra, largFacciata);
		positionFacciate(anteriore, posteriore, largColonnato);
		//aggiungo gli elementi al TG
		TG.addChild(TGanteriore);
		TG.addChild(TGposteriore);
		TG.addChild(TGsinistra);
		TG.addChild(TGdestra);
		TG.addChild(TGscala);
		TG.setTransform(dimension);
		addChild(TG);

	}

	private void positionFacciate(Facciata anteriore, Facciata posteriore, float largColonnato) {
		//fisso la posizione
		Transform3D posPost = new Transform3D();
		Transform3D posAnt = new Transform3D();
		posPost.setTranslation(new Vector3d(0, 0, -largColonnato*1.8));
		posAnt.setTranslation(new Vector3d(0, 0, 0));
		//aggiungo ai relativi transformGroup
		TGposteriore.setTransform(posPost);
		TGanteriore.setTransform(posAnt);
		TGposteriore.addChild(posteriore);
		TGanteriore.addChild(anteriore);
	}

	private void positionLati(Colonnato sinistra, Colonnato destra, float largFacciata) {
		//imposto le trasformazioni (posizione e rotazione)
		Transform3D posSx = new Transform3D();
		Transform3D rotSx = new Transform3D();
		posSx.setTranslation(new Vector3d(0, 0, 0));
		rotSx.rotY(Math.PI/2);
		posSx.mul(rotSx);
		//trasformazioni per la colonna destra
		Transform3D posDx = new Transform3D();
		Transform3D rotDx = new Transform3D();
		posDx.setTranslation(new Vector3d(largFacciata*1.66, 0, 0));
		rotDx.rotY(Math.PI/2);
		posDx.mul(rotDx);
		//aggiungo le trasformazioni ai relativi TG
		TGsinistra.setTransform(posSx);
		TGsinistra.addChild(sinistra);
		TGdestra.setTransform(posDx);
		TGdestra.addChild(destra);

	}

	private void posizioneScala(float largFacciata, float largColonnato) {
		//creazione scalinata
		Scala scala = new Scala(largFacciata, 0.2f, largColonnato, 0f, 0f);
		Transform3D position = new Transform3D();
		position.setTranslation(new Vector3d(largFacciata-1, -1.5f, -largColonnato+1));
		TGscala.setTransform(position);
		TGscala.addChild(scala);
	}


}
