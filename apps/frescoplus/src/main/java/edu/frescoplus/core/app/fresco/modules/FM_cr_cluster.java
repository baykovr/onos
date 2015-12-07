package edu.frescoplus.core.app.fresco.modules;

import edu.frescoplus.core.lib.AFP_Library;

import java.util.ArrayList;

/*
*
* The cross cluster correlates the c and a plane clustering in order to identify malicious hosts (bots)
* The input is a set of hosts and their botnet score from a and c planes.
* apply bellow threshold to filter and then cluster for reinforced detection.
* High score if hosts performs multiple types of a plane activity and if
* other c plane hosts (related) have high scores.
* */
public class FM_cr_cluster<T> extends AFresco_Module {

    public FM_cr_cluster(String name, AFP_Library library,
                         ArrayList<T> leftSet, ArrayList<T> rightSet)
    {
        super(name,library);
    }

    // Apply k means to the two sets.
    public void clusterStatistics()
    {

    }

    @Override
    public void run() {

    }
}
