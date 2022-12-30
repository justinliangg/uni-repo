#! /bin/sh

for string in $*; do
    echo $string | rev
done
