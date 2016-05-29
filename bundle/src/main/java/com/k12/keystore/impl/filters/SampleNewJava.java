package com.k12.keystore.impl.filters;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

import java.awt.*;

/**
 * Created by Saurabh on 5/2/2016.
 */
@Component(label="my new label")
@Service(IAdd.class)
@Property(name = "service.label", value = "SampleNewjava")
public class SampleNewJava implements IAdd{

    public String add() {
        return "shivani";
    }
}
