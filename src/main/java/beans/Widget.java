package beans;

public class Widget {
    private String entity;

    public Widget(String entity) {
        this.entity = entity;
    }

    public String returnContent(){
        return this.entity;
    }
}