import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileFinder {

  public static void main(String[] args) {    

    String dirName = System.getProperty("user.dir");
    
    Scanner sc = new Scanner(System.in);
    System.out.println("Input name of file with relevant extension:");
    String fileName = sc.next();
    sc.close();
    
    // return file path
    File folder = new File(dirName);
    FileFinder finder = new FileFinder();
    String result = finder.fileMatch(folder, fileName);
    System.out.println(result);
  }

  public String fileMatch(File folder, String fileName) {
    try {
      FilteredFileIterator filter = new FilteredFileIterator(folder, fileName);
      File file = filter.next();
      return file.getAbsolutePath();
    } catch (FileNotFoundException fnf) {
        String error = "Error! File not found.";
      return error;
    }
  }
}
