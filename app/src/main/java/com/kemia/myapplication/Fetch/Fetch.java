package com.kemia.myapplication.Fetch;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Fetch extends AsyncTask<GoogleNewsHandler, Integer, ArrayList<GoogleNewsHandler>>  {

    public static GoogleNews XMLParser(String content) {
        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(content));
            Document doc = documentBuilder.parse(is);
            Element root = doc.getDocumentElement();
            var channel = root.getChildNodes().item(0);
            return new GoogleNews(channel);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String search;

    public Fetch() {
        this.search = "";
    }

    public Fetch(String search) {
        this.search = search.trim();
    }

    public String GetAndCreateData() {
        try {
            URL url = new URL("https://news.google.com/rss?hl=vi&gl=VN&ceid=VN:vi");
            if (search.length() != 0) {
                url = new URL("https://news.google.com/rss/search?q="+this.search+"&hl=vi&gl=VN&ceid=VN:vi");
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/xml");

            BufferedReader br = null;
            if (100 <= conn.getResponseCode() && conn.getResponseCode() <= 399) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String content = "", line;
            while ((line = br.readLine()) != null) {
                content += line + "\n";
            }
            return content;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    protected ArrayList<GoogleNewsHandler> doInBackground(GoogleNewsHandler... googleNewsHandlers) {
        ArrayList<GoogleNewsHandler> list = new ArrayList<>();
        for (var ggNewsHandler: googleNewsHandlers) {
            var ggNews = XMLParser(GetAndCreateData());
            ggNewsHandler.setGoogleNews(ggNews);
            list.add(ggNewsHandler);
        }
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<GoogleNewsHandler> googleNewsHandlers) {
        super.onPostExecute(googleNewsHandlers);
        for (var ggNewHandler : googleNewsHandlers) {
            ggNewHandler.getHandler().accept(ggNewHandler.getGoogleNews());
        }
    }
}
