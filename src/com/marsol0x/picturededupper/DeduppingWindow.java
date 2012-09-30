package com.marsol0x.picturededupper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class DeduppingWindow {
	
	public static Shell createShell(final Display display) {
		final Shell shell = new Shell(display);
		shell.setText("Picture Dedupper");
		
		GridLayout gridLayout = new GridLayout(2, true);
		shell.setLayout(gridLayout);
		
		Group pictureGroup =  new Group(shell, SWT.NONE);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.widthHint = 600;
		pictureGroup.setLayoutData(gridData);
		pictureGroup.setText("Are these pictures duplicates?");
		pictureGroup.setLayout(new RowLayout());
		
		Button yesButton = new Button(shell, SWT.PUSH);
		gridData = new GridData();
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
}
