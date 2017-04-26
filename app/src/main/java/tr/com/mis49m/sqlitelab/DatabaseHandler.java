package tr.com.mis49m.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "db";
    private static final int DB_VERSION = 1;

    private static final String TBL_NAME = "tblContact";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /* CREATE TABLE tblContact (ID    INTEGER PRIMARY KEY,
                                    NAME  TEXT,
                                    PHONE TEXT)
*/
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(TBL_NAME).append(" (")
                .append(KEY_ID).append(" INTEGER PRIMARY KEY, ")
                .append(KEY_NAME).append(" TEXT, ")
                .append(KEY_PHONE).append(" TEXT ) ");

        sqLiteDatabase.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("ALTER TABLE " + TBL_NAME);

        //onCreate(sqLiteDatabase);
    }

    public long insertContact(Contact contact){
        // INSERT INTO tblContact (NAME, PHONE) VALUES ('a','123')

        SQLiteDatabase sqlDB = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, contact.getName());
        cv.put(KEY_PHONE, contact.getPhoneNumber());

        long r = sqlDB.insert(TBL_NAME, null, cv);

        return r;
    }

    public Contact getContact(int id){
        // SELECT ID, NAME, PHONE FROM tblContact WHERE ID = ?
        Contact contact = new Contact();

        SQLiteDatabase sqlDB = getReadableDatabase();

        Cursor cursor = sqlDB.query(
                TBL_NAME,
                new String[]{KEY_ID,KEY_NAME,KEY_PHONE},
                "id = ? ",
                new String[] { String.valueOf(id) },
                null,
                null,
                null
        );
        if(cursor!=null){
            cursor.moveToFirst();

            contact.setId(cursor.getInt(0));
            contact.setName(cursor.getString(1));
            contact.setPhoneNumber(cursor.getString(2));

        }

        cursor.close();
        sqlDB.close();

        return contact;
    }

    public void update(Contact contact){
        // UPDATE tblContact SET name = ?, phone=? WHERE id = ?

        SQLiteDatabase sqlDB = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, contact.getName());
        cv.put(KEY_PHONE, contact.getPhoneNumber());

        long r= sqlDB.update(
                TBL_NAME,
                cv,
                KEY_ID + " = ?",
                new String[]{ String.valueOf(contact.getId()) }
        );

        sqlDB.close();
    }

}
