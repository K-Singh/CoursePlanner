package com.company;

/**
 * The FullPlannerException is a custom exception thrown when a Planner object is asked to add a course
 * when the number of courses inside it is already equal to MAX_COURSES.
 */
class FullPlannerException extends Exception{
    /**
     * Exception constructor that calls the super constructor for all Checked exceptions.
     * @param e
     *      The message shown when the Exception is thrown.
     */
    public FullPlannerException(String e){
        super(e);
    }
}