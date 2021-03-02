import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;
import java.io.*;
import java.net.*;


public class main{
    
    public static void main(String[] args) {
        if (checkCharity("StJohn")){
            System.out.println("found");
        }else{
            System.out.println("not found");
        }
    }
    
    public static boolean checkCharity(String name){
    /* checkCharity retrieves the list of charities from trademe and returns true if the inputted charity name is part of the list
        @param name     The name of the charity to check for
        @return boolean True if charity found false otherwise
        @throws         IO Exceptions
    */
        try{
            URL page = new URL("https://api.trademe.co.nz/v1/Charities.xml");
            URLConnection file = page.openConnection();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();             
            DocumentBuilder db = dbf.newDocumentBuilder(); 
            Document doc = db.parse(file.getInputStream());
            doc.getDocumentElement().normalize(); 
            NodeList data = doc.getElementsByTagName("CharityType");
            int i = 0;
            if (data.getLength()==0){
                return false;
            }
            do{
               if (name.equals(data.item(i).getTextContent())){
                    return true;
                }
                i++;
                
            }while(i<data.getLength());
 
            return false;
        }catch(Exception e){
            throw new AssertionError(e);
        }
    }
}
    