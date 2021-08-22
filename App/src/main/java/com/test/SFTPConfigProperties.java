package com.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sftp")
public class SFTPConfigProperties
{

	@Value("${sftp.hostName}")
	private String hostName;

	@Value("${sftp.port}")
	private int port;

	@Value("${sftp.userName}")
	private String userName;

	@Value("${sftp.password}")
	private String password;

	@Value("${sftp.openSSHPrivateKey}")
	private String openSSHPrivateKey;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOpenSSHPrivateKey() {
		return openSSHPrivateKey;
	}

	public void setOpenSSHPrivateKey(String openSSHPrivateKey) {
		this.openSSHPrivateKey = openSSHPrivateKey;
	}

	@Override
	public String toString() {
		return "SFTPConfig [Host=" + hostName + ", Port=" + port + ", User=" + userName+"]";
	}
}
