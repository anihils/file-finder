run : FileFinder.class
	java FileFinder

FileFinder.class : dependencies
	javac FileFinder.java

dependencies :
	javac DeepFileIterator.java
	javac FilteredFileIterator.java

clean :
	rm FileFinder.class
	rm FilteredFileIterator.class
	rm DeepFileIterator.class	
