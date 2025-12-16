import java.sql.*;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/student_db"; // DB 이름 확인
        String user = "root";              // 본인 DB 아이디
        String password = "1111";      // 본인 DB 비번

        String sql = """
            SELECT
              s.student_name,
              c.course_name,
              sc.midterm,
              sc.final,
              sc.assignment,
              (sc.midterm + sc.final + sc.assignment) AS total,
              ROUND((sc.midterm + sc.final + sc.assignment) / 3.0, 2) AS average
            FROM student s
            JOIN enrollment e ON s.student_id = e.student_id
            JOIN course c ON e.course_id = c.course_id
            JOIN score sc ON e.enroll_id = sc.enroll_id
            WHERE s.student_name = ?
            ORDER BY c.course_id;
            """;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = conn.prepareStatement(sql)) {

            System.out.println("DB 연결 성공!");
            pst.setString(1, "이지성"); // 조회할 학생 이름

            try (ResultSet rs = pst.executeQuery()) {
                System.out.println("학생 | 과목 | 중간 | 기말 | 과제 | 총점 | 평균");
                System.out.println("-------------------------------------------------");
                boolean has = false;
                while (rs.next()) {
                    has = true;
                    String student = rs.getString("student_name");
                    String course = rs.getString("course_name");
                    int mid = rs.getInt("midterm");
                    int fin = rs.getInt("final");
                    int asg = rs.getInt("assignment");
                    int total = rs.getInt("total");
                    double avg = rs.getDouble("average");
                    System.out.printf("%s | %s | %d | %d | %d | %d | %.2f%n",
                            student, course, mid, fin, asg, total, avg);
                }
                if (!has) {
                    System.out.println("조회 결과가 없습니다. 학생 이름/데이터 확인하세요.");
                }
            }

        } catch (SQLException e) {
            System.err.println("DB 연결/쿼리 실패:");
            e.printStackTrace();
        }
    }
}
