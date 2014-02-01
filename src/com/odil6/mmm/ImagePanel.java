package com.odil6.mmm;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public ImagePanel(Component parent, InputStream in)
	{
		try
		{
			image = ImageIO.read(in);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}