package com.ppi.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ppi.database.ConnectionFactory;
import com.ppi.model.ExpKnowledge;
import com.ppi.model.ExpRemarks;
import com.ppi.model.ExpSkills;
import com.ppi.model.Record;



public class ExpRecordsIMPL {
	
static Connection connection = null;

public List<Record> getStudents(){

	List<Record> list=new ArrayList<Record>();

    ResultSet rs = null;
    try{
    	connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from records");
        rs = ps.executeQuery();
        while(rs.next()){
        	Record r =new Record();
			r.setRoll(rs.getString(1));
			r.setName(rs.getString(2));
			r.setAssign(rs.getInt(3));
            list.add(r);
        }
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
	return list;
}


	
	public static ExpKnowledge getRecordById(String id){

        ExpKnowledge k = new ExpKnowledge();

        ResultSet rs = null;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from expKnowledge where roll_no=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
            	k.setRoll(rs.getString(1));
				k.setName(rs.getString(2));
				k.setData(rs.getString(3));
				k.setLogic(rs.getString(4));
				k.setCao(rs.getString(5));
				k.setDbms(rs.getString(6));
				k.setOs(rs.getString(7));
				k.setCn(rs.getString(8));
				k.setApp(rs.getString(9));
				k.setTotal(rs.getString(10));
                return k;
            }
            else{
                return null;
            }
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
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		return k;
    }

	
	public static List<ExpKnowledge> getKnowledge(){

		List<ExpKnowledge> list=new ArrayList<ExpKnowledge>();

        ResultSet rs = null;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from expKnowledge");
            rs = ps.executeQuery();
            while(rs.next()){
            	ExpKnowledge k =new ExpKnowledge();
				k.setData(rs.getString(3));
				k.setLogic(rs.getString(4));
				k.setCao(rs.getString(5));
				k.setDbms(rs.getString(6));
				k.setOs(rs.getString(7));
				k.setCn(rs.getString(8));
				k.setApp(rs.getString(9));
				k.setTotal(rs.getString(10));
                list.add(k);
            }
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
		return list;
    }

	
	public static List<ExpSkills> getSkills(){

		List<ExpSkills> list=new ArrayList<ExpSkills>();

        ResultSet rs = null;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from expSkills");
            rs = ps.executeQuery();
            while(rs.next()){
            	ExpSkills s =new ExpSkills();
				s.setTeam(rs.getString(3));
				s.setEnth(rs.getString(4));
				s.setConf(rs.getString(5));
				s.setClean(rs.getString(6));
				s.setOral(rs.getString(7));
				s.setLang(rs.getString(8));
				s.setProb(rs.getString(9));
				s.setSkill(rs.getString(10));
				s.setTotal(rs.getString(11));
                list.add(s);
            }
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
		return list;
    }

	
	public static ExpSkills getSkillsById(String id){

		ExpSkills s = new ExpSkills();

        ResultSet rs = null;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from expSkills where roll_no=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
            	s.setRoll(rs.getString(1));
				s.setName(rs.getString(2));
				s.setTeam(rs.getString(3));
				s.setEnth(rs.getString(4));
				s.setConf(rs.getString(5));
				s.setClean(rs.getString(6));
				s.setOral(rs.getString(7));
				s.setLang(rs.getString(8));
				s.setProb(rs.getString(9));
				s.setSkill(rs.getString(10));
				s.setTotal(rs.getString(11));
                connection.close();
                return s;
            }
            else{
                return null;
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                rs.close();
                ConnectionFactory.close(connection);
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		return s;
    }

	
	public static ExpRemarks getRemarksById(String id){

		ExpRemarks r = new ExpRemarks();

        ResultSet rs = null;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from expRemarks where id=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
            	r.setRoll(rs.getString(1));
				r.setRemarks(rs.getString(2));
                connection.close();
                return r;
            }
            else{
                return null;
            }
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
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		return r;
    }

	
	public static void addKnowledge(String id,String name,String ds, String log,String cao,String dbms,String os, String cn, String app){
		
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into expKnowledge values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, ds);
            ps.setString(4, log);
            ps.setString(5, cao);
            ps.setString(6, dbms);
            ps.setString(7, os);
            ps.setString(8, cn);
            ps.setString(9, app);
            int a=Integer.valueOf(ds);
			int b=Integer.valueOf(log);
			int c=Integer.valueOf(cao);
			int d=Integer.valueOf(dbms);
			int e=Integer.valueOf(os);
			int f=Integer.valueOf(cn);
			int g=Integer.valueOf(app);
			ps.setString(10, String.valueOf(a+b+c+d+e+f+g));

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
	
	public static void addSkills(String id,String name,String team,String enth,String conf,String clean,String oral,String lang,String prob,String skill){

        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into expSkills values(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, team);
            ps.setString(4, enth);
            ps.setString(5, conf);
            ps.setString(6, clean);
            ps.setString(7, oral);
            ps.setString(8, lang);
            ps.setString(9, prob);
            ps.setString(10, skill);
            int a=Integer.valueOf(team);
			int b=Integer.valueOf(enth);
			int c=Integer.valueOf(conf);
			int d=Integer.valueOf(clean);
			int e=Integer.valueOf(oral);
			int f=Integer.valueOf(lang);
			int g=Integer.valueOf(prob);
			int h=Integer.valueOf(skill);
			ps.setString(11, String.valueOf(a+b+c+d+e+f+g+h));
			
			ps.execute();
           
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            ConnectionFactory.close(connection);
        }
		
    }
	
	public static void addRemarks(String id,String remark){

        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into expRemarks values(?,?)");
            
            ps.setString(1, id);
            ps.setString(2, remark);
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

	
	public static void updateKnowledge(String id,String ds, String log,String cao,String dbms,String os, String cn, String app){

        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("update expKnowledge set data_structure=?,"
            		+ "logic_building=?,cao=?,dbms=?,os=?,computer_network=?,app_dev=?,total_score=? where roll_no=?");
            ps.setString(1, ds);
            ps.setString(2, log);
            ps.setString(3, cao);
            ps.setString(4, dbms);
            ps.setString(5, os);
            ps.setString(6, cn);
            ps.setString(7, app);
            int a=Integer.valueOf(ds);
			int b=Integer.valueOf(log);
			int c=Integer.valueOf(cao);
			int d=Integer.valueOf(dbms);
			int e=Integer.valueOf(os);
			int f=Integer.valueOf(cn);
			int g=Integer.valueOf(app);
			ps.setString(8, String.valueOf(a+b+c+d+e+f+g));
            ps.setString(9, id);
            ps.executeUpdate();
            
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
	
	public static void updateSkills(String id,String team,String enth,String conf,String clean,String oral,String lang,String prob,String skill){

    
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("update expSkills set team_spirit=?,enthusiasm=?,self_conf=?"
            		+ ",cleanliness=?,oral_comm=?,body_lang=?,prob_solving=?,analytical_skill=?,total_score=? where roll_no=?");
            ps.setString(1, team);
            ps.setString(2, enth);
            ps.setString(3, conf);
            ps.setString(4, clean);
            ps.setString(5, oral);
            ps.setString(6, lang);
            ps.setString(7, prob);
            ps.setString(8, skill);
            int a=Integer.valueOf(team);
			int b=Integer.valueOf(enth);
			int c=Integer.valueOf(conf);
			int d=Integer.valueOf(clean);
			int e=Integer.valueOf(oral);
			int f=Integer.valueOf(lang);
			int g=Integer.valueOf(prob);
			int h=Integer.valueOf(skill);
			ps.setString(9, String.valueOf(a+b+c+d+e+f+g+h));
            ps.setString(10, id);
            ps.executeUpdate();
           
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            ConnectionFactory.close(connection);
        }
	
    }
	
	public static void updateRemarks(String id,String remark){

        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("update expRemarks set remarks=? where id=?");
            ps.setString(1, remark);
            ps.setString(2, id);
            ps.executeUpdate();
            
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
