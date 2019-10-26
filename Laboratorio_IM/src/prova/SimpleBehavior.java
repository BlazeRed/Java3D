package prova;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.media.j3d.Alpha;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnElapsedTime;

import com.sun.j3d.utils.geometry.ColorCube;



public class SimpleBehavior extends Behavior{

	private TransformGroup targetTG;
	private Transform3D rotation = new Transform3D();
	private double angle = 0.0;

	public SimpleBehavior(TransformGroup targetTG) {
		//Questo Behavior rispondera' ad eventi di tastiera sul key pressed
		this.targetTG = targetTG;
	}

	public void initialize() {
		this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
	}

	//rotazione oggetto con pressione tasto
	//	public void processStimulus(Enumeration criteria) {
	//		//incrementa l'angolo
	//		angle += 0.1;
	//		//Evita problemi di overflow
	//		if(angle>2*Math.PI) angle = 0;
	//		//imposta la rotazione dell'angolo
	//		rotation.rotY(angle);
	//		targetTG.setTransform(rotation);
	//		//Resetta il behaviour per continuare a rispondere ad eventi di tastiera
	//		this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
	//	}

	//rotazione oggetto con pressione tasto (tieni premuto)
	//	public void processStimulus(Enumeration criteria){
	//		//Recupera gli stimoli che hanno attivato il behavior
	//		AWTEvent[] ev=null;
	//		while (criteria.hasMoreElements()){
	//			Object obj=criteria.nextElement();
	//			if (obj instanceof WakeupOnAWTEvent)
	//				ev=((WakeupOnAWTEvent)obj).getAWTEvent();}
	//		if (ev!=null)
	//			for (int i=0;i<ev.length;i++) //scorre tutti gli eventi AWT in cerca di un evento di tastiera
	//				if (ev[i] instanceof KeyEvent){
	//					//Recupera l'evento
	//					KeyEvent key=(KeyEvent)ev[i];
	//					//Recupera il codice dell'evento
	//					int code=key.getKeyCode();
	//					if (code==KeyEvent.VK_LEFT){
	//						//decrementa l'angolo
	//						angle-=0.1;
	//						//Evita problemi di overflow
	//						if (angle<0) angle=2*Math.PI;
	//					}else if (code==KeyEvent.VK_RIGHT) { //Codice freccia a destra
	//						//Incrementa l'angolo
	//						angle+=0.1;
	//						//Evita problemi di overflow
	//						if (angle>2*Math.PI) angle=0;
	//					}
	//					//imposta la rotazione dell'angolo
	//					rotation.rotY(angle);
	//					targetTG.setTransform(rotation);
	//				}
	//		//Resetta il Behavior per continuare a rispondere ad eventi di tastiera
	//		this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
	//	}

	//rotazione oggetto con mouse 
	public void processStimulus(Enumeration criteria) {
		//incrementa l'angolo
		angle += 0.1;
		//Evita problemi di overflow
		if(angle>2*Math.PI) angle = 0;
		//imposta la rotazione dell'angolo
		rotation.rotY(angle);
		targetTG.setTransform(rotation);
		//Resetta il behaviour per continuare a rispondere ad eventi di tastiera
		this.wakeupOn(new WakeupOnElapsedTime(100));
	}



}
