# -*- coding:utf-8 -*-
import psycopg2
import configparser
import sys

from os import listdir
from os.path import isfile, join

if __name__ == '__main__':
    config = configparser.ConfigParser()
    config.read(".env")

    DB_HOST = config.get("DB", "DB_HOST")
    DB_PORT = config.get("DB", "DB_PORT")
    DB_DB = config.get("DB", "DB")
    DB_USER = config.get("DB", "DB_USER")
    DB_PWD = config.get("DB", "DB_PWD")

    try:
        migrations = [f for f in sorted(listdir('../migrations')) if isfile(join('../migrations', f))]
        conn = psycopg2.connect(f'dbname={DB_DB} host={DB_HOST} port={DB_PORT} user={DB_USER} password={DB_PWD}')
        cur = conn.cursor()

        cur.execute('SELECT name FROM migrations')
        rset = cur.fetchall()
        applied_migrations = list()
        for r in rset:
            applied_migrations.append(r[0])

        nenhuma_migration_aplicada = True
        for m in migrations:
            if m not in applied_migrations:
                nenhuma_migration_aplicada = False
                print ('Aplicando migration ' + m + '...')
                fid = open(join('../migrations', m))
                content = fid.read()
                fid.close()

                try:
                    cur.execute(content)
                except Exception as e:
                    if type(e).__module__ != 'psycopg2.errors':
                        raise e
                    print ('Erro aplicando migration.')
                    print (e)
                    resp = input('Deseja forçar a marcação da migration como aplicada [s/n]? ')
                    if resp != 'S' and resp != 's':
                        break
                    conn.reset()
                cur.execute('INSERT INTO migrations (name) VALUES (%s)', (m,))
                conn.commit()
                print ('Migration ' + m + ' aplicada com sucesso!')

        if nenhuma_migration_aplicada:
            print ('Nenhuma migration aplicada.')
    except Exception as e:
        print ('Ocorreu um erro ao realizar realizar a migração.')
        print (e)
        sys.exit(-1)
    finally:
        cur.close()
        conn.close()
