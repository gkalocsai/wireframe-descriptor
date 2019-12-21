package display.graphutil;

import static org.lwjgl.opengl.GL11.GL_RGB;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glReadPixels;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class ScreenShot {
	
	
	  public ScreenShot(String fileLocation) {
		screenShot(fileLocation);
	}

	  private void screenShot(String fileName){
          //Creating an rbg array of total pixels
          int WIDTH = 1920;
			int HEIGHT = 1080;
			int[] pixels = new int[WIDTH * HEIGHT];
          int bindex;
          // allocate space for RBG pixels
          ByteBuffer fb = ByteBuffer.allocateDirect(WIDTH * HEIGHT * 3);

          // grab a copy of the current frame contents as RGB
          glReadPixels(0, 0, WIDTH, HEIGHT, GL_RGB, GL_UNSIGNED_BYTE, fb);

          BufferedImage imageIn = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
          // convert RGB data in ByteBuffer to integer array
          for (int i=0; i < pixels.length; i++) {
              bindex = i * 3;
              pixels[i] =
                  ((fb.get(bindex) << 16))  +
                  ((fb.get(bindex+1) << 8))  +
                  ((fb.get(bindex+2) << 0));
          }
          //Allocate colored pixel to buffered Image
          imageIn.setRGB(0, 0, WIDTH, HEIGHT, pixels, 0 , WIDTH);

          //Creating the transformation direction (horizontal)
          AffineTransform at =  AffineTransform.getScaleInstance(1, -1);
          at.translate(0, -imageIn.getHeight(null));

          //Applying transformation
          AffineTransformOp opRotated = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
          BufferedImage imageOut = opRotated.filter(imageIn, null);

          try {//Try to screate image, else show exception.
              ImageIO.write(imageOut, "png" , new File("haromszog.png"));
          }
          catch (Exception e) {
              System.out.println("ScreenShot() exception: " +e);
          }
      }
	
}


