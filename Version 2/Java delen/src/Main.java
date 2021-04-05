import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.time.*;
import java.util.*;



public class Main<PopupMenuListener> implements ActionListener
{
    /*Tabeller*/
    public static ArrayList<beställningC> beställningar_TAB;
    public static ArrayList<kategoriC> kategorier_TAB;
    public static ArrayList<kategorierAllaSkorC> kategorierAllaSkor_TAB;
    public static ArrayList<kundC> kunder_TAB;
    public static ArrayList<kunderPerOrtC> kunderPerOrt_TAB;
    public static ArrayList<ortC> orter_TAB;
    public static ArrayList<skoBeställningC> skoBeställningar_TAB;
    public static ArrayList<skoC> skor_TAB;
    public static ArrayList<slutILager_FinalC> slutILager_Final_TAB;
    
    /*Grafiska komponenter*/
    public static JFrame windowMain;
    public static JPanel windowForUsersLogIn;
    public static JPanel windowForResults;
    public static JPanel windowForShopMain;
    public static JPanel windowForShopTextInfo;
    public static JPanel windowForShopOptions;
    public static JPanel windowForShopButtons;
    public static JComboBox users_Usernames;
    public static JComboBox tables_beställningar;
    public static JComboBox tables_skor;
    public static JComboBox tables_LäggTillIVarukorg;
    public static JComboBox tables_taBortUrVarukorg;
    public static JLabel textToUserResult;
    public static JLabel textToUserShopGrid1_1;
    public static JLabel textToUserShopGrid2_1;
    public static JLabel textToUserShopGrid3_1;
    public static JLabel textToUserShopGrid4_1;
    public static JLabel textToUserShopGrid5_1;
    public static JLabel textToUserShopGrid3_2;
    public static JLabel textToUserShopGrid4_2;
    public static JLabel textToUserShopGrid5_2;
    public static JTextField windowForPasswordLogIn;
    public static JTextField windowForUserResults;
    public static JButton logInAttempt;
    public static JButton läggTillIVarukorgenJB;
    public static JButton taBortUrVarukorgenJB;
    public static JButton loggaUtJB;
    public static JButton seSlutILagerJB;
    public static JButton skickaBeställningJB;
    public static Border blackline;
    
    /*Användarnamn och lösenord till databasen*/
    public static String username;
    public static String password;
    
    /*Variabler för varukorgs hantering*/
    public static int user_Id;
    public static int nuvarandeBeställning_Id;
    public static ArrayList<skoC> skor_Varukorg;
    public static String nuMånad;
    
    /*Booleans för hela programmet*/
    public static boolean isAtLogIn;
    public static boolean isAtShop;
    
    
    
    public Main() throws SQLException
    {
        /*Server verifications*/
        username = "a1";
        password = "a1";
    
        /*Set all ActionListeners*/
        logInAttempt = new JButton("Logga in");
        logInAttempt.addActionListener(this);
        logInAttempt.setVisible(true);
        logInAttempt.setEnabled(true);
    
     
    
        läggTillIVarukorgenJB = new JButton("Lägg till vald sko i varukorgen");
        läggTillIVarukorgenJB.addActionListener(this);
        läggTillIVarukorgenJB.setVisible(true);
        läggTillIVarukorgenJB.setEnabled(true);
    
        taBortUrVarukorgenJB = new JButton("Välj sko att ta bort ur varukorgen");
        taBortUrVarukorgenJB.addActionListener(this);
        taBortUrVarukorgenJB.setVisible(true);
        taBortUrVarukorgenJB.setEnabled(true);
    
        loggaUtJB = new JButton("Logga ut");
        loggaUtJB.addActionListener(this);
        loggaUtJB.setVisible(true);
        loggaUtJB.setEnabled(true);
    
        seSlutILagerJB = new JButton("Se alla skor som sålt slut och månad.");
        seSlutILagerJB.addActionListener(this);
        seSlutILagerJB.setVisible(true);
        seSlutILagerJB.setEnabled(true);
    
        skickaBeställningJB= new JButton("Skicka beställning");
        skickaBeställningJB.addActionListener(this);
        skickaBeställningJB.setVisible(true);
        skickaBeställningJB.setEnabled(true);
        
        
    
        tables_LäggTillIVarukorg = new JComboBox();
        tables_taBortUrVarukorg = new JComboBox();
    
        setMainWindow();
        showStartWindow();
        
    }
    
    
    
