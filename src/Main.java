package src;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selamat datang di IQ puzzler solver");
        boolean done = false;
        while (true){
            String fileName;

            do { 
                System.out.print("Masukkan nama file: ");
                fileName = scanner.nextLine();
                if(ReadFile.isExistFile(fileName)){
                    System.out.println("Mencari solusi...");
                    break;
                }
                System.out.println("File tidak ditemukan. Letakkan file pada folder IO");
                
            } while (true);

            ArrayList<Piece> allPieces = new ArrayList<>();
            Grid board = ReadFile.readCase(fileName, allPieces);
            long counter;
            ArrayList<char[][]> solutions = new ArrayList<>();

            long startTime = System.currentTimeMillis();

            Algorithm.allSolution(solutions, board, allPieces, 0, allPieces.size()-1);

            long endTime = System.currentTimeMillis();

            counter = Algorithm.counter;

            if (solutions.isEmpty()){
                System.out.println("No solution found");
            }else{
                char[][] solution = solutions.get(0);
                System.out.println("Solusi ditemukan !");
                for (int i = 0; i < solution.length; i++){
                    for (int j = 0; j < solution[0].length; j++){
                        System.out.print(Mapping.findColor(solution[i][j]) + solution[i][j] + Mapping.RESET);
                    }
                    System.out.println("");
                }
            }
            System.out.println("");
            
            long elapsedTime = endTime - startTime;
            System.out.println("Waktu eksekusi: " + elapsedTime + " ms" + "\n");
            System.out.println("Jumlah iterasi: " + counter + "\n");

            String choice;

            while (true){
                System.out.print("Apakah anda ingin menyimpan solusi? (Ya/Tidak): ");
                choice = scanner.nextLine();
                String ya = "YA";
                String tidak = "TIDAK";
                if(ya.equals(choice.toUpperCase()) ){
                    System.out.print("Masukkan nama file: ");
                    fileName = scanner.nextLine();
                    WriteFile.WriteSolution(fileName, solutions, elapsedTime, counter);
                    done = true;
                    break;
                }else if(tidak.equals(choice.toUpperCase())){
                    System.out.println("Terimakasih sudah menggunakan solver ini");
                    done = true;
                    break;
                }else{
                    System.out.println("Pilihan tidak valid");
                }  
            }
            if (done){
                break;
            }
        }
        scanner.close();
    }
}
