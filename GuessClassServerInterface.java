import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GuessClassServerInterface extends Remote 
{
	public char getClassOfNetwork() throws RemoteException;
}
