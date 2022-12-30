#----------------------------------------------------------------------------
#FILE: findKeyword.sh                                                        
#AUTHOR: Justin Liang(19821986)
#UNIT: COMP1000
#LAST MOD: 1/11/2021
#PURPOSE: find a "keyword" in the data directory and write to output.txt
#REQUIRES: ./data
#----------------------------------------------------------------------------

#checking no. of commandline arguments
if [ $# == 1 ]; then 
    #clear output.txt first
    if [ -e output.txt ]; then  
        rm output.txt
    fi 

    #recursively find files in data, grep keyword and write to output.txt
    find data -type f | while read file; do
        cat $file | grep -w $1 >> output.txt
    done
#print error message
else 
    echo "Invalid number of arguments" 
    echo "Usage: ./findKeyword.sh wordToFind"
fi
