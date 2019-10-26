package prova;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture2D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3d;

import com.jogamp.opengl.util.texture.Texture;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class PlaneTexture extends Applet{
	
	public PlaneTexture() {
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
        t3d.lookAt(new Point3d(0d, 0d, 5d), new Point3d(0d, 0d, 0d), new Vector3d(0d, 1d, 0d));
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
		
		//Crea un piano
	    QuadArray plane=new QuadArray(4,GeometryArray.COORDINATES | GeometryArray.TEXTURE_COORDINATE_2);
	    Point3f p=new Point3f(); 
	    p.set(-1,1,0); plane.setCoordinate(0,p);
	    p.set(-1,-1,0); plane.setCoordinate(1,p); 
	    p.set(1,-1,0); plane.setCoordinate(2,p); 
	    p.set(1,1,0); plane.setCoordinate(3,p);
	    
	    //Imposta le coordinate della texture
	    TexCoord2f q=new TexCoord2f();
	    q.set(0,3); plane.setTextureCoordinate(0,0,q); 
	    q.set(0,0); plane.setTextureCoordinate(0,1,q); 
	    q.set(3,0); plane.setTextureCoordinate(0,2,q); 
	    q.set(3,3); plane.setTextureCoordinate(0,3,q);
	    
	    //Crea l'aspetto del piano
	    Appearance appear=new Appearance();
	    //Carica la texture
	    TextureLoader loader=new TextureLoader("../texture/mattone.jpg",this);
	    ImageComponent2D image=loader.getImage();
	    //Crea la texture
	    Texture2D texture=new Texture2D(Texture2D.BASE_LEVEL, Texture2D.RGBA, image.getWidth(),image.getHeight());
	    texture.setImage(0,image);
	    
	    //Imposta il comportamento ai bordi
	    texture.setBoundaryModeS(Texture2D.CLAMP);//orizzontale
	    texture.setBoundaryModeT(Texture2D.CLAMP); //verticale
	     
	    //Imposta la texture nell'aspetto
	    appear.setTexture(texture);
		
		Shape3D planeObj = new Shape3D(plane, appear);
		
		TransformGroup transform = new TransformGroup(); // crea oggetto TG
		transform.addChild(planeObj);  //aggiungo al TG come figlio il cubo
		BG.addChild(transform); //aggiunge l’oggetto TG come figlio del BrachGruop
		return BG; 
	}
	
	public static void main(String[] args) {
		new MainFrame(new PlaneTexture(), 1024, 768);

	}
}
