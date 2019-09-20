using System;
using System.IO;
using System.Reflection;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

using System.Threading.Tasks;
using NModbus.Device;
using NModbus;
using NModbus.Utility;
using System.Net.Sockets;
using System.Diagnostics;

namespace ConsoleApp1
{
    class Conexao
    {



        public string ip;
        //Lista de ips que serão utilizados
        private List<string> clps = new List<string>();
        //Lista de clientes nescessarios para fazer a conexao
        private List<TcpClient> client = new List<TcpClient>();
        //Lista de "mestres" que serao utilizados para realizar os comandos
        private List<IModbusMaster> masters = new List<IModbusMaster>();


        public TcpClient cliente;
        public IModbusMaster master;

        private ModbusFactory factory = new ModbusFactory();
        //"Cronometro para sabermos quanto tempo se passaou desde o ultimo comando enviado"
        public Stopwatch tempo = new Stopwatch();

        public bool isConected = false;
        //Variavel utilizada para saber se é a primeira vez que a conexão foi feita
        public bool first = false;
        //Variavel utilizada para saber se algum comando foi enviado para a clp
        public bool comd = false;
        public bool paralel = false;

        public Conexao(string ip)
        {
            this.ip = ip;
            first = true;
            Thread t1 = new Thread(new ThreadStart(criaConexao));
            t1.Start();
        }
        //recebe a lista de ips e disponibiliza para a classe
        //cria uma thread responsavel por criar a conexão e reconexão
        public Conexao(List<string> clps)
        {
            this.clps = clps;
            first = true;
            //Console.WriteLine(this.clps.ElementAt(0) + " paralelo");
            criaConexapParalel();
            Thread t2 = new Thread(new ThreadStart(hold));
            t2.Start();

            Thread t3 = new Thread(new ThreadStart(leIN));
            t3.Start();

        }

        int cont = 3;
        public void hold()
        {
            tempo.Start();
            Console.WriteLine("entrou " + tempo.Elapsed);
            while (true)
            {
                if (tempo.Elapsed >= TimeSpan.FromSeconds(5))
                {
                    //Console.WriteLine("------------------");
                    int j = 0;
                    for (int i = 0; i < masters.Count; i++)
                    {

                        try
                        {
                            masters.ElementAt(i).ReadInputs(0, 0, 15);
                            //Console.WriteLine("Lendo CLP: " + clps.ElementAt(i) + " " + tempo.Elapsed);
                            tempo.Restart();
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine("Erro CLP: " + clps.ElementAt(i));
                            reconecta(i);
                            //reconecta();

                        }
                    }
                }
            }
            //Console.WriteLine("Lendo");
        }
        public Dictionary<int, List<int>> links = new Dictionary<int, List<int>>();

        public void link(int Entrada, int saida)
        {
            if (!(links.Count == 0))
            {
                if (links.ContainsKey(Entrada))
                {

                    foreach (KeyValuePair<int, List<int>> x in links)
                    {

                        if (x.Key == Entrada)
                        {
                            x.Value.Add(saida);
                            //Console.Write(x.Key + " : ");
                           // x.Value.ForEach(Console.Write);
                           // Console.WriteLine();
                            break;
                        }
                    }

                }
                else
                {
                    List<int> y = new List<int>();
                    y.Add(saida);
                    links.Add(Entrada, y);
                }
            }
            else
            {
                List<int> y = new List<int>();
                y.Add(saida);
                links.Add(Entrada, y);
            }
        }

        int intr = 0;
        bool b = false;

        public void leIN()
        {
            while (true){

                bool[] vet = masters.ElementAt(0).ReadInputs(0, 0, 15);

                for (int i = 0; i < vet.Length; i++)
                {
                    if (vet[i] && !b)
                    {
                        foreach (KeyValuePair<int, List<int>> x in links)
                        {

                            if (x.Key == i)
                            {
                                intr = i;
                                double soma = 0;
                                int[] v = x.Value.ToArray();
                                foreach (int j in v)
                                {
                                    soma += Math.Pow(2, j);
                                }
                                try
                                {
                                    masters.ElementAt(0).WriteSingleRegister(0, 4, (ushort)soma);
                                    b = true;

                                }
                                catch
                                {

                                }
                                }
                        }
                    }else if (!vet[intr])
                    {
                        try
                        {
                            masters.ElementAt(0).WriteSingleRegister(0, 4, (ushort)Math.Pow(2, -1));
                            b = false;

                        }
                        catch
                        {

                        }
                        }
                }
            }
        }

        public void reconecta(int i)
        {
            try
            {
                client.RemoveAt(i);
                masters.RemoveAt(i);


                client.Add(new TcpClient(clps.ElementAt(i), 502));
                //client.ElementAt(client.Count).Client.SetSocketOption(SocketOptionLevel.Socket, SocketOptionName.KeepAlive, true);
                masters.Add(factory.CreateMaster(client.ElementAt(client.Count - 1)));
                masters.ElementAt(masters.Count - 1).Transport.ReadTimeout = 300;



            }
            catch
            {
                Console.WriteLine("reconecta erro");
            }
        }