    public static void main(String[] args)  throws SQLException
    {
        Main m1 = new Main();
    }
    
    
    
    public static void fillAllLists() throws SQLException
    {
        int
        oldSize ,
        newSize ,
        excessIndexes;
    
        JComboBox
        tempJCB;
    
        String
        inputString ,
        feedString;
        
        
        
        getTable_beställningar();
        getTable_kategorier();
        getTable_kategorierAllaSkor();
        getTable_kunder();
        getTable_kunderPerOrt();
        getTable_orter();
        getTable_skoBeställningar();
        getTable_skor();
        getTable_slutILager_Final();
    
    
    
        tables_LäggTillIVarukorg.removeAllItems();
        tables_taBortUrVarukorg.removeAllItems();
    
        for(int i1 = 0; i1 < skor_TAB.size(); i1++)
        {
            feedString = "Färg: " + skor_TAB.get(i1).getFärg() + ", Storlek: " + skor_TAB.get(i1).getStorlek() + ", " +
                         "Pris: " + skor_TAB.get(i1).getPris() + ", Märke: " + skor_TAB.get(i1).getMärke() + ", Finns i lager: " + skor_TAB.get(i1).getILager();
        
            tables_LäggTillIVarukorg.addItem(feedString);
        
        }
    
        oldSize = skor_TAB.size();
        newSize = tables_LäggTillIVarukorg.getItemCount();
    
        if(newSize > oldSize)
        {
            excessIndexes = newSize - oldSize;
            tempJCB = new JComboBox();
        
            for(int i1 = 0; i1 < excessIndexes; i1++)
            {
                inputString = tables_LäggTillIVarukorg.getItemAt(i1).toString();
                tempJCB.addItem(inputString);
            }
        
            tables_LäggTillIVarukorg.removeAllItems();
            tables_taBortUrVarukorg.removeAllItems();
        
            for(int i1 = 0; i1 < excessIndexes; i1++)
            {
                inputString = tempJCB.getItemAt(i1).toString();
                tables_LäggTillIVarukorg.addItem(inputString);
            
            }
        
        }
    
    
        setVarukorg();
        windowMain.revalidate();
        
    }
    
    
    
    public static void getTable_beställningar() throws SQLException
    {
        Statement
        stmt;
        
        ResultSet
        rs;
        
        Integer
        Id ,
        kund_Id ,
        månad;
        
        Double
        kostnad;
        
        
        
        beställningar_TAB = new ArrayList<beställningC>();
    
        try (Connection con = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false", username, password);
        )
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Id, kund_Id, månad, kostnad FROM beställningar");
            while(rs.next())
            {
                beställningC temp = new beställningC();
        
                Id = rs.getInt("Id");
                kund_Id = rs.getInt("kund_Id");
                månad = rs.getInt("månad");
                kostnad = rs.getDouble("kostnad");
        
                temp.setId(Id);
                temp.setKund_Id(kund_Id);
                temp.setMånad(månad);
                temp.setKostnad(kostnad);
        
                beställningar_TAB.add(temp);
        
            }
            
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        
    }
    
    
    
    public static void getTable_kategorier() throws SQLException
    {
        Statement
        stmt;
        
        ResultSet
        rs;
        
        Integer
        Id ,
        produkter;
        
        String
        kategori;
        
        
        
        kategorier_TAB = new ArrayList<kategoriC>();
    
        try (Connection con = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false", username, password);
        )
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Id, kategori, produkter FROM kategorier");
            while(rs.next())
            {
                kategoriC temp = new kategoriC();
        
                Id = rs.getInt("Id");
                kategori = rs.getString("kategori");
                produkter = rs.getInt("produkter");
        
                temp.setId(Id);
                temp.setKategori(kategori);
                temp.setProdukter(produkter);
        
                kategorier_TAB.add(temp);
        
            }
            
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        
    }
    
    
    
