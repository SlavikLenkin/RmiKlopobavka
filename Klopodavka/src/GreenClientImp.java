import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GreenClientImp extends KlopodavkaClientImp {

    public static final String TAG = "GreenKlopodavkaClientImp";
    protected static final int port = 8776;

    private int myType = MyCell.GREEN;

    public GreenClientImp() throws RemoteException {
        super();
    }

    public void connect() throws RemoteException, NotBoundException, MalformedURLException {
        Registry registry = LocateRegistry.getRegistry(localhost, 8777);
        myServer = (KlopodavkaServer) registry.lookup(KlopodavkaServerImp.TAG);
        myServer.SetGreenPlayer();
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

    public static void main(String[] argv) throws RemoteException, NotBoundException, MalformedURLException {
        //Создание удаленного RMI Объекта.
        GreenClientImp mClient = new GreenClientImp();
        KlopodavkaClient stub = (KlopodavkaClient) UnicastRemoteObject.exportObject(mClient, port);
        System.out.println("Initializing " + TAG);

        //Регистрация удаленного RMI Объекта в реестре
        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind(TAG, stub);
        System.out.println("Starting " + TAG);
        mClient.connect();
    }
}
