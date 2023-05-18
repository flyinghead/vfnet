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

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.flyinghead.vf4.VanillaAccessCode;
import com.flyinghead.vf4.db.IDbService;

@Controller
public class UploadCardController {
	@Autowired
    private IDbService dbService;

	@GetMapping({"/", "/upload-card"})
	public String uploadCard() {
	    return "upload-card";
	}

	@PostMapping("/upload-card")
	public String uploadCard(@RequestParam("card") MultipartFile card, ModelMap modelMap) {
		try {
			byte[] bytes = card.getBytes();
			if (bytes.length != 128)
				throw new RuntimeException("Invalid card file");
			int cardId;
			if (bytes[37] == 0 && bytes[38] == 0) {
				// Vanilla
				// card id is made from big-endian int at offset 4 for vanilla
				String cardIdStr = VanillaAccessCode.makeCode(((bytes[4] & 0xff) << 24) | ((bytes[5] & 0xff) << 16) | ((bytes[6] & 0xff) << 8) | (bytes[7] & 0xff));
				if (cardIdStr == null)
					throw new RuntimeException("Internal error");
				cardId = Integer.parseInt(cardIdStr);
			}
			else
			{
				// Evo, FT
				// card id is at offset 39 for ft and evo
				cardId = (bytes[39] & 0xff) | ((bytes[40] & 0xff) << 8) | ((bytes[41] & 0xff) << 16);
				if (cardId == 0)
					throw new RuntimeException("Unregistered card");
			}
			if (dbService.getPlayer(cardId) == null)
				throw new RuntimeException("Player not found");
			return "redirect:player?id=" + String.valueOf(cardId);
		} catch (IOException e) {
			modelMap.put("message", "Upload failed");
			return "upload-card";
		} catch (Exception e) {
			modelMap.put("message", e.getMessage());
			return "upload-card";
		}
	}

}
