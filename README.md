FileFinder will print the absolute working path to any file given its name and correct extension.

Note: Source files should be compiled and run in the closest known parent directory of the file to be found.
To change default search to user's home directory instead of curreny working directory:
Change line 9 in FileFinder.java to -
String dirName = System.getProperty("user.home");

Copyright Shalini Ramakrishnan 2020
