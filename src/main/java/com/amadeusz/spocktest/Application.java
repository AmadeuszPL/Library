package com.amadeusz.spocktest;

class Application {

    Service service;

    public Application(Service service) {
        this.service = service;
    }

    public void run() {

        service.start();
        service.doWork();
        service.stop();

    }

}
