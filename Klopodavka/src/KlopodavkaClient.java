import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KlopodavkaClient extends Remote {

    public MyField getField() throws RemoteException;

    public int getType() throws RemoteException;

    public void setTurn(boolean Turn) throws RemoteException;

    public void startPlay(boolean Turn) throws RemoteException;

    public boolean click(int x, int y) throws RemoteException;

    public boolean updateCell(int X, int Y, int Type) throws RemoteException;
    //TODO interface
}
