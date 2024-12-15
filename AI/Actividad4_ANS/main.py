from sklearn.preprocessing import LabelEncoder
import pandas as pd
from sklearn.cluster import KMeans
import numpy as np

# Datos
data = {
    'estacion_origen': ['A', 'B', 'A', 'C', 'B', 'D', 'C', 'D', 'D', 'E'],
    'estacion_destino': ['B', 'A', 'C', 'A', 'D', 'B', 'D', 'C', 'E', 'D'],
    'costo': [5, 5, 10, 10, 7, 7, 4, 4, 6, 6]
}
df = pd.DataFrame(data)

# Codificar estaciones
le = LabelEncoder()
df['origen_encoded'] = le.fit_transform(df['estacion_origen'])
df['destino_encoded'] = le.transform(df['estacion_destino'])

# Seleccionar características para el agrupamiento
X = df[['origen_encoded', 'destino_encoded']]

# Aplicar KMeans para agrupamiento
kmeans = KMeans(n_clusters=3, random_state=42)
df['cluster'] = kmeans.fit_predict(X)

# Añadir la columna de costo a los clusters
df['costo'] = df['costo']

# Mostrar los resultados del clustering
print("Resultados de Clustering:")
print(df)

# Predicción del costo en función del grupo
# Supongamos que queremos predecir el costo de una nueva ruta
origen = 'A'
destino = 'D'
origen_encoded = le.transform([origen])[0]
destino_encoded = le.transform([destino])[0]

# Identificar a qué grupo pertenece la nueva ruta
cluster_pred = kmeans.predict([[origen_encoded, destino_encoded]])[0]

# Filtrar los datos del cluster correspondiente
cluster_data = df[df['cluster'] == cluster_pred]

# Calcular el costo promedio dentro de ese grupo
average_cost = cluster_data['costo'].mean()
print(f"El costo promedio estimado entre {origen} y {destino} es {average_cost:.2f}.")
