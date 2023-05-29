package practiceW11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Diary implements Cloneable {
    private Date[] listOfDates;

    public Diary(Date[] listOfDates) {
        this.listOfDates = listOfDates;
    }

    @Override
    public String toString() {
        return Arrays.toString(listOfDates);
    }

    // 1번
//    public Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    // 2번
    public Object clone() throws CloneNotSupportedException {
        Diary myObj = (Diary) super.clone();
        myObj.listOfDates = listOfDates.clone();
        for (int i=0; i<listOfDates.length; i++) {
            myObj.listOfDates[i] = (Date) listOfDates[i].clone();
        }
        return myObj;
    }

    public Date[] getListOfDates() {
        return listOfDates;
    }

    public void setListOfDates(Date[] listOfDates) {
        this.listOfDates = listOfDates;
    }
}
