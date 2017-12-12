package engineTester;

import static org.lwjgl.glfw.GLFW.*;

import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;
import textures.Texture;

public class MainGameLoop
{
	/*
	 * Voy por el capitulo 6 min 7:10
	 * slick util no funciona para cargar texturas porque utiliza metodos deprecados
	 */
	
	private static float x = 0;
	private static float y = 0;

	public static void main(String[] args)
	{
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
//		float[] vertices =
//		{
//				-0.5f + x,  0.5f + y, 0f,
//				-0.5f + x, -0.5f + y, 0f,
//				 0.5f + x, -0.5f + y, 0f,
//				
//				 0.5f + x, -0.5f + y, 0f,
//				 0.5f + x,  0.5f + y, 0f,
//				-0.5f + x,  0.5f + y, 0f
//		};
		
		float[] vertices =
		{
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				 0.5f, -0.5f, 0f,
				 0.5f, 0.5f, 0f
		};
		
		int[] indices =
		{
				0,1, 3,
				3,1,2
		};
		
		float[] textCoords = 
		{
				1,1,	//V0
				0,1,	//V1
				1,1,	//V2
				1,0		//V3
		};
		
		RawModel model = loader.loadToVAO(vertices, textCoords, indices);
		Texture texture = new Texture("char");
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while(!glfwWindowShouldClose(DisplayManager.getWindow()))
		{
			renderer.prepare();
			//	 UPDATE METOD
			update();
			
			//TestCode
			
			//	RENDER METOD
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
	}

	private static void update()
	{
		glfwPollEvents();
		
		//keyboard events
		if(glfwGetKey(DisplayManager.getWindow(), GLFW_KEY_A) == GLFW_TRUE)
		{
			System.out.println("A");
		}
		if(glfwGetKey(DisplayManager.getWindow(), GLFW_KEY_ESCAPE) == GLFW_TRUE)
		{
			glfwSetWindowShouldClose(DisplayManager.getWindow(), true);
		}
		//joystick events
		if(glfwJoystickPresent(GLFW_JOYSTICK_1))
		{
			byte boton = glfwGetJoystickButtons(GLFW_JOYSTICK_1).get(0);
			if(boton == 1){System.out.println("Joystick button A pressed");}
			
			float leftStickX = glfwGetJoystickAxes(0).get(0);
			float leftStickY = glfwGetJoystickAxes(0).get(1);
			
			if(leftStickX > 0.2f){x += leftStickX;}
			if(leftStickX < -0.2f){x += leftStickX;}
			
			if(leftStickY > 0.2f){y += leftStickY;}
			if(leftStickY < -0.2f){y += leftStickY;}
			
			System.out.println(x);
		}
	}
}