    public static void getTable_kategorierAllaSkor() throws SQLException
    {
        Statement
        stmt;
        
        ResultSet
        rs;
        
        Integer
        Id ,
        sko_Id ,
        kategori_Id;
    
    
        
        kategorierAllaSkor_TAB = new ArrayList<kategorierAllaSkorC>();
    
        try (Connection con = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false", username, password);
        )
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Id, sko_Id, kategori_Id FROM kategorierAllaSkor");
            while(rs.next())
            {
                kategorierAllaSkorC temp = new kategorierAllaSkorC();
        
                Id = rs.getInt("Id");
                sko_Id = rs.getInt("sko_Id");
                kategori_Id = rs.getInt("kategori_Id");
        
                temp.setId(Id);
                temp.setSko_Id(sko_Id);
                temp.setKategori_Id(kategori_Id);
        
                kategorierAllaSkor_TAB.add(temp);
        
            }
            
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        
    }
    
    
    
    public static void getTable_kunder() throws SQLException
    {
        Statement
        stmt;
        
        ResultSet
        rs;
        
        Integer
        Id ,
        ort_Id;
    
        String
        förnamn ,
        efternamn ,
        lösenord;
    
        Double
        spenderadePengar;
    
        
        
        kunder_TAB = new ArrayList<kundC>();
    
        try (Connection con = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false", username, password);
        )
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Id, förnamn, efternamn, ort_Id, spenderadePengar, lösenord FROM kunder");
            while(rs.next())
            {
                kundC temp = new kundC();
        
                Id = rs.getInt("Id");
                förnamn = rs.getString("förnamn");
                efternamn = rs.getString("efternamn");
                ort_Id = rs.getInt("ort_Id");
                spenderadePengar = rs.getDouble("spenderadePengar");
                lösenord = rs.getString("lösenord");
        
                temp.setId(Id);
                temp.setFörnamn(förnamn);
                temp.setEfternamn(efternamn);
                temp.setOrt_Id(ort_Id);
                temp.setSpenderadePengar(spenderadePengar);
                temp.setLösenord(lösenord);
        
                kunder_TAB.add(temp);
        
            }
    
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        
    }
    
    
    
    public static void getTable_kunderPerOrt() throws SQLException
    {
        Statement
        stmt;
        
        ResultSet
        rs;
        
        Integer
        Id ,
        ort_Id ,
        kund_Id;
    
    
    
        kunderPerOrt_TAB = new ArrayList<kunderPerOrtC>();
    
        try (Connection con = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false", username, password);
        )
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Id, ort_Id, kund_Id FROM kunderPerOrt");
            while(rs.next())
            {
                kunderPerOrtC temp = new kunderPerOrtC();
        
                Id = rs.getInt("Id");
                ort_Id = rs.getInt("ort_Id");
                kund_Id = rs.getInt("kund_Id");
        
                temp.setId(Id);
                temp.setOrt_Id(ort_Id);
                temp.setKund_Id(kund_Id);
        
                kunderPerOrt_TAB.add(temp);
        
            }
    
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        
    }
    
    
    
    public static void getTable_orter() throws SQLException
    {
        Statement
        stmt;
        
        ResultSet
        rs;
    
        Integer
        Id ,
        antalKunder;
        
        String
        ortnamn;
      
        Double
        inhandlingsvärde;
    
    
    
        orter_TAB = new ArrayList<ortC>();
    
        try (Connection con = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false", username, password);
        )
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Id, ortnamn, antalKunder, inhandlingsvärde FROM orter");
            while(rs.next())
            {
                ortC temp = new ortC();
        
                Id = rs.getInt("Id");
                ortnamn = rs.getString("ortnamn");
                antalKunder = rs.getInt("antalKunder");
                inhandlingsvärde = rs.getDouble("inhandlingsvärde");
        
                temp.setId(Id);
                temp.setOrtnamn(ortnamn);
                temp.setAntalKunder(antalKunder);
                temp.setInhandlingsvärde(inhandlingsvärde);
        
                orter_TAB.add(temp);
        
            }
    
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        
    }
    
    
    
