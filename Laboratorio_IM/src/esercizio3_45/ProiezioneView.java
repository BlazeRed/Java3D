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

public class ProiezioneView extends Applet{
	
	public ProiezioneView() {
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
		//Impostazione della distanza dal piano sullo sfondo 
		myView.setBackClipDistance(10);
		//Impostazione del clip dal piano frontale 
		myView.setFrontClipDistance(0.55);
		//Impostazione del campo visivo 
		myView.setFieldOfView(Math.PI/4);
		//Impostazione del tipo di proiezione 
		//myView.setProjectionPolicy(View.PERSPECTIVE_PROJECTION); 
		myView.setProjectionPolicy(View.PARALLEL_PROJECTION); 
		myView.setVisibilityPolicy(View.VISIBILITY_DRAW_ALL); 
		createViewBranch(simpleU); simpleU.addBranchGraph(scene);
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
		new MainFrame(new ProiezioneView(), 1024, 768);

	}

}
