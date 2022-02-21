package mx.itam.packages.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import mx.itam.packages.interfaces.Compute;

public class ComputeClient {

    public static void main(String args[]) {
        System.setProperty("java.security.policy", "src/mx/itam/packages/client/client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry("localhost"); // server's ip address args[0]
            Compute comp = (Compute) registry.lookup(name);

            System.out.println("3^2 = " + comp.square(3));
            System.out.println("3^3 = " + comp.power(3, 3));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
