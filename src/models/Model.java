package models;

import static org.lwjgl.opengl.GL11.*;

public class Model
{
	private int draw_count;
	private int v_id;
	
	public Model(Float[] vertices)
	{
		draw_count = vertices.length;
	}
}
