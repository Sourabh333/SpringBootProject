package com.example.SpringBootProject.Service;

import com.example.SpringBootProject.Entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SpringBootProject.Repository.ImageRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository repository;
    public List<Image> getAllImage()
    {
        List<Image> newImageList = new ArrayList<>();
        List<Image> images = repository.findAll();
        for (Image image :images) {
            String imageBase64 = Base64.getEncoder().encodeToString(image.getImage());
            image.setBase64image(imageBase64);
            newImageList.add(image);
        }
        System.out.println(images);
        System.out.println(newImageList);

        return images;
    }

    public void uploadImage(String imageName, String imageDescription, String imageOwner, MultipartFile file) {
        Image image = new Image();git remote add origin https://github.com/Sourabh333/SpringBootProject.git
        image.setImageName(imageName);
        image.setImageDescription(imageDescription);
        image.setImageOwner(imageOwner);
        try {
            image.setImage(file.getBytes());
        }
        catch(IOException ex)
        {
            System.out.println(ex);
            return ;
        }
        repository.save(image);
    }
    public Image getImagebyId(int id){
        Optional<Image> image = repository.findById(id);
        String imageBase64 = Base64.getEncoder().encodeToString(image.get().getImage());
        image.get().setBase64image(imageBase64);
        System.out.println(image.get().getBase64image());

        return image.get();
    }
    public String updateImage(int id,String imageName, String imageDescription, String imageOwner, MultipartFile file)
    {
        Image image = repository.findById(id).get();
        if(image!=null)
        {
            if(!imageName.isEmpty()) image.setImageName(imageName);
            if(!imageDescription.isEmpty()) image.setImageDescription(imageDescription);
            if(!imageOwner.isEmpty()) image.setImageOwner(imageOwner);
            try {
                if (file.getBytes().length > 0)
                {
                    System.out.println(file.getBytes().length);
                    image.setImage(file.getBytes());
                }
            }
            catch (IOException ex)
            {
                System.out.println(ex);
                return "invalid image";
            }
            repository.save(image);
            return "Saved succesfully";
        }
        else
        {
            return  "Error while saving the data";
        }
    }

}
