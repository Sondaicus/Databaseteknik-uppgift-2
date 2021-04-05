public class kategoriC
{
	private Integer Id;
	private String kategori;
	private Integer produkter;
	
	
	
	kategoriC()
	{}
	
	
	
	public Integer getId()
	{
		return Id;
	}
	
	public String getKategori()
	{
		return kategori;
	}
	
	public Integer getProdukter()
	{
		return produkter;
	}
	
	
	
	public void setId(Integer Id)
	{
		this.Id = Id;
	}
	
	public void setKategori(String kategori)
	{
		this.kategori = kategori;
	}
	
	public void setProdukter(Integer produkter)
	{
		this.produkter = produkter;
	}
	
}