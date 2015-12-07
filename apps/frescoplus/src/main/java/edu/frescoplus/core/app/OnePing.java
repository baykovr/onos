package edu.frescoplus.core.app;

import edu.frescoplus.core.app.template.T_Timer;
import edu.frescoplus.core.event.FP_Event;
import edu.frescoplus.core.lib.AFP_Library;

public class OnePing extends AFP_App<AFP_Library,FP_Event>
{
    T_Timer< AFP_Library.fMac > timer;

    public OnePing(String name, AFP_Library library) {
        super(name, library);

        timer = new T_Timer<>(30);

        subscribedEvents.add(FP_Event.PACKET);
    }

    @Override
    public void run() {
        long curTime = System.currentTimeMillis();

        int ip = this.library.getDstIP();

        AFP_Library.fMac mac = this.library.getMAC();

        if ( timer.timedItems.contains(mac) )
        {
            if(timer.violation(mac))
            {
                this.library.DROP();
            }
        }
        else
        {
            timer.addItem(mac);
        }
    }
}
