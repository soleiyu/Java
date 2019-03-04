import java.util.Random;

public final class otherUtil
{
    public static int getRand(int limit)
    {
	Random rd = new Random();

	return rd.nextInt(limit);
    }

    public static int getRandomPosx(pict canv, pict item, int ofs)
    {
	int left = item.width - ofs;
	int right = canv.width - ofs;

	int range = left + right;
	
	Random rd = new Random();

	int r = rd.nextInt(range);
	
	return r - left;
    } 

    public static int getRandomPosy(pict canv, pict item, int ofs)
    {
	int up = item.height - ofs;
	int bt = canv.height - ofs;

	int range = up + bt;
	
	Random rd = new Random();

	int r = rd.nextInt(range);
	
	return r - up;
    }

}
