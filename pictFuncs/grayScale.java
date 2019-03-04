

public final class grayScale
{
    public static pict gScale(pict inp)
    {
	pict res = new pict(inp.width, inp.height);

	for(int y = 0; y < res.height; y++)
            for(int x = 0; x < res.width; x++)
                {
                    int ave = (inp.px[x][y][1] +
                               inp.px[x][y][2] +
                               inp.px[x][y][3]) / 3;

                    res.px[x][y][0] = inp.px[x][y][0];
                    res.px[x][y][1] = ave;
                    res.px[x][y][2] = ave;
                    res.px[x][y][3] = ave;
                }

        return res;
    }

    public static pict luminance(pict inp)
    {
	pict res = new pict(inp.width, inp.height);

	for(int y = 0; y < inp.height; y++)
            for(int x = 0; x < inp.width; x++)
                {
                    int max = inp.px[x][y][1];
                    int min = max;
                    if(max < inp.px[x][y][2])
                        max = inp.px[x][y][2];
                    else if(inp.px[x][y][2] < min)
                        min = inp.px[x][y][2];
                    if(max < inp.px[x][y][3])
                        max = inp.px[x][y][3];
                    else if(inp.px[x][y][3] < min)
                        min = inp.px[x][y][3];

                    int ave = (min + max) / 2;

                    res.px[x][y][0] = inp.px[x][y][0];
                    res.px[x][y][1] = ave;
                    res.px[x][y][2] = ave;
                    res.px[x][y][3] = ave;
                }

        return res;
    }

    public static int[][][] gScale(int[][][] inp, int w, int h)
    {
	int[][][]res = new int[w][h][4];

	for(int y = 0; y < h; y++)
	    for(int x = 0; x < w; x++)
		{
		    int ave = (inp[x][y][1] + 
			       inp[x][y][2] + 
			       inp[x][y][3]) / 3;

		    res[x][y][0] = inp[x][y][0];
		    res[x][y][1] = ave;
                    res[x][y][2] = ave;
                    res[x][y][3] = ave;
		}

	return res;
    }

    public static int[][][] luminance(int[][][] inp, int w, int h)
    {
	int[][][]res = new int[w][h][4];
	for(int y = 0; y < h; y++)
            for(int x = 0; x < w; x++)
		{
		    int max = inp[x][y][1];
		    int min = max;
		    if(max < inp[x][y][2])
			max = inp[x][y][2];
		    else if(inp[x][y][2] < min)
			min = inp[x][y][2];
		    if(max < inp[x][y][3])
			max = inp[x][y][3];
                    else if(inp[x][y][3] < min)
			min = inp[x][y][3];

                    int ave = (min + max) / 2;
		    
                    res[x][y][0] = inp[x][y][0];
                    res[x][y][1] = ave;
		    res[x][y][2] = ave;
                    res[x][y][3] = ave;
                }

        return res;
    }

}