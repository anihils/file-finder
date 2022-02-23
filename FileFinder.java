import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileFinder {

  public static void main(String[] args) {    

    String dirName = System.getProperty("user.dir");
    Scanner sc = new Scanner(System.in);
    System.out.println("Input name of file (with extension):");
    String fileName = sc.next();
    sc.close();
    
    // return file path
    File home = new File(dirName); // searching home directory
    FileFinder finder = new FileFinder();
    System.out.println(finder.fileMatch(home, fileName));
  }

  public String fileMatch(File folder, String fileName) {
    try {
      FilteredFileIterator filter = new FilteredFileIterator(folder, fileName);
      File file = filter.next();
      return file.getAbsolutePath();
    } catch (FileNotFoundException fnf) {
        return "Error! File not found.";
    } catch (Exception e) {
      return "Unexpected exception:" + e.getStackTrace();
    }
  }
}