    public static void getTable_skoBeställningar() throws SQLException
    {
        Statement
        stmt;
        
        ResultSet
        rs;
        
        Integer
        Id ,
        beställning_Id ,
        sko_Id;
    
    
    
        skoBeställningar_TAB = new ArrayList<skoBeställningC>();
    
        try (Connection con = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false", username, password);
        )
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Id, beställning_Id, sko_Id FROM skoBeställningar");
            while(rs.next())
            {
                skoBeställningC temp = new skoBeställningC();
        
                Id = rs.getInt("Id");
                beställning_Id = rs.getInt("beställning_Id");
                sko_Id = rs.getInt("sko_Id");
        
                temp.setId(Id);
                temp.setBeställning_Id(beställning_Id);
                temp.setSko_Id(sko_Id);
        
                skoBeställningar_TAB.add(temp);
        
            }
    
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        
    }
    
    
    
    public static void getTable_skor() throws SQLException
    {
        Statement
        stmt;
        
        ResultSet
        rs;
    
        Integer
        Id ,
        storlek ,
        sålda ,
        iLager;
        
        String
        färg ,
        märke;

        Double
        pris;
    
    
    
        skor_TAB = new ArrayList<skoC>();
    
        try (Connection con = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false", username, password);
        )
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Id, färg, storlek, pris, märke, sålda, iLager FROM skor");
            while(rs.next())
            {
                skoC temp = new skoC();
        
                Id = rs.getInt("Id");
                färg = rs.getString("färg");
                storlek = rs.getInt("storlek");
                pris = rs.getDouble("pris");
                märke = rs.getString("märke");
                sålda = rs.getInt("sålda");
                iLager = rs.getInt("iLager");
        
                temp.setId(Id);
                temp.setFärg(färg);
                temp.setStorlek(storlek);
                temp.setPris(pris);
                temp.setMärke(märke);
                temp.setSålda(sålda);
                temp.setILager(iLager);
        
                skor_TAB.add(temp);
        
            }
    
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        
    }
    
    
    
    public static void getTable_slutILager_Final() throws SQLException
    {
        Statement
        stmt;
        
        ResultSet
        rs;
        
        Integer
        Id ,
        månad ,
        sko_Id;
    
    
    
        slutILager_Final_TAB = new ArrayList<slutILager_FinalC>();
    
        try (Connection con = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false", username, password);
        )
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Id, månad, sko_Id FROM slutILager_Final");
            while(rs.next())
            {
                slutILager_FinalC temp = new slutILager_FinalC();
        
                Id = rs.getInt("Id");
                månad = rs.getInt("månad");
                sko_Id = rs.getInt("sko_Id");
        
                temp.setId(Id);
                temp.setMånad(månad);
                temp.setSko_Id(sko_Id);
        
                slutILager_Final_TAB.add(temp);
        
            }
    
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        
    }
    
    
    
