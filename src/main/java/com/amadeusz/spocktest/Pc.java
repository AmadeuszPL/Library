package com.amadeusz.spocktest;

class Pc {

    private String vendor;
    private int clockRate;
    private int ram;
    private String os;

    public Pc() {
    }

    public Pc(String vendor, int clockRate, int ram, String os) {
        this.vendor = vendor;
        this.clockRate = clockRate;
        this.ram = ram;
        this.os = os;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getClockRate() {
        return clockRate;
    }

    public void setClockRate(int clockRate) {
        this.clockRate = clockRate;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

}
