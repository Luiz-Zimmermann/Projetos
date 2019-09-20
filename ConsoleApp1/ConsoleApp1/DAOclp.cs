using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System.Data;
using Npgsql;

namespace ConsoleApp1
{
    class DAOclp
    {
        //Dictionary<string>
        public List<string> cacheIP = new List<string>();
        static string serverName ="127.0.0.1";
        static string port = "5432";
        static string userName = "postgres";
        static string password = "asdf1234g";
        static string databaseName = "Automacao";
        NpgsqlConnection npgsqlConnection = null;
        string connString = null;
        //Função que vai pegar todos os IPs que estao no banco e colocar na cache
        //sera usada quando, se acontecer, o sistema cair, e tiver que refazer todas as conexoes

        public DAOclp()
        {
            connString = string.Format("Server={0};Port={1};User id={2};Password={3};Database={4};", serverName, port, userName, password, databaseName);
        }

        public DataTable recoveryEverything()
        {
            //comando de recuperacao do banco e colocando na lista
            //cacheIP = select ip from clps

            DataTable dt = new DataTable();

            try
            {
                using (npgsqlConnection = new NpgsqlConnection(connString))
                {
                    //abre a conexao com o PgSQL e define a instrução   SQL
                    npgsqlConnection.Open();
                    string cmdSeleciona = "Select * from Automacao order by gate";

                    using(NpgsqlDataAdapter adpt = new NpgsqlDataAdapter(cmdSeleciona, npgsqlConnection))
                    {
                        adpt.Fill(dt);
                    }
                }
            }
            catch (NpgsqlException ex)
            {
                throw ex;
            }catch(Exception ex)
            {
                throw ex;
            }
            finally
            {
                npgsqlConnection.Close();
            }
            return dt;
        }

        //Função que vair pegar do banco um novo ip cadastrado de uma nova clp e colocar na list
        private void attCLP()
        {
            //adiciona o ip que não esya contido na lista(ip novo)
            //cacheIP.Add();
        }

    }
}
