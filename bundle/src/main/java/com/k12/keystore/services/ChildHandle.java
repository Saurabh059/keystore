package com.k12.keystore.services;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.WCMMode;
import com.k12.keystore.models.ChildListModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Saurabh on 4/26/2016.
 */
public class ChildHandle  extends WCMUse {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void activate() {
        logger.info("FooterComponent Implementation");

    }
    public String getFun()throws Exception
    {
       // String path = getProperties().get("pagePath","saurabh");
        List<ChildListModel> list=new ArrayList<ChildListModel>();
        String path = getProperties().get("pagePath","saurabh");
        Resource resource = getResourceResolver().getResource(path);
        logger.info("...................................................."+resource.getPath());
        Iterator <Resource> itr = resource.listChildren();
        while(itr.hasNext())
        {
            Resource  child= itr.next();
            Node node= child.adaptTo(Node.class);
            if(!node.getName().equals("jcr:content"))
            {
              Node childnode= node.getNode("jcr:content");
                String path1= childnode.getProperty("jcr:title").toString();
                System.out.print("yyyyyyyyyyyyy");
            }
           // String pgpath = page.get("jcr:title","xxxx");
           // list.add(new ChildLisModel(page.getPath(),page.getPageTitle()));

        }

        return "saurabh";
    }
}
