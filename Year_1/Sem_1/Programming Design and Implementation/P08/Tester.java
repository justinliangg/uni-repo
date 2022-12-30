public class Tester
{
    public static void main(String[]args)
    {
        String s = "asfasdfa"+ "," + "," + "dsfasdfas";
        String [] array = s.split(",");
        if(array[2] != null)
        {
            System.out.println("true");
        }

    } 
   }
