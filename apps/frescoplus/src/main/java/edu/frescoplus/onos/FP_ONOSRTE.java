/*
 * Copyright 2014 Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.frescoplus.onos;

import edu.frescoplus.module.*;
import edu.frescoplus.runtime.AFP_RTE;

import edu.frescoplus.runtime.FPM_Graph;
import org.apache.felix.scr.annotations.*;
import org.onlab.packet.Ethernet;
import org.onosproject.core.ApplicationId;
import org.onosproject.core.CoreService;
import org.onosproject.net.flow.*;
import org.onosproject.net.flowobjective.FlowObjectiveService;
import org.onosproject.net.packet.PacketContext;
import org.onosproject.net.packet.PacketPriority;
import org.onosproject.net.packet.PacketProcessor;
import org.onosproject.net.packet.PacketService;
import org.slf4j.LoggerFactory;


import java.util.HashMap;

@Component(immediate = true)
public class FP_ONOSRTE extends AFP_RTE {

    private ApplicationId appId;
    private static final int PRIORITY      = 128;
    private static final int DROP_PRIORITY = 129;
    private static final int TIMEOUT_SEC   = 60; // seconds

    //<editor-fold desc="Services">
    // Services
    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected CoreService coreService;

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected FlowObjectiveService flowObjectiveService;

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected FlowRuleService flowRuleService;

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected PacketService packetService;
    //</editor-fold>

    //<editor-fold desc="Network Processors">
    // Network Processors
    private PacketProcessor packetProcessor = new FP_PacketProcessor();
    private FlowRuleListener flowListener   = new FP_FlowListener();

    // Selector for ICMP traffic that is to be intercepted
    private final TrafficSelector intercept = DefaultTrafficSelector.builder()
            .matchEthType(Ethernet.TYPE_IPV4).build();
    //we can also select by ICMP
    //matchIPProtocol(IPv4.ICMP).build
    //</editor-fold>


    //private final PacketProcessor packetProcessor = new PingPacketProcessor();
    //private final FlowRuleListener flowListener = new InternalFlowListener();


    @Activate
    protected void activate()
    {
        appId = coreService.registerApplication("edu.frescoplug.onos.FP_ONOSRTE");
        packetService.addProcessor(packetProcessor, PRIORITY);
        flowRuleService.addListener(flowListener);

        // packet selector intercept
        packetService.requestPackets(intercept, PacketPriority.REACTIVE, appId);

        // Instantiate the generic function library with our ONOS function binding imlementation.
        library = new FP_LibONOS(LoggerFactory.getLogger( getClass() ));

        addStaticApp();

        library.log.info("Started");
    }

    @Deactivate
    protected void deactivate()
    {
        packetService.removeProcessor(packetProcessor);
        flowRuleService.removeFlowRulesById(appId);
        flowRuleService.removeListener(flowListener);
        library.log.info("Stopped");
    }

    //<editor-fold desc="FP Run Time Engine">
    // Run Time Engine
    @Override
    public void exec() {
        // Do custom operations (ONOS)
        library.log.info( library.db.data.get(0).data.toString() );

        // Execute AFP RTE Applications
        //super.exec();
    }

    public void addStaticApp()
    {
        //String name, String mainModule ,HashMap<String,AFP_Module modules
        HashMap<String,AFP_Module> modules = new HashMap<String,AFP_Module>();

        // This test app has a single module, next is null since this is the one and only module

        FPM_E_NewFlow getter  = new FPM_E_NewFlow("Get_Source_Host","Print_Source",super.library);

        FP_LoggingModule printer = new FP_LoggingModule("Print_Source","Blacklist_Check",super.library,
                getter.out_ports.get(0));

        FP_BlacklistModule blacklist = new FP_BlacklistModule("Blacklist_Check","Dropper",super.library,
                getter.out_ports.get(0),
                null );
        blacklist.addBlackItem("10.0.0.1");

        FP_DropperModule dropper = new FP_DropperModule("Dropper",null,super.library,
                blacklist.out_ports.get(0));

        modules.put(getter.getName(),getter);
        modules.put(printer.getName(),printer);
        modules.put(blacklist.getName(),blacklist);
        modules.put(dropper.getName(),dropper);

        FPM_Graph testApp = new FPM_Graph("static-app-test",getter.getName(),modules);

        super.fpApps.add(testApp);
    }
    //</editor-fold>
// ---------------------------------------------------------------------------------------------------------------------
    //<editor-fold desc="Listeners">
    private class FP_PacketProcessor implements PacketProcessor {
        @Override
        public void process(PacketContext context)
        {
            library.log.info("Got a packet!");
            exec();

            // traffic filter grantees IPv4
            //
            //Ethernet eth = context.inPacket().parsed(); // null if not ethernet
            //            if (isIcmpPing(eth)) {
            //                processPing(context, eth);
            //            }
        }
    }
    // Onos allows us to easily listen for flow rule events
    // flow removed etc...
    private class FP_FlowListener implements FlowRuleListener {
        @Override
        public void event(FlowRuleEvent event)
        {
//            FlowRule flowRule = event.subject();
//            if (event.type() == RULE_REMOVED && flowRule.appId() == appId.id()) {
//                Criterion criterion = flowRule.selector().getCriterion(ETH_SRC);
//                MacAddress src = ((EthCriterion) criterion).mac();
//                MacAddress dst = ((EthCriterion) criterion).mac();
//                log.warn("Re-enabled ping from {} to {} on {}", src, dst, flowRule.deviceId());
//            }
        }
    }
    //</editor-fold>
}
