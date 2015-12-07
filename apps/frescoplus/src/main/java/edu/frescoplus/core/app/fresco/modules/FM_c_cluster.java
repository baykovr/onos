package edu.frescoplus.core.app.fresco.modules;

import edu.frescoplus.core.lib.AFP_Library;

/**
 * C Plane, who is communicating with whom (TCP and UDP flows)
 * time, duration, src/dst IP,PORT and #packets & #bytes
 * Original Fresco applies a limited correlation of only packets and bytes.
 *
 * ! One problem is lack of performance/fidelity tradeoff in sdn.
 * 1. Time and duration is not stored in the flow table.
 * 2. Use of wildcards masks fidelity information from us.
 *
 * Create two clusters by (kmeans,etc) from (two feature) data set where features are (byte_count,packet_count)
 * The input is the entire flow table, the output is two clusters of ip addresses, we expect one cluster to be very small
 * this is the malicious case, the other cluster is benign (normal hosts).
 * We return the two clusters for cross clustering analysis (uses a plane and c plane to find malicious)
 */
public class FM_c_cluster extends AFresco_Module
{
    int src,dst;
    public FM_c_cluster(String name, AFP_Library library,
                        int src, int dst)
    {
        super(name, library);
        this.src = src;
        this.dst = dst;
    }

    public void clusterStatistics()
    {
        // Requires k-means or other clustering algorithm.

    }
    public void extractFlowStatistics()
    {
        // #packets & #bytes between hosts.
        // ask lib for statistics information from flow table.

    }
    @Override
    public void run() {

    }
}
