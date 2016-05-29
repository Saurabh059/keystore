package com.k12.keystore.impl.filters;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Saurabh on 5/2/2016.
 */
@Component(label = "My new new servlet", metatype = true)
@SlingServlet(paths="/bin/newservlet",
               methods = "GET",
      selectors = "p1",
generateComponent = false)
public class NewSampleServlet extends SlingSafeMethodsServlet {
    @Reference(target="(service.label=SampleJava)")
    IAdd ad;
    @Override

    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("shivani");
    }
}

