package dto;

public class BookDto {
  private String title;
  private String description;
  private String dateOfPublishment;
  private AuthorDto author;
  private boolean isAvailable;

  public BookDto() {
  }

  public BookDto(String title, String description, String dateOfPublishment) {
    this.title = title;
    this.description = description;
    this.dateOfPublishment = dateOfPublishment;
  }

  public BookDto(String title, String description, String dateOfPublishment, AuthorDto author, boolean isAvailable) {
    this.title = title;
    this.description = description;
    this.dateOfPublishment = dateOfPublishment;
    this.author = author;
    this.isAvailable = isAvailable;
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

  public AuthorDto getAuthor() {
    return author;
  }

  public void setAuthor(AuthorDto author) {
    this.author = author;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
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
