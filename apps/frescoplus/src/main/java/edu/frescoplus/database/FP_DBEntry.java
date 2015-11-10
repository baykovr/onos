package edu.frescoplus.database;

import java.util.function.Predicate;

/**
 * Created by baykovr on 11/10/15.
 */
public class FP_DBEntry<K>
{
    public K identifier;
    public Object data;

    public FP_DBEntry(K identifier,Object data)
    {
        this.identifier = identifier;
        this.data = data;
    }
}
