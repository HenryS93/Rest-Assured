package cydeo.pojo;

import java.util.List;

public class Search {


    private List<Spartan> content;
    private int totalElement;



    public List<Spartan> getContent() {
        return content;

        //Search Class has a relation with Spartan Class
        //It is called as HAS A Relationship

    }

    public void setContent(List<Spartan> content) {
        this.content = content;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    @Override
    public String toString() {
        return "Search{" +
                "content=" + content +
                ", totalElement=" + totalElement +
                '}';
    }
}


