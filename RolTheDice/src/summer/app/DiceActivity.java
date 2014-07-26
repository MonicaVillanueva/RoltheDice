package summer.app;

import summer.app.R;

import java.util.Calendar;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DiceActivity extends ActionBarActivity {
	
	private static final Random r = new Random(Calendar.getInstance().getTimeInMillis());
	private int flag = 1; //Inicialmente, dado en movimiento
	private int min;
	private int max;
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
        
        //Establecer minimo y maximo
        Bundle bundle = this.getIntent().getExtras();
        min = bundle.getInt("VMIN");
        Log.d("DiceActivity", "min: "+ min);
        max = bundle.getInt("VMAX");
        Log.d("DiceActivity", "max: "+ max);
        
        //Localizar los controles
        final TextView dice = (TextView)findViewById(R.id.txt_dice);
        //Titulo
        int numtitle = max - min + 1;
        Log.d("DiceActivity", "titulo: "+ numtitle);
        this.setTitle(Integer.toString(numtitle)+"-D"); 
        
        //Bucle dado
        handler = new Handler();
        runnable = new Runnable() {        	
        	public void run(){
            	int num = tirarDado(min,max);
            	while (Integer.toString(num).equals(dice.getText())){
            		 num = tirarDado(min, max);        		
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
        	int num = tirarDado(min, max);
        	while (Integer.toString(num).equals(dice.getText())){
        		 num = tirarDado(min, max);        		
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
		
		int numtitle;
		
		switch (item.getItemId()) { 
		case R.id.menu_stats:
            

            return true;
        case R.id.d4:
        	//Valores
        	min = 1;
        	max = 4;  
        	
        	//Titulo
            numtitle = max - min + 1;
            Log.d("DiceActivity", "titulo: "+ numtitle);
            this.setTitle(Integer.toString(numtitle)+"-D"); 
            
            return true;
        case R.id.d6:
        	//Valores
        	min = 1;
        	max = 6;  
        	
        	//Titulo
            numtitle = max - min + 1;
            Log.d("DiceActivity", "titulo: "+ numtitle);
            this.setTitle(Integer.toString(numtitle)+"-D");  
            
            return true;
        case R.id.d8:
        	//Valores
        	min = 1;
        	max = 8; 
        	
        	//Titulo
            numtitle = max - min + 1;
            Log.d("DiceActivity", "titulo: "+ numtitle);
            this.setTitle(Integer.toString(numtitle)+"-D"); 
            
            return true;
        case R.id.d10:
        	//Valores
        	min = 1;
        	max = 10;  
        	
        	//Titulo
            numtitle = max - min + 1;
            Log.d("DiceActivity", "titulo: "+ numtitle);
            this.setTitle(Integer.toString(numtitle)+"-D"); 
            
            return true;
        case R.id.d12:
        	//Valores
        	min = 1;
        	max = 12;  
        	
        	//Titulo
            numtitle = max - min + 1;
            Log.d("DiceActivity", "titulo: "+ numtitle);
            this.setTitle(Integer.toString(numtitle)+"-D"); 
            
            return true;
        case R.id.d20:
        	//Valores
        	min = 1;
        	max = 20;  
        	
        	//Titulo
            numtitle = max - min + 1;
            Log.d("DiceActivity", "titulo: "+ numtitle);
            this.setTitle(Integer.toString(numtitle)+"-D"); 
            
            return true;
        case R.id.d100:
        	//Valores
        	min = 1;
        	max = 100;  
        	
        	//Titulo
            numtitle = max - min + 1;
            Log.d("DiceActivity", "titulo: "+ numtitle);
            this.setTitle(Integer.toString(numtitle)+"-D"); 
            
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
