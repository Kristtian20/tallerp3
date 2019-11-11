package parqueadero.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import parqueadero.mundo.Parqueadero;

/**
 * Este panel sirve para mostrar el estado actual del parqueadero
 */
public class PanelParqueadero extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Indicadores Puesto Parqueo
     */
    private JTextField puestos[];

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es una referencia al parqueadero que se est� mostrando
     */
    private Parqueadero parqueadero;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye el panel. <br>
     * <b>post: </b>Se construy� el panel
     * @param parq - Es una referencia al parqueadero que se va a mostrar
     */
    public PanelParqueadero( Parqueadero parq )
    {
        parqueadero = parq;
        inicializar( );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Organiza los componentes que se usan para representar el parqueadero
     */
    private void inicializar( )
    {
        setLayout( new GridLayout( 5, 20 ) );
        setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 5, 0 ), new TitledBorder( "Parqueadero" ) ) );
        setPreferredSize( new Dimension( 10, 170 ) );
        puestos = new JTextField[parqueadero.calcularPuestosLibres( )];
        for( int i = 0; i < parqueadero.calcularPuestosLibres( ); i++ )
        {
            puestos[ i ] = new JTextField( );
            puestos[ i ].setText( "" + ( i + 1 ) );
            puestos[ i ].setEnabled( false );
            puestos[ i ].setBackground( Color.BLACK);
            add( puestos[ i ] );
        }
    }

    /**
     * Este m�todo se encarga de actualizar la representaci�n del parqueadero <br>
     * <b>post: </b>Se actualiz� la informaci�n mostrada del parqueadero
     */
    public void refrescarParqueadero( )
    {
       
    }
}
