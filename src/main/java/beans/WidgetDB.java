package beans;

import java.util.ArrayList;
import java.util.List;

public class WidgetDB {
    private List<String> widgetArrayList = new ArrayList<>();

    public WidgetDB(){
        widgetArrayList.add("Reddit");
        widgetArrayList.add("Twitter");
        widgetArrayList.add("Youtube");
        widgetArrayList.add("SampleWidget");
    }

    public List getWidgetArrayList() {
        return widgetArrayList;
    }
}