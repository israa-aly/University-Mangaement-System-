package DTO;

public class RegistrationDTO {
    String header;
    int id;
    String name;
    int age;
    String phone;
    int dept;
    int semester;
    String city;
    String degree;
    int salary;
    String fname;
    String lname;
    String email ;
    String pass;
    String dob;
   String dept2;
   String gender;
  
    
   

 public RegistrationDTO(String header, String fname, String lname,String email ,
         String phone,String dept2,String city,String pass,String dob,String gender) {
        this.header = header;
        this.fname = fname;
        this.lname=lname;
        this.email=email;
        this.pass=pass;
        this.dept2=dept2;
       this.dob=dob;
        this.phone = phone;
       this.city=city;
       this.gender=gender;
      
       
    }
 
 
  public RegistrationDTO(String header,int id, String fname, String lname,String email ,
         String phone,String dept2,String city,String pass) {
        this.header = header;
        this.id=id;
        this.fname = fname;
        this.lname=lname;
        this.email=email;
        this.pass=pass;
        this.dept2=dept2;
       
        this.phone = phone;
       this.city=city;
      
       
    }
  
 public RegistrationDTO(String header, String fname, String lname,String email ,
         String phone,String dept2,String city,String pass,String dob,int salary,String degree) {
        this.header = header;
        this.fname = fname;
        this.lname=lname;
        this.email=email;
        this.pass=pass;
        this.dept2=dept2;
       this.dob=dob;
        this.phone = phone;
       this.city=city;
      this.salary=salary;
      this.degree=degree;
       
    }
  public RegistrationDTO(String header, int id,String fname, String lname,String email ,
         String phone,String dept2,String city,String pass,String dob,int salary,String degree) {
        this.header = header;
        this.id=id;
        this.fname = fname;
        this.lname=lname;
        this.email=email;
        this.pass=pass;
        this.dept2=dept2;
       this.dob=dob;
        this.phone = phone;
       this.city=city;
      this.salary=salary;
      this.degree=degree;
       
    }
    public RegistrationDTO(String header, int id,String fname, String lname,String email ,
         String phone,String dept2,String city,String pass,int salary,String degree) {
        this.header = header;
        this.id=id;
        this.fname = fname;
        this.lname=lname;
        this.email=email;
        this.pass=pass;
        this.dept2=dept2;
       
        this.phone = phone;
       this.city=city;
      this.salary=salary;
      this.degree=degree;
       
    }
    public RegistrationDTO(String header, String name, int age, String phone, int dept, int semester) {
        this.header = header;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.dept = dept;
        this.semester = semester;
       
    }
     public RegistrationDTO(String header,String name, String city,int age, String phone, int dept, int salary,String degree) {
        this.header = header;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.dept = dept;
        this.salary=salary;
        this.degree=degree;
        this.city=city;
       
    }
      public RegistrationDTO(String header,int id,String name, String city,int age, String phone, int dept, int salary,String degree) {
        this.header = header;
        this.id=id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.dept = dept;
        this.salary=salary;
        this.degree=degree;
        this.city=city;
       
    }
    public RegistrationDTO(String header, int id,String name, String city,int age, String phone, int dept, int semester) {
        this.header = header;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.dept = dept;
        this.semester = semester;
        this.id=id;
        this.city=city;
       
    }

    public String getHeader() {
        return header;
    }
     public int getId() {
        return id;
    }
     public String getGender() {
        return gender;
    }
      public int getSalary() {
        return salary;
    }
      public String getCity() {
        return city;
    }
       public String getDegree() {
        return degree;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getName() {
        return name;
    }
     public String getFname() {
        return fname;
    }
      public String getLname() {
        return lname;
    }
       public String getEmail() {
        return email;
    }
        public String getPass() {
        return pass;
    }
         public String getDept2() {
        return dept2;
    }
          public String getDob() {
        return dob;
    }

    

    public int getAge() {
        return age;
    }

   

    public String getPhone() {
        return phone;
    }

    

    public int getDept() {
        return dept;
    }

  

    public int getSem() {
        return semester;
    }

  
}
