public class beställningC
{
	private Integer Id;
	private Integer kund_Id;
	private Integer månad;
	private Double kostnad;
	
	
	
	beställningC()
	{}
	
	
	
	public Integer getId()
	{
		return Id;
	}
	
	public Integer getKund_Id()
	{
		return kund_Id;
	}
	
	public Integer getMånad()
	{
		return månad;
	}
	
	public Double getKostnad()
	{
		return kostnad;
	}
	
	
	
	public void setId(Integer Id)
	{
		this.Id = Id;
	}
	
	public void setKund_Id(Integer kund_Id)
	{
		this.kund_Id = kund_Id;
	}
	
	public void setMånad(Integer månad)
	{
		this.månad = månad;
	}
	
	public void setKostnad(Double kostnad)
	{
		this.kostnad = kostnad;
	}
	
}