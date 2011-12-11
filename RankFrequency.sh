#!/bin/bash
set -e # stop execution if there is an error

# compile RankRandomItems
javac -sourcepath java/src -d java/classes java/src/RankFrequency.java

#run it
userItemsPath=$1
randomItemsPath=$2
outputResultsPath=$3
java -Xmx2048m -classpath java/classes RankFrequency ${userItemsPath} ${randomItemsPath} #> ${outputResultsPath}
