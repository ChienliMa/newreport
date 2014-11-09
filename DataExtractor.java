package org.parosproxy.paros.extension.newreport;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.parosproxy.paros.db.Database;
import org.parosproxy.paros.db.RecordParam;
import org.parosproxy.paros.db.TableHistory;
import org.parosproxy.paros.db.TableParam;
import org.parosproxy.paros.db.TableScan;
import org.parosproxy.paros.model.Model;
import org.parosproxy.paros.network.HttpMalformedHeaderException;
import org.parosproxy.paros.network.HttpMessage;
import org.parosproxy.paros.view.View;
import org.zaproxy.zap.extension.httppanel.HttpPanelRequest;

public class DataExtractor {
	

	public static String getHttpHistory(Model model) {
		//get all http request and response in http history
		
		// get session id
		long sessionid = model.getSingleton().getSession().getSessionId();
		
		// get list of http history id, basicly I just want to know the max value.
		TableHistory history = Database.getSingleton().getTableHistory();
		List<Integer> historylist = new  ArrayList<Integer>();
		try {
			historylist = history.getHistoryIds(sessionid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String result="\n";
		// iterate through httphistory table, read each request header. 
		// in the same method  we can read the body of request as well as 
		// of response if needed.
		for( int id: historylist ){
			try {
				HttpMessage temp = history.read(id).getHttpMessage();
				String header =  temp.getRequestHeader().toString();
				// print out the result for debug
				System.out.println(header);
				result += header + "\n";
			
			} catch (HttpMalformedHeaderException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static String getPanelHistory(View view) {
		//get http response and request showed in http pannel
		HttpPanelRequest requestpanel= view.getRequestPanel();	
		HttpMessage message = (HttpMessage) requestpanel.getMessage();
		String result = message.getRequestHeader().toString()+"\n"+message.getResponseHeader().toString();
		return result;
	}
	
	public static String getParams(){
		Database db = Database.getSingleton();
		TableParam params = db.getTableParam();
		List<RecordParam> list = new ArrayList();
		String result = "";
		try {
			list = params.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for( RecordParam record : list ){
			result += record.toString() + "\n";
		}
		
		return result;
	}
	
	public static String geScans(){
		Database db = Database.getSingleton();
		TableScan scans = db.getTableScan();
		List<RecordParam> list = new ArrayList();
		String result = "";
//		try {
//			scans = null;
//			//list = scans.
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		for( RecordParam record : list ){
			result += record.toString() + "\n";
		}
		
		return result;
	}
	

	
	
}
