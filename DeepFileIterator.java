//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: DeepFileIterator
// Files: DeepFileIterator.java, FilteredFileIterator.java, FileFinder.java
// Course: CS 300, Spring 2020
//
// Author: Shalini Ramakrishnan
// Email: ramakrishn22@wisc.edu
// Lecturer's Name: Hobbes Legault
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class DeepFileIterator implements Iterator<File> {

    // private fields
    private File[] dirContents; // directory contents
    private int nextIndex;
    private DeepFileIterator contentsIterator; // iterate through inner
                                               // directories

    // constructor
    public DeepFileIterator(File file) throws FileNotFoundException {
        if (file.exists() && file.isDirectory()) {
            dirContents = file.listFiles();
            Arrays.sort(dirContents);
        } else {
            throw new FileNotFoundException("Directory not found.");
        }
    }

    /**
     * Returns true if the iteration has more elements.
     * 
     * @return true if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        if (dirContents.length > 0) {  // check if directory is empty
            if ((nextIndex < dirContents.length) && (dirContents[nextIndex].exists())) {
                return true;  // valid next element
            } else if (nextIndex == dirContents.length) {  // no next element in directory
                if (dirContents[nextIndex - 1].isDirectory() && contentsIterator.hasNext()) {
                    return true;
                }
            }
            return false;  // no more elements in directory
        }
        return false;  // directory was empty
    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     * @throws NoSuchElementException - if the iteration has no more elements
     */
    @Override
    public File next() {
        int currentIndex = nextIndex - 1;
        if (currentIndex < 0) {
            currentIndex = 0;
        }

        if (hasNext() || contentsIterator.hasNext()) {
            if (dirContents[currentIndex].isDirectory()) {
                if (nextIndex > 0) {
                    if (!contentsIterator.equals(null)) {
                        if (contentsIterator.hasNext()) {
                            return contentsIterator.next();

                        } else {
                            contentsIterator = null;
                        }
                    }
                }
            }
            if (!dirContents[nextIndex].isDirectory()) {
                if (nextIndex < dirContents.length) {
                    nextIndex++;
                }
                return dirContents[nextIndex - 1];

            } else {
                try {
                    contentsIterator = new DeepFileIterator(dirContents[nextIndex]);
                    if (nextIndex < dirContents.length) {
                        nextIndex++;
                    }
                    return dirContents[nextIndex - 1];
                } catch (FileNotFoundException e) {
                    System.out.println(
                            "DeepFileIterator unexpectedly threw a FileNotFoundException.");
                    contentsIterator = null;
                }
            }
        } else {
            throw new NoSuchElementException("File not found in system.");
        }
        if (nextIndex < dirContents.length) {
            nextIndex++;
        }
        return dirContents[nextIndex - 1];
    }
}
