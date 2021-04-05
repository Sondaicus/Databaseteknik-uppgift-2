public class ortC
{
	private Integer Id;
	private String ortnamn;
	private Integer antalKunder;
	private Double inhandlingsvärde;
	
	
	
	ortC()
	{}
	
	
	
	public Integer getId()
	{
		return Id;
	}
	
	public String getOrtnamn()
	{
		return ortnamn;
	}
	
	public Integer getAntalKunder()
	{
		return antalKunder;
	}
	
	public Double getInhandlingsvärde()
	{
		return inhandlingsvärde;
	}
	
	
	
	public void setId(Integer Id)
	{
		this.Id = Id;
	}
	
	public void setOrtnamn(String ortnamn)
	{
		this.ortnamn = ortnamn;
	}
	
	public void setAntalKunder(Integer antalKunder)
	{
		this.antalKunder = antalKunder;
	}
	
	public void setInhandlingsvärde(Double inhandlingsvärde)
	{
		this.inhandlingsvärde = inhandlingsvärde;
	}
	
}