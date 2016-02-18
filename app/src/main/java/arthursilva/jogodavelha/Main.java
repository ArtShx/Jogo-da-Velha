package arthursilva.jogodavelha;

import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;


public class Main extends ActionBarActivity {


    public final String QUADRADO = "quadrado";

    private final String BOLA = "O";
    private final String XIS  = "X";

    int[][] estadoFinal = new int[][]
    {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},

            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},

            {1, 5, 9},
            {3, 5, 7},
    };

    public String getLastPlay() {
        return lastPlay;
    }

    public void setLastPlay(String lastPlay) {
        this.lastPlay = lastPlay;
    }

    private String lastPlay = "X";

    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(getLayoutInflater().inflate(R.layout.main, null));
        setContentView(getView());
        setEnableQuadrado(false);
    }

    public Button getQuadrado(int tagNum)
    {
        return (Button) getView().findViewWithTag(QUADRADO+tagNum);
    }

    public void clickQuadrado(View v)
    {
        if(!isFim())
        {
            if (((Button) v).getText().equals(""))
            {

                if (getLastPlay().equals("O")) {
                    ((Button) v).setText(XIS);
                    setLastPlay(XIS);
                } else {
                    ((Button) v).setText(BOLA);
                    setLastPlay(BOLA);
                }
                isFim();
            } else {
                //  Toast.makeText(getView().getContext(),v.getTag().toString() ,Toast.LENGTH_SHORT).show();
                Toast.makeText(getView().getContext(), "Escolha outro campo", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void setColorBlack()
    {
        for(int i = 1; i <= 9; i++)
        {
            getQuadrado(i).setTextColor(getResources().getColor(R.color.black));
        }
    }

    public void setColorQuadrado(int btn, int color)
    {
        getQuadrado(btn).setTextColor(getResources().getColor(color));
    }
    public boolean isFim()
    {
        for(int i = 0; i <= 7; ++i)
        {
            String s1 = getQuadrado( estadoFinal[i][0] ).getText().toString();
            String s2 = getQuadrado( estadoFinal[i][1] ).getText().toString();
            String s3 = getQuadrado( estadoFinal[i][2] ).getText().toString();

            if(!s1.equals("") &&
               !s2.equals("") &&
               !s3.equals("") )
            {
                if(s1.equals(s2) && s2.equals(s3))
                {
                    setColorQuadrado(estadoFinal[i][0], R.color.red);
                    setColorQuadrado(estadoFinal[i][1], R.color.red);
                    setColorQuadrado(estadoFinal[i][2], R.color.red);
                    Toast.makeText(getView().getContext(), "Fim de jogo", Toast.LENGTH_SHORT).show();
                    return true;
                    //setEnableQuadrado(false);
                }
            }
        }
        return false;
    }

    public void newGame(View v)
    {
        RadioButton rbtO = (RadioButton) getView().findViewById(R.id.rbtO);
        RadioButton rbtX = (RadioButton) getView().findViewById(R.id.rbtX);
        if(rbtO.isChecked())
            setLastPlay(XIS);
        else
            setLastPlay(BOLA);

        setColorBlack();
        setEnableQuadrado( true );
        for(int i = 1; i <= 9; i++ )
        {
            if(getQuadrado(i)!= null)
            {
                getQuadrado(i).setText("");
            }
        }
    }

    public void setEnableQuadrado(boolean b)
    {
        for(int i = 1; i <= 9; i++)
        {
            getQuadrado(i).setEnabled(b);
        }
    }

}
