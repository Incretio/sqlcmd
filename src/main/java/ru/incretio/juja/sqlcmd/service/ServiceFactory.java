package ru.incretio.juja.sqlcmd.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class ServiceFactory {
    @Lookup
    public abstract Service makeService();
}
