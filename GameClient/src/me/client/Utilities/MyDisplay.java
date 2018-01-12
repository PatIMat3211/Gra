package me.client.Utilities;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import me.client.Mains.Configs;

public class MyDisplay {
	private String title;
	private int WIDTH;
	private int HEIGHT;
	private int FPS_CAP;
	
	private long lastFrameTime;
	private float delta;
	
	private long lastFPS;
	private int fps;
	private int fpses;
	
	private boolean shouldClose = false;
	
	public MyDisplay(String title, Configs configs) {
		this.title = title;
		WIDTH = configs.WIDTH;
		HEIGHT = configs.HEIGHT;
		FPS_CAP = configs.FPS_CAP;
		init();
	}
	
	private void init() {
		ContextAttribs attribs = new ContextAttribs(3,3)
		.withForwardCompatible(true)
		.withProfileCore(true);
		
		try {
		Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
		Display.create(new PixelFormat(), attribs);
		Display.setTitle(title);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		lastFPS = Sys.getTime();
	}
	
	public void update() {
		shouldClose = Display.isCloseRequested();
		Display.sync(FPS_CAP);
		Display.update();
		while(Keyboard.next()) {
			
			if(Keyboard.getEventKeyState()) {
				
				if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					shouldClose = true;
				}
				
				if(Keyboard.isKeyDown(Keyboard.KEY_E) && Mouse.isGrabbed()) {
					Mouse.setGrabbed(false);
					
				} else if(Keyboard.isKeyDown(Keyboard.KEY_E) && !Mouse.isGrabbed()) {
					Mouse.setGrabbed(true);
				}
				
			}
		}
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime)/1000f;
		lastFrameTime = currentFrameTime;
		calculateFPS();
	}
	
	private void calculateFPS() {
	    if (Sys.getTime() - lastFPS > 1000) {
	        //Display.setTitle("FPS: " + fps); 
	    	fpses = fps;
	        fps = 0; //reset the FPS counter
	        lastFPS += 1000; //add one second
	    }
	    fps++;
	}
	
	public void close() {
		shouldClose = true;
		Display.destroy();
	}
	
	private long getCurrentTime() {
		return Sys.getTime()*1000/Sys.getTimerResolution();
	}
	
	public float getFrameTimeSeconds() {
		return delta;
	}
	
	public boolean shouldClose() {
		return this.shouldClose;
	}
	
	public int getFPS() {
		return fpses;
	}
}
