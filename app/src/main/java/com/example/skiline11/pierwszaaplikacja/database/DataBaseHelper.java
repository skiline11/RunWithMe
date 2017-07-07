package com.example.skiline11.pierwszaaplikacja.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.skiline11.pierwszaaplikacja.AddNewResult;
import com.example.skiline11.pierwszaaplikacja.ChooseDistance;
import com.example.skiline11.pierwszaaplikacja.LogIn;
import com.example.skiline11.pierwszaaplikacja.MainMenu;
import com.example.skiline11.pierwszaaplikacja.model.Person;
import com.example.skiline11.pierwszaaplikacja.model.Run;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "BD.sqlite3";
    public static final String DB_PATH = "/data/data/com.example.skiline11.pierwszaaplikacja/databases/";
    private static Context mContext;
    private static SQLiteDatabase mDatabase;
    public int ileBiegow = 0;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void openDatabase() {
        System.out.println("------------------------------ Jestem w funkcji open database");
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        String dbPath = mContext.getDatabasePath(DB_NAME).getPath(); // TA GŁUPIA LINIJKA POWODOWAŁA WSZYSTKIE PROBLEMY !!!!!!!!!!!!!
//        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        System.out.println("-------------------------------- Baza danych nie była otwarta");
//        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
        mDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);

    }

    public static void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public static List<Person> getListOfPeoples() {
        System.out.println("------------------------------- Jestem w funkcji getListOfPeoples()");
        Person person = null;
        List<Person> peopleList = new ArrayList<>();
        openDatabase();
        System.out.println("------------------------------- Baza danych jest : " + mDatabase.isOpen());
        Cursor cursor = mDatabase.rawQuery("Select * from Osoba", null);
        System.out.println("------------------------------- Wykonałem .query() w funkcji getListOfPeoples()");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            person = new Person(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            peopleList.add(person);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return peopleList;
    }

    public static int numberOfRun()
    {
        Cursor cursor = mDatabase.rawQuery("Select (Select Count(*) from BiegNa100m) + (Select Count(*) from BiegNa800m) + (Select Count(*) from BiegNa1500m) + (Select Count(*) from BiegNa5000m) + (Select Count(*) from BiegNa10000m) + (Select Count(*) from Maraton)", null);
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public static boolean addPersonIfNotExist()
    {
        openDatabase();
        System.out.println("______________________Z poziomu funkcji myPesel = " + LogIn.myPesel);
        //Sprawdza czy osoba o takim peselu istnieje w bazie
        Cursor cursor = mDatabase.rawQuery("Select count(*) from Osoba where idOsoby = '" + LogIn.myPesel + "'", null);
        cursor.moveToNext();
        if(cursor.getInt(0) == 0) // To znaczy że takiej osoby nie ma w bazie i trzeba ją dodać
        {
            System.out.println("---------------------Dodaję taką osobę:");
            System.out.println("Insert into Osoba values ('" + LogIn.myPesel + "', '" + LogIn.myName + "', '" + LogIn.mySurname + "')");
            mDatabase.execSQL("Insert into Osoba values ('" + LogIn.myPesel + "', '" + LogIn.myName + "', '" + LogIn.mySurname + "')");
            closeDatabase();
            openDatabase();
            System.out.println("_____________________Sprawdzam czy ta osoba jest już w bazie danych");
            Cursor cursor2 = mDatabase.rawQuery("Select count(*) from Osoba where idOsoby = '" + LogIn.myPesel + "'", null);
            cursor2.moveToNext();
            if(cursor2.getInt(0) == 0) System.out.println("______________Tej osoby znowu nie ma w bazie :-(");
            else System.out.println("--------------------------Ta osoba jest w bazie :-) ");
            closeDatabase();
            return true;
        }
        else // Osoba o takim peselu istnieje więc sprawdzam czy imie i nazwisko są poprawne
        {
            System.out.println("______________________Osoba o takim peselu jest w bazie");
            cursor = mDatabase.rawQuery("Select count(*) from osoba where idOsoby = '" + LogIn.myPesel + "' and imie = '" + LogIn.myName + "' and nazwisko = '" + LogIn.mySurname + "'", null);
            cursor.moveToNext();
            if(cursor.getInt(0) != 0) // Podane imie i nazwisko są poprawne
            {
                closeDatabase();
                System.out.println("___________________________Podane imie i nazwisko są poprawne");
                return true;
            }
            else // Podane imie i nazwisko są niepoprawne
            {
                closeDatabase();
                System.out.println("__________________Podane imie lub nazwisko są niepoprawne ");
                return false;
            }
        }
    }

    public static void addNewResult()
    {
        //TODO - upewnić się że działą
        openDatabase();
        String run_details, data, year, month, day, result = "";
        year = "" + AddNewResult.year_x;
        if(AddNewResult.month_x < 10) month = "0" + AddNewResult.month_x;
        else month = "" + AddNewResult.month_x;
        if(AddNewResult.day_x < 10) day = "0" + AddNewResult.day_x;
        else day = "" + AddNewResult.day_x;
        data = "'" + year + "-" + month + "-" + day + "'";
        switch(AddNewResult.choosenDistance)
        {
            case "BiegNa100m" :
                result = AddNewResult.seconds_x + ", " + AddNewResult.miliseconds_x;
                break;
            case "BiegNa800m" :
            case "BiegNa1500m" :
            case "BiegNa5000m" :
                result = AddNewResult.minutes_x + ", " + AddNewResult.seconds_x + ", " + AddNewResult.miliseconds_x;
                break;
            case "BiegNa10000m" :
            case "Maraton" :
                result = AddNewResult.hours_x + ", " + AddNewResult.minutes_x + ", " + AddNewResult.seconds_x;
                break;
            default:
                break;
        }
        run_details = "(NULL, " + LogIn.myPesel + ", " + result + ", " + data + ")";
        mDatabase.execSQL("Insert into " + AddNewResult.choosenDistance + " values " + run_details);
        closeDatabase();
    }

    public static void addNewFriends(String friendPesel)
    {
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select count(*) from Znajomosci where (idOsoby1 = '" + LogIn.myPesel + "' and idOsoby2 = '" + friendPesel + "') or (idOsoby1 = '" + friendPesel + "' and idOsoby2 = '" + LogIn.myPesel + "')", null);
        cursor.moveToNext();
        if(cursor.getInt(0) == 0)
        {
            System.out.println("Dodaj nową znajomosc");
            mDatabase.execSQL("Insert into Znajomosci values ('" + LogIn.myPesel + "', '" + friendPesel + "')");
        }
        closeDatabase();
    }

    public static List<Run> getRank()
    {

        openDatabase();
        Cursor cursor;
        String query = "", parametryCzasu, parametry, IAndMyFriends;
        System.out.println("Jestem w rankingu : " + MainMenu.rankType);
        switch (ChooseDistance.choosenDistance) {
            case "Maraton":
                parametryCzasu = "ileGodzin, ileMinut, ileSekund";
//                cursor = mDatabase.rawQuery("Select imie, nazwisko, dataBiegu, ileGodzin, ileMinut, ileSekund from Maraton NATURAL JOIN Osoba" + where + "ORDER BY ileGodzin, ileMinut, ileSekund", null);
                break;
            case "BiegNa10000m":
                parametryCzasu = "ileGodzin, ileMinut, ileSekund, ileSetnychSekundy";
//                cursor = mDatabase.rawQuery("Select imie, nazwisko, dataBiegu, ileGodzin, ileMinut, ileSekund, ileSetnychSekundy from BiegNa10000m NATURAL JOIN Osoba" + where + "ORDER BY ileGodzin, ileMinut, ileSekund, ileSetnychSekundy", null);
                break;
            case "BiegNa5000m":
            case "BiegNa1500m":
            case "BiegNa800m":
                parametryCzasu = "ileMinut, ileSekund, ileSetnychSekundy";
//                cursor = mDatabase.rawQuery("Select imie, nazwisko, dataBiegu, ileMinut, ileSekund, ileSetnychSekundy from BiegNa" + ChooseDistance.choosenDistance + " NATURAL JOIN Osoba" + where + "ORDER BY ileMinut, ileSekund, ileSetnychSekundy", null);
                break;
            default: // case "100m":
                parametryCzasu = "ileSekund, ileSetnychSekundy";
//                cursor = mDatabase.rawQuery("Select imie, nazwisko, dataBiegu, ileSekund, ileSetnychSekundy from BiegNa100m NATURAL JOIN Osoba" + where + "ORDER BY ileSekund, ileSetnychSekundy", null);
                break;
        }
        parametry = "imie, nazwisko, dataBiegu, " + parametryCzasu;
        switch (MainMenu.rankType)
        {
            case "Friends Rank":
                IAndMyFriends = "(Select idOsoby1 as idOsoby from Znajomosci where idOsoby2 = " + LogIn.myPesel + " UNION Select idOsoby2 from Znajomosci where idOsoby1 = " + LogIn.myPesel + " or idOsoby2 = " + LogIn.myPesel+ ")";
                query = "Select " + parametry + " from " + ChooseDistance.choosenDistance + " NATURAL JOIN Osoba where idOsoby in " + IAndMyFriends + " ORDER BY " + parametryCzasu;
                break;
            case "Your Results":
                query = "Select " + parametry + " from " + ChooseDistance.choosenDistance + " NATURAL JOIN Osoba where idOsoby = " + LogIn.myPesel + " ORDER BY " + parametryCzasu;
                break;
            case "World Rank":
                query = "Select " + parametry + " from " + ChooseDistance.choosenDistance + " NATURAL JOIN Osoba ORDER BY " + parametryCzasu;
                break;
        }
        System.out.println("____________________ QUERY:_" + query + "_");
        cursor = mDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        Run run = null;
        List<Run> wrList = new ArrayList<>();
        switch(ChooseDistance.choosenDistance) {
            case "Maraton":
                while (!cursor.isAfterLast()) {
                    run = new Run(cursor.getString(0) + " " + cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), 0);
                    wrList.add(run);
                    cursor.moveToNext();
                }
                break;
            case "BiegNa10000m":
                while (!cursor.isAfterLast()) {
                    run = new Run(cursor.getString(0) + " " + cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6));
                    wrList.add(run);
                    cursor.moveToNext();
                }
                break;
            case "BiegNa800m":
            case "BiegNa1500m":
            case "BiegNa5000m":
                while (!cursor.isAfterLast()) {
                    run = new Run(cursor.getString(0) + " " + cursor.getString(1), cursor.getString(2), 0, cursor.getInt(3), cursor.getInt(4), cursor.getInt(5));
                    wrList.add(run);
                    cursor.moveToNext();
                }
                break;
            case "BiegNa100m":
                while (!cursor.isAfterLast()) {
                    run = new Run(cursor.getString(0) + " " + cursor.getString(1), cursor.getString(2), 0, 0, cursor.getInt(3), cursor.getInt(4));
                    wrList.add(run);
                    cursor.moveToNext();
                }
                break;
            default:
                break;
        }
        cursor.close();
        closeDatabase();
        return wrList;

    }