        //Thread que faz a conexao e reconexao
        public void criaConexapParalel()
        {
            //inicia o "cronometro" para começar a contar quanto tempo se passou desde a primeira conexao
            //tempo.Start();
            // while (1 == 1)
            //  {
            //verifica se um comando foi enviado a clp
            //   if (comd)
            //  {
            //se sim, reinicia o cronometro
            //    tempo.Restart();
            //  comd = false;
            // }
            //verifica se o tempo desde o ultimo comando ou primeira conexao é maior ou igual
            //ao tempo de conexao da clp
            // if (tempo.Elapsed >= TimeSpan.FromSeconds(14) || first)
            // {
            //se sim, deve ser feita a conexao/reconexao 

            //try
            // {
            //Verifica se é a primeira conexao 
            //       if (!first)
            //       {
            //           //se não, fecha a conexao antes de ela acabar
            //           foreach(TcpClient x in client)
            //          {
            //               x.Close();                              
            //           }
            //Limpa a lista de clientes e masters pois eles não servem mais                           
            //           client.Clear();
            //           masters.Clear();

            //   }

            //para cada CLP na lista de ip/clp , é criado um cliente e adicionado a lista de clientes
            for (int i = 0; i < clps.Count; i++)
            {
                try
                {
                    //Console.WriteLine("esse aqi: "+ clps.ElementAt(1));
                    client.Add(new TcpClient(clps.ElementAt(i), 502));
                    //string s = clps.ElementAt(0);
                    //client.Add(new TcpClient(s, 502));
                }
                catch (Exception ex)
                {
                    Console.WriteLine("cliente add: " + ex.Message);
                    //Console.ReadKey();
                }

            }


            //Para cada cliente na lista de clientes cria-se o socket que fara a conexao
            foreach (TcpClient x in client)
            {
                try
                {
                    x.Client.SetSocketOption(SocketOptionLevel.Socket, SocketOptionName.KeepAlive, true);

                }
                catch (Exception ex)
                {
                    Console.WriteLine("client socketoption: " + ex.Message);
                    //Console.ReadKey();
                }



            }
            //Para cada cliente na lista de clientes, é criado um master e adicionado a lista de masters
            for (int i = 0; i < client.Count; i++)
            {
                try
                {
                    masters.Add(factory.CreateMaster(client.ElementAt(i)));
                    masters.ElementAt(i).Transport.ReadTimeout = 300;

                }
                catch
                {
                    Console.WriteLine("MASTER ADD");
                    //Console.ReadKey();
                }
            }

            //tempo.Stop();
            //cronometro é reiniciado pois uma nova conexao foi criada
            // tempo.Restart();
            isConected = true;
            //se for a primeira vez que é criado a conexao, as conexoes subsequentes devem ser fechadas antes
            //do tempo de conexao da CLP
            first = false;
            Console.WriteLine("Reconected!!");
            paralel = true;

            //}
            //catch (exception ex)
            // {
            //    console.writeline("failed: " + ex.message);
            // }
        }

        //  }
        // }
        public void criaConexao()
        {
            tempo.Start();
            while (1 == 1)
            {
                if (comd)
                {
                    tempo.Restart();
                    comd = false;
                }
                if (tempo.Elapsed >= TimeSpan.FromSeconds(14) || first)
                {
                    try
                    {
                        if (!first)
                        {
                            cliente.Close();
                        }
                        cliente = new TcpClient(ip, 502);
                        cliente.Client.SetSocketOption(SocketOptionLevel.Socket, SocketOptionName.KeepAlive, true);
                        master = factory.CreateMaster(cliente);

                        tempo.Stop();
                        tempo.Restart();
                        isConected = true;
                        first = false;
                        Console.WriteLine("Reconected!!");

                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine("Failed: " + ex.Message);
                    }
                }

            }

        }

        public void read()
        {
            int i = 0;
            foreach (IModbusMaster x in masters)
            {
                bool[] vet = x.ReadInputs(0, 0, 15);


                Console.Write("CLP{0}:", i);
                foreach (bool j in vet)
                {
                    Console.Write(" " + j);
                }
                Console.WriteLine();
                i++;
            }
        }



        public void Log(int porta)
        {
            string caminhoExe = string.Empty;
            caminhoExe = Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location);
            string caminhoArquivo = Path.Combine(caminhoExe, "logs.txt");
            if (!File.Exists(caminhoArquivo))
            {
                FileStream arquivo = File.Create(caminhoArquivo);
                arquivo.Close();
            }
            using (StreamWriter w = File.AppendText(caminhoArquivo))
            {
                w.Write("Comando enviado a porta " + porta + " as " + DateTime.Now + "\n");

            }
        }

        public void commando(int porta)
        {
            try
            {
                if (paralel)
                {
                    foreach (IModbusMaster x in masters)
                    {
                        if (porta > 11)
                        {
                            Console.WriteLine("NAO!!!");
                            return;
                        }
                        x.WriteSingleRegister(0, 4, (ushort)Math.Pow(2, porta));
                        comd = true;
                        Log(porta);
                    }
                }
                else
                {
                    master.WriteSingleRegister(0, 4, (ushort)Math.Pow(2, porta));
                    comd = true;



                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Erro: " + ex.Message);
            }
        }



    }
}
