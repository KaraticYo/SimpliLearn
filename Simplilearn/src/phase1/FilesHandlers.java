package phase1;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilesHandlers {

	static String path = "D:\\";
	static String Filename = "CN Syllabus - Copy.pdf";
	static String DeleteFilename = "CN Syllabus.pdf";
	static Scanner Obj = new Scanner(System.in);
	
	@SuppressWarnings("resource")
	
	public static void main(String[] args) throws IOException {

		System.out.println("\t\tWELCOME TO FILE ORGANIZERS\t\t");
		System.out.println("");
		System.out.println("\t\tDeveloper Name:Karthik\t\t");
		System.out.println("");
		Menu() ;
	}
	
	public static void Menu() throws IOException {

		int ch;
		do {
			System.out.println("Enter your choice");
			System.out.println("\t\t1.Displaying all the files");
			System.out.println("\t\t2.File Opeartions");
			System.out.println("\t\t3.Exit");

			ch = Integer.parseInt(Obj.nextLine());
			switch (ch) {
			case 1:
				listAllFiles(path);
				System.out.println("");
				break;
			case 2:
				Operations();
				System.out.println("");
				break;
			case 3:
				System.exit(0);
				System.out.println("");
				break;
			default:
				System.out.println("invalid option");
				break;
			}
		} while (ch > 0);

	}

	public static void Operations() throws IOException {
		
		String File;
		int ch;
		do {

			System.out.println("Please select Operation");
			System.out.println("\t\t1.Adding files to the existing directory");
			System.out.println("\t\t2.Deleting the file");
			System.out.println("\t\t3.Searching the file");
			System.out.println("\t\t4.MainMenu");

			ch = Integer.parseInt(Obj.nextLine());
			switch (ch) {
			case 1:
				System.out.println("Enter FileName:");
				File = Obj.nextLine();
				createNewFile(path, File);
				System.out.println("");
				break;
			case 2:
				System.out.println("Enter FileName:");
				File = Obj.nextLine();
				deleteFile(path, File);
				System.out.println("");
				break;
			case 3:
				System.out.println("Enter FileName:");
				File = Obj.nextLine();
				searchFile(path, File);
				System.out.println("");
				break;
			case 4:
				Menu() ;
				System.out.println("");
				break;
			default:
				System.out.println("invalid option");
				break;
			}
		} while (ch > 0);
	}
	
	public static void listAllFiles(String path) {

		if (path == null || path.isEmpty())
			throw new NullPointerException("Path cannot be Empty or null");

		File dir = new File(path);

		if (!dir.exists())
			throw new IllegalArgumentException("Path does not exist");

		if (dir.isFile())
			throw new IllegalArgumentException("The given path is a file. A directory is expected.");

		String[] files = dir.list();

		System.out.println("\n***********************************");
		if (files != null && files.length > 0) {

			Set<String> filesList = new TreeSet<String>(Arrays.asList(files));
			System.out.println("The Files in " + dir.getAbsolutePath() + " are: \n");
			int k=1;
			for (String file1 : filesList) {

				System.out.println(k+")"+file1);
				k++;
			}

			System.out.println("\nTotal Number of files: " + filesList.size());
			System.out.println("\n***********************************");
		} else {

			System.out.println("Directory is Empty");
		}

	}

	public static void createNewFile(String path, String fileName) throws IOException {
		
		
		if (path == null || path.isEmpty())
			throw new NullPointerException("Path cannot be Empty or null");

		if (fileName == null || fileName.isEmpty())
			throw new NullPointerException("File Name cannot be Empty or null");

		File newFile = new File(path + File.separator + fileName);

		boolean createFile = newFile.createNewFile();

		if (createFile) {

			System.out.println("\nFile Successfully Created: " + newFile.getAbsolutePath());

		} else if (!createFile) {

			System.out.println("\nFile Already Exist.. Please try again.");

		}

	}

	public static void deleteFile(String path, String fileName) throws IOException {

		if (path == null || path.isEmpty())
			throw new NullPointerException("Path cannot be Empty or null");

		if (fileName == null || fileName.isEmpty())
			throw new NullPointerException("File Name cannot be Empty or null");

		File newFile = new File(path + File.separator + fileName);

		boolean deleteFile = newFile.delete();

		if (deleteFile) {

			System.out.println("\nFile deleted Successfully");

		} else {

			System.out.println("\nFile Not Found.. Please try again.");

		}

	}

	public static void searchFile(String path, String fileName) {

		if (path == null || path.isEmpty())
			throw new NullPointerException("Path cannot be Empty or null");

		if (fileName == null || fileName.isEmpty())
			throw new NullPointerException("File Name cannot be Empty or null");

		File dir = new File(path);

		if (!dir.exists())
			throw new IllegalArgumentException("Path does not exist");

		if (dir.isFile())
			throw new IllegalArgumentException("The given path is a file. A directory is expected.");

		String[] fileList = dir.list();
		boolean flag = false;

		Pattern pat = Pattern.compile(fileName);

		if (fileList != null && fileList.length > 0) {
			for (String file : fileList) {
				Matcher mat = pat.matcher(file);
				if (mat.matches()) {
					System.out.println("File Found at location: " + dir.getAbsolutePath());
					flag = true;
					break;
				}
			}
		}

		if (flag == false)
			System.out.println("File Not Found.. Please try again.");

	}

}