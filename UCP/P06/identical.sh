#! /bin/sh

find -type f | while read file; do

    find -type f | while read file2; do
        
        if [ $file != $file2 ] && diff $file $file2 > /dev/null ; then
            echo "$file same as $file2"
        fi
    done
done

