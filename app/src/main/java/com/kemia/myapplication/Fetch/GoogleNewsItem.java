package com.kemia.myapplication.Fetch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class GoogleNewsItem {
    private String title;
    private String link;
    private String guid;
    private String pubDate;
    private String description;
    private String source;

    private Bitmap imgBitMap;


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

        fetchImgUrl();
    }


    public void fetchImgUrl() {

        try {
//            URL url = new URL(link);
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.addRequestProperty("User-Agent", "Mozilla");
            conn.addRequestProperty("Referer", "google.com");
            var newUrl = conn.getInputStream();

            String text = new BufferedReader(
                    new InputStreamReader(newUrl, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            text = text.substring(text.indexOf("<a "));
            text = text.substring(text.indexOf("http"), text.indexOf(">"));
            text = text.substring(0, text.indexOf("\""));

            URL url2 = new URL(text);
            HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
            conn2.setReadTimeout(2000);
            conn2.addRequestProperty("User-Agent", "Mozilla");
            conn2.addRequestProperty("Referer", "google.com");
            var newUrl2 = conn2.getInputStream();

            String text2 = new BufferedReader(
                    new InputStreamReader(newUrl2, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            text2 = text2.substring(text2.indexOf("og:image"));
            text2 = text2.substring(text2.indexOf("http"));
            text2 = text2.substring(0, text2.indexOf("\""));

            InputStream in = new URL(text2).openStream();
            this.imgBitMap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            this.imgBitMap = null;
        }

    }

    public Bitmap getImgBitMap() {
        return imgBitMap;
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
