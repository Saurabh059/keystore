package com.k12.keystore.services;

import com.day.cq.wcm.api.Page;
import com.k12.keystore.impl.filters.IAdd;
import com.k12.keystore.models.ChildListModel;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Saurabh on 5/26/2016.
 */
//@Component(label="my new label")
//@Service(IChildResource.class)
public class ChildResource  {
    public List<ChildListModel> getChildlist() {
        return childlist;
    }
    Resource resource;
public ChildResource(Resource resource)
{
    this.resource = resource;
    getName();

}
    List<ChildListModel> childlist;
    public void getName()
    {  childlist=new ArrayList<ChildListModel>();

        Logger logger = LoggerFactory.getLogger(Logger.class);
        System.out.print(resource.getPath());
        ValueMap valueMap = resource.adaptTo(ValueMap.class);
        String parentpath= valueMap.get("parentpath",String.class);
        if(parentpath!=null) {
            logger.info("parentpath"+parentpath);
            Resource path = resource.getResourceResolver().getResource(parentpath);
            Iterator<Resource> itr = path.listChildren();
            while (itr.hasNext()) {

                Resource resourceChildList = itr.next();
                if (resourceChildList.isResourceType("cq:Page")) {
                    Page page = resourceChildList.adaptTo(Page.class);
                    logger.info("child"+page.getPath());
                   childlist.add(new ChildListModel(page.getName(),page.getTitle()));
                   // System.out.print(childlist);

                }
            }
        }
        // String path = getPropertleies().get("pagePath","saurabh");
      //  List<ChildListModel> list=new ArrayList<ChildListModel>();
     //   Resource resource = getResourceResolver().getResource(s);
      //  Iterator <Resource> itr = resource.listChildren();
       /* while(itr.hasNext())
        {
            Resource  child= itr.next();

        }
*/
        //return "saurabh";
    }

}
