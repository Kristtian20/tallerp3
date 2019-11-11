
package parqueadero.mundo;

/**
 * Esta clase representa un parqueadero con TAMANO puestos.
 */
public class Parqueadero
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indica el número de puestos en el parqueadero.
     */
    public static final int TAMANO = 87;

    /**
     * Es el código de error para cuando el parqueadero está lleno.
     */
    public static final int NO_HAY_PUESTO = -1;

    /**
     * Es el código de error para cuando el parqueadero está cerrado.
     */
    public static final int PARQUEADERO_CERRADO = -2;

    /**
     * Es el código de error para cuando el carro que se busca no está dentro del parqueadero.
     */
    public static final int CARRO_NO_EXISTE = -3;

    /**
     * Es el código de error para cuando ya hay un carro en el parqueadero con la placa de un nuevo carro que va a entrar.
     */
    public static final int CARRO_YA_EXISTE = -4;

    /**
     * Es la hora a la que se abre el parqueadero.
     */
    public static final int HORA_INICIAL = 6;

    /**
     * Es la hora a la que se cierra el parqueadero.
     */
    public static final int HORA_CIERRE = 20;

    /**
     * Es la tarifa inicial del parqueadero.
     */
    public static final int TARIFA_INICIAL = 4700;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Contenedora de tamaño fijo de puestos */

    
    /** Tarifa por hora en el parqueadero */

    /** Valor recibido en la caja del parqueadero */

    /** Hora actual en el parqueadero */

    /** Indica si el parqueadero esta abierto */

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea un parqueadero con su información básica. <br>
     * <b>post: </b> Se creó un parqueadero con la tarifa establecida
     * @param tarifa - Tarifa del parqueadero
     */
    private Puesto puestos[];

    //precio por hora en el parqueadero.
     
    private int tarifa;

     //Valor recibido en la caja del parqueadero.
     
    private int caja;

    //Hora actual en el parqueadero.
    
    public int horaActual;

    //Indica si el parqueadero esta abierto.
    
    private boolean abierto;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea un parqueadero con su información básica. <br>
     * <b>post: </b> Se creó un parqueadero con la tarifa establecida
     * @param nTarifa - Tarifa del parqueadero
     */
    public Parqueadero( )
    {
        horaActual = HORA_INICIAL;
        abierto = true;
        tarifa = TARIFA_INICIAL;
        caja = 0;
        // Crea el arreglo de puestos e inicializa cada uno de ellos
        puestos = new Puesto[TAMANO];
        for( int i = 0; i < TAMANO; i++ )
            puestos[ i ] = new Puesto( i );

    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Ingresa un un carro al parqueadero <br>
     * <b>post: </b>El carro quedó parqueado en el puesto indicado
     * @param placa - Placa del carro que ingresa - placa!=null
     * @return puesto en el que debe parquear. <br>
     *         Si el parqueadero está lleno retorna el valor NO_HAY_PUESTO. <br>
     *         Si el parqueadero está cerrado retorna el valor PARQUEADERO_CERRADO.
     */
    public String darPlacaCarro( int Posicion )
    {
        String respuesta = "";
        if( estaOcupado( Posicion ) )
        {
            respuesta = "Placa: " + puestos[ Posicion ].darCarro( ).darPlaca( );
        }
        else
        {
            respuesta = "No hay un carro en esta posición";
        }

        return respuesta;
    }

    /**
     * Ingresa un un carro al parqueadero <br>
     * <b>post: </b>El carro quedó parqueado en el puesto indicado
     * @param placa - Placa del carro que ingresa - placa!=null
     * @return puesto en el que debe parquear. <br>
     *         Si el parqueadero está lleno retorna el valor NO_HAY_PUESTO. <br>
     *         Si el parqueadero está cerrado retorna el valor PARQUEADERO_CERRADO.
     */
    public int entrarCarro( String placa )
    {
        int numPuesto = 0;
        if( !abierto )
        {
            numPuesto = PARQUEADERO_CERRADO;
        }
        else
        {
            // Buscar en el parqueadero un carro con la placa indicada
            int numPuestoCarro = buscarPuestoCarro( placa.toUpperCase( ) );
            if( numPuestoCarro != CARRO_NO_EXISTE )
            {
                numPuesto = CARRO_YA_EXISTE;
            }

            // Buscar un puesto libre para el carro y agregarlo
            numPuesto = buscarPuestoLibre( );
            if( numPuesto != NO_HAY_PUESTO )
            {
                Carro carroEntrando = new Carro( placa, horaActual );
                puestos[ numPuesto ].parquearCarro( carroEntrando );
            }
        }

        return numPuesto;
    }

    /**
     * Sirve para sacar un carro del parqueadero y saber la cantidad de dinero que debe pagar <br>
     * <b>post: </b> El carro salió del parqueadero y el puesto que ocupaba, ahora está libre
     * @param placa - Placa del carro que va a salir - placa != null
     * @return Retorna el valor a pagar. Si el carro no se encontraba dentro del parqueadero entonces retorna CARRO_NO_EXISTE. Si el parqueadero ya estaba cerrado retorna
     *         PARQUEADERO_CERRADO.
     */
    public int sacarCarro( String placa )
    {
        int porPagar = 0;
        if( !abierto )
        {
            porPagar = PARQUEADERO_CERRADO;
        }
        else
        {
            int numPuesto = buscarPuestoCarro( placa.toUpperCase( ) );
            if( numPuesto == CARRO_NO_EXISTE )
            {
                porPagar = CARRO_NO_EXISTE;
            }
            else
            {
                Carro carro = puestos[ numPuesto ].darCarro( );
                int nHoras = carro.darTiempoEnParqueadero( horaActual );
                porPagar = nHoras * tarifa;
                caja = caja + porPagar;
                puestos[ numPuesto ].sacarCarro( );
                porPagar = porPagar;
            }
        }

        return porPagar;
    }

    /**
     * Indica la cantidad de dinero que hay en la caja.
     * @return ingresos
     */
    public int darMontoCaja( )
    {
        return caja;
    }

    /**
     * Indica la cantidad de puestos libres que hay.
     * @return El número de espacios vacíos en el parqueadero.
     */
    public int calcularPuestosLibres( )
    {
        int puestosLibres = 0;
        for( Puesto puesto : puestos )
        {
            if( !puesto.estaOcupado( ) )
            {
                puestosLibres = puestosLibres + 1;
            }
        }
        return puestosLibres;
    }

    /**
     * Cambia la tarifa actual del parqueadero
     * @param nTarifa - Tarifa nueva del parqueadero
     */
    public void cambiarTarifa( int nTarifa )
    {
        tarifa = nTarifa;
    }

    /**
     * Busca un puesto libre en el parqueadero y lo retorna. Si no encuentra retorna el valor NO_HAY_PUESTO
     * @return número del puesto libre
     */
    private int buscarPuestoLibre( )
    {
        int puesto = NO_HAY_PUESTO;
        for( int i = 0; i < TAMANO && puesto == NO_HAY_PUESTO; i++ )
        {
            if( !puestos[ i ].estaOcupado( ) )
            {
                puesto = i;
            }
        }
        return puesto;
    }

    /**
     * Indica el número de puesto en el que se encuentra el carro con una placa dada. Si no lo encuentra retorna el valor CARRO_NO_EXISTE
     * @param placa - Placa del carro que se busca - placa != null
     * @return número del puesto en el que se encuentra el carro
     */
    private int buscarPuestoCarro( String placa )
    {
        int puesto = CARRO_NO_EXISTE;
        for( int i = 0; i < TAMANO && puesto == CARRO_NO_EXISTE; i++ )
        {
            if( puestos[ i ].tieneCarroConPlaca( placa ) )
            {
                puesto = i;
            }
        }
        return puesto;
    }

    /**
     * Avanza una hora en el parqueadero. Si la hora actual es igual a la hora de cierre, el parqueadero se cierra.
     */
    public void avanzarHora( )
    {
        if( horaActual <= HORA_CIERRE )
        {
            horaActual = ( horaActual + 1 );
        }
        if( horaActual == HORA_CIERRE )
        {
            abierto = false;
        }
    }

    /**
     * Retorna la hora actual
     * @return horaActual
     */
    public int darHoraActual( )
    {
        return horaActual;
    }

    /**
     * Indica si el parqueadero está abierto
     * @return Retorna true si el parqueadero está abierto. False en caso contrario
     */
    public boolean estaAbierto( )
    {
        return abierto;
    }

    /**
     * Retorna la tarifa por hora del parqueadero
     * @return tarifa
     */
    public int darTarifa( )
    {
        return tarifa;
    }

    /**
     * Indica si el puesto i está ocupado
     * @param i - El puesto que se quiere saber si está ocupado - i >= 0 y i < puestos.length
     * @return Retorna true si el puesto está ocupado. False en caso contrario.
     */
    public boolean estaOcupado( int i )
    {
        boolean ocupado = puestos[ i ].estaOcupado( );
        return ocupado;
    }

    //-----------------------------------------------------------------
    // Puntos de Extension
    //-----------------------------------------------------------------

    /**
     * Este método ejecuta la opción 1 y retorna el resultado de la operación
     * @return respuesta1
     */
    public int metodo1( )
    {
        int puestosocu = 0;
        int a ;
        
        for( Puesto puesto : puestos )
        {
            if( puesto.estaOcupado( ) )
            {
                puestosocu = puestosocu + 1;
            }
        }
        //a =  HORA_CIERRE-horaActual;
        return puestosocu;
       
    }

    /**
     * Este método ejecuta la opción 2 y retorna el resultado de la operación
     * @return respuesta2
     */
    public String metodo2( )
    {
        int puestosocu = 0;
        int numPuesto = buscarPuestoLibre( );
        
        for( Puesto puesto : puestos )
        {
            if( puesto.estaOcupado( ) )
            {
                puestosocu = puestosocu + 1;
            }
        }
        
        for( int i = 0; i < TAMANO; i++ ){
            
            if( numPuesto != puestosocu )
            {
                
                return "SI hay espacios entre carros.";
            }else{
                return "NO hay espacios entre carros.";
            }
        }
        return null;
        
    }

}