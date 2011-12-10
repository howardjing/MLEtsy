#!/bin/bash
set -e # stop execution if there is an error

# compile RankRandomItems
javac -sourcepath java/src -d java/classes java/src/RankRandomItems.java

# run it
# userItemsPath=$1
# randomItemsPath=$2
# outputResultsPath=$3
# java -Xmx2048m -classpath java/classes RankRandomItems ${userItemsPath} ${randomItemsPath} > ${outputResultsPath}

for i in {0..9}; do
    echo "processing ${i}..."
    java -Xmx3072m -classpath java/classes RankRandomItems data/trainingnot${i}.txt data/test${i}.txt > output/results${i}.txt
done