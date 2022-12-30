public class Main
{
    public static void main(String[] args)
    {
        DSAHashTable hashTable = new DSAHashTable(7000);
        
        //Reading in file and storing values inside hashTable
        FileIO.fileRead("RandomNames7000.csv",hashTable);
        Object[][] array = hashTable.export();

        FileIO.writeToFile(array);
    }
}
