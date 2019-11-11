package parqueadero.mundo;

/**
 * Esta clase representa un puesto en el parqueadero
 */

public class Puesto
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Eventual carro en el puesto. Si no hay ninguno, carro == null */

    /** Numero del puesto dentro del parqueadero */

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea un puesto vacío <br>
     * <b>post: </b>Se creó un puesto vacío
     * @param nPuestoP - Número de puesto
     */
    private Carro carro;

    //Número del puesto dentro del parqueadero.
    
    private int numeroPuesto;

    public Puesto( int nPuestoP )
    {
        carro = null;
        numeroPuesto = nPuestoP;
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Retorna el carro del puesto. Si no hay ningún carro retorna null.
     * @return carro
     */
    public Carro darCarro( )
    {
        return carro;
    }

    /**
     * Indica si el puesto está ocupado.
     * @return Retorna true si el puesto está ocupado. Retorna false en caso contrario.
     */
    public boolean estaOcupado( )
    {
        boolean ocupado = carro != null;
        return ocupado;
    }

    /**
     * Parquea un carro en el puesto <br>
     * <b>pre: </b>El puesto está vacío <br>
     * <b>post: </b> El puesto ahora está ocupado por el carro carroP
     * @param carroP - Carro que se está parqueando - carroP != null
     */
    public void parquearCarro( Carro carroP )
    {
        carro = carroP;
    }

    /**
     * Saca el carro del puesto. <br>
     * <b>post: </b>El puesto está vacío
     */
    public void sacarCarro( )
    {
        carro = null;
    }

    /**
     * Retorna el número del puesto
     * @return nPuesto
     */
    public int darNumeroPuesto( )
    {
        return numeroPuesto;
    }

   /**
     * Indica si el carro tiene la placa recibida
     * @param placa
     * @return true si tiene la placa, false de lo contrario
     */
    public boolean tieneCarroConPlaca( String placa )
    {
        boolean tieneCarro = true;

        if( carro == null )
        {
            tieneCarro = false;
        }
        else if( carro.tienePlaca( placa ) )
        {
            tieneCarro = true;
        }
        else
        {
            tieneCarro = false;
        }

        return tieneCarro;
    }

}

