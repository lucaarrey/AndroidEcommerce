package sophia.com.ecommerce2;

/**
 * Created by archimede on 06/06/17.
 */

public class Category {

    private String imagePath;
    private String title;
    private String subTitle;

    public Category(String imagePath, String title, String subTitle) {
        this.imagePath = imagePath;
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @Override
    public String toString() {
        return "Category{" +
                "imagePath='" + imagePath + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (imagePath != null ? !imagePath.equals(category.imagePath) : category.imagePath != null)
            return false;
        if (title != null ? !title.equals(category.title) : category.title != null) return false;
        return subTitle != null ? subTitle.equals(category.subTitle) : category.subTitle == null;

    }

    @Override
    public int hashCode() {
        int result = imagePath != null ? imagePath.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (subTitle != null ? subTitle.hashCode() : 0);
        return result;
    }
}
