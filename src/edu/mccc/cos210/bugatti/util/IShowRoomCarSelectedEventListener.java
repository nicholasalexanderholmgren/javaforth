package edu.mccc.cos210.bugatti.util;

import java.util.EventListener;

public interface IShowRoomCarSelectedEventListener extends EventListener {
	void CarSelected(String carName);
}
