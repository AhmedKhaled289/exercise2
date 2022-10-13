/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import exercise.pkg.Exercise2;
import exercise.pkg.ReadWriteFiles;
import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moj04272
 */
public class JUnitTest {
    
    public JUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
 @Test
    public void test_File_Exist(){
        File file = new File("order_log00.csv");
        assertTrue(file.exists());

    }
    
    @Test
    public void test_Read_File(){
    ReadWriteFiles readWriteFiles=new ReadWriteFiles();
     int recordsSize=readWriteFiles.readFile().size();
     assertEquals("Checking size of List", 5, recordsSize);
    }
    
     @Test
    public void test_Most_Popular_Brand_At_Order(){
    Exercise2 exercise = new Exercise2();
      ArrayList<String> brands = new ArrayList<String>();
    brands.add("Rowe and Legros");
    brands.add("Hilll-Gorczany");
    brands.add("Rowe and Legros");
    brands.add("Hilll-Gorczany");
    brands.add("Hilll-Gorczany");
    exercise.setBrands(brands);
  String mostPubularBrand=exercise.mostPopularBrand();
  assertEquals("Most Pubular Brand", "Hilll-Gorczany", mostPubularBrand);
    
    }
}
