package com.example.SpringBootProject.Entity;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "imageDetails")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String imageName;
    private String imageOwner;
    private String imageDescription;
    @Lob
    @Column(name = "image", columnDefinition="MEDIUMBLOB")
    private byte[] image;
    @JsonInclude
    @Transient
    private String base64image;
    public Image( String imageName, String imageOwner, String imageDescription,byte[] image){
        this.imageName = imageName;
        this.imageOwner = imageOwner;
        this.imageDescription = imageDescription;
        this.image = image;
    }
    public Image() {
    }

    public int getId() {
        return id;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageOwner() {
        return imageOwner;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageOwner(String imageOwner) {
        this.imageOwner = imageOwner;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getBase64image() {
        return base64image;
    }

    public void setBase64image(String base64image) {
        this.base64image = base64image;
    }
}
