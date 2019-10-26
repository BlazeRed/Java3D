package prova;
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

import shapes.MyColorCube;

public class Lookat extends Applet{
	
	public Lookat() {
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
        
        //spostamento visuale con rotazione 
        Transform3D t3d = new Transform3D();
        t3d.lookAt(new Point3d(0d, 0d, 10d), new Point3d(0d, 0d, 0d), new Vector3d(0d, 1d, 0d));
        t3d.invert();
        //creazione viewing platform (transformGroup e viewPlatform)
        ViewingPlatform vp = simpleU.getViewingPlatform();
        TransformGroup vtg = vp.getViewPlatformTransform();
        vtg.setTransform(t3d);
        
        simpleU.addBranchGraph(scene);
}
	/**
	* Funzione che crea il sottografo
	* @return il BranchGroup da aggiungere al SimpleUniverse
	*/
	public BranchGroup createSceneGraph() {
		BranchGroup BG = new BranchGroup();
		TransformGroup transform = new TransformGroup(); // crea oggetto TG
		
		Transform3D rot = new Transform3D();
		Transform3D rot2 = new Transform3D();
		
		rot.rotX(Math.PI / 3.5);
		rot2.rotY(Math.PI / 2.9);
		rot.mul(rot2);
		transform.setTransform(rot);
		
		transform.addChild(new MyColorCube());  //aggiungo al TG come figlio il cubo
		BG.addChild(transform); //aggiunge l’oggetto TG come figlio del BrachGruop
		return BG; 
	}
	
	public static void main(String[] args) {
		new MainFrame(new Lookat(), 1024, 768);

	}

}
