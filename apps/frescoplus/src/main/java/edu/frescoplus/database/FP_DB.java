package edu.frescoplus.database;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by baykovr on 11/10/15.
 *
 * Need to support generic filter and selector to retrieve collection of data (based on filter).
 * A filter is a generic predicate function, which we will implement in the instantiation of the library.
 */
public class FP_DB
{
    public ArrayList<FP_DBEntry> data;

    public FP_DB()
    {
        data = new ArrayList<>();
    }

    public <T> Stream<T> select()
    {
        //return data.stream().filter( p -> System.out.println(p) );
        return null;
    }
}
