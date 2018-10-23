package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YoutubeWidget {
    List trendingVideos = new ArrayList();

    public void insertEntity(Map vidDetails) {
        trendingVideos.add(vidDetails);
    }

    public List getTrendingVideos() {
        return trendingVideos;
    }
}