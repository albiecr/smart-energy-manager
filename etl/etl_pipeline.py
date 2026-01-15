import pandas as pd
from sqlalchemy import create_engine

DB_CONNECTION = 'postgresql://postgres:admin@localhost:5432/SmartEnergy'
FILE_ENERGY = 'data/loureiro_energy.csv'
FILE_WEATHER = 'data/weather_aveiro_final.csv'

def executar_etl():
    # 1. Carregar dados de Energia
    print("-> Lendo dados de consumo (Loureiro)... ")
    try:
        df_energy = pd.read_csv(FILE_ENERGY)
    except FileNotFoundError:
        print(f"ERRO: Arquivo {FILE_ENERGY} não encontrado. Verifique se a pasta 'data' existe.")
        return

    df_energy['Time'] = pd.to_datetime(df_energy['Time'])
    df_energy.set_index('Time', inplace=True)

    # Cálculo consumo total do Hotel
    print("-> Calculando a demanda total (Somando medidores) ")
    cols_medidores = [col for col in df_energy.columns if 'Energy_Meter' in col]
    df_energy['total_active_power_kw'] = df_energy[cols_medidores].sum(axis=1)

    # Manter só a coluna total para facilitar
    df_final_energy = df_energy[['total_active_power_kw']].copy()

    # 2. Carregar dados climáticos
    print("-> Lendo dados climáticos (Aveiro)... ")
    try:
        df_weather = pd.read_csv(FILE_WEATHER)
    except FileNotFoundError:
        print(f"ERRO: Arquivo {FILE_WEATHER} não encontrado. Verifique se a pasta 'data' existe.")
        return

    df_weather['time'] = pd.to_datetime(df_weather['time'])
    df_weather.set_index('time', inplace=True)

    # Selecionar colunas úteis
    cols_weather = ['Avg_Temp', 'Total_Global_Rad', 'Avg_Rel_Humidity']

    cols_existentes = [col for col in cols_weather if col in df_weather.columns]
    df_weather_clean = df_weather[cols_existentes].copy()

    # 3. Combinar dados de Energia e Clima (Merge)
    print("-> Combinando dados de consumo e climáticos... ")
    df_merged = df_final_energy.join(df_weather_clean, how='inner')

    df_merged.reset_index(inplace=True)
    df_merged.rename(columns={
        'Time': 'timestamp',
        'total_active_power_kw': 'demand_kw',
        'Avg_Temp': 'temperature_c',
        'Total_Global_Rad': 'solar_irradiance',
        'Avg_Rel_Humidity': 'humidity'
    }, inplace=True)

    # 4. Salvar no banco de dados
    engine = create_engine(DB_CONNECTION)
    df_merged.to_sql('hotel_readings', engine, if_exists='replace', index=False)

    print(f"SUCESSO! {len(df_merged)} linhas inseridas na tabela 'hotel_readings'.")

if __name__ == "__main__":
    executar_etl()

