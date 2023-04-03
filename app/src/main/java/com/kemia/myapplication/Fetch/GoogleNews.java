package com.kemia.myapplication.Fetch;

import org.w3c.dom.Node;
import java.util.ArrayList;


public class GoogleNews {

    private String generator;
    private String title;
    private String link;
    private String language;
    private String webMaster;
    private String copyright;
    private String lastBuildDate;
    private String description;

    private ArrayList<GoogleNewsItem> items = new ArrayList<>();

    public GoogleNews(Node channelNode) {
        var children = channelNode.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            var node = children.item(i);
            var nodeName = node.getNodeName();
            if (nodeName.equals("generator")) {
                this.generator = node.getTextContent();
            }
            else if (nodeName.equals("title")) {
                this.title = node.getTextContent();
            }
            else if (nodeName.equals("link")) {
                this.link = node.getTextContent();
            }
            else if (nodeName.equals("language")) {
                this.language = node.getTextContent();
            }
            else if (nodeName.equals("webMaster")) {
                this.webMaster = node.getTextContent();
            }
            else if (nodeName.equals("copyright")) {
                this.copyright = node.getTextContent();
            }
            else if (nodeName.equals("lastBuildDate")) {
                this.lastBuildDate = node.getTextContent();
            }
            else if (nodeName.equals("description")) {
                this.description = node.getTextContent();
            }
            else if (nodeName.equals("item")) {
                GoogleNewsItem googleNewsItem = new GoogleNewsItem(node);
                items.add(googleNewsItem);
            }
        }
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWebMaster() {
        return webMaster;
    }

    public void setWebMaster(String webMaster) {
        this.webMaster = webMaster;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<GoogleNewsItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GoogleNewsItem> items) {
        this.items = items;
    }

}
