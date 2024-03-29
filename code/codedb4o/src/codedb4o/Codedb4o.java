/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codedb4o;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.io.File;
import codedb4o.Pilot;


/**
 *
 * @author pedro
 * java -cp /code/codedb4o/db4o-7.2.44.10827-java1.2.jar:/code/codedb4o/build/classes/ codedb4o.Codedb4o
 */

public class Codedb4o extends Util
{
    final static String DB4OFILENAME = System.getProperty("user.home")+ "/pilot.db4o";
	public static void main(String[] args)
	{
		new File(DB4OFILENAME).delete();
		accessDb4o();
		new File(DB4OFILENAME).delete();
		ObjectContainer db=Db4o.openFile(DB4OFILENAME);
		try
		{
			storeFirstPilot(db);
			storeSecondPilot(db);
			retrieveAllPilots(db);
			retrievePilotByName(db);
			retrievePilotByExactPoints(db);
			updatePilot(db);
			deleteFirstPilotByName(db);
			deleteSecondPilotByName(db);
		}
		finally { db.close(); }
	}

	public static void accessDb4o()
	{
		ObjectContainer db=Db4o.openFile(DB4OFILENAME);
		try
		{
			// do something with db4o
		}
		finally
		{
			db.close();
		}
	}

	public static void storeFirstPilot(ObjectContainer db)
	{
		Pilot pilot1=new Pilot("Michael Schumacher",100);
		db.store(pilot1);
		System.out.println("Stored "+pilot1);
	}

	public static void storeSecondPilot(ObjectContainer db)
	{
		Pilot pilot2=new Pilot("Rubens Barrichello",99);
		db.store(pilot2);
		System.out.println("Stored "+pilot2);
	}

	public static void retrieveAllPilotQBE(ObjectContainer db)
	{
		Pilot proto=new Pilot(null,0);
		ObjectSet result=db.queryByExample(proto);
		listResult(result);
	}

	public static void retrieveAllPilots(ObjectContainer db)
	{
		ObjectSet result=db.queryByExample(Pilot.class);
		listResult(result);
	}

	public static void retrievePilotByName(ObjectContainer db)
	{
		Pilot proto=new Pilot("Michael Schumacher",0);
		ObjectSet result=db.queryByExample(proto);
		listResult(result);
	}

	public static void retrievePilotByExactPoints(ObjectContainer db)
	{
		Pilot proto=new Pilot(null,100);
		ObjectSet result=db.queryByExample(proto);
		listResult(result);
	}

	public static void updatePilot(ObjectContainer db)
	{
		ObjectSet result=db.queryByExample(new Pilot("Michael Schumacher",0));
		Pilot found=(Pilot)result.next();
		found.addPoints(11);
		db.store(found);
		System.out.println("Added 11 points for "+found);
		retrieveAllPilots(db);
	}

	public static void deleteFirstPilotByName(ObjectContainer db)
	{
		ObjectSet result=db.queryByExample(new Pilot("Michael Schumacher",0));
		Pilot found=(Pilot)result.next();
		db.delete(found);
		System.out.println("Deleted "+found);
		retrieveAllPilots(db);
	}

	public static void deleteSecondPilotByName(ObjectContainer db)
	{
		ObjectSet result=db.queryByExample(new Pilot("Rubens Barrichello",0));
		Pilot found=(Pilot)result.next();
		db.delete(found);
		System.out.println("Deleted "+found);
		retrieveAllPilots(db);
	}
}
