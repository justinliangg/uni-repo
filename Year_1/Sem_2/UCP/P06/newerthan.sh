#! /bin/sh

#getting first file
echo "File1: "
read file1

#getting second file
echo "File2: "
read file2

#checking if they are files first
if [ -e $file1 ] && [ -e $file2 ]; then
    echo "hi" 
    if [ $file1 -nt $file2 ]; then
        echo "$file1 is newer than $file2"
    else
        echo "$file1 is not newer than $file2"
    fi
fi


