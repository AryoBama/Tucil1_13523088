package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {

    public static boolean isExistFile(String fileName) {
        File file = new File("IO/",fileName);
        return file.exists();
    }

    public static boolean isAllSpace(String line){
        for (int i = 0; i < line.length(); i++){
            if (line.charAt(i) != ' '){
                return false;
            }
        }
        return true;
    }

    public static boolean isValidRowPiece(String line){
        if (!line.isEmpty()){
            char alphabet='a';
        
            for (int i = 0; i < line.length(); i++){
                boolean isAlphabet = (Character.toUpperCase(line.charAt(i)) >= 'A' &&  Character.toUpperCase(line.charAt(i)) <= 'Z');
                if(isAlphabet){
                    alphabet = line.charAt(i);
                }
            }

            for (int i = 0; i < line.length(); i++){
                boolean isAlphabet = (Character.toUpperCase(line.charAt(i)) >= 'A' &&  Character.toUpperCase(line.charAt(i)) <= 'Z');
                if (isAlphabet){
                    if(alphabet != line.charAt(i)){
                        return false;
                    }
                }
            }  
            return true;
        }
        return false;
    }

    public static Grid readCase(String fileName, ArrayList<Piece> allPieces){
        fileName = "IO/" + fileName;
        File file = new File(fileName);
        Grid grid = null;

        try(Scanner scanner = new Scanner(file)) {
            int nPiece = 0;
            if (scanner.hasNextLine()){
                int n = scanner.nextInt();
                int m = scanner.nextInt();
                grid = new Grid(n,m);
                nPiece = scanner.nextInt();
            }
            if (scanner.hasNextLine()){
                grid.type = scanner.nextLine();
                grid.type = scanner.nextLine();

            }

            boolean readPiece = false;
            Piece newPiece = null;
            char currentAlphabet = '!';// inisalisasi ajah
            int y = 0;
            String line = "";

            while (nPiece != 0){
                if (scanner.hasNextLine()){
                    line = scanner.nextLine();
                }
                

                if(!isValidRowPiece(line) || isAllSpace(line) || line.isEmpty()){ // kalau dalam satu baris ga valid misal ada piece 'AAB'
                    System.out.println();
                    System.out.println("found invalid line. Failed add line");
                
                    readPiece = false;
                    continue;
        
                }
                
                for(int i = 0; i < line.length(); i++){
                    if((line.charAt(i) != ' ' && currentAlphabet != line.charAt(i)) || (!scanner.hasNextLine() && line.charAt(i) != ' ')){
                        currentAlphabet = line.charAt(i);
                        if (scanner.hasNextLine() || currentAlphabet != newPiece.alphabet){
                            readPiece = false;
                        }
                        
                        if (newPiece != null){

                            if (!grid.isPieceExist(newPiece)){

                    
                                allPieces.add(newPiece);
                                grid.addPiece(newPiece);
                                nPiece--;
                                break;

                            }else{
                                System.out.println("Piece already in grid");
                            }
                        }
                        
                    }
                }
                if (nPiece == 0 && !readPiece){
                    break;
                }
                

                if (!readPiece){ // kalau masih belum masukin piece atau transisi ke piece yang beda
                    y = 0;
                    newPiece = new Piece(Character.toUpperCase(currentAlphabet));
    
                    if (scanner.hasNextLine()){
                        readPiece = true;
                    }
                    for(int i = 0; i < line.length(); i++){
                        if(line.charAt(i) != ' '){
                            newPiece.addPoint(i, y);
                        }
                    }
                }else{
                    for(int i = 0; i < line.length(); i++){
                        if(line.charAt(i) != ' '){
                            newPiece.addPoint(i,y);
                        }
                    }
                }
                y++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return grid;
    
    }
}
