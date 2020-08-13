package com.dxc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.dxc.pojos.Bookroom;
import com.dxc.pojos.Customer;
import com.dxc.pojos.Hotels;
import com.dxc.repository.MangoBook;
import com.dxc.repository.MongoCustomer;
import com.dxc.repository.MongoHotel;
@Repository
@Service
public class CustomerDaoImpl 
{
	@Autowired
	MongoCustomer moc;
	@Autowired
	MongoHotel moh;
	@Autowired
	MangoBook mob;
	
	Random random =new Random();
	public boolean logindetails(Customer c) 
	{
	  moc.save(c);
	  
	   
		return true;
	}

	public boolean login(Customer c) {
		

		List<Customer> alist = new ArrayList<>();
		
        alist =  moc.findAll();
        System.out.println(alist.size());
        System.out.println(alist);
        for(int i=0;i<alist.size();i++)
        {
        Customer c1 = alist.get(i);
      
        if(c1.getCid()==(c.getCid()) && c1.getCpass().equals(c.getCpass()))
        {
            return true;
        }
        }
      
		
       return false;
	}
	public List<Hotels> gethotels() {
		List<Hotels> hlist = new ArrayList<>();
		hlist=moh.findAll();
		
		return hlist;
	}
	public List<Hotels> find(int hid)
	{
	
		List<Hotels> hlist = new ArrayList<>();
		hlist=moh.findHotelsByhid(hid);
		return hlist;
	}

	public boolean bookroom(Bookroom b, int hid, int cid) {
	     
	      List<Customer> clist = moc.findCustomerBycid(cid);
	       
	               System.out.println(cid);
	       
	               Customer c = clist.get(0);
	           
	               b.setName(c.getName());
	               b.setCid(c.getCid());
	               b.setCphno(c.getCphno());
	               
	               List<Hotels> hlist =  new ArrayList<Hotels>();
	               hlist = moh.findHotelsByhid(hid);
	       
	            Hotels h = hlist.get(0);
	           b.setHid(hid);
	           b.setHname(h.getHname());
	           b.setHaddress(h.getHaddress());
	           
	           int originalrooms = h.getRooms();
	           int bookedrooms = b.getNoofrooms();
	          
	           
	          
	           
	         
	           
	            if(bookedrooms<originalrooms)
	            {
	                originalrooms = originalrooms - bookedrooms;
	                h.setRooms(originalrooms);
	                
	               
	                 moh.save(h);
	                 double cost=b.getNoofrooms()*h.getCost()*b.getDays();
	                
	               b.setTotalamount(cost);
	           
	               
	       int bid = random.nextInt(20);
	     
	               
	                b.setBookingid(bid);
	               b.setStatus("Booked");
	               System.out.println(b);
	                mob.save(b);
	               
	                //mongo.insert(b);
	               
	                return true;
	            }
	            
	            
	            
	            
	            
	            else 
	            {
	                return false;
	            }
	
		
	}
	
	public List<Bookroom> showBookings(int cid)
    {
        List<Bookroom> blist = mob.findBookroomBycid(cid);
        return blist;
   
    }
	 public List<Bookroom> requestCustomer(int bookid)
     {
         List<Bookroom> blist=new ArrayList<>();
         blist=mob.findAll();
         for(Bookroom b:blist)
         {
                  if(bookid==b.getBookingid())
                 {    
                      b.setStatus("requesting for cancel room");
                      mob.save(b);
                       return mob.findBookingBybookingid(bookid);
                 }
         }
         return mob.findBookingBybookingid(20);
     }

}

