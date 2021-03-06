
package org.databasesync.autogen.sqlmap.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.io.InputStream;
import java.io.InputStreamReader;


import org.apache.log4j.Logger;
import org.databasesync.autogen.SaveToFile;
import org.databasesync.autogen.sqlmap.GenExtractXml;


/**
 * @ClassName:  AbstractGenExtractXml
 * @Description:TODO
 * 
 * @author nxn on 2013-3-13
 */
public abstract class AbstractGenExtractXml extends SaveToFile implements GenExtractXml {
	Logger logger = Logger.getLogger(this.getClass());
	

	public String readFile(String exampleName) throws FileNotFoundException, IOException
	{
		
		InputStream stream=ClassLoader.getSystemClassLoader().getResourceAsStream(exampleName);
		StringBuilder builder=new StringBuilder();
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			inputStreamReader=new InputStreamReader(stream);
			bufferedReader=new BufferedReader(inputStreamReader);
			String str;
			while((str=bufferedReader.readLine())!=null)
			{
				builder.append(str).append("\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(exampleName +" not exite");
			throw e;
		}catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(exampleName +" IOException ");
			throw e;
		}finally
		{
			if(bufferedReader!=null)
			{
				bufferedReader.close();
			}
			
			if(inputStreamReader!=null)
			{
				inputStreamReader.close();
			}
			
			if(stream!=null)
			{
				stream.close();
			}
			
		}
		return builder.toString();
	}
}
