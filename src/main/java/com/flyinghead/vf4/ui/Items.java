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

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;

import org.springframework.web.context.support.ServletContextResource;

public class Items {
	private static String[] charPath = {
			"akira", "sarah", "lau", "shun", "jeffry", "pai", "jacky", "kage",
			"lion", "wolf", "aoi", "lei", "vanessa", "dural", "goh", "brad"
	};

	static class OnePlayerCharItems {
		OnePlayerCharItems() {
			head = new TreeMap<>();
			face = new TreeMap<>();
			body = new TreeMap<>();
			legs = new TreeMap<>();
		}
		public Map<Integer, String> head;
		public Map<Integer, String> face;
		public Map<Integer, String> body;
		public Map<Integer, String> legs;
	}
	
	static class CharItems {
		CharItems() {
			p1Items = new OnePlayerCharItems();
			p2Items = new OnePlayerCharItems();
		}
		OnePlayerCharItems p1Items;
		OnePlayerCharItems p2Items;
	}
	
	private static CharItems[] items = new CharItems[16];
	
	static {
		for (int i = 0; i < items.length; i++) {
			items[i] = new CharItems();
			// common items
			items[i].p1Items.head.put(101, "Tutankhamen's Mask");
			items[i].p1Items.body.put(80, "Glowing Bracelet");
			// FT only?
			/* TODO explore this range
			items[i].p1Items.body.put(161, "Big White Gloves");
			items[i].p1Items.body.put(162, "Flame thrower");
			items[i].p1Items.body.put(163, "Sonic the hedgehog");
			items[i].p1Items.body.put(164, "Koinobori");
			items[i].p1Items.body.put(165, "Big Trophy");
			items[i].p1Items.body.put(166, "Opa-opa");
			items[i].p1Items.body.put(167, "Nights");
			*/
		}
		// akira
		items[0].p1Items.head.put(1, "Wild Hair");
		items[0].p1Items.head.put(2, "Blue Bandana");
		items[0].p1Items.head.put(3, "Hyper Wild Hair");
		items[0].p1Items.head.put(4, "Short Hair");
		items[0].p1Items.head.put(5, "Dreadlocks");
		items[0].p1Items.head.put(6, "Long Bangs");
		items[0].p1Items.head.put(7, "Twisted Headband");
		items[0].p1Items.head.put(8, "School Hat");
		items[0].p1Items.head.put(9, "Black Hood");
		items[0].p1Items.head.put(10, "Victory Headband");
		items[0].p1Items.head.put(11, "Navy Blue Bandana");
		// FT only?
		items[0].p1Items.head.put(12, "Wolf-chan");
		items[0].p1Items.head.put(13, "Messy Long Hair");
		items[0].p1Items.head.put(14, "Pony Tail");
		items[0].p1Items.head.put(15, "Bamboo Hat");
		items[0].p1Items.head.put(16, "Big Chonmage");
		items[0].p1Items.head.put(17, "Santa Hat");
		items[0].p1Items.head.put(18, "Halloween Hat");
		items[0].p1Items.head.put(19, "Crown");
		items[0].p1Items.head.put(102, "Daruma Mask");
		items[0].p1Items.head.put(103, "Head Gear (White)");
		items[0].p1Items.head.put(104, "Head Gear (Red)");
		items[0].p1Items.head.put(105, "Japanese Fencing Helmet");
		
		items[0].p1Items.face.put(31, "Sunglasses (Blue)");
		items[0].p1Items.face.put(32, "Sunglasses (Black)");
		items[0].p1Items.face.put(33, "Sunglasses (Goggle)");
		items[0].p1Items.face.put(34, "Chain Earring");
		items[0].p1Items.face.put(35, "Stubble");
		items[0].p1Items.face.put(36, "Glasses");
		items[0].p1Items.face.put(37, "Tengu Mask");
		items[0].p1Items.face.put(38, "Kabuki Face Paint");
		items[0].p1Items.face.put(39, "Demon Mask");

		items[0].p1Items.body.put(51, "Demon Emblem");
		items[0].p1Items.body.put(52, "Spiked Arm Brace");
		items[0].p1Items.body.put(53, "Lucky Charm");
		items[0].p1Items.body.put(54, "Little Bear");
		items[0].p1Items.body.put(55, "Big Bear");
		items[0].p1Items.body.put(56, "Arm Brace");
		items[0].p1Items.body.put(57, "Leather Brace");
		items[0].p1Items.body.put(58, "Double Brace");
		items[0].p1Items.body.put(59, "Thorn Tattoo");
		items[0].p1Items.body.put(60, "Conqueror Emblem");
		items[0].p1Items.body.put(61, "Sanskrit Emblem");
		items[0].p1Items.body.put(62, "Dragon Emblem");
		items[0].p1Items.body.put(63, "Flame Emblem");
		items[0].p1Items.body.put(64, "Prosperity Charm");
		items[0].p1Items.body.put(65, "Sun Tattoo");
		items[0].p1Items.body.put(66, "Spiked Iron Club");
		items[0].p1Items.body.put(67, "Hand Axe");
		items[0].p1Items.body.put(68, "Ringed Staff");
		items[0].p1Items.body.put(69, "Forearm Guard");
		items[0].p1Items.body.put(70, "Risu-san");
		items[0].p1Items.body.put(71, "Usagi-san");
		items[0].p1Items.body.put(72, "Yuki Dojo Sign");
		// FT only?
		items[0].p1Items.body.put(73, "Gama (Toad)");
		items[0].p1Items.body.put(74, "Tora Tiger");
		items[0].p1Items.body.put(75, "Panda-chi");
		items[0].p1Items.body.put(76, "Wood Warbler Joe");
		items[0].p1Items.body.put(77, "Fukurou (owl)");
		items[0].p1Items.body.put(78, "ChoCho (Butterflies)");
		items[0].p1Items.body.put(79, "Japanese Fencing Armor");
		
		items[0].p1Items.legs.put(81, "Gloves");
		items[0].p1Items.legs.put(82, "Spare Gi");
		items[0].p1Items.legs.put(83, "Dumbell");
		items[0].p1Items.legs.put(84, "Wooden Clogs");
		items[0].p1Items.legs.put(85, "Conch Shell");
		// FT only?
		items[0].p1Items.legs.put(86, "Alien Skeleton");
		items[0].p1Items.legs.put(87, "Araguma-chan (Raccoon)");
		items[0].p1Items.legs.put(88, "Alexander (Beaver)");
		items[0].p1Items.legs.put(89, "Small Shark");
		items[0].p1Items.legs.put(90, "OneBull-chan (Bulldog)");
		items[0].p1Items.legs.put(91, "PuraPura Neko-chan");
		items[0].p1Items.legs.put(92, "PuraPura Usagi-chan");
		items[0].p1Items.legs.put(93, "My Monkey");
		items[0].p1Items.legs.put(94, "Puchin Bee");
		items[0].p1Items.legs.put(95, "Bamboo Katana");
		items[0].p1Items.legs.put(96, "Masamune Sword");
		
		// sarah
		items[1].p1Items.head.put(1, "Short Hair");
		items[1].p1Items.head.put(2, "Mid-parted Hair");
		items[1].p1Items.head.put(3, "Ten Gallon Hat");
		items[1].p1Items.head.put(4, "Headphones");
		items[1].p1Items.head.put(5, "Black Leather Cap");
		items[1].p1Items.head.put(6, "Vertical Roll Hair");
		items[1].p1Items.head.put(7, "Bandana Semi-Long");
		items[1].p1Items.head.put(8, "Sunglasses in Hair");
		items[1].p1Items.head.put(9, "All Back");
		items[1].p1Items.head.put(10, "Dog Ears");
		items[1].p1Items.head.put(104, "Face Care Set");
		items[1].p1Items.face.put(31, "Gradient Sunglasses");
		items[1].p1Items.face.put(32, "Silver Earring");
		items[1].p1Items.face.put(33, "Round Purple Sunglasses");
		items[1].p1Items.face.put(34, "Gold Frame Sunglasses");
		items[1].p1Items.face.put(35, "Stripe Pierce");
		items[1].p1Items.face.put(36, "Silver Heart Pierce");
		items[1].p1Items.face.put(37, "Amethist Start Pierce");
		items[1].p1Items.face.put(38, "Hexagon Pierce");
		items[1].p1Items.face.put(39, "Coin Pierce");
		items[1].p1Items.face.put(40, "Gold Ring Pierce");
		items[1].p1Items.face.put(41, "Crimson Sunglasses");
		items[1].p1Items.face.put(42, "Green Rim Sunglasses");
		items[1].p1Items.face.put(43, "Anchor Pierce");
		items[1].p1Items.face.put(44, "Blue Sunglasses");
		items[1].p1Items.face.put(45, "Heavy Makeup");
		items[1].p1Items.face.put(46, "Mistress Glasses");
		items[1].p1Items.face.put(47, "Tornado Earring");
		items[1].p1Items.face.put(48, "Dusty Makeup");
		items[1].p1Items.face.put(49, "Virtual Fantasy Sunglasses");
		items[1].p1Items.body.put(51, "Leather Bracelet");
		items[1].p1Items.body.put(52, "Egyptian Style Necklace");
		items[1].p1Items.body.put(53, "Stylish Bracelet");
		items[1].p1Items.body.put(54, "Five Ruby Bracelet");
		items[1].p1Items.body.put(55, "Turquoise Bracelet");
		items[1].p1Items.body.put(56, "Python Wristband");
		items[1].p1Items.body.put(57, "Punker Wristband");
		items[1].p1Items.body.put(58, "Bullet Wristband");
		items[1].p1Items.body.put(59, "Leopard Wristband");
		items[1].p1Items.body.put(60, "Star Necklace");
		items[1].p1Items.body.put(61, "Pink Heart Necklace");
		items[1].p1Items.body.put(62, "Green Leaf Necklace");
		items[1].p1Items.body.put(63, "Traditional Necklace");
		items[1].p1Items.body.put(64, "Seven Ball Necklace");
		items[1].p1Items.body.put(65, "Secret Choker");
		items[1].p1Items.body.put(66, "Butterfly Choker");
		items[1].p1Items.body.put(67, "Anchor Necklace");
		items[1].p1Items.body.put(68, "Dual Necklace");
		items[1].p1Items.body.put(69, "White Wings");
		items[1].p1Items.body.put(70, "Black Wings");
		items[1].p1Items.body.put(71, "Gold Necklace");
		items[1].p1Items.body.put(161, "Fukurou (owl)");
		items[1].p1Items.body.put(162, "Butterflies");
		items[1].p1Items.body.put(163, "Sceptre");
		items[1].p1Items.body.put(165, "Long Lace Gloves");
		items[1].p1Items.body.put(167, "Big White Gloves");
		items[1].p1Items.body.put(168, "Sonic The Hedgehog");
		items[1].p1Items.body.put(169, "Koinobori");
		items[1].p1Items.body.put(170, "Big Trophy");
		items[1].p1Items.body.put(171, "Opa-opa");
		items[1].p1Items.body.put(172, "Nights");
		items[1].p1Items.legs.put(81, "Holster");
		items[1].p1Items.legs.put(82, "Survival Knife");
		items[1].p1Items.legs.put(83, "Whip");
		items[1].p1Items.legs.put(84, "Nightstick");
		items[1].p1Items.legs.put(85, "Waist Belt");
		items[1].p1Items.legs.put(86, "Cat Leash");
		items[1].p1Items.legs.put(87, "Waist Pouch");
		items[1].p1Items.legs.put(88, "Leopard Leg Guard");
		items[1].p1Items.legs.put(89, "Alexander (beaver)");
		
		// lau
		items[2].p1Items.head.put(1, "Gold Bead Crown");
		items[2].p1Items.head.put(2, "Silver Coin Crown");
		items[2].p1Items.head.put(3, "Soldier Helmet");
		items[2].p1Items.head.put(4, "Old Head Helmet");
		items[2].p1Items.head.put(5, "Silver Bead Crown");
		items[2].p1Items.head.put(6, "Multi Bead Crown");
		items[2].p1Items.head.put(7, "Gold Coin Crown");
		items[2].p1Items.head.put(8, "Monk-style Hair");
		items[2].p1Items.head.put(9, "Long Bangs");
		items[2].p1Items.head.put(10, "Fleeing Soldier Hair");
		items[2].p1Items.head.put(11, "Swallow Feather Crown");
		items[2].p1Items.head.put(12, "Chef's Hat");
		items[2].p1Items.head.put(13, "Dumpling Hair");
		items[2].p1Items.head.put(14, "Bobbed-style Hair");
		items[2].p1Items.head.put(103, "Chinese Rice Bowl");
		items[2].p1Items.face.put(31, "Eye Glass");
		items[2].p1Items.face.put(32, "Health Mask");
		items[2].p1Items.face.put(33, "Glasses");
		items[2].p1Items.face.put(34, "Sun Glasses");
		items[2].p1Items.face.put(35, "Peking Opera Mask");
		items[2].p1Items.body.put(51, "Tortoise Shoulder Pads");
		items[2].p1Items.body.put(52, "Golden Spike Bracelet");
		items[2].p1Items.body.put(53, "Spiked Shoulder Pads");
		items[2].p1Items.body.put(54, "Silver Shoulder Pads");
		items[2].p1Items.body.put(55, "Shoulder Pads");
		items[2].p1Items.body.put(56, "Prosperity Shoulder Pads");
		items[2].p1Items.body.put(57, "Gold Bracelet");
		items[2].p1Items.body.put(58, "White Tiger Bracelet");
		items[2].p1Items.body.put(59, "Red Gem Bracelet");
		items[2].p1Items.body.put(60, "Marine Spike Bracelet");
		items[2].p1Items.body.put(61, "Phoenix Shoulder Pads");
		items[2].p1Items.body.put(62, "Sea Eagle Shoulder Pads");
		items[2].p1Items.body.put(63, "Purple Shoulder Pads");
		items[2].p1Items.body.put(64, "Ocher Shoulder Pads");
		items[2].p1Items.body.put(65, "Red Bracelet");
		items[2].p1Items.body.put(66, "Blue Bracelet");
		items[2].p1Items.body.put(67, "Gold Spiked Bracelet");
		items[2].p1Items.body.put(68, "Silver Spiked Bracelet");
		items[2].p1Items.body.put(69, "Old Head Shield");
		items[2].p1Items.body.put(70, "Blue Dragon Sword");
		items[2].p1Items.body.put(71, "Machete");
		items[2].p1Items.body.put(72, "Wok");
		items[2].p1Items.body.put(73, "Tora Tiger");
		items[2].p1Items.legs.put(81, "Equivocal Leg Guard");
		items[2].p1Items.legs.put(82, "Orange Leg Guard");
		items[2].p1Items.legs.put(83, "Wooden Leg Guard");
		items[2].p1Items.legs.put(84, "Large Board Red Leg Guard");
		items[2].p1Items.legs.put(85, "Leg Guard");
		items[2].p1Items.legs.put(86, "Large Board Yellow Leg Guard");
		items[2].p1Items.legs.put(87, "Leg Guard");
		items[2].p1Items.legs.put(88, "Homura Leg Guard");
		items[2].p1Items.legs.put(89, "Bronze Leg Guard");
		items[2].p1Items.legs.put(90, "Brass Leg Guard");
		items[2].p1Items.legs.put(91, "Old Head Sword");
		items[2].p1Items.legs.put(92, "Fire Cracker");
		items[2].p1Items.legs.put(93, "Nanchaku");
		items[2].p1Items.legs.put(94, "Waist Towel");
		items[2].p1Items.legs.put(95, "Kitchen Knife");
		items[2].p1Items.legs.put(96, "Ladle");
		items[2].p1Items.legs.put(97, "Cooking Ingredients");
		items[2].p1Items.legs.put(98, "Chinese Kitchen Knife");
		
		// shun
		items[3].p1Items.head.put(1, "Emperor's Hat");
		items[3].p1Items.head.put(2, "Celebration Hat");
		items[3].p1Items.head.put(3, "Red Straw Hat");
		items[3].p1Items.head.put(4, "Pale Blue Sky Hat");
		items[3].p1Items.head.put(5, "Hat of Life");
		items[3].p1Items.head.put(6, "Hat");
		items[3].p1Items.head.put(7, "Hat");
		items[3].p1Items.head.put(8, "Hat");
		items[3].p1Items.head.put(9, "Hat");
		items[3].p1Items.head.put(10, "Hat");
		items[3].p1Items.head.put(11, "Hat");
		items[3].p1Items.head.put(12, "Hat");
		items[3].p1Items.head.put(13, "Hat");
		items[3].p1Items.head.put(14, "Hat");
		items[3].p1Items.head.put(15, "Hat");
		items[3].p1Items.head.put(16, "Mid-parted Hair");
		items[3].p1Items.head.put(17, "Plaited Pony Tail");
		items[3].p1Items.head.put(18, "Hat");
		items[3].p1Items.head.put(19, "Long Tail");
		items[3].p1Items.head.put(20, "Pony Tail");
		items[3].p1Items.head.put(21, "Hat");
		items[3].p1Items.head.put(22, "Hat");
		items[3].p1Items.head.put(103, "Clown Mask");
		items[3].p1Items.face.put(31, "Peking Opera Mask");
		items[3].p1Items.face.put(32, "Fox Mask");
		items[3].p1Items.face.put(33, "Glasses");
		items[3].p1Items.body.put(51, "Bottle");
		items[3].p1Items.body.put(52, "Crest");
		items[3].p1Items.body.put(53, "Dragon Crest");
		items[3].p1Items.body.put(54, "Crest");
		items[3].p1Items.body.put(55, "Crest");
		items[3].p1Items.body.put(56, "Crest");
		items[3].p1Items.body.put(57, "Gourd");
		items[3].p1Items.body.put(58, "Bottle");
		items[3].p1Items.body.put(59, "Yellow Bottle");
		items[3].p1Items.body.put(60, "Bottle");
		items[3].p1Items.body.put(61, "Phoenix Crest");
		items[3].p1Items.body.put(62, "Crest");
		items[3].p1Items.body.put(63, "Vase");
		items[3].p1Items.body.put(64, "Vase");
		items[3].p1Items.body.put(65, "Red Rosary");
		items[3].p1Items.body.put(66, "White Rosary");
		items[3].p1Items.body.put(67, "Autumn Rosary");
		items[3].p1Items.body.put(68, "Wood Warbler Joe");
		items[3].p1Items.body.put(69, "Bottle");
		items[3].p1Items.body.put(70, "Bottle");
		items[3].p1Items.legs.put(81, "Shoes");
		items[3].p1Items.legs.put(82, "Shoes");
		items[3].p1Items.legs.put(83, "Shoes");
		items[3].p1Items.legs.put(84, "Shoes");
		items[3].p1Items.legs.put(85, "Shoes");
		items[3].p1Items.legs.put(86, "Shoes");
		items[3].p1Items.legs.put(87, "Shoes");
		items[3].p1Items.legs.put(88, "Shoes");
		items[3].p1Items.legs.put(89, "Shoes");
		
		// jeffry
		items[4].p1Items.head.put(1, "Pirate's Bandana");
		items[4].p1Items.head.put(2, "Bodyguard's Bandana");
		items[4].p1Items.head.put(3, "Afro Hair");
		items[4].p1Items.head.put(4, "Hyper Afro Hair");
		items[4].p1Items.head.put(5, "Thick Mohican");
		items[4].p1Items.head.put(6, "Viking Helmet");
		items[4].p1Items.head.put(7, "Straw Hat");
		items[4].p1Items.head.put(8, "Heavy Dreads");
		items[4].p1Items.head.put(9, "Indigo Bandana");
		items[4].p1Items.head.put(10, "Skin Head");
		items[4].p1Items.head.put(102, "Devil Mask");
		items[4].p1Items.head.put(103, "Silver Helm");
		items[4].p1Items.head.put(105, "Roman Helm");
		items[4].p1Items.face.put(31, "Round Purple Sunglasses");
		items[4].p1Items.face.put(32, "Silver Frame Sunglasses");
		items[4].p1Items.face.put(33, "Sunglasses (Goggle)");
		items[4].p1Items.face.put(34, "Slim Sunglasses");
		items[4].p1Items.face.put(35, "Pirate Eye Patch");
		items[4].p1Items.face.put(36, "Fishing Sunglasses");
		items[4].p1Items.face.put(37, "Goggles & Snorkels");
		items[4].p1Items.body.put(51, "Ruby Bracelet");
		items[4].p1Items.body.put(52, "Needle Bracelet");
		items[4].p1Items.body.put(53, "Saphire Bracelet");
		items[4].p1Items.body.put(54, "Topaz Bracelet");
		items[4].p1Items.body.put(55, "Emerald Bracelet");
		items[4].p1Items.body.put(56, "Skeleton Necklace");
		items[4].p1Items.body.put(57, "Bone Necklace (Triple)");
		items[4].p1Items.body.put(58, "Wing Heart Tattoo");
		items[4].p1Items.body.put(59, "Love-Love Skull");
		items[4].p1Items.body.put(60, "Skull Tattoo (red)");
		items[4].p1Items.body.put(61, "Tribal Tattoo (red)");
		items[4].p1Items.body.put(62, "Clark Bracelet");
		items[4].p1Items.body.put(63, "Snake Bracelet");
		items[4].p1Items.body.put(64, "Tribal Tattoo (black)");
		items[4].p1Items.body.put(65, "Skull Tattoo (white)");
		items[4].p1Items.body.put(66, "Neck Ring");
		items[4].p1Items.body.put(67, "Shark Necklace");
		items[4].p1Items.body.put(68, "Silver Bracelet");
		items[4].p1Items.body.put(69, "Oxygen Cylinder");
		items[4].p1Items.body.put(70, "Roman Shoulder Pad");
		items[4].p1Items.body.put(71, "Small Shield");
		items[4].p1Items.legs.put(81, "Great Sword");
		items[4].p1Items.legs.put(82, "Double Axe");
		items[4].p1Items.legs.put(83, "Fins");
		items[4].p1Items.legs.put(84, "Roman Leg Guard");
		items[4].p1Items.legs.put(85, "Small Shark");
		
		// pai
		items[5].p1Items.head.put(1, "Queen's Crown");
		items[5].p1Items.head.put(2, "Lily Crown");
		items[5].p1Items.head.put(3, "Jewel Crown");
		items[5].p1Items.head.put(4, "Asian Crown");
		items[5].p1Items.head.put(5, "Peking Opera Mask");
		items[5].p1Items.head.put(6, "Plait Hair");
		items[5].p1Items.head.put(7, "Child Hair");
		items[5].p1Items.head.put(8, "Knitted Cap");
		items[5].p1Items.head.put(9, "People's Cap");
		items[5].p1Items.head.put(10, "Bun Covers");
		items[5].p1Items.head.put(11, "Cutie Hair");
		items[5].p1Items.head.put(12, "Flower Tied Hair");
		items[5].p1Items.head.put(13, "Short Cut");
		items[5].p1Items.head.put(102, "Panda-chan Mask");
		items[5].p1Items.head.put(104, "Chinese Lion Mask");
		items[5].p1Items.face.put(31, "Peking Opera Makeup");
		items[5].p1Items.face.put(32, "Violet Sunglasses");
		items[5].p1Items.face.put(33, "White Sunglasses");
		items[5].p1Items.head.put(34, "Black Eye Shadow");
		items[5].p1Items.head.put(35, "Green Eye Shadow");
		items[5].p1Items.body.put(51, "Crystal Necklace");
		items[5].p1Items.body.put(52, "Lily Bracelet");
		items[5].p1Items.body.put(53, "Bronze Necklace");
		items[5].p1Items.body.put(54, "Rose Necklace");
		items[5].p1Items.body.put(55, "Jade Necklace");
		items[5].p1Items.body.put(56, "Enamel Metal Bracelet");
		items[5].p1Items.body.put(57, "Jewel Bracelet");
		items[5].p1Items.body.put(58, "Ultramarine Bracelet");
		items[5].p1Items.body.put(59, "Red and White Bracelet");
		items[5].p1Items.body.put(60, "Fire Dragon Crest");
		items[5].p1Items.body.put(61, "Floral Crest");
		items[5].p1Items.body.put(62, "Lotus Flower Crest");
		items[5].p1Items.body.put(63, "Dragon Spirit Crest");
		items[5].p1Items.body.put(64, "Gold Flower Crest");
		items[5].p1Items.body.put(65, "Panda-chi");
		items[5].p1Items.body.put(66, "Long Sword");
		items[5].p1Items.body.put(67, "Shield");
		items[5].p1Items.body.put(68, "Gold Threaded Bracelet");
		items[5].p1Items.body.put(69, "Bamboo Grass Necklace");
		items[5].p1Items.body.put(70, "Spindle");
		items[5].p1Items.body.put(71, "Porcelain Necklace");
		items[5].p2Items.legs.put(81, "Knee Armor");
		items[5].p2Items.legs.put(82, "Egyptian Anklet");
		items[5].p2Items.legs.put(83, "Crimson Gem Shin Guards");
		items[5].p2Items.legs.put(84, "Crystal Knee Armor");
		items[5].p1Items.legs.put(85, "Flame Knee Armor");
		items[5].p1Items.legs.put(86, "7-Head Daggers");
		items[5].p1Items.legs.put(87, "Bamboo Anklette");
		items[5].p1Items.legs.put(88, "Blade Rings");
		
		// jacky
		items[6].p1Items.head.put(1, "Leather Cap");
		items[6].p1Items.head.put(2, "Bandana (Blue)");
		items[6].p1Items.head.put(3, "Long Hair");
		items[6].p1Items.head.put(4, "Short Hair");
		items[6].p1Items.head.put(5, "Regent");
		items[6].p1Items.head.put(6, "Sauvage");
		items[6].p1Items.head.put(7, "Red Cap");
		items[6].p1Items.head.put(8, "Flight Cap");
		items[6].p1Items.head.put(9, "Bobbed-style Hair");
		items[6].p1Items.head.put(10, "Sideburns");
		items[6].p1Items.head.put(11, "Bandana (Red)");
		items[6].p1Items.head.put(102, "Racer Helmet");
		items[6].p1Items.head.put(103, "Hero Helmet");
		items[6].p1Items.head.put(104, "Baby Bonnet");
		items[6].p1Items.face.put(31, "Brown Lens Sunglasses");
		items[6].p1Items.face.put(32, "Blow-type Sunglasses");
		items[6].p1Items.face.put(33, "Rim-less Sunglasses");
		items[6].p1Items.face.put(34, "Sunglasses");
		items[6].p1Items.face.put(35, "Blue Lens Sunglasses");
		items[6].p1Items.face.put(36, "Goggle-type Sunglasses");
		items[6].p1Items.face.put(37, "Ear Pierce");
		items[6].p1Items.face.put(38, "Nose Pierce");
		items[6].p1Items.face.put(39, "White Goggles");
		items[6].p1Items.face.put(40, "Flight Goggles");
		items[6].p1Items.face.put(41, "Band Aid");
		items[6].p1Items.body.put(51, "Wing Head Necklace");
		items[6].p1Items.body.put(52, "Silver Edge Pendant");
		items[6].p1Items.body.put(53, "Gold Emblem Pendant");
		items[6].p1Items.body.put(54, "Key Head Pendant");
		items[6].p1Items.body.put(55, "Roller Head Pendant");
		items[6].p2Items.body.put(56, "Back Print (Skull)");
		items[6].p2Items.body.put(57, "Back Print (Fire Soul)");
		items[6].p2Items.body.put(58, "Back Print (Iron Horse)");
		items[6].p2Items.body.put(59, "Back Print (Victory Wings)");
		items[6].p1Items.body.put(60, "Nap Sack");
		items[6].p1Items.body.put(61, "Eagle Emblem");
		items[6].p1Items.body.put(62, "Skull Emblem");
		items[6].p1Items.body.put(63, "Trophy");
		items[6].p1Items.body.put(64, "Silver Axe Pendant");
		items[6].p1Items.body.put(65, "Muffler");
		items[6].p1Items.body.put(65, "Bandage");
		items[6].p1Items.body.put(162, "Sonic the hedgehog");
		items[6].p1Items.body.put(163, "Koinobori");
		items[6].p1Items.body.put(164, "Big Trophy");
		items[6].p1Items.body.put(165, "Opa-opa");
		items[6].p1Items.body.put(166, "Nights");
		items[6].p1Items.legs.put(81, "Brown Leather Boots");	// P1 only
		items[6].p2Items.legs.put(81, "");
		items[6].p1Items.legs.put(82, "High Cut Sneakers (Black)");
		items[6].p2Items.legs.put(82, "");
		items[6].p1Items.legs.put(83, "High Cut Sneakers (Copper)");
		items[6].p2Items.legs.put(83, "");
		items[6].p1Items.legs.put(84, "Black Leather Boots");
		items[6].p2Items.legs.put(84, "");
		items[6].p1Items.legs.put(85, "Chequered Flag");
		items[6].p1Items.legs.put(86, "Waist Bag");
		items[6].p1Items.legs.put(87, "Pocket Case");
		items[6].p1Items.legs.put(88, "Half Chaps");
		items[6].p1Items.legs.put(89, "Spare Gloves");
		items[6].p1Items.legs.put(90, "Nunchaku");
		items[6].p1Items.legs.put(91, "Araguma-chan (raccoon)");
		
		// kage
		items[7].p1Items.head.put(1, "Ninja Headband");
		items[7].p2Items.head.put(1, "Giant Flame Crest");
		items[7].p1Items.head.put(2, "Stylish Hood");
		items[7].p2Items.head.put(2, "Stylish Helmett");
		items[7].p1Items.head.put(3, "Ninja Circlet");
		items[7].p2Items.head.put(3, "Flame Crest");
		items[7].p1Items.head.put(4, "Sided Hair");
		items[7].p1Items.head.put(5, "Samurai Hair");
		items[7].p1Items.head.put(6, "Samurai Helmet");
		items[7].p2Items.head.put(6, "Jet Helmet");
		items[7].p1Items.head.put(7, "Short Hair");
		items[7].p1Items.head.put(8, "Regent Hair");
		items[7].p1Items.head.put(9, "Retired Man's Hair");
		items[7].p1Items.head.put(10, "");
		items[7].p2Items.head.put(10, "Jagged Tail");
		items[7].p1Items.head.put(102, "Scary Mask");
		items[7].p1Items.face.put(31, "Samurai Mask");
		items[7].p1Items.face.put(32, "Night Scope");
		items[7].p1Items.body.put(51, "Spiked Bracelet");
		items[7].p1Items.body.put(52, "Ninja Sword (Double)");
		items[7].p1Items.body.put(53, "Scroll");
		items[7].p1Items.body.put(54, "Twin Scrolls");
		items[7].p1Items.body.put(55, "Jagged Bracelet");
		items[7].p1Items.body.put(56, "Ninja Claws");
		items[7].p1Items.body.put(57, "Gama (Toad)");
		items[7].p1Items.body.put(58, "Samurai Armor");
		items[7].p2Items.body.put(58, "Rocket");
		items[7].p1Items.body.put(59, "Bow and Arrow");
		items[7].p1Items.body.put(60, "Wooden Dummy");
		items[7].p1Items.body.put(61, "Bamboo Hat");
		items[7].p1Items.body.put(62, "Flag1");
		items[7].p1Items.body.put(63, "Flag2");
		items[7].p1Items.body.put(64, "Flag3");
		items[7].p1Items.body.put(65, "Flag4");
		items[7].p1Items.body.put(66, "Flag5");
		items[7].p1Items.body.put(67, "Flag6");
		items[7].p1Items.body.put(68, "Ninja Sword");
		items[7].p1Items.body.put(69, "Great Shuriken");
		items[7].p1Items.body.put(70, "Risu-san (Squirrel)");
		items[7].p1Items.body.put(71, "Usagi-san (Bunny)");
		items[7].p1Items.legs.put(81, "Water Spider");
		items[7].p1Items.legs.put(82, "Masamune Sword");
		items[7].p1Items.legs.put(83, "Preparation Sword");
		items[7].p1Items.legs.put(84, "Triple Shuriken");
		items[7].p1Items.legs.put(85, "Triple Shuriken (Double)");
		items[7].p1Items.legs.put(86, "Rice Ball");
		items[7].p1Items.legs.put(87, "Rice Ball Belt");
		items[7].p1Items.legs.put(88, "Samurai Leg Armor");
		items[7].p2Items.legs.put(88, "Rocket Boots");
		items[7].p1Items.legs.put(89, "Kunai (throwing knives)");
		items[7].p1Items.legs.put(90, "Chained Sickle");
		items[7].p1Items.legs.put(91, "Grappling Hook");
		
		// lion
		items[8].p1Items.head.put(1, "Beanie");
		items[8].p1Items.head.put(2, "Suade Cap");
		items[8].p1Items.head.put(3, "Red Cap (front)");
		items[8].p1Items.head.put(4, "Black Cap (front)");
		items[8].p1Items.head.put(5, "Red Cap (back)");
		items[8].p1Items.head.put(6, "Black Cap (back)");
		items[8].p1Items.head.put(7, "Cowboy Hat (black)");
		items[8].p1Items.head.put(8, "Ten Gallon Hat");
		items[8].p1Items.head.put(9, "Sky Blue Ten Gallon Hat");
		items[8].p1Items.head.put(10, "Loose Strand Hair");
		items[8].p1Items.head.put(11, "High Sense Hair");
		items[8].p1Items.head.put(12, "Smart-look Hair");
		items[8].p1Items.head.put(13, "Skater's Helmet");
		items[8].p2Items.head.put(13, "Biker's Helmet");
		items[8].p1Items.head.put(14, "Leopard Ten Gallon Hat");
		items[8].p2Items.head.put(103, "Mantis Head");
		items[8].p2Items.head.put(105, "Helmet (White)");
		items[8].p2Items.head.put(106, "Hot Blood Helmet");
		items[8].p2Items.head.put(107, "Cool Guy Helmet");
		items[8].p2Items.head.put(108, "Turmeric Helmet");
		items[8].p1Items.face.put(31, "Silver Earring");
		items[8].p1Items.face.put(32, "Pink Goggle Sunglasses");
		items[8].p1Items.face.put(33, "Nose Chain Pierce");
		items[8].p1Items.face.put(34, "Skeleton Earring");
		items[8].p1Items.face.put(35, "Dragon Nail Earring");
		items[8].p1Items.face.put(36, "Yellow Goggle Sunglasses");
		items[8].p1Items.face.put(37, "Sunglasses (Green)");
		items[8].p1Items.face.put(38, "Sharp Glasses");
		items[8].p1Items.face.put(39, "Brown Lens Sunglasses");
		items[8].p1Items.body.put(51, "Metal Leather Choker");
		items[8].p1Items.body.put(52, "Flair Pendant");
		items[8].p1Items.body.put(53, "Skeleton Bone Necklace");
		items[8].p1Items.body.put(54, "Wind Heart Necklace");
		items[8].p1Items.body.put(55, "Skeleton Bones Necklace");
		items[8].p1Items.body.put(56, "Silver Knuckles");
		items[8].p1Items.body.put(57, "Sleeve Pattern (Red)");
		items[8].p1Items.body.put(58, "Sleeve Pattern (Blue)");
		items[8].p1Items.body.put(59, "Sleeve Pattern (Green)");
		items[8].p1Items.body.put(60, "Sleeve Pattern (Red Line)");
		items[8].p1Items.body.put(61, "Sleeve Pattern (Blue Line)");
		items[8].p1Items.body.put(62, "Sleeve Pattern (Green Line)");
		items[8].p1Items.body.put(63, "Biker's Goggles");
		items[8].p1Items.body.put(64, "Chain Pendant");
		items[8].p1Items.body.put(65, "Skateboard");
		items[8].p2Items.body.put(65, "Biker's Rucksack");
		items[8].p1Items.legs.put(66, "Text Books");
		items[8].p1Items.legs.put(81, "Inline Skates");
		items[8].p1Items.legs.put(82, "Wallet Chain");
		items[8].p1Items.legs.put(83, "High Cut Sneakers");
		items[8].p2Items.legs.put(83, "Shift Putt");
		items[8].p1Items.legs.put(84, "OneBull-chan (bulldog)");
		items[8].p1Items.legs.put(85, "Knife");
		items[8].p1Items.legs.put(86, "Skateboard Knee Guards");
		items[8].p2Items.legs.put(86, "Biker's Knee Guards");
		
		// wolf
		items[9].p1Items.head.put(1, "Western Hat");
		items[9].p1Items.head.put(2, "Long Hair");
		items[9].p1Items.head.put(3, "Wolf Hat");
		items[9].p1Items.head.put(4, "Indian Hat");
		items[9].p1Items.head.put(5, "Pig Tails");
		items[9].p1Items.head.put(6, "Mohican");
		items[9].p1Items.head.put(7, "Skin Head");
		items[9].p1Items.head.put(8, "Crew Cut");
		items[9].p1Items.head.put(9, "Wide Indian Hat");
		items[9].p1Items.head.put(10, "Black Bandana");
		items[9].p1Items.head.put(11, "Wolf-chan");
		items[9].p1Items.head.put(102, "Pro-Wrestling Mask (Black)");
		items[9].p1Items.head.put(103, "Pro-Wrestling Mask (Enji)");
		items[9].p1Items.head.put(104, "Dandy Daddy");
		items[9].p1Items.head.put(105, "Party Goods");
		items[9].p1Items.head.put(106, "ZigZag Mask");
		items[9].p1Items.head.put(108, "Pro-Wrestling Mask (Fire Tiger)");
		items[9].p1Items.head.put(109, "Iron Mask");
		items[9].p1Items.face.put(31, "Round Purple Sunglasses");
		items[9].p1Items.face.put(32, "Silver Frame Sunglasses");
		items[9].p1Items.face.put(33, "Goggle-type Sunglasses");
		items[9].p1Items.face.put(34, "Slim Sunglasses");
		items[9].p1Items.face.put(35, "Face Painting");
		items[9].p1Items.face.put(36, "Beard");
		items[9].p1Items.face.put(37, "Devil Painting");
		items[9].p1Items.face.put(38, "No Makeup");
		items[9].p1Items.face.put(39, "Solid Sunglasses");
		items[9].p1Items.body.put(51, "Wrist Bandage");
		items[9].p2Items.body.put(51, "");
		items[9].p1Items.body.put(52, "Red Fury Towel");
		items[9].p1Items.body.put(53, "Wolf Head Tattoo");
		items[9].p1Items.body.put(54, "Full Bandage");
		items[9].p2Items.body.put(54, "");
		items[9].p1Items.body.put(55, "Wrist Band");
		items[9].p2Items.body.put(55, "");
		items[9].p1Items.body.put(56, "Half-Finger Glove");
		items[9].p2Items.body.put(56, "");
		items[9].p1Items.body.put(57, "Green Tube Necklace");
		items[9].p2Items.body.put(57, "");
		items[9].p1Items.body.put(58, "Tribal Tattoo");
		items[9].p1Items.body.put(59, "Tribal Tattoo 2");
		items[9].p1Items.body.put(60, "Snake Bracelet");
		items[9].p1Items.body.put(61, "Spike Bracelet");
		items[9].p1Items.body.put(62, "Clark Bracelet");
		items[9].p1Items.body.put(63, "Double Bracelet");
		items[9].p1Items.body.put(64, "Tribal Tattoo (Black)");
		items[9].p1Items.body.put(65, "Tribal Tattoo 2 (Black)");
		items[9].p1Items.body.put(66, "Ring Bracelet");
		items[9].p1Items.body.put(67, "Double Bracelet (Ruby)");
		items[9].p1Items.body.put(68, "Champion's Towel");
		items[9].p2Items.body.put(69, "Dream Catcher");
		items[9].p2Items.body.put(70, "Bead Necklace");
		items[9].p2Items.body.put(71, "Nail Necklace");
		items[9].p2Items.body.put(72, "Knit Necklace");
		items[9].p1Items.body.put(73, "Black Gloves");
		items[9].p2Items.body.put(73, "");
		items[9].p1Items.legs.put(81, "Tomahawk");
		items[9].p1Items.legs.put(82, "High Drum");
		items[9].p1Items.legs.put(83, "Chain");
		items[9].p1Items.legs.put(84, "Champion's Belt");
		items[9].p1Items.legs.put(85, "Leg Warmer");
		items[9].p1Items.legs.put(86, "Fringe");
		
		// aoi
		items[10].p1Items.head.put(1, "Kabuki Star (Silver)");
		items[10].p1Items.head.put(2, "Kabuki Star (Red)");
		items[10].p1Items.head.put(3, "Kabuki Star (Blue)");
		items[10].p1Items.head.put(4, "Kabuki Star (Green)");
		items[10].p1Items.head.put(5, "Kabuki Star (Yellow)");
		items[10].p1Items.head.put(6, "Hood (Blue)");
		items[10].p1Items.head.put(7, "Hood (Purple)");
		items[10].p1Items.head.put(8, "Hood (Red)");
		items[10].p1Items.head.put(9, "White Silk Headband");
		items[10].p1Items.head.put(10, "Red-White Silk Headband");
		items[10].p1Items.head.put(11, "Japanese Ponytail");
		items[10].p1Items.head.put(12, "Short Style");
		items[10].p1Items.head.put(13, "Court Noble's Hat");
		items[10].p1Items.head.put(14, "Stylish Hair");
		items[10].p1Items.head.put(15, "Volume Curls");
		items[10].p1Items.head.put(16, "Town Girl Hair");
		items[10].p1Items.head.put(102, "Kitty Ears");
		items[10].p1Items.head.put(103, "Ribbon Bow");
		items[10].p1Items.face.put(31, "Flower Earring");
		items[10].p1Items.face.put(32, "Horned Demon Mask");
		items[10].p1Items.face.put(33, "Demon Mask");
		items[10].p1Items.face.put(34, "Fierce God Mask");
		items[10].p1Items.face.put(35, "White Mask");
		items[10].p1Items.face.put(36, "Silver Snow Earring");
		items[10].p1Items.face.put(37, "Flower Earring");
		items[10].p1Items.face.put(38, "Eye Glasses");
		items[10].p1Items.body.put(51, "Large Fan");
		items[10].p1Items.body.put(52, "Gorgeous Large Fan");
		items[10].p1Items.body.put(53, "Back Knife");
		items[10].p1Items.body.put(54, "White Wave Necklace");
		items[10].p1Items.body.put(55, "Gold Necklace");
		items[10].p1Items.body.put(56, "Crescent Bead Necklace");
		items[10].p1Items.body.put(57, "Umbrella");
		items[10].p1Items.body.put(58, "Sunflower Brooch");
		items[10].p1Items.body.put(59, "Peony Brooch");
		items[10].p1Items.body.put(60, "3-string Instrument");
		items[10].p1Items.body.put(61, "Goldfish Pack");
		items[10].p1Items.body.put(62, "Fan");
		items[10].p1Items.legs.put(81, "Concealed Knife");
		items[10].p1Items.legs.put(82, "Butterfly Pattern");
		items[10].p1Items.legs.put(83, "Pattern");
		items[10].p1Items.legs.put(84, "Pattern");
		items[10].p1Items.legs.put(85, "Lotus Flower Pattern");
		items[10].p1Items.legs.put(86, "Pattern");
		items[10].p1Items.legs.put(87, "Nature Pattern");
		items[10].p1Items.legs.put(88, "Pattern");
		items[10].p1Items.legs.put(89, "Snow-white Flower Pattern");
		items[10].p1Items.legs.put(90, "PuraPura Neko-chan");
		items[10].p1Items.legs.put(91, "PuraPura Usagi-chan");
		items[10].p1Items.legs.put(92, "Arrival Package");
		items[10].p1Items.legs.put(93, "Payment Stick");
		items[10].p1Items.legs.put(94, "Large Bell");
		items[10].p1Items.legs.put(95, "Hand Drum");
		
		// lei-fei
		items[11].p1Items.head.put(1, "Hat1");
		items[11].p1Items.head.put(2, "Hat2");
		items[11].p2Items.head.put(2, "Hat3");
		items[11].p1Items.head.put(3, "Hat4");
		items[11].p1Items.head.put(4, "Hat5");
		items[11].p1Items.head.put(5, "Circlet");
		items[11].p1Items.head.put(6, "Monk Hair");
		items[11].p1Items.head.put(7, "Chinese Hair");
		items[11].p1Items.head.put(8, "3-style Hair");
		items[11].p1Items.head.put(9, "73 Parts");
		items[11].p1Items.head.put(10, "Bamboo Hat");
		items[11].p1Items.head.put(11, "Hair Bun");
		items[11].p1Items.head.put(12, "Long Rose Hair");
		items[11].p1Items.head.put(13, "Gold Circlet");
		items[11].p1Items.head.put(14, "Child Hair");
		items[11].p1Items.head.put(103, "Elephant Head");
		items[11].p1Items.head.put(105, "Talisman Hat");
		items[11].p1Items.head.put(106, "Chinese Lion Mask");
		items[11].p1Items.face.put(31, "Low Moon Earring");
		items[11].p1Items.face.put(32, "Water Blade Earring");
		items[11].p1Items.face.put(33, "Blind Fold");
		items[11].p1Items.face.put(34, "Round Glasses");
		items[11].p1Items.body.put(51, "Rosary");
		items[11].p1Items.body.put(52, "Rosary Bracelet");
		items[11].p1Items.body.put(53, "Comma-shaped Bead Necklace");
		items[11].p2Items.body.put(53, "Silver Arrow-head Necklace");
		items[11].p1Items.body.put(54, "Necklace");
		items[11].p1Items.body.put(55, "Scented Wood Necklace");
		items[11].p1Items.body.put(56, "Scrap Metal Bracelet");
		items[11].p1Items.body.put(57, "Green Bracelet");
		items[11].p1Items.body.put(58, "Golden Bracelet");
		items[11].p2Items.body.put(58, "Bracelet of Great Strength");
		items[11].p1Items.body.put(59, "Stone Bracelet");
		items[11].p1Items.body.put(60, "Trident");
		items[11].p1Items.body.put(61, "Sword");
		items[11].p1Items.body.put(62, "Shoulder Bag");
		items[11].p1Items.body.put(63, "Necklace");
		items[11].p1Items.body.put(64, "Moon Fang");
		items[11].p1Items.body.put(65, "Silver Rim Bracelet");
		items[11].p1Items.body.put(66, "Wooden Bracelet");
		items[11].p1Items.body.put(67, "Rosary");
		items[11].p1Items.legs.put(81, "Alligator Skin Shin Guard");
		items[11].p1Items.legs.put(82, "Foundation Shin Guard");
		items[11].p1Items.legs.put(83, "Blue Dragon Shin Guard");
		items[11].p1Items.legs.put(84, "Steel Shin Guard");
		items[11].p1Items.legs.put(85, "Wooden Shin Guard");
		items[11].p1Items.legs.put(86, "Thoughtful Shin Guard");
		items[11].p1Items.legs.put(87, "Dragon Scale Shin Guard");
		items[11].p1Items.legs.put(88, "Red Shin Guard");
		items[11].p1Items.legs.put(89, "Black Lizard Shin Guard");
		items[11].p1Items.legs.put(90, "Flute");
		items[11].p1Items.legs.put(91, "Moon Fang Blades");
		items[11].p1Items.legs.put(92, "Emperor's Shin Guard");
		items[11].p1Items.legs.put(93, "My Monkey");
		
		// vanessa
		items[12].p1Items.head.put(1, "Army Beret");
		items[12].p1Items.head.put(2, "Cool Short Cut");
		items[12].p1Items.head.put(3, "Long Pony Tail");
		items[12].p1Items.head.put(4, "Dread Hair");
		items[12].p1Items.head.put(5, "Cowboy Hat");
		items[12].p1Items.head.put(6, "Beanie");
		items[12].p1Items.head.put(7, "Something Helmet");
		items[12].p1Items.head.put(8, "GI Cut");
		items[12].p1Items.head.put(9, "Bob Hair");
		items[12].p1Items.head.put(10, "Armor Helmet");
		items[12].p1Items.head.put(11, "Camouflage Bandana");
		items[12].p1Items.head.put(14, "Marines Beret");
		items[12].p1Items.head.put(15, "Wolf Head Hat");
		items[12].p1Items.head.put(16, "High Bunches Hair");
		items[12].p1Items.head.put(17, "Long Curly Hair");
		items[12].p1Items.head.put(18, "Camouflage Cap");
		items[12].p1Items.head.put(19, "Peaked Cap");
		items[12].p1Items.head.put(20, "Santa Hat");
		items[12].p1Items.head.put(21, "Halloween Hat");
		items[12].p1Items.head.put(22, "Crown");
		items[12].p1Items.head.put(102, "Daruma Mask");
		//items[12].p1Items.head.put(103, "Hockey Mask");	// not working
		
		items[12].p1Items.face.put(31, "Goggles");
		items[12].p1Items.face.put(32, "Yellow Sports Sunglasses");
		items[12].p1Items.face.put(33, "Oldies Sunglasses");
		items[12].p1Items.face.put(34, "Gas Mask");
		items[12].p1Items.face.put(35, "Eye Mask");
		items[12].p1Items.face.put(36, "Pink Taft Earring");
		items[12].p1Items.face.put(37, "Blue Crystal Earring");
		items[12].p1Items.face.put(38, "Gold Circlet");
		items[12].p1Items.face.put(39, "Sapphire Circlet");
		items[12].p1Items.face.put(40, "Night Vision Goggles");
		items[12].p1Items.face.put(41, "Headset");
		items[12].p1Items.face.put(42, "Camouflage Face Paint");
		items[12].p1Items.face.put(43, "Full Vision Gas Mask");
		items[12].p1Items.face.put(44, "Aqua Eye Shadow");
		items[12].p1Items.face.put(45, "Red & Yellow Eye Shadow");
		items[12].p1Items.face.put(46, "Feather Eye Mask");
		
		items[12].p1Items.body.put(51, "Binoculars");
		items[12].p1Items.body.put(52, "Rifle");
		items[12].p1Items.body.put(53, "Medal Necklace");
		items[12].p1Items.body.put(54, "Leather Choker");
		items[12].p1Items.body.put(55, "Triple Gold Choker");
		items[12].p1Items.body.put(56, "Dog Tags");
		items[12].p1Items.body.put(57, "Bow Gun");
		items[12].p1Items.body.put(58, "Radio");
		items[12].p1Items.body.put(59, "Butterfly Tattoo");
		items[12].p2Items.body.put(59, "Sleeve Crest");
		items[12].p1Items.body.put(60, "Arabesque Tattoo");
		items[12].p2Items.body.put(60, "Sleeve Crest");
		items[12].p1Items.body.put(61, "Rose Tattoo");
		items[12].p2Items.body.put(61, "Sleeve Crest");
		items[12].p1Items.body.put(62, "Dragon Tattoo");
		items[12].p2Items.body.put(62, "Sleeve Crest");
		items[12].p1Items.body.put(63, "Eagle Tattoo");
		items[12].p2Items.body.put(63, "Sleeve Crest");
		items[12].p1Items.body.put(64, "Parachute Napsack");
		items[12].p1Items.body.put(65, "Sniper Rifle");
		items[12].p1Items.body.put(66, "Shot Gun");
		items[12].p1Items.body.put(67, "Vulcan Machinegun");
		items[12].p1Items.body.put(68, "Fukurou (owl)");
		// FT only?
		items[12].p1Items.body.put(69, "Big Bear");
		items[12].p1Items.body.put(70, "Little Bear");
		items[12].p1Items.body.put(71, "Risu-san (Squirrel)");
		items[12].p1Items.body.put(72, "Usagi-san (Bunny)");
		items[12].p1Items.body.put(73, "Gama (Toad)");
		items[12].p1Items.body.put(74, "Tora Tiger");
		items[12].p1Items.body.put(75, "Panda-chi");
		items[12].p1Items.body.put(76, "Wood Warbler Joe");
		items[12].p1Items.body.put(77, "ChoCho (Butterflies)");
		items[12].p1Items.body.put(78, "Mini Helicopter");
		items[12].p1Items.body.put(79, "Rocket Launcher");
		items[12].p1Items.body.put(161, "Big White Gloves");
		items[12].p1Items.body.put(162, "Flame thrower");
		items[12].p1Items.body.put(163, "Sonic the hedgehog");
		items[12].p1Items.body.put(164, "Koinobori");
		items[12].p1Items.body.put(165, "Big Trophy");
		items[12].p1Items.body.put(166, "Opa-opa");
		items[12].p1Items.body.put(167, "Nights");

		items[12].p1Items.legs.put(81, "Hand Grenades");
		items[12].p1Items.legs.put(82, "Bazooka");
		items[12].p1Items.legs.put(83, "Jungle Knife");
		items[12].p1Items.legs.put(84, "Survival Pack");
		items[12].p1Items.legs.put(85, "Water Bottles");
		items[12].p1Items.legs.put(86, "Rope");
		items[12].p1Items.legs.put(87, "Tonfa");
		items[12].p1Items.legs.put(88, "Araguma-chan (Raccoon)");
		items[12].p1Items.legs.put(89, "Risu-san (Squirrel)");
		items[12].p1Items.legs.put(90, "Small Shark");
		items[12].p1Items.legs.put(91, "OneBull-chan (Bulldog)");
		
		// TODO dural
		//items[13].p1Items.head.put(1, "");
		//items[13].p1Items.face.put(31, "");
		//items[13].p1Items.body.put(51, "");
		//items[13].p1Items.legs.put(81, "");
		
		// goh
		items[14].p1Items.head.put(1, "Jet Hair");
		items[14].p1Items.head.put(2, "Super Jet Hair");
		items[14].p1Items.head.put(3, "Short Hair");
		items[14].p1Items.head.put(4, "Soft Mohican");
		items[14].p1Items.head.put(5, "Poochi Pony Tail");
		items[14].p1Items.head.put(6, "Long Bangs");
		items[14].p1Items.head.put(7, "Corn Rolls");
		items[14].p1Items.head.put(8, "Baseball Cap");
		items[14].p1Items.head.put(9, "Bandana");
		items[14].p1Items.head.put(10, "Baseball Cap");
		items[14].p1Items.head.put(11, "Hunting Cap");
		items[14].p1Items.head.put(12, "Hood");
		items[14].p2Items.head.put(12, "Hood-less");
		items[14].p1Items.head.put(13, "Hood (Fire)");
		items[14].p1Items.head.put(14, "Net Cap");
		items[14].p1Items.head.put(103, "Mutant Helmet");
		items[14].p1Items.face.put(31, "Rimless Sunglasses");
		items[14].p1Items.face.put(32, "Gold Frame Sunglasses");
		items[14].p1Items.face.put(33, "Division Sunglasses");
		items[14].p1Items.face.put(34, "Silver Sunglasses");
		items[14].p1Items.face.put(35, "Ear Piercing");
		items[14].p1Items.face.put(36, "Skeleton Pierce");
		items[14].p1Items.face.put(37, "Face Paint");
		items[14].p1Items.face.put(38, "Clown Mask");
		items[14].p1Items.face.put(39, "Half Mask");
		items[14].p1Items.body.put(51, "Large Necklace");
		items[14].p1Items.body.put(52, "Sunlight Necklace");
		items[14].p1Items.body.put(53, "Seed Necklace");
		items[14].p1Items.body.put(54, "Leather Choker");
		items[14].p1Items.body.put(55, "Sleeve (Yellow/Black)");
		items[14].p2Items.body.put(55, "Chest Tattoo (Big Eyes)");
		items[14].p1Items.body.put(56, "Sleeve (Name)");
		items[14].p2Items.body.put(56, "Scratch Marks");
		items[14].p1Items.body.put(57, "Sleeve (English)");
		items[14].p2Items.body.put(57, "Chest Tattoo (Flame Flower)");
		items[14].p1Items.body.put(58, "Back Pattern (No.1)");
		items[14].p1Items.body.put(59, "Back Pattern (Skull)");
		items[14].p1Items.body.put(60, "Back Pattern (Spirit)");
		items[14].p1Items.body.put(61, "Back Pattern (J6)");
		items[14].p1Items.body.put(62, "Chain Saw");
		items[14].p1Items.body.put(63, "Nail Bat");
		items[14].p1Items.body.put(64, "Patchy Clothing");
		items[14].p1Items.body.put(65, "Gold Medal");
		items[14].p1Items.body.put(66, "Barb Wire");
		items[14].p1Items.legs.put(81, "Puchin Bee");
		items[14].p1Items.legs.put(82, "Bandage (White)");
		items[14].p2Items.legs.put(82, "Silver Sneakers");
		items[14].p1Items.legs.put(83, "Bandage (Blue)");
		items[14].p2Items.legs.put(83, "Bare Feet");
		items[14].p1Items.legs.put(84, "Emblem");
		items[14].p1Items.legs.put(85, "Winding Chain");
		items[14].p1Items.legs.put(86, "Tied Belt");
		items[14].p2Items.legs.put(86, "Orange Pants");
		
		// brad
		items[15].p1Items.head.put(1, "Long Hair");
		items[15].p1Items.head.put(2, "Pony Tail");
		items[15].p1Items.head.put(3, "Hard Mohican");
		items[15].p1Items.head.put(4, "Straight Hair");
		items[15].p1Items.head.put(5, "Head Monkon (red)");
		items[15].p2Items.head.put(5, "Felt Hat (black)");
		items[15].p1Items.head.put(6, "Head Monkon (blue)");
		items[15].p2Items.head.put(6, "Felt Hat (grey)");
		items[15].p1Items.head.put(7, "Head Monkon (green)");
		items[15].p2Items.head.put(7, "Straw Hat");
		items[15].p2Items.head.put(8, "Ten Gallon Hat");
		items[15].p2Items.head.put(9, "Ten Gallon Hat (animal)");
		items[15].p2Items.head.put(10, "Army Beret");
		items[15].p1Items.head.put(104, "Mask");
		items[15].p1Items.head.put(105, "Head Gear (black)");
		items[15].p1Items.head.put(106, "Head Gear (red)");
		items[15].p1Items.head.put(107, "Head Gear (blue)");
		items[15].p1Items.face.put(31, "Yellow Lens Sunglasses");
		items[15].p1Items.face.put(32, "Round Sunglasses");
		items[15].p1Items.face.put(33, "Blue Frame Sunglasses");
		items[15].p1Items.face.put(34, "Red Frame Sunglasses");
		items[15].p1Items.face.put(35, "Nose Pierce");
		items[15].p1Items.face.put(36, "Ear Piercing");
		items[15].p1Items.face.put(37, "Black Pearl Pierce");
		items[15].p1Items.face.put(38, "Asian Pierce");
		items[15].p1Items.face.put(39, "Nail Pierce");
		items[15].p1Items.face.put(40, "Feather Pierce");
		items[15].p1Items.face.put(41, "Nose Pierce (chain)");
		items[15].p1Items.face.put(42, "Nose Pierce (eyeball)");
		items[15].p1Items.face.put(43, "Nose Pierce (skeleton)");
		items[15].p1Items.body.put(51, "Asian Necklace");
		items[15].p1Items.body.put(52, "Silver Head Pendant");
		items[15].p1Items.body.put(53, "Square Pendant");
		items[15].p1Items.body.put(54, "Sun Necklace");
		items[15].p1Items.body.put(55, "Triangle Necklace");
		items[15].p1Items.body.put(56, "Key-head Necklace");
		items[15].p1Items.body.put(57, "Dog-tag Necklace");
		items[15].p1Items.body.put(58, "Jade Bracelet");
		items[15].p1Items.body.put(59, "Weave Bracelet");
		items[15].p1Items.body.put(60, "Sanskrit Bracelet");
		items[15].p1Items.body.put(61, "Silver Work Bracelet");
		items[15].p1Items.body.put(62, "Round-type Bracelet");
		items[15].p1Items.body.put(63, "Silver Pipe Bracelet");
		items[15].p1Items.body.put(64, "Rosary Bracelet");
		items[15].p1Items.body.put(65, "Spikey Rosary Bracelet");
		items[15].p1Items.body.put(66, "Guitar Case");
		items[15].p1Items.body.put(67, "ChoCho (Butterflies)");
		items[15].p1Items.body.put(68, "T-Shirt (white)");
		items[15].p2Items.body.put(68, "Net Shirt");
		items[15].p1Items.body.put(69, "T-Shirt (grey)");
		items[15].p2Items.body.put(69, "Style Shirt");
		items[15].p1Items.body.put(70, "Tank Top (white)");
		items[15].p1Items.body.put(71, "Tank Top (black)");
		items[15].p1Items.body.put(72, "Bone Wing Tattoo");
		items[15].p1Items.body.put(73, "Tribal Tattoo (chest)");
		items[15].p1Items.body.put(74, "Tribal Tattoo (arm)");
		items[15].p2Items.body.put(74, "Aloha Shirt (black)");
		items[15].p1Items.body.put(75, "Arm Monkon (red)");
		items[15].p2Items.body.put(75, "Aloha Shirt (animal)");
		items[15].p1Items.body.put(76, "Arm Monkon (blue)");
		items[15].p2Items.body.put(76, "Aloha Shirt (blue)");
		items[15].p1Items.body.put(77, "Arm Monkon (green)");
		items[15].p2Items.body.put(77, "Aloha Shirt (light blue)");
		items[15].p1Items.legs.put(81, "Shin Guards (black)");
		items[15].p2Items.legs.put(81, "Belt (tea)");
		items[15].p1Items.legs.put(82, "Shin Guards (red)");
		items[15].p2Items.legs.put(82, "Belt (black)");
		items[15].p1Items.legs.put(83, "Shin Guards (blue)");
		items[15].p2Items.legs.put(83, "Hand Gun");
		items[15].p1Items.legs.put(84, "Shin Guards (supporter type)");
		items[15].p2Items.legs.put(84, "Revolver Gun");
		items[15].p1Items.legs.put(85, "Trunks (animal)");
		items[15].p2Items.legs.put(85, "Wallet Strap");
		items[15].p1Items.legs.put(86, "Trunks (green)");
		items[15].p2Items.legs.put(86, "Wallet Chain");
		items[15].p1Items.legs.put(87, "Rose Bouquet");
		items[15].p1Items.legs.put(88, "Champion Belt");
		items[15].p1Items.legs.put(89, "Bananas");
	}
	
