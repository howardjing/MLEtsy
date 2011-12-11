#!/bin/bash
set -e # stop execution if there is an error

# compile RankRandomItems
javac -sourcepath java/src -d java/classes java/src/RankRandomItems.java
javac -sourcepath java/src -d java/classes java/src/RankRandomFilteredItems.java

# run it
# userItemsPath=$1
# randomItemsPath=$2
# outputResultsPath=$3
# java -Xmx2048m -classpath java/classes RankRandomItems ${userItemsPath} ${randomItemsPath} > ${outputResultsPath}

for i in {1..4}; do
    echo "processing ${i}..."
    for j in {1..4}; do
        if [ ${i} -ne ${j} ]; then
            java -Xmx3072m -classpath java/classes RankRandomItems data/splits/splits${i} data/splits/test${j} > output/results${i}${j}
            java -Xmx3072m -classpath java/classes RankRandomFilteredItems data/splits/splits${i} data/splits/test${j} > output/filteredResults${i}${j}
        fi
    done
done