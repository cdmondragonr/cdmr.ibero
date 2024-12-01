from experta import *

# Definir las estaciones y sus costos como hechos
class Estacion(Fact):
    """Hecho para representar una estación y su costo asociado"""
    pass

class RutaMinima(KnowledgeEngine):
    @Rule(Estacion(estacion='A'), Estacion(estacion='B', costo=5))
    @Rule(Estacion(estacion='B'), Estacion(estacion='A', costo=5))
    def ruta_ab(self):
        self.declare(Estacion(estacion='A', costo=5))
        self.declare(Estacion(estacion='B', costo=5))

    @Rule(Estacion(estacion='A'), Estacion(estacion='C', costo=10))
    @Rule(Estacion(estacion='C'), Estacion(estacion='A', costo=10))
    def ruta_ac(self):
        self.declare(Estacion(estacion='A', costo=10))
        self.declare(Estacion(estacion='C', costo=10))

    @Rule(Estacion(estacion='B'), Estacion(estacion='D', costo=7))
    @Rule(Estacion(estacion='D'), Estacion(estacion='B', costo=7))
    def ruta_bd(self):
        self.declare(Estacion(estacion='B', costo=7))
        self.declare(Estacion(estacion='D', costo=7))

    @Rule(Estacion(estacion='C'), Estacion(estacion='D', costo=4))
    @Rule(Estacion(estacion='D'), Estacion(estacion='C', costo=4))
    def ruta_cd(self):
        self.declare(Estacion(estacion='C', costo=4))
        self.declare(Estacion(estacion='D', costo=4))

    @Rule(Estacion(estacion='D'), Estacion(estacion='E', costo=6))
    @Rule(Estacion(estacion='E'), Estacion(estacion='D', costo=6))
    def ruta_de(self):
        self.declare(Estacion(estacion='D', costo=6))
        self.declare(Estacion(estacion='E', costo=6))


def calcular_ruta_minima(inicio, destino):
    engine = RutaMinima()

    # Declarar las estaciones de inicio y destino
    engine.declare(Estacion(estacion=inicio))
    engine.declare(Estacion(estacion=destino))
    
    # Ejecutar el motor de inferencia
    engine.reset()
    engine.run()

    # Buscar la ruta y el costo
    ruta = []
    costo_total = 0
    for hecho in engine.facts.values():
        if isinstance(hecho, Estacion):
            if hecho.estacion == inicio or hecho.estacion == destino:
                ruta.append(hecho.estacion)
                costo_total += hecho.costo
    
    return ruta, costo_total


inicio = input("Ingrese la estación de inicio: ").strip()
destino = input("Ingrese la estación de destino: ").strip()

ruta, costo = calcular_ruta_minima(inicio, destino)

if ruta:
    print(f"La mejor ruta es: {' -> '.join(ruta)} con un costo de {costo} minutos.")
else:
    print("No se encontró una ruta válida.")
