package com.kemia.myapplication.Fetch;

import org.w3c.dom.Node;

public class GoogleNewsItem {
    private String title;
    private String link;
    private String guid;
    private String pubDate;
    private String description;
    private String source;


    public GoogleNewsItem(Node itemNode) {
        var children = itemNode.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            var node = children.item(i);
            var nodeName = node.getNodeName();
            if (nodeName.equals("title")) {
                this.title = node.getTextContent();
            }
            else if (nodeName.equals("link")) {
                this.link = node.getTextContent();
            }
            else if (nodeName.equals("guid")) {
                this.guid = node.getTextContent();
            }
            else if (nodeName.equals("pubDate")) {
                this.pubDate = node.getTextContent();
            }
            else if (nodeName.equals("description")) {
                this.description = node.getTextContent();
            }
            else if (nodeName.equals("source")) {
                this.source = node.getTextContent();
            }
        }
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
