package com.kemia.myapplication.Fetch;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Fetch extends AsyncTask<GoogleNewsHandler, Integer, ArrayList<GoogleNewsHandler>>  {

    public static GoogleNews JsonParser(String content) {
        try {
            JSONObject json = new JSONObject(content);//biến chữ/dữ liệu vừa lấy thành dạng json
            JSONArray json2 = json.getJSONArray("articles");//lấy thuộc tính articles trong json đó(nó là mảng)
            return new GoogleNews(json2);//truyền cái mảng json artical đó vào ggnew để xử lý
        } catch (JSONException e) {
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
            URL url = new URL("https://newsapi.org/v2/top-headlines?country=us&apiKey=0ffdd425a4cb4a3bb8920d5f23459f7f");//lấy báo mới nhất từ api của new api
            if (search.length() != 0) {
                url = new URL("https://newsapi.org/v2/everything?q="+this.search+"&apiKey=0ffdd425a4cb4a3bb8920d5f23459f7f");//nếu tìm kiếm trên thanh tìm kiếm nó sẽ tìm theo thanh tìm kiếm
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();// mở cổng kết nối với địa chỉ 2 trên
            conn.setRequestProperty("Accept", "application/json");//nhận giá trị trả về chỉ nhận dạng theo dạng jason
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");// để new api biết nó từ trình duyệt gì
            conn.setRequestMethod("GET");//gửi thông tin đó dưới dạng get (dạng lấy dữ liệu) (post là dạng đăng dữ liệu lên trang wed)
            BufferedReader br = null;
            if (100 <= conn.getResponseCode() && conn.getResponseCode() <= 399) {//check mã trả về đúng không
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));// nếu đúng thì nó sẽ đọc từ InputStream
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));// kh thì đọc dòng dữ liệu của lỗi
            }
            String content = "", line;
            while ((line = br.readLine()) != null) {
                content += line + "\n";//cho từng các dòng trong BufferedReader thì nó sẽ thêm vô trong cái chuỗi content
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
            var ggNews = JsonParser(GetAndCreateData());
            ggNewsHandler.setGoogleNews(ggNews);
            list.add(ggNewsHandler);
        }//chạy tác vụ nền
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<GoogleNewsHandler> googleNewsHandlers) {
        super.onPostExecute(googleNewsHandlers);
        try {
            for (var ggNewHandler : googleNewsHandlers) {
                ggNewHandler.getHandler().accept(ggNewHandler.getGoogleNews());
            }
        }// cập nhật dữ liệu lên màn hình
        catch (Exception ignored){

        }
    }
}
