run : build
	java FileFinder

build : dependencies
	javac FileFinder.java

dependencies :
	javac DeepFileIterator.java
	javac FilteredFileIterator.java

clean :
	rm FileFinder.class
	rm FilteredFileIterator.class
	rm DeepFileIterator.class	
