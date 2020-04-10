package com.ssh.bbc.messcategory.domain;

import com.ssh.bbc.util.parameterverify.VerifyError;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Table(name = "TmessCategory")
@Entity
public class TmessCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private int categoryId;

    @Column(name = "Category")
    @NotEmpty(message = VerifyError.CATEGORY_NOT_NULL)
    @Size(min = 2 ,max = 4, message = VerifyError.CATEGORY_NAME_SIZE)
    private String category;

    @Column(name = "CategoryOwner")
    @NotEmpty(message = VerifyError.CATEGORY_OWNER_NOT_NULL)
    private String categoryOwner;

    public TmessCategory() {

    }

    public TmessCategory(String category, String categoryOwner) {
        this.category = category;
        this.categoryOwner = categoryOwner;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryOwner() {
        return categoryOwner;
    }

    public void setCategoryOwner(String categoryOwner) {
        this.categoryOwner = categoryOwner;
    }
}
