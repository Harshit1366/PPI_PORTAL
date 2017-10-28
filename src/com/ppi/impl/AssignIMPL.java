package com.ppi.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ppi.database.ConnectionFactory;
import com.ppi.model.Assign;

public class AssignIMPL {
	
static Connection connection = null;
	
	public List<Assign> getAssigned(){
		List<Assign> list=new ArrayList<Assign>();
		
		try{
			connection = ConnectionFactory.getConnection();
			PreparedStatement ps=connection.prepareStatement("select expert_id,student_id,name,ppi_taken from assign,records "
					+ "where assign.student_id=records.rno");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Assign a=new Assign();
				a.setExpert(rs.getString(1));
				a.setStudId(rs.getString(2));
				a.setStudName(rs.getString(3));
				a.setPpi(rs.getInt(4));
				list.add(a);
			}
			connection.close();
			rs.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
	
	
	public static void updateAssign(String id){

        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps1=connection.prepareStatement("update assign set ppi_taken=? where student_id = ?");
            ps1.setInt(1,1);
            ps1.setString(2,id);
          
            ps1.executeUpdate();
            
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
