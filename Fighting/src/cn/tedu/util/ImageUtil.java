package cn.tedu.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import cn.tedu.FightingGame;

public class ImageUtil {

	/**
	 * 
	 * @param imageName ��ʼͼƬ����
	 * @param width ����
	 * @param height ����
	 * @param length ��ͼƬ�� 
	 * @return ͼƬ����
	 * @throws IOException 
	 */
	public static BufferedImage[][] getBufferedImages(String imageName, int width, int height, int length)
			throws IOException {
		BufferedImage image = ImageIO.read(FightingGame.class.getResource("image/"+imageName));
		BufferedImage[][] images = new BufferedImage[2][length];
		int wid = image.getWidth()/width;
		int hig = image.getHeight()/height;
		for (int i = 0; i < length; i++) {
			images[0][i] = image.getSubimage((i % width) * wid, (i / width) * hig, wid, hig);
			images[1][i] = flip(images[0][i]);
		}
		return images;
	}
	
	
	/** 
	  * ͼƬ��ת 
	  * @param degree ��ת���� 0-360
	  */  
	public static  BufferedImage rotateImg(BufferedImage image, int degree) throws IOException {  
	    int iw = image.getWidth();// ԭʼͼ��Ŀ��  
	    int ih = image.getHeight();// ԭʼͼ��ĸ߶�  
	    int w = 0;  
	    int h = 0;  
	    int x = 0;  
	    int y = 0;  
	    degree = degree % 360;  
	    if (degree < 0)  
	        degree = 360 + degree;// ���Ƕ�ת����0-360��֮��  
	    double ang = Math.toRadians(degree);// ���Ƕ�תΪ����  
	  
	    /** 
	     * ȷ����ת���ͼ��ĸ߶ȺͿ�� 
	     */  
	  
	    if (degree == 180 || degree == 0 || degree == 360) {  
	        w = iw;  
	        h = ih;  
	    } else if (degree == 90 || degree == 270) {  
	        w = ih;  
	        h = iw;  
	    } else {  
	        int d = iw + ih;  
	        w = (int) (d * Math.abs(Math.cos(ang)));  
	        h = (int) (d * Math.abs(Math.sin(ang)));  
	    }  
	  
	    x = (w / 2) - (iw / 2);// ȷ��ԭ������  
	    y = (h / 2) - (ih / 2);  
	    BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());  
	    AffineTransform at = new AffineTransform();  
	    at.rotate(ang, w / 2, h / 2);// ��תͼ��  
	    at.translate(x, y);  
	    AffineTransformOp op = new AffineTransformOp(at,  
	            AffineTransformOp.TYPE_BICUBIC);  
	    op.filter(image, rotatedImage);  
	    return rotatedImage;  
	  
	}  
	/**
	 * ��ͼƬˮƽ��ת
	 * @param image
	 * @return
	 */
	public static BufferedImage flip(BufferedImage image) {  
        AffineTransform transform = new AffineTransform();  
		transform.scale(-1, 1);
		transform.translate(0 - image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
        BufferedImage rotatedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());  
        op.filter(image, rotatedImage);
        return rotatedImage;  
    }  
	
	
}
