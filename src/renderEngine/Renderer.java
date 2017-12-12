package renderEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import models.RawModel;
import models.TexturedModel;

public class Renderer
{
	public void prepare()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glClearColor(0, 0, 0, 1);
	}
	
	public void render(TexturedModel texturedModel)
	{
		RawModel model = texturedModel.getRawModel();
		glBindVertexArray(model.getVaoID());
		glEnableVertexAttribArray(Loader.VERTEX_ATTRIBUTE_INDEX);
		glEnableVertexAttribArray(Loader.TEX_COORDS_ATTRIBUTE_INDEX);
		glActiveTexture(GL_TEXTURE0);
//		glBindTexture(GL_TEXTURE_2D, texturedModel.getTexture().getID() );
		texturedModel.getTexture().bind();
		glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(Loader.VERTEX_ATTRIBUTE_INDEX);
		glDisableVertexAttribArray(Loader.TEX_COORDS_ATTRIBUTE_INDEX);
		glBindVertexArray(0);
	}
}
