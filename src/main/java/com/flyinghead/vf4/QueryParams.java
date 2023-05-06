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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpUtils;

public class QueryParams
{
	public QueryParams(String q) {
		Map<String, String[]> tmp = HttpUtils.parseQueryString(q);
		params = new HashMap(tmp.size());
		for (String name : tmp.keySet())
			params.put(name, tmp.get(name)[0]);
	}
	
	public String get(String name) {
		return params.get(name);
	}
	
	public String get(String name, String defValue) {
		String s = params.get(name);
		if (s == null || s.isEmpty())
			return defValue;
		else
			return s;
	}
	
	public int getInt(String name) {
		return getInt(name, 0);
	}

	public int getInt(String name, int defValue) {
		String s = params.get(name);
		if (s == null)
			return defValue;
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return defValue;
		}
	}
	
	public String playerGet(int player, String name) {
		return get(playerQName(player, name));
	}
	
	public String playerGet(int player, String name, String defValue) {
		return get(playerQName(player, name), defValue);
	}

	public int playerGetInt(int player, String name) {
		return getInt(playerQName(player, name));
	}

	public int playerGetInt(int player, String name, int defValue) {
		return getInt(playerQName(player, name), defValue);
	}

	public static String playerQName(int player, String base) {
		return base + "_" + String.valueOf(player + 1) + "p";
	}

	private Map<String, String> params;
}
