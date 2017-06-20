package ru.incretio.juja.sqlcmd.service;

public class ServiceFactory {
    private Service service;

    public ServiceFactory(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }
}
