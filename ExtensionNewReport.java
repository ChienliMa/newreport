/*
 * Zed Attack Proxy (ZAP) and its related class files.
 * 
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package  org.parosproxy.paros.extension.newreport;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.parosproxy.paros.Constant;
import org.parosproxy.paros.extension.ExtensionAdaptor;
import org.parosproxy.paros.extension.ExtensionHook;
import org.zaproxy.zap.view.ZapMenuItem;

/*
 * An example ZAP extension which adds a top level menu item. 
 * 
 * This class is defines the extension.
 */
public class ExtensionNewReport extends ExtensionAdaptor {

	public static final String NAME = "ExtensionNewReport";
	
    private ZapMenuItem menuExample = null;

    private JFrame optionframe = null;
    private ScopePanel scopetab = null;
    private JPanel advancedtab = null;
    
    /**
     * 
     */
    public ExtensionNewReport() {
        super();
        initialize();
    }

    /**
     * @param name
     */
    public ExtensionNewReport(String name) {
        super(name);
    }

        /**
         * This method initializes this
         * 
         */
        private void initialize() {
        this.setName("ExtensionNewReport");
        }
        
		@Override
		// Hook the extension to the top menu
        public void hook(ExtensionHook extensionHook) {
            super.hook(extensionHook);
            
            if (getView() != null) {
                // Register our top menu item, as long as we're not running as a daemon
                // Use one of the other methods to add to a different menu list
                extensionHook.getHookMenu().addReportMenuItem(getMenuExample());
            }

        }

        private ZapMenuItem getMenuExample() {
        if (menuExample == null) {
                menuExample = new ZapMenuItem( "menu.report.html.generate" );
                menuExample.setText("自定义HTML报告");

                menuExample.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                        // This is where you do what you want to do.
                        // In this case we'll just show a popup message.
                	getNewOptionFrame().setVisible(true);
                }
            });
        }
        return menuExample;
    }// zap menu item
        
        public JFrame getNewOptionFrame(){
            optionframe = new JFrame("Generate Report");
        	//optionframe.setPreferredSize( new Dimension(530,320) );
            JTabbedPane mainpane = new JTabbedPane();
            mainpane.add("Scope", getScopeTab());
            mainpane.add("Advanced", getAdvancedTab());
            optionframe.add(mainpane);
            optionframe.pack();
        	return optionframe;
        }
        
        private ScopePanel getScopeTab(){
        	scopetab = new ScopePanel( optionframe, this );	
        	return scopetab;
        }
        
        private JPanel getAdvancedTab(){
        	advancedtab = new AdvancedPanel();
        	return advancedtab;
        }
        
        public String getReportName(){
        	return scopetab.getReportName();
        }
        
        public String getReportDescription(){
        	return scopetab.getReportDescription();
        }
        
        @Override
        public String getAuthor() {
                return "\n Author: Chienli MA";
        }


        @Override
        public URL getURL() {
                try {
                        return new URL(Constant.ZAP_EXTENSIONS_PAGE);
                } catch (MalformedURLException e) {
                        return null;
                }
        }
}
