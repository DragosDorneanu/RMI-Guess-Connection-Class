import java.rmi.RemoteException;
import java.io.IOException;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.io.ByteArrayInputStream;

public class GuessClassRMIObject extends UnicastRemoteObject implements GuessClassServerInterface
{
	public static final long serialVersionUID = 0L;
	
	public GuessClassRMIObject() throws RemoteException {}
	
	@Override
	public char getClassOfNetwork() throws RemoteException 
	{
		InetAddress localHost = null;
		try{
			localHost = InetAddress.getLocalHost();
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
			System.exit(1);
		}
		ByteArrayInputStream baos = new ByteArrayInputStream(localHost.getAddress());
		int ip = baos.read();
		if((ip & 1) == 0)
			return 'A';
		if((ip & (1 << 1)) == 1 && (ip & 1) == 0)
			return 'B';
		if((ip & (1 << 2)) == 1 && (ip & (1 << 1)) == 1 && (ip & 1) == 0)
			return 'C';
		if((ip & (1 << 3)) == 1 && (ip & (1 << 2)) == 1 && (ip & (1 << 1)) == 1 && (ip & 1) == 0)
			return 'D';
		if((ip & (1 << 4)) == 1 && (ip & (1 << 3)) == 1 && (ip & (1 << 2)) == 1 && (ip & (1 << 1)) == 1 && (ip & 1) == 0)
			return 'E';
		return 'X';
	}
	
	public static void main(String[] args) throws Exception 
	{
		GuessClassRMIObject obj = new GuessClassRMIObject();
		Naming.bind("RMIGuessClassObject", obj);
	}
}