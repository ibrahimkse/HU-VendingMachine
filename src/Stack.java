import java.util.ArrayList;

public class Stack <T> extends ArrayList<T> {
    public String id;
    public ArrayList<T> stackArrayList;

    public Stack(String id){
        this.id = id;
        this.stackArrayList = new ArrayList<T>();
    }

    public void push(T object){
        stackArrayList.add(object);
    }

    public void pop(){
        stackArrayList.remove((stackArrayList.size()-1));
    }

}


