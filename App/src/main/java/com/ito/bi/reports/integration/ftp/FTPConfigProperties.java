package com.ito.bi.reports.integration.ftp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ftp")
public class FTPConfigProperties {

	@Value("${ftp.hostName}")
	private String hostName;

	@Value("${ftp.port}")
	private int port;

	@Value("${ftp.userName}")
	private String userName;

	@Value("${ftp.password}")
	private String password;

	@Value("${ftp.openSSHPrivateKey}")
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
		return "FTPConfig [Host=" + hostName + ", Port=" + port + ", User=" + userName+"]";
	}
}
