package com.kemia.myapplication.Fetch;

import java.util.function.Consumer;

public class GoogleNewsHandler {
    private String url;
    private Consumer<GoogleNews> handler;

    private GoogleNews googleNews = null;

    public GoogleNewsHandler(String url, Consumer<GoogleNews> handler) {
        this.url = url;
        this.handler = handler;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Consumer<GoogleNews> getHandler() {
        return handler;
    }

    public void setHandler(Consumer<GoogleNews> handler) {
        this.handler = handler;
    }

    public GoogleNews getGoogleNews() {
        return googleNews;
    }

    public void setGoogleNews(GoogleNews googleNews) {
        this.googleNews = googleNews;
    }
}
