package www.runa.mapaarica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    TextView editLatitud,editLongitud;
    Button btnGuardarBuscarDatos,btnBuscarGPS,btnVer;
    DBGPS dbgps ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbgps = new DBGPS(this);




//////////////////////////////////////////////////////////
        editLatitud = (EditText)findViewById(R.id.editLatitud);
        editLongitud = (EditText)findViewById(R.id.editLongitud);
//////////////////////////////////////////////////////////
        btnVer = (Button)findViewById(R.id.btnVer);
        btnGuardarBuscarDatos = (Button)findViewById(R.id.btnGuardarBuscarDatos);
        //btnBuscarGPS = (Button)findViewById(R.id.btnBuscarGPS);
//////////////////////////////////////////////////////////



//////////////////////////////////////////////////////////

        verDatos();
        AgregarDatos();
    }

//////////////////////////////////////////////////////////
    private void AgregarDatos() {
        btnGuardarBuscarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                String Longitud = editLongitud.getText().toString();
                String Latitud = editLatitud.getText().toString();

                intent.putExtra("Latitud",editLatitud.getText().toString());
                intent.putExtra("Longitud",editLongitud.getText().toString());

                if(validacionDatos())
                {
                    boolean AGGD=dbgps.AgregarDatos(Longitud,Latitud);
                    if(AGGD)
                    {
                        Toast.makeText(getApplicationContext(),"Datos Ingresados",Toast.LENGTH_SHORT).show();

                        editLongitud.setText("");
                        editLatitud.setText("");
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Datos no ingresados",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
//////////////////////////////////////////////////////////
    private void verDatos() {
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor ver = dbgps.verDatos();
                if(ver.getCount()==0)
                {
                    MostrarMensajes("Nop","No se encontro nada");
                }
                StringBuffer buffer=new StringBuffer();
                while (ver.moveToNext())
                {
                    buffer.append("Id: "+ver.getString(0)+"\n");
                    buffer.append("Latitud: "+ver.getString(1)+"\n");
                    buffer.append("Longitud: "+ver.getString(2)+"\n");


                }
                MostrarMensajes("Datos",buffer.toString());
            }
        });
    }
//////////////////////////////////////////////////////////

    public boolean validacionDatos()
    {
        if(editLatitud.getText().toString().isEmpty())
        {
            editLatitud.setError("Ingrese Latitud");
            editLatitud.requestFocus();
            return false;
        }
        if(editLatitud.getText().toString().isEmpty())
        {
            editLongitud.setError("Ingrese Longitud");
            editLongitud.requestFocus();
            return false;
        }
        else
        {
            return true;
        }
    }

//////////////////////////////////////////////////////////

    public void MostrarMensajes(String title, String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}