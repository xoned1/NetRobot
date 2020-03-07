package de.timo.netrobot.net;

public class NetworkService {

	private static NetworkService INSTANCE;
	private BroadCast broadcast = new BroadCast();

	private CommandServer server = new CommandServer();
	
	private NetworkService() { }

	public static NetworkService getInstance() {
		if (INSTANCE == null)
			INSTANCE = new NetworkService();
		return INSTANCE;
	}

	public BroadCast getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(BroadCast broadcast) {
		this.broadcast = broadcast;
	}

	public CommandServer getServer() {
		return server;
	}

	public void setServer(CommandServer server) {
		this.server = server;
	}
	
	public void setOn() {
		broadcast.start();
		server.listen();
	}
	
	public void disconnect() {
		//TODO Stop Broadcasting
		server.disconnect();
	}
	
	
}
