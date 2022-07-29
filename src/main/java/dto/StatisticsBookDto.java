package dto;

public class StatisticsBookDto extends BookDto {
    int count;
    String date;

    public StatisticsBookDto(BookDto bookDto, StatisticsBookDto statisticsBookDto) {
        super(bookDto);

        try {
            this.date = statisticsBookDto.date;
            this.count = statisticsBookDto.count;
        } catch (NullPointerException e) {
            this.date = "";
            this.count = 0;
        }
    }

    public StatisticsBookDto(String date, int count) {
        this.date = date;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AvarageBookDto{" +
                ", date='" + date + '\'' +
                '}';
    }
}
