package esercizio3_1;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Rotazione extends Applet{
	
	public Rotazione() {
		setLayout(new BorderLayout());
		Transform3D t = new Transform3D();
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas3D = new Canvas3D(config);
		add("Center", canvas3D);
		BranchGroup scene = createSceneGraph();
		scene.compile();
		
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
		simpleU.getViewingPlatform().setNominalViewingTransform();
		simpleU.addBranchGraph(scene);
	}
	
	//funzione che crea il sottografo
	public  BranchGroup createSceneGraph() {
		BranchGroup node = new BranchGroup();
		TransformGroup TG = createSubGraph();
		node.addChild(TG);
		return node;
	}
	
	//Funzione che crea un nuovo TG e aggiunge un cubo come suo figlio 
	public TransformGroup createSubGraph(){
		// crea nuovo TG
		TransformGroup transform = new TransformGroup();
		// crea oggetto per la trasformazione
		Transform3D t3d = new Transform3D(); 
		//Definisco rotazione sugli assi
		t3d.rotZ(-Math.PI/3);
		// assegno a transform la trasformazione
		transform.setTransform(t3d); 
		//aggiungo al TG come figlio il cubo
		transform.addChild(new ColorCube(0.3));  
		return transform;
	}
	
	public static void main(String[] args) {
		new MainFrame(new Rotazione(), 1024, 768);	
	}

}
