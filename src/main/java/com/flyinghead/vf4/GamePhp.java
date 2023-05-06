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
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.zip.DeflaterInputStream;
import java.util.zip.InflaterInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/game.php")
public class GamePhp extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final String origBase64  = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	private static final String substBase64 = "0DkhSLQt4H3lcd5qIVw7pNRU/WmoTzAE8s6gPZ+yCiJYv1GMKO9funXbajFxe2Br";
	
	private String substitute(String s, String from, String to) {
		StringBuilder sb = new StringBuilder(s.length());
		for (int i = 0; i < s.length(); i++) {
			int idx = from.indexOf(s.charAt(i));
			if (idx != -1)
				sb.append(to.charAt(idx));
			else
				sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	private String scramble(String s) {
		return substitute(s, origBase64, substBase64);
	}

	private String unscramble(String s) {
		return substitute(s, substBase64, origBase64);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String in = req.getReader().readLine();
		// url decode payload after SDF=
		in = URLDecoder.decode(in.substring(4), "UTF-8");
		// unscramble
		in = unscramble(in);
		// base64 decode
		byte[] bytes = Base64.getDecoder().decode(in);
		// inflate
		// looks like the first 2 bytes are missing (and the last 4)
		byte[] fullStream = new byte[bytes.length + 2];
		fullStream[0] = 0x78;
		fullStream[1] = (byte)0x9c;
		System.arraycopy(bytes, 0, fullStream, 2, bytes.length);
		ByteArrayInputStream bais = new ByteArrayInputStream(fullStream);
		InflaterInputStream iis = new InflaterInputStream(bais);

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
		log(sb.toString());
		// zk=1&gi=SBHX&gv=1.10&bn=37821967197&gs=0&ti=192.168.1.30&gt=0
		// after game:
		// zk=4&gi=SBHX&gv=1.10&bn=37821967197&gs=1&ti=192.168.1.30&gt=228
		// gi	game id
		// gv	game version
		// bn	serial#
		// ti	ip address
		// gt	game time?
		// response expects:
		// st=OK or NG (mandatory)
		// if OK:
		// bn	mandatory, must match
		// ti	mandatory, must match
		QueryParams params = new QueryParams(sb.toString());
		sb = new StringBuilder();
		sb.append("st=OK&ti=").append(params.get("ti", ""))
			.append("&bn=").append(params.get("bn", ""));
		log(" -> " + sb.toString());

		// deflate
		DeflaterInputStream dos = new DeflaterInputStream(new ByteArrayInputStream(sb.toString().getBytes()));
		bytes = dos.readAllBytes();
		// base64 encode
		String out = Base64.getEncoder().encodeToString(bytes);
		// scramble
		out = scramble(out);

		resp.setHeader("Connection", "close");	// ignored, need nginx config
		PrintWriter writer = resp.getWriter();
		writer.write(out);
	}

}
