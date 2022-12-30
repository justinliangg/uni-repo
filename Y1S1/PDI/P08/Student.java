public class Student
{
    private String name;
    private int studentID;
    private double mark;

    //CONSTRUCTOR METHODS

    //Parameter Constructor
    public Student(String pName, int pStudentID, double pMark)
        {       if(pName != null )
                {
                    if(pStudentID <= 99999999 && pStudentID >= 10000000)
                    {
                        if(pMark <= 100 && pMark >= 0)
                            {        
                                name = pName;
                                studentID = pStudentID;
                                mark = pMark;
                            }
                    
                    } 
                }  
        }
        
    public Student(Student pStudent)
    {
        name = pStudent.getName();
        studentID = pStudent.getStudentID();
        mark = pStudent.getMark();    
    }
  
    public Student()
    {
        name = "John";
        studentID = 10000000;
        mark = 50.0;
    }

    //ACCESSOR METHODS
    public String getName()
    {
        return name;
    }

    public int getStudentID()
    {
        return studentID;
    }
    
    public double getMark()
    {
        return mark;
    }

    //MUTATOR METHODS
    public void setName(String pName)
    {
        name = pName;
    }

    public void setStudentID(int pStudentID)
    {
        if(pStudentID >= 10000000 && pStudentID <= 99999999)
            {
                studentID = pStudentID;
            }
                
    }

    public void setMark(double pMark)
    {
        if(pMark <= 100.0 && pMark >= 0.0)
        {
            mark = pMark;           
        }
    }

    //SUBMODULE: equals
    //IMPORT: pObject(Object)
    //EXPORT: isEqual(boolean)
    //ASSERTION: to test if two objects are equal to each other
    public boolean equals(Object pObject)
    {
        boolean isEquals = false;
        Student pStudent = null;
        if(pObject instanceof Student)
        {
            pStudent = (Student) pObject;
            if(name.equals(pStudent.getName()))
            {
                if(studentID == pStudent.getStudentID())
                {
                    if(mark == pStudent.getMark())
                    {
                            isEquals = true;
                    }
                }
            }
        }

    return isEquals;   
    }

    //SUBMODULE:toString
    //IMPORT: nil
    //EXPORT:studentString(String)
    //ASSERTION: return the instance variables in an object as a string
    public String toFileString()
    {
        String studentString = name + "," + studentID + "," + mark;
        return studentString;
    }
    
    //SUBMODULE: getGrade
    //IMPORT: 
    //EXPORT: grade(String)
    //ASSERTION: return a grade of type string for a given mark
    public String getGrade()
    {   
        String grade;
        if(mark <=100.0 && mark >= 80.0)
        {
            grade = "HD";
        }
        else if(mark <80.0 && mark >= 70.0)
        {
            grade = "D";
        }
        else if(mark<70.0 && mark >= 60)
        {
            grade = "C";
        }
        else if(mark<60 && mark >=50)
        {
            grade = "P";
        }
        else 
        {
            grade = "F";
        }
        return grade;
    }
    
        
    public String toString()
    { 
 
       String fileString = name + " (" + studentID + ") "+ "has scored "  + mark+"%";
       return fileString;
    }

}
