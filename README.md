# Naive Bayes Classification Program

## What Does This Program do

This program is designed to use naive bayes on a large data set in order to classify an attribute into 1 of 3 categories.  The data set provided is an Excel file with 1000 rows and 6 columns.  The first five columns contain the predictive attributes, a1 - a5.  They can take values between 1 and four.  The sixth attribute is the one that this program tries to learn how to predict, and can take values 1 - 3.

The data set is constructed so that Naive Bayes should predict attribute 6 much better than by pure chance, but not perfectly either.

The program takes 4 command line arguments.  First, the path to the data set.  Second, the number of rows to use for training.  Third, the number of rows used for testing.  Fourth, `-v` can be included for verbose output.  The rows used for training are taken from the front of the data set and the rows used for testing are taken from the back.  The combined number of rows cannot exceed the number in the file (1000).

The program will output the overall accuracy on the test set.  It will also output the precision and recall for the specific category `X.a6 = 3` in the test set.

If verbose is specified, then the values of the negative log probabilities for each individual pair `(X.a(y) = z | X.a6 = q)` will be output to terminal as well.  Namely, the output will be displayed in a grid like format, where each grid element contains 3 numbers for each possible value of `a6`.  The row number represents the value `y`, and the column number represents the value `z`.  So, for example, `grid(1,2)` contains:

```
lp(X.a1 = 2|X.a6 = 1)  
lp(X.a1 = 2|X.a6 = 2)  
lp(X.a1 = 2|X.a6 = 3)
```

## How to run

This project relies on Apache POI to read excel xlsx files.  It can only
take xlsx files as input.  The required dependencies must be provided to
work.  Class files are already included to make running easier.

(if needed) compile with:
   
`javac (-cp Apache POI Libraries) Classify.java Data.java Frequencies.data LogProbability.java`

run with:

`java (-cp Apache POI Libraries) Classify [file path][training num][testing num][-v (verbose, optional)]`
