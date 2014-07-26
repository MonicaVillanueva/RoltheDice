package summer.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
    }
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
		
		//Creamos el Intent
        Intent intent = new Intent(MainActivity.this, DiceActivity.class);
        //Añadimos información a pasar (por defecto dado 6 caras)
        Bundle b = new Bundle();
        b.putInt("VMAX", 6);
        b.putInt("VMIN", 1);
        intent.putExtras(b);        
        
        int action = MotionEventCompat.getActionMasked(event);
        if (action == MotionEvent.ACTION_DOWN){
        	//Iniciamos la nueva actividad
            startActivity(intent);
            
            return true;        	
        }
        
        return false;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
