package summer.app;

import java.util.Calendar;
import java.util.Random;

import summer.app.MainActivity.PlaceholderFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class DiceActivity extends ActionBarActivity {
	
	private static final Random r = new Random(Calendar.getInstance().getTimeInMillis());
	private int flag = 1; //Inicialmente, dado en movimiento
	private Handler handler;
	private Runnable runnable;
	
	/**
     * tirarDado. Devuelve un valor aleatorio entre el par de numeros pasados
     * por argumento.
     *
     * @param min: numero minimo
     * @param max: numero maximo
     * @return int: valor de la tirada
     * @throws InterruptedException
     */
    public static int tirarDado(int min, int max) {

        return min + r.nextInt(max - min + 1);
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dice);	
		
		//Localizar los controles
        final TextView dice = (TextView)findViewById(R.id.txt_dice);
        
        handler = new Handler();
        runnable = new Runnable() {        	
        	public void run(){
            	int num = tirarDado(1,6);
            	while (Integer.toString(num).equals(dice.getText())){
            		 num = tirarDado(1,6);        		
            	}
            	dice.setText(Integer.toString(num)); 
            	handler.postDelayed(this, 250);
             }        	
        };
        handler.postDelayed(runnable, 250);        
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
		
		//Localizar los controles
        final TextView dice = (TextView)findViewById(R.id.txt_dice);
        final TextView res = (TextView)findViewById(R.id.txt_result);
        
        int action = MotionEventCompat.getActionMasked(event);
		
		if (flag == 1 && action == MotionEvent.ACTION_DOWN) { //Parar dado
			
			// Para hilo
			handler.removeCallbacks(runnable);			
			
			//Un random mas
        	int num = tirarDado(1,6);
        	while (Integer.toString(num).equals(dice.getText())){
        		 num = tirarDado(1,6);        		
        	}
        	dice.setText(Integer.toString(num)); 
        	res.setText(Integer.toString(num));
        	
        	flag = 0;
            
            return true;   
		} 
		
		if (flag == 0 && action == MotionEvent.ACTION_DOWN) { //Poner en movimiento
			
			//Reiniciar hilo
			handler.post(runnable);
			
			res.setText("");
			flag = 1;
			
			return true;
		}
        
        return false;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dice, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		switch (item.getItemId()) { 
		case R.id.menu_stats:
            

            return true;
        case R.id.menu_dices:
            

            return true;
        case R.id.menu_number:
            

            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
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
			View rootView = inflater.inflate(R.layout.fragment_dice, container,
					false);
			return rootView;
		}
	}

}
