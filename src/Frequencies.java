import java.util.Arrays;
/**Class that holds the number of occurances of each attribute at a given value,
 * coinsiding with the classification attribute a6 at a given value
 * 
 * @author Reed Nathaniel Schick
 */
public class Frequencies{
    int[][] v1 = new int[5][4]; //occurances of each attribute at any given value, coinsiding with A6 = 1
    int[][] v2 = new int[5][4]; //occurances of each attribute at any given value, coinsiding with A6 = 2
    int[][] v3 = new int[5][4]; //occurances of each attribute at any given value, coinsiding with A6 = 3

    /**This method takes one piece of data and records it's
     * data in their proper places in the array.  The array to
     * record the attribute values in is chosen from three,
     * based on the value of a6
     * 
     * @param e - Piece of data to be added to the set
     */
    public void fill(Data e){
        int[][] current = null; //will hold the proper array
        switch(e.getA6()){ // chose the right array
            case 1: {current = v1; break;}
            case 2: {current = v2; break;}
            case 3: {current = v3; break;}
            default:
        }
        //increment the array at the positions given by the data piece
        current[0][e.getA1()-1] ++;
        current[1][e.getA2()-1] ++;
        current[2][e.getA3()-1] ++;
        current[3][e.getA4()-1] ++;
        current[4][e.getA5()-1] ++;
    }

    /**This method returns the number of occurances of attribute A
     * at value val that coinside with a6 = 1
     * 
     * @param A - number indicating A1 - A5
     * @param val - the value that A is set to
     * @return an integer denoting the number of occurances, or -1 if the parameters are bad
     */
    public int freq_1(int A, int val){
        if(val < 1 || val > 4) {return -1;}
        switch(A) {
            case 1: return v1[0][val - 1];
            case 2: return v1[1][val - 1];
            case 3: return v1[2][val - 1];
            case 4: return v1[3][val - 1];
            case 5: return v1[4][val - 1];
            default: return -1;
        }
    }

    /**This method returns the number of occurances of attribute A
     * at value val that coinside with a6 = 2
     * 
     * @param A - number indicating A1 - A5
     * @param val - the value that A is set to
     * @return an integer denoting the number of occurances, or -1 if the parameters are bad
     */
    public int freq_2(int A, int val){
        if(val < 1 || val > 4) {return -1;}
        switch(A){
            case 1: return v2[0][val - 1];
            case 2: return v2[1][val - 1];
            case 3: return v2[2][val - 1];
            case 4: return v2[3][val - 1];
            case 5: return v2[4][val - 1];
            default: return -1;
        }
    }

    /**This method returns the number of occurances of attribute A
     * at value val that coinside with a6 = 3
     * 
     * @param A - number indicating A1 - A5
     * @param val - the value that A is set to
     * @return an integer denoting the number of occurances, or -1 if the parameters are bad
     */
    public int freq_3(int A, int val){
        if(val < 1 || val > 4) {return -1;}
        switch(A){
            case 1: return v3[0][val - 1];
            case 2: return v3[1][val - 1];
            case 3: return v3[2][val - 1];
            case 4: return v3[3][val - 1];
            case 5: return v3[4][val - 1];
            default: return -1;
        }
    }

    /**this method returns the number of occurances of a6 = 1
     * 
     * @return number of occurances of a6 = 1
     */
    public int freqA6_1(){
        int ret = 0;
        for(int i = 0; i < 4; i ++){
            ret += v1[0][i];
        }
        return ret;
    }

    /**this method returns the number of occurances of a6 = 1
     * 
     * @return number of occurances of a6 = 1
     */
    public int freqA6_2(){
        int ret = 0;
        for(int i = 0; i < 4; i ++){
            ret += v2[0][i];
        }
        return ret;
    }

    /**this method returns the number of occurances of a6 = 1
     * 
     * @return number of occurances of a6 = 1
     */
    public int freqA6_3(){
        int ret = 0;
        for(int i = 0; i < 4; i ++){
            ret += v3[0][i];
        }
        return ret;
    }

    /**Method that returns a String representation of a 2D array.
     * It simply adds each row one by one, seperated by new lines,
     * and using the Arrays.toString(array[])
     * 
     * @param current - the 2D array to make a string of
     * @return - a 2D representation of the array
     */
    private String arrayString(int[][] current){
        StringBuilder build = new StringBuilder();
        for(int i = 0; i < 5; i ++){
            build.append(Arrays.toString(current[i]) + "\n");
        }
        return build.toString();
    }

    /**this method returns a String representation of the Frequencies
     * object, which is just the three 2D arrays printed on after another
     * 
     * @return String representation of this object.
     */
    public String toString(){
        StringBuilder build = new StringBuilder();
        build.append(arrayString(v1) + "\n");
        build.append(arrayString(v2) + "\n");
        build.append(arrayString(v3) + "\n");
        return build.toString();
    }
}