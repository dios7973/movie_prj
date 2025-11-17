package movie;

public class Reservation {

    public boolean reserve9(Movie movie, int count) {
        if (movie.reserveSeat(count)) {
            System.out.println("✅ 예매 성공: " + movie.getTitle() + ", 수량: " + count);
            System.out.println("서연경");
            return true;
        } else {
            System.out.println("❌ 예매 실패: 좌석 부족");
            return false;
        }
    }

    public void reserve1842() {
        System.out.println("예매 완료!_이지성");
    }

    public void reserve(Movie selectedMovie, int i) {
    }
}