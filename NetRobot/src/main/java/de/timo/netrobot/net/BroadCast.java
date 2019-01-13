package de.timo.netrobot.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class BroadCast implements Runnable {

	private DatagramSocket socket = null;
	private Thread thread;

	@Override
	public void run() {
		try {
			while (true) {
				broadcast("NetRobot", listAllBroadcastAddresses());
				Thread.sleep(5000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	public void broadcast(String broadcastMessage, List<InetAddress> addresses) throws IOException {

		for (InetAddress address : addresses) {
			MulticastSocket multicastSocket = new MulticastSocket(8888);
			socket = new DatagramSocket();
			socket.setBroadcast(true);

			byte[] buffer = broadcastMessage.getBytes();
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 8888);
			socket.send(packet);
			socket.close();
		}
	}

	List<InetAddress> listAllBroadcastAddresses() throws SocketException {
		List<InetAddress> broadcastList = new ArrayList<>();
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()) {
			NetworkInterface networkInterface = interfaces.nextElement();

			if (networkInterface.isLoopback() || !networkInterface.isUp()) {
				continue;
			}

			networkInterface.getInterfaceAddresses().stream().map(networkInt -> networkInt.getBroadcast())
					.filter(Objects::nonNull).forEach(broadcastList::add);
		}
		return broadcastList;
	}

}
