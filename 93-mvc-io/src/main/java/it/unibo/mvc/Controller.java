package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    /**
     * @param string is the string we want to print next
     */
    void setNextStringToPrint(String string);

    /**
     * @return the string that is next in line to be printed
     */
    String getNextStringToPrint();

    /**
     * @return all the string that we printed
     */
    List<String> getPrintedStringHistory();

    /**
     * 
     */
    void printCurrentString();

}
