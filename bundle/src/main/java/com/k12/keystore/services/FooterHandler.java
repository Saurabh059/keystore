package com.k12.keystore.services;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import java.util.*;


public class FooterHandler extends WCMUse {
    /*
 The name of the property to indicate that the page should hide from navigation
  */
    private final static String HIDE_FROM_FOOTER = "hideInFooter";
    /**
     * The Path of the Root Page, taken by default in case of automatic
     * selection.
     */
    private final static String DEFAULT_ROOT_PATH = "/content/keystore/en";
    /** The logger. */
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Map<String, Object>> getFooterListofMap() {
        final ResourceResolver resourceResolver = getResourceResolver();
        final Resource styleResource = getResource();
        final List<Map<String, Object>> listOfPages = new ArrayList<Map<String, Object>>();
        Resource resource;
        if (styleResource == null) {
            resource = getDefaultResource(resourceResolver);
        } else {
            final ValueMap valueMap = styleResource.adaptTo(ValueMap.class);
            final String listFrom = valueMap.get("listFrom", String.class);

            if ("manual".equals(listFrom)) {
                addCustomPageList(resourceResolver, listOfPages);
            } else { // for "automatic"
                final String parentPath = valueMap.get("parentPath", String.class);
                resource = resourceResolver.getResource(parentPath);
                if (resource == null) {
                    resource = getDefaultResource(resourceResolver);
                }
                final Page rootPage = resource.adaptTo(Page.class);
                final Iterator<Page> pages = subChildren(rootPage);
                while (pages.hasNext()) {
                    final Page page = pages.next();
                    addPageDetails(subChildren(page), page.getTitle(), listOfPages);
                }
            }
        }
        return listOfPages;
    }

    /**
     * Adds the page details.
     *
     * @param subChildsItr the {@link Iterator} of the List of Sub-Children
     * @param title {@link String} the Title of the Parent to be displayed
     * @param listOfPages the {@link List} of All pages and their info
     */
    private void addPageDetails(final Iterator<Page> subChildsItr, final String title,
                                final List<Map<String, Object>> listOfPages) {
        final Map<String, Object> parentMap = new HashMap<String, Object>();
        parentMap.put("Iterator", subChildsItr);
        parentMap.put("Page", title);
        listOfPages.add(parentMap);
    }

    /**
     * Gets the default resource.
     *
     * @param resourceResolver {@link ResourceResolver}
     * @return the default resource
     */
    private Resource getDefaultResource(final ResourceResolver resourceResolver) {
        return resourceResolver.getResource(DEFAULT_ROOT_PATH);
    }

    /**
     * Fetches JSON from Repository containing custom list information and
     * updates a list of Map of required Pages' Info(Title and Child/Leaf Pages
     * of given 'rootpath').
     *
     * @param resourceResolver {@link ResourceResolver}
     * @param listOfPages {@link List} the list of pages
     */
    private void
    addCustomPageList(final ResourceResolver resourceResolver,
                                   final List<Map<String, Object>> listOfPages ) {
        final String[] multiField = getProperties().get("footerArray", String[].class);
        if (multiField == null)
            return;
        try {
            for (final String field : multiField) {
                final JSONObject json = new JSONObject(field);
                final String title = (String) json.get("title");
                final String rootPath = (String) json.get("rootpath");
                final Resource resource = resourceResolver.getResource(rootPath);
                if (resource != null) {
                    final Page rootPage = resource.adaptTo(Page.class);
                    if (rootPage != null) {

                        addPageDetails(subChildren(rootPage), title, listOfPages);

                    }
                }
            }
        } catch (final JSONException e) {
            e.printStackTrace();
        }
    }
    /*
    Filters the page that should hide from navigation
     */
    private Iterator<Page> subChildren(Page page) {
        Iterator<Page> itr = page.listChildren();
        List<Page> list = new ArrayList<Page>();
        while (itr.hasNext()) {
            Page subchild = itr.next();
            Node node = subchild.adaptTo(Node.class);
            try {
                if (!node.getNode("jcr:content").hasProperty(HIDE_FROM_FOOTER)) {
                    list.add(subchild);
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return list.iterator();
    }



    @Override
    public void activate() throws Exception {
        final Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("FooterComponent Implementation");
    }
}
