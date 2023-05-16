package edmt.dev.androidsadecafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edmt.dev.androidsadecafe.Model.User;

public class SignUp extends AppCompatActivity {

    EditText txtPhone,txtPassword,txtName;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        txtPassword=(EditText)findViewById(R.id.txtPassword);
        txtPhone=(EditText)findViewById(R.id.txtPhone);
        txtName=(EditText)findViewById(R.id.txtName);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference table_user=database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog=new ProgressDialog(SignUp.this);
                mDialog.setMessage("Lütfen bekleyin..");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(txtPhone.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Bu telefon numarası ile daha önce kayıt yapılmıştır.", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            mDialog.dismiss();
                            User user=new User(txtName.getText().toString(),txtPassword.getText().toString());
                            table_user.child(txtPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Başarıyla kaydolundu.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}