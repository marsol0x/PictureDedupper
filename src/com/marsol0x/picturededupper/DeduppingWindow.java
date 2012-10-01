package com.marsol0x.picturededupper;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class DeduppingWindow {
	private GridLayout picturesLayout = new GridLayout();
	private GridData pictureData;
	private Group pictureGroup;
	
	public Shell createShell(final Display display, final String directoryPath) {
		final Shell shell = new Shell(display);
		shell.setText("Picture Dedupper");
		
		GridLayout gridLayout = new GridLayout(2, true);
		shell.setLayout(gridLayout);
		
		this.pictureGroup =  new Group(shell, SWT.NONE);
		this.pictureData = new GridData();
		this.pictureData.horizontalSpan = 2;
		this.pictureData.widthHint = 600;
		this.pictureGroup.setLayout(this.picturesLayout);
		this.pictureGroup.setLayoutData(this.pictureData);
		this.pictureGroup.setText("Are these pictures duplicates?");
		
		Button yesButton = new Button(shell, SWT.PUSH);
		GridData gridData = new GridData();
		gridData.widthHint = 100;
		gridData.horizontalAlignment = SWT.RIGHT;
		yesButton.setLayoutData(gridData);
		yesButton.setText("Yes");
		
		Button noButton = new Button(shell, SWT.PUSH);
		gridData = new GridData();
		gridData.widthHint = 100;
		gridData.horizontalAlignment = SWT.LEFT;
		noButton.setLayoutData(gridData);
		noButton.setText("No");
		
		shell.addListener(SWT.Close, new Listener() {

			@Override
			public void handleEvent(Event event) {
				display.dispose();
			}
			
		});
		
		shell.pack();
		shell.open();
		
		return shell;
	}
	
	private void startDedupping(final Shell shell, final String directoryPath) {
		final HashMap<String, ArrayList<String>> dups = PictureDedupper.getChecksumDups(directoryPath);
		
		//Canvas firstImage = new Canvas(shell, )
		
	}
}
