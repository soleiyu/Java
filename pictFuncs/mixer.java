public final class mixer
{
    public static pict integra(pict[] inp)
    {
	int num = inp.length;
	float ratio = 1.0f / (float)num;
	pict res = new pict(inp[0].width, inp[0].height, 0, 0, 0, 0);
	
	for(int i = 0; i < num; i++)
	    for(int x = 0; x < inp[0].width; x++)
		for(int y = 0; y < inp[0].height; y++)
		    for(int z = 0; z < 4; z++)
			res.px[x][y][z] += (int)(inp[i].px[x][y][z] * ratio);
	
	return res;
    }
    public static pict integra_seq(pict[] inp)
    {
	int num = inp.length;
	pict res = new pict(inp[0].width, inp[0].height, 0, 0, 0, 0);
	
	int sum = 0;
	for(int i = 0; i < num; i++)
	    sum += (i + 1);

	for(int i = 0; i < num;i++)
	    {
		float ratio = (float)(num - i) / (float)sum;
 
		for(int x = 0; x < inp[0].width; x++)
		    for(int y = 0; y < inp[0].height; y++)
			for(int z = 0; z < 4; z++)
			    res.px[x][y][z] += (int)(inp[i].px[x][y][z] * ratio);
	    }
	
	return res;
    }
    public static pict integra_seqa(pict[] inp)
    {
        int num = inp.length;
        pict res = new pict(inp[0].width, inp[0].height, 0, 0, 0, 0);

	for(int x = 0; x < inp[0].width; x++)
	    for(int y = 0; y < inp[0].height; y++)
		{
		    int sum = 0;
		    for(int i = 0; i < num; i++)
			{
			    if(inp[i].px[x][y][0] == 0)
				continue;
			    
			    for(int z = 0; z < 4; z++)
				res.px[x][y][z] += inp[i].px[x][y][z] * (num - i);
			 
			    sum += (num - i);
			}

		    for(int z = 0; z < 4; z++)
			res.px[x][y][z] /= sum;
		}
        return res;
    }

    public static pict add(pict a, pict b, int posx, int posy)
    {
	pict res = new pict(a);

	int asx, asy;
	if(posx < 0)	    
	    asx = 0;
	else	    
	    asx = posx;	
   	
	if(posy < 0)
	    asy = 0;
	else
 	    asy = posy;
	
	int bsx, bsy;
	if(posx < 0)
	    bsx = -posx;
	else 
	    bsx = 0;
   
	if(posy < 0)
	    bsy = -posy;
	else
	    bsy = 0;

	int w, h;
	if(posx < 0)
	    w = b.width + posx;
	else if(a.width < posx + b.width)
	    w = a.width - posx;
	else
	    w = b.width;
	
	if(posy < 0)
            h = b.height + posy;
	else if(a.height < posy + b.height) 
            h = a.height - posy;
	else
            h = b.height;
	for(int y = 0; y < h; y++)
	    {
	       	for(int x = 0; x < w; x++)
		    {
			float bRatio = (float)b.px[bsx + x][bsy + y][0] / (float)255;
			float aRatio = 1.0f - bRatio;
			    
			res.px[asx + x][asy + y][0] = 255;
			res.px[asx + x][asy + y][1] = 
			    (int)(a.px[asx + x][asy + y][1] * aRatio +
				  b.px[bsx + x][bsy + y][1] * bRatio);
                        res.px[asx + x][asy + y][2] = 
			    (int)(a.px[asx + x][asy +y][2] *aRatio +
				  b.px[bsx + x][bsy + y][2] * bRatio);
                        res.px[asx + x][asy + y][3] = 
			    (int)(a.px[asx + x][asy +y][3] *aRatio +
				  b.px[bsx + x][bsy + y][3] * bRatio);
		    }
	    }
	
	return res;
    }

    public static pict sub(pict a, pict b)
    {
	pict res = new pict(a.width, a.height);
	
	for(int y = 0; y < a.height; y++)
            for(int x = 0; x < a.width;x++)
		for(int i = 0; i < 4; i++)
                    {
                        res.px[x][y][i] = a.px[x][y][i] - b.px[x][y][i];
                        if(res.px[x][y][i] < 0)
                            res.px[x][y][i] = 0;
                    }
	return res;
    }

    public static pict sum(pict a, pict b)
    {
	pict res = new pict(a.width, a.height);

	for(int y = 0; y < a.height; y++)
	    for(int x = 0; x < a.width; x++)
		for(int i = 0; i < 4; i++)
		    {
			res.px[x][y][i] = a.px[x][y][i] + b.px[x][y][i];
			if(255 < res.px[x][y][i])
			    res.px[x][y][i] = 255;
		    }
	return res;
    }

    public static pict shadeChecker(pict a, pict b, int stride)
    {
	pict res = new pict(a.width, a.height);

	for(int y = 0; y < a.height; y++)
	    {
		for(int x = 0; x < a.width; x++)
		    {
			float aRatio = (float)0.5 * (float)(
			    (0.5 + 0.5 * Math.cos(2 * 3.14159 * x / stride))+
			    (0.5 + 0.5 * Math.cos(2 * 3.14159 * y / stride)));
			float bRatio = 1 - aRatio;
			
			res.px[x][y][0] = a.px[x][y][0];
			res.px[x][y][1] = (int)(a.px[x][y][1] * aRatio 
						+b.px[x][y][1] * bRatio);
			res.px[x][y][2] = (int)(a.px[x][y][2]* aRatio
						+b.px[x][y][2] * bRatio);
			res.px[x][y][3] = (int)(a.px[x][y][3]* aRatio
						+b.px[x][y][3] * bRatio);
		    }
	    }

	return res;
    }

    public static pict checker(pict a, pict b, int stride)
    {
	pict res = new pict(a);
	
	int xflag;
	int yflag = 0;

	for(int y = 0; y < a.height; y++)
	    {
		xflag = 0;
		if(y % stride == 0)
		    yflag++;

		for(int x = 0; x < a.width; x++)
		    {
			if(x % stride == 0)
			    xflag ++;
			
			if((xflag + yflag) % 2 == 0)
			    {
				res.px[x][y][1] = b.px[x][y][1];
				res.px[x][y][2] = b.px[x][y][2];
                                res.px[x][y][3] = b.px[x][y][3];
			    }
		    }
	    }
	
	return res;
    }

    public static pict xcut(pict a, pict b)
    {
	pict res = new pict(a.width, a.height);

	int hol = a.width / 2;

	for(int y = 0; y < a.height; y++)
	    for(int x = 0; x < a.width; x++)
		if(x < hol)
		    res.px[x][y] = a.px[x][y];
		else
		    res.px[x][y] = b.px[x][y];
	
	return res;
    }

    public static pict bcircle(pict a, pict b)
    {
        pict res = new pict(a.width, a.height);

        int slength = a.width;
        if(slength < a.height)
            slength = a.height;
        slength = slength * slength / 4;

        float aRatio;
        float bRatio;

        int cx = a.width / 2;
        int cy = a.height / 2;

        for(int y = 0; y < a.height; y++)
            {
                for(int x = 0; x < a.width; x++)
                    {
                        int d = sq_dist(cx, cy, x, y);

                        bRatio = (float)d / (float)slength;
                        if(1 < bRatio)
			    bRatio = 1;
                        aRatio = 1 - bRatio;

                        res.px[x][y][0] = a.px[x][y][0];
                        res.px[x][y][1] = (int)(a.px[x][y][1] * aRatio +
                                                b.px[x][y][1] * bRatio);
                        res.px[x][y][2] = (int)(a.px[x][y][2] * aRatio +
                                                b.px[x][y][2] * bRatio);
                        res.px[x][y][3] = (int)(a.px[x][y][3] * aRatio +
						b.px[x][y][3] * bRatio);
		    }
	    }
	
	return res;
    }

    public static pict circle(pict a, pict b)
    {
	pict res = new pict(a.width, a.height);

	int slength = a.width;
	if(a.height < slength)
	    slength = a.height;
	slength = slength * slength / 4;

	float aRatio;
	float bRatio;

	int cx = a.width / 2;
	int cy = a.height / 2;

	for(int y = 0; y < a.height; y++)
	    {
		for(int x = 0; x < a.width; x++)
		    {
			int d = sq_dist(cx, cy, x, y);
			
			bRatio = (float)d / (float)slength;
			if(1 < bRatio)
				bRatio = 1;
			aRatio = 1 - bRatio;

			res.px[x][y][0] = a.px[x][y][0];
			res.px[x][y][1] = (int)(a.px[x][y][1] * aRatio + 
						b.px[x][y][1] * bRatio);
			res.px[x][y][2] = (int)(a.px[x][y][2] * aRatio +
						b.px[x][y][2] * bRatio);
			res.px[x][y][3] = (int)(a.px[x][y][3] * aRatio +
						b.px[x][y][3] * bRatio);
		    }
	    }

	return res;
    }

    static int sq_dist(int x1, int y1, int x2, int y2)
    {
	int xl = x1 - x2;
	int yl = y1 - y2;

	return xl * xl + yl * yl;
    }

}