#! /bin/sh

find -type f | while read file; do
    date -r $file | grep '21 Oct' 
done

    

