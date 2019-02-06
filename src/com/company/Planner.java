package com.company;

/**
 * The Planner class is an object that holds a list of courses defined by MAX_COURSES. It holds these courses in an
 * array and allows users to add, remove, filter out, and print courses. It also allows users to access the size
 * of the array in the list so that users may know how many courses are in the Planner(As opposed to the total number
 * of courses it can hold).
 *
 * @author Kirat Singh
 */
public class Planner {
    private Course[] courseList;
    private final int MAX_COURSES = 50;
    private int size;

    /**
     * Planner constructor, initializes the array of courses, courseList, with a length of MAX_COURSES, and sets the
     * initial size of the Planner to 0.
     *
     * <dt><b>Postcondition:</b></dt>
     *      The planner has been initialized to an empty list of Courses.
     */
    public Planner() {
        courseList = new Course[MAX_COURSES];
        size = 0;
    }

    /**
     * Determines the number of courses in the list.
     * <dt><b>Precondition:</b></dt>
     *      The Planner has been instantiated.
     * @return
     *      Returns the size of the array, a counter that tells the user how many Courses are in the planner.
     */
    public int size() {
        return size;
    }

    /**
     * This function allows users to add a Course into the planner at a specified position in the Planner.
     * <dt><b>Precondition:</b></dt>
     *      The Course has been instantiated and 1 ≤ position ≤ # of courses in the list + 1. The number of courses
     *      in the list is less than MAX_COURSES.
     * <dt><b>Postconditions</b></dt>
     *      The new Course is now listed in the correct preference on the list. All Courses that were originally
     *      greater than or equal to position are moved back one position. (e.g. If there are 5 Courses in a Planner,
     *      positioned 1-5, and you insert a Course in position 4, the new Course would be placed in position 4,
     *      the Course that was in position 4 will be moved to position 5, and the Course that was in position 5 will
     *      be moved to position 6.)
     * @param course
     *      The instantiated course that will be added.
     * @param position
     *      The position at which the course will be added.
     * @throws FullPlannerException
     *      Thrown when the # of courses in the list is equal to MAX_COURSES
     * @throws IllegalArgumentException
     *      Thrown when the position passed is not valid or if the code or section are negative.
     */
    public void addCourse(Course course, int position) throws FullPlannerException, IllegalArgumentException{
        if (size() < MAX_COURSES) {
            if (course.getSection() >= 0 || course.getCode() >= 0) {
                if (position <= size() && position >= 0) {
                    if(exists(course)) {
                        if (getCourse(position) != null) {
                            for (int i = size(); i > position; i--) {
                                courseList[i] = courseList[i - 1];
                            }
                        }
                        courseList[position] = course;
                    }
                } else
                    throw new IllegalArgumentException("The course's listing preference must be between 1 and the current" +
                            " size of the Planner + 1!");
            }else
                throw new IllegalArgumentException("All numbers must be valid(Not negative)!");
        }else
            throw new FullPlannerException("The Planner has the maximum number of courses inside it!");
        size++;
    }

