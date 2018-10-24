package Tests;

import beans.UserProfile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
//    public static void main(String[] args) throws IOException {
//        Map<String, UserProfile> userProfileMap = new HashMap<String, UserProfile>();
//
//        UserProfile userProfile = new UserProfile("a","b","c");
//        userProfileMap.put("a",userProfile);
//
//        FileOutputStream fileOut = new FileOutputStream("./userData/userProfile.ser");
//        ObjectOutputStream out = new ObjectOutputStream(fileOut);
//        out.writeObject(userProfileMap);
//        out.close();
//        fileOut.close();
//        System.out.printf("Serialized data is saved");
//    }

    public static void main(String [] args) {
        Map<String, UserProfile> userProfileMap = null;
        try {
            FileInputStream fileIn = new FileInputStream("./userData/userProfile.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userProfileMap = (Map<String, UserProfile>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Deserialized userProfileMap");
        System.out.println("Name: " + userProfileMap.get("a").twitterAccessToken);
    }
}