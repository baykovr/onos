package edu.frescoplus.core.service.storage;

import edu.frescoplus.core.service.AFP_Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FP_Storage extends AFP_Service
{
    public HashSet<Integer> connections;
    public ArrayList<String> events;
    public HashMap<String,ArrayList<FP_DBEntry>> app_tables;

    public FP_Storage()
    {
        connections = new HashSet<Integer>();
        events  = new ArrayList<String>();
        app_tables = new HashMap<String, ArrayList<FP_DBEntry> >();
    }
    public void makeTable(String id, ArrayList<FP_DBEntry> content )
    {
        app_tables.put(id, content);
    }

    public ArrayList<String> getTableAsStringArray(String tableName)
    {
        ArrayList<FP_DBEntry> table = app_tables.get(tableName);
        ArrayList<String> stringTable = new ArrayList<String>();
        for (FP_DBEntry entry : table)
        {
            stringTable.add(entry.toString());
        }
        return stringTable;
    }
}
