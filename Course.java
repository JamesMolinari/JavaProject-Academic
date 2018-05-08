// -----------------------------------------------------
// Assignment 4
// Written by: James Molinari (27801866)
// ----------------------------------------------------

import java.util.Scanner;

class Course implements DirectlyRelatable {
    private String courseID;
    private String courseName;
    private double credit;
    private String preReqID;
    private String coReqID;
    Scanner key = new Scanner(System.in);

    //Constructor
    public Course(String courseID, String courseName, double credit, String preReqID, String coReqID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credit = credit;
        this.preReqID = preReqID;
        this.coReqID = coReqID;
    }
    
    //Copy constructor
    public Course(Course c, String id) {
        this.courseID = id;
        this.courseName = c.courseName;
        this.credit = c.credit;
        this.preReqID = c.preReqID;
        this.coReqID = c.coReqID;
    }
    
    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPreReqID() {
        return preReqID;
    }

    public void setPreReqID(String preReqID) {
        this.preReqID = preReqID;
    }

    public String getCoReqID() {
        return coReqID;
    }

    public void setCoReqID(String coReqID) {
        this.coReqID = coReqID;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
    
    
    //clone
    public Course clone() {
        System.out.println("Enter the new courseId: ");
        String id = key.next();
        return new Course(this, id);
    }

    public String toString() {
        return "Course [courseID=" + courseID + ", courseName=" + courseName
                + ", preReqID=" + preReqID + ", coReqID=" + coReqID
                + ", credit=" + credit + "]";
    }

    public boolean equals(Course other) {
        if (coReqID == null) {
            if (other.coReqID != null)
                return false;
        } else if (!coReqID.equals(other.coReqID))
            return false;

        if (courseName == null) {
            if (other.courseName != null)
                return false;
        } else if (!courseName.equals(other.courseName))
            return false;

        if (Double.doubleToLongBits(credit) != Double
                .doubleToLongBits(other.credit))
            return false;

        if (preReqID == null) {
            if (other.preReqID != null)
                return false;
        } else if (!preReqID.equals(other.preReqID))
            return false;

        return true;
    }
    
    public boolean isDirectlyRelated(Course c) {
        return (c.getCourseID().equals(preReqID) || courseID.equals(c.getPreReqID())
                || c.getCourseID().equals(coReqID) || courseID.equals(c.getCoReqID()));
    }

}