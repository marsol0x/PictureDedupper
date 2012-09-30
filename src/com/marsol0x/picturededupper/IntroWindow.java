package com.marsol0x.picturededupper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class IntroWindow {

	public Shell createShell(final Display display) {
		final Shell shell = new Shell(display);
		shell.setText("Picture Dedupper");
		shell.setLayout(new GridLayout(4, false));
		
		final Label welcomeLabel = new Label(shell, SWT.NONE);
		welcomeLabel.setText("Please select a directory to dedup.");
		GridData gridData = new GridData();
		gridData.horizontalSpan = 4;
		welcomeLabel.setLayoutData(gridData);
		
		final Text directoryPath = new Text(shell, SWT.BORDER);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		gridData.widthHint = 300;
		directoryPath.setLayoutData(gridData);
		directoryPath.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				return;
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.KEYPAD_CR || e.keyCode == SWT.CR) {
					new DeduppingWindow();
					DeduppingWindow.createShell(display);
					shell.setVisible(false);
				}
					
			}
			
		});
		
		final Button browseButton = new Button(shell, SWT.PUSH);
		browseButton.setText("Browse");
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				DirectoryDialog dlg = new DirectoryDialog(shell);
				
				dlg.setFilterPath(directoryPath.getText());
				dlg.setText("Picture Directory");
				//dlg.setMessage("Select a directory");
				
				String dir = dlg.open();
				if (dir != null) {
					directoryPath.setText(dir);
				}
				
			}
		});
		
		final Button nextButton = new Button(shell, SWT.PUSH);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.RIGHT;
		gridData.horizontalSpan = 3;
		nextButton.setLayoutData(gridData);
		nextButton.setText("Next");
		nextButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (directoryPath.getText() != "") {
					new DeduppingWindow();
					DeduppingWindow.createShell(display);
					shell.setVisible(false);
				}
					
				return;
			}
		});
		
		final Button cancelButton = new Button(shell, SWT.PUSH);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.RIGHT;
		cancelButton.setLayoutData(gridData);
		cancelButton.setText("Cancel");
		cancelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				shell.dispose();
			}
		});
		
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
