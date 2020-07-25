/**Class to represent a piece of data from the data set.
 * This class contains the 6 attributes for a data set
 * that are used for this particular classification algorithm.
 * 
 * @author Reed Schick
 */
public class Data{
    private int a1, a2, a3, a4, a5, a6; //data attributes

    /**make a new piece of data.  First check to make sure that each piece of
     * data is within the valid range.
     * 
     * @param a1 - attribute 1
     * @param a2 - attribute 2
     * @param a3 - attribute 3
     * @param a4 - attribute 4
     * @param a5 - attribute 5
     * @param a6 - attribute 6
     */
    public Data(int a1, int a2, int a3, int a4, int a5, int a6){
        if(!validInput(a1, a2, a3, a4, a5, a6)){ throw new IllegalArgumentException("a1 - a5 must be between 1 and 4.\n"
                                                                                    + "a6 must be between 1 and 3.");}
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.a5 = a5;
        this.a6 = a6;
    }

    /**Method to check that the input for a piece of data is valid.
     * al - a5 must be within 1 and 4, while a6 must be within
     * 1 and 3.
     * 
     * @param a1 - attribute 1
     * @param a2 - attribute 2
     * @param a3 - attribute 3
     * @param a4 - attribute 4
     * @param a5 - attribute 5
     * @param a6 - attribute 6
     * @return true if the input is valid, false otherwise.
     */
    private boolean validInput(int a1, int a2, int a3, int a4, int a5, int a6){
        if(a1 < 1 || a1 > 4){ return false; }
        if(a2 < 1 || a2 > 4){ return false; }
        if(a3 < 1 || a3 > 4){ return false; }
        if(a4 < 1 || a4 > 4){ return false; }
        if(a5 < 1 || a5 > 4){ return false; }
        if(a6 < 1 || a6 > 3){ return false; }
        return true;
    }

    /**getters for the various attributes
     * 
     * @return given attribute
     */
    public int getA1(){ return a1; }
    public int getA2(){ return a2; }
    public int getA3(){ return a3; }
    public int getA4(){ return a4; }
    public int getA5(){ return a5; }
    public int getA6(){ return a6; }

    /**returns a string representation of the piece of data.
     * This string contains all of the attributes, seperated
     * by spaces.
     * 
     * @return String representation of this object
     */
    public String toString(){
        return a1 + " " + a2 + " " + a3 + " " +
                a4 + " " + a5 + " " + a6;
    }
}