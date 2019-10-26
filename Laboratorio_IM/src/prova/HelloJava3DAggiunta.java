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

public class HelloJava3DAggiunta extends Applet{
	
	public HelloJava3DAggiunta() {
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
	
	// Funzione che crea il sottografo
	public BranchGroup createSceneGraph() {
	  BranchGroup BG = new BranchGroup();
	  //Primo nodo
	  TransformGroup TG = new TransformGroup();
	  Transform3D t3d = new Transform3D();
	  t3d.rotX(Math.PI/4);
	  TG.setTransform(t3d);
	  TG.addChild(new ColorCube(0.4));
	  //Secondo nodo
	  TransformGroup TG2 = new TransformGroup();
	  Transform3D t3d2 = new Transform3D();
	  Transform3D temp1 = new Transform3D();
	  t3d2.setTranslation(new Vector3d(-0.5d, 0.0d, 0.0d));
	  temp1.rotY(Math.PI/4);
	  t3d2.mul(temp1);
	  TG2.setTransform(t3d2);
	  TG2.addChild(new ColorCube(0.3));
	  BG.addChild(TG);
	  BG.addChild(TG2);
	 return BG;
	}

	
	public static void main(String[] args) {
		new MainFrame(new HelloJava3DAggiunta (), 1024, 768);	
	}
}
