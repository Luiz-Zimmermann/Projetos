using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using System.Diagnostics;

namespace ConsoleApp1
{
    class Program
    {
        

        static void Main(string[] args)
        {
            Conexao conexao;
            List<string> clps = new List<string>();
            clps.Add("192.168.1.71");
            //clps.Add("192.168.1.72");
            clps.Add("192.168.1.73");
            clps.Add("192.168.1.74");

            Console.Write("Escrever em todas? ");
            //string td = Console.ReadLine();

            if (1==1)
            {
                 conexao = new Conexao(clps);
            }
            else
            {
                Console.Write("CLP ID: ");
                Console.WriteLine(""+clps.ElementAt(0));
                 conexao = new Conexao(clps.ElementAt(int.Parse(Console.ReadLine())));
                //Console.ReadKey();
            }
                   

            
            Thread.Sleep(500);
            if (conexao.isConected)
            {
                
                while (1 == 1)
                {
                    try
                    {
                        //DAOclp dao = new DAOclp();
                        //Console.WriteLine(dao.recoveryEverything().Rows[0]["ip"].ToString());

                        //conexao.read();
                        //Console.WriteLine("--------");
                        //conexao.comd = true;
                        //Console.ReadKey();                        
                        //Console.Write("porta: ");
                        //conexao.commando(int.Parse(Console.ReadLine()));

                        Console.Write("Entrada: ");
                        int entrada = int.Parse(Console.ReadLine());

                        Console.Write("saida: ");
                        int saida = int.Parse(Console.ReadLine());
                        conexao.link(entrada, saida);

                        Console.Write("Ver: ");
                        if (Console.ReadLine() == "s")
                        {
                            conexao.leIN();
                        }

                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine(ex.Message);
                    }
                }
                
             }
        }

        public  void reconecta()
        {
          
            while (1 == 1)
            {


            }
        }
    }
}
