#! /bin/sh

for file in $*; do
    
    for file2 in $*; do
        
        if [ $file != $file2 ] && diff $file $file2 > /dev/null ; then
            echo "$file same as $file2"
        fi
    done
done
