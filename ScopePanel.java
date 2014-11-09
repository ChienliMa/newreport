package org.parosproxy.paros.extension.newreport;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.parosproxy.paros.extension.AbstractPanel;
import org.parosproxy.paros.extension.ExtensionAdaptor;


public class ScopePanel extends AbstractPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame optionframe = null;
	private ExtensionAdaptor extension = null;
	private JTextArea name, description;
	private JComboBox template;
	private JCheckBox justAlert;
	
	public ScopePanel( JFrame frame , ExtensionAdaptor extension){
		initialize();
		optionframe = frame;
		this.extension = extension;
	}
	
	private void initialize(){
        this.setLayout( new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2,3,2,3);
        
        // name line 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
		this.add( new JLabel("Name : "), gbc );
		
		gbc.gridx++ ;
		name = new JTextArea(" Report ");
		this.add( new JScrollPane(name, JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), gbc );
        
        // description line 
        gbc.gridy++ ;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        this.add( new JLabel("Description :¡¡"), gbc );
 
        gbc.gridx++;
		description = new JTextArea( " Description ", 3, 30 );
		description.setLineWrap( true );
        this.add( new JScrollPane( description ), gbc );
        
        // template line
        gbc.gridx = 0 ;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
	    this.add( new JLabel("Template : "), gbc );
	    
	    String[] selection = {"selection one", "selection two"};
	    template = new JComboBox<>( selection );
	    template.setSelectedIndex(0);
	    gbc.gridx++;
	    gbc.anchor = GridBagConstraints.EAST;
	    this.add( template, gbc );
	    
	    // just alert check box line 
	    gbc.gridx = 0;
	    gbc.gridy++;
	    gbc.anchor = GridBagConstraints.WEST;
	    this.add( new JLabel("Just alerts in scope?"), gbc );
	    
        justAlert = new JCheckBox();
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        this.add( justAlert, gbc );
	    
	    // button line 
	    gbc.gridx = 1;
        gbc.gridy++ ;
        JPanel buttonpane = new JPanel( new FlowLayout( FlowLayout.RIGHT ));
        buttonpane.add( getCancleButton() );
        buttonpane.add( getGenerateButton() );
        this.add( buttonpane, gbc );
	}
	
	public String getReportName(){
		return name.getText();
	}
	
	public String getReportDescription(){
		return description.getText();
	}
	
	public boolean justAlert(){
		return justAlert.isSelected();
	}
	
	public int getTemplate(){
		return template.getSelectedIndex();
	}
	
	private JButton getCancleButton(){
		JButton canclebutton = new JButton("Cancle");
		canclebutton.addActionListener(
				new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		               optionframe.setVisible(false);
		            }
		        });
		return canclebutton;
	}
	
	private JButton getGenerateButton(){
		JButton generatebutton = new JButton("Generate");
		generatebutton.addActionListener(
				new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                ReportLastScan report = new ReportLastScan();
					    report.generateReport(extension.getView(), extension.getModel(), ReportLastScan.ReportType.HTML);
					    optionframe.setVisible( false );
		            }
		        });
		return generatebutton;
	}
	
	
	
}
