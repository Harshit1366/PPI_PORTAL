package com.ppi.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ppi.database.ConnectionFactory;
import com.ppi.model.Knowledge;
import com.ppi.model.Record;
import com.ppi.model.Attitude;
import com.ppi.model.Skills;


public class RecordsIMPL {
	
static Connection connection = null;

public static void updateAssess(String id){

    try{
    	connection = ConnectionFactory.getConnection();
        PreparedStatement ps1=connection.prepareStatement("update records set selfAssess=? where rno = ?");
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

public static List<Record> getStudents(){

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

public static String getName(String id){

    ResultSet rs = null;
    String res=null;
    try{
    	connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement("select name from records where rno=?");
        ps.setString(1, id);
        rs = ps.executeQuery();
        while(rs.next()){
        	res=rs.getString(1);
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
	return res;
}


public static int getAssess(String id){

    ResultSet rs = null;
    int res=0;
    try{
    	connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement("select selfAssess from records where rno=?");
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

public static int getFeedback(String id){

    ResultSet rs = null;
    int res=0;
    try{
    	connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement("select feedback from records where rno=?");
        ps.setString(1, id);
        rs = ps.executeQuery();
        while(rs.next()){
        	res=rs.getInt(1);
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
	return res;
}


	public static Knowledge getRecordById(String id){

        Knowledge k = new Knowledge();

        ResultSet rs = null;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from knowledge where roll_no=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
            	k.setRoll_no(rs.getString(1));
				k.setStud_name(rs.getString(2));
				k.setData_structures(rs.getString(3));
				k.setAlgorithms(rs.getString(4));
				k.setLogic_building(rs.getString(5));
				k.setCao(rs.getString(6));
				k.setDbms(rs.getString(7));
				k.setOs(rs.getString(8));
				k.setNetworks(rs.getString(9));
				k.setInfo_security(rs.getString(10));
				k.setSoftware_dev(rs.getString(11));
				k.setSoftware_project(rs.getString(12));
				k.setMobility(rs.getString(13));
				k.setData_mining(rs.getString(14));
				k.setSoftware_metrics(rs.getString(15));
				k.setInternet(rs.getString(16));
				k.setApplication_dev(rs.getString(17));
				k.setBusiness_financial(rs.getString(18));
				k.setBusi_intelligence(rs.getString(19));
				k.setHuman_resource(rs.getString(20));
				k.setModeling_simulation(rs.getString(21));
				k.setEnterprise_resource(rs.getString(22));
				k.setTotal_score(rs.getString(23));
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

	
	public static List<Knowledge> getKnowledge(){

		List<Knowledge> list=new ArrayList<Knowledge>();

        ResultSet rs = null;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from knowledge");
            rs = ps.executeQuery();
            while(rs.next()){
            	Knowledge k =new Knowledge();
            	k.setData_structures(rs.getString(3));
				k.setAlgorithms(rs.getString(4));
				k.setLogic_building(rs.getString(5));
				k.setCao(rs.getString(6));
				k.setDbms(rs.getString(7));
				k.setOs(rs.getString(8));
				k.setNetworks(rs.getString(9));
				k.setInfo_security(rs.getString(10));
				k.setSoftware_dev(rs.getString(11));
				k.setSoftware_project(rs.getString(12));
				k.setMobility(rs.getString(13));
				k.setData_mining(rs.getString(14));
				k.setSoftware_metrics(rs.getString(15));
				k.setInternet(rs.getString(16));
				k.setApplication_dev(rs.getString(17));
				k.setBusiness_financial(rs.getString(18));
				k.setBusi_intelligence(rs.getString(19));
				k.setHuman_resource(rs.getString(20));
				k.setModeling_simulation(rs.getString(21));
				k.setEnterprise_resource(rs.getString(22));
				k.setTotal_score(rs.getString(23));
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

	
	public static List<Skills> getSkills(){

		List<Skills> list=new ArrayList<Skills>();

        ResultSet rs = null;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from skills");
            rs = ps.executeQuery();
            while(rs.next()){
            	Skills s =new Skills();
            	s.setIT_skills(rs.getString(3));
				s.setListening(rs.getString(4));
				s.setOral_comm(rs.getString(5));
				s.setWritten_comm(rs.getString(6));
				s.setBody_lang(rs.getString(7));
				s.setConceptual(rs.getString(8));
				s.setEI(rs.getString(9));
				s.setConflict_reso(rs.getString(10));
				s.setProb_solving(rs.getString(11));
				s.setOrganising(rs.getString(12));
				s.setInter_personal(rs.getString(13));
				s.setSocial(rs.getString(14));
				s.setInnovative(rs.getString(15));
				s.setPro_active(rs.getString(16));
				s.setMulti_tasking(rs.getString(17));
				s.setObservational(rs.getString(18));
				s.setAnalytical(rs.getString(19));
				s.setNegotiating(rs.getString(20));
				s.setRobust_common(rs.getString(21));
				s.setTime_manage(rs.getString(22));
				s.setTotal_score(rs.getString(23));
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

	
	public static Skills getSkillsById(String id){

        Skills s = new Skills();

        ResultSet rs = null;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from skills where roll_no=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
            	s.setRoll_no(rs.getString(1));
				s.setStud_name(rs.getString(2));
				s.setIT_skills(rs.getString(3));
				s.setListening(rs.getString(4));
				s.setOral_comm(rs.getString(5));
				s.setWritten_comm(rs.getString(6));
				s.setBody_lang(rs.getString(7));
				s.setConceptual(rs.getString(8));
				s.setEI(rs.getString(9));
				s.setConflict_reso(rs.getString(10));
				s.setProb_solving(rs.getString(11));
				s.setOrganising(rs.getString(12));
				s.setInter_personal(rs.getString(13));
				s.setSocial(rs.getString(14));
				s.setInnovative(rs.getString(15));
				s.setPro_active(rs.getString(16));
				s.setMulti_tasking(rs.getString(17));
				s.setObservational(rs.getString(18));
				s.setAnalytical(rs.getString(19));
				s.setNegotiating(rs.getString(20));
				s.setRobust_common(rs.getString(21));
				s.setTime_manage(rs.getString(22));
				s.setTotal_score(rs.getString(23));
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

	
	public static Attitude getAttitudeById(String id){

        Attitude a = new Attitude();

        ResultSet rs = null;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from attitude where roll_no=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
            	a.setRoll_no(rs.getString(1));
            	a.setStud_name(rs.getString(2));
				a.setPositive_thinking(rs.getString(3));
				a.setIntegrity(rs.getString(4));
				a.setDependability(rs.getString(5));
				a.setDiscipline(rs.getString(6));
				a.setExcellence(rs.getString(7));
				a.setPerseverance(rs.getString(8));
				a.setEnthusiasm(rs.getString(9));
				a.setSelf_confidence(rs.getString(10));
				a.setInitiative(rs.getString(11));
				a.setFarsightedness(rs.getString(12));
				a.setTeam_spirit(rs.getString(13));
				a.setDesire_learn(rs.getString(14));
				a.setOpen_mindedness(rs.getString(15));
				a.setAdaptability(rs.getString(16));
				a.setEmpathy(rs.getString(17));
				a.setConcern(rs.getString(18));
				a.setFairness(rs.getString(19));
				a.setModesty(rs.getString(20));
				a.setEthics(rs.getString(21));
				a.setCleanliness(rs.getString(22));
				a.setTotal_score(rs.getString(23));
                connection.close();
                return a;
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
		return a;
    }

	
	public static boolean addKnowledge(String id,String name,String ds, String algo,String logic,String cao,String dbms, String os, String network,String info, String soft_dev,String soft_pro,String mobi,String data, String metric, String internet,String app_dev, String bus_fin,String bus_intel,String hr,String ms, String er){
		int res=0;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into knowledge values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, ds);
            ps.setString(4, algo);
            ps.setString(5, logic);
            ps.setString(6, cao);
            ps.setString(7, dbms);
            ps.setString(8, os);
            ps.setString(9, network);
            ps.setString(10, info);
            ps.setString(11, soft_dev);
            ps.setString(12, soft_pro);
            ps.setString(13, mobi);
            ps.setString(14, data);
            ps.setString(15, metric);
            ps.setString(16, internet);
            ps.setString(17, app_dev);
            ps.setString(18, bus_fin);
            ps.setString(19, bus_intel);
            ps.setString(20, hr);
            ps.setString(21, ms);
            ps.setString(22, er);
            int a=Integer.valueOf(ds);
			int b=Integer.valueOf(algo);
			int c=Integer.valueOf(logic);
			int d=Integer.valueOf(cao);
			int e=Integer.valueOf(dbms);
			int f=Integer.valueOf(os);
			int g=Integer.valueOf(network);
			int h=Integer.valueOf(info);
			int i=Integer.valueOf(soft_dev);
			int j=Integer.valueOf(soft_pro);
			int k=Integer.valueOf(mobi);
			int l=Integer.valueOf(data);
			int m=Integer.valueOf(metric);
			int n=Integer.valueOf(internet);
			int o=Integer.valueOf(app_dev);
			int p=Integer.valueOf(bus_fin);
			int q=Integer.valueOf(bus_intel);
			int r=Integer.valueOf(hr);
			int s=Integer.valueOf(ms);
			int t=Integer.valueOf(er);

			ps.setString(23, String.valueOf(a+b+c+d+e+f+g+h+i+j+k+l+m+n+o+p+q+r+s+t));

            res=ps.executeUpdate();
         
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
        if((res)>0)
        	return true;
        else
        	return false;

    }
	
	public static boolean addSkills(String id,String name,String it,String lis,String oral,String written,String body,String concept,String ei,String reso,String prob,String org,String inter,String social,String inno,String pro,String multi,String obs,String ana,String nego,String robust,String time){
		int res=0;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into skills values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, it);
            ps.setString(4, lis);
            ps.setString(5, oral);
            ps.setString(6, written);
            ps.setString(7, body);
            ps.setString(8, concept);
            ps.setString(9, ei);
            ps.setString(10, reso);
            ps.setString(11, prob);
            ps.setString(12, org);
            ps.setString(13, inter);
            ps.setString(14, social);
            ps.setString(15, inno);
            ps.setString(16, pro);
            ps.setString(17, multi);
            ps.setString(18, obs);
            ps.setString(19, ana);
            ps.setString(20, nego);
            ps.setString(21, robust);
            ps.setString(22, time);
            int a=Integer.valueOf(it);
			int b=Integer.valueOf(lis);
			int c=Integer.valueOf(oral);
			int d=Integer.valueOf(written);
			int e=Integer.valueOf(body);
			int f=Integer.valueOf(concept);
			int g=Integer.valueOf(ei);
			int h=Integer.valueOf(reso);
			int i=Integer.valueOf(prob);
			int j=Integer.valueOf(org);
			int k=Integer.valueOf(inter);
			int l=Integer.valueOf(social);
			int m=Integer.valueOf(inno);
			int n=Integer.valueOf(pro);
			int o=Integer.valueOf(multi);
			int p=Integer.valueOf(obs);
			int q=Integer.valueOf(ana);
			int r=Integer.valueOf(nego);
			int s=Integer.valueOf(robust);
			int t=Integer.valueOf(time);

			ps.setString(23, String.valueOf(a+b+c+d+e+f+g+h+i+j+k+l+m+n+o+p+q+r+s+t));
			
			res=ps.executeUpdate();
           
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
        if((res)>0)
        	return true;
        else
        	return false;
		
    }
	
	public static boolean addAttitude(String id,String name,String pos,String inte,String depend,String disc,String exc,String per,String enth,String conf,String init,String far,String team,String learn,String open,String adapt,String emp,String concern,String fair,String mode,String ethics,String clean){
        int res=0;
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into attitude values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, pos);
            ps.setString(4, inte);
            ps.setString(5, depend);
            ps.setString(6, disc);
            ps.setString(7, exc);
            ps.setString(8, per);
            ps.setString(9, enth);
            ps.setString(10, conf);
            ps.setString(11, init);
            ps.setString(12, far);
            ps.setString(13, team);
            ps.setString(14, learn);
            ps.setString(15, open);
            ps.setString(16, adapt);
            ps.setString(17, emp);
            ps.setString(18, concern);
            ps.setString(19, fair);
            ps.setString(20, mode);
            ps.setString(21, ethics);
            ps.setString(22, clean);
            int a=Integer.valueOf(pos);
			int b=Integer.valueOf(inte);
			int c=Integer.valueOf(depend);
			int d=Integer.valueOf(disc);
			int e=Integer.valueOf(exc);
			int f=Integer.valueOf(per);
			int g=Integer.valueOf(enth);
			int h=Integer.valueOf(conf);
			int i=Integer.valueOf(init);
			int j=Integer.valueOf(far);
			int k=Integer.valueOf(team);
			int l=Integer.valueOf(learn);
			int m=Integer.valueOf(open);
			int n=Integer.valueOf(adapt);
			int o=Integer.valueOf(emp);
			int p=Integer.valueOf(concern);
			int q=Integer.valueOf(fair);
			int r=Integer.valueOf(mode);
			int s=Integer.valueOf(ethics);
			int t=Integer.valueOf(clean);

			ps.setString(23, String.valueOf(a+b+c+d+e+f+g+h+i+j+k+l+m+n+o+p+q+r+s+t));
            res=ps.executeUpdate();
            
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
        if((res)>0)
        	return true;
        else
        	return false;
    }

	
	public static void updateKnowledge(String id,String ds, String algo,String logic,String cao,String dbms, String os, String network,String info, String soft_dev,String soft_pro,String mobi,String data, String metric, String internet,String app_dev, String bus_fin,String bus_intel,String hr,String ms, String er){

        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("update knowledge set data_structures=?, algorithms=?, "
            		+ "logic_building=?, cao=?, dbms=?, os=?, networks=?, info_security=?, software_dev=?, software_project=?, "
            		+ "mobility=?, data_mining=?, software_metrics=?, internet=?, application_dev=?, business_financial=?, "
            		+ "busi_intelligence=?, human_resource=?, modeling_simulation=?, enterprise_resource =?,total_score =? where roll_no=?");
            ps.setString(1, ds);
            ps.setString(2, algo);
            ps.setString(3, logic);
            ps.setString(4, cao);
            ps.setString(5, dbms);
            ps.setString(6, os);
            ps.setString(7, network);
            ps.setString(8, info);
            ps.setString(9, soft_dev);
            ps.setString(10, soft_pro);
            ps.setString(11, mobi);
            ps.setString(12, data);
            ps.setString(13, metric);
            ps.setString(14, internet);
            ps.setString(15, app_dev);
            ps.setString(16, bus_fin);
            ps.setString(17, bus_intel);
            ps.setString(18, hr);
            ps.setString(19, ms);
            ps.setString(20, er);
            int a=Integer.valueOf(ds);
			int b=Integer.valueOf(algo);
			int c=Integer.valueOf(logic);
			int d=Integer.valueOf(cao);
			int e=Integer.valueOf(dbms);
			int f=Integer.valueOf(os);
			int g=Integer.valueOf(network);
			int h=Integer.valueOf(info);
			int i=Integer.valueOf(soft_dev);
			int j=Integer.valueOf(soft_pro);
			int k=Integer.valueOf(mobi);
			int l=Integer.valueOf(data);
			int m=Integer.valueOf(metric);
			int n=Integer.valueOf(internet);
			int o=Integer.valueOf(app_dev);
			int p=Integer.valueOf(bus_fin);
			int q=Integer.valueOf(bus_intel);
			int r=Integer.valueOf(hr);
			int s=Integer.valueOf(ms);
			int t=Integer.valueOf(er);

			ps.setString(21, String.valueOf(a+b+c+d+e+f+g+h+i+j+k+l+m+n+o+p+q+r+s+t));

            ps.setString(22, id);
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
	
	public static void updateSkills(String id,String it,String lis,String oral,String written,String body,String concept,String ei,String reso,String prob,String org,String inter,String social,String inno,String pro,String multi,String obs,String ana,String nego,String robust,String time){

    
        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("update skills set IT-skills=?,listening=?,oral_comm=?,written_comm=?,body_lang=?"
            		+ ",conceptual=?,EI=?,conflict_reso=?,prob_solving=?,organising=?,inter-personal=?,social=?,innovative=?,pro-active=?,"
            		+ "multi-tasking=?,observational=?,analytical=?,negotiating=?,robust_common=?,time_manage=?,total_score=? where roll_no=?");
            ps.setString(1, it);
            ps.setString(2, lis);
            ps.setString(3, oral);
            ps.setString(4, written);
            ps.setString(5, body);
            ps.setString(6, concept);
            ps.setString(7, ei);
            ps.setString(8, reso);
            ps.setString(9, prob);
            ps.setString(10, org);
            ps.setString(11, inter);
            ps.setString(12, social);
            ps.setString(13, inno);
            ps.setString(14, pro);
            ps.setString(15, multi);
            ps.setString(16, obs);
            ps.setString(17, ana);
            ps.setString(18, nego);
            ps.setString(19, robust);
            ps.setString(20, time);
            int a=Integer.valueOf(it);
			int b=Integer.valueOf(lis);
			int c=Integer.valueOf(oral);
			int d=Integer.valueOf(written);
			int e=Integer.valueOf(body);
			int f=Integer.valueOf(concept);
			int g=Integer.valueOf(ei);
			int h=Integer.valueOf(reso);
			int i=Integer.valueOf(prob);
			int j=Integer.valueOf(org);
			int k=Integer.valueOf(inter);
			int l=Integer.valueOf(social);
			int m=Integer.valueOf(inno);
			int n=Integer.valueOf(pro);
			int o=Integer.valueOf(multi);
			int p=Integer.valueOf(obs);
			int q=Integer.valueOf(ana);
			int r=Integer.valueOf(nego);
			int s=Integer.valueOf(robust);
			int t=Integer.valueOf(time);

			ps.setString(21, String.valueOf(a+b+c+d+e+f+g+h+i+j+k+l+m+n+o+p+q+r+s+t));
            ps.setString(22, id);
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
	
	public static void updateAttitude(String id,String pos,String inte,String depend,String disc,String exc,String per,String enth,String conf,String init,String far,String team,String learn,String open,String adapt,String emp,String concern,String fair,String mode,String ethics,String clean){

        try{
        	connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("update attitude set positive_thinking=?,integrity=?,dependability=?,"
            		+ "discipline=?,excellence=?,perseverance=?,enthusiasm=?,self-confidence=?,initiative=?,farsightedness=?,team_spirit=?,"
            		+ "desire_learn=?,open_mindedness=?,adaptability=?,empathy=?,concern=?,fairness=?,modesty=?,ethics=?,cleanliness=?,"
            		+ "total_score=? where id=?");
            ps.setString(1, pos);
            ps.setString(2, inte);
            ps.setString(3, depend);
            ps.setString(4, disc);
            ps.setString(5, exc);
            ps.setString(6, per);
            ps.setString(7, enth);
            ps.setString(8, conf);
            ps.setString(9, init);
            ps.setString(10, far);
            ps.setString(11, team);
            ps.setString(12, learn);
            ps.setString(13, open);
            ps.setString(14, adapt);
            ps.setString(15, emp);
            ps.setString(16, concern);
            ps.setString(17, fair);
            ps.setString(18, mode);
            ps.setString(19, ethics);
            ps.setString(20, clean);
            int a=Integer.valueOf(pos);
			int b=Integer.valueOf(inte);
			int c=Integer.valueOf(depend);
			int d=Integer.valueOf(disc);
			int e=Integer.valueOf(exc);
			int f=Integer.valueOf(per);
			int g=Integer.valueOf(enth);
			int h=Integer.valueOf(conf);
			int i=Integer.valueOf(init);
			int j=Integer.valueOf(far);
			int k=Integer.valueOf(team);
			int l=Integer.valueOf(learn);
			int m=Integer.valueOf(open);
			int n=Integer.valueOf(adapt);
			int o=Integer.valueOf(emp);
			int p=Integer.valueOf(concern);
			int q=Integer.valueOf(fair);
			int r=Integer.valueOf(mode);
			int s=Integer.valueOf(ethics);
			int t=Integer.valueOf(clean);

			ps.setString(21, String.valueOf(a+b+c+d+e+f+g+h+i+j+k+l+m+n+o+p+q+r+s+t));
            ps.setString(22, id);
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
