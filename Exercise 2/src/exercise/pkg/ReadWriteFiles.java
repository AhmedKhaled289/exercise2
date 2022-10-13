/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise.pkg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moj04272
 */
public class ReadWriteFiles {

    public String line = "";
    public String splitBy = ",";
    public OrderDTO orderDTO = new OrderDTO();
    public List<OrderDTO> orders = new ArrayList<OrderDTO>();

    public List<OrderDTO> readFile() {
        try {
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader("order_log00.csv"));
            while ((line = br.readLine()) != null) //returns a Boolean value  
            {
                String[] order = line.split(splitBy);
                //use comma as separator  
                orderDTO = new OrderDTO();
                orderDTO.setName(order[0]);
                orderDTO.setArea(order[1]);
                orderDTO.setItem(order[2]);
                orderDTO.setQuantity(Integer.parseInt(order[3]));
                orderDTO.setBrand(order[4]);
                orders.add(orderDTO);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void writeDataAtOnce(List<String[]> rows, String FileName) throws IOException {

        File csvFile = new File(FileName + ".csv");
        FileWriter fileWriter = new FileWriter(csvFile);
        for (String[] data : rows) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                line.append("\"");
                line.append(data[i].replaceAll("\"", "\"\""));
                line.append("\"");
                if (i != data.length - 1) {
                    line.append(',');
                }
            }
            line.append("\n");
            fileWriter.write(line.toString());
        }
        fileWriter.close();
    }

}
