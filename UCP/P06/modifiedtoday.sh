./identical.sh: not found
#! /bin/sh

find -type f | while read file; do
    "$(date -r $file)" | grep -E -R '01 Oct' 
done

    

