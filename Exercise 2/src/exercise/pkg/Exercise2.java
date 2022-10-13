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

    private ReadWriteFiles rwFiles = new ReadWriteFiles();
    private OrderDTO orderDTO = new OrderDTO();
    private List<OrderDTO> orders = new ArrayList<OrderDTO>();
    private List items = new ArrayList();
    private List brands = new ArrayList();
    private List<String[]> rows = new LinkedList<String[]>();

    public static void main(String[] args) {

        Exercise2 exercise = new Exercise2();
        exercise.readWriteOrders();

    }

    public void readWriteOrders() {
        setOrders(getRwFiles().readFile());
        getOrderItems(getOrders());
        writeFileData();
    }

    public void getOrderItems(List<OrderDTO> customers) {
        for (OrderDTO cusDTO : customers) {
            boolean notFound = false;
            if (getItems().isEmpty()) {
                getItems().add(cusDTO.getItem());
            } else {
                for (int i = 0; i < getItems().size(); i++) {

                    if (cusDTO.getItem().equals(getItems().get(i))) {
                        notFound = false;
                        break;
                    } else {
                        notFound = true;
                    }
                }
                if (notFound) {
                    getItems().add(cusDTO.getItem());
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

    public void getItemBrands(String item) {
        setBrands(new ArrayList());
        for (OrderDTO orderDTO : getOrders()) {

            if (orderDTO.getItem().equals(item)) {
                boolean notFound = false;
                if (getBrands().isEmpty()) {
                    getBrands().add(orderDTO.getBrand());
                } else {
                    for (int i = 0; i < getBrands().size(); i++) {

                        if (orderDTO.getBrand().equals(getBrands().get(i))) {
                            notFound = false;
                            break;
                        } else {
                            notFound = true;
                        }
                    }
                    if (notFound) {
                        getBrands().add(orderDTO.getBrand());
                    }
                }
            }
        }
    }

    public String mostPopularBrand() {
        String brand = null;
        int count, maxCount = 0;
        for (int i = 0; i < getBrands().size(); i++) {
            count = 1;
            for (int j = i + 1; j < getBrands().size(); j++) {
                if (getBrands().get(i).equals(getBrands().get(j))) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                brand = getBrands().get(i).toString();
            }
        }
        return brand;
    }

    public void writeFileData() {
        double quantity;
        for (Object item : getItems()) {
            quantity = getQuantityOfBrand(item.toString());
            double total = getOrders().size();
            double avarage = quantity / total;
            getRows().add(new String[]{item.toString(), String.valueOf(avarage)});
        }
        try {
            getRwFiles().writeDataAtOnce(getRows(), "0_order_log00");

            setRows(new LinkedList<String[]>());
            for (Object item : getItems()) {
                getItemBrands(item.toString());

                getRows().add(new String[]{item.toString(), mostPopularBrand()});
            }

            getRwFiles().writeDataAtOnce(getRows(), "1_order_log00");
        } catch (IOException ex) {
            Logger.getLogger(Exercise2.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * @return the brands
     */
    public List getBrands() {
        return brands;
    }

    /**
     * @param brands the brands to set
     */
    public void setBrands(List brands) {
        this.brands = brands;
    }

    /**
     * @return the rwFiles
     */
    public ReadWriteFiles getRwFiles() {
        return rwFiles;
    }

    /**
     * @param rwFiles the rwFiles to set
     */
    public void setRwFiles(ReadWriteFiles rwFiles) {
        this.rwFiles = rwFiles;
    }

    /**
     * @return the orderDTO
     */
    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    /**
     * @param orderDTO the orderDTO to set
     */
    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    /**
     * @return the items
     */
    public List getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List items) {
        this.items = items;
    }

    /**
     * @return the rows
     */
    public List<String[]> getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(List<String[]> rows) {
        this.rows = rows;
    }

}
