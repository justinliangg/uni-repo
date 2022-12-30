
echo "File: "
read file

if [ -e $file ]; then
    echo "File is: "
else
    echo "File does not exist"
fi

#checking if file is readable
if [ -r $file ]; then
    echo "Read "
fi

#checking if file is writable
if [ -w $file ]; then
    echo "Write " 
fi

#checking if file is executable
if [ -x $file ]; then
    echo "Executable "
fi


