import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KlopodavkaServer extends Remote {

    public boolean SetGreenPlayer() throws RemoteException, NotBoundException, MalformedURLException;
    public boolean SetBluePlayer() throws RemoteException, NotBoundException, MalformedURLException;
    public boolean clickFromPlayer(int X, int Y, int Type) throws RemoteException;
    //Todo interface
}
