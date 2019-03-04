public final class color
{
    public static pict ngps(pict inp)
    {
	pict res = new pict(inp.width, inp.height);

	for(int y = 0; y < inp.height; y++)
	    {
		for(int x = 0; x < inp.width; x++)
		    {
			res.px[x][y][0] = inp.px[x][y][0];
			res.px[x][y][1] = 255 - inp.px[x][y][1];
			res.px[x][y][2] = 255 - inp.px[x][y][2];
			res.px[x][y][3] = 255 - inp.px[x][y][3];
		    }
	    }

	return res;
    }

    public static pict dark(pict inp, float ratio)
    {
	pict res = new pict(inp.width, inp.height);

	for(int y = 0; y < inp.height; y++)
	    for(int x = 0; x < inp.width; x++)
		{
		    res.px[x][y][0] = inp.px[x][y][0];
		    res.px[x][y][1] = (int)(inp.px[x][y][1] * ratio);
                    res.px[x][y][2] = (int)(inp.px[x][y][2] * ratio);
                    res.px[x][y][3] = (int)(inp.px[x][y][3] * ratio);
		}
	return res;
    }

    public static pict greenKill(pict inp)
    {
	pict res = new pict(inp);

	for(int y = 0; y < inp.height; y++)
	    for(int x = 0; x < inp.width; x++)
		if(inp.px[x][y][1] < inp.px[x][y][2] &&
		   inp.px[x][y][3] < inp.px[x][y][2])
		    if(15 < inp.px[x][y][2] - inp.px[x][y][1] &&
		       15 < inp.px[x][y][2] - inp.px[x][y][3])
			res.px[x][y][2] = 0;		    
		    
	return res;
    }

}