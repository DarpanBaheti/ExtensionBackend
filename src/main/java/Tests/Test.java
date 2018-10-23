package Tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
//        Gson gsonBuilder = new GsonBuilder().create();
//        // Convert Java Array into JSON
//        List languagesArrayList = new ArrayList();
//        languagesArrayList.add("Russian");
//        languagesArrayList.add("English");
//        languagesArrayList.add("French");
//
//        String jsonFromJavaArrayList = gsonBuilder.toJson(languagesArrayList);
//
//        System.out.println(jsonFromJavaArrayList);

        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20woeid%20from%20geo.places%20where%20text%3D%22(" + 18.975 + "," + 72.8258 + ")%22%20limit%201&diagnostics=false";

        Document yahooApiResponse = Jsoup.connect(url).timeout(10 * 1000).get();
        String xmlString = yahooApiResponse.html();
        Document doc = Jsoup.parse(xmlString, "", Parser.xmlParser());

        System.out.println(doc.select("woeid").first().text().toString());
    }
}