package cosc331_project1_jpbutler0;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Jonathan Butler
 */
public class Chat {

    private DatagramSocket socket;
    private DatagramPacket packet_Sent;

    public static void main(String[] args) {
        new Chat();
    }

    public Chat() {
        try {
            socket = new DatagramSocket();
            System.out.println("Port Address: " + socket.getLocalPort());
            System.out.println("IPAddress: " + InetAddress.getLocalHost().getHostAddress());
            clientServer();

        } catch (Exception ex) {
            System.out.println("Cause: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Local Message: " + ex.getLocalizedMessage());
            //ex.printStackTrace();*/
        }
    }

    private void clientServer() {

        try {
            Boolean check = false;
            Scanner input;
            String connectedIPAddress;
            int connectedPortAddress;

            do {
                input = new Scanner(System.in);
                System.out.println("\n" + "Please enter conection Port Address: ");
                connectedPortAddress = input.nextInt();

                input = new Scanner(System.in);
                System.out.println("Please enter conection IPAddress: ");
                connectedIPAddress = input.nextLine();

                if (InetAddress.getByName(connectedIPAddress).isReachable(5000)) {
                    System.out.println("found");
                    check = true;
                } else {
                    System.out.println("ERROR: connection not found.");
                }
            } while (check == false);

            System.out.println("Port: " + connectedPortAddress + " IP: " + connectedIPAddress);
            System.out.println("Connection Established. Please enter 'EXIT' to exit program.");

            new RecieveData(socket).start();
            new SendData(socket, packet_Sent, connectedIPAddress, connectedPortAddress).start();

        } catch (Exception ex) {
            System.out.println("Cause: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Local Message: " + ex.getLocalizedMessage());
            //ex.printStackTrace();*/
        }
    }
}
