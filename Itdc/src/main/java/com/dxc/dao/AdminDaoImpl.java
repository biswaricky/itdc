package com.dxc.dao;

import java.util.List;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.dxc.pojos.Admin;
import com.dxc.pojos.Bookroom;
import com.dxc.pojos.Hotels;
import com.dxc.repository.MangoAdmin;
import com.dxc.repository.MangoBook;
import com.dxc.repository.MongoHotel;
@Repository
@Service
public class AdminDaoImpl 
{
	@Autowired
	MangoAdmin mon;
	@Autowired
	MongoHotel moh;
	@Autowired
	MangoBook mob;
	

	public boolean logindetails(Admin a) 
	{
	  mon.save(a);
	  
	   
		return true;
	}

	public boolean login(Admin a) {
		
		
		List<Admin> alist = new ArrayList<>();
		
        alist =  mon.findAll();
        System.out.println(alist.size());
        System.out.println(alist);
       
        
        for(int i=0;i<alist.size();i++)
        {
        	Admin a1 = alist.get(i);
        if(a1.getAdminid()==(a.getAdminid()) && a1.getPass().equals(a.getPass()))
        {
            return true;
        }
        }
		
       return false;
	}
	public boolean adddetails(Hotels h) {
		moh.save(h);
		
		return true; 
	}

	public List<Hotels> getall() {
		List<Hotels> hlist = new ArrayList<>();
		hlist=moh.findAll();

		return hlist;
	}
	public List<Bookroom> showBookings()
    {
        List<Bookroom> blist = mob.findAll();
        return blist;
   
    }
	public List<Bookroom> displayCancel()
    {
        List<Bookroom> b1=mob.findBookingBystatus("requesting for cancel room");
        return b1;
    }
  public String displayDone()
    {
        List<Bookroom> b1=mob.findAll();
        List<Hotels> h=moh.findAll();
        for(Bookroom b:b1)
        {
            if(b.getStatus().equals("requesting for cancel room"))
            {
                b.setStatus("cancelled!!!");
                mob.save(b);
                for(Hotels h1:h)
                {
                	if(h1.getHid()==b.getHid())
                    {
                		int rooms=h1.getRooms();
                        
                        h1.setRooms(rooms);
                        
                        moh.save(h1);
                    }
                   
                }
                }
               
            }
       
       
        return "Successfully cancelled";
    }
	
	
	
	
}

