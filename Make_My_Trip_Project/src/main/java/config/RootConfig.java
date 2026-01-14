package config;

import java.util.Map;

public class RootConfig {
	
	private String defaultEnv;
	
	private Map<String, EnvironmentConfig> environment;
	
	public String getDefaultEnv()
	{
		return defaultEnv;
	}
	
	public Map<String, EnvironmentConfig> getEnvironment()
	{
		return environment; 
	}

}
