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
			PreparedStatement ps=connection.prepareStatement("select expert_id,student_id,name,date,ppi_taken from assign,records "
					+ "where assign.student_id=records.rno");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Assign a=new Assign();
				a.setExpert(rs.getString(1));
				a.setStudId(rs.getString(2));
				a.setStudName(rs.getString(3));
				a.setDate(rs.getString(4));
				a.setPpi(rs.getInt(5));
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

	
	public static int getPPI(String id){

	    ResultSet rs = null;
	    int res=0;
	    try{
	    	connection = ConnectionFactory.getConnection();
	        PreparedStatement ps = connection.prepareStatement("select ppi_taken from assign where student_id=?");
	        ps.setString(1, id);
	        rs = ps.executeQuery();
	        while(rs.next()){
	        	res=rs.getInt(1);
	        }
	        //return res;
	    }
	    catch(SQLException s){
	        s.printStackTrace();
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	    finally{
			
			try {
				rs.close();
				ConnectionFactory.close(connection);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	    }
		return res;
	}

}
