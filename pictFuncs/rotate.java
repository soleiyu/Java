public final class rotate
{
    public static pict rot(pict inp, float th)
    {
	System.out.println(th);

	pict res = new pict (inp.width, inp.height, 0, 0, 0, 0);

	int cx = inp.width / 2;
	int cy = inp.height / 2;

	for(int x = 0; x < inp.width; x++)
	    for(int y = 0; y < inp.height; y++)
		{
		    int px = x - cx;
		    int py = y - cy;		    

		    float nx = (float)px * (float)Math.cos(th) + (float)py * (float)Math.sin(th);
		    float ny = (float)px * (float)-Math.sin(th) + (float)py * (float)Math.cos(th);
		    
		    nx += (float)cx;
		    ny += (float)cy;	       

		    if(nx < 0)
			continue;
		    if(ny < 0)
			continue;
		    if(inp.width - 1.000001 < nx)
			continue;
		    if(inp.height - 1.000001 < ny)
			continue;
		    
		    st_col c = pixel.getPixel(inp, nx, ny);
		    
		    res.px[x][y][0] = c.a;
		    res.px[x][y][1] = c.r;
		    res.px[x][y][2] = c.g;
		    res.px[x][y][3] = c.b;
		}

	return res;
    }

    public static pict[] moveRotl(pict inp, float th, int num)
    {
	pict[] res = new pict[num];

	res[0] = inp;
	
	for(int i = 1; i < num; i++)
	    res[i] = rot(inp, (float)Math.pow(1.0f + th, -i) - 1.0f);

	return res;
    }

    public static pict rotfull(pict inp, float th)
    {
	int msx = (int)(inp.width * 1.5f);
	int msy = (int)(inp.height * 1.5f);

	pict cache = new pict(msx, msy, 0, 0, 0, 0);

        int msxh = inp.width / 4;
        int msyh = inp.height / 4;

	//coppy
	for(int x = 0; x < inp.width; x++)
	    for(int y = 0; y < inp.height; y++)
		for(int i = 0; i < 4; i++)
		    cache.px[x + msxh][y + msyh][i] = inp.px[x][y][i];

	//rotate
	cache = rot(cache, th);
	
	//cut
	int mx = -1;
	int Mx = -1;
	int my = -1;
	int My = -1;

	for(int y = 0; y < msy; y++)
	    {
		for(int x = 0; x < msx; x++)
		    if(cache.px[x][y][0] != 0)
		    {
			my = y;
			break;
		    }
		    
		if(-1 < my)
		    break;
	    }
	for(int y = 0; y < msy; y++)
            {
                for(int x = 0; x < msx; x++)
                    if(cache.px[x][msy - 1 - y][0] != 0)
			{
			    My = msy - 1 - y;
			    break;
			}

                if(-1 <My)
		    break;
            }
        for(int x = 0; x < msx; x++)
            {
                for(int y = 0; y < msy; y++)
                    if(cache.px[x][y][0] != 0)
			{
			    mx = x;
			    break;
			}

                if(-1 <mx)
		    break;
            }
        for(int x = 0; x < msx; x++)
            {
                for(int y = 0; y < msy; y++)
                    if(cache.px[msx - 1 - x][y][0] != 0)
                        {
                            Mx = msx - 1 - x;
                            break;
                        }

                if(-1 <Mx)
                    break;
            }

	pict res = new pict(Mx - mx, My - my, 0, 0, 0, 0);

	for(int x = 0; x < Mx - mx; x++)
	    for(int y = 0; y < My - my; y++)
		for(int i = 0; i < 4; i++)
		    res.px[x][y][i] = cache.px[mx + x][my + y][i];

	return res;
    }
}