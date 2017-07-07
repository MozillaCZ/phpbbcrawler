#!/usr/bin/env bash

var=0;
while read url; do
    var=$((var+1))
    echo "$var: https://web.archive.org/save/$url"
    curl -L --output /dev/null "https://web.archive.org/save/$url"
done < "$1"
