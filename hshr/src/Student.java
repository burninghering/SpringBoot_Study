public class Student {

    private int studentID;
    private String studentName;

    public void getStudentID() {
        System.out.println(studentID);
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}