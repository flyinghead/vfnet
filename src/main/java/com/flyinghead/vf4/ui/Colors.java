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
package com.flyinghead.vf4.ui;

import java.util.Arrays;

public class Colors {
	/*
	private static String[] BaseColorNames = {
			"Default", "White", "Light Gray", "Gray", "Dark Gray", "Yellow", "Cream", "Dark Yellow",
			"Orange", "Pink", "Red", "Brown", "Dark Brown", "Light Blue", "Blue", "Dark Blue",
			"Violet", "Light Green", "Green", "Dark Green", "Black"
	};
	private static String[] BaseColorValues = {
			"000000", "ffffff", "c0c0c0", "808080", "404040", "ffff10", "c0c080", "a0a008",
			"f0a010", "ff10f0", "ff1010", "c01010", "800808", "10ffff", "1010ff", "0000c0",
			"c010f0", "00ff00", "10f010", "00c000", "000000"
	};
	*/
	
	private static String[][] CharColors = new String[32][];
	private static String[][] CharItems = new String[32][];
	private static String[][] HairColors = new String[16][];

	static {
		// akira
		CharItems[0] = new String[] { "Vest", "Headband", "Pants", "Belt & wristband" };
		CharItems[1] = CharItems[0];
		CharColors[0] = new String[] {
			"Default", "Brown", "Dark Green", "Yellow", "Violet", "Green", "Dark Blue", "Dark Brown",
			"Light Blue", "Gray", "Black", "Silver", "Gold", "Dark Orange", "Light Blue", "White",		// Dark Orange -> Dark Yellow?
			"Blue", "Light Green", "Red", "Orange", "?"
		};
		HairColors[0] = new String[] { "Default", "Blonde", "Red" };
		CharColors[1] = CharColors[0];

		// sarah P1
		CharItems[2] = new String[] { "Body Suit", "Boots", "Belt & boot soles", "Belt buckle" };
		// Metal Red/Brown? [14]
		// Metal Green/Blue? [13]
		CharColors[2] = new String[] {
			"Default", "Light Blue", "Violet", "Yellow", "Pink", "Green", "Orange", "Light Blue",		// FIXME x2 light blue/aqua
			"Red", "White", "Black", "Silver", "Gold", "Light Green", "Brown",
			"Dark Green", "Dark Red", "Grey", "Bright Yellow", "Blue"
		};
		HairColors[1] = new String[] { "Default", "Red", "Orange" };
		// sarah P2
		CharItems[3] = new String[] { "Shirt", "Pants", "Jewelry", "Shoes & belt" };
		CharColors[3] = Arrays.copyOf(CharColors[2], CharColors[2].length);
		CharColors[3][13] = "Light Blue";
		CharColors[3][14] = "Dark Brown";

		// lau
		CharItems[4] = new String[] { "Vest", "Body suit", "Belt", "Shoes" };
		CharItems[5] = new String[] { "Shirt", "Pants", "Trim", "Shoes" };
		CharColors[4] = new String[] {
			"Default", "Brown", "Dark Blue", "Yellow", "Green", "Orange", "Grey", "Purple",
			"Blue", "Pink", "White", "Silver", "Gold", "Red", "Black", "Dark Yellow",
			"Pink", "Light Blue", "Light Green", "Orange"
		};
		HairColors[2] = new String[] { "Default", "Red", "Gray" };
		CharColors[5] = CharColors[4];
		
		// shun P1
		CharItems[6] = new String[] { "Pants", "Vest", "Sleeves", "Belt" };
		CharColors[6] = new String[] {
			"Default", "Brown", "Violet", "Yellow", "Green", "Orange", "Dark Brown", "Blue",
			"Light Green", "Light Blue", "Brown", "Silver", "Gold", "White", "Black",
			"Gray", "Pink", "Light Green", "Red", "Pink"
		};
		HairColors[3] = new String[] { "Default", "Black", "Brown" };
		// shun P2
		CharItems[7] = new String[] { "Pants", "Shirt", "Shoes", "Belt" };
		CharColors[7] = new String[] {
			"Default", "Brown", "Violet", "Green", "Violet", "Light Blue", "Dark Brown", "Cream",
			"Dark Blue", "Red", "Orange", "Silver", "Gold", "White", "Black",
			"Gray", "Pink", "Light Green", "Red", "Pink"
		};
		
		// jeffry
		CharItems[8] = new String[] { "Belt", "Gloves", "Pants", "Foot guards" };
		CharItems[9] = new String[] { "Belt", "Bracers", "Shorts", "Belt" };
		CharColors[8] = new String[] {
			"Default", "Light Blue", "Violet", "Yellow", "Green", "Red", "Dark Blue", "Light Gray",
			"Tan", "Brown", "Black", "Silver", "Gold", "Orange", "Pink", "Grey",
			"Aqua", "Blue", "Light Yellow", "White"
		};
		HairColors[4] = new String[] { "Default", "Blonde", "Red" };
		CharColors[9] = CharColors[8];
		
		// pai P1
		CharItems[10] = new String[] { "Shirt", "Pants", "Hat", "Shoes & belt" };
		CharColors[10] = new String[] {
			"Default", "Violet", "Orange", "Pink", "Dark Yellow", "Light Green", "Pink", "Light Blue",
			"Violet", "Yellow", "Red", "Silver", "Gold", "White", "Black", "Gray",
			"Light Blue", "Red Orange", "Orange", "Red"
		};
		HairColors[5] = new String[] { "Default", "Brown", "Blonde" };
		// pai P2
		CharItems[11] = new String[] { "Body suit", "Vest", "Front flap", "Hat" };
		CharColors[11] = new String[] {
			"Default", "Lemon Yellow", "Light Blue", "Purple", "Light Green", "Pink", "Light Blue", "Orange",
			"dark red", "Blue", "Purple", "Silver", "Gold", "White", "Black", "Gray",
			"Light Blue", "Red Orange", "Orange", "Red"
		};
		
		// jacky
		CharItems[12] = new String[] { "Vest", "Shirt", "Pants", "Trim" };
		CharItems[13] = new String[] { "Shirt", "Sleeves", "Collar", "Pants" };
		CharColors[12] = new String[] {
			"Default", "White", "Dark Gray", "Gray", "Red", "Light Green", "Orange", "Green",
			"Blue", "Violet", "Pink", "Silver", "Gold", "Blue Green", "Light Green", "Light Blue",
			"Dark Brown", "Red", "Orange", "Black"
		};
		HairColors[6] = new String[] { "Default", "Red / Purple", "Brunette / Green" };
		CharColors[13] = CharColors[12];
		
		// kage
		CharItems[14] = new String[] { "Head cloth", "Shirt", "Belt", "Pants" };
		CharItems[15] = new String[] { "Helmet & armor", "Shirt", "Upper pants", "Lower pants" };
		CharColors[14] = new String[] {
			"Default", "Red", "Yellow", "Dark Yellow", "Dark Violet", "Dark Green", "Black", "Brown",
			"Green", "Gray", "Matte Black", "Silver", "Gold", "Orange", "White", "Pink",
			"Hot Pink", "Dark Orange", "Plain Yellow"
		};
		HairColors[7] = new String[] { "Default", "1?", "2?" };
		CharColors[15] = CharColors[14];
		
		// lion
		CharItems[16] = new String[] { "Jacket", "Shirt", "Shorts", "Shoes" };
		CharItems[17] = CharItems[16];
		CharColors[16] = new String[] {
			"Default", "Brown", "Blue", "Light Green", "Green", "Yellow", "Gray", "Violet",
			"Light Blue", "Light Pink", "White", "Silver", "Gold", "Red", "Black", "Dark Blue",
			"Pink", "Dark Green", "Yellow Green", "Blue Green", "Dark Gray"
		};
		HairColors[8] = new String[] { "Default", "Brown", "White" };
		CharColors[17] = CharColors[16];
		
		// wolf
		CharItems[18] = new String[] { "Stripe", "Shorts", "Boots", "Pads" };
		CharItems[19] = new String[] { "Trim", "Pants", "Boots", "Loin cloth" };
		CharColors[18] = new String[] {
			"Default", "Cream", "Yellow", "Light Blue", "Red", "Purple", "Orange", "Dark Blue",
			"Gray", "Brown", "Black", "Silver", "Gold", "Mint", "Blue-green", "Orange",
			"Green", "Pink", "Red", "White"
		};
		HairColors[9] = new String[] { "Default", "White", "Black" };
		CharColors[19] = CharColors[18];
		
		// aoi
		CharItems[20] = new String[] { "Shirt", "Under shirt & socks", "Belt & sandals", "Pants" };
		CharItems[21] = new String[] { "Shirt", "Pants", "Sandals", "Gloves & trim" };
		CharColors[20] = new String[] {
			"Default", "Brown", "Green", "Lime", "Blue", "Purple", "Light Pink", "Light Blue",
			"Red", "Cream", "Black", "Silver", "Gold", "Dark Blue", "Aqua", "Dark Red",
			"Navy Blue", "Pink", "Yellow", "White"
		};
		HairColors[10] = new String[] { "Default", "White", "Blonde" };
		CharColors[21] = CharColors[20];
		
		// lei-fei
		CharItems[22] = new String[] { "1?", "2?", "3?", "4?" };
		CharItems[23] = CharItems[22];
		CharColors[22] = new String[] {
			"Default", // TODO
		};
		HairColors[11] = new String[] { "Default", "1?", "2?" };
		CharColors[23] = CharColors[22];
		
		// vanessa
		CharItems[24] = new String[] { "Top", "Shorts", "Shoes", "Socks" };
		CharItems[25] = new String[] { "Vest", "Pants", "Shoes", "Shirt" };
		CharColors[24] = new String[] {
			"Default", "White", "Dark Yellow", "Light Blue", "Pink", "Yellow", "Brown", "Violet",
			"Green", "Dark Brown", "Dark Blue", "Silver", "Gold", "Orange", "Gray", "Light Green",
			"Blue", "Black", "Light Gray", "Red", "Dark Gray"
		};
		HairColors[12] = new String[] { "Default" };
		CharColors[25] = CharColors[24];
		
		// dural
		CharItems[26] = new String[0];
		CharItems[27] = CharItems[26];
		CharColors[26] = new String[] { "Default" };
		HairColors[13] = new String[] { "Default" };
		CharColors[27] = CharColors[26];
		
		// goh
		CharItems[28] = new String[] { "Vest", "Shirt", "Pants", "Belt" };
		CharItems[29] = new String[] { "Jacket", "Inner jacket", "Pants", "Underwear" };
		CharColors[28] = new String[] {
			"Default", "Red", "Dark Yellow", "Yellow", "Light Pink", "Dark Green", "Grey Blue", "Brown",
			"Turquois", "Gray", "Black", "Silver", "Gold", "Dark Orange", "Light Blue", "White",
			"Blue", "Hot Pink", "Red", "Orange"
		};
		HairColors[14] = new String[] { "Default", "Red", "Silver" };
		CharColors[29] = CharColors[28];
		
		// brad
		CharItems[30] = new String[] { "Shorts", "Gloves", "Tattoo", "Shinguards" };
		CharItems[31] = new String[] { "Pants", "Tattoo", "Gloves", "Shirt" };
		CharColors[30] = new String[] {
			"Default", "Green", "Blue", "Red", "Yellow", "Light Blue", "Purple", "Orange",
			"Cream", "Brown", "Dark Yellow", "Silver", "Gold", "White", "Black", "Dark Blue",
			"Hot Pink", "Gray", "Teal", "Bright Orange"
		};
		HairColors[15] = new String[] { "Default", "Blonde", "Red" };
		CharColors[31] = CharColors[30];
	}
	
	public String getColorItem(int charNum, int playerNum, int item) {
		String[] items = CharItems[charNum * 2 + playerNum];
		if (item >= items.length)
			return null;
		else
			return items[item];
	}
	
	public String[] getPalette(int charNum, int playerNum) {
		return CharColors[charNum * 2 + playerNum];
	}
	
	public String[] getHairPalette(int charNum) {
		return HairColors[charNum];
	}
}
