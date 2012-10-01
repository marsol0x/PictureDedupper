package com.marsol0x.picturededupper;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
	
	private static HashMap<String, ArrayList<String>> checksumFiles(File[] files) {
		HashMap<String, ArrayList<String>> checksum = new HashMap<String, ArrayList<String>>();
		ArrayList<String> el;
		
		for (File f : files) {
			try {
				String md5sum = MD5Sum.md5sum(f.getAbsolutePath());
				if (md5sum != null) {
					if (checksum.get(md5sum) != null){
						el = checksum.get(md5sum);
						el.add(f.getAbsolutePath());
					} else {
						el = new ArrayList<String>();
						el.add(f.getAbsolutePath());
						checksum.put(md5sum, el);
					}
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return checksum;
	}
	
	public static final HashMap<String, ArrayList<String>> getChecksumDups(String directoryPath) {
		File[] files;
		HashMap<String, ArrayList<String>> checksums = null;
		HashMap<String, ArrayList<String>> dups = null;
		
		try {
			files = getFiles(directoryPath);
			checksums = checksumFiles(files);
			
			Set<String> keys = checksums.keySet();
			dups = new HashMap<String, ArrayList<String>>();
			
			for (String k : keys) {
				if (checksums.get(k).size() > 1) {
					dups.put(k, checksums.get(k));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dups;
	}
}
