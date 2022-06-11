package com.app.nutritionistfavourites.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_nutritionist_favorite")
public class NutritionistFavourite {

    private int id;
    private int fdcId;
    private String description;
    private String publishedDate;
    private String brandOwner;
    private double score;
    private String username;

    public NutritionistFavourite() {
    }

    public NutritionistFavourite(int id, int fdcId, String description, String publishedDate, String brandOwner, double score, String username) {
        this.id = id;
        this.fdcId = fdcId;
        this.description = description;
        this.publishedDate = publishedDate;
        this.brandOwner = brandOwner;
        this.score = score;
        this.username = username;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @Column(name = "fdcId", nullable = false)
    public int getFdcId() {
        return fdcId;
    }

    @Column(name = "description", nullable = true)
    public String getDescription() {
        return description;
    }

    @Column(name = "publishedDate", nullable = true)
    public String getPublishedDate() {
        return publishedDate;
    }

    @Column(name = "brandOwner", nullable = true)
    public String getBrandOwner() {
        return brandOwner;
    }

    @Column(name = "score", nullable = true)
    public double getScore() {
        return score;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFdcId(int fdcId) {
        this.fdcId = fdcId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setBrandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
