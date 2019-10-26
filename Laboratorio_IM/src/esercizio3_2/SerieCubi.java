package esercizio3_2;
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

public class SerieCubi extends Applet {

	public SerieCubi() {
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas3D = new Canvas3D(config);
		add("Center", canvas3D);
		BranchGroup scene = createSceneGraph();
		scene.compile();
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
		simpleU.getViewingPlatform().setNominalViewingTransform();
		simpleU.addBranchGraph(scene);
	}
	
	public BranchGroup createSceneGraph() {
		BranchGroup node = new BranchGroup();
		//numero di cubi da creare
		int n = 5;
		for(int i=0; i<n; i++) {
			TransformGroup TG = createSetCube(n, i);
			node.addChild(TG);
		}
		return node;
	}
	
	public TransformGroup createSetCube(int n, int i) {
		TransformGroup transform = new TransformGroup();
		Transform3D translation = new Transform3D();
		Transform3D far = new Transform3D(); 
		//allontano il cubo dalla telecamera per una visualizzazione migliore
		far.setTranslation(new Vector3d(0d, 0d, -2d));
		//imposto la traslazione dei cubi creati passati come parametro
		translation.setTranslation(new Vector3d( (Math.sin(i * 2 * Math.PI / n)), (Math.cos(i * 2 * Math.PI / n)), 0d));
		translation.mul(far);
		transform.setTransform(translation);
		transform.addChild(new ColorCube(0.2));
		return transform;
	}
	
	public static void main(String[] args) {
		new MainFrame(new SerieCubi(), 1024, 768);
	}
}
