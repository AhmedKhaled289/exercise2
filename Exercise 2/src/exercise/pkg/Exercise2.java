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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moj04272
 */
public class Exercise2 {

    public ReadWriteFiles rwFiles = new ReadWriteFiles();
    public OrderDTO orderDTO = new OrderDTO();
    private List<OrderDTO> orders = new ArrayList<OrderDTO>();
    public List items = new ArrayList();
    public List brands = new ArrayList();
    public List<String[]> rows = new LinkedList<String[]>();

    public static void main(String[] args) {

        Exercise2 exercise = new Exercise2();
        exercise.readWriteOrders();

    }

    public void readWriteOrders() {
        orders = rwFiles.readFile();
        getOrderItems(orders);
        writeFileData();
    }

    public void writeFileData() {
        double quantity;
        for (Object item : items) {
            quantity = getQuantityOfBrand(item.toString());
            double total = getOrders().size();
            double avarage = quantity / total;
            rows.add(new String[]{item.toString(), String.valueOf(avarage)});
        }
        try {
            rwFiles.writeDataAtOnce(rows, "0_order_log00");

            rows = new LinkedList<String[]>();
            for (Object item : items) {
                getItemBrands(item.toString());

                rows.add(new String[]{item.toString(), mostPopularBrand()});
            }

            rwFiles.writeDataAtOnce(rows, "1_order_log00");
        } catch (IOException ex) {
            Logger.getLogger(Exercise2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getOrderItems(List<OrderDTO> customers) {
        for (OrderDTO cusDTO : customers) {
            boolean notFound = false;
            if (items.isEmpty()) {
                items.add(cusDTO.getItem());
            } else {
                for (int i = 0; i < items.size(); i++) {

                    if (cusDTO.getItem().equals(items.get(i))) {
                        notFound = false;
                        break;
                    } else {
                        notFound = true;
                    }
                }
                if (notFound) {
                    items.add(cusDTO.getItem());
                }
            }
        }
    }

    public int getQuantityOfBrand(String item) {
        int quantity = 0;
        for (OrderDTO orderDTO : getOrders()) {
            if (orderDTO.getItem().equals(item)) {
                quantity += orderDTO.getQuantity();
            }
        }
        return quantity;
    }

    public String mostPopularBrand() {
        String brand = null;
        int count, maxCount = 0;
        for (int i = 0; i < brands.size(); i++) {
            count = 1;
            for (int j = i + 1; j < brands.size(); j++) {
                if (brands.get(i).equals(brands.get(j))) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                brand = brands.get(i).toString();
            }
        }
        return brand;
    }

    public void getItemBrands(String item) {
        brands = new ArrayList();
        for (OrderDTO orderDTO : getOrders()) {

            if (orderDTO.getItem().equals(item)) {
                boolean notFound = false;
                if (brands.isEmpty()) {
                    brands.add(orderDTO.getBrand());
                } else {
                    for (int i = 0; i < brands.size(); i++) {

                        if (orderDTO.getBrand().equals(brands.get(i))) {
                            notFound = false;
                            break;
                        } else {
                            notFound = true;
                        }
                    }
                    if (notFound) {
                        brands.add(orderDTO.getBrand());
                    }
                }
            }
        }
    }

    /**
     * @return the orders
     */
    public List<OrderDTO> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

}
