package edu.frescoplus.core.app.template;

import org.apache.commons.collections.ArrayStack;

import java.util.ArrayList;

public class T_Timer< T >
{
    long threshold;

    public ArrayList< TimedItem<T> > timedItems;

    public T_Timer(long threshold)
    {
        timedItems = new ArrayList<>();

        this.threshold = threshold;
    }

    public boolean violation(T t)
    {
        long currentTime = System.currentTimeMillis();

        // todo : lookup item
        return false;
    }

    public void addItem(T t)
    {
        this.timedItems.add( new TimedItem(t) );
    }

    public void rmItem(T t)
    {
        timedItems.remove(t);
    }
}
