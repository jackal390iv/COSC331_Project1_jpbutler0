package cosc331_project1_jpbutler0;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Reaper
 */
public class RecieveData extends Thread {

    String message;
    DatagramSocket socket;
    byte[] messageByte = new byte[65508];
    DatagramPacket packet_Recieved = new DatagramPacket(messageByte, messageByte.length);

    public RecieveData(DatagramSocket Socket) throws SocketException, UnknownHostException, IOException {
        socket = Socket;
    }

    public void run() {
        Boolean check = true;
        do {
            try {
                packet_Recieved = new DatagramPacket(messageByte, messageByte.length);
                socket.receive(packet_Recieved);
                System.out.println("Packet Recieved: " + packet_Recieved.getPort() + " " + packet_Recieved.getAddress());

            } catch (IOException ex) {
                Logger.getLogger(RecieveData.class.getName()).log(Level.SEVERE, null, ex);
            }
            message = new String(packet_Recieved.getData(), packet_Recieved.getOffset(), packet_Recieved.getLength());      //packet_Recieved.getData());
            System.out.println("Peer: " + message);
            try {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
            }
        } while (check == true);
    }
}
