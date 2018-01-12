package me.client.Mains;

import me.client.Rendering.DisplayManager;
import me.client.Utilities.MyDisplay;

public class Main {

	public static void main(String[] args) {
		MyDisplay display = DisplayManager.initalizeDisplay("Game");
		
		while(!display.shouldClose()) {
			display.update();
		}
		
		display.close();
	}

}
