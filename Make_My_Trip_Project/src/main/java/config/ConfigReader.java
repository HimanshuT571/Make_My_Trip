package config;

import java.io.InputStream;
import java.util.Map;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigReader {
	
	public static RootConfig config;
	
	static
	{
		try
		{
			ObjectMapper objectMapper = new ObjectMapper();
			InputStream inputStream = ConfigReader.class.getClassLoader()
					.getResourceAsStream("config/environmentConfig.json");
			 if (inputStream == null) {
	                throw new RuntimeException("environmentConfig.json not found");
	            }
			 
			config = objectMapper.readValue(inputStream, RootConfig.class);
			
		}
		catch(Exception e)
		{
			System.out.println("Failed to read the json");
			e.printStackTrace();
		}
		
	}
	
	public static EnvironmentConfig getActiveEnvironment()
	{
		String selectEnv;
		String runtime = System.getProperty("env");
		
		if(runtime != null)
		{
			selectEnv = runtime;
		}
		else
		{
			selectEnv = config.getDefaultEnv();
		}
		System.out.println("Selected environment is: "+selectEnv);
		
		Map<String, EnvironmentConfig> envs = config.getEnvironment();
		return envs.get(selectEnv); 
 	}

}
