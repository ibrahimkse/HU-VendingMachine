import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {

    public ReadFromFile() { }

    public File openFile(String fileToRead){
        File file = new File(fileToRead);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
    public ArrayList<String> readFile(File file){
        ArrayList<String> myArrayList = new ArrayList<>();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                myArrayList.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return myArrayList;
    }
    public static void writeFile(File file, String line){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath(),true));
            if (file.length() != 0) {
                writer.newLine();
            }
            writer.write(line);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
