package edu.frescoplus.core.app.template;


public class TimedItem<T>
{
    public T t;
    public long time;

    public TimedItem(T t)
    {
        this.t = t;
        this.time = System.currentTimeMillis();
    }
}