    public static void setMainWindow()
    {
        windowMain = new JFrame();
    
        blackline = BorderFactory.createLineBorder(Color.black);
    
        windowMain.setVisible(true);
        windowMain.setEnabled(true);
        windowMain.setSize(2900, 1100);
        windowMain.setLayout(new GridLayout(2, 1));
        windowMain.setLocationRelativeTo(null);
        windowMain.setResizable(false);
        windowMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
        windowForResults = new JPanel();
        windowForResults.setLayout(new GridLayout(1, 1));
        
    
        textToUserResult = new JLabel("");
        textToUserResult.setSize(1, 1);
        textToUserResult.setVisible(true);
        textToUserResult.setEnabled(true);
        textToUserResult.setBorder(blackline);
        
        windowForResults.add(textToUserResult);
        
        windowMain.add(windowForResults);
        windowMain.revalidate();
    
    
        textToUserShopGrid1_1 = new JLabel();
        textToUserShopGrid2_1 = new JLabel();
        textToUserShopGrid3_1 = new JLabel();
        textToUserShopGrid4_1 = new JLabel();
        textToUserShopGrid5_1 = new JLabel();
        textToUserShopGrid3_2 = new JLabel();
        textToUserShopGrid4_2 = new JLabel();
        textToUserShopGrid5_2 = new JLabel();
    
        textToUserShopGrid1_1.setBorder(blackline);
        textToUserShopGrid2_1.setBorder(blackline);
        textToUserShopGrid3_1.setBorder(blackline);
        textToUserShopGrid4_1.setBorder(blackline);
        textToUserShopGrid5_1.setBorder(blackline);
        textToUserShopGrid3_2.setBorder(blackline);
        textToUserShopGrid4_2.setBorder(blackline);
        textToUserShopGrid5_2.setBorder(blackline);
        
        textToUserShopGrid1_1.setText("Välj en sko att lägga till i varukorgen.");
        textToUserShopGrid2_1.setText("Välj en sko att ta bort från nuvarande varukorg.");
        textToUserShopGrid3_1.setText("Ta bort nuvarande beställning och logga ut.");
        textToUserShopGrid4_1.setText("Se status för slut i lager.");
        textToUserShopGrid5_1.setText("Skicka beställning.");
        textToUserShopGrid3_2.setText("Du kommer att loggas ut.");
        textToUserShopGrid4_2.setText("Information visas i det andra textfältet");
        textToUserShopGrid5_2.setText("Du kommer att loggas ut och beställningen registreras");
        
    }
    
    
    public static void showStartWindow() throws SQLException
    {
        fillAllLists();
    
        user_Id = -1;
        isAtLogIn = true;
        isAtShop = false;
    
        windowForUsersLogIn = new JPanel();
        windowForUsersLogIn.setLayout(new GridLayout(3, 1));
    
        users_Usernames = new JComboBox();
        for(int i1 = 0; i1 < kunder_TAB.size(); i1++)
        {
            users_Usernames.addItem(kunder_TAB.get(i1).getFörnamn() + " " + kunder_TAB.get(i1).getEfternamn());
        }
    
        windowForPasswordLogIn = new JTextField();
        windowForUsersLogIn.add(users_Usernames);
        windowForUsersLogIn.add(windowForPasswordLogIn);
        windowForUsersLogIn.add(logInAttempt);
    
        users_Usernames.setVisible(true);
        users_Usernames.setEnabled(true);
    
        windowForPasswordLogIn.setVisible(true);
        windowForPasswordLogIn.setEnabled(true);
    
        textToUserResult.setText("Logga in med en användare.");
        
        try
        {
            windowMain.remove(windowForShopMain);
        }
        catch(Exception e)
        {}
        windowMain.add(windowForUsersLogIn);
        windowMain.revalidate();
  
    }
    
    
    
    public static void showShopWindow() throws SQLException
    {
        fillAllLists();
        
        isAtLogIn = false;
        isAtShop = true;
    
        windowForShopMain = new JPanel();
        windowForShopTextInfo = new JPanel();
        windowForShopOptions = new JPanel();
        windowForShopButtons = new JPanel();
    
        windowForShopMain.setLayout(new GridLayout(3, 1));
        windowForShopTextInfo.setLayout(new GridLayout(1, 5));
        windowForShopOptions.setLayout(new GridLayout(1, 5));
        windowForShopButtons.setLayout(new GridLayout(1, 5));
    
        windowForShopTextInfo.add(textToUserShopGrid1_1);
        windowForShopTextInfo.add(textToUserShopGrid2_1);
        windowForShopTextInfo.add(textToUserShopGrid3_1);
        windowForShopTextInfo.add(textToUserShopGrid4_1);
        windowForShopTextInfo.add(textToUserShopGrid5_1);
        
        
        
        windowForPasswordLogIn = new JTextField();
        windowForUsersLogIn.add(users_Usernames);
        windowForUsersLogIn.add(windowForPasswordLogIn);
        windowForUsersLogIn.add(logInAttempt);
        
        users_Usernames.setVisible(true);
        users_Usernames.setEnabled(true);
        
        windowForPasswordLogIn.setVisible(true);
        windowForPasswordLogIn.setEnabled(true);
    
        textToUserResult.setText("Välkomen in i butiken!");
        
        
        windowForShopOptions.add(tables_LäggTillIVarukorg);
        windowForShopOptions.add(tables_taBortUrVarukorg);
        windowForShopOptions.add(textToUserShopGrid3_2);
        windowForShopOptions.add(textToUserShopGrid4_2);
        windowForShopOptions.add(textToUserShopGrid5_2);
        
        
        windowForShopButtons.add(läggTillIVarukorgenJB);
        windowForShopButtons.add(taBortUrVarukorgenJB);
        windowForShopButtons.add(loggaUtJB);
        windowForShopButtons.add(seSlutILagerJB);
        windowForShopButtons.add(skickaBeställningJB);
        
    
        windowForShopMain.add(windowForShopTextInfo);
        windowForShopMain.add(windowForShopOptions);
        windowForShopMain.add(windowForShopButtons);
    
        windowMain.remove(windowForUsersLogIn);
        windowMain.add(windowForShopMain);
        windowMain.revalidate();
        
    }
    
    
    
