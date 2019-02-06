package com.company;

/**
 * The Course class represents a single course in the planner. It has 5 important fields that help define it. The name,
 * the department, the code, the section, and the instructor.
 *
 * @author Kirat Singh
 */
public class Course {
    private String name;
    private String department;
    private int code;
    private byte section;
    private String instructor;

    /**
     * This constructor creates the Course object and assigns all values to it.
     *
     * @param courseName
     *      The name of the Course
     * @param courseDepartment
     *      The three letter department name
     * @param courseCode
     *      The numbered course code, may not be negative
     * @param courseSection
     *      The numbered course section, may not be negative
     * @param courseInstructor
     *      The name of the course's instructor
     */
    public Course(String courseName, String courseDepartment, int courseCode, byte courseSection, String courseInstructor ){
        name = courseName;
        department = courseDepartment;
        code = courseCode;
        section = courseSection;
        instructor = courseInstructor;
    }

    /**
     * Getter function that returns the name of the Course
     * @return
     *      String object representing name of the course.
     */
    public String getName(){
        return name;
    }

    /**
     * Getter function that returns the department of the Course
     * @return
     *      String object representing the department of the course.
     */
    public String getDepartment(){
        return department;
    }

    /**
     * Getter function that returns the code of the Course
     * @return
     *      Integer representing Course code.
     */
    public int getCode(){
        return code;
    }

    /**
     * Getter function that returns the section of the Course.
     * @return
     *      Byte representing section of the Course
     */
    public byte getSection(){
        return section;
    }
    /**
     * Getter function that returns the name of the Course's instructor.
     * @return
     *      String object representing name of the course's instructor.
     */
    public String getInstructor(){
        return instructor;
    }

    /**
     * Setter function that sets the name of the Course.
     * @param newName
     *      The name you wish to change to.
     */
    public void setName(String newName){
        name = newName;
    }

    /**
     * Setter function that sets the department of the Course.
     * @param newDepartment
     *      The new department that the course will be set to.
     */
    public void setDepartment(String newDepartment){
        department = newDepartment;
    }

    /**
     * Setter function that sets the code of the Course.
     * @param newCode
     *      The new code you wish to set the Course to.
     *
     * @exception
     *      throws an IllegalArgumentException when the code is negative.
     */
    public void setCode(int newCode){
        if(newCode >= 0){
            code = newCode;
        }else {
            throw new IllegalArgumentException("The code cannot be negative!");
        }

    }
    /**
     * Setter function that sets the section of the Course.
     * @param newSection
     *      The new section you wish to set the Course to.
     *
     * @exception
     *      throws an IllegalArgumentException when the section is negative.
     */
    public void setSection(byte newSection){
        if(newSection >= 0){
            section = newSection;

        }else {
            throw new IllegalArgumentException("The section cannot be negative!");
        }

    }

    /**
     * Setter function that sets the course's instructor.
     * @param newInstructor
     *      The new instructor you wish to set the course to.
     */
    public void setInstructor(String newInstructor){
        instructor = newInstructor;

    }

    /**
     *  Copies the attributes of this Course to a new one, such that any changes made to this Course object do not affect the clone
     * @return
     *      Returns Course object with the same attributes as the current Course.
     */
    public Object clone(){
        return new Course(name, department, code, section, instructor);
    }

    /**
     *  Compares two courses by checking whether their various attributes(name, code, instructor, etc.) are equal to one another. If all attributes are equal, then the courses are equal.
     *
     * @param obj
     *      The object that the Course is being compared to, most likely a course itself
     * @return
     *      Returns true if the courses are equal, returns false if they are not.
     */
    public boolean equals(Object obj){
        if(obj instanceof Course){
            if(((Course)obj).getName().equalsIgnoreCase(name) && ((Course) obj).getDepartment().equalsIgnoreCase(department)){
                if(((Course) obj).getCode() == code && ((Course) obj).getSection() == section){
                    if(((Course) obj).getInstructor().equalsIgnoreCase(instructor)){
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
