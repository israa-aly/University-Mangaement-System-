package Database;

import oracle.sql.StructDescriptor;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.Array;
import java.sql.CallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class DataAccessLayer {

    Connection con = null;
    PreparedStatement prepare;
    ResultSet rs;
    boolean x;

 
    public boolean checkLogin(String username, String password) throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String sql = "SELECT * FROM admins where email =? and password =?";
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, username);
                prepare.setString(2, password);
                rs = prepare.executeQuery();
                if (rs.next()) {
                    // correct username and password
                    //System.out.println("Login Success");
                    return true;
                } else {
                    
                    System.out.println("Login Failed");
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public int getAdminData(String userName) {
        int id = -1; // Initialize to null

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "select id from admins  where email = ?";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setString(1, userName);
            ResultSet searchResult = searchStatement.executeQuery();

            if (searchResult.next()) {
                id = searchResult.getInt("id");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }
        public String getFacultyName(int adminId) {
        String id = ""; // Initialize to null

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "select fac_name from faculty where id =(select faculty_id from admins where id =?) ";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, adminId);
            ResultSet searchResult = searchStatement.executeQuery();

            if (searchResult.next()) {
                id = searchResult.getString("fac_name");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public boolean addStudent(int adminId, String fname, String lname, String email, String pass, String city, String dob, String phone, String dept, String gender) throws SQLException {
        try {
            System.out.println("Hello, World!");
            System.out.println(dept);
            int result = -1;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String sql = "{call Add_Student(?,?, ?, ?, ?, ?,?, ?, ?,?)}";
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, fname);
                prepare.setString(2, lname);
                prepare.setString(3, dob);
                prepare.setString(4, phone);
                prepare.setString(5, city);
                prepare.setString(6, email);
                prepare.setString(7, pass);
                prepare.setString(8, dept);
                prepare.setString(9, gender);
                prepare.setInt(10, adminId);

                result = prepare.executeUpdate();
                if (result != -1) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean addProf(int adminId, String fname, String lname, String email, String pass, String city, String dob, String phone, String dept, int salary, String degree) throws SQLException {
        try {
            int result = -1;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String sql = "{call Add_professor(?,?, ?, ?, ?, ?,?,?,?,?,?)}";
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, fname);
                prepare.setString(2, lname);
                prepare.setString(3, dob);
                prepare.setString(4, phone);
                prepare.setString(5, city);
                prepare.setString(6, email);
                prepare.setString(7, pass);
                prepare.setString(8, dept);
                prepare.setInt(9, salary);
                prepare.setString(10, degree);
                prepare.setInt(11, adminId);

                result = prepare.executeUpdate();
                if (result != -1) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean addDeptCourse(String name, int id) throws SQLException {
        try {
            System.out.println(name);
            int result = -1;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String sql = "{call AddDeptCourse(?,?)}";
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, name);
                prepare.setInt(2, id);
                

                result = prepare.executeUpdate();
                if (result != -1) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean addDept(int adminId,String name) throws SQLException {
        try {
            int result = -1;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String sql = "{call Add_Dept(?, ?)}";
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, name);
               // prepare.setInt(2, head);
                 prepare.setInt(2, adminId);
                result = prepare.executeUpdate();
                if (result != -1) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean addCourse(int adminId,String name, int hours, int max) throws SQLException {
        try {
            int result = -1;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String sql = "{call Add_Course(?,?,?,?)}";
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, name);
                prepare.setInt(2, hours);
                prepare.setInt(3, max);
                 prepare.setInt(4, adminId);
                result = prepare.executeUpdate();
                if (result != -1) {

                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean addStudentCourse(String name, int id, int year, int sem) throws SQLException {
        try {
            int result = -1;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String sql = "{call AddStudentCourse(?,?,?,?)}";
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, name);
                prepare.setInt(2, id);
                prepare.setInt(3, year);
                prepare.setInt(4, sem);
                result = prepare.executeUpdate();
                if (result != -1) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public JSONObject searchStudent(int stdId, int adminId) throws SQLException {
        JSONObject item = null; // Initialize to null

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "select s.id, fName,lname,password,email,dob,city,phone,s.dept_id,d.dept_name,GPA from student2 s left outer join department2 d on s.dept_id= d.id  where s.id = ? and s.faculty_id=(select faculty_id from admins where id=?)";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, stdId);
            searchStatement.setInt(2, adminId);
            ResultSet searchResult = searchStatement.executeQuery();

            if (searchResult.next()) {
                item = new JSONObject()
                        .put("ID", searchResult.getInt("id"))
                        .put("FNAME", searchResult.getString("fname"))
                        .put("LNAME", searchResult.getString("lname"))
                        .put("CITY", searchResult.getString("city"))
                        .put("GPA", searchResult.getInt("GPA"))
                        .put("EMAIL", searchResult.getString("email"))
                        .put("PASS", searchResult.getString("password"))
                        .put("DOB", searchResult.getString("dob"))
                        .put("DNAME", searchResult.getString("dept_name"))
                        .put("PHONE", searchResult.getString("phone"))
                        .put("DID", searchResult.getInt("dept_id"));
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return item;
    }

    public JSONObject searchDept(int adminId,int deptId) throws SQLException {
        JSONObject item = null; // Initialize to null

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "SELECT d.ID, d.dept_name, d.head_id, t.fname || ' ' || t.lname AS head_name FROM department2 d LEFT JOIN teacher2 t ON t.id = d.head_id where d.id=? and d.faculty_id=(select faculty_id from admins where id=?) order by d.id ";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, deptId);
            searchStatement.setInt(2, adminId);
            ResultSet searchResult = searchStatement.executeQuery();

            if (searchResult.next()) {
                item = new JSONObject()
                        .put("ID", searchResult.getInt("id"))
                        .put("NAME", searchResult.getString("dept_name"))
                        .put("HEAD", searchResult.getInt("head_id"))
                        .put("HNAME", searchResult.getString("head_name"));

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    public JSONObject searchCourse(int adminId,int deptId) throws SQLException {
        JSONObject item = null; // Initialize to null

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "SELECT c.id, c.Name, c.credit_hours, c.max_mark,d.dept_name, COUNT(s.student_id) AS student_count FROM course2 c LEFT JOIN student_course2 s ON s.course_id = c.id left join Course_department cd on c.id=cd.course_id left join department2 d on cd.department_id=d.id WHERE c.id = ? and c.faculty_id=(select faculty_id from admins where id=?) GROUP BY c.id, c.Name, c.credit_hours, c.max_mark,d.dept_name";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, deptId);
             searchStatement.setInt(2, adminId);
            ResultSet searchResult = searchStatement.executeQuery();

            if (searchResult.next()) {
                item = new JSONObject()
                        .put("ID", searchResult.getInt("id"))
                        .put("NAME", searchResult.getString("name"))
                        .put("HOURS", searchResult.getInt("credit_hours"))
                        .put("MAX", searchResult.getInt("max_mark"))
                        .put("NUM", searchResult.getInt("student_count"))
                        .put("GPA", "0")
                        .put("DEPTNAME", searchResult.getString("dept_name"));

            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    public JSONObject searchProfessor(int stdId, int adminId) throws SQLException {
        JSONObject item = null; // Initialize to null

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "select s.ID, fName,lname,password,email,dob,city,phone,salary,degree,s.dept_id,d.dept_name from teacher2 s,department2 d where s.id = ? and s.dept_id= d.id and s.faculty_id=(select faculty_id from admins where id=?)";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, stdId);
            searchStatement.setInt(2, adminId);
            ResultSet searchResult = searchStatement.executeQuery();

            if (searchResult.next()) {
                item = new JSONObject()
                        .put("ID", searchResult.getInt("id"))
                        .put("FNAME", searchResult.getString("fname"))
                        .put("LNAME", searchResult.getString("lname"))
                        .put("CITY", searchResult.getString("city"))
                        .put("EMAIL", searchResult.getString("email"))
                        .put("PASS", searchResult.getString("password"))
                        .put("DOB", searchResult.getString("dob"))
                        .put("DNAME", searchResult.getString("dept_name"))
                        .put("PHONE", searchResult.getString("phone"))
                        .put("SALARY", searchResult.getInt("salary"))
                        .put("DEGREE", searchResult.getString("degree"))
                        .put("DID", searchResult.getInt("dept_id"));
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return item;
    }

    public JSONArray getAllstd(int adminId) throws SQLException {
        JSONObject item = null; // Initialize to null
        JSONArray prof = new JSONArray();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "SELECT s.ID, s.fName, s.lname, s.password, s.email, s.dob, s.city, s.phone, s.dept_id, d.dept_name, "
                    + "NVL(calculate_avg_gpa_for_student(s.ID), 0) AS GPA "
                    + "FROM student2 s "
                    + "LEFT OUTER JOIN department2 d ON s.dept_id = d.id where s.faculty_id =(select faculty_id from admins where id=?)";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, adminId);

            ResultSet searchResult = searchStatement.executeQuery();

            while (searchResult.next()) {
                // Get student details
                int studentId = searchResult.getInt("ID");
                String fName = searchResult.getString("fName");
                String lname = searchResult.getString("lname");
                String city = searchResult.getString("city");
                String email = searchResult.getString("email");
                String password = searchResult.getString("password");
                String dob = searchResult.getString("dob");
                String deptName = searchResult.getString("dept_name");
                String phone = searchResult.getString("phone");
                int deptId = searchResult.getInt("dept_id");
                double gpa = searchResult.getDouble("GPA");

                // Create JSON object with student details including GPA
                item = new JSONObject()
                        .put("ID", studentId)
                        .put("FNAME", fName)
                        .put("LNAME", lname)
                        .put("CITY", city)
                        .put("GPA", gpa)
                        .put("EMAIL", email)
                        .put("PASS", password)
                        .put("DOB", dob)
                        .put("DNAME", deptName)
                        .put("PHONE", phone)
                        .put("DID", deptId);

                // Add JSON object to the array
                prof.put(item);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prof;
    }

    public JSONArray getAllprof(int adminId) throws SQLException {
        JSONObject item = null; // Initialize to null
        JSONArray prof = new JSONArray();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "select s.ID, fName,lname,password,email,dob,city,phone,salary,degree,s.dept_id,d.dept_name from teacher2 s left outer join department2 d on s.dept_id= d.id where s.faculty_id=(select faculty_id from admins where id=?)";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, adminId);

            ResultSet searchResult = searchStatement.executeQuery();
            while (searchResult.next()) {
                item = new JSONObject()
                        .put("ID", searchResult.getInt("id"))
                        .put("FNAME", searchResult.getString("fname"))
                        .put("LNAME", searchResult.getString("lname"))
                        .put("CITY", searchResult.getString("city"))
                        .put("EMAIL", searchResult.getString("email"))
                        .put("PASS", searchResult.getString("password"))
                        .put("DOB", searchResult.getString("dob"))
                        .put("DNAME", searchResult.getString("dept_name"))
                        .put("PHONE", searchResult.getString("phone"))
                        .put("SALARY", searchResult.getInt("salary"))
                        .put("DEGREE", searchResult.getString("degree"))
                        .put("DID", searchResult.getInt("dept_id"));
                prof.put(item);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prof;
    }

    public JSONArray getAllCourses(int adminId) throws SQLException {
        JSONObject item = null; // Initialize to null
        JSONArray prof = new JSONArray();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "SELECT c.id, c.Name, c.credit_hours, c.max_mark, COUNT(s.student_id) AS student_count FROM course2 c LEFT JOIN student_course2 s ON s.course_id = c.id where c.faculty_id =(select faculty_id from admins where id=?)GROUP BY c.id,s.course_id, c.Name, c.credit_hours, c.max_mark ORDER BY c.id";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
                        searchStatement.setInt(1, adminId);

            ResultSet searchResult = searchStatement.executeQuery();

            // Create a CallableStatement for calling the PL/SQL function
            String callFunction = "{? = call calculate_avg_gpa(?)}";
            try (CallableStatement stmt = connect.prepareCall(callFunction)) {
                while (searchResult.next()) {
                    // Set the course_id for which you want to calculate the average GPA
                    int courseID = searchResult.getInt("id");

                    stmt.setInt(2, courseID);

                    stmt.registerOutParameter(1, Types.NUMERIC);
                    stmt.execute();

                    double avgGPA = stmt.getDouble(1);

                    item = new JSONObject()
                            .put("ID", searchResult.getInt("id"))
                            .put("NAME", searchResult.getString("name"))
                            .put("HOURS", searchResult.getInt("credit_hours"))
                            .put("MAX", searchResult.getInt("max_mark"))
                            .put("NUM", searchResult.getInt("student_count"))
                            .put("GPA", avgGPA);
                           // .put("DEPTNAME", searchResult.getString("dept_name"));

                    prof.put(item);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prof;
    }

    public JSONArray getAlldept(int adminId) throws SQLException {
        JSONObject item = null; // Initialize to null
        JSONArray prof = new JSONArray();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "SELECT d.ID, d.dept_name, d.head_id, t.fname || ' ' || t.lname AS head_name FROM department2 d LEFT JOIN teacher2 t ON t.id = d.head_id where d.faculty_id=(select faculty_id from admins where id=?) order by d.id  ";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
           searchStatement.setInt(1, adminId);
            ResultSet searchResult = searchStatement.executeQuery();
            while (searchResult.next()) {
                item = new JSONObject()
                        .put("ID", searchResult.getInt("id"))
                        .put("NAME", searchResult.getString("dept_name"))
                        .put("HEAD", searchResult.getInt("head_id"))
                        .put("HNAME", searchResult.getString("head_name"));
                prof.put(item);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prof;
    }

    public JSONArray getDeptStdCount(int adminId) throws SQLException {
        JSONObject item = null; // Initialize to null
        JSONArray departmentCounts = new JSONArray();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "SELECT d.dept_name, COUNT(s.dept_id) AS student_count FROM department2 d LEFT JOIN student2 s ON s.dept_id = d.id where d.faculty_id=(select faculty_id from admins where id =?) GROUP BY d.dept_name ORDER BY d.dept_name";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
                        searchStatement.setInt(1, adminId);

            ResultSet searchResult = searchStatement.executeQuery();

            while (searchResult.next()) {
                item = new JSONObject()
                        .put("DEPTNAME", searchResult.getString("dept_name"))
                        .put("STUDENT_COUNT", searchResult.getInt("student_count"));
                departmentCounts.put(item);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return departmentCounts;
    }

    public JSONArray getHead(int adminId) throws SQLException {
        JSONObject item = null; // Initialize to null
        JSONArray prof = new JSONArray();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "SELECT id FROM teacher2 WHERE id NOT IN (SELECT head_id FROM department2 WHERE head_id IS NOT NULL) and faculty_id=(select faculty_id from admins where id=?)";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, adminId);
            ResultSet searchResult = searchStatement.executeQuery();
            while (searchResult.next()) {
                item = new JSONObject()
                .put("ID", searchResult.getInt("id"));
                prof.put(item);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prof;
    }

    public JSONArray studentCourses(int adminId,int stdId) throws SQLException {
        JSONObject item;
        JSONArray courses = new JSONArray(); // Initialize as an empty array

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "select s.course_id, c.Name, s.mark,s.year,c.max_mark, s.semester,  g.grade from student_course2 s left outer join grades g  on g.id=s.grade_id left outer join course2 c on s.course_id=c.id  where s.student_id=? and (select faculty_id from student2 where id=?)=(select faculty_id from admins where id=?)";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, stdId);
            searchStatement.setInt(2, stdId);
            searchStatement.setInt(3, adminId);
            ResultSet searchResult = searchStatement.executeQuery();

            while (searchResult.next()) {
                item = new JSONObject()
                        .put("COURSE_ID", searchResult.getInt("course_id"))
                        .put("COURSE_NAME", searchResult.getString("Name"))
                        .put("YEAR", searchResult.getInt("year"))
                        .put("SEM", searchResult.getInt("semester"))
                        .put("MARK", searchResult.getInt("mark"))
                        .put("MAX", searchResult.getInt("max_mark"))
                        .put("GRADE", searchResult.getString("grade"));
                courses.put(item);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

    public JSONArray courseStudents(int stdId) throws SQLException {
        JSONObject item;
        JSONArray courses = new JSONArray(); // Initialize as an empty array

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "select c.max_mark, s.id, s.fname ||' '||s.lname as name,d.dept_name,sc.mark, sc.year,sc.semester,g.grade from student_course2 sc left outer join course2 c on sc.course_id=c.id left outer join  grades g  on g.id=sc.grade_id  join student2 s  on sc.student_id=s.id left outer join department2 d on d.id =s.dept_id  where sc.course_id=? ";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, stdId);
            ResultSet searchResult = searchStatement.executeQuery();

            while (searchResult.next()) {
                item = new JSONObject()
                        .put("ID", searchResult.getInt("id"))
                        .put("NAME", searchResult.getString("Name"))
                        .put("YEAR", searchResult.getInt("year"))
                        .put("SEM", searchResult.getInt("semester"))
                        .put("GRADE", searchResult.getString("grade"))
                        .put("DEPT", searchResult.getString("dept_name"))
                        .put("MARK", searchResult.getInt("mark"))
                 .put("MAX", searchResult.getInt("max_mark"));

                courses.put(item);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

    public JSONArray deptCourses(int stdId) throws SQLException {
        JSONObject item;
        JSONArray courses = new JSONArray(); // Initialize as an empty array

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "select s.course_id, c.Name, c.credit_hours, c.max_mark from Course_department s left outer join course2 c on s.course_id=c.id where s.department_id=? ";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, stdId);
            ResultSet searchResult = searchStatement.executeQuery();

            while (searchResult.next()) {
                item = new JSONObject()
                        .put("COURSE_ID", searchResult.getInt("course_id"))
                        .put("COURSE_NAME", searchResult.getString("Name"))
                        .put("HOURS", searchResult.getInt("credit_hours"))
                        .put("MAX", searchResult.getInt("max_mark"));

                courses.put(item);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

    public JSONArray NotDeptCourses(int adminId,int stdId) throws SQLException {
        JSONObject item;
        JSONArray courses = new JSONArray(); // Initialize as an empty array

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "SELECT c.name FROM course2 c WHERE c.id NOT IN (SELECT course_id FROM course_department WHERE department_id = ?) and  c.faculty_id= (select faculty_id from admins where id=?)";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, stdId);
            searchStatement.setInt(2, adminId);
            ResultSet searchResult = searchStatement.executeQuery();

            while (searchResult.next()) {
                item = new JSONObject()
                        .put("COURSE_NAME", searchResult.getString("Name"));

                courses.put(item);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

    public JSONArray NotStudentCourses(int adminid,int stdId) throws SQLException {
        JSONObject item;
        JSONArray courses = new JSONArray(); // Initialize as an empty array
         System.out.println("Hi");
 System.out.println(stdId);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String searchQuery = "SELECT c.name FROM course2 c  WHERE c.id NOT IN (SELECT s.course_id FROM student_course2 s WHERE s.student_id = ?)and c.id in(select course_id from course_department where department_id = (select dept_id from student2 where id=?) )";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, stdId);
            searchStatement.setInt(2, stdId);
            ResultSet searchResult = searchStatement.executeQuery();
            while (searchResult.next()) {
                item = new JSONObject()
                        .put("COURSE_NAME", searchResult.getString("name"));
                 System.out.println(searchResult.getString("name"));
                courses.put(item);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

    public JSONArray deptRegCourses(int stdId) throws SQLException {
        JSONObject item;
        JSONArray courses = new JSONArray(); // Initialize as an empty array

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String searchQuery = "SELECT c.id, c.Name, c.credit_hours, c.max_mark, COUNT(s.student_id) AS student_count FROM course2 c LEFT JOIN student_course2 s ON s.course_id = c.id left join Course_department cd on c.id=cd.course_id where cd.department_id=? GROUP BY c.id, c.Name, c.credit_hours, c.max_mark  order by c.id";
            PreparedStatement searchStatement = connect.prepareStatement(searchQuery);
            searchStatement.setInt(1, stdId);
            ResultSet searchResult = searchStatement.executeQuery();

            while (searchResult.next()) {
                item = new JSONObject()
                        .put("ID", searchResult.getInt("id"))
                        .put("NAME", searchResult.getString("name"))
                        .put("HOURS", searchResult.getInt("credit_hours"))
                        .put("MAX", searchResult.getInt("max_mark"))
                        .put("NUM", searchResult.getInt("student_count"))
                        .put("GPA", "0");

                courses.put(item);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

    public String CheckStdEmail(String email) throws SQLException {
        String result = "no";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            // Use SELECT COUNT(*) to check if the department name exists
            String sql = "SELECT COUNT(*) FROM student2 WHERE LOWER(email) = LOWER(?)";
            try (PreparedStatement countStatement = connect.prepareStatement(sql)) {
                countStatement.setString(1, email);
                try (ResultSet resultSet = countStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        result = "yes";
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public String CheckProfEmail(String email) throws SQLException {
        String result = "no";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            // Use SELECT COUNT(*) to check if the department name exists
            String sql = "SELECT COUNT(*) FROM teacher2 WHERE LOWER(email) = LOWER(?)";
            try (PreparedStatement countStatement = connect.prepareStatement(sql)) {
                countStatement.setString(1, email);
                try (ResultSet resultSet = countStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        result = "yes";
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public String CheckDeptName(int adminId,String name) throws SQLException {
        String result = "no";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            // Use SELECT COUNT(*) to check if the department name exists
            String sql = "SELECT COUNT(*) FROM department2 WHERE LOWER(dept_name) = LOWER(?)";
            try (PreparedStatement countStatement = connect.prepareStatement(sql)) {
                countStatement.setString(1, name);
               
                try (ResultSet resultSet = countStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        result = "yes";
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public String CheckourseName(int adminId,String name) throws SQLException {
        String result = "no";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            // Use SELECT COUNT(*) to check if the department name exists
            String sql = "SELECT COUNT(*) FROM course2 WHERE LOWER(name) = LOWER(?) and  faculty_id=(select faculty_id from admins where id =? )";
            try (PreparedStatement countStatement = connect.prepareStatement(sql)) {
                countStatement.setString(1, name);
                 countStatement.setInt(2, adminId);
                try (ResultSet resultSet = countStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        result = "yes";
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public String CheckDeptStudents(int id) throws SQLException {
        String result = "no";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            // Use SELECT COUNT(*) to check if the department name exists
            String sql = "SELECT COUNT(*) FROM student2 WHERE dept_id = ?";
            try (PreparedStatement countStatement = connect.prepareStatement(sql)) {
                countStatement.setInt(1, id);
                try (ResultSet resultSet = countStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        result = "yes";
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public boolean deleteStudent(int stdId) throws SQLException {

        boolean success = false;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String sql = "{call DeleteStudent(?)}";
            PreparedStatement searchStatement = connect.prepareStatement(sql);
            searchStatement.setInt(1, stdId);
            int result = searchStatement.executeUpdate();
            success = (result > 0); // Check if any rows were affected

            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success;
    }

    public boolean deleteCourse(int courseId) throws SQLException {

        boolean success = false;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String sql = "{call DeleteCourse(?)}";
            PreparedStatement searchStatement = connect.prepareStatement(sql);
            searchStatement.setInt(1, courseId);
            int result = searchStatement.executeUpdate();
            success = (result > 0);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success;
    }

    public boolean deleteProfessor(int profId) throws SQLException {

        boolean success = false;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String sql = "{call DeleteProfessor(?)}";
            PreparedStatement searchStatement = connect.prepareStatement(sql);
            searchStatement.setInt(1, profId);
            int result = searchStatement.executeUpdate();
            success = (result > 0); // Check if any rows were affected

          
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success;
    }

    public boolean deleteDept(int deptId) throws SQLException {

        boolean success = false;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");

            String sql = "{call DeleteDept(?)}";
            PreparedStatement searchStatement = connect.prepareStatement(sql);
            searchStatement.setInt(1, deptId);
            int result = searchStatement.executeUpdate();
            success = (result > 0); // Check if any rows were affected

        
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success;
    }

    public boolean updateStudent(int adminId,int id, String fname, String lname, String email, String pass, String city, String phone, String dept) throws SQLException {
        try {
            int result = -1;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String sql = "{call update_Student(?,?,?, ?, ?, ?, ?,?,?)}";
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, id);
                prepare.setString(2, fname);
                prepare.setString(3, lname);

                prepare.setString(4, phone);
                prepare.setString(5, city);
                prepare.setString(6, email);
                prepare.setString(7, pass);
                prepare.setString(8, dept);
                prepare.setInt(9, adminId);
                result = prepare.executeUpdate();
                if (result != -1) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean updateProfessor(int adminId,int id, String fname, String lname, String email, String pass, String city, String dob, String phone, String dept, int salary, String degree) throws SQLException {
        try {
            int result = -1;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String sql = "{call update_Professor(?,?, ?, ?, ?, ?,?,?,?,?,?)}";
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, id);
                prepare.setString(2, fname);
                prepare.setString(3, lname);

                prepare.setString(4, phone);
                prepare.setString(5, city);
                prepare.setString(6, email);
                prepare.setString(7, pass);
                prepare.setString(8, dept);
                prepare.setInt(9, salary);
                prepare.setString(10, degree);
                 prepare.setInt(11, adminId);

                result = prepare.executeUpdate();
                if (result != -1) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean updateDpt(int id, String name, int head) throws SQLException {
        try {
            int result = -1;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String sql = "{call updateDept(?, ?, ?)}";
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, id);
                prepare.setString(2, name);
                prepare.setInt(3, head);
                result = prepare.executeUpdate();

                if (result != -1) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean updateCourse(int id, String name, int hours, int max) throws SQLException {
        try {
            int result = -1;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String sql = "{call UpdateeCourse(?, ?, ?,?)}";
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, id);
                prepare.setString(2, name);
                prepare.setInt(3, hours);
                prepare.setInt(4, max);
                result = prepare.executeUpdate();

                if (result != -1) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateGrade(int studentid, int courseid, int mark) throws SQLException {
        try {
            int result = -1;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "israa", "123");
            String sql = "{call UpdateGrade(?,?,?)}";
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, studentid);
                prepare.setInt(2, courseid);
                prepare.setInt(3, mark);

                result = prepare.executeUpdate();

                if (result != -1) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean deleteItem(int userId, int itemId, int paid) throws SQLException {
        boolean success = false; 

        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/i-wish-db", "root", "oa123123")) {
            String sql = "DELETE FROM wishlist WHERE user_id = ? AND item_id = ? AND paid = ?";

            try (PreparedStatement wishlistStatement = connect.prepareStatement(sql)) {
                wishlistStatement.setInt(1, userId);
                wishlistStatement.setInt(2, itemId);
                wishlistStatement.setInt(3, paid);

                int result = wishlistStatement.executeUpdate();
                success = (result > 0); 

                if (success) {
                    System.out.println("Item successfully deleted for user: " + userId);
                } else {
                    System.out.println("Item deletion failed for user: " + userId);
                }
            }
        } catch (SQLException ex) {
           
            System.err.println("Error deleting item: " + ex.getMessage());
            throw ex; 
        }

        return success;
    }

   

}
