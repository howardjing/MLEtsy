#!/bin/bash
set -e # stop execution if there is an error

# compile RankRandomItems
javac -sourcepath java/src -d java/classes java/src/RankFrequency.java
javac -sourcepath java/src -d java/classes java/src/RankFrequencyFiltered.java

# run it
# userItemsPath=$1
# randomItemsPath=$2
# outputResultsPath=$3
# java -Xmx2048m -classpath java/classes RankRandomItems ${userItemsPath} ${randomItemsPath} > ${outputResultsPath}

for i in {1..4}; do
    echo "processing ${i}..."
    for j in {1..4}; do
        if [ ${i} -ne ${j} ]; then
            #java -Xmx2048m -classpath java/classes RankFrequency data/splits/splits${i} data/splits/test${j} > output/results${i}${j}
            java -Xmx2048m -classpath java/classes RankFrequencyFiltered data/splits/splits${i} data/splits/test${j} > output/frequencies/filteredResults${i}${j}
        fi
    done
done