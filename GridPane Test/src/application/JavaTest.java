package application;
import junit.framework.TestCase;


public class JavaTest extends TestCase {
   protected String value1, value2;
   
   // assigning the values
   protected void setUp(){
      value1= "hello ";
      value2="world";
   }

   // test method to add two values
   public void testAdd(){
       value1 = value1.concat(value2);
      System.out.println(value1);
      assertTrue(value1 == "hello world");
   }
}