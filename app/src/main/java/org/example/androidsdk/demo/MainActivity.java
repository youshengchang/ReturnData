package org.example.androidsdk.demo;

import java.util.List;

import org.example.androidsdk.demo.data.Flower;
import org.example.androidsdk.demo.data.FlowerData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final int REQUEST_CODE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		List<Flower> flowers = new FlowerData().getFlowers();
		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);

		for (final Flower flower : flowers) {
			Button button = new Button(this);
			button.setText(flower.flowerName);
			layout.addView(button);

			button.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this, DetailActivity.class);
					intent.putExtra("flowerName", flower.flowerName);
					intent.putExtra("imageResource", flower.imageResource);
					intent.putExtra("instructions", flower.instructions);
					startActivityForResult(intent, REQUEST_CODE);
				}
			});
		}

	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode == REQUEST_CODE)&&(resultCode == RESULT_OK)){

            String flowerName = data.getStringExtra("flowerName");
            String action = data.getStringExtra("action");
            if(action.equals("add")){
                Toast.makeText(this, "Adding 1 " + flowerName + " to Cart", Toast.LENGTH_LONG).show();
                
            }
        }
    }
}
