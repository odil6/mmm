package com.odil6.mmm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public static File rootFile;

	public static void main(String[] args)
	{
		new MusicFile(new File("/users/meshulamsilk/test.something"));
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane artistScroller = new JScrollPane();
		artistScroller.setBounds(20, 48, 283, 122);
		contentPane.add(artistScroller);

		Scanner reader = new Scanner(System.in);
		String path = reader.nextLine();// JOptionPane.showInputDialog("Enter root path:");
		while (!new File(path).exists())
			path = reader.nextLine();// JOptionPane.showInputDialog("Enter root path:");
		rootFile = new File(path);

		File[] artists = rootFile.listFiles(new FileFilter()
		{
			public boolean accept(File child)
			{
				return child.isDirectory();
			}
		});
		DefaultListModel artistModel = new DefaultListModel();
		for (File f : artists)
			artistModel.add(0, new MusicFile(f));
		final JList artistList = new JList(artistModel);
		artistScroller.setViewportView(artistList);

		JLabel lblBandsartist = new JLabel("bands/artist");
		lblBandsartist.setBounds(20, 26, 99, 16);
		contentPane.add(lblBandsartist);

		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(315, 48, 163, 147);
		imagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(imagePanel);

		JLabel lblAlbums = new JLabel("albums");
		lblAlbums.setBounds(20, 179, 61, 16);
		contentPane.add(lblAlbums);

		JLabel lblSongs = new JLabel("songs");
		lblSongs.setBounds(175, 182, 61, 16);
		contentPane.add(lblSongs);

		JScrollPane albumScroller = new JScrollPane();
		albumScroller.setBounds(20, 210, 128, 251);
		contentPane.add(albumScroller);

		final JList albumList = new JList();
		albumScroller.setViewportView(albumList);

		JScrollPane songScroller = new JScrollPane();
		songScroller.setBounds(175, 210, 128, 251);
		contentPane.add(songScroller);

		final JList songList = new JList();
		songScroller.setViewportView(songList);

		JScrollPane infoScroller = new JScrollPane();
		infoScroller.setBounds(315, 210, 163, 210);
		contentPane.add(infoScroller);

		JTextArea textArea = new JTextArea();
		infoScroller.setViewportView(textArea);

		JButton btnCopy = new JButton("Copy");
		btnCopy.setBounds(315, 432, 163, 29);
		contentPane.add(btnCopy);

		artistList.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				DefaultListModel albums = new DefaultListModel();
				for (Object o : artistList.getSelectedValues())
				{
					File[] albumsForArtist = new File(((MusicFile) o).getPath()).listFiles(new FileFilter()
					{
						public boolean accept(File arg0)
						{
							return arg0.isDirectory();
						}
					});
					for (File f : albumsForArtist)
						albums.add(0, new MusicFile(f));
				}
				albumList.setModel(albums);
			}
		});

		albumList.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				DefaultListModel songs = new DefaultListModel();
				for (Object o : albumList.getSelectedValues())
				{
					File[] songsForAlbum = new File(((MusicFile) o).getPath()).listFiles(new FileFilter()
					{
						public boolean accept(File arg0)
						{
							return arg0.isFile() && (arg0.getName().endsWith(".mp3") || arg0.getName().endsWith(".flac"));
						}
					});
					for (File f : songsForAlbum)
						songs.add(0, new MusicFile(f));
				}
				songList.setModel(songs);
			}
		});
	}
}
