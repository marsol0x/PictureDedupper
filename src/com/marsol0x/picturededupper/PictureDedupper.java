package com.marsol0x.picturededupper;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class PictureDedupper {

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell introWindow = new IntroWindow().createShell(display);
		
		while (!introWindow.isDisposed()) {
			try {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static File[] getFiles(final String dirPath) throws IOException {
		File dir = new File(dirPath);
		File[] children = dir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isFile();
			}
		});
		
		return children;
	}
	
	private static HashMap<String, String> checksumFiles(File[] files) {
		HashMap<String, String> checksum = new HashMap<String, String>();
		
		for (File f : files) {
			try {
				String md5sum = MD5Sum.md5sum(f.getAbsolutePath());
				if (md5sum != null) {
					checksum.put(f.getAbsolutePath(), md5sum);
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return checksum;
	}
	
}
