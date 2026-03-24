/*
	vf.net web server revival
	Copyright (C) 2026 flyinghead

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.flyinghead.vf4;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class ServerStatus implements InitializingBean, Runnable {
	private Thread thread = null;
	private long updateInterval = 300;
	private String statusUrl = null;
	private File statusDir = null;

	@Override
	public void afterPropertiesSet() throws Exception
	{
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("/usr/local/etc/dcnet/status.conf"));
			statusUrl = props.getProperty("status-url", null);
			if (statusUrl != null)
				statusUrl += "/vf4";
			String update = props.getProperty("update-interval", "300");
			updateInterval = Integer.parseInt(update);
			statusDir = new File(props.getProperty("status-dir", "/var/local/lib/dcnet/status"));
		} catch (NumberFormatException e) {
			updateInterval = 300;
		} catch (IOException e) {
			e.printStackTrace();
			updateInterval = 300;
		}
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run()
	{
		while (true)
		{
			JSONObject status = new JSONObject();
			status.put("gameId", "vf4");
			status.put("timestamp", System.currentTimeMillis() / 1000);
			JSONArray array = new JSONArray();
			array.put(status);
			String text = array.toString(4);
			if (statusUrl == null)
			{
				try (OutputStream ostream = new FileOutputStream(new File(statusDir, "vf4"))) {
					ostream.write(text.getBytes("UTF-8"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				URL url;
				try {
					url = new URL(statusUrl);
				} catch (MalformedURLException e) {
					e.printStackTrace();
					break;
				}
				try {
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					conn.setDoOutput(true);
					conn.setInstanceFollowRedirects(false);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/json");
					conn.setRequestProperty("User-Agent", "DCNet-DiscordWebhook");	// required!
					byte[] postData = text.getBytes("UTF-8");
					conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
					conn.setUseCaches(false);
					try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
					   wr.write(postData);
					}
					int code = conn.getResponseCode();
					if (code < 200 || code >= 300)
						System.out.println("Status HTTP error: " + conn.getResponseCode() + " " + conn.getResponseMessage());
					conn.getInputStream().close();
					conn.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			try {
				Thread.sleep(updateInterval * 1000);
			} catch (InterruptedException e) {
				break;
			}
		}
		
	}
}
