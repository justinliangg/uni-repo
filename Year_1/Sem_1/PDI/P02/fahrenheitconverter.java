import java.util.*;
public class fahrenheitconverter
{ 
public static void main(String[] args)
{

Scanner sc = new Scanner(System.in);

//Variables
double celcius;
double fahrenheit;

//

System.out.print("Please enter in Temperature in Celcius ");
celcius = sc.nextDouble();
fahrenheit = 9.0/5.0 * celcius + 32.0;

System.out.println("Fahrenheit= " + fahrenheit);

sc.close();

}
}



