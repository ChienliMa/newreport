package org.parosproxy.paros.extension.newreport;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AdvancedPanel extends JPanel{

	
	public AdvancedPanel(){
		initialize();
	}
	
	private void initialize(){
		this.setLayout( new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		// include all line
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		this.add( new JLabel("Include all alerts"), gbc );
		
		gbc.gridx++;
		gbc.anchor = GridBagConstraints.EAST;
		this.add( new JCheckBox(), gbc );
		
	}
}

