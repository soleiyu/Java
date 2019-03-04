import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

// Format pictFrame directory sizeX sizeY

public class pictFrame
{
	public static void main(String[] args) throws IOException {	
	
	//Picture Origin Instance
	pict p_org = new pict();
	//Canvas Size
	int cx = Integer.parseInt(args[1]);
	int cy = Integer.parseInt(args[2]);

	//Make Canvas
	pict p_new = new pict(cx, cy, 255, 255, 255, 255);
	
	//Get Directry Name And Files
	File file = new File(args[0]);
        File files[] = file.listFiles();
	System.out.println(files.length);
	System.out.println(files[0].getName());

	//Open 1st Picture
	p_org.open(args[0] + "/" + files[otherUtil.getRand(files.length)].getName());

	//Make Frame 1st p
  pict p_t = frame.mkShadow(frame.mkFrame(tone.light(tone.vivid(p_org)), 15), 15);
	System.out.println("PrepareDone.");

	for (int i = 0; i < files.length; i++) {
		System.out.println( i+1 + "/" + files.length);
		System.out.println(args[0] + "/" + files[i].getName());

		//Open Picture
		pict p = new pict();
		p.open(args[0] + "/" + files[i].getName());

		//Image Proccesing
		if(i % 4 == 0)
		    p = tone.light(tone.light(p));
		else if(i % 4 == 1)
		    p = grayScale.gScale(p);
		else if(i % 4 == 2)
		    p = tone.light(tone.vivid(grayScale.luminance(p)));
		else 
		    p = tone.light(color.dark(p, 0.7f));
		
		//Make Frame
		p = frame.mkShadowFrame(p, 15, 15);
	
		//Random Rotate
		float th = (float)((float)(otherUtil.getRand(20) - 10) / (float)20);
		if(th != 0)
		  p = rotate.rotfull(p, th); 
		
		//Random Offset Position
		int posx = otherUtil.getRandomPosx(p_new, p, 10);  
		int posy = otherUtil.getRandomPosy(p_new, p, 10);

		//Add to Canvas
		p_new = mixer.add(p_new, p, posx, posy);
	}
	
	p_new = mixer.add(p_new, p_t, (cx - p_t.width) / 2, (cy - p_t.height) / 2);
	
	//Dark Grd
	p_new = mixer.bcircle(p_new, color.dark(p_new, 0.7f));		       	
	//Save Picture
	p_new.write("Ans.png");

	System.out.println("D O N E .");
 	}
}
