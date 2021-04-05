public class kundC
{
	private Integer Id;
	private String förnamn;
	private String efternamn;
	private Integer ort_Id;
	private Double spenderadePengar;
	private String lösenord;
	
	
	
	kundC()
	{}
	
	
	
	public Integer getId()
	{
		return Id;
	}
	
	public String getFörnamn()
	{
		return förnamn;
	}
	
	public String getEfternamn()
	{
		return efternamn;
	}
	
	public Integer getOrt_Id()
	{
		return ort_Id;
	}
	
	public Double getSpenderadePengar()
	{
		return spenderadePengar;
	}
	
	public String getLösenord()
	{
		return lösenord;
	}
	
	
	
	public void setId(int Id)
	{
		this.Id = Id;
	}
	
	public void setFörnamn(String förnamn)
	{
		this.förnamn = förnamn;
	}
	
	public void setEfternamn(String efternamn)
	{
		this.efternamn = efternamn;
	}
	
	public void setOrt_Id(int ort_Id)
	{
		this.ort_Id = ort_Id;
	}
	
	public void setSpenderadePengar(Double spenderadePengar)
	{
		this.spenderadePengar = spenderadePengar;
	}
	
	public void setLösenord(String lösenord)
	{
		this.lösenord = lösenord;
	}
	
}