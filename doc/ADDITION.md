CompSci 308: SLogo Addition
===
Louis Jensen (lbj7)

The feature I completed was allowing the user to change the turtle image on click.
The user first clicks a turtle to make that turtle active and then right clicks on that 
turtle to see the list of commands they can run from that menu. The 'setshape' option
will allow the user to enter a number of a turtle visible in the palette on the left
side of the screen and change the turtle to that image.

# Before

### Estimation

 * How long do you think it will take you to complete this new feature?
   * I plan to add the feature that will allow users
 to click on a turtle to change its image and I believe that 
 it will take me about an hour.


 * How many files will you need to add or update? Why?
   * I think I will need to add one file for the turtle
 selection context menu and modify the currently existing 
 turtle context menu.


# After

### Review

 * How long did it take you to complete this new feature?
    * It only took me about 20 minutes to add this new feature and the
    majority of that time was spent re-familiarizing myself with
    the code.

 * How many files did you need to add or update? Why?
   * I only needed to update one file to add the feature. The only class
   I modified was the TurtleContextMenu class. This is because when I added
   the command to the list of the commands in that class everything else
   was auto-populated and the command was given the ability to run.

 * Did you get it completely right on the first try? If not, why not?
   * At first I created a new class, but then I realized that I could delete
   it and just add the 'setshape' option in the existing class.

### Analysis

 * What do you feel this exercise reveals about your project's design and documentation --- was it as good (or bad) as you remembered?
   * I believe this proves that we had very well defined code. To implement this new feature,
   all I had to do was add one new line and modify one existing line of code. The rest was completed
   automatically by our existing code which illustrates that our design is 
   very extensible. Since our GIU already showed all available turtle images and our design already had the ability to be able to
   run commands on turtle click all I had to do was add 'setshape' to the list of options.

 * What about the design or documentation could be improved?
   * I don't think this feature reveals in areas of design that could be improved
   but it does show that are documentation is not as clear as it should have been. The classes were
   only lightly commented so if I wasn't familiar with the code it would've been difficult to figure out where
   to make the addition.

 * What would it have been like if you were not familiar with the code at all?
    * If I was completely unfamiliar with the code the most challenging part would be figuring
    my way through the code. Our comments are not super thorough so it may be difficult
    to navigate to the TurtleContextMenu and figure out that adding a string
    there will add another option for the user on the right click of an active turtle. However, once
    that is figured out it is very easy to add the necessary string and make the command possible.