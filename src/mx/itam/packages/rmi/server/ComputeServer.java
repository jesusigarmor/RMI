package mx.itam.packages.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import mx.itam.packages.rmi.interfaces.Compute;

public class ComputeServer implements Compute {

    public ComputeServer() throws RemoteException {
        super();
    }

    @Override
    public double square(int number) throws RemoteException {
        return (number * number);
    }

    @Override
    public double power(int num1, int num2) throws RemoteException {
        return (Math.pow(num1, num2));
    }

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "src/mx/itam/packages/rmi/server/server.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            // start the rmiregistry 
            LocateRegistry.createRegistry(1099);   /// default port
            // if the rmiregistry is not started by using java code then
            // 1) Start it as follows: rmiregistry -J-classpath -J"c:/.../RMI.jar" or
            // 2) Add this to the classpath C:\...\RMI.jar and then start the rmiregistry

            String name = "Compute";
            ComputeServer engine = new ComputeServer();
            Compute stub =
                    (Compute) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}