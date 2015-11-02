package edu.frescoplus.onos;

import edu.frescoplus.generic.IFP_Generic;

/**
 * Created by baykovr on 10/27/15.
 */
public class FpLibONOS implements IFP_Generic {

    @Override
    public boolean isPacketIN() {
        return false;
    }

    @Override
    public boolean isIPv4() {
        return false;
    }

    @Override
    public boolean isICMP() {
        return false;
    }

    @Override
    public boolean isTCP() {
        return false;
    }

    @Override
    public boolean isUDP() {
        return false;
    }

    @Override
    public <T> T getSrcHostIP() {
        return null;
    }

    @Override
    public <T> T getDstHostIP() {
        return null;
    }

    @Override
    public void getDstPort() {

    }

    @Override
    public void getSrcPort() {

    }

    @Override
    public <T> void print(T data) {

    }

    @Override
    public <T> void logModuleError(T error) {

    }

    @Override
    public void blockPacket() {

    }
}