//    Copy the database from assets
    private void copyDataBase() throws IOException
    {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

//    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
//    //destination path (location) of our database on device
//    private static String DB_PATH = "";
//    private static String DB_NAME ="BD";// Database name
//    private SQLiteDatabase mDataBase;
//
//    public DataBaseHelper(Context context)
//    {
//        super(context, DB_NAME, null, 1);// 1? Its database Version
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE Osoba (" +
//                        "idOsoby TEXT NOT NULL PRIMARY KEY," +
//                        "imie TEXT NOT NULL," +
//                        "nazwisko TEXT NOT NULL);");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//    public void createDataBase() throws IOException
//    {
//        //If the database does not exist, copy it from the assets.
//
//        boolean mDataBaseExist = checkDataBase();
//        if(!mDataBaseExist)
//        {
//            this.getReadableDatabase();
//            this.close();
//            try
//            {
//                //Copy the database from assests
//                copyDataBase();
//                Log.e(TAG, "createDatabase database created");
//            }
//            catch (IOException mIOException)
//            {
//                throw new Error("ErrorCopyingDataBase");
//            }
//        }
//    }
//
//    //Check that the database exists here: /data/data/your package/databases/Da Name
//    private boolean checkDataBase()
//    {
//        File dbFile = new File(DB_PATH + DB_NAME);
//        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
//        return dbFile.exists();
//    }
//
//
//
//    //Open the database, so we can query it
//    public boolean openDataBase() throws SQLException
//    {
//        String mPath = DB_PATH + DB_NAME;
//        //Log.v("mPath", mPath);
//        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
//        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
//        return mDataBase != null;
//    }
//
//    @Override
//    public synchronized void close()
//    {
//        if(mDataBase != null)
//            mDataBase.close();
//        super.close();
//    }
}