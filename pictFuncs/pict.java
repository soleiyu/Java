import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class pict
{
	public int width;
	public int height;
	public int[][][]px;

	public pict()
	{/*DEFAULT CONSTRACTOR*/}

	public pict(int w, int h)
	{
		px = new int[w][h][4];
		width = w;
		height = h;

		for(int x = 0; x < w; x++)
			for(int y = 0; y < h; y++)
			{
				px[x][y][0] = 255;
				px[x][y][1] = 0;
				px[x][y][2] = 0;
				px[x][y][3] = 0;
			}
	}

	public pict(pict inp)
	{
		width = inp.width;
		height = inp.height;
		px = inp.px;
	}

	public pict(int w, int h, int a, int r, int g, int b)
	{
		px = new int[w][h][4];
		width = w;
		height = h;

		for(int y = 0; y < h; y++)
			for(int x = 0; x < w; x++)
			{
				px[x][y][0] = a;
				px[x][y][1] = r;
				px[x][y][2] = g;
				px[x][y][3] = b;
			}
	}

	public void open(String fn)throws IOException
	{
		BufferedImage img = ImageIO.read(new File(fn));
       
		width = img.getWidth();
		height = img.getHeight();
		px = new int[width][height][4]; // X Y A R G B                                    
        
		System.out.println(width + " x " + height);

		for(int y = 0; y < height; y++)
			for(int x = 0; x < width; x++)
			{
				int color = img.getRGB(x, y);
				px[x][y][0] = a(color);
				px[x][y][1] = r(color);
				px[x][y][2] = g(color);
				px[x][y][3] = b(color);
			}
	
	//	System.out.println("FileOpenDone.");
	}

	public void write(String fn)throws IOException
	{
		BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < height; y++)
			for(int x = 0; x < width; x++)
				output.setRGB(x, y,
					argb(px[x][y][0],
						px[x][y][1],
						px[x][y][2],
						px[x][y][3]));

		ImageIO.write(output, "png", new File(fn));

		System.out.println("FileWriteDone.");
	}

	int a(int c){ return c >>> 24; }
	int r(int c){ return c >> 16&0xff; }
	int g(int c){ return c >> 8&0xff; }
	int b(int c){ return c&0xff; }
	int argb(int a,int r,int g,int b){ return a<<24 | r <<16 | g <<8 | b; }
}