	public Map<Integer, String> getHeadItems(int character, boolean p2) {
		CharItems charItems = items[character];
		Map<Integer, String> labels = new TreeMap<>();
		labels.putAll(charItems.p1Items.head);
		if (p2)
			labels.putAll(charItems.p2Items.head);
		labels.put(0, "");
		return labels;
	}
	
	public Map<Integer, String> getFaceItems(int character, boolean p2) {
		CharItems charItems = items[character];
		Map<Integer, String> labels = new TreeMap<>();
		labels.putAll(charItems.p1Items.face);
		if (p2)
			labels.putAll(charItems.p2Items.face);
		labels.put(0, "");
		return labels;
	}
	
	public Map<Integer, String> getBodyItems(int character, boolean p2) {
		CharItems charItems = items[character];
		Map<Integer, String> labels = new TreeMap<>();
		labels.putAll(charItems.p1Items.body);
		if (p2)
			labels.putAll(charItems.p2Items.body);
		labels.put(0, "");
		return labels;
	}
	
	public Map<Integer, String> getLegsItems(int character, boolean p2) {
		CharItems charItems = items[character];
		Map<Integer, String> labels = new TreeMap<>();
		labels.putAll(charItems.p1Items.legs);
		if (p2)
			labels.putAll(charItems.p2Items.legs);
		labels.put(0, "");
		return labels;
	}
	
	private static String getCharPath(int character) {
		if (character < 0 || character >= charPath.length)
			return "none";
		else
			return charPath[character];
	}

	public static String getItemImage(ServletContext servletContext, int character, boolean p2, int item) {
		if (item == 0)
			return new ServletContextResource(servletContext, "images/items/blank.png").getPath();
		String charPath = getCharPath(character);
		String itemNum = Integer.toString(item);
		if (itemNum.length() < 3)
			itemNum = "000".substring(0, 3 - itemNum.length()) + itemNum;
		ServletContextResource res = new ServletContextResource(servletContext,
				"images/items/" + charPath + "/" + itemNum + (p2 ? "a" : "") + "_" + charPath + ".gif");
		if (res.isReadable())
			return res.getPath();
		if (p2) {
			res = new ServletContextResource(servletContext,
					"images/items/" + charPath + "/" + itemNum + "_" + charPath + ".gif");
			if (res.isReadable())
				return res.getPath();
		}
		res = new ServletContextResource(servletContext, "images/items/nopic.gif");
		return res.getPath();
	}
}
