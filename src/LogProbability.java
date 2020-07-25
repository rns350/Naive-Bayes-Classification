/**Class storing the logarithmic probabilities calculated from
 * a given set of frequencies.
 * 
 * @author Reed Nathaniel Schick
 */
public class LogProbability{
    double[][] v1 = new double[5][4]; //logarithmic probabilities for a1 = 6 and a1 - a5 = 1 - 4
    double[][] v2 = new double[5][4]; //logarithmic probabilities for a1 = 6 and a1 - a5 = 1 - 4
    double[][] v3 = new double[5][4]; //logarithmic probabilities for a1 = 6 and a1 - a5 = 1 - 4
    double probA6_1; //logarithmic probability that a6 = 1
    double probA6_2; //logarithmic probability that a6 = 2
    double probA6_3; //logarithmic probability taht a6 = 3

    /**Construtor that takes a Frequencies objects and calculates its logarithmic
     * probabilities at all values A6 = (1 - 3) and a1 - a5 = (1 - 4)
     * 
     * @param frequence - frequencies of occurances of ax and a6 = (1 - 3)
     * @param instances - total number of data pieces in frequence
     */
    public LogProbability(Frequencies frequence, int instances){
        double calculation; //variable for holding calculations

        //for each value (a1 - a5) = (1 - 4), calculate logarithmic
        //probabilities for the value coinsiding with a6 = (1 - 3)
        for(int i = 1; i <= 5; i ++){
            for(int j = 1; j <= 4; j ++){
                //a6 = 1
                calculation = frequence.freq_1(i, j) + 0.1;
                calculation /= (frequence.freqA6_1() + 0.4);
                v1[i-1][j-1] = -log2(calculation);
                //a6 = 2
                calculation = frequence.freq_2(i, j) + 0.1;
                calculation /= (frequence.freqA6_2() + 0.4);
                v2[i-1][j-1] = -log2(calculation);
                //a6 = 3
                calculation = frequence.freq_3(i, j) + 0.1;
                calculation /= (frequence.freqA6_3() + 0.4);
                v3[i-1][j-1] = -log2(calculation);
            }
        }
        
        //logarithmic probability of a6 = 1
        calculation = frequence.freqA6_1() + 0.1;
        calculation /= instances + 0.3;
        probA6_1 = -log2(calculation);
        //logarithmic probability of a6 = 2
        calculation = frequence.freqA6_2() + 0.1;
        calculation /= instances + 0.3;
        probA6_2 = -log2(calculation);
        //logarithmic probability of a6 = 3
        calculation = frequence.freqA6_3() + 0.1;
        calculation /= instances + 0.3;
        probA6_3 = -log2(calculation);
    }

    /**This method takes a piece of data and classifies its a6 attribute
     * based on the logarithmic probabilities that the class computed.
     * this is done by summing over the logarithmic probabilities for
     * a6 = (1 - 3) individually and taking the smallest one
     * 
     * @param A - piece of data to classify
     * @return the value of a6 that this data was classified as
     */
    public int classify(Data A){
        double sum1 = v1[0][A.getA1() - 1] + v1[1][A.getA2() - 1] +
                        v1[2][A.getA3() - 1] + v1[3][A.getA4() - 1] +
                        v1[4][A.getA5() - 1] + probA6_1;
        double sum2 = v2[0][A.getA1() - 1] + v2[1][A.getA2() - 1] +
                        v2[2][A.getA3() - 1] + v2[3][A.getA4() - 1] +
                        v2[4][A.getA5() - 1] + probA6_2;
        double sum3 = v3[0][A.getA1() - 1] + v3[1][A.getA2() - 1] +
                        v3[2][A.getA3() - 1] + v3[3][A.getA4() - 1] +
                        v3[4][A.getA5() - 1] + probA6_3;
        if(sum1 < sum2){
            if(sum1 < sum3){ return 1; }
            else{ return 3; }
        }
        if(sum2 < sum3){ return 2; }
        else {return 3; }
    }

    /**simple method for calculating log base 2 using java's built
     * in log function and the change in base theorem
     * 
     * @param x - the number to take log base 2 of
     * @return - log base 2 (x)
     */
    public static double log2(double x){
        return Math.log(x) / Math.log(2);
    }

    /**Method to return a String representation of a LogProbability object.
     * A string representation is simply the extra verbose output required
     * by the project.
     * 
     * @return a string representation of the object (verbose output)
     */
    public String toString(){
        StringBuilder build = new StringBuilder();
        build.append(String.format("%.4f  %.4f  %.4f\n\n", probA6_1, probA6_2, probA6_3));
        for(int i = 0; i < 5; i ++){
            build.append(String.format("%.4f  %.4f  %.4f  %.4f\n", v1[i][0], v1[i][1], v1[i][2], v1[i][3]));
            build.append(String.format("%.4f  %.4f  %.4f  %.4f\n", v2[i][0], v2[i][1], v2[i][2], v2[i][3]));
            build.append(String.format("%.4f  %.4f  %.4f  %.4f\n\n", v3[i][0], v3[i][1], v3[i][2], v3[i][3]));
        }
        return build.toString().substring(0, build.length() - 1);
    }
}