package parqueadero.mundo;

public class Carro
{
    
    private String placa;

   
    private int horaLlegada;

    
    public Carro( String placa, int horaLlegada )
    {
        this.placa = placa;
        this.horaLlegada = horaLlegada;
    }

    
    public String darPlaca( )
    {
        return placa;
    }

    
    public int darHoraLlegada( )
    {
        return horaLlegada;
    }

   
    public boolean tienePlaca( String placa )
    {
        boolean tienePlaca = false;
        if( placa.equalsIgnoreCase( placa ) )
        {
            tienePlaca = true;
        }
        else
        {
            tienePlaca = false;
        }
        return tienePlaca;
    }

   
    public int darTiempoEnParqueadero( int horaSalida )
    {
        int tiempoParqueadero = horaSalida - horaLlegada + 1;
        return tiempoParqueadero;
    }
}