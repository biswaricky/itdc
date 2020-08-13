package com.dxc.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dxc.pojos.Admin;
import com.dxc.pojos.Bookroom;
import com.dxc.pojos.Hotels;
import com.dxc.services.AdminServiceImpl;
@Controller 
@RequestMapping("/views")
public class AdminController 
{
	@Autowired
    AdminServiceImpl service;
	@RequestMapping("/home")
	public String home()
	{
		return "home";
	}
	@RequestMapping("/log")
	public String login(@ModelAttribute Admin a,Model m)
	{ 
		
       
	    boolean result=service.login(a); 
	    if(result==true)
	    {
	    	String login="LOGIN AS ADMIN";
	    	m.addAttribute("login",login);
	    	return "adminmenu";
	    }
	    else
	    {
	   
	    	String fail="INVALID CREDENTIALS";
	    	m.addAttribute("result",fail);
	    }
	     
	     return "message";
	}
	@RequestMapping("/login")
	public String adminlogin(@ModelAttribute Admin a,Model m)
	{   
        System.out.println(a);
		
	    boolean result=service.logindetails(a); 
	    if(result==true)
	    {
	    	String login="ADMIN ADDED SUCESSFULLY";
	    	m.addAttribute("result",login);
	    	return "message";
	    }
	    else
	    {
	   
	    	String fail="INVALID CREDENTIALS";
	    	m.addAttribute("result",fail);
	    }
	     
	     return "message";
	}
	@RequestMapping("/add")
	public String adminlogin(@ModelAttribute Hotels h,Model m)
	{   
         System.out.println(h);
		
	    boolean result=service.adddetails(h); 
	    if(result==true)
	    {
	    	String login="HOTEL ADDED SUCESSFULLY";
	    	m.addAttribute("result",login);
	    	return "message";
	    }
	    else
	    {
	   
	    	String fail="INVALID CREDENTIALS";
	    	m.addAttribute("result",fail);
	    }
	     
	     return "message";
	}
	@RequestMapping("/displayall")
 	public String getAllproduct(Model model)
 	{
 		List<Hotels> list=service.getall();
 		System.out.println(list);
 		model.addAttribute("list", list);
 		return "displayall";
 	}
	@RequestMapping("/displaybookings")
	public String showbookings(Model m,HttpSession session)
	{
		
		
		List<Bookroom> blist=service.showBookings();
		if(blist.isEmpty())
		{
			String message="No active Bookings";
					m.addAttribute("result",message);
					return "message";
		}
		else
		{
			m.addAttribute("list",blist);
			return "showbookings";
		}
		
	}
	@RequestMapping("/displaycancelbooking")
    public String approveCancel(HttpSession session,Model m)
    {
        List<Bookroom> blist=service.displayCancel();
        System.out.println(blist);
        if(!(blist.isEmpty()))
        {
            m.addAttribute("list", blist);
            return "displaycancelbooking";
           
        }
        else
        {
           String message="NO REQUEST SENT FROM CUSTOMER";
            m.addAttribute("result", message);
            return "message";
        }
                     
    }
    @RequestMapping("/nomoney")
    public String requestforcancellation(Model m,HttpSession session)
    {
       
        String b1=service.displayDone();
        m.addAttribute("result", b1);
        return "message";
    }
}
 
	


