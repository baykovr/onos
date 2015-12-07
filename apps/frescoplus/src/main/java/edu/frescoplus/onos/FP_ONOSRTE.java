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

import edu.frescoplus.core.app.fresco.FrescoApp;
import edu.frescoplus.core.event.FP_Event;
import edu.frescoplus.core.models.FP_StaticPriorityModel;
import edu.frescoplus.core.runtime.FP_RTE;

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

@Component(immediate = true)
public class FP_ONOSRTE {

    private ApplicationId appId;

    private static final int PRIORITY      = 128; //
    private static final int DROP_PRIORITY = 129; //

    // Flow rule timeout in seconds
    private static final int TIMEOUT_SEC   = 60;

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

    // Subscribe to IPv4
    private final TrafficSelector intercept = DefaultTrafficSelector.builder()
            .matchEthType(Ethernet.TYPE_IPV4).build();
    //we can also select by ICMP or TCP, etc etc.
    //matchIPProtocol(IPv4.ICMP).build

    //</editor-fold>

    // Fresco plus run time engine.
    // We specify a static priority model for application execution
    // and a function library for onos.
    private FP_RTE<FP_StaticPriorityModel,FP_libONOS> fpEngine;

    @Activate
    protected void activate()
    {
        appId = coreService.registerApplication("edu.frescoplug.onos.FP_ONOSRTE");
        packetService.addProcessor(packetProcessor, PRIORITY);
        flowRuleService.addListener(flowListener);

        // packet selector intercept
        packetService.requestPackets(intercept, PacketPriority.REACTIVE, appId);

        // Two arguments, new model() and library(logger)
        fpEngine = new FP_RTE<>(
                new FP_StaticPriorityModel(),
                new FP_libONOS(
                        LoggerFactory.getLogger(getClass())
                )
        );

        // add an application for testing.
        addTestApplications();

        fpEngine.library.log.info("Started");
    }

    @Deactivate
    protected void deactivate()
    {
        packetService.removeProcessor(packetProcessor);
        flowRuleService.removeFlowRulesById(appId);
        flowRuleService.removeListener(flowListener);
        fpEngine.library.log.info("Stopped");
    }


    public void addTestApplications()
    {
        FrescoApp frescoTestApp = new FrescoApp("hello-world-fre",fpEngine.library);

        fpEngine.addApp(frescoTestApp);
    }

    //<editor-fold desc="Listeners">
    private class FP_PacketProcessor implements PacketProcessor {
        @Override
        public void process(PacketContext context)
        {
            fpEngine.library.log.info("Got a packet!");

            fpEngine.library.setContext(appId,context,flowObjectiveService,flowRuleService);

            // A new event has occured.
            fpEngine.exec(FP_Event.PACKET); // exec RTE

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
