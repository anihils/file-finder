import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class FilteredFileIterator implements Iterator<File> {

  // private fields
  DeepFileIterator fileIterator; // steps through all files/folders within the initial directory
  String searchPattern; // a String that must be part of a file’s name
  File nextMatchingFile; // a File reference to the next file that this iterator will return

  public FilteredFileIterator(File folder, String pattern) throws FileNotFoundException {
    if (folder.exists()) {
      fileIterator = new DeepFileIterator(folder);
    } else {
      throw new FileNotFoundException("Directory not found in system.");
    }
    this.searchPattern = pattern;
    nextMatchingFile = (File) nextHelper();
  }

  /**
   * Returns true if the iteration has more valid elements.
   * 
   * @return true if the iteration has more valid elements
   */
  @Override
  public boolean hasNext() {
    if (nextMatchingFile == null) {
      return false;
    }
    return true;
  }

  /**
   * Returns the next element in the iteration that contains the String searchPattern
   * 
   * @return the next element in the iteration
   * @throws NoSuchElementException - if the iteration has no more valid elements
   */
  @Override
  public File next() throws NoSuchElementException {
    File fileToReturn = nextMatchingFile;
    if (!hasNext()) {
      throw new NoSuchElementException("File not found in system.");
    }
    nextHelper();
    return fileToReturn;

  }

  /**
   * This method can repeatedly call next on the fileIterator, until it returns a file that contains
   * the specified searchPattern. This file can then be stored within nextMatchingFile. If the
   * fileIterator is exhausted before a matching file can be found, then null can be stored in
   * nextMatchingFile instead.
   * 
   * @return the next element in the iteration
   */
  private File nextHelper() {
    File currentFile;
    String fileName = "";

    do {
      if (!fileIterator.hasNext()) {
        nextMatchingFile = null;
        return nextMatchingFile;
      }
      currentFile = (File) fileIterator.next();
      if (currentFile != null) {
        fileName = currentFile.getName();
      }
    } while (!fileName.equals(searchPattern));
    nextMatchingFile = currentFile;
    return nextMatchingFile;
  }
}