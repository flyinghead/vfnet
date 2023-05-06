/*
	vf.net web server revival
	Copyright (C) 2023 flyinghead

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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Base64;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.DeflaterInputStream;
import java.util.zip.InflaterInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xbill.DNS.ARecord;
import org.xbill.DNS.DClass;
import org.xbill.DNS.Message;
import org.xbill.DNS.Name;
import org.xbill.DNS.Rcode;
import org.xbill.DNS.Record;
import org.xbill.DNS.Resolver;
import org.xbill.DNS.Section;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.Type;

@WebServlet("/sys/servlet/PowerOn")
public class PowerOn extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String in = req.getReader().readLine();
		// base64 decode
		byte[] bytes = Base64.getDecoder().decode(in);
		// deflate
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		InflaterInputStream iis = new InflaterInputStream(bais);

		// vf4 vanilla: Unexpected end of ZLIB input stream
		// so let's read data one byte at a time
		StringBuilder sb = new StringBuilder();
		try {
			int c;
			while (true)
			{
				c = iis.read();
				if (c == -1)
					break;
				sb.append((char)c);
			}
		} catch (IOException e) {
			// expected log("deflate failed", e);
		}

		String params = sb.toString();
		log(params); // game_id=SBHX&ver=1.10&serial=37821967197&ip=192.168.1.30&firm_ver=0317&boot_ver=0000
		// required: Connection: close
		//           Content-Length or Transfer-Encoding: chunked
		// expects deflated/base64 response with:
		// stat=1		(mandatory)
		// uri=			(mandatory)
		// host=		(mandatory)
		// place_id=[0-9A-Fa-f] (4 digits) passed back to VF4NetUpload power_on and start msgs at least
		// year			int, server now time
		// month		int
		// day			int
		// hour			int
		// minute		int
		// second 		int
		// name=		ft
		// nickname=	ft, possible '#' char followed by stuff
		// setting		ft, int
		// region0		ft, int
		// region_name0	ft
		// region_name1	ft
		// region_name2	ft
		// region_name3	ft
		sb = new StringBuilder();
		sb.append("stat=1&uri=http://")
			.append(getServletContext().getAttribute("localIp"))
//			.append("127.0.0.1")
			.append("/sys/servlet/vf4/&host=naominet.jp");
		sb.append("&place_id=0001");
		
		Calendar cal = Calendar.getInstance();
		sb.append("&year=").append(String.valueOf(cal.get(Calendar.YEAR)));
		sb.append("&month=").append(String.valueOf(1 + cal.get(Calendar.MONTH)));
		sb.append("&day=").append(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		sb.append("&hour=").append(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
		sb.append("&minute=").append(String.valueOf(cal.get(Calendar.MINUTE)));
		sb.append("&second=").append(String.valueOf(cal.get(Calendar.SECOND)));
		
		log(" -> " + sb.toString());

		// deflate
		DeflaterInputStream dos = new DeflaterInputStream(new ByteArrayInputStream(sb.toString().getBytes()));
		bytes = dos.readAllBytes();
		// base64 encode
		String out = Base64.getEncoder().encodeToString(bytes) + "\r\n";
		
		resp.setHeader("Pragma", "DFI");		// indicates that payload is deflated and base64 encoded
		resp.setHeader("Connection", "close");	// ignored, need nginx config
		resp.getWriter().write(out);
	}

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			// Try with opendns.com
			Record queryRecord = Record.newRecord(Name.fromString("myip.opendns.com."), Type.A, DClass.IN);
			Message queryMessage = Message.newQuery(queryRecord);
			Resolver r = new SimpleResolver("resolver1.opendns.com");
			Message answer = r.send(queryMessage);
			if (answer.getHeader().getRcode() != Rcode.NOERROR)
				throw new ServletException("myip.opendns.com lookup failed");
			List<Record> records = answer.getSection(Section.ANSWER);
			for (Record record : records)
				if (record instanceof ARecord) {
					String ip = ((ARecord)record).getAddress().getHostAddress();
					log("External IP is " + ip);
					getServletContext().setAttribute("localIp", ip);
					return;
				}
			// Fall back to enumerating the network interfaces and use the first IPv4 address
			Enumeration<NetworkInterface> ifs = NetworkInterface.getNetworkInterfaces();
			while (ifs.hasMoreElements()) {
				NetworkInterface iface = ifs.nextElement();
				if (iface.isLoopback())
					continue;
				for (InterfaceAddress addr : iface.getInterfaceAddresses())
					if (addr.getAddress() instanceof Inet4Address) {
						String ip = addr.getAddress().getHostAddress();
						log("LAN IP is " + ip);
						getServletContext().setAttribute("localIp", ip);
						return;
					}
			}
		} catch (Exception e) {
			throw new ServletException("myip.opendns.com lookup failed", e);
		}
	}

}
