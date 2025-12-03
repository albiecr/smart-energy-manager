import pandas as pd
from sqlalchemy import create_engine

DB_CONNECTION = 'postgresql://postgres:admin@localhost:5432/SmartEnergy'
FILE_ENERGY = 'data/loureiro_energy.csv'
FILE_WEATHER = 'data/weather_aveiro_final.csv'

def executar_etl():
    # 1. Carregar dados de Energia
    print("-> Lendo dados de consumo (Loureiro)... ")
    df_energy = pd.read_csv(FILE_ENERGY)
    df_energy['Time'] = pd.to_datetime(df_energy['Time'])
    df_energy.set_index('Time', inplace=True)