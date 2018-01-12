package me.client.Rendering;

import me.client.Mains.Configs;
import me.client.Utilities.MyDisplay;

public class DisplayManager {
	public static MyDisplay initalizeDisplay(String title) {
		Configs configs = null;
		MyDisplay dis = new MyDisplay(title, configs);
		return dis;
	}
}
