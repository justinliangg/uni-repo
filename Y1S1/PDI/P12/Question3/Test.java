public class Test
{
    public static void main(String[] args)
    {   
        //Test Constructor
        Plant plantTest = new Plant("Mulga",623,1982,"Torchworth", "Bentley");
        Plant planTest2 = new Plant("Mulga",623,1982,"Lets", "Bentley");
        //Test Super method
        System.out.println(plantTest.getGardenName());
        System.out.println(planTest2.getGardenName());

        //Test FileIO class
        Plant [] plantData = FileIO.storeData("data.csv");
        String [] plantName = new String [plantData.length];
        for(int i = 0; i < plantData.length; i++)
        {
            plantName[i] = plantData[i].getName();
        }

        String x = String.join("\n", plantName);

        System.out.println(x);
    }

}
