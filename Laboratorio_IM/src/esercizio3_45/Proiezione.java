package esercizio3_45;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class Proiezione extends Applet {
	
	public Proiezione() {
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration(); 
		Canvas3D canvas3D = new Canvas3D(config);
		add("Center", canvas3D);
		BranchGroup scene = createSceneGraph();
		scene.compile();
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D); 
		simpleU.getViewingPlatform().setNominalViewingTransform();
		//Accedo all'oggetto view del SimpleUniverse
		View myView = simpleU.getViewer().getView();
		//Abilitazione compatibility mode per modificare la matrice di proiezione 
		myView.setCompatibilityModeEnable(true);
		//Creazione trasformazione
		Transform3D t3d = new Transform3D(); 
		//Impostazione matrice di proiezione ortogonale e prospettica 
		//t3d.ortho(-2, 2, -2, 2, 2, 10.0); 
		//t3d.ortho(-2, 2, -2, 2, 2.7, 10.0); 
		//t3d.perspective(Math.PI/6, 1024/768, 2.7, 10); 
		t3d.perspective(Math.PI/4, 1024/768, 2.6, 10); 
		myView.setLeftProjection(t3d);
		createViewBranch(simpleU);
		simpleU.addBranchGraph(scene);
	}
	
	//funzione che crea e gestisce la ViewBranch per un certo simpleUniverse
	public TransformGroup createViewBranch(SimpleUniverse simpleU) {
		Transform3D fuga2 = new Transform3D();
        Transform3D fuga3 = new Transform3D();
		//utilizzo la funzione lookAt per lo spostamento della telecamera 
        fuga2.lookAt(new Point3d(2d, 0d, 2d), new Point3d(0d, 0d, 0d), new Vector3d(0d, 1d, 0d));
        fuga2.invert();
        
        fuga3.lookAt(new Point3d(2d, 3d, 2d), new Point3d(0d, 0d, 0d), new Vector3d(0d, 1d, 0d));
        fuga3.invert();
        
        ViewingPlatform vp = simpleU.getViewingPlatform();
        TransformGroup vtg = vp.getViewPlatformTransform();
        vtg.setTransform(fuga2);
		return vtg;
	}

	/**
	* Funzione che crea il sottografo
	* @return il BranchGroup da aggiungere al SimpleUniverse
	*/
	public BranchGroup createSceneGraph() {
		BranchGroup BG = new BranchGroup();
		TransformGroup transform = new TransformGroup(); // crea oggetto TG
		
		transform.addChild(new ColorCube(0.3));  //aggiungo al TG come figlio il cubo
		BG.addChild(transform); //aggiunge l’oggetto TG come figlio del BrachGruop
		return BG; 
	}
	
	public static void main(String[] args) {
		new MainFrame(new Proiezione(), 1024, 768);

	}

}
