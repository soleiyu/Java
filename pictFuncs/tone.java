
public final class tone
{
	public static pict amp(pict inp)
	{
		int max = 0;
		int min = 255;
		pict res = new pict(inp.width, inp.height, 255, 0, 0, 0);
		
		for (int x = 0; x < inp.width; x++)
			for (int y = 0; y < inp.height; y++)
				for (int i = 1; i < 4; i++)
				{
					if (max < inp.px[x][y][i])
						max = inp.px[x][y][i];
					if (inp.px[x][y][i] < min)
						min = inp.px[x][y][i];
				}
		
		int d = max - min;
		
		float ratio = (float)255 / (float)d;

		for (int x = 0; x < inp.width; x++)
			for (int y = 0; y < inp.height; y++)
				for (int i = 1; i < 4; i++)
					res.px[x][y][i] = (int)(inp.px[x][y][i] * ratio);

		int v = (int)(min * ratio);
	
		for (int x = 0; x < inp.width; x++)
			for (int y = 0; y < inp.height; y++)
				for (int i = 1; i < 4; i++)
					res.px[x][y][i] -= v;

		return res;
	}

	public static pict vivid(pict inp)
	{
		pict res = new pict(inp.width, inp.height);
	
		for(int y = 0; y < inp.height; y++)
			for(int x = 0; x < inp.width; x++)
			{
				res.px[x][y][0] = inp.px[x][y][0];
				res.px[x][y][1] = 
					(int)(255 * (1.0 - Math.cos(3.1415 * inp.px[x][y][1] / 511)));
				res.px[x][y][2] =
					(int)(255 * (1.0 - Math.cos(3.1415 * inp.px[x][y][2] / 511)));
				res.px[x][y][3] =
					(int)(255 * (1.0 - Math.cos(3.1415 * inp.px[x][y][3] / 511)));
			}

		return res;
	}


	public static pict light(pict inp)
	{
		pict res = new pict(inp.width, inp.height);

		for (int y = 0; y < inp.height; y++)
			for (int x = 0; x < inp.width; x++)			
			{
				res.px[x][y][0] = inp.px[x][y][0];
				res.px[x][y][1] =
					(int)(255 * Math.sin(3.1415 * inp.px[x][y][1] / 511));
				res.px[x][y][2] =
					(int)(255 * Math.sin(3.1415 * inp.px[x][y][2] / 511));
				res.px[x][y][3] =
					(int)(255 * Math.sin(3.1415 * inp.px[x][y][3] / 511));
			}
		
		return res;
	}
}
