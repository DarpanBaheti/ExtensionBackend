package beans;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TwitterWidget {
    private List<String> trendingTopics = new ArrayList<String>();

    public void insertEntity(String trend){
        trendingTopics.add(trend);
    }

    public List getTrendingTopics() {
        return trendingTopics;
    }
}