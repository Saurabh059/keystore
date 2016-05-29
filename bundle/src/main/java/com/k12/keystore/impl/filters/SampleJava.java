package com.k12.keystore.impl.filters;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

/**
 * Created by Saurabh on 5/2/2016.
 */
@Component(label="my service")
@Service(IAdd.class)
@Property(name = "service.label", value = "SampleJava")
public class SampleJava implements IAdd {

    public String add()
    {

        return "saurabh gupta";
    }

}
