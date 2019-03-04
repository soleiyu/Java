public final class frame
{
    public static pict mkShadowFrame(pict inp, int ssize, int fsize)
    {
	return mkShadow(mkFrame(inp, fsize), ssize);
    }

    public static pict mkFrame(pict inp, int fsize)
    {
	int w = inp.width + 2 * fsize;
	int h = inp.height + 2 * fsize;
	pict res = new pict(w, h);

	for(int y = 0; y < h; y++)
	    for(int x = 0; x < w; x++)
		for(int i = 0; i < 4; i++)
		    res.px[x][y][i] = 255;

	for(int y = 0; y < inp.height; y++)
            for(int x =0; x < inp.width; x++)
		for(int i = 0; i < 4; i++)
                    res.px[x + fsize][y + fsize][i] = inp.px[x][y][i];

	return res;
    }

    public static pict mkShadow(pict inp, int ssize)
    {
	int w =inp.width + 2 *ssize;
        int h =inp.height + 2 * ssize;
	float sratio = (float)128 / (float)ssize;
        
	pict res = new pict(w, h);

        for(int y = 0; y < ssize; y++)
            for(int x = 0; x < w; x++)
		{
		    int val = (int)(255 - sratio * y);
                    res.px[x][y][0] = (int)(127 - sratio * (ssize - y - 1));
                    res.px[x][h - y - 1][0] = (int)(127 - sratio * (ssize - y - 1));
		}
	
	for(int y = 0; y < h; y++)
	    for(int x = 0; x < ssize; x++)
                {
		    int val = (int)(255 - sratio * x);
		    res.px[x][y][0] = (int)(127 - sratio * (ssize - x - 1));
		    res.px[w - x - 1][y][0] = (int)(127 - sratio * (ssize - x - 1));
                }

	for(int y = 0; y < ssize; y++)
	    for(int x = 0; x < ssize; x++)
		{
		    float d = (float)Math.sqrt((ssize - x) * (ssize - x) + 
					       (ssize - y) * (ssize - y));
		    int val = (int)(128 + sratio * d);
		    int aval = (int)(128 - sratio * d);

		    if(255 < val)
			{
			    val = 255;
			    aval = 0;
			}
		    res.px[x][y][0] = aval;		    
		    res.px[w - x - 1][y][0] = aval;
                    res.px[x][h - y - 1][0] = aval;
                    res.px[w - x - 1][h - y - 1][0] = aval;
		}
	
	for(int y = 0; y < inp.height; y++)
            for(int x =0; x < inp.width; x++)
                for(int i = 0; i < 4; i++)
                    res.px[x + ssize][y + ssize][i] = inp.px[x][y][i];

        return res;

    }
}