package sophia.com.ecommerce2;

import android.os.AsyncTask;
import android.os.DropBoxManager;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by archimede on 10/07/17.
 */

public class AsyncTaskSample extends AsyncTask<String, Float, Object> {

    @Override
    protected Object doInBackground(String... params) {

        for (int i=0;i<100;i++){
            publishProgress(new Float(i));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d("onProgressUpdate", "progress" );
    }

    @Override
    protected void onProgressUpdate(Float... values) {
        super.onProgressUpdate(values);

        Log.d("onProgressUpdate", "progress" + values);
    }
}
