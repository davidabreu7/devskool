package com.devsuperior.dslearn.dto;

public class CourseDto {

    private Long id;
    private String name;
    private String imgUri;
    private String imgGrayUri;

    public CourseDto() {
    }

    public CourseDto(Long id, String name, String imgUri, String imgGrayUri) {
        this.id = id;
        this.name = name;
        this.imgUri = imgUri;
        this.imgGrayUri = imgGrayUri;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getImgGrayUri() {
        return imgGrayUri;
    }

    public void setImgGrayUri(String imgGrayUri) {
        this.imgGrayUri = imgGrayUri;
    }
}
