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
import com.sun.j3d.utils.universe.ViewingPlatform;


public class BaseView extends Applet {
	
	public BaseView() {
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
        //simpleU.getViewingPlatform().setNominalViewingTransform();
        
        //spostamento visuale con rotazione 
        Transform3D t3d = new Transform3D();
        t3d.setTranslation(new Vector3d(0,0,10));
        Transform3D t3d2 = new Transform3D();
        t3d2.rotY(Math.PI/4);
        t3d2.invert();
        t3d2.mul(t3d);
        //creazione viewing platform (transformGroup e viewPlatform)
        ViewingPlatform vp = simpleU.getViewingPlatform();
        TransformGroup vtg = vp.getViewPlatformTransform();
        vtg.setTransform(t3d2);
        
        simpleU.addBranchGraph(scene);
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
		new MainFrame(new BaseView(), 1024, 768);

	}

}
