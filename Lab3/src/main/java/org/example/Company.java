package org.example;

public class Company implements Node, Comparable<Company>
{
    private String name;

    public Company(String name)
    {
        this.name = name;
    }

    @Override
    public int compareTo(Company otherCompany)
    {
        return this.name.compareTo(otherCompany.getName());
    }

    @Override
    public String getName() {
        return name;
    }
}
