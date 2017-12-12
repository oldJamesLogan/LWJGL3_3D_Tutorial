package renderEngine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class DisplayManager
{
	private static final int WIDTH = 1024;
	private static final int HEIGHT = WIDTH /12 * 9;
	
	private static final int FPS = 120;
	
	private static final String TITLE = "LWJGL2 3D Game";
	
	private static long window;
	
	public static void createDisplay()
	{
		if(!glfwInit())
		{
			throw new IllegalStateException("ERROR: no se pudo iniciar GLFW");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, TITLE, 0, 0);
		//FullScreen Mode
//		long window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, TITLE, glfwGetPrimaryMonitor(), 0);
		
		if(window == 0)
		{
			throw new IllegalStateException("ERROR: no se pudo crear la ventana");
		}
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (videoMode.width() -WIDTH) / 2, (videoMode.height() - HEIGHT) /2);
		
		glfwShowWindow(window);
		
		GLFW.glfwMakeContextCurrent(window);
		
		GL.createCapabilities();
		
		for(int i = 0; i < 16; i++)
		{
			boolean present = GLFW.glfwJoystickPresent(i);
			if(present)
			{
				if(GLFW.glfwGetJoystickName(i).equalsIgnoreCase("Xbox Controller"))
				{
					System.out.println("Control " + i + ": " + GLFW.glfwGetJoystickName(i));
				}
				System.out.println(GLFW.glfwGetJoystickName(i) + ": " + present);
			}
		}
		
		glEnable(GL_TEXTURE_2D);
	}
	
	public static void updateDisplay()
	{
		glfwSwapBuffers(DisplayManager.getWindow());
	}
	
	public static void closeDisplay()
	{
		glfwTerminate();
	}
	
	public static long getWindow()
	{
		return window;
	}
}
