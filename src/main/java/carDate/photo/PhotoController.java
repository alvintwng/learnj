package carDate.photo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PhotoController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
//	@Autowired
//	private PhotoRepository photoRepo;
	
//	@RequestMapping("/img/photos")
//	public String ShowUsersList(Model model) {
//		java.util.List<photo> photos = photoRepo.findAll();
//		model.addAttribute("photos", photos);
//		
//		return "img/photos";
//	}
//	
//	@GetMapping("/img/photoNew")
//	public String ShowUserform(Model model) {
//		model.addAttribute("vehPhoto", new photo());
//		return "img/vehPhotoNew";
//	}
//	
//	@PostMapping("/img/photoSave")
//	public RedirectView savePhoto(photo photo, @RequestParam("image") MultipartFile multipartFile) throws IOException {
//		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//		photo.setPhoto(fileName);
//         
//        photo savedPhoto = photoRepo.save(photo);
// 
//        String uploadDir = "img";
// 
//        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//         
//        return new RedirectView("/img/photos", true);
//	}
//	@GetMapping("/img/photoEdit/{id}")
//	public String ShowEditForm(@PathVariable("id") int id, Model model) {
//		photo photo =   photoRepo.findById(id).get();
//		model.addAttribute("vehPhoto", photo);
//
//		return "img/vehPhotoNew";
//	}	
}
