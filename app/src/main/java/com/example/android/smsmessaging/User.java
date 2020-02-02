package com.example.android.smsmessaging;


public class User
{
    private String username;        // User name
    // gender, 0 is female // 1 is male //2 is other
    private String gender;
    private String pnum;
    private String plink;
    private boolean isBusiness;
    private String message;
    private double location [];


    /* Constructors */
    public User()
    {
        String gender = "";
        pnum = "";
        plink = "";
        isBusiness = false;
    }

    public User(String newName)
    {
        username = newName;
    }

    /* Accessors and Mutators */
    public void setName(String newName)
{
    username = newName;
}

    public String getName()
    {
        return username;
    }
    public void setGender(String g)
    {
        gender = g;
    }

    public String getGender()
    {
        return gender;
    }

    public void setpnum(String k)
    {
        pnum = k;
    }
    public String getpnum()
    {
        return pnum;
    }
    public void setMessage(String k)
    {
        message = k;
    }
    public String getMessage()
    {
        return message;
    }
    public void setplink(String k)
{
    plink = k;
}
    public String getplink()
    {
        return plink;
    }
    public void setBusiness(Boolean b)
    {
        isBusiness = b;
    }
    public boolean getBusiness()
    {
        return isBusiness;
    }



    public String toMessage()
    {
        String s = "";
        s+=getName()+getplink()+getGender()+ getMessage();
        return s;
    }
    private static final User cuse= new User();
    public static User cuser() {return cuse;}
}

