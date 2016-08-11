import java.rmi.Naming;

public class GuessClassClient 
{
	public static void main(String[] args) throws Exception
	{
		if(args.length != 1)
		{
			System.out.println("You should enter a IP address from command line");
			System.exit(1);
		}
		GuessClassServerInterface myObj = (GuessClassServerInterface)(Naming.lookup("rmi://" + args[0] + "/RMIGuessClassObject"));
		System.out.println(myObj.getClassOfNetwork());
	}
}
