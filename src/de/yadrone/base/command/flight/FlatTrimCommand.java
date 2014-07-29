package de.yadrone.base.command.flight;

import de.yadrone.base.command.ATCommand;

public class FlatTrimCommand extends ATCommand
{
    @Override
    protected String getID()
    {
        return "FTRIM";
    }

    @Override
    protected Object[] getParameters()
    {
        return new Object[] {};
    }
}
