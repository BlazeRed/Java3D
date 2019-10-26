package esercizio3_3;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;


public class PuntoFuga extends Applet{

	public PuntoFuga() {
        setLayout(new BorderLayout()); //layout manager del container
        //trova la miglior configurazione grafica per il sistema
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        // Canvas3: si occupa del rendering 3D on-screen e off-screen
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();    // creazione del sottografo principale
        scene.compile();
        //Creazione del SimpleUniverse
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        
        //creo la view per il simple universe
        createViewBranch(simpleU);
        
        simpleU.addBranchGraph(scene);
	}
	
	//funzione che crea e gestisce la ViewBranch per un certo simpleUniverse
	public TransformGroup createViewBranch(SimpleUniverse simpleU) {
		//Definisco 3 variabili per i diversi punti di fuga
        Transform3D fuga1 = new Transform3D();
        Transform3D fuga2 = new Transform3D();
        Transform3D fuga3 = new Transform3D();
		//utilizzo la funzione lookAt per lo spostamento della telecamera 
        fuga1.lookAt(new Point3d(0d, 0d, 2d), new Point3d(0d, 0d, 0d), new Vector3d(0d, 1d, 0d));
        fuga1.invert();
        
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
		new MainFrame(new PuntoFuga(), 1024, 768);

	}


}
