package models;

public class Book {
    private Integer id;
    private String title;
    private String description;
    private String dateOfPublishment;
    private int count;
    private Author author;
    private boolean isAvailable;

    public Book() {
    }

    public Book(Integer id, String title, String description, String dateOfPublishment) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateOfPublishment = dateOfPublishment;
    }

    public Book(Integer id, String title, String description, String dateOfPublishment, int count, Author author, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateOfPublishment = dateOfPublishment;
        this.count = count;
        this.author = author;
        this.isAvailable = isAvailable;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfPublishment() {
        return dateOfPublishment;
    }

    public void setDateOfPublishment(String dateOfPublishment) {
        this.dateOfPublishment = dateOfPublishment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!id.equals(book.id)) return false;
        if (!title.equals(book.title)) return false;
        if (!description.equals(book.description)) return false;
        return dateOfPublishment.equals(book.dateOfPublishment);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + dateOfPublishment.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateOfPublishment='" + dateOfPublishment + '\'' +
                '}';
    }
}
