package com.odil6.mmm;

import java.io.File;

public class MusicFile
{
	private String path;

	private String displayName;

	public MusicFile(File f)
	{
		this.displayName = f.getName();
		this.path = f.getPath();
	}

	public String toString()
	{
		return this.displayName;
	}

	public String getPath()
	{
		return this.path;
	}
}