    /**
     * Works just like addCourse(Course c, int position) but adds the Course to the end of the list.
     * @param course
     *      The course to be added to the end of the list.
     */
    public void addCourse(Course course){
        try {
            addCourse(course, size());
        }catch (IllegalArgumentException | FullPlannerException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Removes the course at the specified position in the Planner.
     *
     * <dt><b>Preconditions:</b></dt>
     *      This Planner has been instantiated and 1 ≤ position ≤ items_currently_in_list.
     * <dt><b>Postconditions:</b></dt>
     *      The Course at the desired position has been removed. All Courses that were originally greater than or
     *      equal to position are moved backward one position. (e.g. If there are 5 Courses in a Planner,
     *      positioned 1-5, and you remove the Course in position 4, the item that was in position 5 will be moved
     *      to position 4.)
     * @param position
     *      The position at which the course will be removed.
     * @throws IllegalArgumentException
     *      Thrown when no course exists at the given position/the position is invalid.
     */
    public void removeCourse(int position) throws IllegalArgumentException{
        if (position >= 0 && position < size()) {
            for (int i = position; i < size()-1; i++) {
                courseList[i] = courseList[i+1];
            }
            courseList[size() - 1] = null;
            System.out.println(size);

            size--;
        } else
            throw new IllegalArgumentException("No course exists at that position!");
    }


    /**
     * Gets a course at a given position.
     * <dt><b>Preconditions:</b></dt>
     *      The Planner object has been instantiated and 1 ≤ position ≤ items_currently_in_list.
     * @param position
     *      The position at which you'd like to find the Course.
     * @return
     *      Returns the course at the position, or null if no course was found there.
     */
    public Course getCourse(int position){
        if (courseList[position] != null) {
            return courseList[position];
        }
        return null;
    }

    /**
     * Prints all courses in the specified department.\
     * <dt><b>Preconditions:</b></dt>
     *      This planner object has been instantiated
     * <dt><b>Postconditions:</b></dt>
     *      Displays a neatly formatted table of each course filtered from the Planner. Keep the preference numbers the
     *      same.
     * @param planner
     *      The planner from which you'd like to search and filter the courses from.
     * @param department
     *      The three letter department code for the courses that are being searched for.
     */
    public static void filter(Planner planner, String department) {
        int plannerSize = planner.size();
        boolean found = false;
        String msg = "# | Course Name               | Department | Code | Section | Instructor |\n";
        msg += "--------------------------------------------------------------------------\n";
        for (int i = 0; i < plannerSize; i++) {
            if(planner.getCourse(i) != null) {
                if (planner.getCourse(i).getDepartment().equalsIgnoreCase(department)) {
                    Course c = planner.getCourse(i);
                    String s = String.format("%-3d%-28s%-13s%-7d%-10d%-13s",
                            ++i, c.getName(), c.getDepartment(), c.getCode(), c.getSection(), c.getInstructor());
                    s += "\n";
                    msg += s;
                    found = true;
                }
            }
        }
        if(!found){
            System.out.println("No courses with that code were found!");
            return;
        }
        System.out.println(msg);
    }

    /**
     * Searches through the given planner and prints out a table containing the Course at the given position.
     * @param planner
     *      The planner from which the course will be searched from.
     * @param position
     *      The position belonging to the course that is being searched for.
     */
    public static void printCourse(Planner planner, int position) {
        String msg = "# | Course Name               | Department | Code | Section | Instructor |\n";
        msg += "--------------------------------------------------------------------------\n";
        if(planner.getCourse(position) != null){
            Course c = planner.getCourse(position);
            String s = String.format("%-3d%-28s%-13s%-7d%-10d%-13s",
                    position+1, c.getName(), c.getDepartment(), c.getCode(), c.getSection(), c.getInstructor());
            s += "\n";
            msg += s;
        }
        else
             System.out.println("No course was found at that position!");
        System.out.println(msg);
    }

    /**
     * Prints a neatly formatted table containing all the courses in the Planner.
     * <dt><b>Preconditions:</b></dt>
     *      This planner has been instantiated.
     * <dt><b>Postconditions:</b></dt>
     *      Displays a neatly formatted table containing all courses.
     */
    public void printAllCourses(){
        System.out.print(this.toString());
    }

    /**
     * Gets the String representation of this Planner object, which is a neatly formatted table of each Course in the
     * Planner on its own line with its position number as shown in the sample output.
     * @return
     *      The String representation of the Planner.
     */
    public String toString() {
        String msg = "# | Course Name               | Department | Code | Section | Instructor |\n";
        msg += "--------------------------------------------------------------------------\n";
        for (int i = 0; i < size(); i++) {
            Course c = getCourse(i);
            String s = String.format("%-3d%-28s%-13s%-7d%-10d%-13s", (i+1),
                    c.getName(), c.getDepartment(), c.getCode(), c.getSection(), c.getInstructor());
            s += "\n";
            msg += s;
        }
        return msg;
    }

    /**
     * Creates a copy of this Planner. Subsequent changes to the copy will not affect the original and vice versa.
     * <dt><b>Preconditions:</b></dt>
     *      This Planner object has been instantiated.
     * @return
     *      Returns a copy(backup) of this Planner.
     */
    public Object clone(){
        Planner clone = new Planner();

        for(Course c : courseList){
            if(c != null) {
                clone.addCourse((Course) c.clone());
            }
        }
        return clone;
    }

    /**
     * Checks whether a certain course already exists in the list.
     * <dt><b>Preconditions:</b></dt>
     *      The planner and course have been instantiated.
     * @param check
     *      The course we're looking for
     * @return
     *      Returns true if the course exists already, false if it doesn't.
     */
    public boolean exists(Course check){
        for (Course c : courseList) {
            if (c != null) {
                if (c.equals(check))
                    return true;
            }
        }
        return false;
    }


}

