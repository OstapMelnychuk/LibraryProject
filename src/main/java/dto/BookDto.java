package dto;

public class BookDto {
  private String title;
  private String description;
  private String dateOfPublishment;
  private AuthorDto author;
  private boolean isAvailable;

  /**
   * Default constructor
   */
  public BookDto() {
  }

  /**
   * Constructor with parameters
   * @param title title of book
   * @param description description of book
   * @param dateOfPublishment date of publishment of biik
   */
  public BookDto(String title, String description, String dateOfPublishment) {
    this.title = title;
    this.description = description;
    this.dateOfPublishment = dateOfPublishment;
  }

  /**
   * Constructor with parameters
   * @param title title of book
   * @param description description of book
   * @param dateOfPublishment date of publishment of biik
   * @param author author of book
   * @param isAvailable true if book exist and false if doesn't
   */
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
