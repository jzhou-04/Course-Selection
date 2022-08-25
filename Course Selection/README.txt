Add courses to Courses.txt files using this template:

    Calculus <= Course Name: The Course Name MUST start with a String, CANNOT start with an int
    54915 MWF 2:00 p.m.-3:00 p.m. TTH 9:30 a.m.-10:30 a.m. <= UniqueCourses following this exact format and spacing
    CS314
    52570 MWF 11:00 a.m.-12:00 p.m. M 1:00 p.m.-2:00 p.m.
    CS311
    52410 TTH 12:30 p.m.-2:00 p.m. F 3:00 p.m.-4:00 p.m.
    UGS
    62105 MW 3:00 p.m.-4:30 p.m.

The temp.txt file exists for a temporary storage during runtime and is necessary for the code to run. 
It is not necessary to make modifications to it but don't remove it. 
A file with the name temp.txt must exist in the project folder for the code to run properly.

The tool will print out all available courses from the Courses.txt file. 
The tool will then copy the first available and unselected course from the list, starting from top down.
Entering N (not case sensitive) will move onto and copy the next available course in the list.
Entering S will select the current copied course and remove any courses in the list with time conflicts. The next course in the list will then be copied.
A course can be selected manually by entering its correpsonding index. Time conflicting courses will also be removed in this way.

Once a Course Name group has a course selected from it (i.e. Calculus), further courses from that group will no longer be automatically copied by the tool.
For example, if a Calculus course group has 5 courses in it and 1 course is selected using the tool, either manually or by entering S when the correpsonding course is copied,
no more Calculus courses will be copied by the tool. The tool will skip any Calculus courses in the list when it finds the next unique course id to copy in a top down manner.

Once a course has been selected, a <== Selected will appear next to that course in the list for the remainder of the program run.

The program ends once one course from each course group has been selected or every unique course has been copied and passed.