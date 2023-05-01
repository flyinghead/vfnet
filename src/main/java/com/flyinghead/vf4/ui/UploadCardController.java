package com.flyinghead.vf4.ui;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
			if (bytes[37] == 0 && bytes[38] == 0)
				throw new RuntimeException("VF4 vanilla cards not supported yet");
			// card id is at offset 39 for ft and evo
			int cardId = (bytes[39] & 0xff) | ((bytes[40] & 0xff) << 8) | ((bytes[41] & 0xff) << 16);
			if (cardId == 0)
				throw new RuntimeException("Unregistered card");
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
