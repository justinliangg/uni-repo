import java.util.*;

public class PondCalculator
{
	public static void main(String[] args)
		{
		
		Scanner sc = new Scanner(System.in);
		//initialising variables so it is still viable outside scope of do function
		int choice=0;

		int choiceAnimal=0;
			do 
			{
			do
			{
			System.out.println("Please select a person 1)Joey 2)Corey 3)Rachel");
			choice = sc.nextInt();

			if(choice == 1)
			{

			choiceAnimal = animalInput("String Rays", "Arowana");
			}
			else if (choice == 2)
			{
			choiceAnimal = animalInput("Koi","Puffer Fish");
			}
			else if (choice == 3)
			choiceAnimal = animalInput("Turtles" , "Frogs");
			
			else
			{
			System.out.println("Please Select numbers between 1 - 3") ;
			}

		 	}while (choice < 1 ||choice > 3);

			double width = dimensionsPond("width");
			double length = dimensionsPond("length");		
			double height = dimensionsPond("height");

			double pondDimensions = width*length*height;
			
			double animalPerM3 = animalsPerM3(choice,choiceAnimal); 
			//how many animals can fit into the pond 
		
			int  x =(int) (pondDimensions/animalPerM3);
		

			
		 	String animalName = animalName(choice,choiceAnimal);
			String personName = personName(choice);
			String gender = gender(choice);
			
			System.out.println(personName + " can store " + x + " " + animalName + " in " + gender + " " + pondDimensions + "m^3" + " pond.");
			}while(choice <=1 || choice >=3);	
		}
		       	
	//method for user input for type of animal
	public static int animalInput(String x , String y)
		{
		
		 int animalChoice = 0;
		 do
		 {
		 System.out.println("Please select an animal you would like to store 1)" +  x + " 2)" + y);
		 Scanner sc = new Scanner (System.in);


		 animalChoice= sc.nextInt();
		 }
		 while(animalChoice > 2 || animalChoice < 1);
		 return animalChoice;
		}

	//Method for user input for dimensions of pond
	public static double dimensionsPond(String x)
		{
		System.out.println("Please enter " + x);
		Scanner sc = new Scanner(System.in);

		double valueOfDimension = sc.nextDouble();
		return valueOfDimension;
		}

	//Method for deciding animals per m3
	
	public static double animalsPerM3(int x,int y)
		{
		
		if(x==1 && y==1)
		{
		return 0.5;
		}
		
		else if (x==1 && y==2)
		{
		return 0.4;
		}

		else if (x==2 && y==1)
		{
		return 0.6;
		}

		else if(x==2 && y==2)
		{
		return 0.8;
		}
		else if (x==3 && y==1)
		{
		return 1.2;
		}
		else 
		{
		return 4.5;
		}	
		
		}


	 public static String animalName(int x,int y)
                {

                if(x==1 && y==1)
                {
                return "Sting Rays";
                }

                else if (x==1 && y==2)
                {
                return "Arowana";
                }

                else if (x==2 && y==1)
                {
                return "Koi";
                }

                else if(x==2 && y==2)
                {
                return "Puffer fish";
                }
                else if (x==3 && y==1)
                {
                return "Turtles";
                }
                else
                {
                return "Frogs";
                }
		
               }


	
	public static String personName(int x)
		{
			if (x==1)
			{
				return "Joey";
			}
		
			else if (x==2)
			{
				return "Cory";
			}
			else
			{
				return "Rachel";

			}
		}


	public static String gender(int x)
		{
			if(x==1||x==3)
			{
				return "her";
			}
			else
			{
				return "his";
			}








		}






}

