# Naive Bayes Classification Program

This project relies on Apache POI to read excel xlsx files.  It can only
take xlsx files as input.  The required dependencies must be provided to
work.  Class files are already included to make running easier.

(if needed) compile with:
    javac (-cp Apache POI Libraries) Classify.java Data.java Frequencies.data LogProbability.java

run with:
    java (-cp Apache POI Libraries) Classify [file path][training num][testing num][-v (verbose, optional)]
