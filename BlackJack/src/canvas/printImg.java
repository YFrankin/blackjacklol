package canvas;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

public class printImg extends Canvas{
	public void paint(Graphics g) {  
		  
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("amogus.jpg");  
        g.drawImage(i, 0,0,this);  
          
    }  
	public static void main(String[] args) throws Exception {
		SaveImg s = new SaveImg();
		s.saveImage();
		printImg m=new printImg();  
        JFrame f=new JFrame();  
        f.add(m);  
        f.setSize(4096,4096);  
        f.setVisible(true);  
	}
}
