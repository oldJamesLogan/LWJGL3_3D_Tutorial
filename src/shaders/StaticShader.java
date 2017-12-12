package shaders;

import renderEngine.Loader;

public class StaticShader extends ShaderProgram
{
	private static final String VERTEX_FILE = "src/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.txt";

	public StaticShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void binfAttributes()
	{
		super.bindAttribute(Loader.VERTEX_ATTRIBUTE_INDEX, "position");
		super.bindAttribute(Loader.TEX_COORDS_ATTRIBUTE_INDEX, "textureCoords");
	}

}
