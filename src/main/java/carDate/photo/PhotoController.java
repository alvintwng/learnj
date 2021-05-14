package carDate.photo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

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
	
	@Autowired
	private PhotoRepository photoRepo;
	
	@RequestMapping("/photos")
	public String ShowUsersList(Model model) {
		java.util.List<Photo> photos = photoRepo.findAll();
		model.addAttribute("photos", photos);
		
		return "photo/photos";
	}
	
	@GetMapping("/img/photoNew")
	public String ShowUserform(Model model) {
		model.addAttribute("vehPhoto", new Photo());
		return "photo/vehPhotoNew";
	}
	
	@PostMapping("/img/photoSave")
	public RedirectView savePhoto(Photo photo, @RequestParam("image") MultipartFile multipartFile
			, HttpServletRequest request) throws IOException {

		String[] detailIDs = request.getParameterValues("detailID");
		String[] detailNames = request.getParameterValues("detailName");
		String[] detailValues = request.getParameterValues("detailValue");
		
		for (int i = 0; i< detailNames.length; i++) {
			if(detailIDs != null && detailIDs.length > 0)
				photo.setDetails(Integer.valueOf(detailIDs[i]), detailNames[i], detailValues[i]);
			else {
				photo.addDetails(detailNames[i], detailValues[i]);
			}
		}
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		photo.setPhoto(fileName);
         
        Photo savedPhoto = photoRepo.save(photo);
 
        String uploadDir = "photos";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        log.warn("=====> img/photoSave: " + savedPhoto.getPhotosImagePath());
        return new RedirectView("/photos", true);
	}
	
	@PostMapping("/img/photoDescSave")
	public RedirectView savePhoto(Photo photo, HttpServletRequest request, Model model) throws IOException {

		String[] detailIDs = request.getParameterValues("detailID");
		String[] detailNames = request.getParameterValues("detailName");
		String[] detailValues = request.getParameterValues("detailValue");

		for (int i = 0; i< detailNames.length; i++) {
			if(detailIDs != null && detailIDs.length > 0)
				photo.setDetails(Integer.valueOf(detailIDs[i]), detailNames[i], detailValues[i]);
			else {
				photo.addDetails(detailNames[i], detailValues[i]);
			}
		}
		
		String image = request.getParameter("image");
        photo.setPhoto(image);
        
        try {
            photoRepo.save(photo);
            log.warn("=====> img/photoDescSave: " + photo.getPhotosImagePath());
        } catch (Exception e) {
            System.out.println("Something went wrong, contact the admin..");
            log.warn("=====> photoRepo.save(photo) => Something went wrong to: ");
            log.error(e.toString());
            model.addAttribute("error", e.toString());
        }

        return new RedirectView("/photos", true);
	}
	
	@GetMapping("/img/photoEdit/{id}")
	public String ShowEditForm(@PathVariable("id") int id, Model model) {
		Photo photo =   photoRepo.findById(id).get();
		model.addAttribute("vehPhoto", photo);
		
		log.info("=====> img/photoEdit/{id}: " + photo.getPhotoId());
		return "photo/vehPhotoEdit";
	}
	
	@GetMapping("/img/photoDelete/{id}")
	public String deletePhoto(@PathVariable("id") int id, Model model) {
		photoRepo.deleteById(id);;
		log.warn("=====> img/photoDelete/{id}: " + id);
		return "redirect:/photos";
	}

	/*Carousel pending, still working on*/
	@RequestMapping("/img/photos2")
	public String ShowUsersCoru(Model model) {
		java.util.List<Photo> photos = photoRepo.findAll();
		model.addAttribute("photos", photos);
		
		return "photo/photos2";
	}	
}
