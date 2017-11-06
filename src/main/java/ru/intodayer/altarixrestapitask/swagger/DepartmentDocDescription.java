package ru.intodayer.altarixrestapitask.swagger;


public class DepartmentDocDescription {
    public static final String ADD_DEPARTMENT_DESC =
        "Creating of a department. \n" +
        "When creating a department, you need to specify information about the parent department " +
        "to which it will be included. For the root department, there is no need to specify such information. " +
        "When client trying to add new root department reference to new root sets to previous root.";

    public static final String UPDATE_DEPARTMENT_DESC =
        "Change of the name of the department. " +
        "There can not be two departments with the same name in the system.";

    public static final String DELETE_DEPARTMENT_DESC =
        "Deleting the department. " +
        "Deleting is possible only if department does not contain any employees.";

    public static final String GET_DEPARTMENT_DESC =
        "View information about the department.";

    public static final String GET_CHILD_DEPARTMENT_DESC =
        "Get child departments. " +
        "Providing information on departments directly subordinate to this department (to the level below).";

    public static final String GET_ALL_SUB_DEPARTMENTS_DESC =
        "Get all sub-departments. " +
        "Providing information on all departments subordinate to this department (all subordinate departments.) " +
        "For the head department, these are all other departments.";

    public static final String CHANGE_PARENT_DEPARTMENT_DESC =
        "Transfer of the department. Assignment of another parent department.";

    public static final String GET_PARENT_DEPARTMENTS_DESC =
        "Get information about all the parent departments of this department.";

    public static final String GET_DEPARTMENT_BY_NAME_DESC =
        "Search of the department by name.";

    public static final String GET_DEPARTMENT_SALARY_FUND_DESC =
        "Get information about the department's salary fund. " +
        "Department salary fund is the sum of salaries for all employees of the department.";

}
