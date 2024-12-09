from sklearn.preprocessing import LabelEncoder
import pandas as pd

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

from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestRegressor

# Seleccionar características y etiquetas
X = df[['origen_encoded', 'destino_encoded']]
y = df['costo']

# Dividir en conjuntos de entrenamiento y prueba
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Entrenar el modelo
model = RandomForestRegressor(random_state=42)
model.fit(X_train, y_train)

# Evaluar
score = model.score(X_test, y_test)
print(f"R2 Score: {score}")


# Predicción
origen = 'A'
destino = 'D'
origen_encoded = le.transform([origen])[0]
destino_encoded = le.transform([destino])[0]

predicted_cost = model.predict([[origen_encoded, destino_encoded]])
print(f"El costo predicho entre {origen} y {destino} es {predicted_cost[0]:.2f}.")
