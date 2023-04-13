package edmt.dev.androidsadecafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edmt.dev.androidsadecafe.Model.User;

public class SignIn extends AppCompatActivity {

    EditText txtPhone,txtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txtPassword=(EditText)findViewById(R.id.txtPassword);
        txtPhone=(EditText)findViewById(R.id.txtPhone);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference table_user=database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               final ProgressDialog mDialog=new ProgressDialog(SignIn.this);
               mDialog.setMessage("Lütfen bekleyin..");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(txtPhone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            User user = dataSnapshot.child(txtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(txtPassword.getText().toString())) {
                                Toast.makeText(SignIn.this, "Giriş başarılı", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignIn.this, "Giriş başarısız", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(SignIn.this, "kullanıcı bulunamadı", Toast.LENGTH_SHORT).show();
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