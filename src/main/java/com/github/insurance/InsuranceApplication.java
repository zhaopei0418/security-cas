package com.github.insurance;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@EnableWebSecurity
@SpringBootApplication
public class InsuranceApplication {

	private static Log logger = LogFactory.getLog(InsuranceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InsuranceApplication.class, args);
		disableSslVerification();
	}

	private static void disableSslVerification() {
		try {
			SSLContext sslcontext = SSLContext.getInstance("TLSv1.2");
			sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					if (ObjectUtils.isEmpty(arg0)) {
						throw new CertificateException();
					}
				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					if (ObjectUtils.isEmpty(arg0)) {
						throw new CertificateException();
					}
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}
			}}, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = (String hostname, SSLSession session) -> {
				if (!StringUtils.isEmpty(hostname)) {
					return true;
				} else {
					return false;
				}
			};
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (Exception e) {
			logger.error("处理ssl证书异常", e);
		}
	}
}
