package universityServer;

import DTO.CourseDTO;
import DTO.DeptDTO;
import DTO.LoginDTO;
import DTO.RegistrationDTO;
import Database.DataAccessLayer;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class UniversityServer {

    ServerSocket serverSocket;

    public static void main(String[] args) {
        new UniversityServer();
    }

    public UniversityServer() {
        try {
            serverSocket = new ServerSocket(5005);
            while (true) {
                Socket s = serverSocket.accept();
                new ClientHandler(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(UniversityServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class ClientHandler extends Thread {

    DataInputStream dis;
    PrintStream ps;
    Socket s;
    //int currClient;
    static Vector<ClientHandler> clientsVector = new Vector<ClientHandler>();

    public ClientHandler(Socket s) {
        try {
            this.s = s;
            dis = new DataInputStream(s.getInputStream());
            ps = new PrintStream(s.getOutputStream());
            clientsVector.add(this);
            //currClient = clientsVector.indexOf(this);
            start();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        DataAccessLayer dal = new DataAccessLayer();
        while (true) {
            try {
                String userData = dis.readLine();
                if (userData == null) {

                    clientsVector.remove(this);
                    break;
                }

                JSONObject jsonObject = new JSONObject(userData);
                String method = jsonObject.getString("HEADER");

                switch (method) {
                    case "fnLogin":
                        //dal.printAllAdmins();
                        LoginDTO loginDTO = new LoginDTO(jsonObject.getString("HEADER"),
                                jsonObject.getString("USERNAME"), jsonObject.getString("PASSWORD"));

                        if (dal.checkLogin(loginDTO.getUserName(), loginDTO.getPassword())) {
                            //System.out.println("Hello, World!"); 
                            ps.println("Login Successfully");
                            ps.println(dal.getAdminData(loginDTO.getUserName()));
                            int id66 = dal.getAdminData(loginDTO.getUserName());
                            ps.println(dal.getFacultyName(id66));
                        } else {
                            System.out.println("Hello, World!");
                            ps.println("Error");
                        }
                        break;
                    case "fnLogout":
                        clientsVector.remove(this);
                        break;

                    case "fnAddStd":
                        RegistrationDTO registrationDTO = new RegistrationDTO(jsonObject.getString("HEADER"),
                                jsonObject.getString("FNAME"), jsonObject.getString("LNAME"), jsonObject.getString("EMAIL"),
                                jsonObject.getString("PHONE"), jsonObject.getString("DEPT"), jsonObject.getString("CITY"),
                                jsonObject.getString("PASS"), jsonObject.getString("DOB"), jsonObject.getString("GENDER")
                        );
                        System.out.println(jsonObject.getString("DEPT"));
                        int adminId1 = jsonObject.getInt("ADMINID");

                        if (dal.addStudent(adminId1, registrationDTO.getFname(), registrationDTO.getLname(),
                                registrationDTO.getEmail(), registrationDTO.getPass(), registrationDTO.getCity(),
                                registrationDTO.getDob(), registrationDTO.getPhone(), registrationDTO.getDept2(), registrationDTO.getGender()
                        )) {
                            ps.println("Student added successfully");
                        } else {
                            ps.println("Error");
                        }
                        break;
                    case "fnAddProf":
                        RegistrationDTO registrationDTO6 = new RegistrationDTO(jsonObject.getString("HEADER"),
                                jsonObject.getString("FNAME"), jsonObject.getString("LNAME"), jsonObject.getString("EMAIL"),
                                jsonObject.getString("PHONE"), jsonObject.getString("DEPT"), jsonObject.getString("CITY"),
                                jsonObject.getString("PASS"), jsonObject.getString("DOB"), jsonObject.getInt("SALARY"), jsonObject.getString("DEGREE")
                        );
                        int adminId5 = jsonObject.getInt("ADMINID");

                        if (dal.addProf(adminId5, registrationDTO6.getFname(), registrationDTO6.getLname(),
                                registrationDTO6.getEmail(), registrationDTO6.getPass(), registrationDTO6.getCity(),
                                registrationDTO6.getDob(), registrationDTO6.getPhone(), registrationDTO6.getDept2(), registrationDTO6.getSalary(), registrationDTO6.getDegree()
                        )) {
                            ps.println("Professor added successfully");
                        } else {
                            ps.println("Error");
                        }
                        break;

                    case "fnAddDept":
                        DeptDTO deptdto = new DeptDTO(jsonObject.getString("NAME"));
                        int admin2 = jsonObject.getInt("ADMINID");
                        if (dal.addDept(admin2, deptdto.getName())) {
                            ps.println("Dept added successfully");
                        } else {
                            ps.println("Error");
                        }
                        break;

                    case "fnAddDeptCourse":
                        String course = jsonObject.getString("COURSE");
                        int id5 = jsonObject.getInt("ID");

                        if (dal.addDeptCourse(course, id5)) {
                            ps.println("Course added successfully");
                        } else {
                            ps.println("Error");
                        }
                        break;

                    case "fnAddCourse":

                        CourseDTO coursedto = new CourseDTO(jsonObject.getString("NAME"),
                                jsonObject.getInt("HOURS"), jsonObject.getInt("MAX"));
                        //System.out.println(jsonObject.getString("NAME"));
                        int adminID3 = jsonObject.getInt("ADMINID");
                        if (dal.addCourse(adminID3, coursedto.getName(), coursedto.getHours(), coursedto.getMax())) {
                            ps.println("Course added successfully");
                        } else {
                            ps.println("Error");
                        }
                        break;

                    case "fnAddStudentCourse":
                        int id6 = jsonObject.getInt("ID");
                        String name6 = jsonObject.getString("NAME");
                        int year2 = jsonObject.getInt("YEAR");
                        int sem2 = jsonObject.getInt("SEM");

                        if (dal.addStudentCourse(name6, id6, year2, sem2)) {
                            ps.println("Course added successfully");
                        } else {
                            ps.println("Error");
                        }
                        break;

                    case "fnSearchProfessor":
                        int std_id3 = jsonObject.getInt("ID");
                        int adminId8 = jsonObject.getInt("ADMINID");

                        JSONObject searchedProfessor = dal.searchProfessor(std_id3, adminId8);
                        ps.println(searchedProfessor);
                        break;
                    case "fnSearchStudent":
                        int std_id = jsonObject.getInt("ID");
                        int adminId3 = jsonObject.getInt("ADMINID");

                        JSONObject searchedStudent = dal.searchStudent(std_id, adminId3);
                        ps.println(searchedStudent);
                        break;
                    case "fnSearchDepartment":
                        int deptid = jsonObject.getInt("ID");
                        int admin9 = jsonObject.getInt("ADMINID");
                        JSONObject searchedDept = dal.searchDept(admin9, deptid);
                        System.out.println(searchedDept);

                        ps.println(searchedDept);
                        break;

                    case "fnSearchCourse":
                        int courseid = jsonObject.getInt("ID");
                        int adminID9 = jsonObject.getInt("ADMINID");
                        JSONObject searchedCourse = dal.searchCourse(adminID9, courseid);
                        //System.out.println(searchedCourse);
                        ps.println(searchedCourse);
                        break;
                    case "fnDeleteStudent":
                        int std_id2 = jsonObject.getInt("ID");

                        if (dal.deleteStudent(std_id2)) {
                            ps.println("Student Deleted");
                        } else {
                            ps.println("Error");
                        }

                        break;
                    case "fnDeleteDept":
                        int deptId = jsonObject.getInt("ID");

                        if (dal.deleteDept(deptId)) {
                            ps.println("Dept Deleted");
                        } else {
                            ps.println("Error");
                        }

                        break;

                    case "fnDeleteProfessor":
                        int prof_id = jsonObject.getInt("ID");

                        if (dal.deleteProfessor(prof_id)) {
                            System.out.println("delete!");
                            ps.println("Professor Deleted");
                        } else {
                            ps.println("Error");
                        }

                        break;

                    case "fnDeleteCourse":
                        int course_id = jsonObject.getInt("ID");
                        if (dal.deleteCourse(course_id)) {
                            System.out.println("delete!");
                            ps.println("Course Deleted");
                        } else {
                            ps.println("Error");
                        }

                        break;
                    case "fnUpdateStudent":
                        RegistrationDTO registrationDTO2 = new RegistrationDTO(jsonObject.getString("HEADER"), jsonObject.getInt("ID"),
                                jsonObject.getString("FNAME"), jsonObject.getString("LNAME"), jsonObject.getString("EMAIL"),
                                jsonObject.getString("PHONE"), jsonObject.getString("DEPT"), jsonObject.getString("CITY"),
                                jsonObject.getString("PASS"));
                        int adminnn3 = jsonObject.getInt("ADMINID");
                        //System.out.println(jsonObject.getString("DEPT"));
                        if (dal.updateStudent(adminnn3, registrationDTO2.getId(), registrationDTO2.getFname(), registrationDTO2.getLname(),
                                registrationDTO2.getEmail(), registrationDTO2.getPass(), registrationDTO2.getCity(),
                                registrationDTO2.getPhone(), registrationDTO2.getDept2())) {
                            ps.println("Student Updated successfully");
                        } else {
                            ps.println("Error");
                        }
                        break;

                    case "fnUpdateProfessor":
                        int adminh = jsonObject.getInt("ADMINID");
                        RegistrationDTO registrationDTO4 = new RegistrationDTO(jsonObject.getString("HEADER"), jsonObject.getInt("ID"),
                                jsonObject.getString("FNAME"), jsonObject.getString("LNAME"), jsonObject.getString("EMAIL"),
                                jsonObject.getString("PHONE"), jsonObject.getString("DEPT"), jsonObject.getString("CITY"),
                                jsonObject.getString("PASS"), jsonObject.getInt("SALARY"), jsonObject.getString("DEGREE"));

                        if (dal.updateProfessor(adminh, registrationDTO4.getId(), registrationDTO4.getFname(), registrationDTO4.getLname(),
                                registrationDTO4.getEmail(), registrationDTO4.getPass(), registrationDTO4.getCity(),
                                registrationDTO4.getDob(), registrationDTO4.getPhone(), registrationDTO4.getDept2(), registrationDTO4.getSalary(), registrationDTO4.getDegree()
                        )) {
                            ps.println("Professor Updated successfully");
                        } else {
                            ps.println("Error");
                        }
                        break;

                    case "fnUpdateDept":
                        DeptDTO dept2 = new DeptDTO(jsonObject.getInt("ID"), jsonObject.getString("NAME"),
                                jsonObject.getInt("HEAD"));

                        if (dal.updateDpt(dept2.getId(), dept2.getName(), dept2.getHead())) {
                            ps.println("Dept Updated successfully");
                        } else {
                            ps.println("Error");
                        }
                        break;

                    case "fnUpdateGrade":
                        CourseDTO grade = new CourseDTO(jsonObject.getInt("ID"), jsonObject.getInt("COURSEID"),
                                jsonObject.getInt("MARK"));

                        if (dal.updateGrade(grade.getId(), grade.getId2(), grade.getMark())) {
                            ps.println("Grade Updated successfully");
                        } else {
                            ps.println("Error");
                        }
                        break;

                    case "fnUpdateCourse":
                        CourseDTO course2 = new CourseDTO(jsonObject.getInt("ID"), jsonObject.getString("NAME"),
                                jsonObject.getInt("HOURS"), jsonObject.getInt("MAX"));

                        if (dal.updateCourse(course2.getId(), course2.getName(), course2.getHours(), course2.getMax())) {
                            ps.println("Course Updated successfully");
                        } else {
                            ps.println("Error");
                        }
                        break;

                    case "fnGetStudentCourses":
                        int std_id4 = jsonObject.getInt("ID");
                        int adminnn = jsonObject.getInt("ADMINID");
                        JSONArray courses = dal.studentCourses(adminnn, std_id4);
                        ps.println(courses);
                        break;

                    case "fnGetCourseStudents":
                        System.out.println("fnGetCourseStudents");
                        int std_id7 = jsonObject.getInt("ID");

                        JSONArray students2 = dal.courseStudents(std_id7);
                        ps.println(students2);
                        System.out.println(students2);
                        break;
                    case "fnGetDeptCourses":
                        int deptCourses = jsonObject.getInt("ID");

                        JSONArray courses6 = dal.deptCourses(deptCourses);
                        ps.println(courses6);
                        break;
                    case "fnGetAllCourses":
                        int std_id5 = jsonObject.getInt("ID");
                        int adminid = jsonObject.getInt("ADMINID");
                        JSONArray noncourses = dal.NotStudentCourses(adminid, std_id5);
                        System.out.print(noncourses);
                        ps.println(noncourses);
                        break;

                    case "fnGetDeptNonCourses":
                        int deptID = jsonObject.getInt("ID");
                        int adminid2 = jsonObject.getInt("ADMINID");
                        JSONArray deptNonCourses = dal.NotDeptCourses(adminid2, deptID);
                        ps.println(deptNonCourses);
                        break;

                    case "fnDeptRegCourses":
                        int dept_id = jsonObject.getInt("ID");
                        JSONArray deptRegCourses = dal.deptRegCourses(dept_id);
                        ps.println(deptRegCourses);
                        break;

                    case "fnAllProf":
                        int admin = jsonObject.getInt("ADMINID");

                        JSONArray prof = dal.getAllprof(admin);
                        System.out.println(prof);
                        ps.println(prof);
                        break;

                    case "fnAllStudent":
                        int adminId = jsonObject.getInt("ADMINID");

                        JSONArray students = dal.getAllstd(adminId);
                        //System.out.println(students);
                        ps.println(students);
                        break;
                    case "fnhead":
                        int adminId6 = jsonObject.getInt("ADMINID");

                        JSONArray ids = dal.getHead(adminId6);
                        System.out.println("hi");
                        System.out.println(ids);
                        ps.println(ids);
                        break;

                    case "fnCheckDeptName":
                        String name2 = jsonObject.getString("NAME");
                        int adminId11 = jsonObject.getInt("ADMINID");
                        String x = dal.CheckDeptName(adminId11, name2);
                        System.out.println(x);

                        if (x == "yes") {
                            ps.println("yes");
                        } else {
                            ps.println("no");
                        }

                        break;
                    case "fnCheckStdEmail":
                        String email = jsonObject.getString("EMAIL");
                        String e = dal.CheckStdEmail(email);
                        System.out.println(e);

                        if (e == "yes") {
                            ps.println("yes");
                        } else {
                            ps.println("no");
                        }

                        break;

                    case "fnCheckProfEmail":
                        String profemail = jsonObject.getString("EMAIL");
                        String p = dal.CheckProfEmail(profemail);
                        System.out.println(p);

                        if (p == "yes") {
                            ps.println("yes");
                        } else {
                            ps.println("no");
                        }

                        break;

                    case "fnCheckCourseName":
                        String name3 = jsonObject.getString("NAME");
                        int adminn = jsonObject.getInt("ADMINID");
                        String y = dal.CheckourseName(adminn, name3);
                        System.out.println(y);

                        if (y == "yes") {
                            ps.println("yes");
                        } else {
                            ps.println("no");
                        }

                        break;

                    case "fnCheckDeptStudents":

                        int id = jsonObject.getInt("ID");
                        String x2 = dal.CheckDeptStudents(id);
                        System.out.println(x2);

                        if (x2 == "yes") {
                            ps.println("yes");
                        } else {
                            ps.println("no");
                        }

                        break;
                    case "fnCheckCoursetudents":
                        System.out.println("fnCheckCourseStudents");
                        int id44 = jsonObject.getInt("ID");
                        JSONArray c = dal.courseStudents(id44);
                        System.out.println(c);

                        if (c.length() <= 0) {
                            System.out.println("no");
                            ps.println("no");
                        } else {
                            System.out.println("yes");
                            ps.println("yes");
                        }

                        break;

                    /*case "fnGetCourses":
                        int adminID8 = jsonObject.getInt("ADMINID");

                        JSONArray dept6 = dal.getAllCourses(adminID8);
                        //System.out.println(dept6);
                        ps.println(dept6);
                        break;*/
                    case "fnAllDept":
                        int adminID = jsonObject.getInt("ADMINID");
                        JSONArray dept = dal.getAlldept(adminID);
                        System.out.println(dept);
                        ps.println(dept);
                        break;
                    case "getFacultyName":
                        int adminID33 = jsonObject.getInt("ADMINID");
                        String facultyName = dal.getFacultyName(adminID33);
                        //System.out.println(dept);
                        ps.println(facultyName);
                        break;

                    case "fnDepartmentStudentCount":
                        int adminId55 = jsonObject.getInt("ADMINID");
                        JSONArray count = dal.getDeptStdCount(adminId55);
                        System.out.println(count);
                        ps.println(count);
                        break;
                    case "fnAllCourses":
                        int adminID7 = jsonObject.getInt("ADMINID");
                        JSONArray courses2 = dal.getAllCourses(adminID7);
                        System.out.println(courses2);
                        ps.println(courses2);
                        break;

                }
            } catch (IOException ex) {
                // Client disconnected
                clientsVector.remove(this);
                break;
            } catch (SQLException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
