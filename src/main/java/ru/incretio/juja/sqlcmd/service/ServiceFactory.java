package ru.incretio.juja.sqlcmd.service;

import org.springframework.stereotype.Component;

@Component
public abstract class ServiceFactory {

    public abstract Service makeService();
}
