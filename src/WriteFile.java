package src;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFile {
    public static void WriteSolution(String fileName, ArrayList<char[][]> solution,long time, long iteration){

        try {
            fileName = "IO/" + fileName;
            FileWriter writer = new FileWriter(fileName);
            if(solution.isEmpty()){
                writer.write("No Solution Found!\n");
            }else{
                for (int i = 0; i < solution.get(0).length; i++){
                    for (int j = 0; j < solution.get(0)[0].length; j++){
                        writer.write(solution.get(0)[i][j]);
                    }
                    writer.write("\n");
                }
            }
            writer.write("Waktu eksekusi: " + Long.toString(time) + " ms" + "\n");
            writer.write("Banyak iterasi: " + Long.toString(iteration) + "\n");
            writer.close();
            System.out.println("Berhasil menulis solusi.");

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan.");
            e.printStackTrace();
        }
    }
}
