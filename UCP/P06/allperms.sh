#! /bin/sh 

find -type f | while read file; do
    
    if [ -e $file ]; then 
        echo -n "$file: "
        
        #checking if file is readable
        if [ -r $file ]; then
            echo -n "Read "
        fi

        #checking if file is writable
        if [ -w $file ]; then
            echo -n "Write " 
        fi

        #checking if file is executable
        if [ -x $file ]; then
            echo -n "Executable "
        fi

        echo ""

    fi

done 
       

