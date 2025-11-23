package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> printHistory = new LinkedList<>();
    private String nextString;

    @Override
    public void setNextStringToPrint(final String nextStringToPrint) {
        Objects.requireNonNull(nextStringToPrint, "This method doesn't accept null values");
        this.nextString = nextStringToPrint;
    }

    @Override
    public String getNextStringToPrint() {
        return this.nextString;
    } 

    @Override
    public List<String> getPrintedStringHistory() {
        return List.copyOf(printHistory);
    }

    @Override
    public void printCurrentString() {
        if (nextString == null) {
            throw new IllegalStateException("No strings have been set");
        } else {
            printHistory.add(nextString);
            System.out.println(nextString); //NOPMD
        }
    }

}
