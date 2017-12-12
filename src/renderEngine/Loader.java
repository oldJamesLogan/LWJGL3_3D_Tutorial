package renderEngine;

import java.nio.*;
import java.util.*;

import org.lwjgl.BufferUtils;

import models.RawModel;
import textures.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Loader
{
	public static final int VERTEX_ATTRIBUTE_INDEX = 0;
	public static final int TEX_COORDS_ATTRIBUTE_INDEX = 1;
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	
	public RawModel loadToVAO(float[] positions, float[] textureCoords, int[] indices)
	{
		int vaoID = createVAO();
		storeDataInAttributeList(VERTEX_ATTRIBUTE_INDEX, 3, positions);
		storeDataInAttributeList(TEX_COORDS_ATTRIBUTE_INDEX, 2, positions);
		bindIndicesBuffer(indices);
		unbindVAO();
		return new RawModel(vaoID, indices.length);
	}
	
//	public int loadTexture(String fileName)
//	{
//		Texture texture = null;
//		
//		try
//		{
//			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/" + fileName + ".png"));
//		}
//		catch (FileNotFoundException e)
//		{
//			e.printStackTrace();
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//		
//		int textureID = texture.getTextureID();
//		
//		Texture texture = new Texture(fileName);
//		
//		int textureID = texture.getID();
//		textures.add(textureID);
//		
//		return textureID;
//	}
	
	public void cleanUp()
	{
		for(int vao:vaos)
		{
			glDeleteVertexArrays(vao);
		}
		
		for(int vbo:vbos)
		{
			glDeleteBuffers(vbo);
		}
		
		for(int texture:textures)
		{
			glDeleteTextures(texture);
		}
	}
	
	private int createVAO()
	{
		int vaoID = glGenVertexArrays();
		vaos.add(vaoID);
		glBindVertexArray(vaoID);
		
		return vaoID;
	}
	
	private void storeDataInAttributeList(int attributeNumber, int size,float[] data)
	{
		int vboID = glGenBuffers();
		vbos.add(vboID);
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glVertexAttribPointer(attributeNumber, size, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO()
	{
		glBindVertexArray(0);
	}
	
	private void bindIndicesBuffer(int[] indices)
	{
		int vboID = glGenBuffers();
		vbos.add(vboID);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
}
