package com.parves1527.download.info;

public class Password
{
    private final String passString;

    public Password(String passString)
    {
        this.passString = passString;
    }

    public String getPassString()
    {
        return passString;
    }

    @Override
    public String toString()
    {
        return "Password{" + "passString=" + passString + '}';
    }        
}
