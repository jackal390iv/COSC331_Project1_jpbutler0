package cosc331_project1_jpbutler0;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Reaper
 */
public class SendData extends Thread {

    Scanner input;
    String message;
    DatagramSocket socket;
    DatagramPacket packet_Sent;
    String connectedIPAddress;
    int connectedPortAddress;

    public SendData(DatagramSocket Socket, DatagramPacket Packet, String ipAddress, int port) throws SocketException, UnknownHostException, IOException {
        socket = Socket;
        packet_Sent = Packet;
        connectedIPAddress = ipAddress;
        connectedPortAddress = port;
    }

    public void run() {
        Boolean end = false;
        do {
            input = new Scanner(System.in);
            System.out.println("Send: ");
            message = input.nextLine();
            if (message.equals("EXIT")) {
                end = true;
            } else {
                try {
                    packet_Sent = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName(connectedIPAddress), connectedPortAddress);
                    System.out.println("Packet Sent: " + packet_Sent.getPort() + " " + packet_Sent.getAddress());
                } catch (UnknownHostException ex) {
                    Logger.getLogger(SendData.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    socket.send(packet_Sent);
                } catch (IOException ex) {
                    Logger.getLogger(SendData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
            }
        } while (end == false);
        System.exit(0);
    }
}
