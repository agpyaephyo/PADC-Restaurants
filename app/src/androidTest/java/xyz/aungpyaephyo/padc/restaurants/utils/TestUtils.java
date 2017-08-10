package xyz.aungpyaephyo.padc.restaurants.utils;

import android.support.test.InstrumentationRegistry;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by aung on 7/29/17.
 */

public class TestUtils {

    public static final String OFFLINE_RESTAURANT_LIST = "restaurants-v1.json";
    private static final String TEST_DATA_ROOT = "data";

    /**
     * @param fileName - name of Json File.
     * @return JSONObject from loaded file.
     * @throws IOException
     * @throws JSONException
     */
    public static String loadDummyData(String fileName) throws IOException, JSONException {
        byte[] buffer = readJsonFile(TEST_DATA_ROOT + "/" + fileName);
        return new String(buffer, "UTF-8").toString();
    }

    /**
     * Read text from assets folder.
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static byte[] readJsonFile(String fileName) throws IOException {
        InputStream inStream = InstrumentationRegistry.getContext().getAssets().open(fileName);
        int size = inStream.available();
        byte[] buffer = new byte[size];
        inStream.read(buffer);
        inStream.close();
        return buffer;
    }
}
