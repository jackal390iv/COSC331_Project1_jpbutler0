package cosc331_project1_jpbutler0;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Reaper
 */
public class Chat {

    Scanner input = new Scanner(System.in);
    DatagramSocket socket = new DatagramSocket();
    DatagramPacket packet_Sent;
    String myIPAddress = InetAddress.getLocalHost().getHostAddress();
    int myPortAddress = socket.getLocalPort();
    String connectedIPAddress;
    int connectedPortAddress;
    Boolean end = false;
    String message;

    public Chat() throws SocketException, UnknownHostException, IOException {
        System.out.println("Port Address: " + myPortAddress);
        System.out.println("IPAddress: " + myIPAddress);
        clientServer();
    }

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        Chat server = new Chat();
    }

    public void clientServer() throws SocketException, UnknownHostException, IOException {
        Boolean check = false;
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
    }
}
