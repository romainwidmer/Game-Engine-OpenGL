package engine;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import display.DisplayManager;
import model.Camera;
import model.Entity;
import model.RawModel;
import model.TexturedModel;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		Loader loader = new Loader();
		
		float[] vertices = {			
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
				
		};
		
		float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

				
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};
		
		RawModel rawModel = loader.loadToVao(vertices, indices, textureCoords);
		ModelTexture texture = new ModelTexture(loader.loadTexture("tux2"));
		TexturedModel texturedModel = new TexturedModel(rawModel, texture);
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -5f), 0, 0, 0, 1);
		
		Camera camera = new Camera();
				
		
		// Game loop
		while(!Display.isCloseRequested()) {
			//entity.increasePosition(0,  0,  -0.02f);
			entity.increateRotation(1, 1, 0);
			
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
		
			renderer.render(entity, shader);
		
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		// Clean up is the game is closing
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
	
	/**
	 * Helper to create a textured entity
	 */
	private void createEntity() {
		
	}
}
