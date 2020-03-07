package de.timo.netrobot.commands;

public enum Commands {
	
	CLICK_LEFT(), 
	CLICK_RIGHT(), 
	MOUSE_UP(), 
	MOUSE_RIGHT(), 
	MOUSE_DOWN(), 
	MOUSE_LEFT(),
	VOLUME_SET(),
	VOLUME_UP(),
	VOLUME_DOWN(),
	VOLUME_MUTE(),
	SHUTDOWN(),
	EXIT();
	
	
	@Override
	public String toString() {
		return super.toString();
	}

}