    public static void setVarukorg()
    {
        int
        sko_Id_Save1 ,
        sko_Id_Save2 ,
        skoCount;
        
        skoC
        temp;
        
        String
        addToList1 ,
        addToList2;
        
        ArrayList<String>
        holder1 ,
        addToList3 ,
        addToList4;
        
        
        
        tables_taBortUrVarukorg.removeAllItems();
        skor_Varukorg = new ArrayList<skoC>();
        holder1 = new ArrayList<String>();
        addToList3  = new ArrayList<String>();
        addToList4  = new ArrayList<String>();
      
        for(int i1 = 0; i1 < skoBeställningar_TAB.size(); i1++)
        {
            temp = new skoC();
            
            if(skoBeställningar_TAB.get(i1).getBeställning_Id() == nuvarandeBeställning_Id)
            {
                sko_Id_Save1 = skoBeställningar_TAB.get(i1).getSko_Id();
                sko_Id_Save2 = sko_Id_Save1 - 1;
    
                temp = skor_TAB.get(sko_Id_Save2);
                
                skor_Varukorg.add(temp);
                
            }
            
            
        }
        
        
        for(int i1 = 0; i1 < skor_Varukorg.size(); i1++)
        {
            addToList1 =
            "Färg: " + skor_Varukorg.get(i1).getFärg() + ", Storlek: " + skor_Varukorg.get(i1).getStorlek() + ", Pris: "
            + skor_Varukorg.get(i1).getPris() + ", Märke: " + skor_Varukorg.get(i1).getMärke();
    
            holder1.add(addToList1);
    
        }
    
        for(int i1 = 0; i1 < holder1.size(); i1++)
        {
            skoCount = 0;
            addToList1 = holder1.get(i1);
            
            for(int i2 = 0; i2 < holder1.size(); i2++)
            {
                addToList2 = holder1.get(i2);
                
                if(addToList1.equals(addToList2))
                {
                    ++skoCount;
                }
                
            }
    
            addToList3.add(holder1.get(i1) + ", " + skoCount + "X");
            
        }
    
        for (String sko : addToList3)
        {
            if (!addToList4.contains(sko))
            {
                addToList4.add(sko);
                
            }
            
        }
        
        for(int i1 = 0; i1 < addToList4.size(); i1++)
        {
            tables_taBortUrVarukorg.addItem(addToList4.get(i1));
            
        }
        
        windowMain.revalidate();
        
    }
    
    
    
