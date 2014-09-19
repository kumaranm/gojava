package com.mk.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class UTFFileWriter {

	public static void main(String[] args) throws Exception {

		/*String _alertFile = "/tmp/utf_alert.txt";
		File alertfile = new File(_alertFile);
		if (!alertfile.exists()) {
			alertfile.createNewFile();
		}
		FileWriter fw = new FileWriter(alertfile.getAbsoluteFile());
		BufferedWriter _outwrt = new BufferedWriter(fw);
		String outStr = "1" + "\t" ;
		outStr += "工藤　叡二";
		outStr += "\n";
		
		_outwrt.write(outStr);
		_outwrt.flush();
		_outwrt.close();*/
		
		
		BufferedWriter _outwrt = null;
		 _outwrt = new BufferedWriter(new OutputStreamWriter(new
		FileOutputStream("c:/utf_alert1.txt"), "UTF-8"));

		 List<String> s = new ArrayList<>();
		 s.add("1");
		 s.add("2");
		 s.add("3");
		 s.add("4");
		 
		 String header = "ID \t CUSTOMER \t \n";
		 String data = "elen \n";
		 
		 for (String string : s) {
			 _outwrt.write(string + "\t" + data);
		}
		 
		_outwrt.flush();
		_outwrt.close();
	}

}
