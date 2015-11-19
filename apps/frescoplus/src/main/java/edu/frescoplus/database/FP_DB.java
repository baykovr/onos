package edu.frescoplus.database;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by baykovr on 11/10/15.
 *
 * The fp database provides a high level interface between fp modules and the nos.
 *
 *
 * TODO
 * 1. DB Streams, packets,events and app data
 * 2. DB Stream Selectors/Filters (lambdas)
 *
 */
public class FP_DB
{
    public ArrayList<FP_DBEntry> data;

    public FP_DB()
    {
        data = new ArrayList<FP_DBEntry>();
    }

    public <T> Stream<T> select()
    {
        //return data.stream().filter( p -> System.out.println(p) );
        return null;
    }
}
