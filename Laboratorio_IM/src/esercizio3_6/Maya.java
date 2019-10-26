package esercizio3_6;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class Maya extends Applet {
	
	public Maya() {
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration(); Canvas3D canvas3D = new Canvas3D(config);
		add("Center", canvas3D);
		BranchGroup scene = createSceneGraph();
		scene.compile();
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D); simpleU.getViewingPlatform().setNominalViewingTransform();
		Transform3D transform = new Transform3D(); transform.lookAt(
		                new Point3d(0.0, 0, 10.0),
		                new Point3d(0.0, 0.0, 0.0),
		                new Vector3d(0.0, 0.1, 0.0));
		transform.invert();
		TransformGroup vtg = simpleU.getViewingPlatform().getViewPlatformTransform(); vtg.setTransform(transform);
		simpleU.addBranchGraph(scene);
		//codice per permettere la manipolazione tramite un mouse
		OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE);
		orbit.setSchedulingBounds(new BoundingSphere());
		simpleU.getViewingPlatform().setViewPlatformBehavior(orbit); 
		}
		/**
		* Funzione che crea il sottografo (piramide e trasformazione per visualizzazione) *
		* @return il BranchGroup da aggiungere al SimpleUniverse
		*/
		public BranchGroup createSceneGraph() {
		BranchGroup branchGroup = new BranchGroup(); 
		TransformGroup TG = new TransformGroup();
		Transform3D transform3D = new Transform3D(); transform3D.rotX(-Math.PI/4); TG.setTransform(transform3D);
		TG.addChild(new Piramide()); branchGroup.addChild(TG); return branchGroup;
		}
		public static void main(String[] args) {
		new MainFrame(new Maya(), 800, 800);
		}
	
	
}
