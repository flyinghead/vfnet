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
package vf4;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.flyinghead.vf4.VanillaAccessCode;

public class VanillaAccessCodeTest {
	@Test
	public void testMakeCode()
	{
		assertEquals("3711192", VanillaAccessCode.makeCode(0));
		assertEquals("3571864", VanillaAccessCode.makeCode(1));
		assertEquals("5093100", VanillaAccessCode.makeCode(2));
		assertEquals("7675387", VanillaAccessCode.makeCode(3));
		assertEquals("3027285", VanillaAccessCode.makeCode(4));
		assertEquals("3474590", VanillaAccessCode.makeCode(1999));
		
		// id_1p=4966181567520857
		assertEquals("7520857", VanillaAccessCode.makeCode(0x53703357));
	}
}