    public void actionPerformed(ActionEvent e)
    {
        Object
        actionSource;
        
        actionSource = e.getSource();
        
        if(isAtLogIn)
        {
            String
            användarNamnInmat ,
            lösenordInmat ,
            lösenordRätt ,
            användarNamn1 ,
            användarNamn2 ,
            användarNamnFull;
            
            int
            indexUsers;
    
    
    
            användarNamnInmat = users_Usernames.getSelectedItem().toString();
            lösenordInmat = windowForPasswordLogIn.getText();
            indexUsers = -1;
            
            for(int i1 = 0; i1 < kunder_TAB.size(); i1++)
            {
                användarNamn1 = kunder_TAB.get(i1).getFörnamn();
                användarNamn2 = kunder_TAB.get(i1).getEfternamn();
                användarNamnFull = användarNamn1 + " " + användarNamn2;
                
                if(användarNamnFull.equals(användarNamnInmat))
                {
                    indexUsers = i1;
                    break;
                }
            
            }
    
            lösenordRätt = kunder_TAB.get(indexUsers).getLösenord();
            
            if(lösenordRätt.equals(lösenordInmat))
            {
                user_Id = indexUsers + 1;
    
                try
                {
                    try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false" , username , password);)
                    {
                        Statement
                        stmt;
            
                        ResultSet
                        rs;
            
                        String
                        feedString;
            
            
                        feedString = "call läggTillBeställning_SP(" + user_Id + ")";
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(feedString);
            
                    }
                }
                catch(SQLException eSQL)
                {
                    eSQL.printStackTrace();
                }
    
                try
                {
                    try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false" , username , password);)
                    {
                        Statement
                        stmt;
            
                        ResultSet
                        rs;
            
                        String
                        feedString;
            
            
                        feedString = "select Id from beställningar where kund_Id = " + user_Id + " and månad is null";
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(feedString);
                        while(rs.next())
                        {
                            nuvarandeBeställning_Id = rs.getInt("Id");
                        }
            
                    }
                }
                catch(SQLException eSQL)
                {
                    eSQL.printStackTrace();
                }
                
                try
                {
                    showShopWindow();
                }
                catch(SQLException eSQL)
                {
                    eSQL.printStackTrace();
                }
                
            }
            
            else
            {
                textToUserResult.setText("Fel lösenord för användaren.");
                windowMain.revalidate();
                
            }
            
        }
        
        else if(isAtShop)
        {
            if(actionSource == läggTillIVarukorgenJB)
            {
                int
                valdSko;
                
    
                valdSko = tables_LäggTillIVarukorg.getSelectedIndex();
                ++valdSko;
                
              
    
                try
                {
                    try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false" , username , password);)
                    {
                        Statement
                        stmt;
            
                        ResultSet
                        rs;
    
                        String
                        feedString;
                        
    
                        feedString = "call läggTillISkoBeställning_SP(" + nuvarandeBeställning_Id + ", " + valdSko + ")";
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(feedString);
            
                    }
                }
                catch(SQLException eSQL)
                {
                    eSQL.printStackTrace();
                }
    
                try
                {
                    fillAllLists();
                }
                catch(SQLException eSQL)
                {
                    eSQL.printStackTrace();
                }
    
                windowMain.revalidate();
    
            }
    
    
            else if(actionSource == taBortUrVarukorgenJB)
            {
                String
                valdSkoS ,
                holder;
                
                int
                startCut ,
                endCut ,
                savedValueI ,
                skoId ,
                skoIdTemp ,
                skoBeställningId ,
                beställningIdTemp;
                
                double
                savedValueD;
                
                skoC
                temp1 ,
                temp2 ;
    
    
    
                valdSkoS = null;
                temp1 = new skoC();
                skoId = -1;
                skoBeställningId = -1;
    
                
                try
                {
                    valdSkoS = tables_taBortUrVarukorg.getSelectedItem().toString();
                    textToUserResult.setText("Varan togs bort ur korgen.");
                }
                catch(NullPointerException eNull)
                {
                    textToUserResult.setText("Ingen vald vara att ta bort ur korgen.");
                }
              

                startCut = valdSkoS.indexOf(" ");
                ++startCut;
                endCut = valdSkoS.indexOf(",");
                holder = valdSkoS.substring(startCut, endCut);
                endCut = endCut + 2;
                valdSkoS = valdSkoS.substring(endCut);
                temp1.setFärg(holder);
    
                startCut = valdSkoS.indexOf(" ");
                ++startCut;
                endCut = valdSkoS.indexOf(",");
                holder = valdSkoS.substring(startCut, endCut);
                endCut = endCut + 2;
                valdSkoS = valdSkoS.substring(endCut);
                savedValueI = Integer.parseInt(holder);
                temp1.setStorlek(savedValueI);
    
                startCut = valdSkoS.indexOf(" ");
                ++startCut;
                endCut = valdSkoS.indexOf(",");
                holder = valdSkoS.substring(startCut, endCut);
                endCut = endCut + 2;
                valdSkoS = valdSkoS.substring(endCut);
                savedValueD = Double.parseDouble(holder);
                temp1.setPris(savedValueD);
    
                startCut = valdSkoS.indexOf(" ");
                ++startCut;
                endCut = valdSkoS.indexOf(",");
                holder = valdSkoS.substring(startCut, endCut);
                temp1.setMärke(holder);
                
    
                for(int i1 = 0; i1 < skor_TAB.size(); i1++)
                {
                    temp2 = new skoC();
                    temp2 = skor_TAB.get(i1);
                    
                    if(temp1.getFärg().equals(temp2.getFärg()) && temp1.getStorlek().equals(temp2.getStorlek()) && temp1.getPris().equals(temp2.getPris()) && temp1.getMärke().equals(temp2.getMärke()))
                    {
                        skoId = temp2.getId();
                        
                    }
                    
                }
                
                for(int i1 = 0; i1 < skoBeställningar_TAB.size(); i1++)
                {
                    skoIdTemp = skoBeställningar_TAB.get(i1).getSko_Id();
                    beställningIdTemp = skoBeställningar_TAB.get(i1).getBeställning_Id();
                    
                    
                    if(skoIdTemp == skoId && beställningIdTemp == nuvarandeBeställning_Id)
                    {
                        skoBeställningId = skoBeställningar_TAB.get(i1).getId();
                        break;
                        
                    }
                    
                }
    
    
                try
                {
                    try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false" , username , password);)
                    {
                        Statement
                        stmt;
            
                        ResultSet
                        rs;
            
                        String
                        feedString;
            
            
                        feedString = "call taBortUrSkoBeställning_Id_SP(" + skoBeställningId + ")";
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(feedString);
            
                    }
                }
                catch(SQLException eSQL)
                {
                    eSQL.printStackTrace();
                }
    
                try
                {
                    fillAllLists();
                }
                catch(SQLException eSQL)
                {
                    eSQL.printStackTrace();
                }
                
                windowMain.revalidate();
    
                
            }

            else if(actionSource == loggaUtJB)
            {
                try
                {
                    showStartWindow();
                }
                catch(SQLException eSQL)
                {
                    eSQL.printStackTrace();
                }
                
            }

            else if(actionSource == seSlutILagerJB)
            {
                String
                showToUser;
                
                skoC
                tempSkoC;
                
                int
                skoId;
                
                
                showToUser = "";
                
                for(int i1 = 0; i1 < slutILager_Final_TAB.size(); i1++)
                {
                    tempSkoC = new skoC();
                    tempSkoC.setId(slutILager_Final_TAB.get(i1).getId());
                    skoId = tempSkoC.getId() - 1;
                    tempSkoC.setFärg(skor_TAB.get(skoId).getFärg());
                    tempSkoC.setStorlek(skor_TAB.get(skoId).getStorlek());
                    tempSkoC.setPris(skor_TAB.get(skoId).getPris());
                    tempSkoC.setSålda(skor_TAB.get(skoId).getSålda());
                    showToUser = showToUser +
                             "Färg:     " + tempSkoC.getFärg() + "     Storlek:    " + tempSkoC.getStorlek() +
                             "       Pris:       " + tempSkoC.getPris() + "     Sålda:      " + tempSkoC.getSålda()
                             + "<br/>" +
                             "Månad sålt slut:      " + slutILager_Final_TAB.get(i1).getMånad()
                             + "<br/>" + "<br/>";
                }
                
                if(showToUser.equals(""))
                {
                    showToUser = "Det finns inga skor slut i lager att visa";
                    
                }
                else
                {
                    showToUser = "<html>" + showToUser + "<html>";
                }
    
                textToUserResult.setText(showToUser);
                windowMain.revalidate();
    
            }

            else if(actionSource == skickaBeställningJB)
            {
                String
                månadTempS;
    
                LocalDateTime
                datumLDT;
                
                LocalDate
                currentDate;
    
                Month
                månadTempM;
                
                int
                månadTempI;
    
                
    
                datumLDT = LocalDateTime.now();
                månadTempM = datumLDT.getMonth();
                månadTempI = månadTempM.getValue();
                månadTempS = String.valueOf(månadTempI);
                
                
    
                try
                {
                    try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/skobutik?serverTimezone=UTC&useSSL=false" , username , password);)
                    {
                        Statement
                        stmt;
            
                        ResultSet
                        rs;
            
                        String
                        feedString;
            
            
                        feedString = "call ändraBeställning_SP(" + nuvarandeBeställning_Id + ", " + månadTempS + ")";
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(feedString);
            
                    }
                }
                catch(SQLException eSQL)
                {
                    eSQL.printStackTrace();
                }
                try
                {
                    showStartWindow();
                }
                catch(SQLException eSQL)
                {
                    eSQL.printStackTrace();
                }
                
            }
            
        }
    
    }
    
}