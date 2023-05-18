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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class VanillaAccessCode
{
	public static String makeCode(int id) {
		try {
			String s = String.format("%08d", id);
			s = s.substring(0, 8);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(s.getBytes());
			byte[] digest = md.digest();

			int v =   (((digest[2] & 0x7f) << 16) | ((digest[1] & 0xff) << 8)   | (digest[0] & 0xff))
			        ^ (((digest[5] & 0x3f) << 17) | ((digest[4] & 0xff) << 9)   | ((digest[3] & 0xff) << 1)  | ((digest[2] & 0x80) >> 7))
			        ^ (((digest[8] & 0x1f) << 18) | ((digest[7] & 0xff) << 10)  | ((digest[6] & 0xff) << 2)  | ((digest[5] & 0xc0) >> 6))
			        ^ (((digest[11] & 0xf) << 19) | ((digest[10] & 0xff) << 11) | ((digest[9] & 0xff) << 3)  | ((digest[8] & 0xe0) >> 5))
			        ^ (((digest[14] & 0x7) << 20) | ((digest[13] & 0xff) << 12) | ((digest[12] & 0xff) << 4) | ((digest[11] & 0xf0) >> 4))
			        ^                                                            (((digest[15] & 0xff) << 5) | ((digest[14] & 0xf8) >> 3));
			return String.format("%07d", v);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
