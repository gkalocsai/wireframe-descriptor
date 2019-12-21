package display.graphutil;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import static org.lwjgl.opengl.GL11.glVertex3dv;


import java.awt.Color;

import org.lwjgl.opengl.GL11;


public class Graphics {

	
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 1000;
	

	public static void line2D(float x1, float y1, float x2, float y2, Color color) {
		glBegin(GL_LINE_STRIP);
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex2f( x1,  y1);
		glVertex2f( x2,  y2);
		glEnd();
	}


	public static void circle(float h,float k,double r, Color color ){


		glBegin(GL_LINES);
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		float x,y;
		for (int i = 0; i < 180; i++)
		{
			x = (float) (r * Math.cos(i) - h);
			y = (float) (r * Math.sin(i) + k);
			
			
			glVertex2f( (x + k),  (y - h));

			x = (float) (r * Math.cos(i + 0.1) - h);
			y = (float) (r * Math.sin(i + 0.1) + k);
			glVertex2f( (x + k), (y - h));

		}
		glEnd();
	}
	
	
	public static void point2D(float x, float y, Color color) {
		glBegin(GL_LINE_STRIP);
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex2f( x,  y);
		
		glEnd();
	}

	public static void point2D(float x, float y) {
		glBegin(GL_POINTS);
		//glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex2f( x,  y);
		
		glEnd();
	}


	
	
	public static void point3D(float x, float y, float z) {
		glBegin(GL_POINTS);
		//glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex3f( x,  y, z);
		
		glEnd();
	}


	public static void setColor(Color color) {
		glBegin(GL_POINTS);
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);		
		glEnd();
	}


	public static void point3D(float x, float y, float z, Color color) {
		glBegin(GL_POINTS);
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex3f( x,  y, z);
		glEnd();
	}


	public static void point3D(float x, float y, float z, float roll, float pitch, float yaw, Color color) {
	
		System.out.println(roll +" "+pitch +" "+yaw);
		
		
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);		
		
		glMatrixMode(GL_MODELVIEW);
		  
		
		GL11.glPushMatrix();
		//glTranslatef(0.5f ,0.5f,0.5f);
		
		glRotatef(roll,  1, 0, 0);		
		glRotatef(pitch, 0, 1, 0);
		glRotatef(yaw,   0, 0, 1);
		//glTranslatef(-0.5f ,-0.5f,-0.5f);
		
			
		glBegin(GL_POINTS);		
		glVertex3f( x,  y, z);	
		glEnd();
		GL11.glPopMatrix();
		
	}



	public static void line(Color color,int x1,int y1, int x2, int y2) {
		glBegin(GL_LINE_STRIP);
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex2f( (float)x1/WIDTH,  (float)y1/HEIGHT);
		glVertex2f( (float)x2/WIDTH,  (float)y2/HEIGHT);
		glEnd();
	}
	
	public static void rect(Color color,int x1,int y1, int x2, int y2) {
		glBegin(GL_QUADS);
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex2f( (float)x1/WIDTH,  (float)y1/HEIGHT);
		glVertex2f( (float)x2/WIDTH,  (float)y1/HEIGHT);
		glVertex2f( (float)x2/WIDTH,  (float)y2/HEIGHT);
		glVertex2f( (float)x1/WIDTH,  (float)y2/HEIGHT);
		glEnd();
	}
	
	
	public static void triangle(Color color,int x1,int y1, int x2, int y2, int x3, int y3) {
		glBegin(GL_TRIANGLES);
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex2f( (float)x1/WIDTH,  (float)y1/HEIGHT);
		glVertex2f( (float)x2/WIDTH,  (float)y2/HEIGHT);
		glVertex2f( (float)x3/WIDTH,  (float)y3/HEIGHT);
		glVertex2f( (float)x1/WIDTH,  (float)y1/HEIGHT);
		glEnd();
	}

	
	public static void pixel(Color color,int x, int y) {
		glBegin(GL_POINTS);
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex2f( (float)x/WIDTH,  (float)y/HEIGHT);
		
		glEnd();
	}


	public static void filledTriangle3D(Color color,double[] a, double[] b,double[] c) {
		glBegin(GL_TRIANGLES);
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex3dv(a);
		glVertex3dv(b);
		glVertex3dv(c);	
		glEnd();
	}
	
	
	public static void pixel3D(Color color,double[] koords) {
		glBegin(GL_POINTS);
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex3dv( koords);
		
		glEnd();
	}

	public static void triangle3D(Color color,double[] a, double[] b,double[] c) {
		glBegin(GL_LINE_STRIP);
		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex3dv(a);
		glVertex3dv(b);
		glVertex3dv(c);
		glVertex3dv(a);
		glEnd();
	}

	public static void line3D(Color color,double[] a, double[] b) {
		glBegin(GL_LINE_STRIP);

		glColor3f( ((float)color.getRed())/255.0f,((float)color.getGreen())/255.0f,((float)color.getBlue())/255.0f);
		glVertex3dv(a);
		glVertex3dv(b);
	
		glEnd();
	}
}
