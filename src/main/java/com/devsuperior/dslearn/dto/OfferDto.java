package com.devsuperior.dslearn.dto;

public class OfferDto {

    private Long id;
    private String name;
    private String imgUri;
    private String imgGrayUri;
    private CourseDto course;

    public OfferDto() {
    }

    public OfferDto(Long id, String name, String imgUri, String imgGrayUri, CourseDto course) {
        this.id = id;
        this.name = name;
        this.imgUri = imgUri;
        this.imgGrayUri = imgGrayUri;
        this.course = course;
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

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }
}
