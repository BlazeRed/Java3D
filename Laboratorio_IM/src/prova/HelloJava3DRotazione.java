package prova;
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

public class HelloJava3DRotazione extends Applet{
	
	public HelloJava3DRotazione() {
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
	
	public TransformGroup createSubGraph(){
	       TransformGroup transform = new TransformGroup(); // crea oggetto TG
	       Transform3D td3 = new Transform3D(); // creo oggetto per la trasformazione
	       td3.rotZ(Math.PI*0.7d);// def. rotazione su z
	       transform.setTransform(td3); // assegno a transform la trasformazione
	       transform.addChild(new ColorCube(0.3)); //aggiungo al TG come figlio il cubo
	       return transform;
	}
	
	public static void main(String[] args) {
		new MainFrame(new HelloJava3DRotazione(), 1024, 768);	
	}
}
