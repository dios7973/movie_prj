package Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentList {

    private List<Student> students = new ArrayList<>();

    public StudentList() {
        load();
    }

    private void load() {
        String sql = "SELECT id, name, department, email FROM members";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("email")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void printAll() {
        for (Student s : students) {
            System.out.println(
                    s.getId() + " " +
                            s.getName() + " " +
                            s.getDepartment() + " " +
                            s.getEmail()
            );
        }
    }

    public Student getStudent(int index) {
        if (index < 0 || index >= students.size()) return null;
        return students.get(index);
    }
}
