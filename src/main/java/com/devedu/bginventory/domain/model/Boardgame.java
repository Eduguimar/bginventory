package com.devedu.bginventory.domain.model;

import jakarta.persistence.*;

import java.util.*;

@Entity(name = "tb_boardgame")
public class Boardgame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    Integer year;
    boolean isExpansion;
    boolean isStandalone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_bg_category",
            joinColumns = @JoinColumn(name = "bg_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    List<Category> categories = new ArrayList<>();

    public Boardgame() {}

    public Boardgame(Long id, String name, String description, Integer year, boolean isExpansion, boolean isStandalone, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.isExpansion = isExpansion;
        this.isStandalone = isStandalone;
        this.categories = categories;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public boolean isExpansion() {
        return isExpansion;
    }

    public void setExpansion(boolean expansion) {
        isExpansion = expansion;
    }

    public boolean isStandalone() {
        return isStandalone;
    }

    public void setStandalone(boolean standalone) {
        isStandalone = standalone;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boardgame boardgame = (Boardgame) o;
        return Objects.equals(id, boardgame.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
