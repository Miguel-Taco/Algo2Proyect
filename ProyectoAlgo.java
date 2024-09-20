import java.util.*;

// Clase Bus
class Bus {
    private String idBus;
    private String tipo;
    private int capacidad;
    private String estado;
    private String idRuta;
    private Conductor conductorAsignado;

    public Bus(String idBus, String tipo, int capacidad) {
        this.idBus = idBus;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.estado = "Disponible";
    }

    public void asignarConductor(Conductor conductor) {
        this.conductorAsignado = conductor;
    }

    public void asignarRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public void realizarMantenimiento() {
        this.estado = "En Mantenimiento";
    }

    public String obtenerEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "ID Bus: " + idBus + ", Tipo: " + tipo + ", Capacidad: " + capacidad + ", Estado: " + estado;
    }
}

// Clase Conductor
class Conductor {
    private String idConductor;
    private String nombre;
    private String numeroLicencia;
    private String telefono;
    private List<Bus> busesAsignados = new ArrayList<>();

    public Conductor(String idConductor, String nombre, String numeroLicencia, String telefono) {
        this.idConductor = idConductor;
        this.nombre = nombre;
        this.numeroLicencia = numeroLicencia;
        this.telefono = telefono;
    }

    public void asignarBus(Bus bus) {
        busesAsignados.add(bus);
    }

    public void verHorario() {
        System.out.println("Horario del conductor: " + nombre);
        for (Bus bus : busesAsignados) {
            System.out.println(bus);
        }
    }

    @Override
    public String toString() {
        return "ID Conductor: " + idConductor + ", Nombre: " + nombre + ", Licencia: " + numeroLicencia;
    }
}

// Clase Ruta
class Ruta {
    private String idRuta;
    private String lugarInicio;
    private String lugarDestino;
    private int distancia;
    private int duracionEstimada;
    private List<String> paradas = new ArrayList<>();
    private List<Bus> busesEnRuta = new ArrayList<>();

    public Ruta(String idRuta, String lugarInicio, String lugarDestino, int distancia, int duracionEstimada) {
        this.idRuta = idRuta;
        this.lugarInicio = lugarInicio;
        this.lugarDestino = lugarDestino;
        this.distancia = distancia;
        this.duracionEstimada = duracionEstimada;
    }

    public void agregarBusARuta(Bus bus) {
        busesEnRuta.add(bus);
        bus.asignarRuta(this.idRuta);
    }

    public void eliminarBusDeRuta(Bus bus) {
        busesEnRuta.remove(bus);
    }

    public String obtenerInfoRuta() {
        return "Ruta: " + lugarInicio + " a " + lugarDestino + ", Distancia: " + distancia + " km, Duración: " + duracionEstimada + " horas";
    }

    @Override
    public String toString() {
        return obtenerInfoRuta();
    }
}

// Clase Boleto
class Boleto {
    private String idBoleto;
    private String nombrePasajero;
    private String idBus;
    private String idRuta;
    private Date fechaViaje;
    private String numeroAsiento;
    private double precio;

    public Boleto(String idBoleto, String nombrePasajero, String idBus, String idRuta, Date fechaViaje, String numeroAsiento, double precio) {
        this.idBoleto = idBoleto;
        this.nombrePasajero = nombrePasajero;
        this.idBus = idBus;
        this.idRuta = idRuta;
        this.fechaViaje = fechaViaje;
        this.numeroAsiento = numeroAsiento;
        this.precio = precio;
    }

    public void generarBoleto() {
        System.out.println("Boleto generado para " + nombrePasajero + " en el Bus " + idBus + " para la ruta " + idRuta);
    }

    public void cancelarBoleto() {
        System.out.println("El boleto " + idBoleto + " ha sido cancelado.");
    }

    @Override
    public String toString() {
        return "ID Boleto: " + idBoleto + ", Pasajero: " + nombrePasajero + ", Asiento: " + numeroAsiento + ", Precio: $" + precio;
    }
}

// Clase Paquete (Encomiendas)
class Paquete {
    private String idPaquete;
    private String nombreRemitente;
    private String nombreDestinatario;
    private double peso;
    private String dimensiones;
    private double precio;
    private String origen;
    private String destino;
    private String estado;

    public Paquete(String idPaquete, String nombreRemitente, String nombreDestinatario, double peso, String dimensiones, String origen, String destino) {
        this.idPaquete = idPaquete;
        this.nombreRemitente = nombreRemitente;
        this.nombreDestinatario = nombreDestinatario;
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.origen = origen;
        this.destino = destino;
        this.estado = "Pendiente";
        this.precio = calcularPrecio();
    }

    public double calcularPrecio() {
        return peso * 10; // Fórmula simple basada en el peso
    }

    public void actualizarEstado(String estado) {
        this.estado = estado;
    }

    public void rastrearPaquete() {
        System.out.println("Paquete de " + origen + " a " + destino + " está actualmente " + estado);
    }

    @Override
    public String toString() {
        return "ID Paquete: " + idPaquete + ", Remitente: " + nombreRemitente + ", Destinatario: " + nombreDestinatario + ", Precio: $" + precio;
    }
}

// Clase SistemaDeReservas
class SistemaDeReservas {
    private List<Bus> buses = new ArrayList<>();
    private List<Ruta> rutas = new ArrayList<>();
    private List<Boleto> boletos = new ArrayList<>();
    private List<Conductor> conductores = new ArrayList<>();
    private List<Paquete> paquetes = new ArrayList<>();

    public void agregarBus(Bus bus) {
        buses.add(bus);
    }

    public void agregarConductor(Conductor conductor) {
        conductores.add(conductor);
    }

    public void agregarRuta(Ruta ruta) {
        rutas.add(ruta);
    }

    public void reservarBoleto(Boleto boleto) {
        boletos.add(boleto);
        boleto.generarBoleto();
    }

    public void gestionarPaquetes(Paquete paquete) {
        paquetes.add(paquete);
        System.out.println("Paquete agregado: " + paquete);
    }

    public void mostrarBuses() {
        System.out.println("Lista de Buses:");
        for (Bus bus : buses) {
            System.out.println(bus);
        }
    }

    public void mostrarRutas() {
        System.out.println("Lista de Rutas:");
        for (Ruta ruta : rutas) {
            System.out.println(ruta);
        }
    }
}

// Clase principal
public class SistemaGestionTransporte {
    public static void main(String[] args) {
        SistemaDeReservas sistema = new SistemaDeReservas();

        // Crear buses
        Bus bus1 = new Bus("B1", "Económico", 50);
        Bus bus2 = new Bus("B2", "Premium", 30);
        sistema.agregarBus(bus1);
        sistema.agregarBus(bus2);

        // Crear conductores
        Conductor conductor1 = new Conductor("C1", "Juan Pérez", "L123", "555-1234");
        sistema.agregarConductor(conductor1);
        bus1.asignarConductor(conductor1);

        // Crear rutas
        Ruta ruta1 = new Ruta("R1", "Lima", "Arequipa", 1000, 16);
        sistema.agregarRuta(ruta1);
        ruta1.agregarBusARuta(bus1);

        // Crear un boleto
        Boleto boleto1 = new Boleto("T1", "Ana", "B1", "R1", new Date(), "A1", 100.0);
        sistema.reservarBoleto(boleto1);

        // Crear un paquete
        Paquete paquete1 = new Paquete("P1", "Ana", "Carlos", 5.0, "30x30x30", "Lima", "Arequipa");
        sistema.gestionarPaquetes(paquete1);

        // Mostrar buses y rutas
        sistema.mostrarBuses();
        sistema.mostrarRutas();
    }
}

}
