package models;

public class Book {
  private String title;
  private String description;
  private String dateOfPublishment;

  public Book() {
  }

  public Book(String title, String description, String dateOfPublishment) {
    this.title = title;
    this.description = description;
    this.dateOfPublishment = dateOfPublishment;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Book book = (Book) o;

    if (!title.equals(book.title)) return false;
    if (!description.equals(book.description)) return false;
    return dateOfPublishment.equals(book.dateOfPublishment);
  }

  @Override
  public int hashCode() {
    int result = title.hashCode();
    result = 31 * result + description.hashCode();
    result = 31 * result + dateOfPublishment.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Book{" +
            "title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", dateOfPublishment='" + dateOfPublishment + '\'' +
            '}';
  }
}
