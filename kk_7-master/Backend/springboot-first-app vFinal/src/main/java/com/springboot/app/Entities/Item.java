	package com.springboot.app.Entities;

	import java.io.File;

	import javax.persistence.*;

	@Entity
	public class Item {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		Integer id;
		
		
		@Column 
		String name;

		@Column 
		String description;
		
		@Column
		String price;
		
		@Column
		String lengthInDays;
		
		@Column
		String filepath;
		
		@Column 
		String sellerID;
		
		@Column 
		String buyerID;
		
		@Column 
		Boolean available;
		
		
		
		
		public Item()
		{
			buyerID = "" + -1;
			available = true;
		}
		
		
		public void setAvailable(Boolean setAvail)
		{
			available = setAvail;
		}
		
		
		public boolean getAvailable()
		{
			return available;
		}
		
		public void setBuyerID(String setBuyerID)
		{
			buyerID = setBuyerID;
		}
		
		public String GetBuyerID()
		{
			return buyerID;
		}
		
		

		public void setSellerID(String setSellerID)
		{
			sellerID = setSellerID;
		}
		
		public String GetSellerID()
		{
			return sellerID;
		}
		
		
		
		public void setFilePath(String path) {
			filepath = path;
		}
		
		public String getFilePath() {
			return filepath;
		}
		
		
		public String getName()
		{
			return name;
		}
		
		
		public void setName(String setName)
		{
			name = setName;
		}
		
		public String getDescription()
		{
			return description;
		}
		
		public void setDescription(String setDescription)
		{
			description = setDescription;
		}
		
		public String getPrice()
		{
			return price;
		}
		
		
		public void setPrice(String setPrice)
		{
			price = setPrice;
		}
		
		
		public String getLength()
		{
			return lengthInDays;
		}
		
		
		public void setLength(String setLength)
		{
			lengthInDays = setLength;
		}
		
		public Integer getId()
		{
			return id;
		}
		
		
		public String IDString()
		{
			return ""+id;
		}
		
		
		
		

}
