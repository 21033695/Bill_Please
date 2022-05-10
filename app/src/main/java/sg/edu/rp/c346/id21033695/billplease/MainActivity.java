package sg.edu.rp.c346.id21033695.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;
    RadioGroup rgMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amountInput);
        numPax = findViewById(R.id.numPax);
        totalBill = findViewById(R.id.textTotalBill);
        eachPays = findViewById(R.id.textEachPays);
        svs = findViewById(R.id.noSvs);
        gst = findViewById(R.id.gst);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        discount = findViewById(R.id.editInputDiscount);
        rgMode = findViewById(R.id.paymentModeRG);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().trim().length() !=0 &&
                        numPax.getText().toString().trim().length() !=0);

                    double oriAmt = Double.parseDouble(amount.getText().toString());
                    double newAmt = 0.0;

                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }
                    //Discount
                    if (discount.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }

                    String mode = "in cash.";
                        if (rgMode.getCheckedRadioButtonId() == R.id.payNowBtn) {
                        mode = " via Paynow to 912345678";
                    }
                    totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(numPax.getText().toString());
                    if (numPerson != 1)
                        eachPays.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson) + mode);
                    else
                        eachPays.setText("Each Pays: $" + newAmt + mode);

                    reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            amount.setText("");
                            numPax.setText("");
                            svs.setChecked(false);
                            gst.setChecked(false);
                            discount.setText("");
                    }
        });







    }
}