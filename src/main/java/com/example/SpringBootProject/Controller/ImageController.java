package com.example.SpringBootProject.Controller;

import com.example.SpringBootProject.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import  org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
    //service object
    @Autowired
    private ImageService service;
    @GetMapping("/")
    public String loadDashboard()
    {
        return "dashboard";
    }
    @GetMapping("/addImage")
    public String uploadImageForm()
    {
        return "imageForm";
    }
    @GetMapping("/singleImage")
    public String getImageByIdView(){ return "fetchIamgeById"; }
    @GetMapping("/updateImageForm")
    public String UpdateImage(){ return "updateForm"; }
    @GetMapping("/getAllImages")
    public String getAllImage(Model model)
    {
        model.addAttribute("imageList",service.getAllImage());
        return "imageList";
    }
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("imageName") String imageName,
                            @RequestParam("imageDescription") String imageDescription,
                            @RequestParam("imageOwner") String imageOwner,
                            @RequestPart("image")MultipartFile file, Model model)
    {
        service.uploadImage(imageName,imageDescription,imageOwner,file);
        model.addAttribute("message","Image Uploaded Successfully");
        return "response";

    }

    @PostMapping("/updateImage")
    public String updateImage(
            @RequestParam("imageId") int id,
            @RequestParam("imageName") String imageName,
            @RequestParam("imageDescription") String imageDescription,
            @RequestParam("imageOwner") String imageOwner,
            @RequestPart("image")MultipartFile file,Model model)
    {
        service.updateImage(id,imageName,imageDescription,imageOwner,file);
        model.addAttribute("message","Image details updated");
        return "response";
    }

    @GetMapping("/imageById")
    public String getImageById(@RequestParam("id") int id,Model model)
    {
        model.addAttribute("imageDetail",service.getImagebyId(id));
        return "displayImage";
    }
    @PutMapping("updateImage/{id}")
    public String updateImage2( @PathVariable(value = "id",required = true) int id,
                                @RequestParam(value = "imageName",required = false) String imageName,
                                @RequestParam(value = "imageDescription",required = false) String imageDescription,
                                @RequestParam(value = "imageOwner",required = false) String imageOwner,
                                @RequestPart(value = "image" ,required = false)MultipartFile file,Model model)
    {
        service.updateImage(id,imageName,imageDescription,imageOwner,file);
        model.addAttribute("message","Image details updated");
        return "response";
    }

}
