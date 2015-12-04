package edu.frescoplus.core.util;

public class HostConnection
{
    public long cantor_coordinate;
    public final long timeStarted;

    public HostConnection(int source,int destination,long timeStarted)
    {
        this.timeStarted = timeStarted;
        this.cantor_coordinate = cantor_pairing(source,destination);
    }

    public static long cantor_pairing(int x,int y)
    {
        // Not the most efficient representation (collisions possible)
        // See szudzic et al nks2006 for optimal implementation.
        return ( (x+y)*(x+y+1) )/2 + y;
        //return ((1/2)*(a+b)*(a+b+1)+b);// a little easier to read
    }

    // Inverse of cantor operation
    public static int getCantorX(int cantor_coordinate)
    {
        int j = (int) Math.floor(Math.sqrt(0.25 + 2 * cantor_coordinate) - 0.5);

        return j - getCantorY(cantor_coordinate);
    }

    public static int getCantorY(int cantor_coordinate)
    {
        int j = (int) Math.floor(Math.sqrt(0.25 + 2 * cantor_coordinate) - 0.5);

        return cantor_coordinate - j * (j + 1) / 2;
    }
}
