# Course-Selection
This program is meant to help with course registration by preventing scheduling conflicts when registering for classes
</br>This was designed with The University of Texas at Austin's registration format in mind
# How to Use
Add courses to the Courses.txt file using this format:

    - Course Name
    - All Unique Courses Within that Course Group
    - Course Name
    - All Unique Courses Within that Course Group
    - Etc.
  
A Course Name is a the name of a general group of courses i.e. Calculus, Data Structures, History, Government. 
</br> Integers are allowed in the Course Group Name as long as it starts with a string, i.e. CS 314, CS 311, UGS 303.
</br> <br/>
Unique Courses include all the unique course id's of each course group as well as their class times. 
<br/> Use one line to list all the information for each unique course in the following format:

    Unique Course ID, Meeting Dates, Meeting Times, Meeting Dates, Meeting Times, etc.
Use T to denote Tuesday and TH to denote Thursday. If both are present, use TTH.
<br/> Examples of a correctly formatted unique course are listed below:
<br> <br> 54915 MWF 2:00 p.m.-3:00 p.m. TTH 9:30 a.m.-10:30 a.m.
<br> 52570 MWF 11:00 a.m.-12:00 p.m. M 1:00 p.m.-2:00 p.m.
<br> 52410 TTH 12:30 p.m.-2:00 p.m. F 3:00 p.m.-4:00 p.m.
<br> 62105 MW 3:00 p.m.-4:30 p.m.
<br><br> The space after the time and before a.m./p.m. needs to be included, as well as the period at the end of a.m. or p.m. 
<br> When in doubt, just follow all the spacing in the examples above.
<br/><br/>
A full list in the correct format is shown below. There is no limit to how many course groups or how many unique courses within a course group there are.
    
    UGS <= Course Name: The Course Name MUST start with a String, CANNOT start with an int
    62105 MW 3:00 p.m.-4:30 p.m. <= UniqueCourses following this exact format and spacing
    62625 TTH 9:30 a.m.-11:00 a.m.
    62705 M 12:00 p.m.-2:00 p.m. W 1:00 p.m.-2:00 p.m.
    Data Structures
    52570 MWF 11:00 a.m.-12:00 p.m. M 1:00 p.m.-2:00 p.m.
    52565 MWF 11:00 a.m.-12:00 p.m. M 10:00 a.m.-11:00 a.m.
    52545 MWF 10:00 a.m.-11:00 a.m. M 11:00 a.m.-12:00 p.m.
    Discrete Math
    52410 TTH 12:30 p.m.-2:00 p.m. F 3:00 p.m.-4:00 p.m.
    52380 TTH 9:30 a.m.-11:00 a.m. F 12:00 p.m.-1:00 p.m.
    52430 TTH 2:00 p.m.-3:30 p.m. F 12:00 p.m.-1:00 p.m.
    Calculus 2
    54885 MWF 10:00 a.m.-11:00 a.m. TTH 2:00 p.m.-3:00 p.m.
    54905 MWF 1:00 p.m.-2:00 p.m. TTH 1:00 p.m.-2:00 p.m.
    54920 MWF 2:00 p.m.-3:00 p.m. TTH 1:00 p.m.-2:00 p.m.

The tool will print out all available courses from the Courses.txt file. 
<br>The tool will then copy the first available and unselected course from the list, starting from top down.
<br>Entering N (not case sensitive) will move onto and copy the next available course in the list.
<br>Entering S will select the current copied course and remove any courses in the list with time conflicts. The next course in the list will then be copied.
<br>A course can be selected manually by entering its correpsonding index. Time conflicting courses will also be removed in this way.

<br>Once a Course Name group has a course selected from it (i.e. Calculus), further courses from that group will no longer be automatically copied by the tool.
<br>For example, if a Calculus course group has 5 courses in it and 1 of them is already is selected, either manually or by entering S when the one of them is copied,
no more Calculus courses will be copied by the tool. The tool will skip any Calculus courses in the list when it finds the next unique course id to copy in a top down manner.

<br>Once a course has been selected, a <== Selected will appear next to that course in the list for the remainder of the program run.

<br>The program ends once one course from each course group has been selected or every unique course has been copied and passed.
