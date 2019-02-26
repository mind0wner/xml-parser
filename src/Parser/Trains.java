package Parser;

import java.util.ArrayList;

public class Trains {
    private ArrayList<Train> listTrain = new ArrayList<>();
    public void addTrain(Train t){
        if(t==null){
            throw new IllegalArgumentException("Null");
        }
        listTrain.add(t);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Train t: listTrain){
            sb.append(t).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
