import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        // arg commands
        String partsString = args[0];
        String itemsString = args[1];
        String tokensString = args[2];
        String tasksString = args[3];
        String outputString = args[4];

        // reading all the files
        ReadFromFile readFromFile = new ReadFromFile();
        File partsFile = readFromFile.openFile(partsString);
        File itemsFile = readFromFile.openFile(itemsString);
        File tokensFile = readFromFile.openFile(tokensString);
        File tasksFile = readFromFile.openFile(tasksString);
        File outputFile = readFromFile.openFile(outputString);
        ArrayList<String> partArrayList = readFromFile.readFile(partsFile);
        ArrayList<String> itemArrayList = readFromFile.readFile(itemsFile);
        ArrayList<String> tokenArrayList = readFromFile.readFile(tokensFile);
        ArrayList<String> taskArrayList = readFromFile.readFile(tasksFile);

        // creating stacks for parts
        ArrayList<Stack<String>> stacks = new ArrayList<>();
        for (String part: partArrayList){
            stacks.add(new Stack<>(part));
        }

        //adding items to stack ArrayList
        for (String item: itemArrayList){
            ArrayList<String> itemArr = new ArrayList<>();
            for (String piece : item.split(" ")){
                itemArr.add(piece);
            }
            for (Stack<String> s:stacks){
                if (itemArr.get(1).equals(s.id)){
                    s.push(itemArr.get(0));
                }
            }
        }

        //loop for tasks
        for (String taskLine: taskArrayList){
            ArrayList<String> taskArr = new ArrayList<>();
            for (String piece : taskLine.split("\t")){
                taskArr.add(piece);
            }
            if (taskArr.get(0).equals("BUY")){
                taskArr.remove(0);
                for (String s: taskArr){
                    ArrayList<String> lastArr = new ArrayList<>();
                    for (String piece : s.split(",")){
                        lastArr.add(piece);
                    }
                    for (Stack<String> stack:stacks){
                        if (lastArr.get(0).equals(stack.id)){
                            for (int i = 0; i < Integer.parseInt(lastArr.get(1)); i++){
                                if (!stack.stackArrayList.isEmpty()){
                                    stack.pop();
                                }
                            }
                        }
                    }
                }
            }
            if (taskArr.get(0).equals("PUT")){
                taskArr.remove(0);
                for (String s: taskArr){
                    ArrayList<String> lastArr1 = new ArrayList<>();
                    for (String piece : s.split(",")){
                        lastArr1.add(piece);
                    }
                    for (Stack<String> stack:stacks){
                        if (lastArr1.get(0).equals(stack.id)){
                            lastArr1.remove(0);
                            for (String s1: lastArr1){
                                stack.push(s1);
                            }
                        }
                    }
                }
            }
        }

        //arraylist for token objects
        ArrayList<Token> tokens = new ArrayList<>();
        for (String s: tokenArrayList){
            tokens.add(new Token(s));
        }

        Collections.sort(tokens, Comparator.comparing(h -> h.getPoint(h)));

        //printing to output file
        for (Stack<String> s:stacks){
            ReadFromFile.writeFile(outputFile, s.id + ":");
            Collections.reverse(s.stackArrayList);
            for (String piece: s.stackArrayList){
                ReadFromFile.writeFile(outputFile, piece);
            }
            ReadFromFile.writeFile(outputFile, "---------------");
        }
        ReadFromFile.writeFile(outputFile, "Token Box:");
        for (Token t: tokens){
            ReadFromFile.writeFile(outputFile, t.id);
        }
    }
}
