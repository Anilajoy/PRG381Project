package LayeredArchitecture.PresentationLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import LayeredArchitecture.BusinessLayer.Customer;
import LayeredArchitecture.BusinessLayer.CustomerUser;
import LayeredArchitecture.BusinessLayer.User;
import LayeredArchitecture.DataAccessLayer.CustomerDH;
import LayeredArchitecture.DataAccessLayer.CustomerUserDH;
import LayeredArchitecture.DataAccessLayer.DataAccessLayer;
import LayeredArchitecture.DataAccessLayer.UserDH;

public class ClientPL 
{
    
    private static UserDH UserDH = new UserDH();
    private static CustomerUserDH CusUsDh = new CustomerUserDH();
    private static CustomerDH CusDH = new CustomerDH();
    private static Customer CustomerOBJ = new Customer();
    private static int UserID;
    boolean ClientFound = false ;
    public void ClientSignIn() 
    {
         
        while(ClientFound==false)
        {
        try
        {
            
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("Please Enter Client User Name :");

            Scanner RdClientUser = new Scanner(System.in);
            String strClientUser= RdClientUser.nextLine();
            System.out.printf("Please Enter Password for User %s:", strClientUser);
            System.out.println("");

            String strClientPass= RdClientUser.nextLine();
            
            List<User> UserList = UserDH.ReadAllUsers() ;

            for (User userItem : UserList) 
            {
                if(strClientUser.matches(userItem.getUser_Name()) && strClientPass.matches(userItem.getUser_Password()))
                {
                    UserID = userItem.getUser_ID();
                    ClientFound = true ;
                }
                
            }

            if(ClientFound==true)
            {
                System.out.println("Logged in Successfuly");
            
                CustomerUser CusUSOBJ = CusUsDh.ReadCustomerUser(UserID);
                CustomerOBJ = CusDH.ReadCustomer(CusUSOBJ.getCustomer_ID());
                ClientMenu();
                

            }
            else
            {
                System.out.println("Failed. Please try again");
            }
           
              
        

        }
        catch(Exception e)
        {

        }
        }
    }

    public void ClientMenu()
    {
        System.out.println(CustomerOBJ.getCustomer_Email());
        System.out.println("Client Menu");
    }


    public boolean ClientLogin() 
    {

        return true;
    }
    
}