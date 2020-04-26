import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BlueClientImp extends KlopodavkaClientImp {

    public static final String TAG = "BlueKlopodavkaClientImp";
    protected static final int port = 8775;

    private int myType = MyCell.BLUE;

    public BlueClientImp() throws RemoteException {
        super();
    }

    public void connect() throws RemoteException, NotBoundException, MalformedURLException {
        Registry registry = LocateRegistry.getRegistry(localhost, 8777);
        myServer = (KlopodavkaServer) registry.lookup(KlopodavkaServerImp.TAG);
        myServer.SetBluePlayer();
    }

    @Override
    public boolean click(int x, int y) throws RemoteException {
        if(!myTurn) return false;
        return myServer.clickFromPlayer(x, y, myType);
    }

    @Override
    public int getType() throws RemoteException {
        return myType;
    }

    public static void main(String[] argv) throws RemoteException, NotBoundException, MalformedURLException{
        //Создание удаленного RMI Объекта.
        BlueClientImp mClient = new BlueClientImp();
        KlopodavkaClient stub = (KlopodavkaClient) UnicastRemoteObject.exportObject(mClient, port);
        System.out.println("Initializing " + TAG);

        //Регистрация удаленного RMI Объекта в реестре
        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind(TAG, stub);
        System.out.println("Starting " + TAG);
        mClient.connect();
    }
}
