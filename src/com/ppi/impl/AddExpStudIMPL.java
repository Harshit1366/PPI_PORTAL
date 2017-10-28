package com.ppi.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ppi.database.ConnectionFactory;

public class AddExpStudIMPL {
	
	static Connection connection = null;
	

	public static void addExpert(String name,String id,String password){
		
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into expert_login values(?,?,?)");
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, id);
            ps.execute();
         
        }
        catch(SQLException s){
            s.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            ConnectionFactory.close(connection);
        }

    }

	public static void addStudent(String name,String id,String password){
		
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into student_login values(?,?,?)");
            ps.setString(1, name);
            ps.setString(2, id);
            ps.setString(3, password);
            ps.execute();
         
        }
        catch(SQLException s){
            s.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            ConnectionFactory.close(connection);
        }

    }
}
