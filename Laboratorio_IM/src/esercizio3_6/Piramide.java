package esercizio3_6;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Group;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;

public class Piramide extends Group{
	//variabile che indica il numero di tronchi da creare
	protected static final int n_tronchi = 9;
	protected Tronco tronco[] = new Tronco[n_tronchi+1];
	//nodo TG per tronco[] 
	protected TransformGroup TGtronchi[] = new TransformGroup [n_tronchi+1];
	//nodo TG generico
	protected TransformGroup TG = new TransformGroup();
	protected Transform3D t3dTronchi[] = new Transform3D[n_tronchi+1]; 
	protected Transform3D t3d = new Transform3D();																				
	//terminale della piramide
	protected Box punta = new Box(0.5f,0.5f, 0.2f, createAppearance());
	
	public Piramide() {
		int i;
		double baseMinore = 2.3;
		double baseMaggiore = 2.4;
		double altezza = 0.2;
		//creazione dei vari livelli (tronchi)
		for(i = 0; i < tronco.length-1; i++) {
			tronco[i] = new Tronco(altezza, baseMinore, baseMaggiore);
			//aggiorno le dimensioni delle basi dei tronchi;
			baseMinore = baseMinore - 0.2;
			baseMaggiore = baseMaggiore - 0.2;
			//creo TG per ogni tronco che costruisco
			TGtronchi[i] = new TransformGroup();
			t3dTronchi[i] = new Transform3D();
			t3dTronchi[i].setTranslation(new Vector3d(0, 0, i*altezza));
			TGtronchi[i].setTransform(t3dTronchi[i]);
			TGtronchi[i].addChild(tronco[i]);
			TG.addChild(TGtronchi[i]);
		}
		
		//traslazione per posizionare la punta della piramide
		t3dTronchi[i] = new Transform3D();
		t3dTronchi[i].setTranslation(new Vector3d(0, 0, (i*altezza)+altezza/2));
		TGtronchi[i] = new TransformGroup();
		TGtronchi[i].setTransform(t3dTronchi[i]);
		TGtronchi[i].addChild(punta);
		
		TG.setTransform(t3dTronchi[i]);
		TG.addChild(TGtronchi[i]);
		addChild(TG);
	}
	
	static private Appearance createAppearance(){
		Appearance appearance = new Appearance(); 
		ColoringAttributes color = new ColoringAttributes(); 
		color.setColor(1.0f, 0.6f, 0.2f); 
		appearance.setColoringAttributes(color); 
		appearance.setPolygonAttributes(new PolygonAttributes( PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_NONE, 0.0f));
		//Applico trasparenza al box per migliorare effetto visivo 
		appearance.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.BLENDED, 0.35f));
		return appearance;
	}

}
