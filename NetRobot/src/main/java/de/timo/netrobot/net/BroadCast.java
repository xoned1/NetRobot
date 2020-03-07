package de.timo.netrobot.net;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class BroadCast implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				broadcast("NetRobot", listAllBroadcastAddresses());
				Thread.sleep(5000);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	public void broadcast(String broadcastMessage, List<InetAddress> addresses) throws IOException {

		for (InetAddress address : addresses) {
			MulticastSocket multicastSocket = new MulticastSocket(8888);
			DatagramSocket socket = new DatagramSocket();
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

			networkInterface.getInterfaceAddresses().stream().map(InterfaceAddress::getBroadcast)
					.filter(Objects::nonNull).forEach(broadcastList::add);
		}
		return broadcastList;
	}

}
