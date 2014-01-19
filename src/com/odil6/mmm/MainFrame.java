package com.odil6.mmm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
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
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JScrollPane artistScroller = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.WEST, artistScroller, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, artistScroller, 154, SpringLayout.NORTH, contentPane);
		artistScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
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
		sl_contentPane.putConstraint(SpringLayout.NORTH, artistScroller, 6, SpringLayout.SOUTH, lblBandsartist);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblBandsartist, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBandsartist, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblBandsartist, 109, SpringLayout.WEST, contentPane);
		contentPane.add(lblBandsartist);

		ImagePanel imagePanel = new ImagePanel(this, MainFrame.class.getResource("/res/blank.png"));
		sl_contentPane.putConstraint(SpringLayout.WEST, imagePanel, 22, SpringLayout.EAST, artistScroller);
		imagePanel.setPreferredSize(new Dimension(100, 100));
		sl_contentPane.putConstraint(SpringLayout.NORTH, imagePanel, 32, SpringLayout.NORTH, contentPane);
		imagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(imagePanel);

		JLabel lblAlbums = new JLabel("albums");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAlbums, 1, SpringLayout.SOUTH, artistScroller);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblAlbums, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblAlbums);

		JLabel lblSongs = new JLabel("songs");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSongs, 1, SpringLayout.SOUTH, artistScroller);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSongs, 165, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblSongs, -35, SpringLayout.WEST, imagePanel);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblAlbums, -27, SpringLayout.WEST, lblSongs);
		contentPane.add(lblSongs);

		JScrollPane albumScroller = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, albumScroller, 6, SpringLayout.SOUTH, lblAlbums);
		sl_contentPane.putConstraint(SpringLayout.WEST, albumScroller, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, albumScroller, -5, SpringLayout.SOUTH, contentPane);
		albumScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(albumScroller);

		final JList albumList = new JList();
		albumScroller.setViewportView(albumList);

		JScrollPane songScroller = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.EAST, artistScroller, 0, SpringLayout.EAST, songScroller);
		sl_contentPane.putConstraint(SpringLayout.EAST, albumScroller, -42, SpringLayout.WEST, songScroller);
		sl_contentPane.putConstraint(SpringLayout.NORTH, songScroller, 6, SpringLayout.SOUTH, lblSongs);
		sl_contentPane.putConstraint(SpringLayout.WEST, songScroller, 165, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, songScroller, -5, SpringLayout.SOUTH, contentPane);
		songScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(songScroller);

		final JList songList = new JList();
		songScroller.setViewportView(songList);

		JScrollPane infoScroller = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, imagePanel, -6, SpringLayout.NORTH, infoScroller);
		sl_contentPane.putConstraint(SpringLayout.EAST, imagePanel, 0, SpringLayout.EAST, infoScroller);
		sl_contentPane.putConstraint(SpringLayout.WEST, infoScroller, 315, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, infoScroller, -12, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, imagePanel, 0, SpringLayout.WEST, infoScroller);
		sl_contentPane.putConstraint(SpringLayout.NORTH, infoScroller, 182, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, songScroller, -22, SpringLayout.WEST, infoScroller);
		infoScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(infoScroller);

		JTextArea textArea = new JTextArea();
		infoScroller.setViewportView(textArea);

		JButton btnCopy = new JButton("Copy");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnCopy, 17, SpringLayout.EAST, songScroller);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnCopy, -5, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnCopy, -12, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, infoScroller, -13, SpringLayout.NORTH, btnCopy);
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
							return arg0.isFile() && (arg0.getName().endsWith(".mp3") || arg0.getName().endsWith(".flac") || arg0.getName().endsWith(".m4a"));
						}
					});
					for (File f : songsForAlbum)
						songs.add(0, new MusicFile(f));
				}
				songList.setModel(songs);
			}
		});

		btnCopy.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Object[] selected = null;
				if (songList.getSelectedIndices().length > 0)
					selected = songList.getSelectedValues();
				else
					if (albumList.getSelectedIndices().length > 0)
						selected = albumList.getSelectedValues();
					else
						if (artistList.getSelectedIndices().length > 0)
							selected = artistList.getSelectedValues();

				MusicFile[] files = new MusicFile[selected.length];
				for (int i = 0; i < selected.length; i++)
					files[i] = (MusicFile) selected[i];

				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (fc.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
				{
					File destination = fc.getSelectedFile();
					//Move the files array to the destination file
				}
			}
		});
	}
}
