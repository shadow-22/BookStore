package com.example;

import com.example.services.BookService;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestApplication extends Application {

    /*
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(BookService.class);
        classes.add(MoxyJsonFeature.class); // Register MOXy feature explicitly
        return classes;
    }
    */

    /*
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(BookService.class); // Register your BookService class
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new MOXyJsonProvider()); // Register MOXy JSON provider
        return singletons;
    }
    */

}
